package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname CSrmFinance
 * @Description ${Description}
 */

/**
 * 财务信息表
 */
public class CSrmFinance {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 年度
     */
    private String year;

    /**
     * 总资产(万元)
     */
    private String totalAssets;

    /**
     * 总负债(万元)
     */
    private String grossLiability;

    /**
     * 流动资产(万元)
     */
    private String currentAssets;

    /**
     * 流动负债(万元)
     */
    private String currentLiabilities;

    /**
     * 营业收入(万元)
     */
    private String operatingReceipt;

    /**
     * 净利润(万元)
     */
    private String retainedProfits;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 公司编码
     */
    private String companyCode;



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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(String totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getGrossLiability() {
        return grossLiability;
    }

    public void setGrossLiability(String grossLiability) {
        this.grossLiability = grossLiability;
    }

    public String getCurrentAssets() {
        return currentAssets;
    }

    public void setCurrentAssets(String currentAssets) {
        this.currentAssets = currentAssets;
    }

    public String getCurrentLiabilities() {
        return currentLiabilities;
    }

    public void setCurrentLiabilities(String currentLiabilities) {
        this.currentLiabilities = currentLiabilities;
    }

    public String getOperatingReceipt() {
        return operatingReceipt;
    }

    public void setOperatingReceipt(String operatingReceipt) {
        this.operatingReceipt = operatingReceipt;
    }

    public String getRetainedProfits() {
        return retainedProfits;
    }

    public void setRetainedProfits(String retainedProfits) {
        this.retainedProfits = retainedProfits;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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
        return "CSrmFinance{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", totalAssets='" + totalAssets + '\'' +
                ", grossLiability='" + grossLiability + '\'' +
                ", currentAssets='" + currentAssets + '\'' +
                ", currentLiabilities='" + currentLiabilities + '\'' +
                ", operatingReceipt='" + operatingReceipt + '\'' +
                ", retainedProfits='" + retainedProfits + '\'' +
                ", isDelete=" + isDelete +
                ", supplierCode='" + supplierCode + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
