package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseOrderR
 * @Description ${Description}
 */

/**
 * 采购订单行表
 */
public class CSrmPurchaseOrderR {
    /**
     * 采购订单头表id
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderNumber;

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
    private Date expectedDateOfArrival;

    /**
     * 采购申请号
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

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
     * 物料名称
     */
    private String materialName;


    /**
     * 采购申请单行号
     */
    private String rowProjectNumber;


    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;


    /**
     * 价税合计
     */
    private String priceTaxSum;




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getLineItemNo() {
        return lineItemNo;
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

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getRowProjectNumber() {
        return rowProjectNumber;
    }

    public void setRowProjectNumber(String rowProjectNumber) {
        this.rowProjectNumber = rowProjectNumber;
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

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
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

    public Date getExpectedDateOfArrival() {
        return expectedDateOfArrival;
    }

    public void setExpectedDateOfArrival(Date expectedDateOfArrival) {
        this.expectedDateOfArrival = expectedDateOfArrival;
    }

    public String getPurchaseRequestNo() {
        return purchaseRequestNo;
    }

    public void setPurchaseRequestNo(String purchaseRequestNo) {
        this.purchaseRequestNo = purchaseRequestNo;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPriceTaxSum() {
        return priceTaxSum;
    }

    public void setPriceTaxSum(String priceTaxSum) {
        this.priceTaxSum = priceTaxSum;
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

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CSrmPurchaseOrderR{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", lineItemNo='" + lineItemNo + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", count='" + count + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", taxRate='" + taxRate + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", expectedDateOfArrival=" + expectedDateOfArrival +
                ", purchaseRequestNo='" + purchaseRequestNo + '\'' +
                ", department='" + department + '\'' +
                ", remark='" + remark + '\'' +
                ", accessoryAddress='" + accessoryAddress + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", specification='" + specification + '\'' +
                ", project='" + project + '\'' +
                ", station='" + station + '\'' +
                ", materialName='" + materialName + '\'' +
                ", rowProjectNumber='" + rowProjectNumber + '\'' +
                ", isDelete=" + isDelete +
                ", priceTaxSum=" + priceTaxSum +
                '}';
    }
}
