package com.skeqi.mes.pojo.api;

import java.io.Serializable;

/**
 *
 * @author yinp
 * @date 2020年1月11日
 */
public class GetcurrentempPT implements Serializable{

	private Integer id;
	private String tempSteprecordCount;
	private String tmepEmpName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTempSteprecordCount() {
		return tempSteprecordCount;
	}
	public void setTempSteprecordCount(String tempSteprecordCount) {
		this.tempSteprecordCount = tempSteprecordCount;
	}
	public String getTmepEmpName() {
		return tmepEmpName;
	}
	public void setTmepEmpName(String tmepEmpName) {
		this.tmepEmpName = tmepEmpName;
	}

}
