package com.skeqi.mes.controller.yp.equipment.Information;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.equipment.Information.EquipmentInformationService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 设备资料
 * @date 2020-12-14
 */
@RestController
@RequestMapping("/api/EquipmentInformation")
public class EquipmentInformationController {

	@Autowired
	EquipmentInformationService service;

	/**
	 * @explain 查询集合
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {

			int pageSize = EqualsUtil.pageSize(request);
			int pageNumber = EqualsUtil.pageNumber(request);

			String number = EqualsUtil.string(request, "number", "编号", false);
			String name = EqualsUtil.string(request, "name", "名称", false);
			String model = EqualsUtil.string(request, "model", "型号", false);
			Integer workState = EqualsUtil.integer(request, "workState", "工作状态", false);
			Integer functionState = EqualsUtil.integer(request, "functionState", "运行状态", false);
			String ipAdd = EqualsUtil.string(request, "ipAdd", "ip地址", false);
			Integer lineId = EqualsUtil.integer(request, "lineId", "产线", false);

			JSONObject json = new JSONObject();
			json.put("number", number);
			json.put("name", name);
			json.put("model", model);
			json.put("workState", workState);
			json.put("functionState", functionState);
			json.put("ipAdd", ipAdd);
			json.put("lineId", lineId);

			PageHelper.startPage(pageNumber, pageSize);
			List<Map<String, Object>> list = service.list(json);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 新增
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	@Transactional
	@OptionalLog(module="设备", module2="设备资料", method="新增设备资料")
	public Rjson add(HttpServletRequest request) {
		try {

			String number = EqualsUtil.string(request, "number", "编号", true);
			String name = EqualsUtil.string(request, "name", "名称", true);
			String model = EqualsUtil.string(request, "model", "型号", true);
			String brand = EqualsUtil.string(request, "brand", "品牌", true);
			String type = EqualsUtil.string(request, "type", "类型", true);
			Integer maintenancePeriod = EqualsUtil.integer(request, "maintenancePeriod", "保养周期", true);
			Integer spotCheckCycle = EqualsUtil.integer(request, "spotCheckCycle", "点检周期", true);
//			Integer workState = EqualsUtil.integer(request, "workState", "工作状态", true);
//			Integer functionState = EqualsUtil.integer(request, "functionState", "运行状态", true);
			String plcAdd = EqualsUtil.string(request, "plcAdd", "PLC地址", true);
			String ipAdd = EqualsUtil.string(request, "ipAdd", "ip地址", true);
			Integer port = EqualsUtil.integer(request, "port", "端口号", true);
			Integer lineId = EqualsUtil.integer(request, "lineId", "产线", true);
			String stationIds = EqualsUtil.string(request, "stationIds", "工位", true);
			String describe = EqualsUtil.string(request, "describe", "描述", true);

			JSONObject json = new JSONObject();
			json.put("number", number);
			json.put("name", name);
			json.put("model", model);
			json.put("brand", brand);
			json.put("type", type);
			json.put("maintenancePeriod", maintenancePeriod);
			json.put("spotCheckCycle", spotCheckCycle);
//			json.put("workState", workState);
//			json.put("functionState", functionState);
			json.put("plcAdd", plcAdd);
			json.put("ipAdd", ipAdd);
			json.put("port", port);
			json.put("lineId", lineId);
			json.put("stationIds", stationIds);
			json.put("describe", describe);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 更新
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@Transactional
	@OptionalLog(module="设备", module2="设备资料", method="编辑设备资料")
	public Rjson update(HttpServletRequest request) {
		try {

			Integer id = EqualsUtil.integer(request, "id", "id", true);
			String number = EqualsUtil.string(request, "number", "编号", true);
			String name = EqualsUtil.string(request, "name", "名称", true);
			String model = EqualsUtil.string(request, "model", "型号", true);
			String brand = EqualsUtil.string(request, "brand", "品牌", true);
			String type = EqualsUtil.string(request, "type", "类型", true);
			Integer maintenancePeriod = EqualsUtil.integer(request, "maintenancePeriod", "保养周期", true);
			Integer spotCheckCycle = EqualsUtil.integer(request, "spotCheckCycle", "点检周期", true);
//			Integer workState = EqualsUtil.integer(request, "workState", "工作状态", true);
//			Integer functionState = EqualsUtil.integer(request, "functionState", "运行状态", true);
			String plcAdd = EqualsUtil.string(request, "plcAdd", "PLC地址", true);
			String ipAdd = EqualsUtil.string(request, "ipAdd", "ip地址", true);
			Integer port = EqualsUtil.integer(request, "port", "端口号", true);
			Integer lineId = EqualsUtil.integer(request, "lineId", "产线", true);
			String stationIds = EqualsUtil.string(request, "stationIds", "工位", true);
			String describe = EqualsUtil.string(request, "describe", "描述", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("number", number);
			json.put("name", name);
			json.put("model", model);
			json.put("brand", brand);
			json.put("type", type);
			json.put("maintenancePeriod", maintenancePeriod);
			json.put("spotCheckCycle", spotCheckCycle);
//			json.put("workState", workState);
//			json.put("functionState", functionState);
			json.put("plcAdd", plcAdd);
			json.put("ipAdd", ipAdd);
			json.put("port", port);
			json.put("lineId", lineId);
			json.put("stationIds", stationIds);
			json.put("describe", describe);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	@Transactional
	@OptionalLog(module="设备", module2="设备资料", method="删除设备资料")
	public Rjson delete(HttpServletRequest request) {
		try {

			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.delete(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 查询产线
	 * @return
	 */
	@RequestMapping("/lineList")
	public Rjson lineList(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.lineList();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 通过产线id查询工位
	 * @param request
	 * @return
	 */
	@RequestMapping("/stationListByLineId")
	public Rjson stationListByLineId(HttpServletRequest request) {
		try {
			int lineId = EqualsUtil.integer(request, "lineId", "产线", true);

			List<JSONObject> list = service.stationListByLineId(lineId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/*====================以下是自定义属性====================*/
	/**
	 * @explain 查询自定义列集合
	 * @param request
	 * @return
	 */
	@RequestMapping("/customColumns/list")
	public Rjson customColumnsList(HttpServletRequest request) {
		try {

			int parentId = EqualsUtil.integer(request, "parentId", "设备资料 id", true);

			List<JSONObject> list = service.customColumnsList(parentId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 新增自定义列
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/customColumns/add")
	@OptionalLog(module="设备", module2="设备资料", method="新增设备资料自定义属性")
	public Rjson customColumnsAdd(HttpServletRequest request) {
		try {

			int parentId = EqualsUtil.integer(request, "parentId", "设备资料 id", true);
			String columnName = EqualsUtil.string(request, "columnName", "列名", true);
			String value = EqualsUtil.string(request, "value", "值", true);
			String explain = EqualsUtil.string(request, "explain", "说明", false);

			JSONObject json = new JSONObject();
			json.put("parentId", parentId);
			json.put("columnName", columnName);
			json.put("value", value);
			json.put("explain", explain);

			service.customColumnsAdd(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 更新自定义列
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/customColumns/update")
	@OptionalLog(module="设备", module2="设备资料", method="编辑设备资料自定义属性")
	public Rjson customColumnsUpdate(HttpServletRequest request) {
		try {

			int id = EqualsUtil.integer(request, "id", " id", true);
			int parentId = EqualsUtil.integer(request, "parentId", "设备资料 id", true);
			String columnName = EqualsUtil.string(request, "columnName", "列名", true);
			String value = EqualsUtil.string(request, "value", "值", true);
			String explain = EqualsUtil.string(request, "explain", "说明", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("parentId", parentId);
			json.put("columnName", columnName);
			json.put("value", value);
			json.put("explain", explain);

			service.customColumnsUpdate(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 删除自定义列
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/customColumns/delete")
	@OptionalLog(module="设备", module2="设备资料", method="删除设备资料自定义属性")
	public Rjson customColumnsDelete(HttpServletRequest request) {
		try {

			int id = EqualsUtil.integer(request, "id", " id", true);

			service.customColumnsDelete(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/*====================以下是详情====================*/
	/**
	 * @explain 通过id查询设备 对象
	 * @param request
	 * @return
	 */
	@RequestMapping("/details/objectById")
	public Rjson objectById(HttpServletRequest request) {
		try {

			Integer id = EqualsUtil.integer(request, "id", "id", true);

			JSONObject json = service.objectById(id);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 通过id查询设备 对象
	 * @param request
	 * @return
	 */
	@RequestMapping("/details/objectByNumber")
	public Rjson objectByNumber(HttpServletRequest request) {
		try {

			String number = EqualsUtil.string(request, "number", "number", true);

			JSONObject json = service.objectByNumber(number);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}
