package com.skeqi.mes.service.yp.qh.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.CorpReportListResponse.JsonObject;
import com.skeqi.mes.mapper.yp.qh.ExternalContactsDao;
import com.skeqi.mes.service.yp.qh.ExternalContactsService;

/**
 * 外部联系人
 *
 * @author yinp
 * @data 2021年6月8日
 */
@Service
public class ExternalContactsServiceImpl implements ExternalContactsService {

	@Autowired
	ExternalContactsDao dao;

	// 查询
	@Override
	public List<JSONObject> list(JSONObject json) {
		String selected = json.getString("selectedList");

		List<JSONObject> selectedList = JSONObject.parseArray(selected, JSONObject.class);
		StringBuffer sqlBuffer = new StringBuffer();

		for (JSONObject jsonObject : selectedList) {
			sqlBuffer.append("and tag like '%" + jsonObject.getString("name") + "%' ");
		}

		json.put("selected", sqlBuffer.toString());

		return dao.list(json);
	}

	// 新增
	@Override
	public void add(JSONObject json) throws Exception {
		if (dao.add(json) != 1) {
			throw new Exception("新增失败");
		}
	}

	// 更新
	@Override
	public void update(JSONObject json) throws Exception {
		if (dao.update(json) != 1) {
			throw new Exception("更新失败");
		}
	}

	// 删除
	@Override
	public void delete(int id) throws Exception {
		if (dao.delete(id) != 1) {
			throw new Exception("删除失败");
		}
	}

	// 查询所有标签分组
	@Override
	public List<JSONObject> findLabel() {
		List<JSONObject> list = dao.findLabel(null);
		return list;
	}

	// 查询所有部门
	@Override
	public List<JSONObject> findDepartmentAll() {
		return dao.findDepartmentAll();
	}

	// 通过部门id查询用户
	@Override
	public List<JSONObject> findUserByDepartmentId(int departmentId) {
		return dao.findUserByDepartmentId(departmentId);
	}

}
