package com.skeqi.mes.pojo.chenj.srm.req;



 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmSupplierH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 供应商升降级申请头表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmSupplierHRsp {

    /**
     * 供应商代码
     */
    private String supplierCode;


    /**
     * 供应商名称
     */
    private String name;



    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CSrmSupplierHReqs{" +
                "supplierCode='" + supplierCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
