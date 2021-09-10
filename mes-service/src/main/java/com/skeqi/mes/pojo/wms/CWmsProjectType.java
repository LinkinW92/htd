package com.skeqi.mes.pojo.wms;
/**
 * 项目类型
 * @author Ryan
 * @date 2019年2月21日
 *
 */
public class CWmsProjectType {
	private Integer id;
	private String projectType;
	private String dis;
	private Integer viewMode;

	public Integer getViewMode() {
		return viewMode;
	}
	public void setViewMode(Integer viewMode) {
		this.viewMode = viewMode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getDis() {
		return dis;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}


}
