package com.skeqi.mes.pojo.chenj.srm.rsp;

import java.math.BigDecimal;

/**
 * @author ChenJ
 * @date 2021/7/18
 * @Classname KThreeReq
 * @Description
 */
public class KThreeRsp {
    /**
     * "ID": "POORD035705",
     * "cDate": "2021-05-07",
     * "mDate": null,
     * "gysnum": "1144",
     * "gysname": "上海迈川信息技术有限公司",
     * "YWY": "李明",
     * "FExplanation": "",
     * "FDeliveryPlace": "福建省宁德市蕉城区疏港路115号【北高速路口正对面】 （中通、圆通、韵达不送货上门，勿发） 李明 15280659515",
     * "FKFS": "月结60天",
     * "FEntryID": 1,
     * "wlnum": "02.06.05.0002",
     * "wlname": "扫描枪",
     * "wlgg": "霍尼1902GHD 5米串口套装",
     * "dwname": "把",
     * "FQty": 19.00,
     * "FAuxTaxPrice": 2020.0000000000,
     * "FAllAmount": 38380.0000,
     * "FCess": 13.00,
     * "xmname": "SSY0639",
     * "bz": "",
     * "gw": "",
     * "FFetchTime": "2021-05-30",
     * "FSourceBillNo": "POREQ043308",
     * "FSourceEntryID": 1
     */
    private String jktype;
    private String method;
    private String filter;
    private String ID;
    private String cDate;
    private String mDate;
    private String gysnum;
    private String gysname;
    private String YWY;
    private String FExplanation;
    private String FDeliveryPlace;
    private String FKFS;
    private Integer FEntryID;
    private String wlnum;
    private String wlname;
    private String wlgg;
    private String dwname;
    private BigDecimal FQty;
    private BigDecimal FAuxTaxPrice;
    private BigDecimal FAllAmount;
    private BigDecimal FCess;
    private String xmname;
    private String bz;
    private String gw;
    private String FFetchTime;
    private String FSourceBillNo;
    private Integer FSourceEntryID;

    public String getJktype() {
        return jktype;
    }

    public void setJktype(String jktype) {
        this.jktype = jktype;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getGysnum() {
        return gysnum;
    }

    public void setGysnum(String gysnum) {
        this.gysnum = gysnum;
    }

    public String getGysname() {
        return gysname;
    }

    public void setGysname(String gysname) {
        this.gysname = gysname;
    }

    public String getYWY() {
        return YWY;
    }

    public void setYWY(String YWY) {
        this.YWY = YWY;
    }

    public String getFExplanation() {
        return FExplanation;
    }

    public void setFExplanation(String FExplanation) {
        this.FExplanation = FExplanation;
    }

    public String getFDeliveryPlace() {
        return FDeliveryPlace;
    }

    public void setFDeliveryPlace(String FDeliveryPlace) {
        this.FDeliveryPlace = FDeliveryPlace;
    }

    public String getFKFS() {
        return FKFS;
    }

    public void setFKFS(String FKFS) {
        this.FKFS = FKFS;
    }

    public Integer getFEntryID() {
        return FEntryID;
    }

    public void setFEntryID(Integer FEntryID) {
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

    public BigDecimal getFQty() {
        return FQty;
    }

    public void setFQty(BigDecimal FQty) {
        this.FQty = FQty;
    }

    public BigDecimal getFAuxTaxPrice() {
        return FAuxTaxPrice;
    }

    public void setFAuxTaxPrice(BigDecimal FAuxTaxPrice) {
        this.FAuxTaxPrice = FAuxTaxPrice;
    }

    public BigDecimal getFAllAmount() {
        return FAllAmount;
    }

    public void setFAllAmount(BigDecimal FAllAmount) {
        this.FAllAmount = FAllAmount;
    }

    public BigDecimal getFCess() {
        return FCess;
    }

    public void setFCess(BigDecimal FCess) {
        this.FCess = FCess;
    }

    public String getXmname() {
        return xmname;
    }

    public void setXmname(String xmname) {
        this.xmname = xmname;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw;
    }

    public String getFFetchTime() {
        return FFetchTime;
    }

    public void setFFetchTime(String FFetchTime) {
        this.FFetchTime = FFetchTime;
    }

    public String getFSourceBillNo() {
        return FSourceBillNo;
    }

    public void setFSourceBillNo(String FSourceBillNo) {
        this.FSourceBillNo = FSourceBillNo;
    }

    public Integer getFSourceEntryID() {
        return FSourceEntryID;
    }

    public void setFSourceEntryID(Integer FSourceEntryID) {
        this.FSourceEntryID = FSourceEntryID;
    }

    @Override
    public String toString() {
        return "KThreeRsp{" +
                "jktype='" + jktype + '\'' +
                ", method='" + method + '\'' +
                ", filter='" + filter + '\'' +
                ", ID='" + ID + '\'' +
                ", cDate='" + cDate + '\'' +
                ", mDate='" + mDate + '\'' +
                ", gysnum='" + gysnum + '\'' +
                ", gysname='" + gysname + '\'' +
                ", YWY='" + YWY + '\'' +
                ", FExplanation='" + FExplanation + '\'' +
                ", FDeliveryPlace='" + FDeliveryPlace + '\'' +
                ", FKFS='" + FKFS + '\'' +
                ", FEntryID=" + FEntryID +
                ", wlnum='" + wlnum + '\'' +
                ", wlname='" + wlname + '\'' +
                ", wlgg='" + wlgg + '\'' +
                ", dwname='" + dwname + '\'' +
                ", FQty=" + FQty +
                ", FAuxTaxPrice=" + FAuxTaxPrice +
                ", FAllAmount=" + FAllAmount +
                ", FCess=" + FCess +
                ", xmname='" + xmname + '\'' +
                ", bz='" + bz + '\'' +
                ", gw='" + gw + '\'' +
                ", FFetchTime='" + FFetchTime + '\'' +
                ", FSourceBillNo='" + FSourceBillNo + '\'' +
                ", FSourceEntryID=" + FSourceEntryID +
                '}';
    }
}
