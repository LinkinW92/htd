package com.skeqi.mes.controller.wms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wms.InventoryDetailT;
import com.skeqi.mes.pojo.wms.InventoryT;
import com.skeqi.mes.service.wms.StockInventoryService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 库存盘点
 *
 * @author yinp
 * @date 2020年3月9日
 *
 */
@RestController
@RequestMapping(value = "wms/stockInventory", produces = MediaType.APPLICATION_JSON)
@Api(value = "库存盘点", description = "库存盘点", produces = MediaType.APPLICATION_JSON)
public class StockInventoryController {

	@Autowired
	StockInventoryService service;

	/**
	 * 查询是否有盘点记录未审批或者未执行
	 */
	@Transactional
	@ApiOperation(value = "查询是否有盘点记录未审批或者未执行", notes = "查询是否有盘点记录未审批或者未执行")
	@RequestMapping(value = "queryWhetherThereIsInventory", method = RequestMethod.POST)
	public Rjson queryWhetherThereIsInventory(HttpServletRequest request) {
		try {
			boolean res = service.queryWhetherThereIsInventory();

			return Rjson.success("查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 刷新数据
	 *
	 * @param request
	 */
	@Transactional
	@ApiOperation(value = "刷新数据", notes = "刷新数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query") })
	@RequestMapping(value = "shuaXinData", method = RequestMethod.POST)
	public Rjson shuaXinData(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			service.implement(id);
			return Rjson.success("执行成功", true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询详情 list
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询详情 list", notes = "查询详情 list")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findInventoryDetailList", method = RequestMethod.POST)
	public Rjson findInventoryDetailList(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true); // 单号

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listNo", listNo);
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			PageHelper.startPage(pageNumber, pageSize);
			List<InventoryDetailT> list = service.findInventoryDetailList(map);
			PageInfo<InventoryDetailT> pageInfo = new PageInfo<InventoryDetailT>(list, 5);

			return Rjson.success("查询成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询list
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询 list", notes = "查询 list")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", false); // 单号
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listNo", listNo);

			PageHelper.startPage(pageNumber, pageSize);
			List<InventoryT> list = service.findInventoryList(map);
			PageInfo<InventoryT> pageInfo = new PageInfo<InventoryT>(list, 5);

			return Rjson.success("查询成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增盘点
	 *
	 * @param request
	 */
	@Transactional
	@ApiOperation(value = "新增盘点", notes = "新增盘点")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="库存盘点", method="新增盘点")
	public Rjson add(HttpServletRequest request) {
		try {
			JSONObject json = new JSONObject();
			String listNo = EqualsUtil.string(request, "listNo", "单号", false);
			Integer userId = EqualsUtil.integer(request, "userId", "用户id", true);
			Integer materialId = EqualsUtil.integer(request, "materialId", "物料id", true);
			Integer number = EqualsUtil.integer(request, "number", "盘点数量", true);
			Integer stock = EqualsUtil.integer(request, "stock", "库存数量", true);
			Integer differenceNumber = EqualsUtil.integer(request, "differenceNumber", "差异数量", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目id", true);
			String trayCode = EqualsUtil.string(request, "trayCode", "托盘码", true);

			json.put("listNo", listNo);
			json.put("userId", userId);
			json.put("materialId", materialId);
			json.put("number", number);
			json.put("stock", stock);
			json.put("differenceNumber", differenceNumber);
			json.put("locationId", locationId);
			json.put("projectId", projectId);
			json.put("trayCode", trayCode);

			boolean res = service.addInventory(json);
			if (res) {
				return Rjson.success("提交成功", true);
			} else {
				throw new Exception("提交失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询库存
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询库存", notes = "查询 库存")
	@ApiImplicitParams({ @ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "projectId", value = "项目id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "materialId", value = "审批人", required = false, paramType = "query") })
	@RequestMapping(value = "findMaterialNumber", method = RequestMethod.POST)
	public Rjson findMaterialNumber(HttpServletRequest request) {

		try {

			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目id", true);
			Integer materialId = EqualsUtil.integer(request, "materialId", "物料id", true);

			Integer number = service.findMaterialNumber(locationId, materialId, projectId);

			return Rjson.success(number);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有仓库
	 *
	 * @return
	 */
	@RequestMapping(value = "findWarehouseAll")
	public Rjson findWarehouseAll(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.findWarehouseAll();
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
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findAreaAllByWarehouseId")
	public Rjson findAreaAllByWarehouseId(HttpServletRequest request) {
		try {
			Integer warehouseId = EqualsUtil.integer(request, "warehouseId", "仓库id", true);
			List<JSONObject> list = service.findAreaAllByWarehouseId(warehouseId);
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
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findReservoirAreaAllByAeraId")
	public Rjson findReservoirAreaAllByAeraId(HttpServletRequest request) {
		try {
			Integer areaId = EqualsUtil.integer(request, "areaId", "区域id", true);
			List<JSONObject> list = service.findReservoirAreaAllByAeraId(areaId);
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
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findLocationAllByReservoirAreaId")
	public Rjson findLocationAllByReservoirAreaId(HttpServletRequest request) {
		try {
			Integer reservoirAreaId = EqualsUtil.integer(request, "reservoirAreaId", "库区id", true);
			List<JSONObject> list = service.findLocationAllByReservoirAreaId(reservoirAreaId);
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
	@RequestMapping(value = "findMaterialAll")
	public Rjson findMaterialAll(HttpServletRequest request) {
		try {
			String materialName = EqualsUtil.string(request, "materialName", "物料名称", true);
			List<JSONObject> list = service.findMaterialAll(materialName);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有项目
	 *
	 * @return
	 */
	@RequestMapping(value = "findProjectAll")
	public Rjson findProjectAll(HttpServletRequest request) {
		try {
			String projectName = EqualsUtil.string(request, "projectName", "项目名称", true);
			List<JSONObject> list = service.findProjectAll(projectName);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过单号查询盘点详情
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findInventoryDetailByListNo")
	public Rjson findInventoryDetailByListNo(HttpServletRequest request) {
		try {

			int pageNumber = EqualsUtil.pageNumber(request);
			int pageSize = EqualsUtil.pageSize(request);
			String listNo = EqualsUtil.string(request, "listNo", "单哈", true);

			PageHelper.startPage(pageNumber, pageSize);
			List<JSONObject> list = service.findInventoryDetailByListNo(listNo);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}
