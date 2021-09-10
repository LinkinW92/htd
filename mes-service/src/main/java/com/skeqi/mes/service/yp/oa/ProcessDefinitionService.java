package com.skeqi.mes.service.yp.oa;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 流程定义
 *
 * @author yinp
 * @data 2021年5月10日
 */
public interface ProcessDefinitionService {

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
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 查询表单模板
	 *
	 * @return
	 */
	public List<JSONObject> queryFormTemplate();

	/**
	 * 查询用户信息
	 *
	 * @return
	 */
	public List<JSONObject> queryUser();

	/**
	 * 查询模板明细
	 *
	 * @param id
	 * @return
	 */
	public List<JSONObject> findTemplateDetailed(int id);

	/**
	 * 查询流程xml
	 *
	 * @param key
	 * @return
	 */
	public JSONObject inquiryProcessXML(String key);

	/**
	 * 修改流程
	 *
	 * @param json
	 */
	public void updateProcess(JSONObject json) throws Exception;

	/**
	 * 查询部门
	 *
	 * @return
	 */
	public List<JSONObject> findDepartment();

	/**
	 * 查询职位
	 *
	 * @return
	 */
	public List<JSONObject> findPositionid();

	/**
	 * 删除流程
	 *
	 * @param key
	 * @throws Exception
	 */
	public void delete(String key, String name) throws Exception;


	public void asd(JSONObject json);

}
