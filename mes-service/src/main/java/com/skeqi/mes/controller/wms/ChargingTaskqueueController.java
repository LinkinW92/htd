package com.skeqi.mes.controller.wms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.service.wms.ChargingTaskqueueService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author yinp
 * @explain 加料队列
 * @date 2020-11-23
 */
@RestController
@RequestMapping(value = "wms/chargingTaskqueue", produces = MediaType.APPLICATION_JSON)
@Api(value = "加料队列", description = "加料队列", produces = MediaType.APPLICATION_JSON)
public class ChargingTaskqueueController {

	private String url = "http://127.0.0.1/FISClientService.asmx?wsdl";

	@Autowired
	ChargingTaskqueueService service;

	/**
	 * 查询条码
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findBarCode")
	public Rjson findBarCode(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			int materialId = EqualsUtil.integer(request, "materialId", "物料id", true);

			List<JSONObject> list = service.findBarCode(listNo, materialId);
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 加料不出库
	 *
	 * @param request
	 */
	@ApiOperation(value = "加料不出库", notes = "加料不出库")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "inTaskqueueId", value = "入库队列id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "buchuku", method = RequestMethod.POST)
	@OptionalLog(module = "仓管", module2 = "加料队列", method = "加料不出库")
	public Rjson buchuku(HttpServletRequest request) {
		try {
			Integer inTaskqueueId = EqualsUtil.integer(request, "inTaskqueueId", "入库队列id", true);

			boolean res = service.buchuku(inTaskqueueId);
			if (!res) {
				return Rjson.error("操作失败");
			}
			return Rjson.success(true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 调用物料直流回流接口
	 *
	 * @param request
	 */
	@ApiOperation(value = "调用物料直流回流接口", notes = "调用物料直流回流接口")
	@ApiImplicitParams({})
	@RequestMapping(value = "MaterialForwardOrBackWard", method = RequestMethod.POST)
	@OptionalLog(module = "仓管", module2 = "加料队列", method = "调用物料直流回流接口")
	public Rjson MaterialForwardOrBackWard(HttpServletRequest request) {
		try {
			String namespace = "http://tempuri.org/";
			String methodName = "MaterialForwardOrBackWard";
			String soapActionURI = "http://tempuri.org/MaterialForwardOrBackWard";

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("forwardOrBackWard", 1);

			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(soapActionURI);
			call.setOperationName(new QName(namespace, methodName));
			call.addParameter(new QName(namespace, "str"), XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);

			Object obj = null;

			try {
				// 开始调用服务
				obj = call.invoke(new String[] { JSON.toJSONString(map) });
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("与客户端通讯失败");
			}

			JSONObject jo = JSON.parseObject(obj.toString());

			if (jo.getBoolean("remark") == null) {
				throw new Exception("客户端返回结果异常");
			}
			return Rjson.success("执行成功，请按回流放行按钮", true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 调用出库接口
	 *
	 * @param request
	 */
	@ApiOperation(value = "调用出库接口", notes = "调用出库接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "入库队列id", required = true, paramType = "query") })
	@RequestMapping(value = "MaterialOutbound", method = RequestMethod.POST)
	@OptionalLog(module = "仓管", module2 = "加料队列", method = "调用出库接口")
	public Rjson MaterialOutbound(HttpServletRequest request) {
		try {
			Integer inTaskqueueId = EqualsUtil.integer(request, "inTaskqueueId", "入库队列id", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户Id", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", inTaskqueueId);
			map.put("userId", userId);

			List<CWmsInTaskqueueT> list = service.findInTaskqueueTList(map);

			if (list.size() != 1) {
				throw new Exception("查询入库队列失败！");
			}

			CWmsInTaskqueueT dx = list.get(0);

			String namespace = "http://tempuri.org/";
			String methodName = "MaterialOutbound";
			String soapActionURI = "http://tempuri.org/MaterialOutbound";

			map.put("X", dx.getLocation().getLocationX());
			map.put("Y", dx.getLocation().getLocationY());
			map.put("Z", dx.getLocation().getLocationZ());
			map.put("trayCode", 1);
			map.put("list", 1);

			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(soapActionURI);
			call.setOperationName(new QName(namespace, methodName));
			call.addParameter(new QName(namespace, "str"), XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			// 开始调用服务
			Object obj = null;

			try {
				obj = call.invoke(new String[] { JSON.toJSONString(map) });
			} catch (Exception e) {
				throw new Exception("与客户端通讯失败");
			}

			JSONObject jo = JSON.parseObject(obj.toString());

			if (jo.getBoolean("remark") == null) {
				throw new Exception("客户端返回结果异常");
			}

			if (jo.getBoolean("remark")) {
				this.service.MaterialOutbound(inTaskqueueId, locationId);
				return Rjson.success("执行成功，即将出库", true);
			} else {
				return Rjson.error("执行失败");
			}
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
	@ApiImplicitParams({
			@ApiImplicitParam(name = "inTaskqueueId", value = "入库队列id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findStorageDetail", method = RequestMethod.POST)
	public Rjson findPStorageDetail(HttpServletRequest request) {
		try {
			Integer inTaskqueueId = EqualsUtil.integer(request, "inTaskqueueId", "入库队列id", true);
			Integer pageNumber = EqualsUtil.pageNumber(request);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("inTaskqueueId", inTaskqueueId);
			map.put("pageNumber", pageNumber);

			PageInfo<JSONObject> pageInfo = service.findStorageDetail(map);

			return Rjson.success("查询成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询入库队列
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询入库队列", notes = "查询入库队列")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query") })
	@RequestMapping(value = "findInTaskqueueTList", method = RequestMethod.POST)
	public Rjson findInTaskqueueTList(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户Id", true);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listNo", listNo);
			map.put("userId", userId);

			List<CWmsInTaskqueueT> list = service.findInTaskqueueTList(map);
			return Rjson.success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询审批记录集合
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询审批记录集合", notes = "查询审批记录集合")
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer userId = EqualsUtil.integer(request, "userId", "用户Id", true);

			JSONObject json = new JSONObject();
			json.put("pageNumber", pageNumber);
			json.put("userId", userId);

			PageInfo<CWmsApprovalT> pageInfo = service.findList(json);

			return Rjson.success("查询成功", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 打印条码
	 *
	 * @param request
	 */
	@RequestMapping(value = "printing", method = RequestMethod.POST)
	public Rjson printing(HttpServletRequest request) {
		try {
			Thread.sleep(2000);
			String listNo = null;
			try {
				listNo = request.getParameter("listNo");
			} catch (NullPointerException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'单据号'不能为空");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				throw new Exception("'单据号'参数类型有误");
			}

			boolean res = service.printing(listNo);
			return Rjson.success("打印成功", res);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新库位状态
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateLocationStatus")
	public Rjson updateLocationStatus(HttpServletRequest request) {
		try {
			int locationId = EqualsUtil.integer(request, "locationId", "库位", true);
			int locationStatus = EqualsUtil.integer(request, "locationStatus", "库位状态", true);

			service.updateLocationStatus(locationId, locationStatus);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
