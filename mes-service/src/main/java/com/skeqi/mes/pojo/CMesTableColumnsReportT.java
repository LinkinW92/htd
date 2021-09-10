package com.skeqi.mes.pojo;

public class CMesTableColumnsReportT {

	private Integer id;//主键  integer
	private String tableColumnsName;//数据库表列明 varchar  100
	private String showColumnsName;//报表列显示名称 varchar 100
	private Integer tableReportId;//数据库显示表名主键 integer
	private Integer showFlag;//页面是否显示该列
	public Integer getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(Integer showFlag) {
		this.showFlag = showFlag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableColumnsName() {
		return tableColumnsName;
	}
	public void setTableColumnsName(String tableColumnsName) {
		this.tableColumnsName = tableColumnsName;
	}
	public String getShowColumnsName() {
		return showColumnsName;
	}
	public void setShowColumnsName(String showColumnsName) {
		this.showColumnsName = showColumnsName;
	}
	public Integer getTableReportId() {
		return tableReportId;
	}
	public void setTableReportId(Integer tableReportId) {
		this.tableReportId = tableReportId;
	}




}
