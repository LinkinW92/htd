package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.service.wms.InTaskqueueService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 入库队列
 *
 * @author yinp
 * @date 2020年3月17日
 *
 */
@RestController
@RequestMapping(value = "wms/inTaskqueue", produces = MediaType.APPLICATION_JSON)
@Api(value = "入库队列", description = "入库队列", produces = MediaType.APPLICATION_JSON)
public class InTaskqueueController {

	private String url = "http://127.0.0.1/FISClientService.asmx?wsdl";

	@Autowired
	InTaskqueueService service;

	/**
	 * 直接入库
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/directWarehousing")
	@OptionalLog(module = "仓管", module2 = "入库队列", method = "直接入库")
	public Rjson directWarehousing(HttpServletRequest request) {
		try {
			Integer inTaskqueueId = EqualsUtil.integer(request, "inTaskqueueId", "入库队列id", true);
			Integer locationStatus = EqualsUtil.integer(request, "locationStatus", "库位状态", true);

			service.directWarehousing(inTaskqueueId, locationStatus);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新物料的条码
	 *
	 * @param request
	 */
	@ApiOperation(value = "更新物料的条码", notes = "更新物料的条码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "sourceBarCode", value = "原条码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "presentBarCode", value = "新条码", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "updateBarCode", method = RequestMethod.POST)
	@OptionalLog(module = "仓管", module2 = "入库队列", method = "更新物料的条码")
	public Rjson updateBarCode(HttpServletRequest request) {
		try {
			String sourceBarCode = EqualsUtil.string(request, "sourceBarCode", "原条码", true);
			String presentBarCode = EqualsUtil.string(request, "presentBarCode", "新条码", true);

			boolean result = service.updateBarCode(sourceBarCode, presentBarCode);
			if (result) {
				return Rjson.success("更新成功");
			} else {
				return Rjson.success("更新失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询条码
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询条码", notes = "查询条码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单据号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "materialId", value = "物料id", required = true, paramType = "query") })
	@RequestMapping(value = "findBarCode", method = RequestMethod.POST)
	public Rjson findBarCode(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);
			Integer materialId = EqualsUtil.integer(request, "materialId", "物料id", true);

			List<JSONObject> list = service.findBarCode(listNo, locationId, materialId);
			return Rjson.success("查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 平库入库
	 *
	 * @param request
	 */
	@ApiOperation(value = "平库入库", notes = "平库入库")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "inTaskqueueId", value = "入库队列id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "ruku", method = RequestMethod.POST)
	@OptionalLog(module = "仓管", module2 = "入库队列", method = "平库入库")
	public Rjson ruku(HttpServletRequest request) {
		try {
			Integer inTaskqueueId = EqualsUtil.integer(request, "inTaskqueueId", "入库队列id", true);
			Integer locationStatus = EqualsUtil.integer(request, "locationStatus", "库位状态", true);

			boolean res = service.ruku(inTaskqueueId, locationStatus);
			if (res) {
				return Rjson.success("执行成功", true);
			} else {
				return Rjson.error("执行失败，未知错误！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 物料入库 调用客户端入库接口 XT355_356_357
	 *
	 * @param request
	 */
	@ApiOperation(value = "物料入库", notes = "XT355_356_357物料入库,调用客户端入库接口")
	@ApiImplicitParams({})
	@RequestMapping(value = "MaterialWarehousing", method = RequestMethod.POST)
	@OptionalLog(module = "仓管", module2 = "入库队列", method = "物料入库")
	public Rjson MaterialWarehousingXT355_356_357(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			String namespace = "http://tempuri.org/";
			String methodName = "***";
			String soapActionURI = "http://tempuri.org/***";

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
				obj = call.invoke(new String[] { null });
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("与客户端通讯失败");
			}

			JSONObject jo = JSON.parseObject(obj.toString());

			if (jo.getBoolean("remark") == null) {
				throw new Exception("客户端返回结果异常");
			}

			if (jo.getBoolean("remark")) {
				return Rjson.success("执行成功，请按回流放行按钮", true);
			} else {
				return Rjson.error(jo.getString("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 入库放行
	 *
	 * @param request
	 */
	@ApiOperation(value = "入库放行", notes = "入库放行")
	@ApiImplicitParams({})
	@RequestMapping(value = "TrayPass", method = RequestMethod.POST)
	@OptionalLog(module = "仓管", module2 = "入库队列", method = "入库放行")
	public Rjson TrayPass(HttpServletRequest request) {
		try {

			int id = EqualsUtil.integer(request, "id", "入库队列id", true);
			int locationId = EqualsUtil.integer(request, "locationId", "库位id", true);

			String namespace = "http://tempuri.org/";
			String methodName = "TrayPass";
			String soapActionURI = "http://tempuri.org/TrayPass";

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
				obj = call.invoke(new String[] { null });
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("与客户端通讯失败");
			}

			JSONObject jo = JSON.parseObject(obj.toString());

			if (jo.getBoolean("remark") == null) {
				throw new Exception("客户端返回结果异常");
			}

			if (jo.getBoolean("remark")) {

				// 放行
				this.service.TrayPass(id, locationId);

				return Rjson.success("执行成功，请按回流放行按钮", true);
			} else {
				return Rjson.error(jo.getString("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改托盘码
	 *
	 * @param request
	 */
	@ApiOperation(value = "修改托盘码", notes = "修改托盘码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "trayCode", value = "托盘码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "inTaskqueueId", value = "入库队列id", required = true, paramType = "query") })
	@Transactional
	@RequestMapping(value = "updateTray", method = RequestMethod.POST)
	@OptionalLog(module = "仓管", module2 = "入库队列", method = "修改托盘码")
	public Rjson updateTray(HttpServletRequest request) {
		try {
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);
			String trayCode = EqualsUtil.string(request, "trayCode", "托盘码", true);
			Integer inTaskqueueId = EqualsUtil.integer(request, "inTaskqueueId", "入库队列id", true);

			CWmsLocationT location = service.findLocation(locationId);
			CWmsInTaskqueueT inTaskqueue = service.findInTaskqueueByTray(trayCode);
			Integer materialNumberCount = service.findMaterialNumberCount(trayCode);

			if (inTaskqueue != null) {
				throw new Exception("托盘码：" + trayCode + "已存在入库队列，请更换托盘");
			}
			if (materialNumberCount > 0) {
				throw new Exception("托盘码：" + trayCode + "已存在库位，请更换托盘");
			}

			if (trayCode.length() < 10) {
				throw new Exception("托盘码不能低于十位");
			}

			if (trayCode.substring(0, 9).equals("SKQ-ND-L-")) {
				if (location.getTrayType() == 2) {
					throw new Exception("托盘高矮跟库位不匹配");
				}

			} else if (trayCode.substring(0, 9).equals("SKQ-ND-H-")) {
				if (location.getTrayType() == 1) {
					throw new Exception("托盘高矮跟库位不匹配");
				}
			} else {
				throw new Exception("托盘码格式有误");
			}
			inTaskqueue = new CWmsInTaskqueueT();
			inTaskqueue.setTray(trayCode);
			inTaskqueue.setId(inTaskqueueId);

			boolean res = service.updateInTaskqueue(inTaskqueue);
			if (!res) {
				throw new Exception("更新托盘码失败");
			}
			return Rjson.success("更新托盘码成功!");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过库位id查询库位
	 *
	 * @param request
	 */
	@ApiOperation(value = "通过库位id查询库位", notes = "通过库位id查询库位")
	@ApiImplicitParams({ @ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query") })
	@RequestMapping(value = "findLocation", method = RequestMethod.POST)
	public Rjson findLocation(HttpServletRequest request) {
		try {
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);

			CWmsLocationT location = service.findLocation(locationId);
			return Rjson.success("查询成功", location);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

//	/**
//	 * 调用入库接口
//	 *
//	 * @param request
//	 * @param response
//	 * @throws IOException
//	 * @throws ServicesException
//	 */
//	@ApiOperation(value = "通过库位id查询库位", notes = "通过库位id查询库位")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query")})
//	@RequestMapping(value = "warehousing", method = RequestMethod.POST)
//	public Rjson warehousing(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServicesException {
//
//		// 接收参数
//		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
//		response.setContentType("application/json");
//		// 将接受到的字符串转换为json数组
//		JSONObject json = JSONObject.parseObject(str);
//
//		String trayCode = json.getString("trayCode");
//		String listNo = json.getString("listNo");
//		Integer locationId = json.getInteger("locationId");
//
//		JSONObject result = new JSONObject();
//		JSONObject data = new JSONObject();
//
//		try {
//
//			CWmsLocationT location = service.findLocation(locationId);
//
//			if (location == null) {
//				throw new Exception("查询库位坐标出错");
//			}
//
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("trayCode", trayCode);
//			map.put("listNo", listNo);
//			map.put("locationId", locationId);
//			map.put("X", location.getLocationX());
//			map.put("Y", location.getLocationY());
//			map.put("Z", location.getLocationZ());
//
//			String namespace = "http://tempuri.org/";
//			String methodName = "TrayPass";
//			String soapActionURI = "http://tempuri.org/TrayPass";
//
//			Service service = new Service();
//			Call call;
//			try {
//				call = (Call) service.createCall();
//				call.setTargetEndpointAddress(url);
//				call.setUseSOAPAction(true);
//				call.setSOAPActionURI(soapActionURI);
//				call.setOperationName(new QName(namespace, methodName));
//				call.addParameter(new QName(namespace, "str"), XMLType.XSD_STRING, ParameterMode.IN);
//				call.setReturnType(XMLType.XSD_STRING);
//				// 开始调用服务
//				Object obj = null;
//
//				try {
//					obj = call.invoke(new String[] { JSON.toJSONString(map) });
//				} catch (Exception e) {
//					throw new Exception("与客户端通讯失败");
//				}
//
//				JSONObject jo = JSON.parseObject(obj.toString());
//
//				if (jo.getInteger("code") == null) {
//					throw new Exception("客户端返回结果异常");
//				}
//
//				if (jo.getInteger("code") != 0) {
//					throw new Exception(jo.getString("code"));
//				}
//
//			} catch (ServiceException e) {
//				throw new Exception("调用客户端接口异常");
//			} catch (RemoteException e) {
//				throw new Exception("调用客户端接口异常");
//			}
//
//			data.put("msg", "操作成功，请按放行按钮！");
//			data.put("code", 0);
//			result.put("result", data);
//		} catch (Exception e) {
//			e.printStackTrace();
//			data.put("msg", e.getMessage());
//			data.put("code", -1);
//			result.put("result", data);
//		} finally {
//			response.getWriter().append(result.toJSONString());
//		}
//	}

//	/**
//	 * 调用放行接口
//	 *
//	 * @param request
//	 * @param response
//	 * @throws IOException
//	 * @throws ServicesException
//	 */
//	@RequestMapping(value = "outOfStock", method = RequestMethod.POST)
//	public Rjson fangxing(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServicesException {
//
//		// 接收参数
//		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
//		response.setContentType("application/json");
//		// 将接受到的字符串转换为json数组
//		JSONObject json = JSONObject.parseObject(str);
//
//		JSONObject result = new JSONObject();
//		JSONObject data = new JSONObject();
//
//		try {
//
//			String namespace = "http://tempuri.org/";
//			String methodName = "放行";
//			String soapActionURI = "http://tempuri.org/放行";
//
//			Map<String, Object> map = new HashMap<String, Object>();
//
//			Service service = new Service();
//			Call call;
//			try {
//				call = (Call) service.createCall();
//				call.setTargetEndpointAddress(url);
//				call.setUseSOAPAction(true);
//				call.setSOAPActionURI(soapActionURI);
//				call.setOperationName(new QName(namespace, methodName));
//				call.addParameter(new QName(namespace, "str"), XMLType.XSD_STRING, ParameterMode.IN);
//				call.setReturnType(XMLType.XSD_STRING);
//				// 开始调用服务
//				Object obj = call.invoke(new String[] { JSON.toJSONString(map) });
//
//				try {
//					call.invoke(new String[] { JSON.toJSONString(map) });
//				} catch (Exception e) {
//					throw new Exception("与客户端通讯失败");
//				}
//
//				JSONObject jo = JSON.parseObject(obj.toString());
//
//				if (jo.getInteger("code") == null) {
//					throw new Exception("客户端返回结果异常");
//				}
//
//				if (jo.getInteger("code") != 0) {
//					throw new Exception(jo.getString("code"));
//				}
//
//			} catch (ServiceException e) {
//				throw new Exception("调用客户端接口异常");
//			} catch (RemoteException e) {
//				throw new Exception("调用客户端接口异常");
//			}
//
//			data.put("msg", "操作成功，请按放行按钮！");
//			data.put("code", 0);
//			result.put("result", data);
//		} catch (Exception e) {
//			e.printStackTrace();
//			data.put("msg", e.getMessage());
//			data.put("code", -1);
//			result.put("result", data);
//		} finally {
//			response.getWriter().append(result.toJSONString());
//		}
//	}

	/**
	 * 查询库存详情
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询库存详情", notes = "查询库存详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单据号", required = false, paramType = "query"),
			@ApiImplicitParam(name = "locationId", value = "库位id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findStorageDetail", method = RequestMethod.POST)
	public Rjson findStorageDetail(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", false);
			Integer pageNumber = EqualsUtil.pageNumber(request);

			PageInfo<Map<String, Object>> pageInfo = service.findStorageDetail(listNo, locationId, pageNumber);

			return Rjson.success("查询成功", pageInfo);
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
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findApproval", method = RequestMethod.POST)
	public Rjson findApproval(HttpServletRequest request) {
		try {
			JSONObject json = new JSONObject();
			Integer pageNumber = EqualsUtil.pageNumber(request);
			json.put("pageNumber", pageNumber);

			PageInfo<CWmsApprovalT> pageInfo = service.findApproval(json);

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
	@ApiOperation(value = "通过单据号查询入库队列", notes = "通过单据号查询入库队列")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单据号", required = true, paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query") })
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request) {
		try {
			CWmsInTaskqueueT dx = new CWmsInTaskqueueT();
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer pageNumber = EqualsUtil.pageNumber(request);

			dx.setListNo(listNo);

			PageHelper.startPage(pageNumber, 8);
			List<CWmsInTaskqueueT> list = service.findInTaskqueue(dx);
			PageInfo<CWmsInTaskqueueT> pageInfo = new PageInfo<CWmsInTaskqueueT>(list, 5);

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
			return Rjson.success(e.getMessage());
		}
	}

	/**
	 * 更新库位状态
	 *
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
