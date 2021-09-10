package com.skeqi.mes.pojo;

public class CMesXPTestInfo {
	private String serialnumber;// NVARCHAR2(50),序列号
	private String stepname;//     NVARCHAR2(50),测试项目名称
	private String deviceinfo;//   NVARCHAR2(50),设备信息
	private String testresult;//   NVARCHAR2(50),测试结果
	private String operators;//     NVARCHAR2(50),操作员
	private String barcode;//      NVARCHAR2(50),条码
	private String archivestate;// NUMBER，上传状态
	private String line;
	private String testdate;


	public String getTestdate() {
		return testdate;
	}
	public void setTestdate(String testdate) {
		this.testdate = testdate;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getStepname() {
		return stepname;
	}
	public void setStepname(String stepname) {
		this.stepname = stepname;
	}
	public String getDeviceinfo() {
		return deviceinfo;
	}
	public void setDeviceinfo(String deviceinfo) {
		this.deviceinfo = deviceinfo;
	}
	public String getTestresult() {
		return testresult;
	}
	public void setTestresult(String testresult) {
		this.testresult = testresult;
	}

	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getArchivestate() {
		return archivestate;
	}
	public void setArchivestate(String archivestate) {
		this.archivestate = archivestate;
	}
	public String getOperators() {
		return operators;
	}
	public void setOperators(String operators) {
		this.operators = operators;
	}
	public CMesXPTestInfo(String serialnumber, String stepname, String deviceinfo, String testresult, String operators,
			String barcode, String archivestate, String line, String testdate) {
		super();
		this.serialnumber = serialnumber;
		this.stepname = stepname;
		this.deviceinfo = deviceinfo;
		this.testresult = testresult;
		this.operators = operators;
		this.barcode = barcode;
		this.archivestate = archivestate;
		this.line = line;
		this.testdate = testdate;
	}
	public CMesXPTestInfo() {
		super();
	}
	@Override
	public String toString() {
		return "CMesXPTestInfo [serialnumber=" + serialnumber + ", stepname=" + stepname + ", deviceinfo=" + deviceinfo
				+ ", testresult=" + testresult + ", operators=" + operators + ", barcode=" + barcode + ", archivestate="
				+ archivestate + ", line=" + line + ", testdate=" + testdate + "]";
	}


}
