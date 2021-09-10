package com.skeqi.mes.pojo;

import java.util.Date;

public class PMesDischargeT {

	private Integer id;
	private String sn;
	private String mcNum;
	private String stCode;
	private String operator;
	private String stepNo;
	private String inStructions;
	private String testValue;
	private String judgment;
	private String unit;
	private String isPass;
	private String result;
	private String optime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getMcNum() {
		return mcNum;
	}
	public void setMcNum(String mcNum) {
		this.mcNum = mcNum;
	}
	public String getStCode() {
		return stCode;
	}
	public void setStCode(String stCode) {
		this.stCode = stCode;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getStepNo() {
		return stepNo;
	}
	public void setStepNo(String stepNo) {
		this.stepNo = stepNo;
	}
	public String getInStructions() {
		return inStructions;
	}
	public void setInStructions(String inStructions) {
		this.inStructions = inStructions;
	}
	public String getTestValue() {
		return testValue;
	}
	public void setTestValue(String testValue) {
		this.testValue = testValue;
	}
	public String getJudgment() {
		return judgment;
	}
	public void setJudgment(String judgment) {
		this.judgment = judgment;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getIsPass() {
		return isPass;
	}
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public String getOptime() {
		return optime;
	}
	public void setOptime(String optime) {
		this.optime = optime;
	}



	@Override
	public String toString() {
		return "PMesDischargeT [id=" + id + ", sn=" + sn + ", mcNum=" + mcNum + ", stCode=" + stCode + ", operator="
				+ operator + ", stepNo=" + stepNo + ", inStructions=" + inStructions + ", testValue=" + testValue
				+ ", judgment=" + judgment + ", unit=" + unit + ", isPass=" + isPass + ", result=" + result
				+ ", optime=" + optime + "]";
	}
	public PMesDischargeT(Integer id, String sn, String mcNum, String stCode, String operator, String stepNo,
			String inStructions, String testValue, String judgment, String unit, String isPass, String result,
			String optime) {
		super();
		this.id = id;
		this.sn = sn;
		this.mcNum = mcNum;
		this.stCode = stCode;
		this.operator = operator;
		this.stepNo = stepNo;
		this.inStructions = inStructions;
		this.testValue = testValue;
		this.judgment = judgment;
		this.unit = unit;
		this.isPass = isPass;
		this.result = result;
		this.optime = optime;
	}
	public PMesDischargeT() {
		super();
	}



}
