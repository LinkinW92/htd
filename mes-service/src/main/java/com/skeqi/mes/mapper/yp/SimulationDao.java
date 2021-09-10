package com.skeqi.mes.mapper.yp;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 模拟
 * @date 2020-12-22
 */
public interface SimulationDao {

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
	public List<JSONObject> findStation(@Param("lineId") int lineId);

	/**
	 * @explain 查询正在进行的工单
	 * @return
	 */
	public List<JSONObject> findWorkOrder(@Param("lineId") int lineId);

	/**
	 * @explain 查询sn
	 * @param workOrderId
	 * @return
	 */
	public List<String> findSn(@Param("workOrderId") int workOrderId);

	/**
	 * @explain 查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> findlist(@Param("workOrderId") int workOrderId);

	/**
	 * @explain 查询工单详情
	 * @param workOrderId
	 * @return
	 */
	public JSONObject findWorkOrderDedetails(@Param("workOrderId") int workOrderId);

	/**
	 * @explain 查询R螺栓数据
	 * @param workOrderId
	 * @param sn
	 * @return
	 */
	public List<JSONObject> findRBolt(@Param("workOrderId") int workOrderId, @Param("sn") String sn);

	/**
	 * @explain 查询P螺栓数据
	 * @param workOrderId
	 * @param sn
	 * @return
	 */
	public List<JSONObject> findPBolt(@Param("workOrderId") int workOrderId, @Param("sn") String sn);

	/**
	 * @explain 查询事件
	 * @param lineName
	 * @param stationName
	 * @param sn
	 * @return
	 */
	public List<JSONObject> findEvent(@Param("lineName") String lineName, @Param("stationName") String stationName,
			@Param("sn") String sn);

	/**
	 * @explain 查询工位对象
	 * @param stationId
	 * @return
	 */
	public List<JSONObject> findWorkingProcedureObject(@Param("stationId") int stationId,
			@Param("workOrderId") int workOrderId);

	/**
	 * @explain 查询产品对象
	 * @param sn
	 * @return
	 */
	public JSONObject findProductObject(@Param("sn") String sn);

	/**
	 * @explain 查询bom
	 * @param sn
	 * @return
	 */
	public List<JSONObject> findBom(@Param("sn") String sn);

}
