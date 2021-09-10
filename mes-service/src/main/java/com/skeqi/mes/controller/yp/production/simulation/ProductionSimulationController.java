package com.skeqi.mes.controller.yp.production.simulation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.production.simulation.ProductionSimulationService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 生产模拟
 * @date 2021-3-15
 */
@RestController
@RequestMapping("/api/production/productionSimulation")
public class ProductionSimulationController {

	@Autowired
	ProductionSimulationService service;

	/**
	 * @explain 查询
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.list();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过sn查询出对应的产品
	 * @param request
	 * @return
	 */
	@RequestMapping("/findProduction")
	public Rjson findProduction(HttpServletRequest request) {
		try {
			String sn = EqualsUtil.string(request, "sn", "总成号", true);
			List<JSONObject> list = service.findProduction(sn);

			return Rjson.success(list.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询当前正在做的工艺
	 * @param request
	 * @return
	 */
	@RequestMapping("/findTechnology")
	public Rjson findTechnology(HttpServletRequest request) {
		try {
			int productionId = EqualsUtil.integer(request, "productionId", "产品", true);
			String stationName = EqualsUtil.string(request, "stationName", "工位", true);
			String sn = EqualsUtil.string(request, "sn", "总成码", true);

			JSONObject json = service.findTechnology(productionId, stationName, sn);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 产线产线下的条码
	 * @param request
	 * @return
	 */
	@RequestMapping("/findSN")
	public Rjson findSN(HttpServletRequest request) {
		try {
			int lineId = EqualsUtil.integer(request, "lineId", "产线", true);
			List<JSONObject> list = service.findSN(lineId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 产线产线下的工位
	 * @return
	 */
	@RequestMapping("/findStation")
	public Rjson findStation(HttpServletRequest request) {
		try {
			int lineId = EqualsUtil.integer(request, "lineId", "产线", true);
			List<JSONObject> list = service.findStation(lineId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}

	}

}
