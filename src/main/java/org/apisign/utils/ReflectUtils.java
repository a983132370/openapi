package org.apisign.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.util.*;

/**
 * @Description: 反射工具
 */
public class ReflectUtils {
	private static final Logger log = LoggerFactory.getLogger(ReflectUtils.class);

	private static void getAllFields(Class<?> clazz, Map<String, Field> fieldMap) {
		if(clazz != null){
			if(clazz.getSuperclass() != null){
				//获取父级字段
				getAllFields(clazz.getSuperclass(),fieldMap);
				getFieldsOfSelf(clazz, fieldMap);
			}else {
				//获取自己字段
				getFieldsOfSelf(clazz, fieldMap);
			}
		}
	}

	private static void getFieldsOfSelf(Class<?> clazz, Map<String, Field> fieldMap) {
		//获取自己字段
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			fieldMap.put(field.getName(),field);
		}
	}

	private static Field getFieldOfTarget( Class<?> clazz,  String key) {
		Map<String,Field> fieldMap = new HashMap<>();
		getAllFields(clazz,fieldMap);
		return fieldMap.get(key);
	}

	/**
	 * 获取某对象的属性及值 不包含父类
	 * @param obj
	 * @return
	 */
	public static Set<String> getFields(Object obj,String... ignoreProperties) {
		Set<String> ignores = new HashSet<>();
		ignores.addAll(Arrays.asList(ignoreProperties));

		Set<String > fieldSet = new HashSet<>();
		Class<?> c = obj.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields){
			if(ignores.contains(field.getName())){
				continue;
			}
			fieldSet.add(field.getName());
		}
		return fieldSet;
	}
	/**
	 * 获取某对象的属性及值 不包含父类
	 * @param obj
	 * @return
	 */
	public static Map<String,Object> getFieldAndValue(Object obj,String... ignoreProperties) {
		Set<String> ignores = new HashSet<>();
		ignores.addAll(Arrays.asList(ignoreProperties));

		Map<String,Object> map = new HashMap<>();
		Class<?> c = obj.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields){
			if(ignores.contains(field.getName())){
				continue;
			}
			field.setAccessible(true); // 设置些属性是可以访问的
			try {
				 if(field.get(obj) != null){
					 map.put(field.getName(),field.get(obj));
				 }
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 获取某对象的属性及值 包含父类
	 * @param obj
	 * @return
	 */
	public static Map<String,Object> getAllFieldAndValue(Object obj,String... ignoreProperties) {
		Set<String> ignores = new HashSet<>();
		ignores.addAll(Arrays.asList(ignoreProperties));

		Map<String,Field> fieldsMap = new HashMap<>();
		getAllFields(obj.getClass(),fieldsMap);

		Map<String,Object> map = new HashMap<>();
		for (Map.Entry<String,Field> entry:fieldsMap.entrySet()){
			Field field = entry.getValue();
			if(ignores.contains(field.getName())){
				continue;
			}
			field.setAccessible(true); // 设置些属性是可以访问的
			try {
				if(field.get(obj) != null){
					map.put(field.getName(),field.get(obj));
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 获取obj对象fieldName的Field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if(field!=null){
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName,
			Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
}
