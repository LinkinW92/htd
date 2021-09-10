package com.skeqi.mes.pojo.wms;

/**
 * 物料条码规则属性表
 * @author Administrator
 *
 */
public class CWmsMaterialRuleAttributeT {

	private Integer id;
	private String attributeCn;//中文属性
	private String column;//属性列

	@Override
	public String toString() {
		return "CWmsMaterialRuleAttributeT [id=" + id + ", attributeCn=" + attributeCn + ", column=" + column + "]";
	}
	public CWmsMaterialRuleAttributeT() {
		// TODO Auto-generated constructor stub
	}
	public CWmsMaterialRuleAttributeT(Integer id, String attributeCn, String column) {
		this.id = id;
		this.attributeCn = attributeCn;
		this.column = column;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAttributeCn() {
		return attributeCn;
	}
	public void setAttributeCn(String attributeCn) {
		this.attributeCn = attributeCn;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}

}
