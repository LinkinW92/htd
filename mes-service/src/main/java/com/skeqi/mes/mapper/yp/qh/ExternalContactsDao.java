package com.skeqi.mes.mapper.yp.qh;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 外部联系人
 *
 * @author yinp
 * @data 2021年6月8日
 */
public interface ExternalContactsDao {

	/**
	 * 查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 新增
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 更新
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(@Param("id")int id);

	/**
	 * 查询标签
	 *
	 * @参数1 name
	 * @参数2 level
	 * @参数3 superiorId
	 * @return
	 */
	public List<JSONObject> findLabel(JSONObject json);

	/**
	 * 查询所有部门
	 * @return
	 */
	public List<JSONObject> findDepartmentAll();

	/**
	 * 通过部门id查询用户
	 * @return
	 */
	public List<JSONObject> findUserByDepartmentId(@Param("departmentId")int departmentId);

}
