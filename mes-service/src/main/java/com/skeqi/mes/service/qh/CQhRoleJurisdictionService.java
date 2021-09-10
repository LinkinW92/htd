package com.skeqi.mes.service.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 角色权限
 * @date 2020-9-3
 */
public interface CQhRoleJurisdictionService {

	/**
	 * 查询所有权限
	 *
	 * @return
	 */
	public List<JSONObject> findJurisdictionList();

	/**
	 * 查询角色权限
	 *
	 * @param roleId
	 * @return
	 */
	public List<JSONObject> findRoleJurisdiction(Integer roleId);

	/**
	 * @author 新增角色权限
	 * @return
	 */
	public Integer addRoleJurisdiction(Integer roleId,String []menu) throws Exception;

}
