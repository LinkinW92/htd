package com.skeqi.mes.mapper.yp;

import org.apache.ibatis.annotations.Param;

/**
 * @author yinp
 * @explain 产品上线
 * @date 2020-12-24
 */
public interface SimulationGoOnlineDao {

	/**
	 * @explain 更新条码打印标记
	 * @param sn
	 * @return
	 */
	public int updatePrintFlag(@Param("sn")String sn);

	/**
	 * @explain 新增临时步序表
	 * @param lineId
	 * @param workOrderId
	 * @param sn
	 * @return
	 */
	public int addRStep(@Param("lineName") String lineName, @Param("stationName") String stationName, @Param("sn") String sn);

}
