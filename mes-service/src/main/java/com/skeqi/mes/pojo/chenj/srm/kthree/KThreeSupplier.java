package com.skeqi.mes.pojo.chenj.srm.kthree;


import java.util.Date;

/**
 * @author ChenJ
 * @date 2021/7/28
 * @Classname KThreeSupplier
 * @Description 供应商接口-查询头行
 */

public class KThreeSupplier {
    /**
     * 编码
     */
    private String ID;
    /**
     * 名称
     */
    private String FName;
    /**
     * 创建时间
     */
    private Date cDate;
    /**
     * 更新时间
     */
    private Date mDate;
    /**
     * 地址
     */
    private String FAddress;
    /**
     * 电话
     */
    private String FPhone;
    /**
     * 传真
     */
    private String FFax;
    /**
     * 移动电话
     */
    private String FMobilePhone;
    /**
     * 开户行
     */
    private String FBank;
    /**
     * 银行账号
     */
    private String FAccount;
    /**
     * 简称
     */
    private String FShortName;
    /**
     * 联系人
     */
    private String FContact;
    /**
     * 税号
     */
    private String FTaxNum;

    /**
     * 状态(0.启用1.禁用)
     */
    private Integer FDeleted;

    // 供应商基础信息

    /**
     * 供应商名称
     */
    private String name;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 地址
     */
    private String address;
    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    // 默认密码：123456 然后加密
    // this.password = Encryption.getPassWord("123456" + FName + 666666 + "123456", 555);
    private String password; // Encryption.getPassWord("123456" + this.getAccount() + 666666 + "123456", 555)
    //  cSrmSupplierReq.setPassword(Encryption.getPassWord(cSrmSupplierReq.getPassword() + cSrmSupplierReq.getAccount() + 666666 + cSrmSupplierReq.getPassword(), 555));

    /**
     * 供应商代码
     */
    private String supplierCode;

    /**
     * 认证状态(0.冻结1.未认证2.认证中3.已认证4.认证失败5.企业信息变更失败)
     */
    private Integer status = 3;

    /**
     * 权限id(83.未认证 84.已认证 85.SRM管理员)
     */
    private String roleId = "84";

    /**
     * 电话(座机)
     */
    private String phone;

    /**
     * 传真
     */
    private String fax;

    /**
     * 简称
     */
    private String abbreviation;


    // 供应商银行信息

    /**
     * 开户银行名称
     */
    private String bankName;
    /**
     * 银行账号
     */
    private String bankAccount;
    /**
     * 税号
     */
    private String taxFileNumber;


    public void setID(String ID) {
        this.ID = ID;
        this.supplierCode = ID;
    }

    public String getID() {
        return ID;
    }

    public void setFName(String FName) {
        this.FName = FName;
        this.name = FName;
        this.account = FName;
    }

    public String getFName() {
        return FName;
    }

    public void setCDate(Date cDate) {
        this.cDate = cDate;
    }

    public Date getCDate() {
        return cDate;
    }

    public void setMDate(Date mDate) {
        this.mDate = mDate;
    }

    public Date getMDate() {
        return mDate;
    }

    public void setFAddress(String FAddress) {
        this.FAddress = FAddress;
        this.address = FAddress;
    }

    public String getFAddress() {
        return FAddress;
    }

    public void setFPhone(String FPhone) {
        this.FPhone = FPhone;
        this.phone = FPhone;
    }

    public String getFPhone() {
        return FPhone;
    }

    public void setFFax(String FFax) {
        this.FFax = FFax;
        this.fax = FFax;
    }

    public String getFFax() {
        return FFax;
    }

    public void setFMobilePhone(String FMobilePhone) {
        this.FMobilePhone = FMobilePhone;
        this.contactNumber = FMobilePhone;
    }

    public String getFMobilePhone() {
        return FMobilePhone;
    }

    public void setFBank(String FBank) {
        this.FBank = FBank;
        this.bankName = FBank;
    }

    public String getFBank() {
        return FBank;
    }

    public void setFAccount(String FAccount) {
        this.FAccount = FAccount;
        this.bankAccount = FAccount;
    }

    public String getFAccount() {
        return FAccount;
    }

    public void setFShortName(String FShortName) {
        this.FShortName = FShortName;
        this.abbreviation = FShortName;
    }

    public String getFShortName() {
        return FShortName;
    }

    public void setFContact(String FContact) {
        this.FContact = FContact;
        this.contactPerson = FContact;
    }

    public String getFContact() {
        return FContact;
    }

    public void setFTaxNum(String FTaxNum) {
        this.FTaxNum = FTaxNum;
        this.taxFileNumber = FTaxNum;
    }

    public String getFTaxNum() {
        return FTaxNum;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.FName =name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        this.FAddress = address;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        this.FPhone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
        this.FShortName = abbreviation;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
        this.FBank = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        this.FAccount = bankAccount;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
        this.FContact = contactPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        this.FMobilePhone = contactNumber;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
        this.ID = supplierCode;
    }

    public String getTaxFileNumber() {
        return taxFileNumber;
    }

    public void setTaxFileNumber(String taxFileNumber) {
        this.taxFileNumber = taxFileNumber;
        this.FTaxNum = taxFileNumber;
    }

    public Integer getFDeleted() {
        return FDeleted;
    }

    public void setFDeleted(Integer FDeleted) {
        this.FDeleted = FDeleted;
    }

    @Override
    public String toString() {
        return "KThreeSupplier{" +
                "ID='" + ID + '\'' +
                ", FName='" + FName + '\'' +
                ", cDate=" + cDate +
                ", mDate=" + mDate +
                ", FAddress='" + FAddress + '\'' +
                ", FPhone='" + FPhone + '\'' +
                ", FFax='" + FFax + '\'' +
                ", FMobilePhone='" + FMobilePhone + '\'' +
                ", FBank='" + FBank + '\'' +
                ", FAccount='" + FAccount + '\'' +
                ", FShortName='" + FShortName + '\'' +
                ", FContact='" + FContact + '\'' +
                ", FTaxNum='" + FTaxNum + '\'' +
                ", FDeleted=" + FDeleted +
                ", name='" + name + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", status=" + status +
                ", roleId='" + roleId + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", taxFileNumber='" + taxFileNumber + '\'' +
                '}';
    }
}
