package org.apisign.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apisign.exception.ApiParamsException;
import org.apisign.po.base.ApiBasePo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

/**
 * md5加密工具
 */
public class ApiSignature {

    private static final String CHARSET = "UTF-8";
    private final static String SIGN = "sign";

    /**
     * 签名校验
     *
     * @param params
     * @return
     * @throws IOException
     */
    public static boolean validateSign(Map<String, Object> params, String signKey) {
        if (params == null) {
            throw new ApiParamsException("params can't be null!");
        }
        if (signKey == null || "".equals(signKey)) {
            throw new ApiParamsException("params can't be null!");
        }
        Object signObj = params.get(SIGN);
        if (signObj == null) {
            return false;
        }
        String paramSign = signObj.toString();
        params.remove(SIGN);
        String sign = sign(params, signKey);
        return paramSign.equals(sign);
    }

    /**
     * 签名
     *
     * @param apiBasePo
     * @return
     * @throws IOException
     */
    public static String sign(ApiBasePo apiBasePo, String signKey) {
        if (apiBasePo == null) {
            throw new ApiParamsException("params can't be null!");
        }
        if (signKey == null || "".equals(signKey)) {
            throw new ApiParamsException("params can't be null!");
        }
        return sign(ReflectUtils.getAllFieldAndValue(apiBasePo), signKey);
    }

    /**
     * 签名
     *
     * @param params
     * @return
     */
    public static String sign(Map<String, Object> params, String signKey) {
        if (signKey == null || "".equals(signKey)) {
            throw new ApiParamsException("params can't be null!");
        }
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder query = new StringBuilder();
        for (String key : keys) {
            Object value = params.get(key);
            query.append(key).append(value);
        }
        String text = DigestUtils.md5Hex(query.toString()) + signKey;
        return DigestUtils.md5Hex(getContentBytes(text));
    }

    private static byte[] getContentBytes(String content) {
        try {
            return content.getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + CHARSET);
        }
    }


} 