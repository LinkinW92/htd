package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmContractH
 * @Description ${Description}
 */

/**
 * 合同头表
 */
public class CSrmContractH {
    /**
     * 合同头表id
     */
    private Integer id;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 合同性质(1.普通合同2.附件合同)
     */
    private String contractCharacter;

    /**
     * 公司代码
     */
    private String companyCode;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 合同生效日期
     */
    private Date effectiveDateOfContract;

    /**
     * 合同终止日期
     */
    private Date dateOfTermination;

    /**
     * 使用模板编号
     */
    private String usingTemplateNumbers;

    /**
     * 状态(1.新建2.待审批3.待签署4.待存档5.已存档)
     */
    private String status;

    /**
     * 逻辑删除(0:未删除、1:已删除)
     */
    private Integer isDelete;

    /**
     * 采购员
     */
    private String buyer;
    /**
     * 主合同
     */
    private String masterContract;
    /**
     * 合同来源
     */
    private String sourceOfTheContract;
    /**
     * 合同总额
     */
    private String contractRental;

    /**
     * 采购组织
     */
    private String purchasingOrganization;

    /**
     * 备注
     */
    private String remark;



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

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getMasterContract() {
        return masterContract;
    }

    public void setMasterContract(String masterContract) {
        this.masterContract = masterContract;
    }

    public String getSourceOfTheContract() {
        return sourceOfTheContract;
    }

    public void setSourceOfTheContract(String sourceOfTheContract) {
        this.sourceOfTheContract = sourceOfTheContract;
    }

    public String getContractRental() {
        return contractRental;
    }

    public void setContractRental(String contractRental) {
        this.contractRental = contractRental;
    }

    public String getPurchasingOrganization() {
        return purchasingOrganization;
    }

    public void setPurchasingOrganization(String purchasingOrganization) {
        this.purchasingOrganization = purchasingOrganization;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getContractCharacter() {
        return contractCharacter;
    }

    public void setContractCharacter(String contractCharacter) {
        this.contractCharacter = contractCharacter;
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

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Date getEffectiveDateOfContract() {
        return effectiveDateOfContract;
    }

    public void setEffectiveDateOfContract(Date effectiveDateOfContract) {
        this.effectiveDateOfContract = effectiveDateOfContract;
    }

    public Date getDateOfTermination() {
        return dateOfTermination;
    }

    public void setDateOfTermination(Date dateOfTermination) {
        this.dateOfTermination = dateOfTermination;
    }

    public String getUsingTemplateNumbers() {
        return usingTemplateNumbers;
    }

    public void setUsingTemplateNumbers(String usingTemplateNumbers) {
        this.usingTemplateNumbers = usingTemplateNumbers;
    }
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "CSrmContractH{" +
                "id=" + id +
                ", contractNo='" + contractNo + '\'' +
                ", contractName='" + contractName + '\'' +
                ", creator='" + creator + '\'' +
                ", contractCharacter='" + contractCharacter + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", effectiveDateOfContract=" + effectiveDateOfContract +
                ", dateOfTermination=" + dateOfTermination +
                ", usingTemplateNumbers='" + usingTemplateNumbers + '\'' +
                ", status='" + status + '\'' +
                ", isDelete=" + isDelete +
                ", buyer='" + buyer + '\'' +
                ", masterContract='" + masterContract + '\'' +
                ", sourceOfTheContract='" + sourceOfTheContract + '\'' +
                ", contractRental='" + contractRental + '\'' +
                ", purchasingOrganization='" + purchasingOrganization + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }


}
