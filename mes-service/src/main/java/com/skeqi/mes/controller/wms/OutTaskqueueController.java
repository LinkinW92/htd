package com.skeqi.mes.controller.wms;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.service.wms.InTaskqueueService;
import com.skeqi.mes.service.wms.OutTaskqueueService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 出库队列
 *
 * @author yinp
 * @date 2020年3月16日
 *
 */
@RestController
@RequestMapping(value ="wms/outTaskqueue", produces = MediaType.APPLICATION_JSON)
@Api(value = "出库队列", description = "出库队列", produces = MediaType.APPLICATION_JSON)
public class OutTaskqueueController {

	private String url = "http://127.0.0.1/FISClientService.asmx?wsdl";

	@Autowired
	OutTaskqueueService service;

	@Autowired
	InTaskqueueService itService;

	/**
	 * 查询条码
	 * @param request
	 * @return
	 */
	@RequestMapping("/findBarCode")
	public Rjson findBarCode(HttpServletRequest request) {
		try {

			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			int materialId = EqualsUtil.integer(request, "materialId", "物料id", true);
			int projectId = EqualsUtil.integer(request, "projectId", "项目id", true);
			int locationId = EqualsUtil.integer(request, "locationId", "库位id", true);

			List<JSONObject> list = service.findBarCode(listNo, materialId, projectId, locationId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 直接出库
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/directDelivery")
	@OptionalLog(module="仓管", module2="出库队列", method="直接出库")
	public Rjson directDelivery(HttpServletRequest request) {
		try {
			int outTaskqueueId = EqualsUtil.integer(request, "outTaskqueueId", "出库队列id", true);
			int locationId = EqualsUtil.integer(request, "locationId", "库位id", true);
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			String tray = EqualsUtil.string(request, "tray", "托盘码", true);

			service.directDelivery(outTaskqueueId, listNo, tray, locationId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 出库
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Transactional
	@ApiOperation(value = "出库", notes = "出库")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "outTaskqueueId", value = "出库队列id", required = true, paramType = "query")
	})
	@RequestMapping(value = "chuku0806", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="出库队列", method="出库")
	public Rjson chuku0806(HttpServletRequest request) {
		try {
			Integer outTaskqueueId = EqualsUtil.integer(request, "outTaskqueueId", "出库队列id", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);

			boolean res = service.chuku0806(outTaskqueueId, locationId);
			return Rjson.success("执行成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}


	/**
	 * 平库出库
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Transactional
	@ApiOperation(value = "平库出库", notes = "平库出库")
	@ApiImplicitParams({@ApiImplicitParam(name = "outTaskqueueId", value = "出库队列id", required = true, paramType = "query")})
	@RequestMapping(value = "chuku", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="出库队列", method="平库出库")
	public Rjson chuku(HttpServletRequest request) {
		try {
			Integer outTaskqueueId = EqualsUtil.integer(request, "outTaskqueueId", "出库队列id", true);

			boolean res = service.chuku(outTaskqueueId);
			if(res){
				return Rjson.success("执行成功",true);
			}else{
				return Rjson.error("执行失败，未知错误");
			}

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
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "调用物料直流回流接口", notes = "调用物料直流回流接口")
	@RequestMapping(value = "MaterialForwardOrBackWard", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="出库队列", method="调用物料直流回流接口")
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
			// 开始调用服务
			Object obj = null;

			try {
				obj = call.invoke(new String[] { JSON.toJSONString(map) });
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("与客户端通讯失败");
			}

			JSONObject jo = JSON.parseObject(obj.toString());

			if (jo.getBoolean("remark") == null) {
				throw new Exception("客户端返回结果异常");
			}

			if(jo.getBoolean("remark")){
				return Rjson.success("执行成功，请按回流放行按钮",true);
			}else{
				throw new Exception(jo.getString("reason"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}


	/**
	 * 查询出库队列
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询出库队列", notes = "查询出库队列")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "listNo", value = "单据号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "userName", value = "用户名", required = true, paramType = "query")
	})
	@RequestMapping(value = "findOutTaskqueue", method = RequestMethod.POST)
	public Rjson findOutTaskqueue(HttpServletRequest request) {
		try {

			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户id", true);

			List<CWmsOutTaskqueueT> list = service.findOutTaskqueue(listNo, userId);

			return Rjson.success("查询成功",list);
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
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询入库队列", notes = "查询入库队列")
	@ApiImplicitParams({@ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query")})
	@RequestMapping(value = "findInTaskqueue", method = RequestMethod.POST)
	public Rjson findInTaskqueue(HttpServletRequest request) {
		CWmsInTaskqueueT dx = new CWmsInTaskqueueT();
		try {

			boolean leng = false;
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			dx.setListNo(listNo);

			List<CWmsInTaskqueueT> list = itService.findInTaskqueue(dx);
			if(list.size() == 0 ) {
				leng = true;
			}
			return Rjson.success("查询成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询审批记录集合
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ApiOperation(value = "查询审批记录集合", notes = "查询审批记录集合")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "listNo", value = "单据号", required = false, paramType = "query"),
		@ApiImplicitParam(name = "pageNumber", value = "当前页", required = true, paramType = "query")
		})
	@RequestMapping(value = "findList", method = RequestMethod.POST)
	public Rjson findList(HttpServletRequest request) {
		try {
			Integer userId = EqualsUtil.integer(request, "userId", "用户id", true);
			String listNo = EqualsUtil.string(request, "listNo", "单据号", false);
			Integer pageNumber = EqualsUtil.pageNumber(request);

			PageInfo<CWmsApprovalT> pageInfo = service.findApproval(userId,listNo, pageNumber);

			return Rjson.success("查询成功",pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过id查询
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "通过id查询", notes = "通过id查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query")})
	@RequestMapping(value = "findById", method = RequestMethod.POST)
	public Rjson findById(HttpServletRequest request) {

		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			CWmsOutTaskqueueT dx = service.findOutTaskqueueById(id);

			if (dx == null) {
				throw new Exception();
			}

			return Rjson.success("查询成功",dx);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 调用出库接口
	 * XT355_356_357
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "调用出库接口 XT355_356_357", notes = "调用出库接口 XT355_356_357")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query"),
		 @ApiImplicitParam(name = "trayCode", value = "托盘码", required = true, paramType = "query"),
		 @ApiImplicitParam(name = "X", value = "X", required = false, paramType = "query"),
		 @ApiImplicitParam(name = "Y", value = "Y", required = false, paramType = "query"),
		 @ApiImplicitParam(name = "Z", value = "Z", required = false, paramType = "query"),
		})
	@RequestMapping(value = "MaterialOutboundXT355_356_357", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="出库队列", method="调用出库接口")
	public Rjson MaterialOutboundXT355_356_357(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServicesException {

		try {

			String namespace = "http://tempuri.org/";
			String methodName = "MaterialOutbound";
			String soapActionURI = "http://tempuri.org/MaterialOutbound";

			String listNo = null;
			String trayCode = null;
			Integer X = null;
			Integer Y = null;
			Integer Z = null;

			if(request.getParameter("listNo")!=null && !request.getParameter("listNo").equals("")){
				try {
					listNo =  request.getParameter("listNo");
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			}
			if(request.getParameter("trayCode")!=null && !request.getParameter("trayCode").equals("")){
				try {
					trayCode =  request.getParameter("trayCode");
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}
			}
			if(request.getParameter("X")!=null
					&& !request.getParameter("X").equals("")
					&& !request.getParameter("X").equals("0")){
				try {
					X =  Integer.parseInt(request.getParameter("X"));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
					throw new Exception("'X'参数类型有误");
				}
			}
			if(request.getParameter("X")!=null
					&& !request.getParameter("X").equals("")
					&& !request.getParameter("X").equals("0")){
				try {
					X =  Integer.parseInt(request.getParameter("X"));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
					throw new Exception("'X'参数类型有误");
				}
			}
			if(request.getParameter("Y")!=null
					&& !request.getParameter("Y").equals("")
					&& !request.getParameter("Y").equals("0")){
				try {
					X =  Integer.parseInt(request.getParameter("Y"));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
					throw new Exception("'Y参数类型有误");
				}
			}
			if(request.getParameter("Z")!=null
					&& !request.getParameter("Z").equals("")
					&& !request.getParameter("Z").equals("0")){
				try {
					X =  Integer.parseInt(request.getParameter("Z"));
				}catch (NumberFormatException e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
					throw new Exception("'Z参数类型有误");
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listNo", listNo);
			map.put("trayCode", trayCode);
			map.put("X", X);
			map.put("Y", Y);
			map.put("Z", Z);

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
				e.printStackTrace();
				throw new Exception("与客户端通讯失败");
			}

			JSONObject jo = JSON.parseObject(obj.toString());

			if (jo.getBoolean("remark")==false) {
				throw new Exception(jo.getString("reason"));
			}

			return Rjson.success("执行成功，即将出库",true);
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
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "调用出库接口", notes = "调用出库接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "listNo", value = "单号", required = true, paramType = "query"),
		 @ApiImplicitParam(name = "trayCode", value = "托盘码", required = true, paramType = "query"),
		 @ApiImplicitParam(name = "X", value = "X", required = false, paramType = "query"),
		 @ApiImplicitParam(name = "Y", value = "Y", required = false, paramType = "query"),
		 @ApiImplicitParam(name = "Z", value = "Z", required = false, paramType = "query"),
		})
	@RequestMapping(value = "MaterialOutbound", method = RequestMethod.POST)
	@OptionalLog(module="仓管", module2="出库队列", method="调用出库接口")
	public Rjson MaterialOutbound(HttpServletRequest request) {
		try {

			String namespace = "http://tempuri.org/";
			String methodName = "MaterialOutbound";
			String soapActionURI = "http://tempuri.org/MaterialOutbound";

			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			String trayCode = EqualsUtil.string(request, "trayCode", "托盘码", true);
			Integer X = EqualsUtil.integer(request, "X", "X", true);
			Integer Y = EqualsUtil.integer(request, "Y", "Y", true);
			Integer Z = EqualsUtil.integer(request, "Z", "Z", true);


			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listNo", listNo);
			map.put("trayCode", trayCode);
			map.put("X", X);
			map.put("Y", Y);
			map.put("Z", Z);

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
				e.printStackTrace();
				throw new Exception("与客户端通讯失败");
			}

			JSONObject jo = JSON.parseObject(obj.toString());

			if (jo.getBoolean("remark")==false) {
				throw new Exception(jo.getString("reason"));
			}
			return Rjson.success("执行成功，即将出库",true);
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
	 * @param response
	 * @throws IOException
	 * @throws ServicesException
	 */
	@ApiOperation(value = "查询库存详情", notes = "查询库存详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "listNo", value = "单据号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query")
		})
	@RequestMapping(value = "findStorageDetail", method = RequestMethod.POST)
	public Rjson findStorageDetail(HttpServletRequest request) {

		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("locationId", locationId);

			List<JSONObject> list = service.findStorageDetail(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}
