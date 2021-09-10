package com.skeqi.mes.pojo;

public class RAsmEol {
	private Integer id;
	private String sn;
	private String dt;
	private Integer no;
	private String teststep;
	private String testname;
	private String value;
	private String judge;
	private String result;
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
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getTeststep() {
		return teststep;
	}
	public void setTeststep(String teststep) {
		this.teststep = teststep;
	}
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public RAsmEol(Integer id, String sn, String dt, Integer no, String teststep, String testname, String value,
			String judge, String result) {
		super();
		this.id = id;
		this.sn = sn;
		this.dt = dt;
		this.no = no;
		this.teststep = teststep;
		this.testname = testname;
		this.value = value;
		this.judge = judge;
		this.result = result;
	}
	public RAsmEol() {
		super();
	}
	@Override
	public String toString() {
		return "RAsmEol [id=" + id + ", sn=" + sn + ", dt=" + dt + ", no=" + no + ", teststep=" + teststep
				+ ", testname=" + testname + ", value=" + value + ", judge=" + judge + ", result=" + result + "]";
	}


}
