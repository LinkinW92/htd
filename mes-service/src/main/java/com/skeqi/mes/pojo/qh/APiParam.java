package com.skeqi.mes.pojo.qh;

/**
 *  接口实体类
 * @ 创建人 FQZ
 * @ 创建时间   2020/10/20 11:29
 */
public class APiParam {
    private Integer lineId;  //产线ID
    private String sn;  //总成
    private String stationName;  //工位
    private String lineName;   //产线名称
    private String barcode;  //物料码
    private Integer step;  //步序
    private String a;  //角度
    private String t;  //扭矩
    private String r;  //结果

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public APiParam() {
    }

    public APiParam(Integer lineId, String sn, String stationName, String lineName, String barcode, Integer step, String a, String t, String r) {
        this.lineId = lineId;
        this.sn = sn;
        this.stationName = stationName;
        this.lineName = lineName;
        this.barcode = barcode;
        this.step = step;
        this.a = a;
        this.t = t;
        this.r = r;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public APiParam(Integer lineId) {
        this.lineId = lineId;
    }
}
