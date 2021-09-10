package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


/**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmProduct
 * @Description ${Description}
 */

/**
 * 供应商产品信息表
 */
public class CSrmProduct {
    /**
     * 供应商产品信息id
     */
    private Integer id;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品类别(1.中草药材、2.棉花)
     */
    private String productType;

    /**
     * 经营性质(1.制造商 2.贸易商 3.服务商)
     */
    private String businessNature;
    /**
     * 产品/服务
     */
    private String productsOrServices;
    /**
     * 客户
     */
    private String client;
    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public String getBusinessNature() {
        return businessNature;
    }

    public void setBusinessNature(String businessNature) {
        this.businessNature = businessNature;
    }

    public String getProductsOrServices() {
        return productsOrServices;
    }

    public void setProductsOrServices(String productsOrServices) {
        this.productsOrServices = productsOrServices;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getProductName() {
        return productName;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CSrmProduct{" +
                "id=" + id +
                ", supplierCode='" + supplierCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", businessNature='" + businessNature + '\'' +
                ", productsOrServices='" + productsOrServices + '\'' +
                ", client='" + client + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
