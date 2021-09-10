package com.skeqi.mes.pojo.chenj.srm.req;



 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmPurchaseOrderH
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 采购订单行表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmPurchaseOrderRReq {

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
     * 单价
     */
    private String unitPrice;

    /**
     * 税率
     */
    private String taxRate;

    /**
     * 收货地址
     */
    private String shippingAddress;

    /**
     * 预计到货日期
     */
    private String expectedDateOfArrival;

    /**
     * 采购申请单号
     */
    private String purchaseRequestNo;


    /**
     * 部门
     */
    private String department;
    /**
     * 备注
     */
    private String remark;

    /**
     * 附件地址
     */
    private String accessoryAddress;

    /**
     * 收货工厂名称
     */
    private String shippingAddressName;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 规格
     */
    private String specification;

    /**
     * 项目
     */
    private String project;

    /**
     * 工位
     */
    private String station;


    /**
     * 采购申请单行号
     */
    private String rowProjectNumber;


    public String getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(String lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccessoryAddress() {
        return accessoryAddress;
    }

    public void setAccessoryAddress(String accessoryAddress) {
        this.accessoryAddress = accessoryAddress;
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getRowProjectNumber() {
        return rowProjectNumber;
    }

    public void setRowProjectNumber(String rowProjectNumber) {
        this.rowProjectNumber = rowProjectNumber;
    }

    public String getExpectedDateOfArrival() {
        return expectedDateOfArrival;
    }

    public String getShippingAddressName() {
        return shippingAddressName;
    }

    public void setShippingAddressName(String shippingAddressName) {
        this.shippingAddressName = shippingAddressName;
    }

    public void setExpectedDateOfArrival(String expectedDateOfArrival) {
        this.expectedDateOfArrival = expectedDateOfArrival;
    }

    public String getPurchaseRequestNo() {
        return purchaseRequestNo;
    }

    public void setPurchaseRequestNo(String purchaseRequestNo) {
        this.purchaseRequestNo = purchaseRequestNo;
    }

    @Override
    public String toString() {
        return "CSrmPurchaseOrderRReq{" +
                "lineItemNo='" + lineItemNo + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", count='" + count + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", taxRate='" + taxRate + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", expectedDateOfArrival='" + expectedDateOfArrival + '\'' +
                ", purchaseRequestNo='" + purchaseRequestNo + '\'' +
                ", department='" + department + '\'' +
                ", remark='" + remark + '\'' +
                ", accessoryAddress='" + accessoryAddress + '\'' +
                ", shippingAddressName='" + shippingAddressName + '\'' +
                ", materialName='" + materialName + '\'' +
                ", specification='" + specification + '\'' +
                ", project='" + project + '\'' +
                ", station='" + station + '\'' +
                ", rowProjectNumber='" + rowProjectNumber + '\'' +
                '}';
    }
}
