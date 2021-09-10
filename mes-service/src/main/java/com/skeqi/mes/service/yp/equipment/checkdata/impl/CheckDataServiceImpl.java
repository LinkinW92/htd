package com.skeqi.mes.service.yp.equipment.checkdata.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.checkdata.CheckDataDao;
import com.skeqi.mes.service.yp.equipment.checkdata.CheckDataService;

/**
 * 点检记录
 *
 * @date2021年3月11日
 * @author yinp
 */
@Service
public class CheckDataServiceImpl implements CheckDataService {

	@Autowired
	CheckDataDao dao;

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
		int equipmentId = json.getInteger("equipmentId");
		// 新增
		if (dao.add(json) != 1) {
			throw new Exception("新增失败");
		}

		// 通过设备id查询出需要点检的点检项
		List<JSONObject> itemList = dao.itemList(equipmentId);
		if (itemList == null || itemList.size() == 0) {
			throw new Exception("选择的设备未配置点检项");
		}

		StringBuffer sql = new StringBuffer();
		sql.append("value");
		for (JSONObject item : itemList) {
			sql.append("('" + item.getString("item") + "',");
			sql.append("'" + item.getString("explain") + "',");
			sql.append("'" + json.getString("id") + "'),");
		}

		if (dao.addCheckDataDetailed(sql.substring(0, sql.length() - 1).toString()) < 1) {
			throw new Exception("新增失败");
		}

	}

	/**
	 * 更新
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		if (dao.update(json) != 1) {
			throw new Exception("更新失败");
		}

	}

	/**
	 * 查询所有产线跟设备
	 * @return
	 */
	@Override
	public List<JSONObject> lineAndEquipment() {
		return dao.lineAndEquipment();
	}

	/**
	 * 提交
	 *
	 * @param id
	 * @return
	 */
	@Override
	public void sub(int id) throws Exception {
		int result = dao.sub(id);
		if(result!=1) {
			throw new Exception("操作失败");
		}
	}

}
