package com.skeqi.mes.pojo;

public class CMesXPTestData {
	private String serialnumber;//    NVARCHAR2(50),序列号
	private String item;//            NVARCHAR2(50),测试序号
	private String testcontent;//     NVARCHAR2(50),测试内容
	private String testconditional;// NVARCHAR2(50),测试条件
	private String unit;//            NVARCHAR2(50),单位
	private String realvalue;//       NVARCHAR2(50),实测值
	private String minvalue;//        NVARCHAR2(50),最小值
	private String maxvalue;//        NVARCHAR2(50),最大值
	private String testresult;//      NVARCHAR2(50),测试结果
	private String testitem;//        NVARCHAR2(50),测试项目
	private String archivestate;//    NUMBER 上传状态
	private String line;//所属产线
	private String nowdate;//当前时间
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getTestcontent() {
		return testcontent;
	}
	public void setTestcontent(String testcontent) {
		this.testcontent = testcontent;
	}
	public String getTestconditional() {
		return testconditional;
	}
	public void setTestconditional(String testconditional) {
		this.testconditional = testconditional;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRealvalue() {
		return realvalue;
	}
	public void setRealvalue(String realvalue) {
		this.realvalue = realvalue;
	}
	public String getMinvalue() {
		return minvalue;
	}
	public void setMinvalue(String minvalue) {
		this.minvalue = minvalue;
	}
	public String getMaxvalue() {
		return maxvalue;
	}
	public void setMaxvalue(String maxvalue) {
		this.maxvalue = maxvalue;
	}
	public String getTestresult() {
		return testresult;
	}
	public void setTestresult(String testresult) {
		this.testresult = testresult;
	}
	public String getTestitem() {
		return testitem;
	}
	public void setTestitem(String testitem) {
		this.testitem = testitem;
	}
	public String getArchivestate() {
		return archivestate;
	}
	public void setArchivestate(String archivestate) {
		this.archivestate = archivestate;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}

	public String getNowdate() {
		return nowdate;
	}
	public void setNowdate(String nowdate) {
		this.nowdate = nowdate;
	}
	public CMesXPTestData(String serialnumber, String item, String testcontent, String testconditional, String unit,
			String realvalue, String minvalue, String maxvalue, String testresult, String testitem,
			String archivestate, String line) {
		super();
		this.serialnumber = serialnumber;
		this.item = item;
		this.testcontent = testcontent;
		this.testconditional = testconditional;
		this.unit = unit;
		this.realvalue = realvalue;
		this.minvalue = minvalue;
		this.maxvalue = maxvalue;
		this.testresult = testresult;
		this.testitem = testitem;
		this.archivestate = archivestate;
		this.line = line;
	}
	public CMesXPTestData() {
		super();
	}
	@Override
	public String toString() {
		return "CMesXPTestData [serialnumber=" + serialnumber + ", item=" + item + ", testcontent=" + testcontent
				+ ", testconditional=" + testconditional + ", unit=" + unit + ", realvalue=" + realvalue + ", minvalue="
				+ minvalue + ", maxvalue=" + maxvalue + ", testresult=" + testresult + ", testitem=" + testitem
				+ ", archivestate=" + archivestate + ", line=" + line + "]";
	}



}
