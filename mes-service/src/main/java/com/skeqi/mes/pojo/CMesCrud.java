package com.skeqi.mes.pojo;

import java.util.Arrays;

import io.swagger.annotations.ApiModelProperty;

public class CMesCrud {
	private Integer id;
	@ApiModelProperty(value = "角色ID", required = true)
	private Integer rid;// 角色ID
	@ApiModelProperty(value = "权限ID", required = false)
	private Integer cid;// CRUD权限ID
	@ApiModelProperty(value = "菜单ID", required = false)
	private Integer mid;
	@ApiModelProperty(value = "选中的数据", required = true)
	private String josns;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getJosns() {
		return josns;
	}

	public void setJosns(String josns) {
		this.josns = josns;
	}

	public CMesCrud(Integer id, Integer rid, Integer cid, Integer mid, String josns) {
		super();
		this.id = id;
		this.rid = rid;
		this.cid = cid;
		this.mid = mid;
		this.josns = josns;
	}

	public CMesCrud() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CMesCrud [id=" + id + ", rid=" + rid + ", cid=" + cid + ", mid=" + mid + ", josns=" + josns + "]";
	}


}
