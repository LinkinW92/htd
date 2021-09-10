package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmContractR
 * @Description ${Description}
 */

/**
 * 合同行表
 */
public class CSrmContractR {
    /**
     * 合同行表id
     */
    private Integer id;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 行项目号
     */
    private String lineItemNo;

    /**
     * 物料编码
     */
    private String materialCode;

    /**
     * 数量
     */
    private String count;

    /**
     * 单位
     */
    private String unit;

    /**
     * 币种
     */
    private String currency;

    /**
     * 税率
     */
    private String taxRate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 合同头表id
     */
    private Integer contractId;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料分类
     */
    private String materialClassification;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 型号
     */
    private String model;

    /**
     * 税种
     */
    private String taxCategory;

    /**
     * 税额
     */
    private String taxAmount;

    /**
     * 原币种税单价
     */
    private String unitPriceOfTaxInOriginalCurrency;

    /**
     * 不含税单价
     */
    private String unitPriceExcludingTax;

    /**
     * 含税价金额
     */
    private String amountIncludingTax;

    /**
     * 不含税价金额
     */
    private String amountExcludingTax;

    /**
     * 支付日期
     */
    private String paymentDate;

    /**
     * 属性
     */
    private String attribute;

    /**
     * 采购员
     */
    private String buyer;

    /**
     * 保管人
     */
    private String custodian;

    /**
     * 验收人
     */
    private String acceptancePerson;

    /**
     * 费用承担部门
     */
    private String costBearingDepartment;

    /**
     * 地点
     */
    private String place;

    /**
     * 项目编号
     */
    private String projectNumber;

    /**
     * 项目名称
     */
    private String entrName;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 来源单据编号
     */
    private String sourceDocumentNumber;

    /**
     * 来源单据行号
     */
    private String sourceDocumentLineNumber;



    /**
     * 逻辑删除(0:未删除、1:已删除)
     */
    private Integer isDelete;






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

    public String getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(String lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
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

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialClassification() {
        return materialClassification;
    }

    public void setMaterialClassification(String materialClassification) {
        this.materialClassification = materialClassification;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(String taxCategory) {
        this.taxCategory = taxCategory;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getUnitPriceOfTaxInOriginalCurrency() {
        return unitPriceOfTaxInOriginalCurrency;
    }

    public void setUnitPriceOfTaxInOriginalCurrency(String unitPriceOfTaxInOriginalCurrency) {
        this.unitPriceOfTaxInOriginalCurrency = unitPriceOfTaxInOriginalCurrency;
    }

    public String getUnitPriceExcludingTax() {
        return unitPriceExcludingTax;
    }

    public void setUnitPriceExcludingTax(String unitPriceExcludingTax) {
        this.unitPriceExcludingTax = unitPriceExcludingTax;
    }

    public String getAmountIncludingTax() {
        return amountIncludingTax;
    }

    public void setAmountIncludingTax(String amountIncludingTax) {
        this.amountIncludingTax = amountIncludingTax;
    }

    public String getAmountExcludingTax() {
        return amountExcludingTax;
    }

    public void setAmountExcludingTax(String amountExcludingTax) {
        this.amountExcludingTax = amountExcludingTax;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getCustodian() {
        return custodian;
    }

    public void setCustodian(String custodian) {
        this.custodian = custodian;
    }

    public String getAcceptancePerson() {
        return acceptancePerson;
    }

    public void setAcceptancePerson(String acceptancePerson) {
        this.acceptancePerson = acceptancePerson;
    }

    public String getCostBearingDepartment() {
        return costBearingDepartment;
    }

    public void setCostBearingDepartment(String costBearingDepartment) {
        this.costBearingDepartment = costBearingDepartment;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getEntrName() {
        return entrName;
    }

    public void setEntrName(String entrName) {
        this.entrName = entrName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSourceDocumentNumber() {
        return sourceDocumentNumber;
    }

    public void setSourceDocumentNumber(String sourceDocumentNumber) {
        this.sourceDocumentNumber = sourceDocumentNumber;
    }

    public String getSourceDocumentLineNumber() {
        return sourceDocumentLineNumber;
    }

    public void setSourceDocumentLineNumber(String sourceDocumentLineNumber) {
        this.sourceDocumentLineNumber = sourceDocumentLineNumber;
    }

    @Override
    public String toString() {
        return "CSrmContractR{" +
                "id=" + id +
                ", contractNo='" + contractNo + '\'' +
                ", lineItemNo='" + lineItemNo + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", count='" + count + '\'' +
                ", unit='" + unit + '\'' +
                ", currency='" + currency + '\'' +
                ", taxRate='" + taxRate + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", contractId=" + contractId +
                ", materialName='" + materialName + '\'' +
                ", materialClassification='" + materialClassification + '\'' +
                ", specifications='" + specifications + '\'' +
                ", model='" + model + '\'' +
                ", taxCategory='" + taxCategory + '\'' +
                ", taxAmount='" + taxAmount + '\'' +
                ", unitPriceOfTaxInOriginalCurrency='" + unitPriceOfTaxInOriginalCurrency + '\'' +
                ", unitPriceExcludingTax='" + unitPriceExcludingTax + '\'' +
                ", amountIncludingTax='" + amountIncludingTax + '\'' +
                ", amountExcludingTax='" + amountExcludingTax + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", attribute='" + attribute + '\'' +
                ", buyer='" + buyer + '\'' +
                ", custodian='" + custodian + '\'' +
                ", acceptancePerson='" + acceptancePerson + '\'' +
                ", costBearingDepartment='" + costBearingDepartment + '\'' +
                ", place='" + place + '\'' +
                ", projectNumber='" + projectNumber + '\'' +
                ", entrName='" + entrName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", sourceDocumentNumber='" + sourceDocumentNumber + '\'' +
                ", sourceDocumentLineNumber='" + sourceDocumentLineNumber + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }
}
