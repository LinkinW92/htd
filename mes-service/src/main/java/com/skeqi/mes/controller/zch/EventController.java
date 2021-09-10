package com.skeqi.mes.controller.zch;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.pojo.qh.CMesMaterialInstanceT;
import com.skeqi.mes.service.qh.CMesMaterialInstanceTService;
import com.skeqi.mes.service.qh.PMesTrackingTService;
import com.skeqi.mes.service.qh.RMesTrackingTService;
import com.skeqi.mes.util.StringUtil;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.zch.EventService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 事件管理
 * @author SKQ
 *
 */
@Controller
@RequestMapping(value = "eve", produces = MediaType.APPLICATION_JSON)
@Api(value = "事件管理", description = "事件管理", produces = MediaType.APPLICATION_JSON)
public class EventController {

	@Autowired
	EventService service;

	/**
	 * 下线
	 */
	@Resource
	private PMesTrackingTService pMesTrackingTService;

	/**
	 * 上线
	 */
	@Resource
	private RMesTrackingTService rMesTrackingTService;

	@Autowired
	private CMesMaterialInstanceTService instanceTService;

	@RequestMapping(value = "/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询事件列表", notes = "查询事件列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "OBJECT_TYPE", value = "对象类型", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "OBJECT_ID", value = "对象ID", required = false, paramType = "query", dataType = "String")
			 })
	@ResponseBody
	public Rjson findList(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;
		List<Map<String, Object>> listNew = null;
		ArrayList<String> idList = new ArrayList<>();

		Map<String, Object> map = new HashMap<>();

		try {
			map.put("OBJECT_TYPE", request.getParameter("OBJECT_TYPE"));
			map.put("OBJECT_ID", request.getParameter("OBJECT_ID"));

			Integer pageNum = 1;
			Integer pageSize = 6;
			if(request.getParameter("pageNum") != null){
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if(request.getParameter("pageSize") != null){
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}

			PageHelper.startPage(pageNum, pageSize);
			list = service.findEventIds(map);
			list.forEach(item -> idList.add( ((Integer) item.get("ID")).toString()));
			if (idList.size() > 0) {
				listNew = service.findEventById(idList);
				list.clear();
				list.addAll(listNew);
			}

			PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);

			System.out.println("userName: " + JSONObject.toJSONString(map));

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findOrderEventByOrderId", method = RequestMethod.POST)
	@ApiOperation(value = "按对象id和类型查询事件列表", notes = "按对象id和类型查询事件列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "OBJECT_TYPE", value = "对象类型", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "OBJECT_ID", value = "对象ID", required = false, paramType = "query", dataType = "String")
	})
	@ResponseBody
	public Rjson findOrderEventByOrderId(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;

		Map<String, Object> map = new HashMap<>();

		try {
			map.put("OBJECT_TYPE", request.getParameter("OBJECT_TYPE"));
			map.put("OBJECT_ID", request.getParameter("OBJECT_ID"));

			Integer pageNum = 1;

			Integer pageSize = 6;
			if(request.getParameter("pageNum") != null){
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if(request.getParameter("pageSize") != null){
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}

			PageHelper.startPage(pageNum, pageSize);
			list=service.findOrderEventByOrderId(map);
			PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);

			System.out.println("userName: " + JSONObject.toJSONString(map));

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/findEventTimeShaft", method = RequestMethod.POST)
	@ApiOperation(value = "查询订单时间轴时间", notes = "查询订单时间轴时间")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "OBJECT_TYPE", value = "对象类型", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "OBJECT_ID", value = "对象ID", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "EVENT", value = "事件类型", required = false, paramType = "query", dataType = "String")})
	@ResponseBody
	public Rjson findEventTimeShaft(HttpServletRequest request) throws ServicesException {

		List<Map<String, Object>> list = null;

		Map<String, Object> map = new HashMap<>();

		try {
			map.put("EVENT",request.getParameter("EVENT"));
			map.put("OBJECT_TYPE", request.getParameter("OBJECT_TYPE"));
			map.put("OBJECT_ID", request.getParameter("OBJECT_ID"));

			list=service.findEventTimeShaft(map);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@ApiOperation(value = "查询物料事件列表", notes = "查询物料事件列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "objectId", value = "对象id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "event", value = "事件类型", required = false, paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "当前页码 ", required = false, paramType = "query") })
	@RequestMapping(value = "findMaterialEventList", method = RequestMethod.POST)
	@ResponseBody
	public Rjson findEventListMaterial(HttpServletRequest request){
		try{
			String  objectId = EqualsUtil.string(request, "objectId", "对象id", false);
			String event = EqualsUtil.string(request, "event", "事件类型", false);
			String materialType = EqualsUtil.string(request, "materialType", "物料类型", false);
			Integer pageNumber = EqualsUtil.pageNumber(request);

			HashMap<String, Object> map = new HashMap<>();
			map.put("objectId",objectId);
			map.put("event",event);
			map.put("objectType","物料");
			if (!StringUtils.isEmpty(materialType)){
				map.put("objectType",materialType);
			}
			PageHelper.startPage(pageNumber, 10);
			List<Map<String,Object>> allMaterialEvent = service.findMaterialEventList(map);
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(allMaterialEvent, 5);
			return Rjson.success("查询成功",pageInfo);
		}catch (Exception e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	@ApiOperation(value = "物料实例条件模糊查询事件列表", notes = "物料实例条件模糊查询事件列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "objectId", value = "对象id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "event", value = "事件类型", required = false, paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "当前页码 ", required = false, paramType = "query") })
	@RequestMapping(value = "findMaterialEventListLike", method = RequestMethod.POST)
	@ResponseBody
	public Rjson findMaterialEventListLike(HttpServletRequest request){
		try{
			String  objectId = EqualsUtil.string(request, "objectId", "对象id", false);
			String event = EqualsUtil.string(request, "event", "事件类型", false);
			String materialType = EqualsUtil.string(request, "materialType", "物料类型", false);
			Integer pageNumber = EqualsUtil.pageNumber(request);

			HashMap<String, Object> map = new HashMap<>();
			map.put("objectId",objectId);
			map.put("event",event);
			map.put("objectType","物料");
			if (!StringUtils.isEmpty(materialType)){
				map.put("objectType",materialType);
			}
			PageHelper.startPage(pageNumber, 10);
			List<Map<String,Object>> allMaterialEvent = service.findMaterialEventListLike(map);
			PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(allMaterialEvent, 5);
			return Rjson.success("查询成功",pageInfo);
		}catch (Exception e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	@ApiOperation(value = "查询实例原料信息", notes = "查询实例原料信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "objectId", value = "对象id", required = true, paramType = "query"),
			})
	@RequestMapping(value = "findMaterialByMaterialSn", method = RequestMethod.POST)
	@ResponseBody
	public Rjson findMaterialByMaterialSn(HttpServletRequest request){
		List<CMesMaterialInstanceT> materialInstanceTS = new ArrayList<>();
		List<CMesMaterialInstanceT> cMesMaterialInstanceTS = new ArrayList<>();
		try{
			String  objectId = EqualsUtil.string(request, "objectId", "对象id", false);

			HashMap<String, Object> map = new HashMap<>();
			map.put("OBJECT_ID",objectId);
			map.put("OBJECT_TYPE","成品");
			map.put("PARAMETER4","不为空");
			List<Map<String,Object>> allMaterialEvent = service.findOrderEventByOrderId(map);

			if (allMaterialEvent != null&&allMaterialEvent.size()>0) {
				for (Map<String, Object> objectMap : allMaterialEvent) {
					//查询物料实例表得到产品原料信息
					String parameter4 = (String) objectMap.get("PARAMETER4");
					Integer id = Integer.valueOf(parameter4);
					List<CMesMaterialInstanceT> instanceTS = instanceTService.findMaterialById(id);
					for (CMesMaterialInstanceT instanceT : instanceTS) {
						materialInstanceTS.add(instanceT);
					}
				}
				//去重
				HashSet<CMesMaterialInstanceT> tHashSet = new HashSet<>();
				tHashSet.addAll(materialInstanceTS);
				cMesMaterialInstanceTS = new ArrayList<>(tHashSet);
			}
			return Rjson.success("查询成功",cMesMaterialInstanceTS);
		}catch (Exception e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}


	@ApiOperation(value = "查询工单生产事件", notes = "查询工单生产事件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "workOrderId", value = "工单id", required = true, paramType = "query"),
	})
	@RequestMapping(value = "findWorkOrderEvent", method = RequestMethod.POST)
	@ResponseBody
	public Rjson findWorkOrderEvent(HttpServletRequest request){

		try{
			Integer  workOrderId = EqualsUtil.integer(request, "workOrderId", "工单id", true);

			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);
			PageHelper.startPage(pageNumber, pageSize);

			//查询事件
			List<Map<String,Object>> list = service.findWorkOrderByWorkOrderId(workOrderId);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list, 5);
			return Rjson.success("查询成功",pageInfo);
		}catch (Exception e){
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}
}
