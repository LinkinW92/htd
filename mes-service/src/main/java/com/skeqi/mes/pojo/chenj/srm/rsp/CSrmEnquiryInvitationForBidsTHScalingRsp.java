package com.skeqi.mes.pojo.chenj.srm.rsp;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmEnquiryInvitationForBidsTH
 * @Description ${Description}
 */

/**
 * 定标出参
 */
public class CSrmEnquiryInvitationForBidsTHScalingRsp {

    /**
     * 状态(1.新建2.报价中3.待核价4.待审批5.已完成6.招标中7.待开标8.评分中)
     */
    private String status;


    /**
     * 询价/招标单编码
     */
    private String rfqOrTenderFormCode;

    /**
     * 标题
     */
    private String title;


    /**
     * 公司
     */
    private String companyName;


    /**
     * 创建人
     */
    private String creator;

    /**
     * 发布时间
     */
    private String createTime;

    /**
     * 投标开始时间
     */
    private String quotationStartTime;


    /**
     * 负责人
     */
    private String principal;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRfqOrTenderFormCode() {
        return rfqOrTenderFormCode;
    }

    public void setRfqOrTenderFormCode(String rfqOrTenderFormCode) {
        this.rfqOrTenderFormCode = rfqOrTenderFormCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getQuotationStartTime() {
        return quotationStartTime;
    }

    public void setQuotationStartTime(String quotationStartTime) {
        this.quotationStartTime = quotationStartTime;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Override
    public String toString() {
        return "CSrmEnquiryInvitationForBidsTHScalingRsp{" +
                "status='" + status + '\'' +
                ", rfqOrTenderFormCode='" + rfqOrTenderFormCode + '\'' +
                ", title='" + title + '\'' +
                ", companyName='" + companyName + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", quotationStartTime=" + quotationStartTime +
                ", principal='" + principal + '\'' +
                '}';
    }
}
