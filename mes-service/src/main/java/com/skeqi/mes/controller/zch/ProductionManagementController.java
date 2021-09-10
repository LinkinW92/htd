package com.skeqi.mes.controller.zch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.zch.ProductionManagementService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 生产管理
 * @ClassName: ProductionController
 */
@Controller
@RequestMapping(value = "pro", produces = MediaType.APPLICATION_JSON)
@Api(value = "生产管理", description = "生产管理", produces = MediaType.APPLICATION_JSON)
public class ProductionManagementController {

	@Autowired
	ProductionManagementService service;

	@RequestMapping(value = "/findPlanList", method = RequestMethod.POST)
	@ApiOperation(value = "查询计划管理列表", notes = "查询计划管理列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "planNo", value = "计划单号", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "exOrderNo", value = "销售订单", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "projectNo", value = "项目号", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = "productId", value = "产品id", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson findPlanList(HttpServletRequest request) throws ServicesException {
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
			list = service.findPlanList(map);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addPlan", method = RequestMethod.POST)
	@ApiOperation(value = "新增计划", notes = "新增计划")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "planNo", value = "计划号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "exOrderNo", value = "订单号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "projectNo", value = "项目号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "projectName", value = "项目名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "productId", value = "产品id", required = false, paramType = "query"),
		@ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "quantity", value = "数量", required = true, paramType = "query"),
		@ApiImplicitParam(name = "customer", value = "客户", required = true, paramType = "query"),
		@ApiImplicitParam(name = "planStartDate", value = "计划开始日期", required = true, paramType = "query"),
		@ApiImplicitParam(name = "planEndDate", value = "计划结束日期", required = true, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "计划管理", method= "新增计划")
	public Rjson addPlan(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);
			if(StringUtils.isEmpty(map.get("productId"))) {
				map.put("productId", null);
			}

			Integer result = service.addPlan(map);
			if(result == -1) {
				return Rjson.error("计划号已存在");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editPlan", method = RequestMethod.POST)
	@ApiOperation(value = "编辑计划", notes = "编辑计划")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "计划id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "planNo", value = "计划号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "exOrderNo", value = "订单号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "projectNo", value = "项目号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "projectName", value = "项目名称", required = true, paramType = "query"),
		@ApiImplicitParam(name = "productId", value = "产品id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "quantity", value = "数量", required = true, paramType = "query"),
		@ApiImplicitParam(name = "customer", value = "客户", required = true, paramType = "query"),
		@ApiImplicitParam(name = "planStartDate", value = "计划开始日期", required = true, paramType = "query"),
		@ApiImplicitParam(name = "planEndDate", value = "计划结束日期", required = true, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "计划管理", method= "编辑计划")
	public Rjson editPlan(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);
			if(StringUtils.isEmpty(map.get("productId"))) {
				map.put("productId", null);
			}

			Integer result = service.editPlan(map);
			if(result == -1) {
				return Rjson.error("计划号已存在");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deletePlan", method = RequestMethod.POST)
	@ApiOperation(value = "删除计划", notes = "删除计划")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "计划id", required = false, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "计划管理", method= "删除计划")
	public Rjson deletePlan(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			service.deletePlan(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/mrpExecute", method = RequestMethod.POST)
	@ApiOperation(value = "执行MRP", notes = "执行MRP")
	@ApiImplicitParams({
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "计划管理", method= "执行MRP")
	public Rjson mrpExecute(HttpServletRequest request) throws ServicesException {

		try {
			service.mrpExecute();

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findSNList", method = RequestMethod.POST)
	@ApiOperation(value = "查询SN列表", notes = "查询SN列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "sn", value = "总成", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson findSNList(HttpServletRequest request) throws ServicesException {
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
			list = service.findSNList(map);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/transcodingSN", method = RequestMethod.POST)
	@ApiOperation(value = "SN转码", notes = "SN转码")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sn", value = "源码", required = true, paramType = "query"),
		@ApiImplicitParam(name = "newSn", value = "新码", required = true, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "SN管理", method= "SN转码")
	public Rjson transcodingSN(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer result = service.transcodingSN(map);
			switch (result) {
			case -1:
				return Rjson.error("未查到sn");
			case -2:
				return Rjson.error("目标总成已存在");
			default:
				break;
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/splitSN", method = RequestMethod.POST)
	@ApiOperation(value = "SN拆分", notes = "SN拆分")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sn", value = "源码", required = true, paramType = "query"),
		@ApiImplicitParam(name = "quantity", value = "拆分数量", required = true, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "SN管理", method= "SN拆分")
	public Rjson splitSN(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer result = service.splitSN(map);
			switch (result) {
			case -1:
				return Rjson.error("未查到sn");
			default:
				break;
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findContainerList", method = RequestMethod.POST)
	@ApiOperation(value = "查询容器定义列表", notes = "查询容器定义列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "containerName", value = "容器名称", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson findContainerList(HttpServletRequest request) throws ServicesException {
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
			list = service.findContainerList(map);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addContainer", method = RequestMethod.POST)
	@ApiOperation(value = "新增容器定义", notes = "新增容器定义")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "containerName", value = "容器名称", required = true, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "capacity", value = "容器大小", required = true, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "容器定义", method= "新增容器定义")
	public Rjson addContainer(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("containerName")) || StringUtils.isEmpty(map.get("capacity"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			Integer result = service.addContainer(map);
			if(result == -1) {
				return Rjson.error("该容器名称已存在");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editContainer", method = RequestMethod.POST)
	@ApiOperation(value = "编辑容器定义", notes = "编辑容器定义")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "容器id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "containerName", value = "容器名称", required = true, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "capacity", value = "容器大小", required = true, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "容器定义", method= "编辑容器定义")
	public Rjson editContainer(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("containerName")) || StringUtils.isEmpty(map.get("capacity"))
					|| StringUtils.isEmpty(map.get("ID"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			Integer result = service.editContainer(map);
			if(result == -1) {
				return Rjson.error("该容器名称已存在");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deleteContainer", method = RequestMethod.POST)
	@ApiOperation(value = "删除容器定义", notes = "删除容器定义")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "容器定义id", required = true, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "容器定义", method= "删除容器定义")
	public Rjson deleteContainer(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("ID"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			service.deleteContainer(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findPackList", method = RequestMethod.POST)
	@ApiOperation(value = "查询包装列表", notes = "查询包装列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "containerId", value = "容器类型id", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson findPackList(HttpServletRequest request) throws ServicesException {
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
			list = service.findPackList(map);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addPack", method = RequestMethod.POST)
	@ApiOperation(value = "新增包装", notes = "新增包装")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "containerId", value = "容器类型id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "containerNo", value = "容器号", required = true, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "packager", value = "打包人", required = true, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "包装管理", method= "新增包装")
	public Rjson addPack(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("containerId")) || StringUtils.isEmpty(map.get("containerNo"))
					|| StringUtils.isEmpty(map.get("lineId"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			Integer result = service.addPack(map);
			if(result == -1) {
				return Rjson.error("该容器号已存在");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editPack", method = RequestMethod.POST)
	@ApiOperation(value = "编辑包装", notes = "编辑包装")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "容器id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "containerId", value = "容器类型id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "containerNo", value = "容器号", required = true, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "packager", value = "打包人", required = true, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "lineId", value = "产线id", required = true, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "包装管理", method= "编辑包装")
	public Rjson editPack(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("containerId")) || StringUtils.isEmpty(map.get("containerNo"))
					|| StringUtils.isEmpty(map.get("lineId")) || StringUtils.isEmpty(map.get("ID"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			Integer result = service.editPack(map);
			if(result == -1) {
				return Rjson.error("该容器名称已存在");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deletePack", method = RequestMethod.POST)
	@ApiOperation(value = "删除包装", notes = "删除包装")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "容器定义id", required = true, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "包装管理", method= "删除包装")
	public Rjson deletePack(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("ID"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			Integer result = service.deletePack(map);
			if(result == -1) {
				return Rjson.error("该包装已打包总成");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findPackDetailList", method = RequestMethod.POST)
	@ApiOperation(value = "查询包装详情列表", notes = "查询包装详情列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "containerNo", value = "容器号", required = true, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson findPackDetailList(HttpServletRequest request) throws ServicesException {
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
			list = service.findPackDetailList(map);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addPackDetail", method = RequestMethod.POST)
	@ApiOperation(value = "新增包装详情", notes = "新增包装详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "containerNo", value = "容器号", required = true, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "sn", value = "打包人", required = true, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "包装管理", method= "新增包装详情")
	public Rjson addPackDetail(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("sn")) ) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			Integer result = service.addPackDetail(map);
			if(result == -1) {
				return Rjson.error("该sn已录入");
			}
			if(result == -2) {
				return Rjson.error("sn不存在，无法录入");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editPackDetail", method = RequestMethod.POST)
	@ApiOperation(value = "编辑包装详情", notes = "编辑包装详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "包装详情id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "containerNo", value = "容器号", required = true, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "sn", value = "打包人", required = true, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "包装管理", method= "编辑包装详情")
	public Rjson editPackDetail(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("sn")) || StringUtils.isEmpty(map.get("ID"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			Integer result = service.editPackDetail(map);
			if(result == -1) {
				return Rjson.error("该sn已录入");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deletePackDetail", method = RequestMethod.POST)
	@ApiOperation(value = "删除包装详情", notes = "删除包装详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "容器定义id", required = true, paramType = "query"),
	})
	@ResponseBody
	@OptionalLog(module = "计划", module2 = "包装管理", method= "删除包装详情")
	public Rjson deletePackDetail(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("ID"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			service.deletePackDetail(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}
}
