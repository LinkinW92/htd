package com.skeqi.mes.mapper.yp;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 执行
 * @date 2020-12-24
 */
public interface SimulationImplementDao {

	/**
	 * @explain 新增事件
	 * @param json
	 * @return
	 */
	public int addEvent(JSONObject json);

	/**
	 * @explain 扫描员工号
	 * @param empNumber
	 * @return
	 */
	public int scanEmp(@Param("emp") String emp, @Param("sn") String sn);

	/**
	 * @explain 下一步
	 * @param sn
	 * @return
	 */
	public int nextStep(@Param("sn") String sn);

	/**
	 * @explain 新增步序表
	 * @param sn
	 * @param stationName
	 * @return
	 */
	public int addStep(@Param("sn") String sn, @Param("stationName") String stationName,
			@Param("lineName") String lineName);

	/**
	 * @explain 修改总成运行表当前工位跟前工位
	 * @param sn
	 * @param stationName
	 * @return
	 */
	public int updateTrackingStAndBSt(@Param("sn") String sn, @Param("stationName") String stationName);

	/**
	 * @explain 查询工位是否可上线
	 * @param workOrderId
	 * @param stationName
	 */
	public int findStationCanIGoOnline(@Param("lineId") int lineId, @Param("stationName") String stationName);

	/**
	 * @explain 气密性测试
	 * @param json
	 */
	public int airTightnessTest(JSONObject json);

	/**
	 * @explain 称重
	 * @param json
	 * @return
	 */
	public int weigh(JSONObject json);

}
