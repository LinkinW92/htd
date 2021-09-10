package com.skeqi.mes.service.yp;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 模拟
 * @date 2020-12-22
 */
public interface SimulationService {

	/**
	 * @explain 查询所有产线
	 * @return
	 */
	public List<JSONObject> findLineAll();

	/**
	 * @explain 查询工位
	 * @param lineId
	 * @return
	 */
	public List<JSONObject> findStation(int lineId);

	/**
	 * @explain 查询正在进行的工单
	 * @return
	 */
	public List<JSONObject> findWorkOrder(int lineId);

	/**
	 * @explain 查询sn
	 * @param workOrderId
	 * @return
	 */
	public List<String> findSn(int workOrderId);

	/**
	 * @explain 查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> findlist(int workOrderId);

	/**
	 * @explain 查询工单详情
	 * @param workOrderId
	 * @return
	 */
	public JSONObject findWorkOrderDedetails(int workOrderId);

	/**
	 * @explain 查询螺栓数据
	 * @param workOrderId
	 * @param sn
	 * @return
	 */
	public List<JSONObject> findBolt(int workOrderId, String sn);

	/**
	 * @explain 查询事件
	 * @param lineName
	 * @param stationName
	 * @param sn
	 * @return
	 */
	public List<JSONObject> findEvent(String lineName, String stationName, String sn);

	/**
	 * @explain 查询工位对象
	 * @param stationId
	 * @return
	 */
	public List<JSONObject> findWorkingProcedureObject(int stationId, int workOrderId);

	/**
	 * @explain 查询产品对象
	 * @param sn
	 * @return
	 */
	public JSONObject findProductObject(String sn);

}
