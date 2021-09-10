package com.skeqi.mes.controller.wms;

import java.io.IOException;
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
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.K3ImportNotifydetail;
import com.skeqi.mes.service.wms.K3RuKuDuiJieService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * k3入库对接
 *
 * @author yinp
 *
 */
@RestController
@RequestMapping(value = "wms/K3RuKuDuiJie", produces = MediaType.APPLICATION_JSON)
@Api(value = "k3出库对接", description = "k3入库对接", produces = MediaType.APPLICATION_JSON)
public class K3RuKuDuiJieController {

	@Autowired
	K3RuKuDuiJieService service;

	/**
	 * 查询所有产品
	 * @return
	 */
	@RequestMapping("/projectList")
	public Rjson projectList() {
		try {
			List<JSONObject> list = service.projectList();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除K3入库信息
	 *
	 * @param request
	 */
	@ApiOperation(value = "删除K3入库信息", notes = "删除K3入库信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "出库队列id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "deleteK3Number")
	@OptionalLog(module="仓管", module2="K3入库信息", method="删除K3入库信息")
	public synchronized Rjson deleteK3Number(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "出库队列id", true);
			boolean res = service.deleteK3Number(id);
			if (!res) {
				throw new Exception("操作失败，未知错误");
			}
			return Rjson.success("操作成功", true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 异常处理
	 *
	 * @param request
	 */
	@ApiOperation(value = "K3入库异常处理", notes = "K3入库异常处理")
	@ApiImplicitParams({ @ApiImplicitParam(name = "importId", value = "K3入库单号", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "exceptionHandle")
	@OptionalLog(module="仓管", module2="K3入库信息", method="K3入库异常处理")
	public synchronized Rjson exceptionHandle( HttpServletRequest request){
		try {
			String importId = EqualsUtil.string(request, "importId", "K3入库单号", true);

			boolean res = service.exceptionHandle(importId);
			if (!res) {
				throw new Exception("操作失败，未知错误");
			}
			return Rjson.success("操作成功", true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过库位id查询是加料操作还是入库操作
	 *
	 * @param request
	 */
	@ApiOperation(value = "通过库位id查询是加料操作还是入库操作", notes = "通过库位id查询是加料操作还是入库操作")
	@ApiImplicitParams({ @ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query") })
	@RequestMapping(value = "findMaterialNumberCount")
	public synchronized Rjson findMaterialNumberCount( HttpServletRequest request){
		try {
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);

			String res = service.findMaterialNumberCount(locationId);
			return Rjson.success("操作成功", res);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 抓取中间表数据
	 *
	 * @param request
	 */
	@ApiOperation(value = "抓取中间表数据", notes = "抓取中间表数据")
	@ApiImplicitParams({})
	@Transactional
	@RequestMapping(value = "getMiddleDataBase")
	public synchronized Rjson getMiddleDataBase(HttpServletRequest request){
		try {
			boolean res = service.getMiddleDataBase();
			if (!res) {
				throw new Exception("获取失败，未知异常");
			}
			return Rjson.success("获取成功", true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 提交啊 调用入库接口开始入库
	 *
	 * @param request
	 */
	@ApiOperation(value = "调用入库接口开始入库", notes = "调用入库接口开始入库")
	@ApiImplicitParams({ @ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "tray", value = "托盘码", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "Submission", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="K3入库信息", method="调用入库接口开始入库")
	public synchronized Rjson Submission(HttpServletRequest request){
		try {
			String materialJson = EqualsUtil.string(request, "materialJson", "入库数据", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户id", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);
			String tray = EqualsUtil.string(request, "trayCode", "托盘码", true);
			boolean res = service.Submission(tray, locationId, materialJson,userId);
			if (!res) {
				throw new Exception("提交失败");
			}
			return Rjson.success("提交成功", true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());

		}
	}

	/**
	 * 查询库位
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询库位", notes = "查询库位")
	@ApiImplicitParams({})
	@RequestMapping(value = "findLocation", method = RequestMethod.POST)
	public Rjson findLocation(HttpServletRequest request){
		try {
			List<JSONObject> list = service.findLocationList();
			return Rjson.success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询K3入库信息集合
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询K3入库信息集合", notes = "查询K3入库信息集合")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单据号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "当前页", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "每页数", required = true, paramType = "query") })
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request){
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", false);
			String materialName = EqualsUtil.string(request, "materialName", "物料名称", false);
			Integer projectId = EqualsUtil.integer(request, "projectId", "项目号", false);
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listNo", listNo);
			map.put("materialName", materialName);
			map.put("projectId", projectId);

			PageHelper.startPage(pageNumber, pageSize);
			List<K3ImportNotifydetail> lineList = service.findK3ImportNotifydetailList(map);
			PageInfo<K3ImportNotifydetail> pageInfo = new PageInfo<K3ImportNotifydetail>(lineList, 5);
			return Rjson.success("查询成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	@Transactional
//	@Scheduled(cron = "0/60 * * * * ?")
	public void k3 () {
		try {
			service.getMiddleDataBase();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

}
