package com.skeqi.mes.pojo.chenj.srm.kthree;


import java.util.Date;

/**
 * @author ChenJ
 * @date 2021/7/28
 * @Classname KThreeSupplier
 * @Description 供应商接口-新增、修改
 */

public class KThreeSupplierReq {
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
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

    public String getFAddress() {
        return FAddress;
    }

    public void setFAddress(String FAddress) {
        this.FAddress = FAddress;
    }

    public String getFPhone() {
        return FPhone;
    }

    public void setFPhone(String FPhone) {
        this.FPhone = FPhone;
    }

    public String getFFax() {
        return FFax;
    }

    public void setFFax(String FFax) {
        this.FFax = FFax;
    }

    public String getFMobilePhone() {
        return FMobilePhone;
    }

    public void setFMobilePhone(String FMobilePhone) {
        this.FMobilePhone = FMobilePhone;
    }

    public String getFBank() {
        return FBank;
    }

    public void setFBank(String FBank) {
        this.FBank = FBank;
    }

    public String getFAccount() {
        return FAccount;
    }

    public void setFAccount(String FAccount) {
        this.FAccount = FAccount;
    }

    public String getFShortName() {
        return FShortName;
    }

    public void setFShortName(String FShortName) {
        this.FShortName = FShortName;
    }

    public String getFContact() {
        return FContact;
    }

    public void setFContact(String FContact) {
        this.FContact = FContact;
    }

    public String getFTaxNum() {
        return FTaxNum;
    }

    public void setFTaxNum(String FTaxNum) {
        this.FTaxNum = FTaxNum;
    }

    public Integer getFDeleted() {
        return FDeleted;
    }

    public void setFDeleted(Integer FDeleted) {
        this.FDeleted = FDeleted;
    }

    @Override
    public String toString() {
        return "KThreeSupplierReq{" +
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
                '}';
    }
}
