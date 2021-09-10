package com.skeqi.mes.pojo.chenj.srm.req;


 /**
 * @author ChenJ
 * @date 2021/6/8
 * @Classname CSrmSupplier
 * @Description ${Description}
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 供应商信息表
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSrmSupplierReq {

    /**
     * 公司名称
     */
    private String companyName;


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
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;


	/**
	 * 供应商编码
	 */
	private String supplierCode;




	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
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

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	@Override
	public String toString() {
		return "CSrmSupplierReq{" +
			"companyName='" + companyName + '\'' +
			", name='" + name + '\'' +
			", contactPerson='" + contactPerson + '\'' +
			", contactNumber='" + contactNumber + '\'' +
			", contactEmail='" + contactEmail + '\'' +
			", account='" + account + '\'' +
			", password='" + password + '\'' +
			", supplierCode='" + supplierCode + '\'' +
			'}';
	}
}
