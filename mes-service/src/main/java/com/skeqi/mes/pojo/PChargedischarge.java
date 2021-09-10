package com.skeqi.mes.pojo;

public class PChargedischarge {
	private Integer id;
	private String dt;
	private String sn;
	private String res;
	private String dicrActual;
	private String dicrSet;
	private String dicrRes;
	private String zeroActual;
	private String zeroSet;
	private String zeroRes;
	private String chargeActual;
	private String chargeSet;
	private String chargeRes;
	private String dischargeActual;
	private String dischargeSet;
	private String dischargeRes;
	private String volumeActual;
	private String volumeSet;
	private String volumeRes;
	private String socActual;
	private String socSet;
	private String socRes;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public String getDicrActual() {
		return dicrActual;
	}
	public void setDicrActual(String dicrActual) {
		this.dicrActual = dicrActual;
	}
	public String getDicrSet() {
		return dicrSet;
	}
	public void setDicrSet(String dicrSet) {
		this.dicrSet = dicrSet;
	}
	public String getDicrRes() {
		return dicrRes;
	}
	public void setDicrRes(String dicrRes) {
		this.dicrRes = dicrRes;
	}
	public String getZeroActual() {
		return zeroActual;
	}
	public void setZeroActual(String zeroActual) {
		this.zeroActual = zeroActual;
	}
	public String getZeroSet() {
		return zeroSet;
	}
	public void setZeroSet(String zeroSet) {
		this.zeroSet = zeroSet;
	}
	public String getZeroRes() {
		return zeroRes;
	}
	public void setZeroRes(String zeroRes) {
		this.zeroRes = zeroRes;
	}
	public String getChargeActual() {
		return chargeActual;
	}
	public void setChargeActual(String chargeActual) {
		this.chargeActual = chargeActual;
	}
	public String getChargeSet() {
		return chargeSet;
	}
	public void setChargeSet(String chargeSet) {
		this.chargeSet = chargeSet;
	}
	public String getChargeRes() {
		return chargeRes;
	}
	public void setChargeRes(String chargeRes) {
		this.chargeRes = chargeRes;
	}
	public String getDischargeActual() {
		return dischargeActual;
	}
	public void setDischargeActual(String dischargeActual) {
		this.dischargeActual = dischargeActual;
	}
	public String getDischargeSet() {
		return dischargeSet;
	}
	public void setDischargeSet(String dischargeSet) {
		this.dischargeSet = dischargeSet;
	}
	public String getDischargeRes() {
		return dischargeRes;
	}
	public void setDischargeRes(String dischargeRes) {
		this.dischargeRes = dischargeRes;
	}
	public String getVolumeActual() {
		return volumeActual;
	}
	public void setVolumeActual(String volumeActual) {
		this.volumeActual = volumeActual;
	}
	public String getVolumeSet() {
		return volumeSet;
	}
	public void setVolumeSet(String volumeSet) {
		this.volumeSet = volumeSet;
	}
	public String getVolumeRes() {
		return volumeRes;
	}
	public void setVolumeRes(String volumeRes) {
		this.volumeRes = volumeRes;
	}
	public String getSocActual() {
		return socActual;
	}
	public void setSocActual(String socActual) {
		this.socActual = socActual;
	}
	public String getSocSet() {
		return socSet;
	}
	public void setSocSet(String socSet) {
		this.socSet = socSet;
	}
	public String getSocRes() {
		return socRes;
	}
	public void setSocRes(String socRes) {
		this.socRes = socRes;
	}
	public PChargedischarge(Integer id, String dt, String sn, String res, String dicrActual, String dicrSet,
			String dicrRes, String zeroActual, String zeroSet, String zeroRes, String chargeActual, String chargeSet,
			String chargeRes, String dischargeActual, String dischargeSet, String dischargeRes, String volumeActual,
			String volumeSet, String volumeRes, String socActual, String socSet, String socRes) {
		super();
		this.id = id;
		this.dt = dt;
		this.sn = sn;
		this.res = res;
		this.dicrActual = dicrActual;
		this.dicrSet = dicrSet;
		this.dicrRes = dicrRes;
		this.zeroActual = zeroActual;
		this.zeroSet = zeroSet;
		this.zeroRes = zeroRes;
		this.chargeActual = chargeActual;
		this.chargeSet = chargeSet;
		this.chargeRes = chargeRes;
		this.dischargeActual = dischargeActual;
		this.dischargeSet = dischargeSet;
		this.dischargeRes = dischargeRes;
		this.volumeActual = volumeActual;
		this.volumeSet = volumeSet;
		this.volumeRes = volumeRes;
		this.socActual = socActual;
		this.socSet = socSet;
		this.socRes = socRes;
	}
	public PChargedischarge() {
		super();
	}
	@Override
	public String toString() {
		return "PChargedischarge [id=" + id + ", dt=" + dt + ", sn=" + sn + ", res=" + res + ", dicrActual="
				+ dicrActual + ", dicrSet=" + dicrSet + ", dicrRes=" + dicrRes + ", zeroActual=" + zeroActual
				+ ", zeroSet=" + zeroSet + ", zeroRes=" + zeroRes + ", chargeActual=" + chargeActual + ", chargeSet="
				+ chargeSet + ", chargeRes=" + chargeRes + ", dischargeActual=" + dischargeActual + ", dischargeSet="
				+ dischargeSet + ", dischargeRes=" + dischargeRes + ", volumeActual=" + volumeActual + ", volumeSet="
				+ volumeSet + ", volumeRes=" + volumeRes + ", socActual=" + socActual + ", socSet=" + socSet
				+ ", socRes=" + socRes + "]";
	}


}
