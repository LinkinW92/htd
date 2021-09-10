package com.skeqi.mes.pojo;

/**
 * 数据报表显示列
 * @author SKQ
 *
 */
public class CMesReportColumn {

	private Integer id;	// 主键
	private String tableColumnsName;	// 数据库表列名
	private String showColumnsName;	// 报表列显示名称
	private Integer tableReportId;	// 数据库显示表名主键
	private Integer showFlag;	// 是否显示标记

	public CMesReportColumn() {
		super();
	}

	public CMesReportColumn(Integer id, String tableColumnsName, String showColumnsName, Integer tableReportId,
			Integer showFlag) {
		super();
		this.id = id;
		this.tableColumnsName = tableColumnsName;
		this.showColumnsName = showColumnsName;
		this.tableReportId = tableReportId;
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
	public Integer getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(Integer showFlag) {
		this.showFlag = showFlag;
	}

	@Override
	public String toString() {
		return "CMesReportColumn [id=" + id + ", tableColumnsName=" + tableColumnsName + ", showColumnsName="
				+ showColumnsName + ", tableReportId=" + tableReportId + ", showFlag=" + showFlag + "]";
	}


}
