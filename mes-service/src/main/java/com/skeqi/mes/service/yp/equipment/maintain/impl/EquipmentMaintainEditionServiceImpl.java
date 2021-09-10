package com.skeqi.mes.service.yp.equipment.maintain.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.maintain.EquipmentMaintainEditionDao;
import com.skeqi.mes.service.yp.equipment.maintain.EquipmentMaintainEditionService;

/**
 * 设备保养版本
 *
 * @author yinp Date 2021年3月11日
 */
@Service
public class EquipmentMaintainEditionServiceImpl implements EquipmentMaintainEditionService {

	@Autowired
	EquipmentMaintainEditionDao dao;

	/**
	 * 查询版本信息集合
	 *
	 * @param equipmentId
	 * @return
	 */
	@Override
	public List<JSONObject> list(int equipmentId) {
		return dao.list(equipmentId);
	}

	/**
	 * 新增版本信息
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		int equipmentId = json.getInteger("equipmentId");
		String edition = json.getString("edition");
		int state = json.getInteger("state");

		// 判断版本号是否有重复项
		int result = dao.findCount(equipmentId, edition, null);
		if (result > 0) {
			throw new Exception("版本号重复");
		}

		if (state == 1) {
			// 通过设备id停用该设备关联的所有版本
			dao.tingYong(equipmentId);
		}

		// 新增版本信息
		result = dao.add(json);
		if (result != 1) {
			throw new Exception("新增失败");
		}
	}

	/**
	 * 更新版本信息
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {

		int id = json.getInteger("id");
		int equipmentId = json.getInteger("equipmentId");
		String edition = json.getString("edition");
		int state = json.getInteger("state");

		// 判断版本号是否有重复项
		int result = dao.findCount(equipmentId, edition, id);
		if (result > 0) {
			throw new Exception("版本号重复");
		}

		if (state == 1) {
			// 通过设备id停用该设备关联的所有版本
			dao.tingYong(equipmentId);
		}

		// 更新版本信息
		result = dao.update(json);
		if (result != 1) {
			throw new Exception("更新失败");
		}
	}

	/**
	 * 删除版本
	 *
	 * @param id
	 * @return
	 */
	@Override
	public void delete(int id) throws Exception {
		// 删除版本
		int result = dao.delete(id);
		if (result != 1) {
			throw new Exception("删除失败");
		}
		// 通过版本表id删除点检项
		dao.deleteItem(id);
	}

	/**
	 * 启用某个版本
	 *
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void qiYong(int id, int equipmentId) throws Exception {
		// 通过设备id停用该设备关联的所有版本
		dao.tingYong(equipmentId);

		//启用某个版本
		int result = dao.qiYong(id);
		if (result != 1) {
			throw new Exception("启用失败");
		}
	}

	/**
	 * 查询闲置版本号
	 * @return
	 */
	@Override
	public List<JSONObject> nullEditionList() {
		// TODO Auto-generated method stub
		return dao.nullEditionList();
	}

}
