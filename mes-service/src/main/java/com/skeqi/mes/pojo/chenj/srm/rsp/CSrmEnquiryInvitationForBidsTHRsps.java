package com.skeqi.mes.pojo.chenj.srm.rsp;

 /**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmEnquiryInvitationForBidsTH
 * @Description ${Description}
 */

/**
 * 询价/招标头表出参
 */
public class CSrmEnquiryInvitationForBidsTHRsps {

    /**
     * 询价/招标单编码
     */
    private String rfqOrTenderFormCode;

    /**
     * 标题
     */
    private String title;


    /**
     * 供应商名称
     */
    private String name;


    /**
     * 模板编号
     */
    private String templateNumber;

    /**
     * 状态(1.新建2.报价中3.待核价4.待审批5.已完成6.招标中7.待开标8.评分中9.已定标)
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
     * 公司代码
     */
    private String companyCode;

    /**
     * 供应商名称
     */
    private String supplierName;
    /**
     * 供应商编码
     */
    private String supplierCode;

    /**
     * 公司名称
     */
    private String companyName;


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
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;


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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setWayToFindTheSource(String wayToFindTheSource) {
        this.wayToFindTheSource = wayToFindTheSource;
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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CSrmEnquiryInvitationForBidsTHRsps{" +
                "rfqOrTenderFormCode='" + rfqOrTenderFormCode + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", templateNumber='" + templateNumber + '\'' +
                ", status='" + status + '\'' +
                ", sourcingType='" + sourcingType + '\'' +
                ", wayOfQuoting='" + wayOfQuoting + '\'' +
                ", wayToFindTheSource='" + wayToFindTheSource + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", currency='" + currency + '\'' +
                ", quotationStartTime='" + quotationStartTime + '\'' +
                ", quotationStopTime='" + quotationStopTime + '\'' +
                ", bidOpeningPlace='" + bidOpeningPlace + '\'' +
                ", bidOpeningTime='" + bidOpeningTime + '\'' +
                ", principal='" + principal + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
