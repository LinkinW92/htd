package com.skeqi.mes.service.yp.equipment.spotcheck.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.spotcheck.EquipmentCheckItemsDao;
import com.skeqi.mes.service.yp.equipment.spotcheck.EquipmentCheckItemsService;

/**
 * 设备点检项
 *
 * @Date 2021年3月4日
 * @author yinp
 */
@Service
public class EquipmentCheckItemsServiceImpl implements EquipmentCheckItemsService {

	@Autowired
	EquipmentCheckItemsDao dao;

	/**
	 * 查询配置项集合
	 *
	 * @param editionId
	 * @return
	 */
	@Override
	public List<JSONObject> list(int editionId) {
		// TODO Auto-generated method stub
		return dao.list(editionId);
	}

	/**
	 * 新增点检项
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		String checkItems = json.getString("checkItems");
		int editionId = json.getInteger("editionId");
		if (dao.findCount(checkItems, editionId, null) > 0) {
			throw new Exception("点检项名重复");
		}

		int result = dao.add(json);
		if (result != 1) {
			throw new Exception("新增失败");
		}

	}

	/**
	 * 更新点检项
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		int id = json.getInteger("id");
		int editionId = json.getInteger("editionId");
		String checkItems = json.getString("checkItems");
		if (dao.findCount(checkItems, editionId, id) > 0) {
			throw new Exception("点检项名重复");
		}

		int result = dao.update(json);
		if (result != 1) {
			throw new Exception("更新失败");
		}
	}

	/**
	 * 删除点检项
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void delete(int id) throws Exception {
		int result = dao.delete(id);
		if (result != 1) {
			throw new Exception("删除失败");
		}
	}

}
