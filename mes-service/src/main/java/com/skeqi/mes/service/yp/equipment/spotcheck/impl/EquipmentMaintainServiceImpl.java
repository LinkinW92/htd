package com.skeqi.mes.service.yp.equipment.spotcheck.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.spotcheck.EquipmentMaintainDao;
import com.skeqi.mes.service.yp.equipment.spotcheck.EquipmentMaintainService;

/**
 * 设备保养
 *
 * @author yinp
 * @Date 2021年3月6日
 */
@Service
public class EquipmentMaintainServiceImpl implements EquipmentMaintainService {

	@Autowired
	EquipmentMaintainDao dao;

	/**
	 * 查询保养记录集合
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> list(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.list(json);
	}

	/**
	 * 新增保养记录
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		// TODO Auto-generated method stub
		int result = dao.add(json);
		if(result<=0) {
			throw new Exception("新增保养记录失败");
		}
	}

	/**
	 * 更新保养记录
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		// TODO Auto-generated method stub
		int result = dao.update(json);
		if(result<=0) {
			throw new Exception("更新保养记录失败");
		}
	}

	/**
	 * 查询所有产线及设备
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findLineAndEquipment() {
		// TODO Auto-generated method stub
		return dao.findLineAndEquipment();
	}

}
