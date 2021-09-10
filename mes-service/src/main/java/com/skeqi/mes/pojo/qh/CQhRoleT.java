package com.skeqi.mes.pojo.qh;

/**
 *
 * @author yinp
 * @explain 角色
 * @date 2020-9-3
 */
public class CQhRoleT {

	private Integer id;
	// 角色名称
	private String roleName;
	// 描述
	private String describe;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

}
