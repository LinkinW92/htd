package com.skeqi.mes.pojo.api;

public class AssembleLeakagePT {


	private Integer Id;
	private String tempL;
	private String tempP;
	private String tempR;
	private String tempName;

	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getTempL() {
		return tempL;
	}
	public void setTempL(String tempL) {
		this.tempL = tempL;
	}
	public String getTempP() {
		return tempP;
	}
	public void setTempP(String tempP) {
		this.tempP = tempP;
	}
	public String getTempR() {
		return tempR;
	}
	public void setTempR(String tempR) {
		this.tempR = tempR;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	@Override
	public String toString() {
		return "AssembleLeakagePT [Id=" + Id + ", tempL=" + tempL + ", tempP=" + tempP + ", tempR=" + tempR
				+ ", tempName=" + tempName + "]";
	}






}
