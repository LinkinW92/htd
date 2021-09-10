package com.skeqi.mes.mapper.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 角色权限
 * @date 2020-9-3
 */
public interface CQhRoleJurisdictionDao {

	/**
	 * @author查询所有权限
	 * @return
	 */
	public List<JSONObject> findJurisdictionList();

	/**
	 * @author 查询角色权限
	 * @param roleId
	 * @return
	 */
	public List<JSONObject> findRoleJurisdiction(@Param("roleId")Integer roleId);

	/**
	 * @author 新增角色权限
	 * @return
	 */
	public Integer addRoleJurisdiction(@Param("sql")String sql);

	/**
	 * @author 删除角色权限
	 * @param roleId
	 * @return
	 */
	public Integer deleteRoleJurisdiction(@Param("roleId")Integer roleId);

}
