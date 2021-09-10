package com.skeqi.mes.pojo.wms;

import java.io.Serializable;

/**
 *
 * 单据类型
 * @author Ryan
 *
 */
public class ProcessType  implements Serializable{
	private Integer id;
	private String processType;
	private String dis;
	private Integer viewMode;

	public Integer getViewMode() {
		return viewMode;
	}
	public void setViewMode(Integer viewMode) {
		this.viewMode = viewMode;
	}
	public ProcessType() {
		// TODO Auto-generated constructor stub
	}
	public ProcessType(Integer id, String processType, String dis) {
		this.id = id;
		this.processType = processType;
		this.dis = dis;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	@Override
	public String toString() {
		return "ProcessType [id=" + id + ", processType=" + processType + ", dis=" + dis + "]";
	}



}
