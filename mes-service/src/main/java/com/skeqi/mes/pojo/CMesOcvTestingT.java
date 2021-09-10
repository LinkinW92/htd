package com.skeqi.mes.pojo;
/**
 * OCV
 * @author : FQZ
 * @Package: com.skeqi.pojo
 * @date   : 2020年3月9日 上午10:30:03
 */
public class CMesOcvTestingT {

	private Integer id;
	private String voltage;   //电压
	private String resistance;   //内阻
	private String batteriesNum;   //电芯条码
	private String batteriesStatus;   //状态
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getResistance() {
		return resistance;
	}
	public void setResistance(String resistance) {
		this.resistance = resistance;
	}
	public String getBatteriesNum() {
		return batteriesNum;
	}
	public void setBatteriesNum(String batteriesNum) {
		this.batteriesNum = batteriesNum;
	}
	public String getBatteriesStatus() {
		return batteriesStatus;
	}
	public void setBatteriesStatus(String batteriesStatus) {
		this.batteriesStatus = batteriesStatus;
	}
	@Override
	public String toString() {
		return "CMesOcvTestingT [id=" + id + ", voltage=" + voltage + ", resistance=" + resistance + ", batteriesNum="
				+ batteriesNum + ", batteriesStatus=" + batteriesStatus + "]";
	}
	public CMesOcvTestingT(Integer id, String voltage, String resistance, String batteriesNum, String batteriesStatus) {
		super();
		this.id = id;
		this.voltage = voltage;
		this.resistance = resistance;
		this.batteriesNum = batteriesNum;
		this.batteriesStatus = batteriesStatus;
	}
	public CMesOcvTestingT() {
		super();
	}


}
