package com.skeqi.mes.pojo.chenj.srm.rsp;



 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmEnquiryInvitationForBidsTH
 * @Description ${Description}
 */

/**
 * 招标变更出参
 */
public class CSrmEnquiryInvitationForBidsTHUpdateRsp {


    /**
     * 状态(1.新建2.报价中3.待核价4.待审批5.已完成6.招标中7.待开标8.评分中9.已定标)
     */
    private String status;

    /**
     * 投标开始时间
     */
    private String quotationStartTime;

    /**
     * 投标截止时间
     */
    private String quotationStopTime;

    /**
     * 开标地点
     */
    private String bidOpeningPlace;

    /**
     * 开标时间
     */
    private String bidOpeningTime;

    /**
     * 变更说明
     */
    private String updateExplain;





    public String getQuotationStartTime() {
        return quotationStartTime;
    }

    public String getUpdateExplain() {
        return updateExplain;
    }

    public void setUpdateExplain(String updateExplain) {
        this.updateExplain = updateExplain;
    }

    public void setQuotationStartTime(String quotationStartTime) {
        this.quotationStartTime = quotationStartTime;
    }

    public String getQuotationStopTime() {
        return quotationStopTime;
    }

    public void setQuotationStopTime(String quotationStopTime) {
        this.quotationStopTime = quotationStopTime;
    }

    public String getBidOpeningPlace() {
        return bidOpeningPlace;
    }

    public void setBidOpeningPlace(String bidOpeningPlace) {
        this.bidOpeningPlace = bidOpeningPlace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBidOpeningTime() {
        return bidOpeningTime;
    }

    public void setBidOpeningTime(String bidOpeningTime) {
        this.bidOpeningTime = bidOpeningTime;
    }

    @Override
    public String toString() {
        return "CSrmEnquiryInvitationForBidsTHUpdateRsp{" +
                "status='" + status + '\'' +
                ", quotationStartTime='" + quotationStartTime + '\'' +
                ", quotationStopTime='" + quotationStopTime + '\'' +
                ", bidOpeningPlace='" + bidOpeningPlace + '\'' +
                ", bidOpeningTime='" + bidOpeningTime + '\'' +
                ", updateExplain='" + updateExplain + '\'' +
                '}';
    }
}
