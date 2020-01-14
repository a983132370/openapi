package org.apisign.request;

import com.alibaba.fastjson.JSONObject;
import org.apisign.config.ApiConfig;
import org.apisign.po.base.ApiBasePo;
import org.apisign.utils.HttpUtils;
import org.apisign.utils.ApiSignature;
import org.apisign.utils.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public abstract class AbstractApiRequest {
    private static final Logger log = LoggerFactory.getLogger(AbstractApiRequest.class);
    private ApiBasePo apiBasePo;
    private ApiConfig apiConfig;
    private String router;

    public String sendPostBody() {
        initParam();
        Map<String, Object> params = ReflectUtils.getAllFieldAndValue(apiBasePo);
        return HttpUtils.sendPostBody(apiConfig.getApiUrl().concat(router), params);
    }

    public <T> T sendPostBody(Class<T> tClass) {
        initParam();
        Map<String, Object> params = ReflectUtils.getAllFieldAndValue(apiBasePo);
        String result = HttpUtils.sendPostBody(apiConfig.getApiUrl().concat(router), params);
        if (result == null) {
            return null;
        }
        return JSONObject.parseObject(result, tClass);
    }

    private void initParam() {
        if (apiBasePo == null) {
            throw new RuntimeException("params can't be null!");
        }
        if (apiConfig == null) {
            throw new RuntimeException("config can't be null!");
        }
        apiBasePo.setAppKey(apiConfig.getAppKey());
        apiBasePo.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        String sign = ApiSignature.sign(apiBasePo, apiConfig.getAppSecret());
        apiBasePo.setSign(sign);
        log.info("参数输出：{}", JSONObject.toJSONString(apiBasePo));
    }

    AbstractApiRequest(ApiBasePo apiBasePo, ApiConfig apiConfig, String router) {
        this.apiBasePo = apiBasePo;
        this.apiConfig = apiConfig;
        this.router = router;
    }

}
