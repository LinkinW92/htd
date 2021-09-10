package com.skeqi.mes.pojo;

/**
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/23 14:45
 */
public class PMesWeightT {

    private Integer id;
    private String dt;
    private String sn;
    private String st;
    private String wid;
    private String weigh;
    private String weigh1;
    private String dis;
    private String transfer;

    public PMesWeightT() {
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public PMesWeightT(Integer id, String dt, String sn, String st, String wid, String weight, String weight1, String dis, String transfer) {
        this.id = id;
        this.dt = dt;
        this.sn = sn;
        this.st = st;
        this.wid = wid;
        this.weigh = weight;
        this.weigh1 = weight1;
        this.dis = dis;
        this.transfer = transfer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getWeight() {
        return weigh;
    }

    public void setWeight(String weight) {
        this.weigh = weight;
    }

    public String getWeight1() {
        return weigh1;
    }

    public void setWeight1(String weight1) {
        this.weigh1 = weight1;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }


}
