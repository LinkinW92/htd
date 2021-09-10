package com.skeqi.mes.service.yp.equipment.Information.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.Information.EquipmentInformationEventDao;
import com.skeqi.mes.service.yp.equipment.Information.EquipmentInformationEventService;

/**
 * @author yinp
 * @explain 设备事件
 * @date 2020-12-18
 */
@Service
public class EquipmentInformationEventServiceImpl implements EquipmentInformationEventService {

	@Autowired
	EquipmentInformationEventDao dao;

	@Override
	public List<JSONObject> list(int parentId) {
		return dao.list(parentId);
	}

	@Override
	public void add(int parentId, int event) throws Exception {
		int result = dao.add(parentId, event);
		if(result != 1) {
			throw new Exception("新增事件失败了");
		}
	}

}
