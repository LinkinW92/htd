package com.skeqi.mes.pojo.chenj.srm.rsp;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author ChenJ
 * @date 2021/7/4
 * @Classname CSrmTheNumberAuditH
 * @Description ${Description}
 */

/**
 * 开票申请审核头表(存储提交非寄售开票单维护绑定数据) 出参
 */
public class CSrmTheNumberAuditHRsp {

    /**
     * 开票单号
     */
    @ApiModelProperty(value = "开票单号")
    private String theNumberOfHeInvoiceApplication;

    /**
     * 供应商名称
     */
    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    /**
     * 不含税总金额
     */
    @ApiModelProperty(value = "不含税总金额")
    private String noTaxCountMoney;

    /**
     * 币种
     */
    @ApiModelProperty(value = "币种")
    private String currency;

    /**
     * 供应商编码
     */
    @ApiModelProperty(value = "供应商编码")
    private String supplierCode;

    /**
     * 总税额
     */
    @ApiModelProperty(value = "总税额")
    private String countTax;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     * 供应商地址
     */
    @ApiModelProperty(value = "供应商地址")
    private String address;

    /**
     * 含税总金额
     */
    @ApiModelProperty(value = "含税总金额")
    private String taxMoneyCountMoney;

    /**
     * 供应商备注
     */
    @ApiModelProperty(value = "供应商备注")
    private String supplierRemark;

    /**
     * 公司
     */
    @ApiModelProperty(value = "公司")
    private String companyName;

    /**
     * 业务实体
     */
    @ApiModelProperty(value = "业务实体")
    private String businessEntity;

    /**
     * 采购组织
     */
    @ApiModelProperty(value = "采购组织")
    private String purOrganization;

    /**
     * 库存组织
     */
    @ApiModelProperty(value = "库存组织")
    private String shippingAddress;

    /**
     * 采购员
     */
    @ApiModelProperty(value = "采购员")
    private String buyer;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String creator;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 提交日期
     */
    @ApiModelProperty(value = "提交日期")
    private String updateTime;

    /**
     * 出票方
     */
    @ApiModelProperty(value = "出票方")
    private String theDrawerSide;

    /**
     * 开票主题
     */
    @ApiModelProperty(value = "开票主题")
    private String makeTheme;

    /**
     * 期初余额
     */
    @ApiModelProperty(value = "期初余额")
    private String theBalance;

    /**
     * 本期增加额
     */
    @ApiModelProperty(value = "本期增加额")
    private String currentIncrease;

    /**
     * 本期减少额
     */
    @ApiModelProperty(value = "本期减少额")
    private String currentReduction;

    /**
     * 期末余额
     */
    @ApiModelProperty(value = "期末余额")
    private String endingBalance;

    /**
     *
     * @return
     */
    @ApiModelProperty(value = "开票申请审核行数据")
    private PageInfo<CSrmTheNumberAuditRRsp> reqList;



    /**
     * 不含税总金额
     */
    private BigDecimal noTaxMoneyCountMoney;
    /**
     * 总税额
     */
    private BigDecimal sumTax;


    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;

    public String getTheNumberOfHeInvoiceApplication() {
        return theNumberOfHeInvoiceApplication;
    }

    public void setTheNumberOfHeInvoiceApplication(String theNumberOfHeInvoiceApplication) {
        this.theNumberOfHeInvoiceApplication = theNumberOfHeInvoiceApplication;
    }


    public String getNoTaxCountMoney() {
        return noTaxCountMoney;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public void setNoTaxCountMoney(String noTaxCountMoney) {
        this.noTaxCountMoney = noTaxCountMoney;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountTax() {
        return countTax;
    }

    public void setCountTax(String countTax) {
        this.countTax = countTax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxMoneyCountMoney() {
        return taxMoneyCountMoney;
    }

    public void setTaxMoneyCountMoney(String taxMoneyCountMoney) {
        this.taxMoneyCountMoney = taxMoneyCountMoney;
    }

    public String getSupplierRemark() {
        return supplierRemark;
    }

    public void setSupplierRemark(String supplierRemark) {
        this.supplierRemark = supplierRemark;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessEntity() {
        return businessEntity;
    }

    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    public String getPurOrganization() {
        return purOrganization;
    }

    public void setPurOrganization(String purOrganization) {
        this.purOrganization = purOrganization;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTheDrawerSide() {
        return theDrawerSide;
    }

    public void setTheDrawerSide(String theDrawerSide) {
        this.theDrawerSide = theDrawerSide;
    }

    public String getMakeTheme() {
        return makeTheme;
    }

    public void setMakeTheme(String makeTheme) {
        this.makeTheme = makeTheme;
    }

    public String getTheBalance() {
        return theBalance;
    }

    public void setTheBalance(String theBalance) {
        this.theBalance = theBalance;
    }

    public String getCurrentIncrease() {
        return currentIncrease;
    }

    public void setCurrentIncrease(String currentIncrease) {
        this.currentIncrease = currentIncrease;
    }

    public String getCurrentReduction() {
        return currentReduction;
    }

    public void setCurrentReduction(String currentReduction) {
        this.currentReduction = currentReduction;
    }

    public String getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(String endingBalance) {
        this.endingBalance = endingBalance;
    }

    public PageInfo<CSrmTheNumberAuditRRsp> getReqList() {
        return reqList;
    }

    public void setReqList(PageInfo<CSrmTheNumberAuditRRsp> reqList) {
        this.reqList = reqList;
    }

    public BigDecimal getNoTaxMoneyCountMoney() {
        return noTaxMoneyCountMoney;
    }

    public void setNoTaxMoneyCountMoney(BigDecimal noTaxMoneyCountMoney) {
        this.noTaxMoneyCountMoney = noTaxMoneyCountMoney;
    }

    public BigDecimal getSumTax() {
        return sumTax;
    }

    public void setSumTax(BigDecimal sumTax) {
        this.sumTax = sumTax;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "CSrmTheNumberAuditHRsp{" +
                "theNumberOfHeInvoiceApplication='" + theNumberOfHeInvoiceApplication + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", noTaxCountMoney='" + noTaxCountMoney + '\'' +
                ", currency='" + currency + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", countTax='" + countTax + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", taxMoneyCountMoney='" + taxMoneyCountMoney + '\'' +
                ", supplierRemark='" + supplierRemark + '\'' +
                ", companyName='" + companyName + '\'' +
                ", businessEntity='" + businessEntity + '\'' +
                ", purOrganization='" + purOrganization + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", buyer='" + buyer + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", theDrawerSide='" + theDrawerSide + '\'' +
                ", makeTheme='" + makeTheme + '\'' +
                ", theBalance='" + theBalance + '\'' +
                ", currentIncrease='" + currentIncrease + '\'' +
                ", currentReduction='" + currentReduction + '\'' +
                ", endingBalance='" + endingBalance + '\'' +
                ", reqList=" + reqList +
                ", noTaxMoneyCountMoney=" + noTaxMoneyCountMoney +
                ", sumTax=" + sumTax +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
