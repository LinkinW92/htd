package com.skeqi.mes.pojo.chenj.srm.rsp;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmEnquiryForBidsTSupplier
 * @Description ${Description}
 */

/**
 * 询价/招标供应商表 (招标大厅出参)
 */
public class CSrmEnquiryForBidsTSupplierHallRsp {

    /**
     * 状态(1.新建2.报价中3.待核价4.待审批5.已完成6.招标中7.待开标8.评分中)
     */
    private String status;
    /**
     * 询价/招标单编码
     */
    private String rfqOrTenderFormCode;

    /**
     * 报价人
     */
    private String offerEr;

    /**
     * 报价时间
     */
    private String offerTime;


    /**
     * 公司
     */
    private String companyCode;

    public String getRfqOrTenderFormCode() {
        return rfqOrTenderFormCode;
    }

    public void setRfqOrTenderFormCode(String rfqOrTenderFormCode) {
        this.rfqOrTenderFormCode = rfqOrTenderFormCode;
    }

    public String getOfferEr() {
        return offerEr;
    }

    public void setOfferEr(String offerEr) {
        this.offerEr = offerEr;
    }

    public String getOfferTime() {
        return offerTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOfferTime(String offerTime) {
        this.offerTime = offerTime;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @Override
    public String toString() {
        return "CSrmEnquiryForBidsTSupplierHallRsp{" +
                "status='" + status + '\'' +
                ", rfqOrTenderFormCode='" + rfqOrTenderFormCode + '\'' +
                ", offerEr='" + offerEr + '\'' +
                ", offerTime='" + offerTime + '\'' +
                ", companyCode='" + companyCode + '\'' +
                '}';
    }
}
