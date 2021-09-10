package com.skeqi.mes.pojo.chenj.srm.kthree;
import java.util.Date;

/**
 * @author ChenJ
 * @date 2021/7/28
 * @Classname KThreeSupplier
 * @Description 采购订单接口-查询头行
 */

public class KThreePOOrder {
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
    private Date mDate;
    /**
     * 供应商代码
     */
    private String gysnum;
    /**
     * 供应商名称
     */
    private String gysname;
    /**
     * 业务员
     */
    private String YWY;
    /**
     * 摘要
     */
    private String FExplanation;
    /**
     * 交货地址
     */
    private String FDeliveryPlace;
    /**
     * 付款方式
     */
    private String FKFS;

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
     * 单位名称
     */
    private String dwname;
    /**
     * 数量
     */
    private int FQty;
    /**
     * 实际含税单价
     */
    private int FAuxTaxPrice;
    /**
     * 价税合计
     */
    private int FAllAmount;
    /**
     * 税率(%)
     */
    private int FCess;
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
     * 到货日期
     */
    private Date FFetchTime;
    /**
     * 原单编号
     */
    private String FSourceBillNo;
    /**
     * 原单行号
     */
    private int FSourceEntryID;


    // 采购订单头
    /**
     *  采购订单号
     */
    private String orderNumber;

    /**
     *  采购员
     */
    private String buyer;

    /**
     *  供应商
     */
    private String supplier;

    /**
     *  创建时间
     */
    private Date createTime;

    /**
     *  修改时间
     */
    private Date updateTime;

    /**
     *  备注
     */
    private String remark;

    /**
     *  付款方式
     */
    private String paymentMethod;

    /**
     *  交货地址
     */
    private String deliveryAddress;

    /**
     *  状态(3.待确认)
     */
    private String status="3";


    /**
     * 采购员类型(1.SRM系统采购员2.K3采购员)
     */
    private String buyerType="2";

    /**
     * 供应商名称
     */
    private String supplierName;



    // 采购订单行
    /**
     *  行项目号
     */
    private String lineItemNo;

    /**
     * 物料编码
     */
    private String materialCode;

    /**
     * 物料名称
     */
    private String materialName;


    /**
     *  规格
     */
    private String specification;

    /**
     *  数量
     */
    private String count;


    /**
     *  单位
     */
    private String unit;


    /**
     *  含税单价
     */
    private String unitPrice;


    /**
     *  税率
     */
    private String taxRate;

    /**
     *  项目
     */
    private String project;


    /**
     *  备注--行表
     */
    private String remarkR;


    /**
     *  工位
     */
    private String station;


    /**
     *  预计到货日期
     */
    private Date expectedDateOfArrival;


    /**
     *  采购申请号(关联采购需求申请单号)
     */
    private String purchaseRequestNo;


    /**
     *  采购申请单行号(关联采购需求申请单行号)
     */
    private String rowProjectNumber;

    /**
     * 价税合计
     */
    private String priceTaxSum;



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
        this.orderNumber = ID;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
        this.createTime = cDate;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
        this.updateTime = mDate;
    }

    public String getGysnum() {
        return gysnum;
    }

    public void setGysnum(String gysnum) {
        this.gysnum = gysnum;
        this.supplier = gysnum;
    }

    public String getGysname() {
        return gysname;
    }

    public void setGysname(String gysname) {
        this.gysname = gysname;
        this.supplierName = gysname;
    }

    public String getYWY() {
        return YWY;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(String buyerType) {
        this.buyerType = buyerType;
    }

    public void setYWY(String YWY) {
        this.YWY = YWY;
        this.buyer = YWY;
    }

    public String getFExplanation() {
        return FExplanation;
    }

    public void setFExplanation(String FExplanation) {
        this.FExplanation = FExplanation;
        this.remark = FExplanation;
    }

    public String getFDeliveryPlace() {
        return FDeliveryPlace;
    }

    public void setFDeliveryPlace(String FDeliveryPlace) {
        this.FDeliveryPlace = FDeliveryPlace;
        this.deliveryAddress = FDeliveryPlace;
    }

    public String getFKFS() {
        return FKFS;
    }

    public void setFKFS(String FKFS) {
        this.FKFS = FKFS;
        this.paymentMethod = FKFS;
    }

    public int getFEntryID() {
        return FEntryID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFEntryID(int FEntryID) {
        this.FEntryID = FEntryID;
        this.lineItemNo = String.valueOf(FEntryID);
    }

    public String getWlnum() {
        return wlnum;
    }

    public void setWlnum(String wlnum) {
        this.wlnum = wlnum;
        this.materialCode = wlnum;
    }

    public String getWlname() {
        return wlname;
    }

    public void setWlname(String wlname) {
        this.wlname = wlname;
        this.materialName = wlname;
    }

    public String getWlgg() {
        return wlgg;
    }

    public void setWlgg(String wlgg) {
        this.wlgg = wlgg;
        this.specification = wlgg;
    }

    public String getDwname() {
        return dwname;
    }

    public void setDwname(String dwname) {
        this.dwname = dwname;
        this.unit = dwname;
    }

    public int getFQty() {
        return FQty;
    }

    public void setFQty(int FQty) {
        this.FQty = FQty;
        this.count = String.valueOf(FQty);
    }

    public int getFAuxTaxPrice() {
        return FAuxTaxPrice;
    }

    public void setFAuxTaxPrice(int FAuxTaxPrice) {
        this.FAuxTaxPrice = FAuxTaxPrice;
        this.unitPrice = String.valueOf(FAuxTaxPrice);
    }

    public int getFAllAmount() {
        return FAllAmount;
    }

    public void setFAllAmount(int FAllAmount) {
        this.FAllAmount = FAllAmount;
        this.priceTaxSum = String.valueOf(FAllAmount);
    }

    public int getFCess() {
        return FCess;
    }

    public void setFCess(int FCess) {
        this.FCess = FCess;
        this.taxRate = String.valueOf(FCess);
    }

    public String getXmname() {
        return xmname;
    }

    public void setXmname(String xmname) {
        this.xmname = xmname;
        this.project = xmname;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
        this.remarkR = bz;
    }

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw;
        this.station = gw;
    }

    public Date getFFetchTime() {
        return FFetchTime;
    }

    public void setFFetchTime(Date FFetchTime) {
        this.FFetchTime = FFetchTime;
        this.expectedDateOfArrival = FFetchTime;
    }

    public String getFSourceBillNo() {
        return FSourceBillNo;
    }

    public void setFSourceBillNo(String FSourceBillNo) {
        this.FSourceBillNo = FSourceBillNo;
        this.purchaseRequestNo = FSourceBillNo;
    }

    public int getFSourceEntryID() {
        return FSourceEntryID;
    }

    public void setFSourceEntryID(int FSourceEntryID) {
        this.FSourceEntryID = FSourceEntryID;
        this.rowProjectNumber = String.valueOf(FSourceEntryID);
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getPriceTaxSum() {
        return priceTaxSum;
    }

    public void setPriceTaxSum(String priceTaxSum) {
        this.priceTaxSum = priceTaxSum;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(String lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRemarkR() {
        return remarkR;
    }

    public void setRemarkR(String remarkR) {
        this.remarkR = remarkR;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Date getExpectedDateOfArrival() {
        return expectedDateOfArrival;
    }

    public void setExpectedDateOfArrival(Date expectedDateOfArrival) {
        this.expectedDateOfArrival = expectedDateOfArrival;
    }

    public String getPurchaseRequestNo() {
        return purchaseRequestNo;
    }

    public void setPurchaseRequestNo(String purchaseRequestNo) {
        this.purchaseRequestNo = purchaseRequestNo;
    }

    public String getRowProjectNumber() {
        return rowProjectNumber;
    }

    public void setRowProjectNumber(String rowProjectNumber) {
        this.rowProjectNumber = rowProjectNumber;
    }

    @Override
    public String toString() {
        return "KThreePOOrder{" +
                "ID='" + ID + '\'' +
                ", cDate=" + cDate +
                ", mDate=" + mDate +
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
                ", FFetchTime=" + FFetchTime +
                ", FSourceBillNo='" + FSourceBillNo + '\'' +
                ", FSourceEntryID=" + FSourceEntryID +
                ", orderNumber='" + orderNumber + '\'' +
                ", buyer='" + buyer + '\'' +
                ", supplier='" + supplier + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", status='" + status + '\'' +
                ", buyerType='" + buyerType + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", lineItemNo='" + lineItemNo + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", materialName='" + materialName + '\'' +
                ", specification='" + specification + '\'' +
                ", count='" + count + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", taxRate='" + taxRate + '\'' +
                ", project='" + project + '\'' +
                ", remarkR='" + remarkR + '\'' +
                ", station='" + station + '\'' +
                ", expectedDateOfArrival=" + expectedDateOfArrival +
                ", purchaseRequestNo='" + purchaseRequestNo + '\'' +
                ", rowProjectNumber='" + rowProjectNumber + '\'' +
                ", priceTaxSum='" + priceTaxSum + '\'' +
                '}';
    }
}
