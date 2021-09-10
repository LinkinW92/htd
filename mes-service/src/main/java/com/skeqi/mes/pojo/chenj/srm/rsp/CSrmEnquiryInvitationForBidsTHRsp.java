package com.skeqi.mes.pojo.chenj.srm.rsp;



 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmEnquiryInvitationForBidsTH
 * @Description ${Description}
 */

import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * 询价/招标头行数据返回
 */
public class CSrmEnquiryInvitationForBidsTHRsp {


    /**
     * 询价/招标单编码
     */
    private String rfqOrTenderFormCode;

    /**
     * 标题
     */
    private String title;


    /**
     * 模板编号
     */
    private String templateNumber;

    /**
     * 供应商代码--至少邀请三家供应商
     */
    private List<String> supplierCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 状态(1.新建2.报价中3.待核价4.待审批5.已完成6.招标中7.待开标8.评分中)
     */
    private String status;

    /**
     * 寻源类型(1.询价2.招标)
     */
    private String sourcingType;

    /**
     * 报价方式(1.线上报价2.线下报价)
     */
    private String wayOfQuoting;

    /**
     * 寻源方式(1.邀请2.公开)
     */
    private String wayToFindTheSource;

    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 币种(1.CNY2.USD)
     */
    private String currency;

    /**
     * 报价开始时间
     */
    private String quotationStartTime;

    /**
     * 报价截止时间
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
     * 负责人
     */
    private String principal;

    /**
     * 创建人
     */
    private String creator;

    /**
     *  询价\招投标数据行
     * @return
     */

    private PageInfo<CSrmEnquiryInvitationForBidsTRRsp> rfqList;


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

    public String getTemplateNumber() {
        return templateNumber;
    }

    public void setTemplateNumber(String templateNumber) {
        this.templateNumber = templateNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSourcingType() {
        return sourcingType;
    }

    public void setSourcingType(String sourcingType) {
        this.sourcingType = sourcingType;
    }

    public String getWayOfQuoting() {
        return wayOfQuoting;
    }

    public void setWayOfQuoting(String wayOfQuoting) {
        this.wayOfQuoting = wayOfQuoting;
    }

    public String getWayToFindTheSource() {
        return wayToFindTheSource;
    }

    public void setWayToFindTheSource(String wayToFindTheSource) {
        this.wayToFindTheSource = wayToFindTheSource;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getQuotationStartTime() {
        return quotationStartTime;
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

    public String getBidOpeningTime() {
        return bidOpeningTime;
    }

    public void setBidOpeningTime(String bidOpeningTime) {
        this.bidOpeningTime = bidOpeningTime;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public PageInfo<CSrmEnquiryInvitationForBidsTRRsp> getRfqList() {
        return rfqList;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRfqList(PageInfo<CSrmEnquiryInvitationForBidsTRRsp> rfqList) {
        this.rfqList = rfqList;
    }

    public List<String> getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(List<String> supplierCode) {
        this.supplierCode = supplierCode;
    }

    @Override
    public String toString() {
        return "CSrmEnquiryInvitationForBidsTHRsp{" +
                "rfqOrTenderFormCode='" + rfqOrTenderFormCode + '\'' +
                ", title='" + title + '\'' +
                ", templateNumber='" + templateNumber + '\'' +
                ", supplierCode=" + supplierCode +
                ", supplierName='" + supplierName + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", sourcingType='" + sourcingType + '\'' +
                ", wayOfQuoting='" + wayOfQuoting + '\'' +
                ", wayToFindTheSource='" + wayToFindTheSource + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", currency='" + currency + '\'' +
                ", quotationStartTime='" + quotationStartTime + '\'' +
                ", quotationStopTime='" + quotationStopTime + '\'' +
                ", bidOpeningPlace='" + bidOpeningPlace + '\'' +
                ", bidOpeningTime='" + bidOpeningTime + '\'' +
                ", principal='" + principal + '\'' +
                ", creator='" + creator + '\'' +
                ", rfqList=" + rfqList +
                '}';
    }
}
