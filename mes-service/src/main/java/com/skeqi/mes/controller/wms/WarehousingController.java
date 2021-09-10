package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.service.wms.ApprovalService;
import com.skeqi.mes.service.wms.WarehousingService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 入库管理
 *
 * @author yinp
 * @date 2020年3月21日
 *
 */
@RestController
@RequestMapping(value = "wms/warehousing", produces = MediaType.APPLICATION_JSON)
@Api(value = "入库管理", description = "入库管理", produces = MediaType.APPLICATION_JSON)
public class WarehousingController {

	@Autowired
	WarehousingService service;

	@Autowired
	ApprovalService aService;

	/**
	 * 查询所有仓库
	 *
	 * @return
	 */
	@RequestMapping(value = "findWarehouse", method = RequestMethod.POST)
	public Rjson findWarehouse(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.findWarehouse();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过仓库id查询区域
	 *
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "findArea", method = RequestMethod.POST)
	public Rjson findArea(HttpServletRequest request) {
		try {
			Integer warehouseId = EqualsUtil.integer(request, "warehouseId", "仓库id", true);
			List<JSONObject> list = service.findArea(warehouseId);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过区域id查询库区
	 *
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "findReservoirArea", method = RequestMethod.POST)
	public Rjson findReservoirArea(HttpServletRequest request) {
		try {
			Integer areaId = EqualsUtil.integer(request, "areaId", "区域id", true);
			List<JSONObject> list = service.findReservoirArea(areaId);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过库区id查询库位
	 *
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "findLocation", method = RequestMethod.POST)
	public Rjson findLocation(HttpServletRequest request) {
		try {
			Integer reservoirAreaId = EqualsUtil.integer(request, "reservoirAreaId", "库区id", true);
			List<JSONObject> list = service.findLocation(reservoirAreaId);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有产品
	 *
	 * @return
	 */
	@RequestMapping(value = "findProject", method = RequestMethod.POST)
	public Rjson findProject(HttpServletRequest request) {
		try {
			String projectName = EqualsUtil.string(request, "projectName", "项目名称", true);
			List<JSONObject> list = service.findProject(projectName);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有物料
	 *
	 * @return
	 */
	@RequestMapping(value = "findMaterial", method = RequestMethod.POST)
	public Rjson findMaterial(HttpServletRequest request) {
		try {
			String materialName = EqualsUtil.string(request, "materialName", "物料名称", true);
			List<JSONObject> list = service.findMaterial(materialName);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询库存详情
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询库存详情", notes = "查询库存详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findStorageDetail", method = RequestMethod.POST)
	public Rjson findStorageDetail(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			Integer pageNumber = EqualsUtil.pageNumber(request);

			PageHelper.startPage(pageNumber, 8);
			List<Map<String, Object>> list = service.findPStorageDetailSum(listNo);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list, 5);
			if (pageInfo.getList().size() == 0) {
				PageHelper.startPage(pageNumber, 8);
				list = service.findRStorageDetailSum(listNo);
				pageInfo = new PageInfo<Map<String, Object>>(list, 5);
			}

			return Rjson.success("查询成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * XT355_356_357 执行
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@Transactional
	@ApiOperation(value = "XT355_356_357 执行", notes = "XT355_356_357 执行")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query") })
	@RequestMapping(value = "zhixingXT355_356_357", method = RequestMethod.POST)

	public Rjson zhixingXT355_356_357(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {

		// 接收参数
		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");
		// 将接受到的字符串转换为json数组
		JSONObject json = new JSONObject();

		/*
		 * JSONObject result = new JSONObject(); JSONObject data = new JSONObject();
		 */
		CWmsStorageDetailT dx = new CWmsStorageDetailT();

		try {
			if (request.getParameter("listNo") != null && !request.getParameter("listNo").equals("")
					&& !request.getParameter("listNo").equals("0")) {
				try {
					// json.put("listNo",request.getParameter("listNo"));

				} catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			}

			boolean res = service.zhixingXT355_356_357(json);
			if (res) {
				/*
				 * data.put("msg", "操作成功！"); data.put("code", true); result.put("result", data);
				 */
				return Rjson.success("操作成功", null);
			} else {
				/*
				 * data.put("msg", "操作失败！"); data.put("code",false); result.put("result", data);
				 */
				return Rjson.error("操作失败");
			}

		} catch (Exception e) {
			/*
			 * e.printStackTrace(); data.put("msg", e.getMessage()); data.put("code",
			 * false); result.put("result", data);
			 */
			e.printStackTrace();
			return Rjson.error(e.getMessage());
			// 手动回滚事务
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} /*
			 * finally { response.getWriter().append(result.toJSONString()); }
			 */
	}

	/**
	 * 撤销领用入库单据
	 *
	 * @param request
	 */
	@Transactional
//	@ApiOperation(value = "撤销领用入库单据", notes = "撤销领用入库单据")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query") })
	@RequestMapping(value = "revoke", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="入库管理", method="撤销领用入库单据")
	public Rjson revoke(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户Id ", true);
			boolean res = service.revoke(listNo,userId);
			if (res) {
				return Rjson.success("操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
		return null;

	}

	/**
	 * 新增入库记录
	 *
	 * @param request
	 */
	@Transactional
	@ApiOperation(value = "新增入库记录", notes = "新增入库记录")
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "listNo", value = "单号", required = false, paramType = "query"),
			@ApiImplicitParam(name = "warehouseId", value = "仓库id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "materialId", value = "物料id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "materialNumber", value = "入库数量", required = false, paramType = "query"),
			@ApiImplicitParam(name = "areaId", value = " 区域id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "resevoirAreaId", value = "库区id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationId", value = "库位id", required = false, paramType = "query") })
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="入库管理", method="新增入库记录")
	public Rjson add(HttpServletRequest request) {
		try {
			Integer userId = EqualsUtil.integer(request, "userId", "用户Id ", true);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目id", true);
			String listNo = EqualsUtil.string(request, "listNo", "单号", false);
			Integer warehouseId = EqualsUtil.integer(request, "warehouseId", "仓库id", true);
			Integer materialId = EqualsUtil.integer(request, "materialId", "物料id", true);
			Integer materialNumber = EqualsUtil.integer(request, "materialNumber", "入库数量", true);
			Integer areaId = EqualsUtil.integer(request, "areaId", "区域id", true);
			Integer resevoirAreaId = EqualsUtil.integer(request, "reservoirAreaId", "库区id", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);

			boolean res = service.add(userId,listNo, projectId, warehouseId, areaId, resevoirAreaId, locationId, materialId,
					materialNumber);
			if (res) {
				return Rjson.success("操作成功", true);
			} else {
				return Rjson.error("操作失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询审批记录集合
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询审批记录集合", notes = "查询审批记录集合")
	@ApiImplicitParams({})
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request) {
		try {
			Integer userId = EqualsUtil.integer(request, "userId", "用户Id ", true);
			Integer pageNumber = EqualsUtil.pageNumber(request);

			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("pageNumber", pageNumber);

			PageInfo<CWmsApprovalT> pageInfo = service.queryStockInInformation(json);
			return Rjson.success("查询成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询可入库的库位
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询可入库的库位", notes = "查询可入库的库位")
	@ApiImplicitParams({ @ApiImplicitParam(name = "raName", value = "库区id", required = true, paramType = "query") })
	@RequestMapping(value = "findLocationIdAndName", method = RequestMethod.POST)
	public Rjson findLocationIdAndName(HttpServletRequest request) {
		try {
			Integer raId = null;
			try {
				raId = Integer.parseInt(request.getParameter("raId"));
			} catch (NullPointerException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'库区id'不能为空");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'库区id'参数类型有误");
			}

			return Rjson.success("查询成功", service.findLocationIdAndName(raId));
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
