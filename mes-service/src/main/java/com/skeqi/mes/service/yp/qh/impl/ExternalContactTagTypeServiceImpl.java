package com.skeqi.mes.service.yp.qh.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.qh.ExternalContactTagTypeDao;
import com.skeqi.mes.service.yp.qh.ExternalContactTagTypeService;

/**
 * 外部联系人标签类型
 *
 * @author yinp
 * @data 2021年6月8日
 */
@Service
public class ExternalContactTagTypeServiceImpl implements ExternalContactTagTypeService {

	@Autowired
	ExternalContactTagTypeDao dao;

	// 查询
	@Override
	public List<JSONObject> list() {
		return dao.list();
	}

	// 新增
	@Override
	public void add(JSONObject json) throws Exception {
		String name = json.getString("name");
		if (dao.findTagCountByName(name, null) > 0) {
			throw new Exception("标签类型名称已存在");
		}
		if (dao.add(json) != 1) {
			throw new Exception("新增失败");
		}
	}

	// 更新
	@Override
	public void update(JSONObject json) throws Exception {
		Integer id = json.getInteger("id");
		String name = json.getString("name");
		if (dao.findTagCountByName(name, id) > 0) {
			throw new Exception("标签类型名称已存在");
		}
		if (dao.update(json) != 1) {
			throw new Exception("更新失败");
		}
	}

	// 删除
	@Override
	public void delete(int id) throws Exception {
		if (dao.findExternalContactTagByTypeId(id) > 0) {
			throw new Exception("该标签类型下存在以绑定的标签");
		}
		if (dao.delete(id) != 1) {
			throw new Exception("删除失败");
		}
	}

}
