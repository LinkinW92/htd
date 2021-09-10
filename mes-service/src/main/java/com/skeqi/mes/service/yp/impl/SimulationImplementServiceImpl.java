package com.skeqi.mes.service.yp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.SimulationImplementDao;
import com.skeqi.mes.service.yp.SimulationImplementService;

/**
 * @author yinp
 * @explain 执行
 * @date 2020-12-24
 */
@Service
public class SimulationImplementServiceImpl implements SimulationImplementService {

	@Autowired
	SimulationImplementDao dao;

	public void addEvent(JSONObject json) throws Exception {
		int result = dao.addEvent(json);
		if (result != 1) {
			throw new Exception("新增事件出错了");
		}
	}

	/**
	 * @explain 扫描员工号
	 * @param empNumber
	 * @return
	 */
	@Override
	public void scanEmp(String emp, String sn, String lineName, String stationName, int stepNo) throws Exception {
		int result = dao.scanEmp(emp, sn);
		if (result != 1) {
			throw new Exception("扫描员工号失败了");
		}

		JSONObject json = new JSONObject();
		json.put("objectType", "成品");
		json.put("objectId", sn);
		json.put("event", "扫描员工号");
		json.put("parameter1", lineName);
		json.put("parameter2", stationName);
		json.put("parameter3", stepNo);
		// 新增事件
		result = dao.addEvent(json);
		if (result != 1) {
			throw new Exception("新增事件出错了");
		}
	}

	/**
	 * @explain 下一步
	 * @param sn
	 * @return
	 */
	@Override
	public void nextStep(String sn) throws Exception {
		int result = dao.nextStep(sn);
		if (result != 1) {
			throw new Exception("跳转下一步失败了");
		}
	}

	/**
	 * @explain 放行
	 * @param sn
	 * @param stationName
	 */
	@Override
	public void release(String sn, String stationName, String lineName) throws Exception {
		// 更新步序表工位跟步序
		int result = dao.addStep(sn, stationName, lineName);
		if (result != 1) {
			throw new Exception("更新步序表工位跟步序失败了");
		}
		// 更新总成运行表当前工位跟前工位
		result = dao.updateTrackingStAndBSt(sn, stationName);
		if (result != 1) {
			throw new Exception("更新总成运行表当前工位跟前工位失败了");
		}
	}

	/**
	 * @explain 查询工位是否可上线
	 * @param workOrderId
	 * @param stationName
	 */
	@Override
	public int findStationCanIGoOnline(int lineId, String stationName) {
		return dao.findStationCanIGoOnline(lineId, stationName);
	}

	/**
	 * @explain 气密性测试
	 * @param json
	 */
	@Override
	public void airTightnessTest(JSONObject json) throws Exception {
		int result = dao.airTightnessTest(json);
		if (result != 1) {
			throw new Exception("气密性测试失败");
		}

	}

	/**
	 * @explain 称重
	 * @param json
	 * @return
	 */
	@Override
	public void weigh(JSONObject json) throws Exception {
		int result = dao.weigh(json);
		if (result != 1) {
			throw new Exception("称重数据新增失败");
		}
	}

}
