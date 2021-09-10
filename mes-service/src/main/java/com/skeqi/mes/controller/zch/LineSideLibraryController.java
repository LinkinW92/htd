package com.skeqi.mes.controller.zch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.zch.LineSideLibraryService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 线边库管理
 * @author SKQ
 *
 */
@Controller
@RequestMapping(value = "lsl", produces = MediaType.APPLICATION_JSON)
@Api(value = "线边库管理", description = "线边库管理", produces = MediaType.APPLICATION_JSON)
public class LineSideLibraryController {
	@Autowired
	private LineSideLibraryService service;

	@RequestMapping(value = "/findRockList", method = RequestMethod.POST)
	@ApiOperation(value = "查询料架列表", notes = "查询料架列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "rackNo", value = "料架号", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "rockNo", value = "料格号", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson findRockList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer pageNum = 1;
            Integer pageSize = 10;
            if (map.get("pageNum") != null) {
                pageNum = Integer.parseInt(map.get("pageNum").toString());
            }
            if (map.get("pageSize") != null) {
                pageSize = Integer.parseInt(map.get("pageSize").toString());
            }

            PageHelper.startPage(pageNum, pageSize);
			list = service.findRockList(map);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addRock", method = RequestMethod.POST)
	@ApiOperation(value = "新增料架", notes = "新增料架")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "rackNo", value = "料架号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "rockNo", value = "料格号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "ptlNo", value = "PTL编号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "describe", value = "描述", required = false, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "物料", module2 = "料架定义", method= "新增料架")
	public Rjson addRock(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer result = service.addRock(map);
			if(result == -1) {
				return Rjson.error("料格号已存在");
			}
			if(result == -2) {
				return Rjson.error("PTL编号已存在");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editRock", method = RequestMethod.POST)
	@ApiOperation(value = "编辑料架", notes = "编辑料架")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "rackNo", value = "料架号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "rockNo", value = "料格号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "ptlNo", value = "PTL编号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "describe", value = "描述", required = false, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "物料", module2 = "料架定义", method= "编辑料架")
	public Rjson editRock(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer result = service.editRock(map);
			if(result == -1) {
				return Rjson.error("料格号已存在");
			}
			if(result == -2) {
				return Rjson.error("PTL编号已存在");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deleteRock", method = RequestMethod.POST)
	@ApiOperation(value = "删除料架", notes = "删除料架")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "id", required = false, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "物料", module2 = "料架定义", method= "删除料架")
	public Rjson deleteRock(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer result = service.deleteRock(map);
			if(result == -1) {
				return Rjson.error("该料架已配置，不可删除");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findRockConfigList", method = RequestMethod.POST)
	@ApiOperation(value = "查询线边库配置列表", notes = "查询线边库配置列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "versionId", value = "版本id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "stationId", value = "工位id", required = false, paramType = "query"),
	})
	@ResponseBody
	public Rjson findRockConfigList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer pageNum = 1;
			Integer pageSize = 10;
			if (map.get("pageNum") != null) {
				pageNum = Integer.parseInt(map.get("pageNum").toString());
			}
			if (map.get("pageSize") != null) {
				pageSize = Integer.parseInt(map.get("pageSize").toString());
			}

			PageHelper.startPage(pageNum, pageSize);
			list = service.findRockConfigList(map);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addRockConfig", method = RequestMethod.POST)
	@ApiOperation(value = "新增料架配置", notes = "新增料架配置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "versionId", value = "版本id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "stationId", value = "工位id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "rockId", value = "料架id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "materialNo", value = "物料编号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "materialName", value = "物料名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "capacity", value = "容量", required = true, paramType = "query"),
		@ApiImplicitParam(name = "safetyLevel", value = "安全水位", required = true, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "物料", module2 = "料架配置", method= "新增料架配置")
	public Rjson addRockConfig(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer result = service.addRockConfig(map);
			if(result == 0) {
				return Rjson.error("料架配置已存在");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editRockConfig", method = RequestMethod.POST)
	@ApiOperation(value = "编辑料架配置", notes = "编辑料架配置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "stationId", value = "工位id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "rockId", value = "料架id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "materialNo", value = "物料编号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "materialName", value = "物料名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "capacity", value = "容量", required = true, paramType = "query"),
		@ApiImplicitParam(name = "safetyLevel", value = "安全水位", required = true, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "物料", module2 = "料架配置", method= "编辑料架配置")
	public Rjson editRockConfig(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer result = service.editRockConfig(map);
			if(result == 0) {
				return Rjson.error("料架配置已存在");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deleteRockConfig", method = RequestMethod.POST)
	@ApiOperation(value = "删除料架配置", notes = "删除料架配置")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "id", required = false, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "物料", module2 = "料架配置", method= "删除料架配置")
	public Rjson deleteRockConfig(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			service.deleteRockConfig(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findInventoryList", method = RequestMethod.POST)
	@ApiOperation(value = "查询线边库库存列表", notes = "查询线边库库存列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query"),
		@ApiImplicitParam(name = "stationId", value = "工位id", required = false, paramType = "query"),
		@ApiImplicitParam(name = "productId", value = "产品id", required = false, paramType = "query"),
	})
	@ResponseBody
	public Rjson findInventoryList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer pageNum = 1;
            Integer pageSize = 10;
            if (map.get("pageNum") != null) {
                pageNum = Integer.parseInt(map.get("pageNum").toString());
            }
            if (map.get("pageSize") != null) {
                pageSize = Integer.parseInt(map.get("pageSize").toString());
            }

            PageHelper.startPage(pageNum, pageSize);
			list = service.findInventoryList(map);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addInventory", method = RequestMethod.POST)
	@ApiOperation(value = "新增线边库库存", notes = "新增线边库库存")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "stationId", value = "工位id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "productId", value = "产品id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "rockId", value = "货架id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "type", value = "条码类型 0 混合 1 批次 2 单个", required = true, paramType = "query"),
		@ApiImplicitParam(name = "materialNo", value = "物料编号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "materialName", value = "物料名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "batchCode", value = "批次号", required = false, paramType = "query"),
		@ApiImplicitParam(name = "materialCode", value = "物料号", required = false, paramType = "query"),
		@ApiImplicitParam(name = "quantity", value = "数量", required = true, paramType = "query"),
	})
	@ResponseBody
	public Rjson addInventory(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			service.addInventory(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editInventory", method = RequestMethod.POST)
	@ApiOperation(value = "编辑线边库库存", notes = "编辑线边库库存")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "id", required = false, paramType = "query"),
		@ApiImplicitParam(name = "quantity", value = "数量", required = false, paramType = "query"),
	})
	@ResponseBody
	public Rjson editInventory(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			service.editInventory(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findRockConfigVersionList", method = RequestMethod.POST)
	@ApiOperation(value = "查询料架配置版本列表", notes = "查询料架配置版本列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query"),
		@ApiImplicitParam(name = "productId", value = "产品id", required = false, paramType = "query"),
	})
	@ResponseBody
	public Rjson findRockConfigVersionList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer pageNum = 1;
            Integer pageSize = 10;
            if (map.get("pageNum") != null) {
                pageNum = Integer.parseInt(map.get("pageNum").toString());
            }
            if (map.get("pageSize") != null) {
                pageSize = Integer.parseInt(map.get("pageSize").toString());
            }

            PageHelper.startPage(pageNum, pageSize);
			list = service.findRockConfigVersionList(map);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addRockConfigVersion", method = RequestMethod.POST)
	@ApiOperation(value = "新增料架配置版本", notes = "新增料架配置版本")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "productId", value = "产品id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "version", value = "版本号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "describe", value = "描述", required = false, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "物料", module2 = "料架配置", method= "新增料架配置版本")
	public Rjson addRockConfigVersion(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			map.put("creator", ToolUtils.getCookieUserName(request));

			Integer result = service.addRockConfigVersion(map);
			if(result == 0) {
				return Rjson.error("版本号已存在");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editRockConfigVersion", method = RequestMethod.POST)
	@ApiOperation(value = "编辑料架配置版本", notes = "编辑料架配置版本")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "productId", value = "产品id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "version", value = "版本号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "describe", value = "描述", required = false, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "物料", module2 = "料架配置", method= "编辑料架配置版本")
	public Rjson editRockConfigVersion(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			map.put("reviser", ToolUtils.getCookieUserName(request));

			Integer result = service.editRockConfigVersion(map);
			if(result == 0) {
				return Rjson.error("版本号已存在");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deleteRockConfigVersion", method = RequestMethod.POST)
	@ApiOperation(value = "删除料架配置版本", notes = "删除料架配置版本")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "id", required = false, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "物料", module2 = "料架配置", method= "删除料架配置版本")
	public Rjson deleteRockConfigVersion(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			service.deleteRockConfigVersion(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/enableRockConfigVersion", method = RequestMethod.POST)
	@ApiOperation(value = "启用料架配置版本", notes = "启用料架配置版本")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "id", required = true, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	@OptionalLog(module = "物料", module2 = "料架配置", method= "启用料架配置版本")
	public Rjson enableRockConfigVersion(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			map.put("reviser", ToolUtils.getCookieUserName(request));

			service.enableRockConfigVersion(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findRockListFuzzy", method = RequestMethod.POST)
	@ApiOperation(value = "模糊查询料架列表", notes = "模糊查询料架列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "rockNo", value = "料架号", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson findRockListFuzzy(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			list = service.findRockListFuzzy(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findMaterialFuzzy", method = RequestMethod.POST)
	@ApiOperation(value = "模糊查询物料列表", notes = "模糊查询物料列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "materialName", value = "物料名称", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson findMaterialFuzzy(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			list = service.findMaterialFuzzy(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findMaterialQuestKanban", method = RequestMethod.POST)
	@ApiOperation(value = "查询物料请求看板", notes = "查询物料请求看板")
	@ApiImplicitParams({
	})
	@ResponseBody
	public JSONObject findMaterialQuestKanban(HttpServletRequest request) throws ServicesException {
		JSONObject jo = new JSONObject();
		JSONObject data = null;

		try {

			data = service.findMaterialQuestKanban();
			jo.put("code", 200);
			jo.put("data", data);

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			jo.put("code", 500);
		}
		return jo;
	}

	@RequestMapping(value = "/reclaimingPTL", method = RequestMethod.POST)
	@ApiOperation(value = "取料PTL亮灯", notes = "取料PTL亮灯")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "billNo", value = "单据号", required = true, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "emp", value = "员工号", required = true, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson reclaimingPTL(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			List<Map<String, Object>> list = service.reclaimingPTL(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findMateriaInstanceByMaterialNo", method = RequestMethod.POST)
	@ApiOperation(value = "捡料查询批次数量", notes = "捡料查询批次数量")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "materialNo", value = "物料编码", required = true, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson findMateriaInstanceByMaterialNo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Map<String, Object> resultMap = service.findMateriaInstanceByMaterialNo(map);

			return Rjson.success(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}
}
