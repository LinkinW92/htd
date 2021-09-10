package com.skeqi.mes.pojo.api;

public class AssembleKeypartPT {

	//ID
	private Integer tempMaxId;
	//物料编号
	private String tempKeypartNum;
	//是否是唯一码
	private Integer tempKeypartType;
	//物料唯一条码
	private String tempSecondNum;


	public Integer getTempMaxId() {
		return tempMaxId;
	}

	public void setTempMaxId(Integer tempMaxId) {
		this.tempMaxId = tempMaxId;
	}

	public String getTempKeypartNum() {
		return tempKeypartNum;
	}

	public void setTempKeypartNum(String tempKeypartNum) {
		this.tempKeypartNum = tempKeypartNum;
	}

	public Integer getTempKeypartType() {
		return tempKeypartType;
	}

	public void setTempKeypartType(Integer tempKeypartType) {
		this.tempKeypartType = tempKeypartType;
	}

	public String getTempSecondNum() {
		return tempSecondNum;
	}

	public void setTempSecondNum(String tempSecondNum) {
		this.tempSecondNum = tempSecondNum;
	}

	@Override
	public String toString() {
		return "AssembleKeypartPT [tempMaxId=" + tempMaxId + ", tempKeypartNum=" + tempKeypartNum + ", tempKeypartType="
				+ tempKeypartType + ", tempSecondNum=" + tempSecondNum + "]";
	}



}
