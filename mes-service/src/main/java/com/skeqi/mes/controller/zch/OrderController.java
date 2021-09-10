package com.skeqi.mes.controller.zch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.mapper.wf.CustomPropertyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.zch.EventService;
import com.skeqi.mes.service.zch.OrderService;
import com.skeqi.mes.service.zch.WorkorderService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 订单管理
 * @ClassName: OrderController
 */
@Controller
@RequestMapping(value = "ord", produces = MediaType.APPLICATION_JSON)
@Api(value = "订单管理", description = "订单管理", produces = MediaType.APPLICATION_JSON)
public class OrderController {

	@Autowired
	OrderService service;
	@Autowired
	EventService serviceEvent;
	@Autowired
	private WorkorderService workorderService;
	@Autowired
	CMesProductionTService productionService;

	@RequestMapping(value = "/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询订单列表", notes = "查询订单列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "customer_name", value = "客户名称", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "order_begin_time", value = "下单开始日期", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "order_end_time", value = "下单结束日期", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "int")
			 })
	@ResponseBody
	public Rjson findList(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;

		Map<String, Object> map = new HashMap<>();

		try {
			map.put("customer_name", request.getParameter("customer_name"));
			Object order_begin_time = request.getParameter("order_begin_time");
			Object order_end_time = request.getParameter("order_end_time");
			map.put("order_begin_time", order_begin_time);
			map.put("order_end_time", order_end_time);
			if(!StringUtils.isEmpty(order_begin_time) && StringUtils.isEmpty(order_end_time)){
				map.put("order_time", order_begin_time);
			}
			if(!StringUtils.isEmpty(order_end_time) && StringUtils.isEmpty(order_begin_time)){
				map.put("order_time", order_end_time);
			}
			map.put("status", request.getParameter("status"));

			Integer pageNum = 1;
			Integer pageSize = 6;
			if(request.getParameter("pageNum") != null){
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if(request.getParameter("pageSize") != null){
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}

			PageHelper.startPage(pageNum, pageSize);
			list=service.findOrderList(map);
			PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	@ApiOperation(value = "新增订单", notes = "新增订单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "customer_name", value = "客户名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "order_time", value = "下单时间", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "delivery_plan_time", value = "计划交货时间", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "delivery_information", value = "交货信息", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "CODE", value = "编码", required = false, paramType = "query", dataType = "String")
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="订单管理", method="新增订单")
	public Rjson addOrder(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {

			map.put("customer_name", request.getParameter("customer_name"));
			map.put("order_time", request.getParameter("order_time"));
			map.put("delivery_plan_time", request.getParameter("delivery_plan_time"));
			map.put("delivery_information", request.getParameter("delivery_information"));
			map.put("CODE", request.getParameter("CODE"));
//			,#{order_type},#{contract_no},#{served_by},#{payer},#{creator},#{customer_po_no},#{modified_by},#{modification_time},#{sales_organization},#{company_code}
			map.put("order_type", request.getParameter("order_type"));
			map.put("contract_no", request.getParameter("contract_no"));
			map.put("served_by", request.getParameter("served_by"));
			map.put("payer", request.getParameter("payer"));
//			String userName = ToolUtils.getCookieUserName(request);
			map.put("creator", ToolUtils.getCookieUserName(request));//创建人
			map.put("customer_po_no", request.getParameter("customer_po_no"));
//			map.put("modified_by", request.getParameter("modified_by"));//修改人

//			map.put("sales_organization", request.getParameter("sales_organization"));
			map.put("company_code", request.getParameter("company_code"));
			//自定义属性所需参数
			JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
			map.put("custom",jsonArray);
			service.addOrder(map);

			// 事件添加
			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "订单");
			mapEvent.put("OBJECT_ID", request.getParameter("CODE"));
			mapEvent.put("EVENT", "创建");
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

	@RequestMapping(value = "/addOrderrecord", method = RequestMethod.POST)
	@ApiOperation(value = "新增订单记录", notes = "新增订单记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ORDER_ID", value = "订单id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "PRODUCT_MODEL", value = "产品型号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "PRODUCT_NAME", value = "产品名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "DEMAND_NUM", value = "需求数量", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "MFG_PARAMS", value = "制造参数", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "ROUTING_ID", value = "工艺id", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "TOTAL_RECIPE_ID", value = "配方id", required = false, paramType = "query", dataType = "int")
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="订单管理", method="新增订单记录")
	public Rjson addOrderrecord(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {

			map.put("ORDER_ID", request.getParameter("ORDER_ID"));
			map.put("PRODUCT_MODEL", request.getParameter("PRODUCT_MODEL"));
			map.put("PRODUCT_NAME", request.getParameter("PRODUCT_NAME"));
			map.put("DEMAND_NUM", request.getParameter("DEMAND_NUM"));
			map.put("MFG_PARAMS", request.getParameter("MFG_PARAMS"));
			map.put("ROUTING_ID", request.getParameter("ROUTING_ID"));
			map.put("TOTAL_RECIPE_ID", request.getParameter("TOTAL_RECIPE_ID"));

			map.put("UNIT_PRICE_EXCLUDING_TAX", request.getParameter("UNIT_PRICE_EXCLUDING_TAX"));
			map.put("PRICE_UNIT", request.getParameter("PRICE_UNIT"));
			map.put("TAX_RATE", request.getParameter("TAX_RATE"));
			map.put("PLANNED_DELIVERY_DATE", request.getParameter("PLANNED_DELIVERY_DATE"));



			service.addOrderrecord(map);

			// 事件添加
			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("ID", request.getParameter("ORDER_ID"));
			mapEvent.put("OBJECT_TYPE", "订单详情");
			mapEvent.put("OBJECT_ID", service.getByID(mapEvent).get("CODE"));
			mapEvent.put("EVENT", "创建");
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

	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
	@ApiOperation(value = "修改订单", notes = "修改订单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "订单id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "customer_name", value = "客户名称", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "order_time", value = "下单时间", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "delivery_plan_time", value = "计划交货时间", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "delivery_information", value = "交货信息", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "CODE", value = "编码", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "statusName", value = "事件名称", required = false, paramType = "query", dataType = "String")
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="订单管理", method="修改订单")
	public Rjson updateOrder(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {
			map.put("ID", request.getParameter("ID"));
			map.put("CUSTOMER_NAME", request.getParameter("customer_name"));
			map.put("ORDER_TIME", request.getParameter("order_time"));
			map.put("DELIVERY_PLAN_TIME", request.getParameter("delivery_plan_time"));
			map.put("DELIVERY_INFORMATION", request.getParameter("delivery_information"));
			map.put("STATUS", request.getParameter("status"));
			map.put("CODE", request.getParameter("CODE"));
			String statusName = request.getParameter("statusName");

			map.put("ORDER_TYPE", request.getParameter("order_type"));
			map.put("CONTRACT_NO", request.getParameter("contract_no"));
			map.put("SERVED_BY", request.getParameter("served_by"));
			map.put("PAYER", request.getParameter("payer"));

			map.put("CUSTOMER_PO_NO", request.getParameter("customer_po_no"));
			map.put("MODIFIED_BY",ToolUtils.getCookieUserName(request));

			map.put("COMPANY_CODE", request.getParameter("company_code"));




			if(statusName != null){
				switch (statusName) {
					case "1": statusName = "创建"; break;
					case "2": statusName = "审批"; break;
					case "3": statusName = "排产"; break;
					case "4": statusName = "生产"; break;
					case "5": statusName = "发货"; break;
					case "6": statusName = "关闭"; break;
				}
			}else {
				statusName = "修改";
			}

			//修改事件
			Map<String, Object> mapOld = service.getByID(map);
			Map<String, Object> mapJson = Rjson.reservingDifferences(map, mapOld);

			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "订单");
			mapEvent.put("OBJECT_ID", mapOld.get("CODE"));
			mapEvent.put("EVENT", statusName);
			mapEvent.put("PARAMETER1", mapJson.get("oMap"));
			mapEvent.put("PARAMETER2", mapJson.get("nMap"));
			mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
			serviceEvent.addEvent(mapEvent);

			//修改自定义属性值（内容）
			JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
			map.put("custom",jsonArray);
			map.put("CODE",mapOld.get("CODE"));
//			map.put("CODE",map.get("CODE"));
			service.updateOrder(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/updateOrderrecord", method = RequestMethod.POST)
	@ApiOperation(value = "修改订单记录", notes = "修改订单记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "订单记录id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "PRODUCT_MODEL", value = "产品型号", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "PRODUCT_NAME", value = "产品名称", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "DEMAND_NUM", value = "需求数量", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "MFG_PARAMS", value = "制造参数", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "ROUTING_ID", value = "工艺id", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "TOTAL_RECIPE_ID", value = "配方id", required = false, paramType = "query", dataType = "int")
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="订单管理", method="编辑订单记录")
	public Rjson updateOrderrecord(HttpServletRequest request) throws ServicesException {

		Map<String, Object> map = new HashMap<>();

		try {
			map.put("ID", request.getParameter("ID"));
			map.put("PRODUCT_MODEL", request.getParameter("PRODUCT_MODEL"));
			map.put("PRODUCT_NAME", request.getParameter("PRODUCT_NAME"));
			map.put("DEMAND_NUM", request.getParameter("DEMAND_NUM"));
			map.put("MFG_PARAMS", request.getParameter("MFG_PARAMS"));
			map.put("ROUTING_ID", request.getParameter("ROUTING_ID"));
			map.put("TOTAL_RECIPE_ID", request.getParameter("TOTAL_RECIPE_ID"));

			map.put("UNIT_PRICE_EXCLUDING_TAX", request.getParameter("UNIT_PRICE_EXCLUDING_TAX"));
			map.put("PRICE_UNIT", request.getParameter("PRICE_UNIT"));
			map.put("TAX_RATE", request.getParameter("TAX_RATE"));
			map.put("PLANNED_DELIVERY_DATE", request.getParameter("PLANNED_DELIVERY_DATE"));


			//修改事件
			Map<String, Object> mapOld = service.getOrderrecordByID(map);
			Map<String, Object> mapJson = Rjson.reservingDifferences(map, mapOld);

			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "订单详情");
			mapEvent.put("OBJECT_ID", mapOld.get("CODE"));
			mapEvent.put("EVENT", "修改");
			mapEvent.put("PARAMETER1", mapJson.get("oMap"));
			mapEvent.put("PARAMETER2", mapJson.get("nMap"));
			mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
			serviceEvent.addEvent(mapEvent);

			service.updateOrderrecord(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findOrderrecondList", method = RequestMethod.POST)
	@ApiOperation(value = "查询订单记录", notes = "查询订单记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "order_id", value = "订单id", required = true, paramType = "query", dataType = "int")
	})
	@ResponseBody
	public Rjson findOrderrecondList(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;

		Map<String, Object> map = new HashMap<>();
		Map<String, Object> workMap = new HashMap<>();
		try {
			map.put("order_id", request.getParameter("order_id"));

			list = service.findOrderrecondList(map);

			//查询产品信息
			for (Map<String, Object> objectMap : list) {
				if (!StringUtils.isEmpty(String.valueOf(objectMap.get("PRODUCT_MODEL")))) {
					String productType = String.valueOf(objectMap.get("PRODUCT_MODEL"));
					CMesProductionT productionByidL = productionService.findProductionByidL(null, productType);
					if (productionByidL != null && !StringUtils.isEmpty(productionByidL)) {
						Integer productId = productionByidL.getId();
						if (productId != null && productId > 0) {
							//查询工单信息
							workMap.put("PRODUCT_ID", productId);
							objectMap.put("workOrderList", workorderService.findWorkorderList(workMap));
						}
					}

				}
			}
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/schedulingOrderList", method = RequestMethod.POST)
	@ApiOperation(value = "排产订单及记录列表", notes = "排产订单及记录列表")
	@ResponseBody
	public Rjson orderScheduling(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;

		try {
			list = service.schedulingOrderList();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/schedulingWorkorderList", method = RequestMethod.POST)
	@ApiOperation(value = "排产工单列表", notes = "排产工单列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "LINE_ID", value = "产线id", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "BEGIN_DATE", value = "开始时间", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "END_DATE", value = "截止时间", required = true, paramType = "query", dataType = "String")
	})
	@ResponseBody
	public Rjson schedulingWorkorderList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("LINE_ID", request.getParameter("LINE_ID"));
			map.put("BEGIN_DATE", request.getParameter("BEGIN_DATE"));
			map.put("END_DATE", request.getParameter("END_DATE"));

			list = service.schedulingWorkorderList(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
	@ApiOperation(value = "删除订单", notes = "删除订单")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "订单id", required = true, paramType = "query", dataType = "int")
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="订单管理", method="删除订单")
	public Rjson deleteOrder(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("ID", request.getParameter("ID"));

			//删除事件
			Map<String, Object> mapOld = service.getByID(map);

			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "订单");
			mapEvent.put("OBJECT_ID", mapOld.get("CODE"));
			mapEvent.put("EVENT", "删除");
			mapEvent.put("PARAMETER1", JSONObject.toJSONString(Rjson.getMapByFormatTime(mapOld)));
			mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
			serviceEvent.addEvent(mapEvent);

			service.deleteOrder(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deleteOrderrecord", method = RequestMethod.POST)
	@ApiOperation(value = "删除订单记录", notes = "删除订单记录")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "订单记录id", required = true, paramType = "query", dataType = "int")
	})
	@ResponseBody
	@OptionalLog(module="生产", module2="订单管理", method="删除订单记录")
	public Rjson deleteOrderrecord(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("ID", request.getParameter("ID"));

			//删除事件
			Map<String, Object> mapOld = service.getOrderrecordByID(map);

			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "订单详情");
			mapEvent.put("OBJECT_ID", mapOld.get("CODE"));
			mapEvent.put("EVENT", "删除");
			mapEvent.put("PARAMETER1", JSONObject.toJSONString(mapOld));
			mapEvent.put("OPERATOR", ToolUtils.getCookieUserName(request));
			serviceEvent.addEvent(mapEvent);

			service.deleteOrderrecord(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findDemandMaterial", method = RequestMethod.POST)
	@ApiOperation(value = "查询工单需求物料列表", notes = "查询工单需求物料列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "beginTime", value = "开始时间", required = true, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "query", dataType = "String")
	})
	@ResponseBody
	public Rjson findDemandMaterial(HttpServletRequest request) throws ServicesException {
		Map<String, Object> mapResult = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("status", request.getParameter("status"));
			map.put("beginTime", request.getParameter("beginTime"));
			map.put("endTime", request.getParameter("endTime"));

			mapResult = service.findDemandMaterial(map);

			return Rjson.success(mapResult);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findMaterialInventory", method = RequestMethod.POST)
	@ApiOperation(value = "查询物料库存", notes = "查询物料库存")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startDt", value = "开始日期", required = true, paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "结束日期", required = true, paramType = "query"),
        @ApiImplicitParam(name = "materialCodes", value = "物料编码集合", required = true, paramType = "query"),
	})
	@ResponseBody
	public Rjson findMaterialInventory(HttpServletRequest request) throws ServicesException {
		List<List<Map<String, Object>>> mapResult = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("startDt", EqualsUtil.string(request,"startDt","开始日期",false));
			map.put("endDt",EqualsUtil.string(request,"endDt","结束日期",false));
			map.put("materialCodes",EqualsUtil.string(request,"materialCodes","物料编码集合",false));


			mapResult = service.findMaterialInventory(map);

			return Rjson.success(mapResult);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findStockMaterial", method = RequestMethod.POST)
	@ApiOperation(value = "查询物料库存", notes = "查询物料库存")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "MATERIAL_NO", value = "状态", required = false, paramType = "query", dataType = "int")
	})
	@ResponseBody
	public Rjson findStockMaterial(HttpServletRequest request) throws ServicesException {
		Map<String, Object> mapResult = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("status", request.getParameter("status"));
			map.put("beginTime", request.getParameter("beginTime"));
			map.put("endTime", request.getParameter("endTime"));

			mapResult = service.findDemandMaterial(map);

			return Rjson.success(mapResult);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findOrderByIdOrCode", method = RequestMethod.POST)
	@ApiOperation(value = "按id或按code查询订单信息", notes = "按id或按code查询订单信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "code", value = "订单号", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "id", value = "订单id", required = false, paramType = "query", dataType = "int")
	})
	@ResponseBody
	public Rjson findOrderByIdOrCode(HttpServletRequest request) throws ServicesException {
		Map<String, Object> mapResult = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("id", EqualsUtil.integer(request,"id","订单id",false));
			map.put("code",EqualsUtil.string(request,"code","订单编号",false));


			mapResult = service.findOrderByIdOrCode(map);

			return Rjson.success(mapResult);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findProductModelByName", method = RequestMethod.POST)
	@ApiOperation(value = "根据产品名称查产品型号", notes = "根据产品名称查产品型号")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "PRODUCT_NAME", value = "产品名称", required = true, paramType = "query", dataType = "String"),
	})
	@ResponseBody
	public Rjson findProductModelByName(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map.put("PRODUCT_NAME", EqualsUtil.string(request,"PRODUCT_NAME","产品名称",false));


			list = service.findProductModelByName(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findListAll", method = RequestMethod.POST)
	@ApiOperation(value = "查询所有订单", notes = "查询所有订单")
	@ResponseBody
	public Rjson findListAll(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
			list = service.findListAll();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}
}
