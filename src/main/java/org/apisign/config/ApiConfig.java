package org.apisign.config;

/**
 * api配置
 */
public class ApiConfig {
    /**
     * api调用地址
     */
    private String apiUrl  ;
    /**
     * 应用key
     */
    private String appKey;
    /**
     * 应用Secret
     */
    private String appSecret;

    public ApiConfig() {
    }

    public ApiConfig(String apiUrl, String appKey, String appSecret) {
        if (apiUrl == null){
            throw new RuntimeException("apiUrl can't be null!");
        }
        if (appKey == null){
            throw new RuntimeException("appKey can't be null!");
        }
        if (appSecret == null){
            throw new RuntimeException("appSecret can't be null!");
        }
        this.apiUrl = apiUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
