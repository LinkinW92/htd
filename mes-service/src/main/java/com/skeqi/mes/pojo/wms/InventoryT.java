package com.skeqi.mes.pojo.wms;

import com.skeqi.mes.pojo.CMesUserT;

/**
 * @date 2020年2月24日
 * @author yinp 库存盘点
 */
public class InventoryT {
	private Integer id;// 主键
	private String listNo;// 盘点单据号
	private String dt;// 日期
	private Integer userId;// 开单用户
	private String dis;// 描述
	private Integer state;// 盘点状态1.生效、0.未生效
	private CWmsApprovalT approval;//审批记录
	private CMesUserT user;//用户

	public CWmsApprovalT getApproval() {
		return approval;
	}

	public void setApproval(CWmsApprovalT approval) {
		this.approval = approval;
	}

	public CMesUserT getUser() {
		return user;
	}

	public void setUser(CMesUserT user) {
		this.user = user;
	}

	public InventoryT() {
		// TODO Auto-generated constructor stub
	}

	public InventoryT(Integer id, String listNo, String dt, Integer userId, String dis, Integer state) {
		this.id = id;
		this.listNo = listNo;
		this.dt = dt;
		this.userId = userId;
		this.dis = dis;
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getListNo() {
		return listNo;
	}

	public void setListNo(String listNo) {
		this.listNo = listNo;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDis() {
		return dis;
	}

	public void setDis(String dis) {
		this.dis = dis;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "InventoryT [id=" + id + ", listNo=" + listNo + ", dt=" + dt + ", userId=" + userId + ", dis=" + dis
				+ ", state=" + state + "]";
	}

}
