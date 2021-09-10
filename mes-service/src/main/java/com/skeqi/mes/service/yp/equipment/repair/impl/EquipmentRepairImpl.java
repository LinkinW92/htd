package com.skeqi.mes.service.yp.equipment.repair.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.repair.EquipmentRepairDao;
import com.skeqi.mes.service.yp.equipment.repair.EquipmentRepairService;

/**
 * @author yinp
 * @explain 设备维修
 * @date 2021年3月11日
 */
@Service
public class EquipmentRepairImpl implements EquipmentRepairService {

	@Autowired
	EquipmentRepairDao dao;

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
	 * @return
	 */
	@Override
	public void add(JSONObject json) throws Exception {
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
		if (dao.update(json) != 1) {
			throw new Exception("更新失败");
		}
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	@Override
	public void delete(int id) throws Exception {
		if (dao.delete(id) != 1) {
			throw new Exception("删除失败");
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

}
