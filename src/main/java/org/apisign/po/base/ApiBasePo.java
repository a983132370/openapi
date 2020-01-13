package org.apisign.po.base;

import java.io.Serializable;

/**
 * api调用基础参数类
 */
public class ApiBasePo implements Serializable {

    /**
     * 应用key
     */
    private String appKey;
    /**
     * 参数签名
     */
    private String sign;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 版本 默认版本 1.0
     */
    private String version = "1.0";

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
