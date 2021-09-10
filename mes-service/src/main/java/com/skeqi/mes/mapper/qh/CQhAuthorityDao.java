package com.skeqi.mes.mapper.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.qh.CQhRoleT;

/**
 * @author yinp
 * @explain 琦航权限
 * @date 2020-9-3
 */
@Repository("cQhAuthorityDao")
public interface CQhAuthorityDao {

	/**
	 * @explain 通过token查询角色
	 * @param userId
	 * @return
	 */
	public CQhRoleT queryRoleByToken(@Param("token")String token);

	/**
	 * @explain 按角色id和路径查询是否有权限
	 * @param roleId 角色id
	 * @param path 路径
	 * @return 有权限返回数将大于0  没有权限返回数将等于0
	 */
	public Integer queryPermissionByRoleIdAndPath(@Param("roleId")Integer roleId,@Param("path")String path);

	/**
	 * @explain 通过角色id查询角色权限
	 * @param roleId
	 * @return
	 */
	public List<JSONObject> findAuthorityInterfaceByRoleId(@Param("roleId")Integer roleId);

	/**
	 * @explain 通过等级查询菜单
	 * @param grade
	 * @return
	 */
	public List<JSONObject> findMenuByGrade(@Param("grade")Integer grade);

}
