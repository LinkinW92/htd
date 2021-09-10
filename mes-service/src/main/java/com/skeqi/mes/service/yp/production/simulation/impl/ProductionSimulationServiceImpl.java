package com.skeqi.mes.service.yp.production.simulation.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.production.simulation.ProductionSimulationDao;
import com.skeqi.mes.service.yp.production.simulation.ProductionSimulationService;

/**
 * @author yinp
 * @explain 生产模拟
 * @date 2021-3-15
 */
@Service
public class ProductionSimulationServiceImpl implements ProductionSimulationService {

	@Autowired
	ProductionSimulationDao dao;

	/**
	 * @explain 查询
	 * @return
	 */
	@Override
	public List<JSONObject> list() {
		return dao.list();
	}

	/**
	 * 通过sn查询对应的产品
	 *
	 * @throws Exception
	 */
	@Override
	public List<JSONObject> findProduction(String sn) throws Exception {

		// 查询是否是系统生成的条码
		List<JSONObject> list = dao.findPlanPrint(sn);
		if (list != null && list.size() != 0) {
			return list;
		}

		// 通过sn匹配产品的校验规则
		list = dao.findProductionVr(sn);
		if (list != null && list.size() != 0) {
			return list;
		}

		throw new Exception("未匹配到产品");
	}

	/**
	 * 查询当前正在做的工艺
	 *
	 * @param productionId
	 * @param stationName
	 * @return
	 */
	@Override
	public JSONObject findTechnology(int productionId, String stationName, String sn) {
		return dao.findTechnology(productionId, stationName, sn);
	}

	/**
	 * 产线产线下的条码
	 *
	 * @param sn
	 * @return
	 */
	@Override
	public List<JSONObject> findSN(int lineId) {
		return dao.findSN(lineId);
	}

	/**
	 * 产线产线下的工位
	 * @return
	 */
	@Override
	public List<JSONObject> findStation(int lineId) {
		return dao.findStation(lineId);
	}
}
