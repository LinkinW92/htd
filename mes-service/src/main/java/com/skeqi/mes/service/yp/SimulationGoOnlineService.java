package com.skeqi.mes.service.yp;

/**
 * @author yinp
 * @explain 产品上线
 * @date 2020-12-24
 */
public interface SimulationGoOnlineService {

	/**
	 * @explain 更新条码打印标记
	 * @param sn
	 * @return
	 */
	public void updatePrintFlag(String sn) throws Exception;

	/**
	 * @explain 产品上线
	 * @param lineId
	 * @param workOrderId
	 * @param sn
	 * @throws Exception
	 */
	public void addRStep(String lineName, String stationName, String sn) throws Exception;

}
