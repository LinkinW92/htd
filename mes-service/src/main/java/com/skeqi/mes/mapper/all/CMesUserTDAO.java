package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesUserT;

public interface CMesUserTDAO {

	public List<CMesUserT> findAllUser(CMesUserT user);

	public CMesUserT findByidUser(Integer id);

	public Integer addUser(CMesUserT user);

	public Integer updateUser(CMesUserT user);

	public Integer delUser(Integer id);

	public Integer findMaxNumber();

	public void updatePwd(@Param("pwd")String pwd,@Param("id") Integer id);

	public List<CMesUserT> findUserByName(CMesUserT user);

	//查询所有部门
	public List<JSONObject> findDepartment();

	/**
	 * 通过用户id查询所在部门用户
	 * @param userId
	 * @return
	 */
	public List<JSONObject> QueryUserSDepartmentUserByuserId(@Param("userId")Integer userId);

	/**
	 * 查询用户直属主管
	 * @param userId
	 * @return
	 */
	public JSONObject findReportsTo(@Param("userId")Integer userId);

	/**
	 * 更新直属主管
	 * @param userId
	 * @param reportsToId
	 * @return
	 */
	public int updateReportsTo(@Param("userId")Integer userId,@Param("reportsToId")Integer reportsToId);
}
