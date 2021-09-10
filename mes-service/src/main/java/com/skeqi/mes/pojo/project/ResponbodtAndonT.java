package com.skeqi.mes.pojo.project;

/**
 * 响应安灯
 * @author : FQZ
 * @Package: com.skeqi.mes.pojo.project
 * @date   : 2020年4月20日 下午1:25:35
 */
public class ResponbodtAndonT extends CMesAndonFaultT{

	private String endDate;
	private String startDate;
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


}
