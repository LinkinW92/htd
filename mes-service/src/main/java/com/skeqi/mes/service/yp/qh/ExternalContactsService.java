package com.skeqi.mes.service.yp.qh;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 外部联系人
 *
 * @author yinp
 * @data 2021年6月8日
 */
public interface ExternalContactsService {

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

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
	 * 查询所有标签
	 *
	 * @return
	 */
	public List<JSONObject> findLabel();

	/**
	 * 查询所有部门
	 * @return
	 */
	public List<JSONObject> findDepartmentAll();

	/**
	 * 通过部门id查询用户
	 * @return
	 */
	public List<JSONObject> findUserByDepartmentId(int departmentId);

}
