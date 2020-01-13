package org.apisign.request;

import org.apisign.config.ApiConfig;
import org.apisign.po.base.ApiBasePo;

/**
 * 创建接口请求对象
 */
public class ApiCreateRequest extends AbstractApiRequest {
    private final static String ROUTER = "/create";

    public ApiCreateRequest(ApiBasePo apiBasePo, ApiConfig apiConfig) {
        super(apiBasePo, apiConfig, ROUTER);
    }


}
