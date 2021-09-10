package com.skeqi.mes.pojo;


public class CMesAlarmCodeT {
	private Integer id;
	private Integer alarmCode;
	private String alarmText;
	private String alarmEnglish;
	private Integer alarmGrade;
	public CMesAlarmCodeT(Integer id, Integer alarmCode, String alarmText, String alarmEnglish,Integer alarmGrade) {
		super();
		this.id = id;
		this.alarmCode = alarmCode;
		this.alarmText = alarmText;
		this.alarmEnglish = alarmEnglish;
		this.alarmGrade=alarmGrade;
	}
	public Integer getAlarmGrade() {
		return alarmGrade;
	}
	public void setAlarmGrade(Integer alarmGrade) {
		this.alarmGrade = alarmGrade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAlarmCode() {
		return alarmCode;
	}
	public void setAlarmCode(Integer alarmCode) {
		this.alarmCode = alarmCode;
	}
	public String getAlarmText() {
		return alarmText;
	}
	public void setAlarmText(String alarmText) {
		this.alarmText = alarmText;
	}
	public String getAlarmEnglish() {
		return alarmEnglish;
	}
	public void setAlarmEnglish(String alarmEnglish) {
		this.alarmEnglish = alarmEnglish;
	}
	public CMesAlarmCodeT() {
		super();
	}
	@Override
	public String toString() {
		return "CMesAlarmCodeT [id=" + id + ", alarmCode=" + alarmCode + ", alarmText=" + alarmText
				+ ", alarmEnglish=" + alarmEnglish + "]";
	}
}
