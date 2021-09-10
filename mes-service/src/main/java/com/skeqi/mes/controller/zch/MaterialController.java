package com.skeqi.mes.controller.zch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.service.wf.ProductionPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.yin.MaterialService;
import com.skeqi.mes.service.zch.EventService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 物料首页
 * @author SKQ
 *
 */
@Controller
@RequestMapping(value = "mat", produces = MediaType.APPLICATION_JSON)
@Api(value = "物料首页", description = "物料首页", produces = MediaType.APPLICATION_JSON)
public class MaterialController {

	@Autowired
	MaterialService materialService;

	@Autowired
	ProductionPageService productionPageService;
	@Autowired
	EventService eventService;

	@RequestMapping(value = "/findMaterialTypeStatistics", method = RequestMethod.POST)
	@ApiOperation(value = "物料分类数量统计", notes = "物料分类数量统计")
	@ResponseBody
	public Rjson findMaterialTypeStatistics(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;

		try {

			list = materialService.findMaterialTypeStatistics();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findMaterialNumStatistics", method = RequestMethod.POST)
	@ApiOperation(value = "物料实例分类数量统计", notes = "物料实例分类数量统计")
	@ResponseBody
	public Rjson findMaterialNumStatistics(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;

		try {

			list = materialService.findMaterialNumStatistics();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findEventLately", method = RequestMethod.POST)
	@ApiOperation(value = "获取最近事件", notes = "获取最近事件")
	@ResponseBody
	public Rjson findEventLately(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;

		try {

			list = eventService.findMaterialEventLately();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findMaterialEventStatisticsLately", method = RequestMethod.POST)
	@ApiOperation(value = "最近事件统计(最近7天)", notes = "最近事件统计(最近7天)")
	@ResponseBody
	public Rjson findMaterialEventStatisticsLately(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = null;

		try {

			map = eventService.findMaterialEventStatisticsLately();

			return Rjson.success(map);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/finishedProduct", method = RequestMethod.POST)
	@ApiOperation(value = "最近产品统计(最近7天)", notes = "最近产品统计(最近7天)")
	@ResponseBody
	public Rjson finishedProduct(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;

		try {

			list = productionPageService.finishedProduct();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findMaterialList", method = RequestMethod.POST)
	@ApiOperation(value = "查询物料列表", notes = "查询物料列表")
	@ResponseBody
	public Rjson findMaterialList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			list = materialService.findMaterialList(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}
}
