package com.skeqi.mes.pojo.chenj.srm.kthree;

/**
 * @author ChenJ
 * @date 2021/7/28
 * @Classname KThreeSupplier
 * @Description 采购发票接口-行数据
 */

public class KThreePOInvoiceRReq {

    private int FEntryID;
    private String wlnum;
    private String wlname;
    private String wlgg;
    private String dwname;
    private int FQty;
    private int FAuxTaxPrice;
    private int FAllAmount;
    private int FCess;
    private String FSourceBillNo;
    private int FSourceEntryID;
    private String FOrderBillNo;
    private int FOrderEntryID;

    public int getFCess() {
        return FCess;
    }

    public void setFCess(int FCess) {
        this.FCess = FCess;
    }

    public int getFEntryID() {
        return FEntryID;
    }

    public void setFEntryID(int FEntryID) {
        this.FEntryID = FEntryID;
    }

    public String getWlnum() {
        return wlnum;
    }

    public void setWlnum(String wlnum) {
        this.wlnum = wlnum;
    }

    public String getWlname() {
        return wlname;
    }

    public void setWlname(String wlname) {
        this.wlname = wlname;
    }

    public String getWlgg() {
        return wlgg;
    }

    public void setWlgg(String wlgg) {
        this.wlgg = wlgg;
    }

    public String getDwname() {
        return dwname;
    }

    public void setDwname(String dwname) {
        this.dwname = dwname;
    }

    public int getFQty() {
        return FQty;
    }

    public void setFQty(int FQty) {
        this.FQty = FQty;
    }

    public int getFAuxTaxPrice() {
        return FAuxTaxPrice;
    }

    public void setFAuxTaxPrice(int FAuxTaxPrice) {
        this.FAuxTaxPrice = FAuxTaxPrice;
    }

    public int getFAllAmount() {
        return FAllAmount;
    }

    public void setFAllAmount(int FAllAmount) {
        this.FAllAmount = FAllAmount;
    }

    public String getFSourceBillNo() {
        return FSourceBillNo;
    }

    public void setFSourceBillNo(String FSourceBillNo) {
        this.FSourceBillNo = FSourceBillNo;
    }

    public int getFSourceEntryID() {
        return FSourceEntryID;
    }

    public void setFSourceEntryID(int FSourceEntryID) {
        this.FSourceEntryID = FSourceEntryID;
    }

    public String getFOrderBillNo() {
        return FOrderBillNo;
    }

    public void setFOrderBillNo(String FOrderBillNo) {
        this.FOrderBillNo = FOrderBillNo;
    }

    public int getFOrderEntryID() {
        return FOrderEntryID;
    }

    public void setFOrderEntryID(int FOrderEntryID) {
        this.FOrderEntryID = FOrderEntryID;
    }

    @Override
    public String toString() {
        return "KThreePOInvoiceRReq{" +
                "FEntryID=" + FEntryID +
                ", wlnum='" + wlnum + '\'' +
                ", wlname='" + wlname + '\'' +
                ", wlgg='" + wlgg + '\'' +
                ", dwname='" + dwname + '\'' +
                ", FQty=" + FQty +
                ", FAuxTaxPrice=" + FAuxTaxPrice +
                ", FAllAmount=" + FAllAmount +
                ", FCess=" + FCess +
                ", FSourceBillNo='" + FSourceBillNo + '\'' +
                ", FSourceEntryID=" + FSourceEntryID +
                ", FOrderBillNo='" + FOrderBillNo + '\'' +
                ", FOrderEntryID=" + FOrderEntryID +
                '}';
    }
}
