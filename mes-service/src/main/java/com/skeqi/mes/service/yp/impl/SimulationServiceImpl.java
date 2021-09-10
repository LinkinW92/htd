package com.skeqi.mes.service.yp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.SimulationDao;
import com.skeqi.mes.service.yp.SimulationService;

/**
 * @author yinp
 * @explain 模拟
 * @date 2020-12-22
 */
@Service
public class SimulationServiceImpl implements SimulationService {

	@Autowired
	SimulationDao dao;

	/**
	 * @explain 查询所有产线
	 * @return
	 */
	@Override
	public List<JSONObject> findLineAll() {
		return dao.findLineAll();
	}

	/**
	 * @explain 查询工位
	 * @param lineId
	 * @return
	 */
	@Override
	public List<JSONObject> findStation(int lineId) {
		return dao.findStation(lineId);
	}

	/**
	 * @explain 查询正在进行的工单
	 * @return
	 */
	@Override
	public List<JSONObject> findWorkOrder(int lineId) {
		return dao.findWorkOrder(lineId);
	}

	/**
	 * @explain 查询sn
	 * @param workOrderId
	 * @return
	 */
	@Override
	public List<String> findSn(int workOrderId) {
		return dao.findSn(workOrderId);
	}

	/**
	 * @explain 查询
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> findlist(int workOrderId) {
		return dao.findlist(workOrderId);
	}

	/**
	 * @explain 查询工单详情
	 * @param workOrderId
	 * @return
	 */
	@Override
	public JSONObject findWorkOrderDedetails(int workOrderId) {
		return dao.findWorkOrderDedetails(workOrderId);
	}

	/**
	 * @explain 查询螺栓数据
	 * @param workOrderId
	 * @param sn
	 * @return
	 */
	@Override
	public List<JSONObject> findBolt(int workOrderId, String sn) {
		List<JSONObject> list1 = dao.findRBolt(workOrderId, sn);
		List<JSONObject> list2 = dao.findPBolt(workOrderId, sn);
		for (JSONObject jsonObject : list2) {
			list1.add(jsonObject);
		}
		return list1;
	}

	/**
	 * @explain 查询事件
	 * @param lineName
	 * @param stationName
	 * @param sn
	 * @return
	 */
	@Override
	public List<JSONObject> findEvent(String lineName, String stationName, String sn) {
		return dao.findEvent(lineName, stationName, sn);
	}

	/**
	 * @explain 查询工位对象
	 * @param stationId
	 * @return
	 */
	@Override
	public List<JSONObject> findWorkingProcedureObject(int stationId, int workOrderId) {
		// TODO Auto-generated method stub
		return dao.findWorkingProcedureObject(stationId, workOrderId);
	}

	/**
	 * @explain 查询产品对象
	 * @param sn
	 * @return
	 */
	@Override
	public JSONObject findProductObject(String sn) {
		List<JSONObject> list = dao.findBom(sn);
		JSONObject json = dao.findProductObject(sn);
		json.put("list", list);
		return json;
	}

}
