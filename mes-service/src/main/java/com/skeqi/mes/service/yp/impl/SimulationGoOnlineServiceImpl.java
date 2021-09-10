package com.skeqi.mes.service.yp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.yp.SimulationGoOnlineDao;
import com.skeqi.mes.service.yp.SimulationGoOnlineService;

/**
 * @author yinp
 * @explain 产品上线
 * @date 2020-12-24
 */
@Service
public class SimulationGoOnlineServiceImpl implements SimulationGoOnlineService {

	@Autowired
	SimulationGoOnlineDao dao;

	/**
	 * @explain 更新条码打印标记
	 * @param sn
	 * @return
	 */
	@Override
	public void updatePrintFlag(String sn) throws Exception {
		dao.updatePrintFlag(sn);
	}

	/**
	 * @explain 产品上线
	 * @param lineId
	 * @param workOrderId
	 * @param sn
	 * @throws Exception
	 */
	@Override
	public void addRStep(String lineName, String stationName, String sn) throws Exception {
		int result = dao.addRStep(lineName, stationName, sn);
		if (result != 1) {
			throw new Exception("新增临时步序表失败了");
		}
	}

}
