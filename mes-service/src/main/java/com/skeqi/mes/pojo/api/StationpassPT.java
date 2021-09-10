package com.skeqi.mes.pojo.api;

import java.io.Serializable;

/**
 * @2020年1月15日
 * @author yinp
 */
public class StationpassPT implements Serializable{

	private Integer id;
	private String locationStationSt;
	private String num1;
	private String num2;
	private String temp3;
	private String testNg;
	private String testAll;
	private String testEmpnum;
	private String locationDataSn;

	public String getLocationStationSt() {
		return locationStationSt;
	}
	public void setLocationStationSt(String locationStationSt) {
		this.locationStationSt = locationStationSt;
	}
	public String getLocationDataSn() {
		return locationDataSn;
	}
	public void setLocationDataSn(String locationDataSn) {
		this.locationDataSn = locationDataSn;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNum1() {
		return num1;
	}
	public void setNum1(String num1) {
		this.num1 = num1;
	}
	public String getNum2() {
		return num2;
	}
	public void setNum2(String num2) {
		this.num2 = num2;
	}
	public String getTemp3() {
		return temp3;
	}
	public void setTemp3(String temp3) {
		this.temp3 = temp3;
	}
	public String getTestNg() {
		return testNg;
	}
	public void setTestNg(String testNg) {
		this.testNg = testNg;
	}
	public String getTestAll() {
		return testAll;
	}
	public void setTestAll(String testAll) {
		this.testAll = testAll;
	}
	public String getTestEmpnum() {
		return testEmpnum;
	}
	public void setTestEmpnum(String testEmpnum) {
		this.testEmpnum = testEmpnum;
	}


}
