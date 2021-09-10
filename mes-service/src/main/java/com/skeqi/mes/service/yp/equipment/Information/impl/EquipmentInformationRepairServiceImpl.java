package com.skeqi.mes.service.yp.equipment.Information.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.Information.EquipmentInformationRepairDao;
import com.skeqi.mes.service.yp.equipment.Information.EquipmentInformationRepairService;

/**
 * @author yinp
 * @explain 设备维修
 * @date 2020-12-18
 */
@Service
public class EquipmentInformationRepairServiceImpl implements EquipmentInformationRepairService {

	@Autowired
	EquipmentInformationRepairDao dao;

	/**
	 * @explain 查询集合
	 * @param parentId
	 * @return
	 */
	@Override
	public List<JSONObject> list(int parentId) {
		return dao.list(parentId);
	}

	/**
	 * @explain 新增维修记录
	 * @param json
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		int parentId = json.getInteger("parentId");
		int result = dao.add(json);
		if (result != 1) {
			throw new Exception("新增失败");
		}
		// 新增事件
		result = dao.addEvent(parentId, 9);
		if (result != 1) {
			throw new Exception("新增失败");
		}

		//更新设备的上次维修时间
		result  = dao.updateEquipmentLastRepairDate(parentId);
		if (result != 1) {
			throw new Exception("新增失败");
		}
	}

}
