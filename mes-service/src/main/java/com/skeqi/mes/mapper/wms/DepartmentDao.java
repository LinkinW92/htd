package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @date 2020年3月12日
 * @author yinp 部门
 */
public interface DepartmentDao {

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list();

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	public int delete(@Param("id") int id);

	/**
	 * 查询部门名称存在得数量
	 *
	 * @param name
	 * @param id
	 * @return
	 */
	public int findCountName(@Param("name") String name, @Param("superiorId") int superiorId, @Param("id") Integer id);

	/**
	 * 通过id查询部门
	 *
	 * @return
	 */
	public JSONObject findDepartmentById(@Param("id") int id);

	/**
	 * 通过部门id查询有多少用户
	 *
	 * @param departmentId
	 * @return
	 */
	public int findUserCountByDepartmentId(@Param("departmentId") int departmentId);

	/**
	 * 通过部门id查询有多少下级部门
	 *
	 * @param superiorId
	 * @return
	 */
	public int findDepartmentCountBySuperiorId(@Param("superiorId") int superiorId);

	/**
	 * 通过部门ID查询用户
	 * @param departmentId
	 * @return
	 */
	public List<JSONObject> findUserByDepartmentId(@Param("departmentId")int departmentId);

	/**
	 * 新增部门主管
	 * @param json
	 * @return
	 */
	public int addHeadOfDepartment(JSONObject json);

	/**
	 * 删除部门主管
	 * @param departmentId
	 * @return
	 */
	public int deleteHeadOfDepartment(@Param("departmentId")Integer departmentId);

	/**
	 * 通过上级部门id查询部门
	 * @param superiorId
	 * @return
	 */
	public List<JSONObject> findDepartmentBySuperiorId(Integer superiorId);

	/**
	 * 通过用户名查询用户
	 * @param userId
	 * @return
	 */
	public JSONObject findUserByUserName(@Param("userName")String userName);

	/**
	 * 新增用户
	 * @param json
	 * @return
	 */
	public int addUser(JSONObject json);

	/**
	 * 更新用户
	 * @param json
	 * @return
	 */
	public int updateUser(JSONObject json);

	/**
	 * 查询所有角色
	 * @return
	 */
	public List<JSONObject> findRoleAll();

	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	public int deleteUser(@Param("userId")Integer userId);

	/**
	 * 查询所有职级
	 * @return
	 */
	public List<JSONObject> findRankAll();

	/**
	 * 新增职级
	 * @param json
	 * @return
	 */
	public int addRank(JSONObject json);
}
