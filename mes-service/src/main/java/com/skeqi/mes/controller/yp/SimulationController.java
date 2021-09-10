package com.skeqi.mes.controller.yp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.SimulationService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 模拟
 * @date 2020-12-22
 */
@RestController
@RequestMapping("/api/Simulation")
public class SimulationController {

	@Autowired
	SimulationService service;

	/**
	 * @explain 查询所有产线
	 * @return
	 */
	@RequestMapping("/findLineAll")
	public Rjson findLineAll(HttpServletRequest request) {
		try {

			List<JSONObject> list = service.findLineAll();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 查询工位
	 * @return
	 */
	@RequestMapping("/findStation")
	public Rjson findStation(HttpServletRequest request) {
		try {

			int lineId = EqualsUtil.integer(request, "lineId", "产线id", true);

			List<JSONObject> list = service.findStation(lineId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 查询正在进行的工单
	 * @return
	 */
	@RequestMapping("/findWorkOrder")
	public Rjson findWorkOrder(HttpServletRequest request) {
		try {

			int lineId = EqualsUtil.integer(request, "lineId", "产线id", true);

			List<JSONObject> list = service.findWorkOrder(lineId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 查询sn
	 * @return
	 */
	@RequestMapping("/findSn")
	public Rjson findSn(HttpServletRequest request) {
		try {

			int workOrderId = EqualsUtil.integer(request, "workOrderId", "工单id", true);

			List<String> list = service.findSn(workOrderId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain
	 * @return
	 */
	@RequestMapping("/findlist")
	public Rjson findlist(HttpServletRequest request) {
		try {

			int workOrderId = EqualsUtil.integer(request, "workOrderId", "workOrderId", true);

			List<JSONObject> list = service.findlist(workOrderId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 查询工单详情
	 * @return
	 */
	@RequestMapping("/findWorkOrderDedetails")
	public Rjson findWorkOrderDedetails(HttpServletRequest request) {
		try {
			int workOrderId = EqualsUtil.integer(request, "workOrderId", "工单id", true);

			JSONObject json = service.findWorkOrderDedetails(workOrderId);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 查询螺栓数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/findBolt")
	public Rjson findBolt(HttpServletRequest request) {
		try {
			int workOrderId = EqualsUtil.integer(request, "workOrderId", "工单id", true);
			String sn = EqualsUtil.string(request, "sn", "sn", false);

			List<JSONObject> list = service.findBolt(workOrderId, sn);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 查询事件
	 * @param request
	 * @return
	 */
	@RequestMapping("/findEvent")
	public Rjson findEvent(HttpServletRequest request) {
		try {
			String lineName = EqualsUtil.string(request, "lineName", "产线名称", true);
			String stationName = EqualsUtil.string(request, "stationName", "工位名称", false);
			String sn = EqualsUtil.string(request, "sn", "sn", false);

			List<JSONObject> list = service.findEvent(lineName, stationName, sn);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 查询工位对象
	 * @param request
	 * @return
	 */
	@RequestMapping("/findWorkingProcedureObject")
	public Rjson findWorkingProcedureObject(HttpServletRequest request) {
		try {
			int stationId = EqualsUtil.integer(request, "stationId", "工位Id", true);
			int workOrderId = EqualsUtil.integer(request, "workOrderId", "工单Id", true);

			List<JSONObject> list = service.findWorkingProcedureObject(stationId, workOrderId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 查询产品对象
	 * @param request
	 * @return
	 */
	@RequestMapping("/findProductObject")
	public Rjson findProductObject(HttpServletRequest request) {
		try {
			String sn = EqualsUtil.string(request, "sn", "sn", true);

			JSONObject json = service.findProductObject(sn);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}
