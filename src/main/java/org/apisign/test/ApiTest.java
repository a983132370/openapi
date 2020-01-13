package org.apisign.test;

import org.apisign.config.ApiConfig;
import org.apisign.po.ApiCreatePo;
import org.apisign.request.ApiCreateRequest;

import java.io.IOException;

/**
 * 测试
 * <p>
 * @Value("${api.url}") private String apiUrl;
 * @Value("${app.key}") private String appKey;
 * @Value("${app.secret}") private String appSecret;
 * @Bean public ApiConfig apiConfig(){
 * return new ApiConfig(apiUrl,appKey,appSecret);
 * }
 * ApiCreatePo apiCreateAddressPo = new ApiCreatePo();
 * apiCreateAddressPo.setAccount("demo");
 * ApiCreateRequest request = new ApiCreateRequest(apiCreateAddressPo,apiConfig);
 * String result = request.sendPostBody();
 * System.out.println(result);
 * </p>
 */
public class ApiTest {


    public static void main(String[] args) throws IOException {
        String appKey = "85ngm9dp";
        String appSecret = "651af1bc9f4cb8d0210cb3a5f186469fddf7444c";
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setApiUrl("http://localhost:9090/api");
        apiConfig.setAppKey(appKey);
        apiConfig.setAppSecret(appSecret);


        ApiCreatePo apiCreateAddressPo = new ApiCreatePo();
        apiCreateAddressPo.setAccount("demo");

        ApiCreateRequest request = new ApiCreateRequest(apiCreateAddressPo, apiConfig);
        String result = request.sendPostBody();
        System.out.println(result);


    }

}
