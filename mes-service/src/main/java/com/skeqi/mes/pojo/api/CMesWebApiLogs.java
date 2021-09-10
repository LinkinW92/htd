package com.skeqi.mes.pojo.api;

import java.io.Serializable;

/**
 * @date 2020年1月16日
 * @author yinp
 *
 */
public class CMesWebApiLogs implements Serializable{
	private static final long serialVersionUID = 735655488285535299L;

	private Integer id;
	private String apiName;
	private String callTime;
	private String parameter;
	private String returnResult;
	private String returnTime;
	private String sn;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public String getCallTime() {
		return callTime;
	}
	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getReturnResult() {
		return returnResult;
	}
	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}


}
