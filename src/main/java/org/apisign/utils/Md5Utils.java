package org.apisign.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apisign.po.base.ApiBasePo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

/**
 * md5加密工具
 */
public class Md5Utils {

    /**
     * 签名
     * @param apiBasePo
     * @return
     * @throws IOException
     */
    public static String sign(ApiBasePo apiBasePo, String signKey, String charset) throws IOException {
        if(apiBasePo == null){
            throw new RuntimeException("params can't be null!");
        }
        if(signKey == null || "".equals(signKey)){
            throw new RuntimeException("params can't be null!");
        }
        return sign(ReflectUtils.getAllFieldAndValue(apiBasePo),signKey,charset);
    }
    /**
     * 签名
     * @param params
     * @return
     * @throws IOException
     */
    public static String sign(Map<String, Object> params, String signKey, String charset) throws IOException {

        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder query = new StringBuilder();
        for (String key : keys) {
            Object value = params.get(key);
            query.append(key).append(value);
        }
        String text = DigestUtils.md5Hex(query.toString()) + signKey;
        return DigestUtils.md5Hex(getContentBytes(text, charset));
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }


} 