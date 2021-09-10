package com.skeqi.mes.service.yp.wms.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsBarcodeTemplateDao;
import com.skeqi.mes.service.yp.wms.WmsBarcodeTemplateService;

/**
 * @author yinp
 * @explain 条码模板
 * @date 2021-07-14
 */
@Service
public class WmsBarcodeTemplateServiceImpl implements WmsBarcodeTemplateService {

	@Autowired
	WmsBarcodeTemplateDao dao;

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<JSONObject> list(JSONObject json) {
		return dao.list(json);
	}

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		String name = json.getString("name");
		String title = json.getString("title");
		if (dao.findCountByName(name, null) > 0) {
			throw new Exception("名称已存在");
		}
		if (dao.findCountByTitle(title, null) > 0) {
			throw new Exception("标题已存在");
		}
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
		String title = json.getString("title");
		if (dao.findCountByName(name, id) > 0) {
			throw new Exception("名称已存在");
		}
		if (dao.findCountByTitle(title, id) > 0) {
			throw new Exception("标题已存在");
		}
		if (dao.update(json) != 1) {
			throw new Exception("更新失败");
		}
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void delete(Integer id) throws Exception {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("viewMode", 0);
		if (dao.update(json) != 1) {
			throw new Exception("删除失败");
		}
	}

	/**
	 * 查询详情
	 *
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<JSONObject> listDetailed(JSONObject json) {
		return dao.listDetailed(json);
	}

	/**
	 * 更新详情
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void updateDetailed(JSONObject json) throws Exception {
		String list = json.getString("list");
		List<JSONObject> detailedList = null;
		try {
			detailedList = JSONObject.parseArray(list, JSONObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("数据格式有误");
		}
		if (detailedList == null || detailedList.size() == 0) {
			throw new Exception("无数据");
		}

		//删除详情
		dao.deleteDetailed(json.getInteger("templateId"));

		for (JSONObject jsonObject : detailedList) {
			jsonObject.put("templateId", json.getInteger("templateId"));
			if (dao.addDetailed(jsonObject) != 1) {
				throw new Exception("更新失败");
			}
		}

	}

}
