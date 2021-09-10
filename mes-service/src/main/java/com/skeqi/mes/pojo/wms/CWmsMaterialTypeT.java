package com.skeqi.mes.pojo.wms;

import java.util.Date;

/**
 * 物料类型表
 * @author FQZ
 *
 */
public class CWmsMaterialTypeT {
	private Integer id;
	private Date modifyTime;  //修改时间
	private String mtNo;  //类别编号
	private String mtName;  //类别名称
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
	public String getMtNo() {
		return mtNo;
	}
	public void setMtNo(String mtNo) {
		this.mtNo = mtNo;
	}
	public String getMtName() {
		return mtName;
	}
	public void setMtName(String mtName) {
		this.mtName = mtName;
	}
	@Override
	public String toString() {
		return "CWmsMaterialTypeT [id=" + id + ", modifyTime=" + modifyTime + ", mtNo=" + mtNo + ", mtName=" + mtName
				+ "]";
	}

}
