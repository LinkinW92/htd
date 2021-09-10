package com.skeqi.mes.service.yp;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 执行
 * @date 2020-12-24
 */
public interface SimulationImplementService {

	/**
	 * @explain 扫描员工号
	 * @param empNumber
	 * @return
	 */
	public void scanEmp(String emp, String sn, String lineName, String stationName, int stepNo) throws Exception;

	/**
	 * @explain 下一步
	 * @param sn
	 * @return
	 */
	public void nextStep(String sn) throws Exception;

	/**
	 * @explain 放行
	 * @param sn
	 * @param stationName
	 */
	public void release(String sn, String stationName, String lineName) throws Exception;

	/**
	 * @explain 查询工位是否可上线
	 * @param workOrderId
	 * @param stationName
	 */
	public int findStationCanIGoOnline(int lineId, String stationName);

	/**
	 * @explain 气密性测试
	 * @param json
	 */
	public void airTightnessTest(JSONObject json) throws Exception;

	/**
	 * @explain 称重
	 * @param json
	 * @return
	 */
	public void weigh(JSONObject json) throws Exception;

}
