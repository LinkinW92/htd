package com.skeqi.mes.service.qh;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 琦航权限
 * @date 2020-10-8
 */
public interface CQhAuthorityServer
{

	/**
	 * @explain 通过角色id查询角色权限
	 * @param roleId
	 * @return
	 */
	public List<JSONObject> findAuthorityInterfaceByRoleId(Integer roleId);

}
