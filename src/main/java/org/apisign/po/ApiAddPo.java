package org.apisign.po;

import org.apisign.po.base.ApiBasePo;

import java.io.Serializable;

/**
 * api添加地址参数对象
 */
public class ApiAddPo extends ApiBasePo implements Serializable {

    private String address;

    private String extra;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
