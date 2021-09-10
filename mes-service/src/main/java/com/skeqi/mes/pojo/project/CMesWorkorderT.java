package com.skeqi.mes.pojo.project;

import com.skeqi.mes.pojo.CMesProductionT;

public class CMesWorkorderT {

	private Integer id;
	private Integer planId;
	private String workName;
	private Integer number;
	private String productMark;
	private Integer surplusNumber;
	private Integer completeNumber;
	private Integer scheId;
	private String planName;
	private String proName;
	private String proType;
	//产品
	private CMesProductionT production;

	public CMesProductionT getProduction() {
		return production;
	}
	public void setProduction(CMesProductionT production) {
		this.production = production;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProType() {
		return proType;
	}
	public void setProType(String proType) {
		this.proType = proType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getProductMark() {
		return productMark;
	}
	public void setProductMark(String productMark) {
		this.productMark = productMark;
	}
	public Integer getSurplusNumber() {
		return surplusNumber;
	}
	public void setSurplusNumber(Integer surplusNumber) {
		this.surplusNumber = surplusNumber;
	}
	public Integer getCompleteNumber() {
		return completeNumber;
	}
	public void setCompleteNumber(Integer completeNumber) {
		this.completeNumber = completeNumber;
	}
	public Integer getScheId() {
		return scheId;
	}
	public void setScheId(Integer scheId) {
		this.scheId = scheId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public CMesWorkorderT(Integer id, Integer planId, String workName, Integer number, String productMark,
			Integer surplusNumber, Integer completeNumber, Integer scheId, String planName) {
		super();
		this.id = id;
		this.planId = planId;
		this.workName = workName;
		this.number = number;
		this.productMark = productMark;
		this.surplusNumber = surplusNumber;
		this.completeNumber = completeNumber;
		this.scheId = scheId;
		this.planName = planName;
	}
	public CMesWorkorderT() {
		super();
	}


}
