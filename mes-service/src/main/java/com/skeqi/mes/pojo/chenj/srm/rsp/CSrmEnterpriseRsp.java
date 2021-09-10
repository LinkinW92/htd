package com.skeqi.mes.pojo.chenj.srm.rsp;

/**
 * @author ChenJ
 * @date 2021/6/18
 * @Classname CSrmEnterpriseRsp
 * @Description
 */
public class CSrmEnterpriseRsp {

    /**
     * 供应商代码
     */
    private String supplierCode;
    /**
     * 企业
     */
    private String enterprise;
    /**
     * 提交时间
     */
    private String submitTime;
    /**
     * 认证状态(0.冻结1.未认证2.认证中3.已认证)
     */
    private Integer status;


    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CSrmEnterpriseRsp{" +
                "supplierCode='" + supplierCode + '\'' +
                ", enterprise='" + enterprise + '\'' +
                ", submitTime='" + submitTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
