package com.skeqi.mes.pojo.chenj.srm;

import java.util.Date;


/**
 * @author ChenJ
 * @date 2021/7/19
 * @Classname CSrmEnquiryInvitationForBidsTH
 * @Description ${Description}
 */

/**
 * 询价/招标头表
 */
public class CSrmEnquiryInvitationForBidsTH {
    /**
     * 询价/招标头id
     */
    private Integer id;

    /**
     * 询价/招标单编码
     */
    private String rfqOrTenderFormCode;

    /**
     * 标题/招标事项
     */
    private String title;

    /**
     * 模板编号
     */
    private String templateNumber;

    /**
     * 状态(1.询价新建2.询价报价中3.询价待核价4.询价待审批5.投标新建6.投标审批中7.投标中8.待开标9.评分10. 待定标11.已完成)
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
     * 币种(1.CNY2.USD)
     */
    private String currency;

    /**
     * 报价/投表开始时间
     */
    private Date quotationStartTime;

    /**
     * 报价/投表截止时间
     */
    private Date quotationStopTime;

    /**
     * 开标地点
     */
    private String bidOpeningPlace;

    /**
     * 开标时间
     */
    private Date bidOpeningTime;

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
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 招标评分模板行id
     */
    private Integer templateId;

    /**
     * 公司代码
     */
    private String companyCode;

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 变更说明
     */
    private String updateExplain;

    /**
     * 逻辑删除(0.未删除1.已删除)
     */
    private Boolean isDelete;

    /**
     * 采购组织名称
     */
    private String purchasingOrganization;

    /**
     * 澄清截止时间
     */
    private Date clarificationDeadline;

    /**
     * 采购员
     */
    private String buyer;

    /**
     * 开标人
     */
    private String theBidOpeningPeople;

    /**
     * 定标人
     */
    private String selectionOfPeople;

    /**
     * 招标技术文件
     */
    private String biddingTechnicalDocuments;

    /**
     * 招标商务文件
     */
    private String biddingBusinessDocuments;

    /**
     * 预算金额
     */
    private String budget;

    /**
     * 项目编号
     */
    private String itemNumber;

    /**
     * 项目名称
     */
    private String itemName;

    /**
     * 项目地址
     */
    private String itemAddress;

    /**
     * 汇率
     */
    private String exchangeRate;

    /**
     * 付款方式(1.线上支付2.线下支付)
     */
    private String paymentMethod;

    /**
     * 付款条款
     */
    private String termsOfPayment;

    /**
     * 采购联系人
     */
    private String purchasingContact;

    /**
     * 联系人电话
     */
    private String telephone;

    /**
     * 联系人邮箱
     */
    private String contactEmail;

    /**
     *   OA审批单号
     * @return
     */
    private String examineNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getQuotationStartTime() {
        return quotationStartTime;
    }

    public void setQuotationStartTime(Date quotationStartTime) {
        this.quotationStartTime = quotationStartTime;
    }

    public Date getQuotationStopTime() {
        return quotationStopTime;
    }

    public void setQuotationStopTime(Date quotationStopTime) {
        this.quotationStopTime = quotationStopTime;
    }

    public String getBidOpeningPlace() {
        return bidOpeningPlace;
    }

    public void setBidOpeningPlace(String bidOpeningPlace) {
        this.bidOpeningPlace = bidOpeningPlace;
    }

    public Date getBidOpeningTime() {
        return bidOpeningTime;
    }

    public void setBidOpeningTime(Date bidOpeningTime) {
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

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getUpdateExplain() {
        return updateExplain;
    }

    public void setUpdateExplain(String updateExplain) {
        this.updateExplain = updateExplain;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getPurchasingOrganization() {
        return purchasingOrganization;
    }

    public void setPurchasingOrganization(String purchasingOrganization) {
        this.purchasingOrganization = purchasingOrganization;
    }

    public Date getClarificationDeadline() {
        return clarificationDeadline;
    }

    public void setClarificationDeadline(Date clarificationDeadline) {
        this.clarificationDeadline = clarificationDeadline;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getExamineNumber() {
        return examineNumber;
    }

    public void setExamineNumber(String examineNumber) {
        this.examineNumber = examineNumber;
    }

    public String getTheBidOpeningPeople() {
        return theBidOpeningPeople;
    }

    public void setTheBidOpeningPeople(String theBidOpeningPeople) {
        this.theBidOpeningPeople = theBidOpeningPeople;
    }

    public String getSelectionOfPeople() {
        return selectionOfPeople;
    }

    public void setSelectionOfPeople(String selectionOfPeople) {
        this.selectionOfPeople = selectionOfPeople;
    }

    public String getBiddingTechnicalDocuments() {
        return biddingTechnicalDocuments;
    }

    public void setBiddingTechnicalDocuments(String biddingTechnicalDocuments) {
        this.biddingTechnicalDocuments = biddingTechnicalDocuments;
    }

    public String getBiddingBusinessDocuments() {
        return biddingBusinessDocuments;
    }

    public void setBiddingBusinessDocuments(String biddingBusinessDocuments) {
        this.biddingBusinessDocuments = biddingBusinessDocuments;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemAddress() {
        return itemAddress;
    }

    public void setItemAddress(String itemAddress) {
        this.itemAddress = itemAddress;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTermsOfPayment() {
        return termsOfPayment;
    }

    public void setTermsOfPayment(String termsOfPayment) {
        this.termsOfPayment = termsOfPayment;
    }

    public String getPurchasingContact() {
        return purchasingContact;
    }

    public void setPurchasingContact(String purchasingContact) {
        this.purchasingContact = purchasingContact;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String toString() {
        return "CSrmEnquiryInvitationForBidsTH{" +
                "id=" + id +
                ", rfqOrTenderFormCode='" + rfqOrTenderFormCode + '\'' +
                ", title='" + title + '\'' +
                ", templateNumber='" + templateNumber + '\'' +
                ", status='" + status + '\'' +
                ", sourcingType='" + sourcingType + '\'' +
                ", wayOfQuoting='" + wayOfQuoting + '\'' +
                ", wayToFindTheSource='" + wayToFindTheSource + '\'' +
                ", currency='" + currency + '\'' +
                ", quotationStartTime=" + quotationStartTime +
                ", quotationStopTime=" + quotationStopTime +
                ", bidOpeningPlace='" + bidOpeningPlace + '\'' +
                ", bidOpeningTime=" + bidOpeningTime +
                ", principal='" + principal + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", templateId=" + templateId +
                ", companyCode='" + companyCode + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", updateExplain='" + updateExplain + '\'' +
                ", isDelete=" + isDelete +
                ", purchasingOrganization='" + purchasingOrganization + '\'' +
                ", clarificationDeadline=" + clarificationDeadline +
                ", buyer='" + buyer + '\'' +
                ", theBidOpeningPeople='" + theBidOpeningPeople + '\'' +
                ", selectionOfPeople='" + selectionOfPeople + '\'' +
                ", biddingTechnicalDocuments='" + biddingTechnicalDocuments + '\'' +
                ", biddingBusinessDocuments='" + biddingBusinessDocuments + '\'' +
                ", budget='" + budget + '\'' +
                ", itemNumber='" + itemNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemAddress='" + itemAddress + '\'' +
                ", exchangeRate='" + exchangeRate + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", termsOfPayment='" + termsOfPayment + '\'' +
                ", purchasingContact='" + purchasingContact + '\'' +
                ", telephone='" + telephone + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", examineNumber='" + examineNumber + '\'' +
                '}';
    }
}
