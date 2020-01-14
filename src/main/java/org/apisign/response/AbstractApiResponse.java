package org.apisign.response;

import org.apisign.config.ApiConfig;

/**
 * 响应
 */
public abstract class AbstractApiResponse {
    private ApiConfig apiConfig;

    public AbstractApiResponse(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }
}
