package org.apisign.po;

import org.apisign.po.base.ApiBasePo;

import java.io.Serializable;

/**
 * api创建参数对象
 */
public class ApiCreatePo extends ApiBasePo implements Serializable {

    private String account;

    private String extra;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
