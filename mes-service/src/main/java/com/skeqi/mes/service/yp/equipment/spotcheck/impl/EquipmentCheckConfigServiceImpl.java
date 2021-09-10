package com.skeqi.mes.service.yp.equipment.spotcheck.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.spotcheck.EquipmentCheckConfigDao;
import com.skeqi.mes.service.yp.equipment.spotcheck.EquipmentCheckConfigService;

/**
 * 设备点检配置
 *
 * @author yinp Date 2021年3月6日
 */
@Service
public class EquipmentCheckConfigServiceImpl implements EquipmentCheckConfigService {

	@Autowired
	EquipmentCheckConfigDao dao;

	/**
	 * 查询点检配置数据集合
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
	 * 查询所有产线及设备
	 *
	 * @return
	 */
	@Override
	public List<JSONObject> findLineAndEquipment() {
		// TODO Auto-generated method stub
		return dao.findLineAndEquipment();
	}

	/**
	 * 新增点检配置
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		// 点检名
		String name = json.getString("name");
		// 点检编号
		String code = json.getString("code");
		// 设备id
		int equipmentId = json.getInteger("equipmentId");
		// 新增版本号
		String edition = json.getString("edition");
		// 版本号id
		int editionId = json.getInteger("editionId");
		// 点检项
		List<JSONObject> itemList = JSON.parseArray(json.getString("itemList"), JSONObject.class);

		if (dao.findConfigByEquipmentId(equipmentId) > 0) {
			throw new Exception("已选设备已存在点检配置");
		}

		// 查询名称重复数量
		if (dao.findNameCount(name, null) > 0) {
			throw new Exception("点检名重复");
		}

		// 查询编号重复数量
		if (dao.findCodeCount(code, null) > 0) {
			throw new Exception("点检编号重复");
		}

		JSONObject editionJson = new JSONObject();
		// 新增版本号
		if (editionId == 0) {
			// 查询版本数量
			if (dao.findEditionCount(edition, equipmentId) > 0) {
				throw new Exception("版本号重复");
			}
			editionJson = new JSONObject();
			editionJson.put("edition", edition);
			editionJson.put("equipmentId", equipmentId);

			// 新增版本号
			int result = dao.addEdition(editionJson);
			if (result != 1) {
				throw new Exception("新增失败");
			}
		} else {
			// 查询版本名数量
			int result = dao.findEditionCount(edition, equipmentId);

			editionJson = new JSONObject();
			editionJson.put("equipmentId", equipmentId);
			editionJson.put("state", 1);
			editionJson.put("where_id", editionId);
			editionJson.put("id", editionId);
			if (result > 0) {
				editionJson.put("edition", edition + "_1");
			} else {
				editionJson.put("edition", edition);
			}
			// 更新版本号
			result = dao.updateEdition(editionJson);
			if (result != 1) {
				throw new Exception("新增失败");
			}
		}

		if (itemList != null) {
			StringBuffer sqlBuff = new StringBuffer();
			sqlBuff.append("value");
			for (JSONObject dx : itemList) {
				sqlBuff.append("('" + dx.getString("name") + "',");
				sqlBuff.append("'" + editionJson.getInteger("id") + "'),");
			}

			String sql = sqlBuff.toString().substring(0, sqlBuff.toString().length() - 1);

			int result = dao.addItems(sql);
			if (result != itemList.size()) {
				throw new Exception("新增失败");
			}
		}

		int result = dao.add(json);
		if (result != 1) {
			throw new Exception("新增失败");
		}
	}

	/**
	 * 更新点检配置
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		int id = json.getInteger("id");
		String name = json.getString("name");
		String code = json.getString("code");
		int equipmentId = json.getInteger("equipmentId");
		int editionId = json.getInteger("editionId");
		String edition = json.getString("edition");
		int editioneEquipmentId = json.getInteger("editioneEquipmentId");

		// 查询名称重复数量
		if (dao.findNameCount(name, id) > 0) {
			throw new Exception("点检名重复");
		}

		// 查询编号重复数量
		if (dao.findCodeCount(code, id) > 0) {
			throw new Exception("点检编号重复");
		}

		// 更新版本号的设备绑定更新
		JSONObject editionJson = new JSONObject();

		// 判断选择的版本号是不是未绑定设备的版本号
		if (editioneEquipmentId == 0) {
			int result = dao.findEditionCount(edition, equipmentId);

			editionJson.put("equipmentId", equipmentId);
			editionJson.put("state", 1);
			editionJson.put("where_id", editionId);
			if (result > 0) {
				editionJson.put("edition", edition + "_1");
			} else {
				editionJson.put("edition", edition);
			}
			// 更新版本号
			result = dao.updateEdition(editionJson);
			if (result != 1) {
				throw new Exception("更新失败");
			}
		}

		editionJson = new JSONObject();
		editionJson.put("state", 2);
		editionJson.put("where_equipmentId", equipmentId);
		// 通过设备id停用用版本号
		dao.updateEdition(editionJson);

		editionJson = new JSONObject();
		editionJson.put("state", 1);
		editionJson.put("where_id", editionId);
		// 启用版本号
		int result = dao.updateEdition(editionJson);
		if (result != 1) {
			throw new Exception("更新失败");
		}

		result = dao.update(json);
		if (result != 1) {
			throw new Exception("更新失败");
		}
	}

	/**
	 * 删除点检配置
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void delete(int id, int equipmentId) throws Exception {
		JSONObject json = new JSONObject();
		json.put("state", 2);
		json.put("equipmentId", "0");
		json.put("where_equipmentId", equipmentId);

		// 更新版本号
		int result = dao.updateEdition(json);

		result = dao.delete(id);
		if (result != 1) {
			throw new Exception("删除失败");
		}

	}

	/**
	 * 查询版本号
	 *
	 * @param equipmentId
	 * @return
	 */
	@Override
	public List<JSONObject> findEdition(int equipmentId) {
		return dao.findEdition(equipmentId);
	}
}
