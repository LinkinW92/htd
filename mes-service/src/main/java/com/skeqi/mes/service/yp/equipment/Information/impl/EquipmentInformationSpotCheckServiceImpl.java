package com.skeqi.mes.service.yp.equipment.Information.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.Information.EquipmentInformationSpotCheckDao;
import com.skeqi.mes.service.yp.equipment.Information.EquipmentInformationSpotCheckService;

/**
 * @author yinp
 * @explain 点检
 * @date 2020-12-18
 */
@Service
public class EquipmentInformationSpotCheckServiceImpl implements EquipmentInformationSpotCheckService {

	@Autowired
	EquipmentInformationSpotCheckDao dao;

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
			throw new Exception("生成点检记录失败");
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

		//新增点检记录详情
		if (dao.addCheckDataDetailed(sql.substring(0, sql.length() - 1).toString()) < 1) {
			throw new Exception("生成点检记录失败");
		}

		//更新设备表点检时间
		if(dao.updateSpotCheckDate(json)!=1) {
			throw new Exception("生成点检记录失败");
		}

	}
}
