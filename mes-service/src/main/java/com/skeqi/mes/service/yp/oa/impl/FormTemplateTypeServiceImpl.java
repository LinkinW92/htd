package com.skeqi.mes.service.yp.oa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.oa.FormTemplateTypeDao;
import com.skeqi.mes.service.yp.oa.FormTemplateTypeService;
import com.skeqi.mes.util.yp.TokenUtil;

/**
 * 表单模板类型
 *
 * @author yinp
 * @date 2021年5月27日
 */
@Service
public class FormTemplateTypeServiceImpl implements FormTemplateTypeService {

	@Autowired
	FormTemplateTypeDao dao;

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
	 * @return
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		String name = json.getString("name");

		if (dao.findFormTemplateTypeCountByName(name, null) > 0) {
			throw new Exception("类型名称已存在");
		}

		if (dao.add(json) != 1) {
			throw new Exception("新增失败");
		}

		// 查询判断菜单有没有跟类型重名的
		if (dao.findMenuCountByName(name) > 0) {
			throw new Exception("当前新增的类型名称与菜单栏名称存在冲突，请更换名称重试");
		}

		// 查询菜单信息
		JSONObject menu = dao.findMenuByName("发起申请");

		// 新增菜单
		JSONObject menuJson = new JSONObject();
		menuJson.put("menuGrade", menu.getInteger("menuGrade") + 1);
		menuJson.put("superiorMenuId", menu.getInteger("id"));
		menuJson.put("menuName", name);
		menuJson.put("ifEnable", "true");
		menuJson.put("path", "/OA/ApprovalCirculation/" + TokenUtil.randomToken(8));
		menuJson.put("icon", "more");
		menuJson.put("order", 2);
		dao.addMenu(menuJson);

	}

	/**
	 * 更新
	 *
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		int id = json.getInteger("id");
		String name = json.getString("name");
		String oldName = json.getString("oldName");

		if (dao.findFormTemplateTypeCountByName(name, id) > 0) {
			throw new Exception("类型名称已存在");
		}

		if (dao.update(json) != 1) {
			throw new Exception("更新失败");
		}

		// 更新菜单
		dao.updateMenu(name, oldName);

	}

	/**
	 * 删除
	 *
	 * @return
	 */
	@Override
	public void delete(int id, String name) throws Exception {
		if (dao.findFormTemplateCountByType(id) > 0) {
			throw new Exception("当前删除的表单模板类型已被表单模板绑定，需要先解除绑定");
		}
		if (dao.delete(id) != 1) {
			throw new Exception("删除失败");
		}

		// 删除菜单
		if (dao.deleteMenu(name) > 1) {
			throw new Exception("删除失败，删除菜单时出现异常");
		}

	}

	@Override
	public void asd(JSONObject json) {
		String name = json.getString("name");

		// 查询菜单信息
		JSONObject menu = dao.findMenuByName("OA");

		// 新增菜单
		JSONObject menuJson = new JSONObject();
		menuJson.put("menuGrade", menu.getInteger("menuGrade") + 1);
		menuJson.put("superiorMenuId", menu.getInteger("id"));
		menuJson.put("menuName", name);
		menuJson.put("ifEnable", "true");
		menuJson.put("path", "/OA/ApprovalCirculation/" + TokenUtil.randomToken(8));
		menuJson.put("icon", "more");
		menuJson.put("order", 2);
		dao.addMenu(menuJson);

	}

}
