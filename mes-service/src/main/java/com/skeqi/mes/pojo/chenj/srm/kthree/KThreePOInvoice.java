package com.skeqi.mes.pojo.chenj.srm.kthree;

import java.util.Date;

/**
 * @author ChenJ
 * @date 2021/7/28
 * @Classname KThreeSupplier
 * @Description 采购发票接口-查询头行
 */

public class KThreePOInvoice {

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
    private int FAuxTaxPrice;
    private int FAllAmount;
    private int FCess;
    private int FTaxRate;
    private String FSourceBillNo;
    private int FSourceEntryID;
    private String FOrderBillNo;
    private int FOrderEntryID;


    // 应收发票单头
    /**
     * 应收发票单号
     */
    private String invoiceReceivableNo;
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
    private String remark;

    // 应收发票单行

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
     * 含税单价
     */
    private Integer unitPrice;

    /**
     * 价税合计
     */
    private Integer priceTaxSum;


    /**
     * 税率(%)
     */
    private Integer taxRate;


    /**
     * 订单编号
     */
    private String orderNumber;

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

    public void setFAuxTaxPrice(int FAuxTaxPrice) {
        this.FAuxTaxPrice = FAuxTaxPrice;
    }

    public int getFAuxTaxPrice() {
        return FAuxTaxPrice;
    }

    public void setFAllAmount(int FAllAmount) {
        this.FAllAmount = FAllAmount;
    }

    public int getFAllAmount() {
        return FAllAmount;
    }

    public void setFTaxRate(int FTaxRate) {
        this.FTaxRate = FTaxRate;
    }

    public int getFTaxRate() {
        return FTaxRate;
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

    public String getInvoiceReceivableNo() {
        return invoiceReceivableNo;
    }

    public void setInvoiceReceivableNo(String invoiceReceivableNo) {
        this.invoiceReceivableNo = invoiceReceivableNo;
        this.ID = invoiceReceivableNo;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
        this.FExplanation = remark;
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

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
        this.FAuxTaxPrice = unitPrice;
    }

    public Integer getPriceTaxSum() {
        return priceTaxSum;
    }

    public void setPriceTaxSum(Integer priceTaxSum) {
        this.priceTaxSum = priceTaxSum;
        this.FAllAmount = priceTaxSum;
    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
        this.FCess = taxRate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        this.FOrderBillNo = orderNumber;
    }

    public Integer getPurLineItemNo() {
        return purLineItemNo;
    }

    public int getFCess() {
        return FCess;
    }

    public void setFCess(int FCess) {
        this.FCess = FCess;
    }

    public void setPurLineItemNo(Integer purLineItemNo) {
        this.purLineItemNo = purLineItemNo;
        this.FOrderEntryID = purLineItemNo;
    }

    @Override
    public String toString() {
        return "KThreePOInvoice{" +
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
                ", FAuxTaxPrice=" + FAuxTaxPrice +
                ", FAllAmount=" + FAllAmount +
                ", FCess=" + FCess +
                ", FTaxRate=" + FTaxRate +
                ", FSourceBillNo='" + FSourceBillNo + '\'' +
                ", FSourceEntryID=" + FSourceEntryID +
                ", FOrderBillNo='" + FOrderBillNo + '\'' +
                ", FOrderEntryID=" + FOrderEntryID +
                ", invoiceReceivableNo='" + invoiceReceivableNo + '\'' +
                ", createTime=" + createTime +
                ", updateTime='" + updateTime + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", remark='" + remark + '\'' +
                ", lineItemNo=" + lineItemNo +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", specification='" + specification + '\'' +
                ", unit='" + unit + '\'' +
                ", count=" + count +
                ", unitPrice=" + unitPrice +
                ", priceTaxSum=" + priceTaxSum +
                ", taxRate=" + taxRate +
                ", orderNumber='" + orderNumber + '\'' +
                ", purLineItemNo=" + purLineItemNo +
                '}';
    }
}
