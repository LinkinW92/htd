package com.skeqi.mes.pojo.chenj.srm.rsp;


/**
 * @author ChenJ
 * @date 2021/6/7
 * @Classname CSrmPurchaseDemandH
 * @Description ${Description}
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 采购需求头表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmPurchaseDemandRReq {

    /**
     * 申请单号
     */
    private String requestCode;

    /**
     * 行项目号
     */
    private String rowProjectNumber;

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
     * 需求日期
     */
    private String requiredDate;

    /**
     * 收货地点
     */
    private String placeOfReceipt;

    /**
     * 规格
     */
    private String specification;

    /**
     * 项目
     */
    private String project;

    /**
     * 注释
     */
    private String remark;

    /**
     * 工位
     */
    private String station;

    /**
     * 到货日期
     */
    private String deliveryDate;


    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getRowProjectNumber() {
        return rowProjectNumber;
    }

    public void setRowProjectNumber(String rowProjectNumber) {
        this.rowProjectNumber = rowProjectNumber;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }



    public String getPlaceOfReceipt() {
        return placeOfReceipt;
    }

    public void setPlaceOfReceipt(String placeOfReceipt) {
        this.placeOfReceipt = placeOfReceipt;
    }

    @Override
    public String toString() {
        return "CSrmPurchaseDemandRReq{" +
                "requestCode='" + requestCode + '\'' +
                ", rowProjectNumber='" + rowProjectNumber + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", count='" + count + '\'' +
                ", unit='" + unit + '\'' +
                ", requiredDate='" + requiredDate + '\'' +
                ", placeOfReceipt='" + placeOfReceipt + '\'' +
                ", specification='" + specification + '\'' +
                ", project='" + project + '\'' +
                ", remark='" + remark + '\'' +
                ", station='" + station + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                '}';
    }
}
