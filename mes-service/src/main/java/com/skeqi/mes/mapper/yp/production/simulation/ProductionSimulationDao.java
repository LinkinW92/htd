package com.skeqi.mes.mapper.yp.production.simulation;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 生产模拟
 * @date 2021-3-15
 */
public interface ProductionSimulationDao {

	/**
	 * @explain 查询
	 * @return
	 */
	List<JSONObject> list();

	/**
	 * 查询sn查询系统生成的条码
	 *
	 * @param sn
	 * @return
	 */
	public List<JSONObject> findPlanPrint(@Param("sn") String sn);

	/**
	 * 通过sn匹配产品的校验规则
	 *
	 * @param sn
	 * @return
	 */
	public List<JSONObject> findProductionVr(@Param("sn") String sn);

	/**
	 * 查询当前正在做的工艺
	 *
	 * @param productionId
	 * @param stationName
	 * @return
	 */
	public JSONObject findTechnology(@Param("productionId") int productionId, @Param("stationName") String stationName,
			@Param("sn") String sn);

	/**
	 * 产线产线下的条码
	 *
	 * @param sn
	 * @return
	 */
	public List<JSONObject> findSN(@Param("lineId") int lineId);

	/**
	 * 产线产线下的工位
	 * @return
	 */
	public List<JSONObject> findStation(@Param("lineId") int lineId);

}
