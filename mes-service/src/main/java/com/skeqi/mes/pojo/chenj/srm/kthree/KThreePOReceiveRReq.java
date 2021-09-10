package com.skeqi.mes.pojo.chenj.srm.kthree;

/**
 * @author ChenJ
 * @date 2021/8/24
 * @Classname KThreePOInStock
 * @Description 采购收料通知接口-行数据
 */
public class KThreePOReceiveRReq {

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

    public String getCkname() {
        return ckname;
    }

    public void setCkname(String ckname) {
        this.ckname = ckname;
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
        return "KThreePOReceiveRReq{" +
                "FEntryID=" + FEntryID +
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
                '}';
    }
}
