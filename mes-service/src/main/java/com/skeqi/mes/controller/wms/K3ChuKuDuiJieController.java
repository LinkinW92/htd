package com.skeqi.mes.controller.wms;

import java.io.IOException;
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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.wms.CWmsOutTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.service.wms.K3ChuKuDuiJieService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * k3出库对接
 *
 * @author yinp
 *
 */
@RestController
@RequestMapping(value = "wms/K3ChuKuDuiJie", produces = MediaType.APPLICATION_JSON)
@Api(value = "k3出库对接", description = "k3出库对接", produces = MediaType.APPLICATION_JSON)
public class K3ChuKuDuiJieController {

	private String url = "http://127.0.0.1/FISClientService.asmx?wsdl";

	@Autowired
	K3ChuKuDuiJieService service;

	/**
	 * 删除K3出库信息
	 *
	 * @param request
	 */
	@ApiOperation(value = "删除K3出库信息", notes = "删除K3出库信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "出库队列id", required = true, paramType = "query")})
	@Transactional
	@RequestMapping(value = "deleteK3Number")
	public synchronized Rjson deleteK3Number(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "出库队列Id", true);

			boolean res = service.deleteK3Number(id);
			if(!res){
				throw new Exception("操作失败，未知错误");
			}
			return Rjson.success("操作成功",true);
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
	@ApiOperation(value = "K3出库异常处理", notes = "K3出库异常处理")
	@ApiImplicitParams({ @ApiImplicitParam(name = "exportId", value = "K3出库单号", required = true, paramType = "query")})
	@RequestMapping(value = "exceptionHandle")
	public synchronized Rjson exceptionHandle(HttpServletRequest request) {
		try {
			String exportId = EqualsUtil.string(request, "exportId", "K3出库单号", true);

			boolean res = service.exceptionHandle(exportId);
			if(!res){
				throw new Exception("操作失败，未知错误");
			}
			return Rjson.success("操作成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改出库队列工位id
	 *
	 * @param request
	 */
	@ApiOperation(value = "修改K3出库队列工位id", notes = "修改K3出库队列工位id")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "出库队列Id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "stationId", value = "工位Id", required = true, paramType = "query")})
	@RequestMapping(value = "updateOutTaskqueueStationId", method = RequestMethod.POST)
	@Transactional
	public Rjson updateOutTaskqueueStationId(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "出库队列Id", true);
			Integer stationId = EqualsUtil.integer(request, "stationId", "工位Id", true);

			boolean res = service.updateOutTaskqueueStationId(id, stationId);
			if(res){
				return Rjson.success("更新成功",true);
			}else{
				return Rjson.error("更新失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询工位集合
	 */
	@ApiOperation(value = "查询工位集合", notes = "查询工位集合")
	@ApiImplicitParams({})
	@Transactional
	@RequestMapping(value = "findStationList", method = RequestMethod.POST)
	public Rjson findStationList(HttpServletRequest request) {
		try {
			List<CMesStationT> list = service.findStationList();
			return Rjson.success("查询成功",list);
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
	@ApiOperation(value = "K3出库调用出库接口", notes = "K3出库调用出库接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "出库队列Id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "trayCode", value = "托盘码", required = true, paramType = "query"),
		@ApiImplicitParam(name = "X", value = "库位X坐标", required = true, paramType = "query"),
		@ApiImplicitParam(name = "Y", value = "库位Y坐标", required = true, paramType = "query"),
		@ApiImplicitParam(name = "Z", value = "库位Z坐标", required = true, paramType = "query")})
	@RequestMapping(value = "MaterialOutbound", method = RequestMethod.POST)
	public Rjson MaterialOutbound(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "出库队列Id", true);
			String trayCode = EqualsUtil.string(request, "trayCode", "托盘码", true);
			Integer X = EqualsUtil.integer(request, "X", "库位X坐标", true);
			Integer Y = EqualsUtil.integer(request, "Y", "库位Y坐标", true);
			Integer Z = EqualsUtil.integer(request, "Z", "库位Z坐标", true);

			CWmsOutTaskqueueT dx = service.findOutTaskqueueById(id);
			if(dx==null){
				throw new Exception("查询不到需要出库的队列");
			}
			if(dx.getStationId()==null || dx.getStationId().equals("") || dx.getStationId()==0){
				throw new Exception("请先选择工位");
			}

			String namespace = "http://tempuri.org/";
			String methodName = "MaterialOutbound";
			String soapActionURI = "http://tempuri.org/MaterialOutbound";

			Map<String, Object> map = new HashMap<String, Object>();
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

			if (jo.getBoolean("remark") == null) {
				throw new Exception("客户端返回结果异常");
			}
			return Rjson.success("执行成功,即将出库",true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 调用物料直流回流接口
	 */
	@ApiOperation(value = "K3出库调用物料直流回流接口", notes = "K3出库调用物料直流回流接口")
	@ApiImplicitParams({})
	@RequestMapping(value = "MaterialForwardOrBackWard", method = RequestMethod.POST)
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
				return Rjson.success("执行成功,请按回流方向按钮");
			}else{
				return Rjson.error("执行失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询物料库存
	 *
	 * @param request
	 */
	@ApiOperation(value = "查询物料库存", notes = "查询物料库存")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "listNo", value = "单据号", required = true, paramType = "query"),
		@ApiImplicitParam(name = "locationId", value = "库位id", required = true, paramType = "query"),
		@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query")})
	@Transactional
	@RequestMapping(value = "findStorageDetailList", method = RequestMethod.POST)
	public Rjson findStorageDetailList(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer locationId = EqualsUtil.integer(request, "locationId", "库位id", true);
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			Map<String,Object> map = new HashMap<String, Object>();
			map.put("listNo", listNo);
			map.put("locationId", locationId);

			PageHelper.startPage(pageNumber, pageSize);
			List<CWmsStorageDetailT> list = service.findStorageDetailList(map);
			PageInfo<CWmsStorageDetailT> pageInfo = new PageInfo<CWmsStorageDetailT>(list, 5);
			return Rjson.success("查询成功",pageInfo);
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
	 */
	@ApiOperation(value = "查询出库队列", notes = "查询出库队列")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNumber", value = "当前页", required = false, paramType = "query")})
	@Transactional
	@RequestMapping(value = "findOutTaskqueue", method = RequestMethod.POST)
	public Rjson findOutTaskqueue(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			PageHelper.startPage(pageNumber, pageSize);
			List<CWmsOutTaskqueueT> list = service.findOutTaskqueue();
			PageInfo<CWmsOutTaskqueueT> pageInfo = new PageInfo<CWmsOutTaskqueueT>(list, 5);
			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 抓取K3下发的任务
	 *
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "抓取K3下发的任务", notes = "抓取K3下发的任务")
	@ApiImplicitParams({})
	@Transactional
	@RequestMapping(value = "getK3ExportNotifydetall", method = RequestMethod.POST)
	public Rjson getK3ExportNotifydetall(HttpServletRequest request) {
		try {
			Integer userId = EqualsUtil.integer(request, "userId", "用户id", true);
			service.getK3ExportNotifydetall(userId);
			return Rjson.success("获取成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	@Transactional
//	@Scheduled(cron = "0/60 * * * * ?")
	public void k3() {
		try {
			service.getK3ExportNotifydetall(1);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

}
