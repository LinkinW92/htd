package com.skeqi.mes.controller.zch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.zch.ModuleServerService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "mod", produces = MediaType.APPLICATION_JSON)
@Api(value = "模组服务端接口", description = "模组服务端接口", produces = MediaType.APPLICATION_JSON)
public class ModuleServerController {

	@Autowired
	ModuleServerService service;

	@RequestMapping(value = "/findDataConfList", method = RequestMethod.POST)
	@ApiOperation(value = "查询数据收束配置列表", notes = "查询数据收束配置列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "lineName", value = "产线名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "stationName", value = "工位名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "productionName", value = "产品名称", required = false, paramType = "query"),
			 })
	@ResponseBody
	public Rjson findDataConfList(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;

		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("lineName")) || StringUtils.isEmpty(map.get("stationName")) || StringUtils.isEmpty(map.get("productionName"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			list=service.findDataConfList(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addDataConf", method = RequestMethod.POST)
	@ApiOperation(value = "新增数据收束配置", notes = "新增数据收束配置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "lineName", value = "产线名称", required = false, paramType = "query"),
		@ApiImplicitParam(name = "stationName", value = "工位名称", required = false, paramType = "query"),
		@ApiImplicitParam(name = "productionName", value = "产品名称", required = false, paramType = "query"),
		@ApiImplicitParam(name = "keyName", value = "参数名", required = false, paramType = "query"),
		@ApiImplicitParam(name = "PLCAddress", value = "PLC地址", required = false, paramType = "query"),
		@ApiImplicitParam(name = "unit", value = "单位", required = false, paramType = "query"),
		@ApiImplicitParam(name = "keyCode", value = "参数编码", required = false, paramType = "query"),
		@ApiImplicitParam(name = "device", value = "设备", required = false, paramType = "query"),
		@ApiImplicitParam(name = "upperLimit", value = "上限", required = false, paramType = "query"),
		@ApiImplicitParam(name = "lowerLimit", value = "下限", required = false, paramType = "query"),
		@ApiImplicitParam(name = "describe", value = "参数描述", required = false, paramType = "query"),
		@ApiImplicitParam(name = "defaultValue", value = "默认值", required = false, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="数据收束配置", method="新增数据收束配置")
	public Rjson addDataConf(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("lineName")) || StringUtils.isEmpty(map.get("stationName")) || StringUtils.isEmpty(map.get("productionName"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			service.addDataConf(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editDataConf", method = RequestMethod.POST)
	@ApiOperation(value = "编辑数据收束配置", notes = "编辑数据收束配置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "数据收束配置id", required = false, paramType = "query"),
		@ApiImplicitParam(name = "keyName", value = "参数名", required = false, paramType = "query"),
		@ApiImplicitParam(name = "PLCAddress", value = "PLC地址", required = false, paramType = "query"),
		@ApiImplicitParam(name = "unit", value = "单位", required = false, paramType = "query"),
		@ApiImplicitParam(name = "keyCode", value = "参数编码", required = false, paramType = "query"),
		@ApiImplicitParam(name = "device", value = "设备", required = false, paramType = "query"),
		@ApiImplicitParam(name = "upperLimit", value = "上限", required = false, paramType = "query"),
		@ApiImplicitParam(name = "lowerLimit", value = "下限", required = false, paramType = "query"),
		@ApiImplicitParam(name = "describe", value = "参数描述", required = false, paramType = "query"),
		@ApiImplicitParam(name = "defaultValue", value = "默认值", required = false, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="数据收束配置", method="编辑数据收束配置")
	public Rjson editDataConf(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			service.editDataConf(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deleteDataConf", method = RequestMethod.POST)
	@ApiOperation(value = "删除数据收束配置", notes = "删除数据收束配置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "数据收束配置id", required = false, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="数据收束配置", method="删除数据收束配置")
	public Rjson deleteDataConf(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			service.deleteDataConf(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findMesInterfaceTypeList", method = RequestMethod.POST)
	@ApiOperation(value = "查询MES接口类型列表", notes = "查询MES接口类型列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "lineName", value = "产线名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "stationName", value = "工位名称", required = true, paramType = "query"),
		})
	@ResponseBody
	public Rjson findMesInterfaceTypeList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();
		try {
			map = ToolUtils.getParameterMap(request);

			list=service.findMesInterfaceTypeList(map);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findMesInterfaceParaList", method = RequestMethod.POST)
	@ApiOperation(value = "查询MES接口参数列表", notes = "查询MES接口参数列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "类型id", required = true, paramType = "query"),
	})
	@ResponseBody
	public Rjson findMesInterfaceParaList(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("typeId"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			list = service.findMesInterfaceParaList(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addMesInterfacePara", method = RequestMethod.POST)
	@ApiOperation(value = "新增MES接口参数", notes = "新增MES接口参数")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "类型id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "key", value = "参数名", required = true, paramType = "query"),
		@ApiImplicitParam(name = "value", value = "参数值", required = true, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="接口参数配置", method="新增MES接口参数")
	public Rjson addMesInterfacePara(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("typeId")) || StringUtils.isEmpty(map.get("key")) || StringUtils.isEmpty(map.get("value"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			service.addMesInterfacePara(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editMesInterfacePara", method = RequestMethod.POST)
	@ApiOperation(value = "编辑MES接口参数", notes = "编辑MES接口参数")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "参数id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "key", value = "参数名", required = true, paramType = "query"),
		@ApiImplicitParam(name = "value", value = "参数值", required = true, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="接口参数配置", method="编辑MES接口参数")
	public Rjson editMesInterfacePara(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("ID")) || StringUtils.isEmpty(map.get("key")) || StringUtils.isEmpty(map.get("value"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			service.editMesInterfacePara(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deleteMesInterfacePara", method = RequestMethod.POST)
	@ApiOperation(value = "删除MES接口参数", notes = "删除MES接口参数")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "参数id", required = true, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="接口参数配置", method="删除MES接口参数")
	public Rjson deleteMesInterfacePara(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("ID"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			service.deleteMesInterfacePara(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findDataCollectList", method = RequestMethod.POST)
	@ApiOperation(value = "查询数据收束列表", notes = "查询数据收束列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "lineName", value = "产线名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "productName", value = "产品名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sn", value = "条码", required = false, paramType = "query"),
		@ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, paramType = "query"),
		@ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query"),
	})
	@ResponseBody
	public Rjson findDataCollectList(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("lineName")) || StringUtils.isEmpty(map.get("productName"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			list = service.findDataCollectList(map);

			list = Rjson.getListByFormatTime(list);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findDataCollectParaList", method = RequestMethod.POST)
	@ApiOperation(value = "查询数据收束参数列表", notes = "查询数据收束参数列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "lineName", value = "产线名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "productName", value = "产品名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sn", value = "条码", required = true, paramType = "query"),
	})
	@ResponseBody
	public Rjson findDataCollectParaList(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("lineName")) || StringUtils.isEmpty(map.get("productName"))
					|| StringUtils.isEmpty(map.get("sn"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			list = service.findDataCollectParaList(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findStationList", method = RequestMethod.POST)
	@ApiOperation(value = "查询数据收束参数列表", notes = "查询数据收束参数列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "lineName", value = "产线名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "productName", value = "产品名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sn", value = "条码", required = true, paramType = "query"),
	})
	@ResponseBody
	public Rjson findStationList(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("lineName")) || StringUtils.isEmpty(map.get("productName"))
					|| StringUtils.isEmpty(map.get("sn"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			list = service.findStationList(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/exportDataCollectParaList", method = RequestMethod.POST)
	@ApiOperation(value = "导出数据收束参数列表", notes = "导出数据收束参数列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "lineName", value = "产线名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "productName", value = "产品名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sn", value = "条码", required = false, paramType = "query"),
	})
	@ResponseBody
	public Rjson exportDataCollectParaList(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("lineName")) || StringUtils.isEmpty(map.get("productName"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			list = service.exportDataCollectParaList(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}
}
