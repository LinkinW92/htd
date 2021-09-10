package com.skeqi.mes.mapper.yp.oa;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 表单权限
 *
 * @author yinp
 * @data 2021年6月10日
 *
 */
public interface FormPermissionsDao {

	/**
	 * 查询公司
	 * @return
	 */
	public List<JSONObject> findCompany();

	/**
	 * 查询角色
	 * @return
	 */
	public List<JSONObject> findRole();

	/**
	 * 查询部门
	 * @param companyId
	 * @return
	 */
	public List<JSONObject> findDepartment(@Param("companyId")Integer companyId,@Param("departmentId")String departmentId);

	/**
	 * 查询用户
	 * @param companyId
	 * @return
	 */
	public List<JSONObject> findUser(@Param("departmentId")int departmentId);

	/**
	 * 提交修改
	 * @param id
	 * @param selectData
	 * @return
	 */
	public int submitUpdate(@Param("id")int id,@Param("selectData")String selectData);

}
