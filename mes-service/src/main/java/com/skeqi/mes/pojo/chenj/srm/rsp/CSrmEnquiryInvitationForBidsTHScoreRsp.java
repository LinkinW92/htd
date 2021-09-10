package com.skeqi.mes.pojo.chenj.srm.rsp;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmEnquiryInvitationForBidsTH
 * @Description ${Description}
 */

/**
 * 专家评分
 */
public class CSrmEnquiryInvitationForBidsTHScoreRsp {

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
     * 寻源类型(1.询价2.招标)
     */
    private String sourcingType;

    /**
     * 寻源方式(1.邀请2.公开)
     */
    private String wayToFindTheSource;

    /**
     * 公司
     */
    private String companyName;

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

    public String getSourcingType() {
        return sourcingType;
    }

    public void setSourcingType(String sourcingType) {
        this.sourcingType = sourcingType;
    }

    public String getWayToFindTheSource() {
        return wayToFindTheSource;
    }

    public void setWayToFindTheSource(String wayToFindTheSource) {
        this.wayToFindTheSource = wayToFindTheSource;
    }



    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Override
    public String toString() {
        return "CSrmEnquiryInvitationForBidsTHScoreRsp{" +
                "status='" + status + '\'' +
                ", rfqOrTenderFormCode='" + rfqOrTenderFormCode + '\'' +
                ", title='" + title + '\'' +
                ", sourcingType='" + sourcingType + '\'' +
                ", wayToFindTheSource='" + wayToFindTheSource + '\'' +
                ", companyName='" + companyName + '\'' +
                ", principal='" + principal + '\'' +
                '}';
    }
}
