package com.skeqi.mes.pojo.chenj.srm.rsp;

/**
 * @author ChenJ
 * @date 2021/6/17
 * @Classname CSrmSupplierRsp
 * @Description
 */
public class CSrmSupplierRsp {

    /**
     * 供应商代码
     */
    private String supplierCode;

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    @Override
    public String toString() {
        return "CSrmSupplierRsp{" +
                "supplierCode='" + supplierCode + '\'' +
                '}';
    }
}
