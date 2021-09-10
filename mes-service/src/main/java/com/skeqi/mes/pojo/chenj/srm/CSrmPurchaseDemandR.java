package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/6/9
 * @Classname CSrmPurchaseDemandR
 * @Description ${Description}
 */

/**
 * 采购单行表
 */
public class CSrmPurchaseDemandR {
    /**
     * 采购单行id
     */
    private Integer id;

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
    private Date requiredDate;

    /**
     * 收货地点
     */
    private String placeOfReceipt;

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
    private Date deliveryDate;

    /**
     * 逻辑删除(0:未删除、1:已删除)
     */
    private Boolean isDelete;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getPlaceOfReceipt() {
        return placeOfReceipt;
    }

    public void setPlaceOfReceipt(String placeOfReceipt) {
        this.placeOfReceipt = placeOfReceipt;
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CSrmPurchaseDemandR{" +
                "id=" + id +
                ", requestCode='" + requestCode + '\'' +
                ", rowProjectNumber='" + rowProjectNumber + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", count='" + count + '\'' +
                ", unit='" + unit + '\'' +
                ", requiredDate=" + requiredDate +
                ", placeOfReceipt='" + placeOfReceipt + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", specification='" + specification + '\'' +
                ", project='" + project + '\'' +
                ", remark='" + remark + '\'' +
                ", station='" + station + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", isDelete=" + isDelete +
                '}';
    }
}
