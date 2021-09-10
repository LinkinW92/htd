package com.skeqi.mes.controller.zch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.pojo.qh.CMesCustomProperty;
import com.skeqi.mes.service.wf.CMesCustomPropertyService;
import com.skeqi.mes.util.wf.CustomAttributesConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.zch.EventService;
import com.skeqi.mes.service.zch.PurchaseOrderService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 采购单管理
 * @ClassName: OrderController
 */
@Controller
@RequestMapping(value = "pur", produces = MediaType.APPLICATION_JSON)
@Api(value = "采购单管理", description = "采购单管理", produces = MediaType.APPLICATION_JSON)
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderService service;
	@Autowired
	EventService serviceEvent;

	@Autowired
	CMesCustomPropertyService customPropertyService;

	@RequestMapping(value = "/findPurchaseList", method = RequestMethod.POST)
	@ApiOperation(value = "查询订单列表", notes = "查询订单列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "PURCHASE_NO", value = "采购单号", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "PURCHASER", value = "采购人", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "SUPPLIER", value = "供应商", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "ORDER_TIME", value = "下单时间", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "ARRIVAL_TIME", value = "到货时间", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "STATUS", value = "状态", required = false, paramType = "query", dataType = "int")
			 })
	@ResponseBody
	public Rjson findPurchaseList(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;

		Map<String, Object> map = new HashMap<>();

		try {
			map.put("PURCHASE_NO", request.getParameter("PURCHASE_NO"));
			map.put("PURCHASER", request.getParameter("PURCHASER"));
			map.put("SUPPLIER", request.getParameter("SUPPLIER"));
			map.put("ORDER_TIME", request.getParameter("ORDER_TIME"));
			map.put("ARRIVAL_TIME", request.getParameter("ARRIVAL_TIME"));
			map.put("STATUS", request.getParameter("STATUS"));

			Integer pageNum = 1;
			Integer pageSize = 6;
			if(request.getParameter("pageNum") != null){
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if(request.getParameter("pageSize") != null){
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}

			PageHelper.startPage(pageNum, pageSize);
			list=service.findPurchaseList(map);
			PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addPurchase", method = RequestMethod.POST)
	@ApiOperation(value = "新增采购单", notes = "新增采购单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "PURCHASE_NO", value = "采购单号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "PURCHASER", value = "采购人", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "SUPPLIER", value = "供应商", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "ORDER_TIME", value = "下单时间", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "ARRIVAL_TIME", value = "到货时间", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "STATUS", value = "状态", required = false, paramType = "query", dataType = "String")
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="采购管理", method="新增采购单")
	public Rjson addPurchase(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {

			map.put("PURCHASE_NO", request.getParameter("PURCHASE_NO"));
			map.put("PURCHASER", request.getParameter("PURCHASER"));
			map.put("SUPPLIER", request.getParameter("SUPPLIER"));
			map.put("ORDER_TIME", request.getParameter("ORDER_TIME"));
			map.put("ARRIVAL_TIME", request.getParameter("ARRIVAL_TIME"));
			map.put("STATUS", request.getParameter("STATUS"));


			//自定义属性所需参数
			JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
			map.put("custom",jsonArray);
			service.addPurchase(map);

			// 事件添加
			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "采购");
			mapEvent.put("OBJECT_ID", request.getParameter("PURCHASE_NO"));
			mapEvent.put("EVENT", "新增");
			mapEvent.put("PARAMETER1", JSONObject.toJSONString(map));
			mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
			serviceEvent.addEvent(mapEvent);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addPurchaseDetails", method = RequestMethod.POST)
	@ApiOperation(value = "新增采购详情", notes = "新增采购详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "PURCHASE_ORDER_ID", value = "采购单id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "MATERIAL_NO", value = "物料编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "MATERIAL_MODEL", value = "型号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "MATERIAL_NAME", value = "名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "MATERIAL_NUM", value = "数量", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "REMARKS", value = "备注", required = false, paramType = "query", dataType = "String")
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="采购管理", method="新增采购详情")
	public Rjson addPurchaseDetails(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {

			map.put("PURCHASE_ORDER_ID", request.getParameter("PURCHASE_ORDER_ID"));
			map.put("MATERIAL_NO", request.getParameter("MATERIAL_NO"));
			map.put("MATERIAL_MODEL", request.getParameter("MATERIAL_MODEL"));
			map.put("MATERIAL_NAME", request.getParameter("MATERIAL_NAME"));
			map.put("MATERIAL_NUM", request.getParameter("MATERIAL_NUM"));
			map.put("REMARKS", request.getParameter("REMARKS"));

			service.addPurchaseDetails(map);

			// 事件添加
			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("ID", request.getParameter("PURCHASE_ORDER_ID"));
			mapEvent.put("OBJECT_TYPE", "采购详情");
			mapEvent.put("OBJECT_ID", service.getByID(mapEvent).get("PURCHASE_NO"));
			mapEvent.put("EVENT", "新增");
			mapEvent.put("PARAMETER1", JSONObject.toJSONString(map));
			mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
			serviceEvent.addEvent(mapEvent);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/updatePurchase", method = RequestMethod.POST)
	@ApiOperation(value = "修改采购单", notes = "修改采购单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "采购id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "PURCHASE_NO", value = "采购单号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "PURCHASER", value = "采购人", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "SUPPLIER", value = "供应商", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "ORDER_TIME", value = "下单时间", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "STATUS", value = "状态", required = false, paramType = "query", dataType = "String")
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="采购管理", method="编辑采购单")
	public Rjson updatePurchase(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {

			map.put("ID", request.getParameter("ID"));
			map.put("PURCHASE_NO", request.getParameter("PURCHASE_NO"));
			map.put("PURCHASER", request.getParameter("PURCHASER"));
			map.put("SUPPLIER", request.getParameter("SUPPLIER"));
			map.put("ORDER_TIME", request.getParameter("ORDER_TIME"));
			map.put("STATUS", request.getParameter("STATUS"));

			//修改事件
			Map<String, Object> mapOld = service.getByID(map);
			Map<String, Object> mapJson = Rjson.reservingDifferences(map, mapOld);

			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "采购");
			mapEvent.put("OBJECT_ID", request.getParameter("PURCHASE_NO"));
			mapEvent.put("EVENT", "修改");
			mapEvent.put("PARAMETER1", mapJson.get("oMap"));
			mapEvent.put("PARAMETER2", mapJson.get("nMap"));
			mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
			serviceEvent.addEvent(mapEvent);

			//修改自定义属性值（内容）
			JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
			map.put("custom",jsonArray);
			map.put("PURCHASE_NO", mapOld.get("PURCHASE_NO"));
			service.updatePurchase(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/updatePurchaseDetails", method = RequestMethod.POST)
	@ApiOperation(value = "修改订单记录", notes = "修改订单记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "采购详情id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "PURCHASE_ORDER_ID", value = "采购单id", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "MATERIAL_NO", value = "物料编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "MATERIAL_MODEL", value = "型号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "MATERIAL_NAME", value = "名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "MATERIAL_NUM", value = "数量", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "REMARKS", value = "备注", required = false, paramType = "query", dataType = "String")
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="采购管理", method="编辑采购详情")
	public Rjson updatePurchaseDetails(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {
			map.put("ID", request.getParameter("ID"));
			map.put("PURCHASE_ORDER_ID", request.getParameter("PURCHASE_ORDER_ID"));
			map.put("MATERIAL_NO", request.getParameter("MATERIAL_NO"));
			map.put("MATERIAL_MODEL", request.getParameter("MATERIAL_MODEL"));
			map.put("MATERIAL_NAME", request.getParameter("MATERIAL_NAME"));
			map.put("MATERIAL_NUM", request.getParameter("MATERIAL_NUM"));
			map.put("REMARKS", request.getParameter("REMARKS"));

			//修改事件
			Map<String, Object> mapOld = service.getDetailsByID(map);
			Map<String, Object> mapJson = Rjson.reservingDifferences(map, mapOld);

			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "采购详情");
			mapEvent.put("OBJECT_ID", mapOld.get("PURCHASE_NO"));
			mapEvent.put("EVENT", "修改");
			mapEvent.put("PARAMETER1", mapJson.get("oMap"));
			mapEvent.put("PARAMETER2", mapJson.get("nMap"));
			mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
			serviceEvent.addEvent(mapEvent);

			service.updatePurchaseDetails(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findPurchaseDetailsList", method = RequestMethod.POST)
	@ApiOperation(value = "查询订单记录", notes = "查询订单记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "PURCHASE_ORDER_ID", value = "采购单id", required = true, paramType = "query", dataType = "int")
	})
	@ResponseBody
	public Rjson findPurchaseDetailsList(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;

		Map<String, Object> map = new HashMap<>();
		try {
			map.put("PURCHASE_ORDER_ID", request.getParameter("PURCHASE_ORDER_ID"));

			list = service.findPurchaseDetailsList(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deletePurchase", method = RequestMethod.POST)
	@ApiOperation(value = "删除采购单", notes = "删除采购单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "采购单id", required = true, paramType = "query", dataType = "int")
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="采购管理", method="删除采购单")
	public Rjson deletePurchase(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("ID", request.getParameter("ID"));

			//删除事件
			Map<String, Object> mapOld = service.getByID(map);

			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "采购");
			mapEvent.put("OBJECT_ID", mapOld.get("PURCHASE_NO"));
			mapEvent.put("EVENT", "删除");
			mapEvent.put("PARAMETER1", JSONObject.toJSONString(Rjson.getMapByFormatTime(mapOld)));
			mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
			serviceEvent.addEvent(mapEvent);


			CMesCustomProperty customProperty = new CMesCustomProperty();
			customProperty.setBindScopeValue((String) mapOld.get("PURCHASE_NO"));
			//删除类型
			customProperty.setObjectType(CustomAttributesConstant.purchaseManagement);
			Integer integer = customPropertyService.delCustomPropertyByBindScopeValueAndObjectType(customProperty);
			if (integer<1){
				throw new Exception("删除自定义属性失败");
			}

			service.deletePurchase(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deletePurchaseDetails", method = RequestMethod.POST)
	@ApiOperation(value = "删除采购详情", notes = "删除采购详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "采购详情id", required = true, paramType = "query", dataType = "int")
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="采购管理", method="删除采购详情")
	public Rjson deletePurchaseDetails(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("ID", request.getParameter("ID"));

			//删除事件
			Map<String, Object> mapOld = service.getDetailsByID(map);

			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "采购详情");
			mapEvent.put("OBJECT_ID", mapOld.get("PURCHASE_NO"));
			mapEvent.put("EVENT", "删除");
			mapEvent.put("PARAMETER1", JSONObject.toJSONString(mapOld));
			mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
			serviceEvent.addEvent(mapEvent);

			service.deletePurchaseDetails(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

}
