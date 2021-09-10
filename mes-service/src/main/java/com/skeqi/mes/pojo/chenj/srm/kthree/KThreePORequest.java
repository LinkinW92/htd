package com.skeqi.mes.pojo.chenj.srm.kthree;

import java.util.Date;

/**
 * @author ChenJ
 * @date 2021/7/28
 * @Classname KThreePORequest
 * @Description 采购申请接口-查询头行
 */
public class KThreePORequest {
    /**
     * 单据编号
     */
    private String ID;
    /**
     * 单据日期
     */
    private Date cDate;
    /**
     * 审核日期
     */
    private String mDate;
    /**
     * 业务类型
     */
    private int YWLX;
    /**
     * 业务员
     */
    private String YWY;
    /**
     * 行号
     */
    private int FEntryID;
    /**
     * 物料代码
     */
    private String wlnum;
    /**
     * 物料名称
     */
    private String wlname;
    /**
     * 规格
     */
    private String wlgg;
    /**
     * 数量
     */
    private int FQty;
    /**
     * 单位名称
     */
    private String dwname;
    /**
     * 项目
     */
    private String xmname;
    /**
     * 注释
     */
    private String bz;
    /**
     * 工位
     */
    private String gw;
    /**
     * 建议采购日期
     */
    private Date FAPurchTime;
    /**
     * 到货日期
     */
    private Date FFetchTime;


    /**
     * 制单人
     */
    private String ZDR;


    // 采购申请头表数据
    /**
     * 申请单号
     */
    private String requestCode;

    /**
     * 申请日期
     */
    private Date applicationDate;

    /**
     * 申请人
     */
    private String proposer;

    /**
     * 状态(3.待分配，4:已分配)
     */
    private String status = "3";

    /**
     * 采购员类型(1.SRM系统采购员2.K3采购员)
     */
    private String buyerType = "2";

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 审核日期
     */
    private String auditData;


    /**
     * 制单人
     */
    private String preparedBy;

    // 采购申请行表数据

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
     * 规格
     */
    private String specification;


    /**
     * 项目
     */
    private String project;


    /**
     * 备注
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



    public void setID(String ID) {
        this.ID = ID;
        this.requestCode = ID;
    }

    public String getID() {
        return ID;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public void setYWY(String YWY) {
        this.YWY = YWY;
        this.proposer = YWY;
    }

    public String getYWY() {
        return YWY;
    }

    public void setFEntryID(int FEntryID) {
        this.FEntryID = FEntryID;
        this.rowProjectNumber = String.valueOf(FEntryID);
    }

    public int getFEntryID() {
        return FEntryID;
    }

    public void setWlnum(String wlnum) {
        this.wlnum = wlnum;
        this.materialCode = wlnum;
    }

    public String getWlnum() {
        return wlnum;
    }

    public void setWlname(String wlname) {
        this.wlname = wlname;
        this.materialName = wlname;
    }

    public String getWlname() {
        return wlname;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setWlgg(String wlgg) {
        this.wlgg = wlgg;
        this.specification = wlgg;
    }

    public String getWlgg() {
        return wlgg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFQty(int FQty) {
        this.FQty = FQty;
        this.count = String.valueOf(FQty);
    }

    public int getFQty() {
        return FQty;
    }

    public void setDwname(String dwname) {
        this.dwname = dwname;
        this.unit = dwname;
    }

    public String getDwname() {
        return dwname;
    }

    public void setXmname(String xmname) {
        this.xmname = xmname;
        this.project = xmname;
    }

    public String getXmname() {
        return xmname;
    }

    public void setBz(String bz) {
        this.bz = bz;
        this.remark = bz;
    }

    public String getBz() {
        return bz;
    }

    public void setGw(String gw) {
        this.gw = gw;
        this.station = gw;
    }

    public String getGw() {
        return gw;
    }

    public void setFAPurchTime(Date FAPurchTime) {
        this.FAPurchTime = FAPurchTime;
        this.requiredDate = FAPurchTime;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
        this.auditData = mDate;
    }

    public int getYWLX() {
        return YWLX;
    }

    public void setYWLX(int YWLX) {
        this.YWLX = YWLX;
        this.businessType = String.valueOf(YWLX);
    }

    public Date getFAPurchTime() {
        return FAPurchTime;
    }

    public Date getFFetchTime() {
        return FFetchTime;
    }

    public void setFFetchTime(Date FFetchTime) {
        this.FFetchTime = FFetchTime;
        this.deliveryDate = FFetchTime;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
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

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
        this.applicationDate = cDate;
    }


    public String getBusinessType() {
        return businessType;
    }

    public String getAuditData() {
        return auditData;
    }

    public void setAuditData(String auditData) {
        this.auditData = auditData;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getZDR() {
        return ZDR;
    }

    public void setZDR(String ZDR) {
        this.ZDR = ZDR;
        this.preparedBy = ZDR;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    @Override
    public String toString() {
        return "KThreePORequest{" +
                "ID='" + ID + '\'' +
                ", cDate=" + cDate +
                ", mDate='" + mDate + '\'' +
                ", YWLX=" + YWLX +
                ", YWY='" + YWY + '\'' +
                ", FEntryID=" + FEntryID +
                ", wlnum='" + wlnum + '\'' +
                ", wlname='" + wlname + '\'' +
                ", wlgg='" + wlgg + '\'' +
                ", FQty=" + FQty +
                ", dwname='" + dwname + '\'' +
                ", xmname='" + xmname + '\'' +
                ", bz='" + bz + '\'' +
                ", gw='" + gw + '\'' +
                ", FAPurchTime=" + FAPurchTime +
                ", FFetchTime=" + FFetchTime +
                ", ZDR='" + ZDR + '\'' +
                ", requestCode='" + requestCode + '\'' +
                ", applicationDate=" + applicationDate +
                ", proposer='" + proposer + '\'' +
                ", status='" + status + '\'' +
                ", buyerType='" + buyerType + '\'' +
                ", businessType='" + businessType + '\'' +
                ", auditData='" + auditData + '\'' +
                ", preparedBy='" + preparedBy + '\'' +
                ", rowProjectNumber='" + rowProjectNumber + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", count='" + count + '\'' +
                ", unit='" + unit + '\'' +
                ", requiredDate=" + requiredDate +
                ", specification='" + specification + '\'' +
                ", project='" + project + '\'' +
                ", remark='" + remark + '\'' +
                ", station='" + station + '\'' +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
