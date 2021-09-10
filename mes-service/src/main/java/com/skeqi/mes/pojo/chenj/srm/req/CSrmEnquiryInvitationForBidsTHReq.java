package com.skeqi.mes.pojo.chenj.srm.req;


/**
 * @author ChenJ
 * @date 2021/6/10
 * @Classname CSrmEnquiryInvitationForBidsTH
 * @Description ${Description}
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 询价/招标头表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmEnquiryInvitationForBidsTHReq {

    /**
     * 行号
     */
    private String lineNumber;

    /**
     * 是否推荐(1.是2.否)
     */
    private String whetherToRecommend;

    /**
     * 选用理由
     */
    private String chooseReason;


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
     * OA审批回调类型(通过,驳回,撤销)
     */
    private String name;


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
     * 采购组织名称
     */
    private String purchasingOrganization;


    /**
     * 澄清截止时间
     */
    private String clarificationDeadline;

    /**
     * 开标人
     */
    private String theBidOpeningPeople;

    /**
     * 定标人
     */
    private String selectionOfPeople;

    /**
     * 操作标记(1.新增2.修改)
     */
    private String operationSign;

    /**
     * 询价/招标单编码
     */
    private String rfqOrTenderFormCode;

    /**
     * 标题
     */
    private String title;

    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;


    /**
     * 物料类别
     */

    private String materialType;

    /**
     * 供应商代码--至少邀请三家供应商
     */
    private List<String> supplierCode;

    /**
     * 供应商编码--查询用
     */
    private String supplierCodeS;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 模板编号
     */
    private String templateNumber;

    /**
     * 状态(1.新建2.报价中3.待核价4.待审批5.已完成6.招标中7.待开标8.评分中)
     */
    private String status;

    /**
     * 寻源类型(1.询报价2.招投标)
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
     * 物料编码
     */
    private String materialCode;
    /**
     * 采购员
     */
    private String buyer;

    /**
     * 公司代码
     */
    private String companyCode;

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
     * 变更说明
     */
    private String updateExplain;

    /**
     * 是否已报价
     */
    private String whetherOffer;

    /**
     * 询价\招投标数据行
     *
     * @return
     */

    private List<CSrmEnquiryInvitationForBidsTRReq> rfqList;

    /**
     * 询价/招标供应商表数据行
     *
     * @return
     */
    private List<CSrmEnquiryForBidsTSupplierReq> supList;


    /**
     * 寻源过程控制状态
     *
     * @return
     */
    private List<Integer> searchSourceConStatus;


    /**
     * OA审批单号
     */
    private String examineNumber;

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

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
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

    public String getExamineNumber() {
        return examineNumber;
    }

    public void setExamineNumber(String examineNumber) {
        this.examineNumber = examineNumber;
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

    public String getPurchasingOrganization() {
        return purchasingOrganization;
    }

    public void setPurchasingOrganization(String purchasingOrganization) {
        this.purchasingOrganization = purchasingOrganization;
    }

    public String getClarificationDeadline() {
        return clarificationDeadline;
    }

    public void setClarificationDeadline(String clarificationDeadline) {
        this.clarificationDeadline = clarificationDeadline;
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

    public List<CSrmEnquiryInvitationForBidsTRReq> getRfqList() {
        return rfqList;
    }

    public void setRfqList(List<CSrmEnquiryInvitationForBidsTRReq> rfqList) {
        this.rfqList = rfqList;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public String getMaterialType() {
        return materialType;
    }

    public String getWhetherToRecommend() {
        return whetherToRecommend;
    }

    public void setWhetherToRecommend(String whetherToRecommend) {
        this.whetherToRecommend = whetherToRecommend;
    }

    public String getChooseReason() {
        return chooseReason;
    }

    public void setChooseReason(String chooseReason) {
        this.chooseReason = chooseReason;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getWhetherOffer() {
        return whetherOffer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setWhetherOffer(String whetherOffer) {
        this.whetherOffer = whetherOffer;
    }

    public String getUpdateExplain() {
        return updateExplain;
    }

    public void setUpdateExplain(String updateExplain) {
        this.updateExplain = updateExplain;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CSrmEnquiryForBidsTSupplierReq> getSupList() {
        return supList;
    }

    public void setSupList(List<CSrmEnquiryForBidsTSupplierReq> supList) {
        this.supList = supList;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperationSign() {
        return operationSign;
    }

    public void setOperationSign(String operationSign) {
        this.operationSign = operationSign;
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

    public List<String> getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(List<String> supplierCode) {
        this.supplierCode = supplierCode;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public List<Integer> getSearchSourceConStatus() {
        return searchSourceConStatus;
    }

    public void setSearchSourceConStatus(List<Integer> searchSourceConStatus) {
        this.searchSourceConStatus = searchSourceConStatus;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    public String getSupplierCodeS() {
        return supplierCodeS;
    }

    public void setSupplierCodeS(String supplierCodeS) {
        this.supplierCodeS = supplierCodeS;
    }

    @Override
    public String toString() {
        return "CSrmEnquiryInvitationForBidsTHReq{" +
                "lineNumber='" + lineNumber + '\'' +
                ", whetherToRecommend='" + whetherToRecommend + '\'' +
                ", chooseReason='" + chooseReason + '\'' +
                ", itemNumber='" + itemNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemAddress='" + itemAddress + '\'' +
                ", exchangeRate='" + exchangeRate + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", termsOfPayment='" + termsOfPayment + '\'' +
                ", purchasingContact='" + purchasingContact + '\'' +
                ", telephone='" + telephone + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", name='" + name + '\'' +
                ", biddingTechnicalDocuments='" + biddingTechnicalDocuments + '\'' +
                ", biddingBusinessDocuments='" + biddingBusinessDocuments + '\'' +
                ", budget='" + budget + '\'' +
                ", purchasingOrganization='" + purchasingOrganization + '\'' +
                ", clarificationDeadline='" + clarificationDeadline + '\'' +
                ", theBidOpeningPeople='" + theBidOpeningPeople + '\'' +
                ", selectionOfPeople='" + selectionOfPeople + '\'' +
                ", operationSign='" + operationSign + '\'' +
                ", rfqOrTenderFormCode='" + rfqOrTenderFormCode + '\'' +
                ", title='" + title + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", materialType='" + materialType + '\'' +
                ", supplierCode=" + supplierCode +
                ", supplierCodeS='" + supplierCodeS + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", templateNumber='" + templateNumber + '\'' +
                ", status='" + status + '\'' +
                ", sourcingType='" + sourcingType + '\'' +
                ", wayOfQuoting='" + wayOfQuoting + '\'' +
                ", wayToFindTheSource='" + wayToFindTheSource + '\'' +
                ", materialCode='" + materialCode + '\'' +
                ", buyer='" + buyer + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", currency='" + currency + '\'' +
                ", quotationStartTime='" + quotationStartTime + '\'' +
                ", quotationStopTime='" + quotationStopTime + '\'' +
                ", bidOpeningPlace='" + bidOpeningPlace + '\'' +
                ", bidOpeningTime='" + bidOpeningTime + '\'' +
                ", principal='" + principal + '\'' +
                ", creator='" + creator + '\'' +
                ", updateExplain='" + updateExplain + '\'' +
                ", whetherOffer='" + whetherOffer + '\'' +
                ", rfqList=" + rfqList +
                ", supList=" + supList +
                ", searchSourceConStatus=" + searchSourceConStatus +
                ", examineNumber='" + examineNumber + '\'' +
                '}';
    }


}
