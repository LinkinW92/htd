package com.skeqi.mes.pojo.api;

import java.io.Serializable;

/**
 * @date 2020年1月11日
 * @author yinp
 *
 */
public class InitializeCurrentstepPT implements Serializable{
	private Integer id;
	private String tempSteprecordCount;
	private String maxid;
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
	public String getMaxid() {
		return maxid;
	}
	public void setMaxid(String maxid) {
		this.maxid = maxid;
	}

}
