package com.skeqi.mes.service.yp.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.FormTemplateManagementDao;
import com.skeqi.mes.service.yp.oa.FormTemplateManagementService;
import com.skeqi.mes.util.yp.TokenUtil;

/**
 * 表单模板管理
 *
 * @author yinp
 * @data 2021年5月5日
 *
 */
@Service
public class FormTemplateManagementServiceImpl implements FormTemplateManagementService {

	@Autowired
	FormTemplateManagementDao dao;

	/**
	 * 查询
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> list() {
		return dao.list();
	}

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		String name = json.getString("name");
		String code = json.getString("code");
		int typeId = json.getInteger("typeId");

		// 查询当前输入的名称是否存在
		if (dao.queryWhetherTheCurrentEnteredNameExists(name, typeId, null) > 0) {
			throw new Exception("模板名称已存在");
		}

		if(dao.findCodeConut(code, null)>0) {
			throw new Exception("编号已存在");
		}

		// 新增
		if (dao.add(json) != 1) {
			throw new Exception("新增失败");
		}

	}

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		Integer id = json.getInteger("id");
		String name = json.getString("name");
		String code = json.getString("code");
		String oldName = json.getString("oldName");
		int typeId = json.getInteger("typeId");

		// 查询当前输入的名称是否存在
		if (dao.queryWhetherTheCurrentEnteredNameExists(name, typeId, id) > 0) {
			throw new Exception("模板名称已存在");
		}

		if(dao.findCodeConut(code, id)>0) {
			throw new Exception("编号已存在");
		}

		// 更新
		if (dao.update(json) != 1) {
			throw new Exception("更新失败");
		}

		//删除草稿
		dao.deleteDraft(id);
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@Override
	public void delete(int id, String name) throws Exception {
		// 删除
		if (dao.delete(id) != 1) {
			throw new Exception("删除失败");
		}

	}

	/**
	 * 新增模板明细
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void addTemplateDetailed(JSONObject json) throws Exception {
		int id = json.getInteger("id");

		// 通过表单模板id删除表单模板明细
		dao.deleteTemplateDetailed(id);

		List<JSONObject> templateDetailedList = JSON.parseArray(json.getString("templateDetailed"), JSONObject.class);
		for (JSONObject templateDetailed : templateDetailedList) {
			if (templateDetailed.getString("key") == null || templateDetailed.getString("type") == null
					|| templateDetailed.getString("check") == null || templateDetailed.getString("width") == null) {
				throw new Exception("数据不完整");
			}

			if (templateDetailed.getString("key").equals("") || templateDetailed.getString("type").equals("")
					|| templateDetailed.getString("check").equals("")
					|| templateDetailed.getString("width").equals("")) {
				throw new Exception("数据不完整");
			}

			templateDetailed.put("formTemplateId", id);

			templateDetailed.remove("parameter");
			templateDetailed.put("parameter", templateDetailed.toString());

			// 新增表单模板明细
			dao.addTemplateDetailed(templateDetailed);

		}
	}

	/**
	 * 查询模板
	 *
	 * @param id
	 * @return
	 */
	@Override
	public JSONObject findTemplateById(int id) {
		return dao.findTemplateById(id);
	}

	/**
	 * 新增表单模板表格
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void addFormTemplateTable(JSONObject json) throws Exception {
		int formTemplateId = json.getInteger("formTemplateId");
		String groupName = json.getString("groupName");
		String group = json.getString("group");

		if (group.equals("")) {
			group = TokenUtil.randomToken(20) + System.currentTimeMillis();
		} else {
			// 通过表单模板id删除表单模板表格
			dao.deleteTemplateTable(formTemplateId, group);
		}

		List<JSONObject> templateTableList = JSON.parseArray(json.getString("templateTable"), JSONObject.class);
		for (JSONObject templateTable : templateTableList) {
			if (templateTable.getString("key") == null || templateTable.getString("type") == null) {
				throw new Exception("数据不完整");
			}

			if (templateTable.getString("key").equals("") || templateTable.getString("type").equals("")) {
				throw new Exception("数据不完整");
			}

			templateTable.put("formTemplateId", formTemplateId);
			templateTable.put("groupName", groupName);
			templateTable.put("group", group);

			// 新增表单模板表格
			dao.addTemplateTable(templateTable);

		}

	}

	/**
	 * 查询表单模板表格
	 *
	 * @param id
	 * @return
	 */
	@Override
	public List<JSONObject> findFormTemplateTable(int formTemplateId, String group) {
		return dao.findFormTemplateTable(formTemplateId, group);
	}

	/**
	 * 查询表单模板类型
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findFormTemplateType() {
		return dao.findFormTemplateType();
	}

	/**
	 * 查询组
	 *
	 * @param formTemplateId
	 * @return
	 */
	@Override
	public List<JSONObject> findGroup(int formTemplateId) {
		return dao.findGroup(formTemplateId);
	}

	/**
	 * 删除组
	 *
	 * @param formTemplateId
	 * @param group
	 */
	@Override
	public void deleteGroup(int formTemplateId, String group) {
		dao.deleteGroup(formTemplateId, group);
	}

	/**
	 * 修改状态
	 *
	 * @param id
	 * @param state
	 * @throws Exception
	 */
	@Override
	public void updateState(int id, String state) throws Exception {
		if (dao.updateState(id, state) != 1) {
			throw new Exception(state + "失败");
		}
	}

}
