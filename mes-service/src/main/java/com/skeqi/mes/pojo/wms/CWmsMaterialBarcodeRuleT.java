package com.skeqi.mes.pojo.wms;

/**
 * 物料条码规则
 *
 * @author Administrator
 *
 */
public class CWmsMaterialBarcodeRuleT {

	private Integer id;
	private String rule;// 规则
	private String condition;// 条件
	private String modifyDt;// 修改时间

	public CWmsMaterialBarcodeRuleT() {
		// TODO Auto-generated constructor stub
	}

	public CWmsMaterialBarcodeRuleT(Integer id, String rule, String condition, String modifyDt) {
		this.id = id;
		this.rule = rule;
		this.condition = condition;
		this.modifyDt = modifyDt;
	}

	@Override
	public String toString() {
		return "CWmsMaterialBarcodeRuleT [id=" + id + ", rule=" + rule + ", condition=" + condition + ", modifyDt="
				+ modifyDt + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getModifyDt() {
		return modifyDt;
	}

	public void setModifyDt(String modifyDt) {
		this.modifyDt = modifyDt;
	}

}
