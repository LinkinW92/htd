package com.skeqi.mes.pojo.chenj.srm.rsp;


/**
 * @author ChenJ
 * @date 2021/7/21
 * @Classname CSrmFinanceChangeRecord
 * @Description ${Description}
 */

/**
    * 财务信息变更记录表
    */
public class CSrmFinanceChangeRecordRsp {
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
    * 状态(0.已保存1.变更中2.已变更3.变更失败)
    */
    private String status;

    /**
    * 逻辑删除(0.未删除1.已删除)
    */
    private Boolean isDelete;

    /**
    * 供应商代码
    */
    private String supplierCode;

    /**
    * 创建时间
    */
    private String createTime;

    /**
    * 修改时间
    */
    private String updateTime;

    /**
    * 公司编码
    */
    private String companyCode;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", year=").append(year);
        sb.append(", totalAssets=").append(totalAssets);
        sb.append(", grossLiability=").append(grossLiability);
        sb.append(", currentAssets=").append(currentAssets);
        sb.append(", currentLiabilities=").append(currentLiabilities);
        sb.append(", operatingReceipt=").append(operatingReceipt);
        sb.append(", retainedProfits=").append(retainedProfits);
        sb.append(", status=").append(status);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", supplierCode=").append(supplierCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", companyCode=").append(companyCode);
        sb.append("]");
        return sb.toString();
    }
}
