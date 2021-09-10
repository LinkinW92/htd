package com.skeqi.mes.pojo.chenj.srm.rsp;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseOrderR
 * @Description ${Description}
 */

/**
 * 采购订单行表
 */
public class CSrmPurchaseOrderRRsp {

    /**
     * 订单号
     */
    private String orderNumber;


    /**
     * 订单号(送货单行表中的数据)
     */
    private String purchaseOrderNo;

    /**
     * 工厂名称
     */
    private String shippingAddressName;


    /**
     * 行项目号
     */
    private String lineItemNo;

    /**
     * 物料编码
     */
    private String materialCode;

    /**
     * 物料名称
     */
    private String materialName;

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
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

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

    /**
     * 采购订单行号
     */
    private String purLineItemNo;


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getPurLineItemNo() {
        return purLineItemNo;
    }

    public void setPurLineItemNo(String purLineItemNo) {
        this.purLineItemNo = purLineItemNo;
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

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getShippingAddress() {
        return shippingAddress;
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

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccessoryAddress() {
        return accessoryAddress;
    }

    public void setAccessoryAddress(String accessoryAddress) {
        this.accessoryAddress = accessoryAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getExpectedDateOfArrival() {
        return expectedDateOfArrival;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getShippingAddressName() {
        return shippingAddressName;
    }

    public void setShippingAddressName(String shippingAddressName) {
        this.shippingAddressName = shippingAddressName;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CSrmPurchaseOrderRRsp{" +
                "orderNumber='" + orderNumber + '\'' +
                ", purchaseOrderNo='" + purchaseOrderNo + '\'' +
                ", shippingAddressName='" + shippingAddressName + '\'' +
                ", lineItemNo='" + lineItemNo + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
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
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", specification='" + specification + '\'' +
                ", project='" + project + '\'' +
                ", station='" + station + '\'' +
                ", rowProjectNumber='" + rowProjectNumber + '\'' +
                ", purLineItemNo='" + purLineItemNo + '\'' +
                '}';
    }
}
