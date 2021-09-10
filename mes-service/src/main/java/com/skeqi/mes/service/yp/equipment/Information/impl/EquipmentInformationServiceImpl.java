package com.skeqi.mes.service.yp.equipment.Information.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.Information.EquipmentInformationDao;
import com.skeqi.mes.service.yp.equipment.Information.EquipmentInformationService;

/**
 * @author yinp
 * @explain 设备资料
 * @date 2020-12-14
 */
@Service
public class EquipmentInformationServiceImpl implements EquipmentInformationService {

	@Autowired
	EquipmentInformationDao dao;

	/**
	 * @explain 查询
	 * @param json
	 * @return
	 */
	@Override
	public List<Map<String, Object>> list(JSONObject json) {
		List<Map<String, Object>> list = dao.list(json);
//		for (int i = 0; i < list.size(); i++) {
//			List<JSONObject> customColumnsList = JSONObject.parseArray(list.get(i).getString("customColumns"),JSONObject.class);
//			StringBuffer customColumns = new StringBuffer();
//			for (int j = 0; j < customColumnsList.size(); j++) {
//				customColumns.append("<span style='color:DeepSkyBlue'>"+customColumnsList.get(j).getString("columnName") +"</span>");
//				customColumns.append(":");
//				customColumns.append(customColumnsList.get(j).getString("value"));
//				customColumns.append(";");
//			}
//			list.get(i).put("customColumns", customColumns);
//		}
		return list;
	}

	/**
	 * @explain 新增
	 * @param json
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		String number = json.getString("number");

		// 校验编号
		int result = dao.checkNumber(number, null);
		if (result > 0) {
			throw new Exception("编号已存在");
		}

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, json.getInteger("maintenancePeriod"));

		json.put("nextMaintenanceDate", sf.format(c.getTime()).toString());

		sf = new SimpleDateFormat("yyyy-MM-dd");
		c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, json.getInteger("spotCheckCycle"));

		json.put("nextSpotCheckDate", sf.format(c.getTime()).toString());

		// 新增
		result = dao.add(json);
		if (result != 1) {
			throw new Exception("新增出错了");
		}

		int id = json.getInteger("id");

		String stationIds = json.getString("stationIds");
		String[] stationId = stationIds.split(",");
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("value");
		for (int i = 0; i < stationId.length; i++) {
			sqlBuffer.append("(");
			sqlBuffer.append(stationId[i] + ",");
			sqlBuffer.append(id);
			sqlBuffer.append(")");
			if (i != (stationId.length - 1)) {
				sqlBuffer.append(",");
			}
		}

		// 新增设备工位信息
		result = dao.addEquipmentInformationStation(sqlBuffer.toString());
		if (result <= 0) {
			throw new Exception("新增出错了");
		}

		// 新增事件
		result = dao.addEvent(id, 1);
		if (result != 1) {
			throw new Exception("新增出错了");
		}

	}

	/**
	 * @explain 更新
	 * @param json
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		String number = json.getString("number");
		int id = json.getInteger("id");

		// 校验编号
		int result = dao.checkNumber(number, id);
		if (result > 0) {
			throw new Exception("编号已存在");
		}

		// 更新
		result = dao.update(json);
		if (result != 1) {
			throw new Exception("更新出错了");
		}

		// 删除设备工位信息
		result = dao.deleteEquipmentInformationStation(id);

		String stationIds = json.getString("stationIds");
		String[] stationId = stationIds.split(",");
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("value");
		for (int i = 0; i < stationId.length; i++) {
			sqlBuffer.append("(");
			sqlBuffer.append(stationId[i] + ",");
			sqlBuffer.append(id);
			sqlBuffer.append(")");
			if (i != (stationId.length - 1)) {
				sqlBuffer.append(",");
			}
		}

		// 新增设备工位信息
		result = dao.addEquipmentInformationStation(sqlBuffer.toString());
		if (result <= 0) {
			throw new Exception("更新出错了");
		}

		// 新增事件
		result = dao.addEvent(id, 2);
		if (result != 1) {
			throw new Exception("更新出错了");
		}

	}

	/**
	 * @explain 删除
	 * @param id
	 */
	@Override
	public void delete(int id) throws Exception {
		// 删除
		int result = dao.delete(id);
		if (result != 1) {
			throw new Exception("删除出错了");
		}

	}

	/**
	 * @explain 查询产线
	 * @return
	 */
	@Override
	public List<JSONObject> lineList() {
		// TODO Auto-generated method stub
		return dao.lineList();
	}

	/**
	 * @explain 通过产线id查询工位
	 * @param lineId
	 * @return
	 */
	@Override
	public List<JSONObject> stationListByLineId(int lineId) {
		// TODO Auto-generated method stub
		return dao.stationListByLineId(lineId);
	}

	/**
	 * @explain 查询自定义列集合
	 * @param parentId
	 * @return
	 */
	@Override
	public List<JSONObject> customColumnsList(int parentId) {
		// TODO Auto-generated method stub
		return dao.customColumnsList(parentId);
	}

	/**
	 * @explain 新增自定义列
	 * @param json
	 * @return
	 */
	@Override
	public void customColumnsAdd(JSONObject json) throws Exception {
		// TODO Auto-generated method stub
		int parentId = json.getInteger("parentId");
		String columnName = json.getString("columnName");
		int result = dao.checkcustomColumnsColumnName(null, parentId, columnName);
		if (result > 0) {
			throw new Exception("列名已存在");
		}
		result = dao.customColumnsAdd(json);
		if (result != 1) {
			throw new Exception("新增自定义列失败");
		}
	}

	/**
	 * @explain 更新自定义列
	 * @param json
	 * @return
	 */
	@Override
	public void customColumnsUpdate(JSONObject json) throws Exception {
		// TODO Auto-generated method stub
		int id = json.getInteger("id");
		int parentId = json.getInteger("parentId");
		String columnName = json.getString("columnName");
		int result = dao.checkcustomColumnsColumnName(id, parentId, columnName);
		if (result > 0) {
			throw new Exception("列名已存在");
		}
		result = dao.customColumnsUpdate(json);
		if (result != 1) {
			throw new Exception("更新自定义列失败");
		}
	}

	/**
	 * @explain 删除自定义列
	 * @param json
	 * @return
	 */
	@Override
	public void customColumnsDelete(int id) throws Exception {
		// TODO Auto-generated method stub
		int result = dao.customColumnsDelete(id);
		if (result != 1) {
			throw new Exception("删除自定义列失败");
		}
	}

	/**
	 * @explain 通过id查询对象
	 * @param id
	 * @return
	 */
	@Override
	public JSONObject objectById(int id) {
		// TODO Auto-generated method stub
		return dao.objectById(id);
	}

	/**
	 * @explain 通过编号查询对象
	 * @param number
	 * @return
	 */
	@Override
	public JSONObject objectByNumber(String number) {
		// TODO Auto-generated method stub
		return dao.objectByNumber(number);
	}

}
