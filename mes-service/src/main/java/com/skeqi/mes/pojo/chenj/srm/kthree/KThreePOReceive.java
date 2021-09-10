package com.skeqi.mes.pojo.chenj.srm.kthree;

import java.util.Date;

/**
 * @author ChenJ
 * @date 2021/8/24
 * @Classname KThreePOInStock
 * @Description 采购收料通知接口-入参
 */
public class KThreePOReceive {

    private String ID;
    private Date cDate;
    private String mDate;
    private String gysnum;
    private String gysname;
    private String FExplanation;
    private int FEntryID;
    private String wlnum;
    private String wlname;
    private String wlgg;
    private String dwname;
    private int FQty;
    private String ckname;
    private String FSourceBillNo;
    private int FSourceEntryID;
    private String FOrderBillNo;
    private int FOrderEntryID;


    // 送货单头
    /**
     * 送货单号
     */
    private String deliveryNumber;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 供应商代码
     */
    private String supplierName;


    /**
     * 摘要
     */
    private String dis;

    // 送货单行

    /**
     * 行号
     */
    private Integer lineItemNo;

    /**
     * 物料代码
     */
    private String materialCode;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 规格
     */
    private String specification;

    /**
     * 单位名称
     */
    private String unit;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 订单编号
     */
    private String purchaseOrderNo;

    /**
     * 订单行号
     */
    private Integer purLineItemNo;


    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
        this.ID = deliveryNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        this.cDate = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        this.mDate = updateTime;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
        this.gysnum = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
        this.gysname = supplierName;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
        this.FExplanation = dis;
    }

    public Integer getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(Integer lineItemNo) {
        this.lineItemNo = lineItemNo;
        this.FEntryID = lineItemNo;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
        this.wlnum = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
        this.wlname = materialName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
        this.wlgg = specification;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
        this.dwname = unit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
        this.FQty = count;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
        this.FOrderBillNo = purchaseOrderNo;
    }

    public Integer getPurLineItemNo() {
        return purLineItemNo;
    }

    public void setPurLineItemNo(Integer purLineItemNo) {
        this.purLineItemNo = purLineItemNo;
        this.FOrderEntryID = purLineItemNo;
    }

    public void setCDate(Date cDate) {
        this.cDate = cDate;
    }

    public Date getCDate() {
        return cDate;
    }

    public void setMDate(String mDate) {
        this.mDate = mDate;
    }

    public String getMDate() {
        return mDate;
    }

    public void setGysnum(String gysnum) {
        this.gysnum = gysnum;
    }

    public String getGysnum() {
        return gysnum;
    }

    public void setGysname(String gysname) {
        this.gysname = gysname;
    }

    public String getGysname() {
        return gysname;
    }

    public void setFExplanation(String FExplanation) {
        this.FExplanation = FExplanation;
    }

    public String getFExplanation() {
        return FExplanation;
    }

    public void setFEntryID(int FEntryID) {
        this.FEntryID = FEntryID;
    }

    public int getFEntryID() {
        return FEntryID;
    }

    public void setWlnum(String wlnum) {
        this.wlnum = wlnum;
    }

    public String getWlnum() {
        return wlnum;
    }

    public void setWlname(String wlname) {
        this.wlname = wlname;
    }

    public String getWlname() {
        return wlname;
    }

    public void setWlgg(String wlgg) {
        this.wlgg = wlgg;
    }

    public String getWlgg() {
        return wlgg;
    }

    public void setDwname(String dwname) {
        this.dwname = dwname;
    }

    public String getDwname() {
        return dwname;
    }

    public void setFQty(int FQty) {
        this.FQty = FQty;
    }

    public int getFQty() {
        return FQty;
    }

    public void setCkname(String ckname) {
        this.ckname = ckname;
    }

    public String getCkname() {
        return ckname;
    }

    public void setFSourceBillNo(String FSourceBillNo) {
        this.FSourceBillNo = FSourceBillNo;
    }

    public String getFSourceBillNo() {
        return FSourceBillNo;
    }

    public void setFSourceEntryID(int FSourceEntryID) {
        this.FSourceEntryID = FSourceEntryID;
    }

    public int getFSourceEntryID() {
        return FSourceEntryID;
    }

    public void setFOrderBillNo(String FOrderBillNo) {
        this.FOrderBillNo = FOrderBillNo;
    }

    public String getFOrderBillNo() {
        return FOrderBillNo;
    }

    public void setFOrderEntryID(int FOrderEntryID) {
        this.FOrderEntryID = FOrderEntryID;
    }

    public int getFOrderEntryID() {
        return FOrderEntryID;
    }

    @Override
    public String toString() {
        return "KThreePOInStockReq{" +
                "ID='" + ID + '\'' +
                ", cDate=" + cDate +
                ", mDate='" + mDate + '\'' +
                ", gysnum='" + gysnum + '\'' +
                ", gysname='" + gysname + '\'' +
                ", FExplanation='" + FExplanation + '\'' +
                ", FEntryID=" + FEntryID +
                ", wlnum='" + wlnum + '\'' +
                ", wlname='" + wlname + '\'' +
                ", wlgg='" + wlgg + '\'' +
                ", dwname='" + dwname + '\'' +
                ", FQty=" + FQty +
                ", ckname='" + ckname + '\'' +
                ", FSourceBillNo='" + FSourceBillNo + '\'' +
                ", FSourceEntryID=" + FSourceEntryID +
                ", FOrderBillNo='" + FOrderBillNo + '\'' +
                ", FOrderEntryID=" + FOrderEntryID +
                ", deliveryNumber='" + deliveryNumber + '\'' +
                ", createTime=" + createTime +
                ", updateTime='" + updateTime + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", dis='" + dis + '\'' +
                ", lineItemNo=" + lineItemNo +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", specification='" + specification + '\'' +
                ", unit='" + unit + '\'' +
                ", count=" + count +
                ", purchaseOrderNo='" + purchaseOrderNo + '\'' +
                ", purLineItemNo=" + purLineItemNo +
                '}';
    }
}
