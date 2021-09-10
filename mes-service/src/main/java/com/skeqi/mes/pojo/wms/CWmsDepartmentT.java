package com.skeqi.mes.pojo.wms;

import java.util.List;

import com.skeqi.mes.pojo.CMesUserT;

/**
 * 部门
 * @author yinp
 *
 */
public class CWmsDepartmentT {

	private Integer id;
	//部门编号
	private String dmNumber;
	//部门名称
	private String dmName;
	//备注
	private String note;
	//修改时间
	private String modifyTime;
	//显示状态
	private Integer viewMode;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDmNumber() {
		return dmNumber;
	}
	public void setDmNumber(String dmNumber) {
		this.dmNumber = dmNumber;
	}
	public String getDmName() {
		return dmName;
	}
	public void setDmName(String dmName) {
		this.dmName = dmName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getViewMode() {
		return viewMode;
	}
	public void setViewMode(Integer viewMode) {
		this.viewMode = viewMode;
	}


}
