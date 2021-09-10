package com.skeqi.mes.service.yp.production.simulation;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 生产模拟
 * @date 2021-3-15
 */
public interface ProductionSimulationService {

	/**
	 * @explain 查询
	 * @return
	 */
	List<JSONObject> list();

	/**
	 * 通过sn查询出对应的产品
	 *
	 * @param sn
	 * @return
	 */
	public List<JSONObject> findProduction(String sn) throws Exception;

	/**
	 * 查询当前正在做的工艺
	 *
	 * @param productionId
	 * @param stationName
	 * @return
	 */
	public JSONObject findTechnology(int productionId, String stationName, String sn);

	/**
	 * 产线产线下的条码
	 *
	 * @param sn
	 * @return
	 */
	public List<JSONObject> findSN(int lineId);


	/**
	 * 产线产线下的工位
	 * @return
	 */
	public List<JSONObject> findStation(@Param("lineId") int lineId);
}
