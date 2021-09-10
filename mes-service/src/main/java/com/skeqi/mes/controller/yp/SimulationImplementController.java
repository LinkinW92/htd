package com.skeqi.mes.controller.yp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.SimulationImplementService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 执行
 * @date 2020-12-24
 */
@RestController
@RequestMapping("/api/SimulationImplement")
public class SimulationImplementController {

	@Autowired
	SimulationImplementService service;

	/**
	 * @explain 扫码员工号
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/scanEmp")
	public Rjson scanEmp(HttpServletRequest request) {
		try {

			String emp = EqualsUtil.string(request, "emp", "员工号", true);
			String sn = EqualsUtil.string(request, "sn", "sn", true);
			String lineName = EqualsUtil.string(request, "lineName", "产线名称", true);
			String stationName = EqualsUtil.string(request, "stationName", "工位名称", true);
			int stepNo = EqualsUtil.integer(request, "stepNo", "步序", true);
			service.scanEmp(emp, sn, lineName, stationName, stepNo);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 下一步
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/nextStep")
	public Rjson nextStep(HttpServletRequest request) {
		try {
			String sn = EqualsUtil.string(request, "sn", "sn", true);
			service.nextStep(sn);
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 放行
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/release")
	public Rjson release(HttpServletRequest request) {
		try {
			String sn = EqualsUtil.string(request, "sn", "sn", true);
			String stationName = EqualsUtil.string(request, "stationName", "工位名称", false);
			String lineName = EqualsUtil.string(request, "lineName", "产线名称", true);

			if (stationName == null || stationName.equals("")) {
				return Rjson.success();
			}

			service.release(sn, stationName, lineName);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 查询工位是否可上线
	 * @param request
	 * @return
	 */
	@RequestMapping("/findStationCanIGoOnline")
	public Rjson findStationCanIGoOnline(HttpServletRequest request) {
		try {
			int lineId = EqualsUtil.integer(request, "lineId", "产线id", true);
			String stationName = EqualsUtil.string(request, "stationName", "工位名称", false);

			if (stationName == null || stationName.equals("")) {
				return Rjson.success();
			}

			int result = service.findStationCanIGoOnline(lineId, stationName);
			return Rjson.success(result);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 气密性测试
	 * @param request
	 * @return
	 */
	@RequestMapping("/airTightnessTest")
	public Rjson airTightnessTest(HttpServletRequest request) {
		try {
			String st = EqualsUtil.string(request, "st", "工位名称", true);
			String sn = EqualsUtil.string(request, "sn", "sn", true);
			String leakageName = EqualsUtil.string(request, "leakageName", "气密名称", true);
			String leakagePv = EqualsUtil.string(request, "leakagePv", "工位名称", true);
			String leakageLv = EqualsUtil.string(request, "leakageLv", "工位名称", true);
			String leakageR = EqualsUtil.string(request, "leakageR", "工位名称", true);
			String emp = EqualsUtil.string(request, "emp", "员工", true);

			JSONObject json = new JSONObject();
			json.put("st", st);
			json.put("sn", sn);
			json.put("leakageName", leakageName);
			json.put("leakagePv", leakagePv);
			json.put("leakageLv", leakageLv);
			json.put("leakageR", leakageR);
			json.put("emp", emp);

			service.airTightnessTest(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 称重
	 * @param request
	 * @return
	 */
	@RequestMapping("/weigh")
	public Rjson weigh(HttpServletRequest request) {
		try {
			String st = EqualsUtil.string(request, "st", "工位名称", true);
			String sn = EqualsUtil.string(request, "sn", "总成", true);
			String emp = EqualsUtil.string(request, "emp", "员工", true);
			String weigh = EqualsUtil.string(request, "weigh", "重量", true);

			JSONObject json = new JSONObject();
			json.put("st", st);
			json.put("sn", sn);
			json.put("emp", emp);
			json.put("weigh", weigh);

			service.weigh(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}
