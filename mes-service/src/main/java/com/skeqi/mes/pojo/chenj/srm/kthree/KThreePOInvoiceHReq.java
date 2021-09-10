package com.skeqi.mes.pojo.chenj.srm.kthree;

import java.util.Date;
import java.util.List;

/**
 * @author ChenJ
 * @date 2021/7/28
 * @Classname KThreeSupplier
 * @Description 采购发票接口-头数据
 */

public class KThreePOInvoiceHReq {

    private String ID;
    private Date cDate;
    private String mDate;
    private String gysnum;
    private String gysname;
    private String FExplanation;

    // 行数据
    private List<KThreePOInvoiceRReq> items;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getFExplanation() {
        return FExplanation;
    }

    public void setFExplanation(String FExplanation) {
        this.FExplanation = FExplanation;
    }

    public List<KThreePOInvoiceRReq> getItems() {
        return items;
    }

    public void setItems(List<KThreePOInvoiceRReq> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "KThreePOInvoiceHReq{" +
                "ID='" + ID + '\'' +
                ", cDate=" + cDate +
                ", mDate='" + mDate + '\'' +
                ", gysnum='" + gysnum + '\'' +
                ", gysname='" + gysname + '\'' +
                ", FExplanation='" + FExplanation + '\'' +
                ", items=" + items +
                '}';
    }
}
