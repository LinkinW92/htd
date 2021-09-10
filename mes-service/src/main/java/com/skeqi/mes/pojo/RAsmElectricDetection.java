package com.skeqi.mes.pojo;

public class RAsmElectricDetection {
	private Integer id;
	private String sn;
	private String dt;
	private String checkBurmer;
	private String checkCommunication;
	private String checkAllvoltage;
	private String valueAllvoltage;
	private String checkSinglevoltage;
	private String valueCellvmax;
	private String valueCellvmin;
	private String valueCellvdif;
	private String checkTemperature;
	private String valueCelltmax;
	private String valueCelltmin;
	private String valueCelltdif;
	private String checkPlusMinus;
	private String checkPriming;
	private String checkTricklecharge;
	private String checkHighhanded;
	private String checkFause;
	private String checkHardbord;
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
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getCheckBurmer() {
		return checkBurmer;
	}
	public void setCheckBurmer(String checkBurmer) {
		this.checkBurmer = checkBurmer;
	}
	public String getCheckCommunication() {
		return checkCommunication;
	}
	public void setCheckCommunication(String checkCommunication) {
		this.checkCommunication = checkCommunication;
	}
	public String getCheckAllvoltage() {
		return checkAllvoltage;
	}
	public void setCheckAllvoltage(String checkAllvoltage) {
		this.checkAllvoltage = checkAllvoltage;
	}
	public String getValueAllvoltage() {
		return valueAllvoltage;
	}
	public void setValueAllvoltage(String valueAllvoltage) {
		this.valueAllvoltage = valueAllvoltage;
	}
	public String getCheckSinglevoltage() {
		return checkSinglevoltage;
	}
	public void setCheckSinglevoltage(String checkSinglevoltage) {
		this.checkSinglevoltage = checkSinglevoltage;
	}
	public String getValueCellvmax() {
		return valueCellvmax;
	}
	public void setValueCellvmax(String valueCellvmax) {
		this.valueCellvmax = valueCellvmax;
	}
	public String getValueCellvmin() {
		return valueCellvmin;
	}
	public void setValueCellvmin(String valueCellvmin) {
		this.valueCellvmin = valueCellvmin;
	}
	public String getValueCellvdif() {
		return valueCellvdif;
	}
	public void setValueCellvdif(String valueCellvdif) {
		this.valueCellvdif = valueCellvdif;
	}
	public String getCheckTemperature() {
		return checkTemperature;
	}
	public void setCheckTemperature(String checkTemperature) {
		this.checkTemperature = checkTemperature;
	}
	public String getValueCelltmax() {
		return valueCelltmax;
	}
	public void setValueCelltmax(String valueCelltmax) {
		this.valueCelltmax = valueCelltmax;
	}
	public String getValueCelltmin() {
		return valueCelltmin;
	}
	public void setValueCelltmin(String valueCelltmin) {
		this.valueCelltmin = valueCelltmin;
	}
	public String getValueCelltdif() {
		return valueCelltdif;
	}
	public void setValueCelltdif(String valueCelltdif) {
		this.valueCelltdif = valueCelltdif;
	}
	public String getCheckPlusMinus() {
		return checkPlusMinus;
	}
	public void setCheckPlusMinus(String checkPlusMinus) {
		this.checkPlusMinus = checkPlusMinus;
	}
	public String getCheckPriming() {
		return checkPriming;
	}
	public void setCheckPriming(String checkPriming) {
		this.checkPriming = checkPriming;
	}
	public String getCheckTricklecharge() {
		return checkTricklecharge;
	}
	public void setCheckTricklecharge(String checkTricklecharge) {
		this.checkTricklecharge = checkTricklecharge;
	}
	public String getCheckHighhanded() {
		return checkHighhanded;
	}
	public void setCheckHighhanded(String checkHighhanded) {
		this.checkHighhanded = checkHighhanded;
	}
	public String getCheckFause() {
		return checkFause;
	}
	public void setCheckFause(String checkFause) {
		this.checkFause = checkFause;
	}
	public String getCheckHardbord() {
		return checkHardbord;
	}
	public void setCheckHardbord(String checkHardbord) {
		this.checkHardbord = checkHardbord;
	}
	public RAsmElectricDetection(Integer id, String sn, String dt, String checkBurmer, String checkCommunication,
			String checkAllvoltage, String valueAllvoltage, String checkSinglevoltage, String valueCellvmax,
			String valueCellvmin, String valueCellvdif, String checkTemperature, String valueCelltmax,
			String valueCelltmin, String valueCelltdif, String checkPlusMinus, String checkPriming,
			String checkTricklecharge, String checkHighhanded, String checkFause, String checkHardbord) {
		super();
		this.id = id;
		this.sn = sn;
		this.dt = dt;
		this.checkBurmer = checkBurmer;
		this.checkCommunication = checkCommunication;
		this.checkAllvoltage = checkAllvoltage;
		this.valueAllvoltage = valueAllvoltage;
		this.checkSinglevoltage = checkSinglevoltage;
		this.valueCellvmax = valueCellvmax;
		this.valueCellvmin = valueCellvmin;
		this.valueCellvdif = valueCellvdif;
		this.checkTemperature = checkTemperature;
		this.valueCelltmax = valueCelltmax;
		this.valueCelltmin = valueCelltmin;
		this.valueCelltdif = valueCelltdif;
		this.checkPlusMinus = checkPlusMinus;
		this.checkPriming = checkPriming;
		this.checkTricklecharge = checkTricklecharge;
		this.checkHighhanded = checkHighhanded;
		this.checkFause = checkFause;
		this.checkHardbord = checkHardbord;
	}
	public RAsmElectricDetection() {
		super();
	}
	@Override
	public String toString() {
		return "RAsmElectricDetection [id=" + id + ", sn=" + sn + ", dt=" + dt + ", checkBurmer=" + checkBurmer
				+ ", checkCommunication=" + checkCommunication + ", checkAllvoltage=" + checkAllvoltage
				+ ", valueAllvoltage=" + valueAllvoltage + ", checkSinglevoltage=" + checkSinglevoltage
				+ ", valueCellvmax=" + valueCellvmax + ", valueCellvmin=" + valueCellvmin + ", valueCellvdif="
				+ valueCellvdif + ", checkTemperature=" + checkTemperature + ", valueCelltmax=" + valueCelltmax
				+ ", valueCelltmin=" + valueCelltmin + ", valueCelltdif=" + valueCelltdif + ", checkPlusMinus="
				+ checkPlusMinus + ", checkPriming=" + checkPriming + ", checkTricklecharge=" + checkTricklecharge
				+ ", checkHighhanded=" + checkHighhanded + ", checkFause=" + checkFause + ", checkHardbord="
				+ checkHardbord + "]";
	}



}
