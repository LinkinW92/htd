package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 表单模板管理
 *
 * @author yinp
 * @data 2021年5月5日
 *
 */
public interface FormTemplateManagementService {

	/**
	 * 查询
	 *
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
	public void delete(int id,String name) throws Exception;

	/**
	 * 新增模板明细
	 *
	 * @param json
	 */
	public void addTemplateDetailed(JSONObject json) throws Exception;

	/**
	 * 查询模板
	 *
	 * @param id
	 * @return
	 */
	public JSONObject findTemplateById(int id);

	/**
	 * 新增表单模板表格
	 *
	 * @param json
	 */
	public void addFormTemplateTable(JSONObject json) throws Exception;

	/**
	 * 查询表单模板表格
	 *
	 * @param id
	 * @return
	 */
	public List<JSONObject> findFormTemplateTable(int formTemplateId,String group);

	/**
	 * 查询表单模板类型
	 * @return
	 */
	public List<JSONObject> findFormTemplateType();

	/**
	 * 查询组
	 * @param formTemplateId
	 * @return
	 */
	public List<JSONObject> findGroup(int formTemplateId);

	/**
	 * 删除组
	 * @param formTemplateId
	 * @param group
	 */
	public void deleteGroup(int formTemplateId,String group);

	/**
	 * 修改状态
	 * @param id
	 * @param state
	 */
	public void updateState(int id,String state)throws Exception;


}
