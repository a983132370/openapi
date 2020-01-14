package org.apisign.dto;

import java.io.Serializable;

/**
 * 响应
 */
public class ApiResponseDto<T> implements Serializable {
    /**错误代码**/
    private Integer code;
    /**异常信息**/
    private String msg ;

    private T data;

    public boolean success() {
        return code != null && code == 1;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
