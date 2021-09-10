package com.skeqi.mes.pojo.chenj.srm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


 /**
 * @author ChenJ
 * @date 2021/7/5
 * @Classname CSrmPurPartnerInfoR
 * @Description ${Description}
 */

/**
 * 添加采购合作伙伴信息行表
 */
@ApiModel(value = "com-skeqi-pojo-chenj-CSrmPurPartnerInfoR")
public class CSrmPurPartnerInfoR {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * 伙伴类型名称
     */
    @ApiModelProperty(value = "伙伴类型名称")
    private String partnerTypeName;

    /**
     * 伙伴类型编码
     */
    @ApiModelProperty(value = "伙伴类型编码")
    private String partnerTypeCode;

    /**
     * 公司编码
     */
    @ApiModelProperty(value = "公司编码")
    private String companyCode;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    private String corporateName;

    /**
     * 代表人
     */
    @ApiModelProperty(value = "代表人")
    private String representative;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = "联系方式")
    private String contactInformation;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String mailbox;

    /**
     * 开户行名称
     */
    @ApiModelProperty(value = "开户行名称")
    private String nameOfBankOfDeposit;

    /**
     * 银行账号
     */
    @ApiModelProperty(value = "银行账号")
    private String bankAccount;

    /**
     * 说明
     */
    @ApiModelProperty(value = "说明")
    private String explain;

    /**
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号")
    private String contractNo;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 行项目号
     * @return
     */
    @ApiModelProperty(value = "行项目号")
    private String lineItemNo;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(String lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getPartnerTypeName() {
        return partnerTypeName;
    }

    public void setPartnerTypeName(String partnerTypeName) {
        this.partnerTypeName = partnerTypeName;
    }

    public String getPartnerTypeCode() {
        return partnerTypeCode;
    }

    public void setPartnerTypeCode(String partnerTypeCode) {
        this.partnerTypeCode = partnerTypeCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getNameOfBankOfDeposit() {
        return nameOfBankOfDeposit;
    }

    public void setNameOfBankOfDeposit(String nameOfBankOfDeposit) {
        this.nameOfBankOfDeposit = nameOfBankOfDeposit;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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

    @Override
    public String toString() {
        return "CSrmPurPartnerInfoR{" +
                "id=" + id +
                ", partnerTypeName='" + partnerTypeName + '\'' +
                ", partnerTypeCode='" + partnerTypeCode + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", corporateName='" + corporateName + '\'' +
                ", representative='" + representative + '\'' +
                ", address='" + address + '\'' +
                ", contactInformation='" + contactInformation + '\'' +
                ", mailbox='" + mailbox + '\'' +
                ", nameOfBankOfDeposit='" + nameOfBankOfDeposit + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", explain='" + explain + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", lineItemNo='" + lineItemNo + '\'' +
                '}';
    }
}
