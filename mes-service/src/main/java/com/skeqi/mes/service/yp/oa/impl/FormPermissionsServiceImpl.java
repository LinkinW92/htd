package com.skeqi.mes.service.yp.oa.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.FormPermissionsDao;
import com.skeqi.mes.service.yp.oa.FormPermissionsService;

/**
 * 表单权限
 *
 * @author yinp
 * @data 2021年6月10日
 *
 */
@Service
public class FormPermissionsServiceImpl implements FormPermissionsService {

	@Autowired
	FormPermissionsDao dao;

	// 查询公司
	@Override
	public List<JSONObject> findCompany() {
		List<JSONObject> list = dao.findCompany();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("type", "公司");
		}
		return list;
	}

	// 查询角色
	@Override
	public List<JSONObject> findRole() {
		List<JSONObject> list = dao.findRole();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("type", "角色");
		}
		return list;
	}

	// 查询下级
	@Override
	public List<JSONObject> findSubordinate(Integer companyId, Integer departmentId) {
		List<JSONObject> list = new ArrayList<JSONObject>();

		if (companyId != null) {
			// 查询部门
			List<JSONObject> list1 = dao.findDepartment(companyId, departmentId.toString());
			for (JSONObject jsonObject : list1) {
				jsonObject.put("type", "部门");
				list.add(jsonObject);
			}
		}
		if (departmentId != null) {
			// 查询部门
			List<JSONObject> list1 = dao.findDepartment(companyId, departmentId.toString());
			for (JSONObject jsonObject : list1) {
				jsonObject.put("type", "部门");
				list.add(jsonObject);
			}

			// 查询用户
			List<JSONObject> list2 = dao.findUser(departmentId);
			for (JSONObject jsonObject : list2) {
				jsonObject.put("type", "用户");
				list.add(jsonObject);
			}
		}
		return list;
	}

	// 提交修改
	@Override
	public void submitUpdate(int id, String selectData) throws Exception {
		if (dao.submitUpdate(id, selectData) != 1) {
			throw new Exception("修改失败");
		}
	}

}
