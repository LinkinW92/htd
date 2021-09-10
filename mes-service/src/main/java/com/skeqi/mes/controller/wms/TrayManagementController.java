package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.List;

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
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.service.wms.TrayManagementService;
import com.skeqi.mes.util.ClientApiUseUtil;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 托盘管理
 * @author yinp
 *
 */
@RestController

@RequestMapping(value = "wms/trayManagement", produces = MediaType.APPLICATION_JSON)
@Api(value = "托盘管理", description = "托盘管理", produces = MediaType.APPLICATION_JSON)
public class TrayManagementController {

	@Autowired
	TrayManagementService service;

	/**
	 * 出料口回流放行
	 */
	@ApiOperation(value = "出料口回流放行", notes = "出料口回流放行")
	@RequestMapping(value = "materialForwardOrBackWard", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="托盘管理", method="出料口回流放行")
	public Rjson materialForwardOrBackWard(HttpServletRequest request) {
		try {

			JSONObject json = new JSONObject();
			//接口名称
			json.put("methodName", "MaterialForwardOrBackWard");
			//直流回流标记
			json.put("forwardOrBackWard", "1");

			// 调用客户端接口
			json = ClientApiUseUtil.UseApi(json);

			if (!json.getBoolean("remark")) {
				throw new Exception("调用客户端接口出现错误");
			}

			return Rjson.success("操作成功，请按放行按钮", null);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 入库托盘放行
	 */
	@ApiOperation(value = "入库托盘放行", notes = "入库托盘放行")
	@RequestMapping(value = "trayPass", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="托盘管理", method="入库托盘放行")
	public Rjson trayPass(HttpServletRequest request) {
		try {

			JSONObject json = new JSONObject();
			//接口名称
			json.put("methodName", "TrayPass");

			// 调用客户端接口
			json = ClientApiUseUtil.UseApi(json);

			if (!json.getBoolean("remark")) {
				throw new Exception("调用客户端接口出现错误");
			}
			return Rjson.success("操作成功，请按放行按钮");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 移出空托盘
	 */
	@Transactional
	@ApiOperation(value = "移出空托盘", notes = "移出空托盘")
	@ApiImplicitParams({ @ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query")})
	@RequestMapping(value = "moveOutNullTray", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="托盘管理", method="移出空托盘")
	public Rjson moveOutNullTray(HttpServletRequest request) {
		try {
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);

			service.moveOutNullTray(locationId);

			return Rjson.success("操作成功，等待堆垛机运行", null);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			//手动回滚事务
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 放入空托盘
	 */
	@Transactional
	@ApiOperation(value = "放入空托盘", notes = "放入空托盘")
	@ApiImplicitParams({ @ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "tray", value = "托盘码", required = true, paramType = "query"),

	})
	@RequestMapping(value = "putInNullTray", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="托盘管理", method="放入空托盘")
	public Rjson putInNullTray(HttpServletRequest request) {
		try {
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);
			String tray = EqualsUtil.string(request, "tray", "托盘码", true);

			JSONObject json = new JSONObject();
			json.put("locationId", locationId);
			json.put("tray", tray);
			service.putInNullTray(json);

			return Rjson.success("操作成功，等待堆垛机运行", null);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			//手动回滚事务
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过库位id查询物料库存
	 */
	@ApiOperation(value = "通过库位id查询物料库存", notes = "通过库位id查询物料库存")
	@ApiImplicitParams({ @ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query")})
	@RequestMapping(value = "findMaterialNumberList", method = RequestMethod.POST)
	public Rjson findMaterialNumberList(HttpServletRequest request) {
		try {
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);

			List<JSONObject> list = service.findMaterialNumberList(locationId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询库位id+name集合
	 * @throws IOException
	 */
	@ApiOperation(value = "查询库位id+name集合", notes = "查询库位id+name集合")
	@RequestMapping(value = "findLocationListIdAndName", method = RequestMethod.POST)
	public Rjson findLocationListIdAndName(HttpServletRequest request) {
		try {
			List<CWmsLocationT> list = service.findLocationListIdAndName();

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
	@ApiOperation(value = "查询库位", notes = "查询库位")
	@ApiImplicitParams({ @ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "state", value = "库位状态", required = false, paramType = "query"),
		@ApiImplicitParam(name = "tray", value = "托盘码", required = false, paramType = "query"),

	})
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request) {
		try {
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", false);
			String tray = EqualsUtil.string(request, "tray", "托盘码", false);
			Integer state = EqualsUtil.integer(request, "state", "库位状态", false);


			Integer pageNumber = EqualsUtil.pageNumber(request);

			JSONObject json = new JSONObject();
			json.put("locationId", locationId);
			json.put("state", state);
			json.put("tray", tray);

			PageHelper.startPage(pageNumber, 10);
			List<JSONObject> lineList = service.findList(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(lineList, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}
