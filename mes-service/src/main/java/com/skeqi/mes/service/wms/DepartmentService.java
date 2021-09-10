package com.skeqi.mes.service.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 部门管理
 *
 * @author yinp
 *
 */
public interface DepartmentService {

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
	public void add(JSONObject json) throws Exception;

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	public void delete(int id) throws Exception;

	/**
	 * 查询部门
	 *
	 * @return
	 */
	public List<JSONObject> findDepartmentBySuperiorId(Integer superiorId);

	/**
	 * 通过部门ID查询用户
	 *
	 * @param departmentId
	 * @return
	 */
	public List<JSONObject> findUserByDepartmentId(int departmentId);

	/**
	 * 批量导入用户
	 *
	 * @throws Exception
	 */
	public void batchImportUsers(Integer departmentId, String userList) throws Exception;

	/**
	 * 新增用户
	 *
	 * @param json
	 * @return
	 */
	public void addUser(JSONObject json) throws Exception;

	/**
	 * 更新用户
	 *
	 * @param json
	 * @return
	 */
	public void updateUser(JSONObject json) throws Exception;

	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	public void deleteUser(Integer userId);

}
