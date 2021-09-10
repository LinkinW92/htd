package com.skeqi.mes.pojo;

public class CMesTableReportT {
	private Integer id;//主键   integer
	private String tableName;//数据库表名  varchar 100
	private String showName;//报表显示名称 varchar 100
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}



}
