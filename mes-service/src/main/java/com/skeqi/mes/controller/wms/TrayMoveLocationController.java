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
import com.skeqi.mes.service.wms.TrayMoveLocationService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 托盘移库
 * @author yinp
 *
 */
@RestController
@RequestMapping(value = "wms/trayMoveLocation", produces = MediaType.APPLICATION_JSON)
@Api(value = "托盘移库", description = "托盘移库", produces = MediaType.APPLICATION_JSON)
public class TrayMoveLocationController {

	@Autowired
	TrayMoveLocationService service;

	/**
	 * 提交
	 */
	@Transactional
	@ApiOperation(value = "提交", notes = "提交")
	@ApiImplicitParams({ @ApiImplicitParam(name = "outLocation", value = "出库库位", required = true, paramType = "query"),
			@ApiImplicitParam(name = "enterLocation", value = "入库库位", required = true, paramType = "query")})
	@RequestMapping(value = "submit", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="托盘移库", method="提交")
	public Rjson submit(HttpServletRequest request){
		try {

			Integer outLocation = EqualsUtil.integer(request, "outLocation", "出库库位", true);
			Integer enterLocation = EqualsUtil.integer(request, "enterLocation", "入库库位", true);

			Map<String,Object> map = new HashMap<String, Object>();
			map.put("outLocation", outLocation);
			map.put("enterLocation", enterLocation);
			service.submit(new JSONObject(map));

	        return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			//手动回滚事务
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询仓库
	 */
	@ApiOperation(value = "查询仓库", notes = "查询仓库")
	@RequestMapping(value = "findWarehouseList", method = RequestMethod.POST)
	public Rjson findWarehouseList(HttpServletRequest request){
		try {
			List<JSONObject> list = service.findWarehouseList();

			 return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询区域
	 */
	@ApiOperation(value = " 查询区域", notes = " 查询区域")
	@RequestMapping(value = "findAreaList", method = RequestMethod.POST)
	public Rjson findAreaList(HttpServletRequest request) {
		try {
			Integer warehouseId = EqualsUtil.integer(request, "warehouseId", "仓库id", true);

			List<JSONObject> list = service.findAreaList(warehouseId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询库区
	 */
	@ApiOperation(value = " 查询库区", notes = " 查询库区")
	@RequestMapping(value = "findreservoirAreaList", method = RequestMethod.POST)
	public Rjson findreservoirAreaList(HttpServletRequest request){
		try {
			Integer areaId = EqualsUtil.integer(request, "areaId", "区域id", true);

			List<JSONObject> list = service.findreservoirAreaList(areaId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询库位
	 */
	@ApiOperation(value = " 查询库位", notes = " 查询库位")
	@ApiImplicitParams({ @ApiImplicitParam(name = "trayType", value = "可容纳类型", required = false, paramType = "query"),
		@ApiImplicitParam(name = "reservoirAreaId", value = "库区id", required = false, paramType = "query"),
		@ApiImplicitParam(name = "moveIn", value = "是查询出库库位还是查询入库库位", required = false, paramType = "query"),
	})
	@RequestMapping(value = "findlocationList", method = RequestMethod.POST)
	public Rjson findlocationList(HttpServletRequest request) {
		try {
			Integer trayType = EqualsUtil.integer(request, "trayType", "可容纳类型", true);
			Integer reservoirAreaId = EqualsUtil.integer(request, "reservoirAreaId", "库区id", true);
			Integer moveIn = EqualsUtil.integer(request, "moveIn", "出库库标识", true);

			JSONObject json = new JSONObject();
			json.put("trayType", trayType);
			json.put("reservoirAreaId", reservoirAreaId);
			json.put("moveIn", moveIn);
			List<JSONObject> list = service.findlocationList(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}
