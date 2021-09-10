package com.skeqi.mes.pojo.wms;

import java.util.Date;

/**
 * 物料单位表
 * @author FQZ
 *
 */
public class CWmsMaterialUnitT {

	private Integer id;
	private Date modifyTime;  //修改时间
	private String unitNo;  //单位编号
	private String unitName;  //单位名称
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	@Override
	public String toString() {
		return "CWmsMaterialUnitT [id=" + id + ", modifyTime=" + modifyTime + ", unitNo=" + unitNo + ", unitName="
				+ unitName + "]";
	}

}
