package com.skeqi.mes.pojo.api;

import java.io.Serializable;

/**
 * @date 2020年1月11日
 * @author yinp
 *
 */
public class UpdatecurrentempPT implements Serializable{

	private Integer id;
	private String tempSteprecordCount;

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

}
