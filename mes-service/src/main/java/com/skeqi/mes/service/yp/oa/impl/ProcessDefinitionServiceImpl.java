package com.skeqi.mes.service.yp.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.ProcessDefinitionDao;
import com.skeqi.mes.service.yp.oa.ProcessDefinitionService;
import com.skeqi.mes.util.yp.TokenUtil;

/**
 * 流程定义
 *
 * @author yinp
 * @data 2021年5月10日
 */
@Service
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {

	@Autowired
	ProcessDefinitionDao dao;

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> list(JSONObject json) {
		return dao.list(json);
	}

	/**
	 * 新增
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void add(JSONObject json) throws Exception {

	}

	/**
	 * 查询表单模板
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> queryFormTemplate() {
		return dao.queryFormTemplate();
	}

	/**
	 * 查询用户信息
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> queryUser() {
		return dao.queryUser();
	}

	/**
	 * 查询模板明细
	 *
	 * @param id
	 * @return
	 */
	@Override
	public List<JSONObject> findTemplateDetailed(int id) {
		return dao.findTemplateDetailed(id);
	}

	/**
	 * 查询流程xml
	 *
	 * @param key
	 * @return
	 */
	@Override
	public JSONObject inquiryProcessXML(String key) {

		return dao.inquiryProcessXML(key);
	}

	/**
	 * 修改流程
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void updateProcess(JSONObject json) throws Exception {

	}

	/**
	 * 查询部门
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findDepartment() {
		return dao.findDepartment();
	}

	/**
	 * 查询职位
	 */
	@Override
	public List<JSONObject> findPositionid() {
		return dao.findPositionid();
	}

	/**
	 * 删除流程
	 *
	 * @param key
	 * @throws Exception
	 */
	@Override
	public void delete(String key, String name) throws Exception {

	}

	@Override
	public void asd(JSONObject json) {
		String name = json.getString("name");
		String key = json.getString("key");
		String typeName = json.getString("typeName");
		int templateId = json.getInteger("templateId");

		// 查询菜单信息
		JSONObject menu = dao.findMenuByName(typeName);
		// 新增菜单
		JSONObject menuJson = new JSONObject();
		menuJson.put("menuGrade", menu.getInteger("menuGrade") + 1);
		menuJson.put("superiorMenuId", menu.getInteger("id"));
		menuJson.put("menuName", name);
		menuJson.put("ifEnable", "true");

		String path = "InitiateApplication/singlePage";
		path = path + "?name=" + name;
		path = path + "&key=" + key;
		path = path + "&formTemplateId=" + templateId;

		menuJson.put("path", path);
		menuJson.put("icon", "more");
		menuJson.put("order", 2);
		dao.addMenu(menuJson);

	}

}
