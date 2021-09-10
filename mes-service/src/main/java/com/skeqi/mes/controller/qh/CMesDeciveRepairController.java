package com.skeqi.mes.controller.qh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesDeviceRepairT;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesDeviceUpkeep;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.fqz.DeviceRepairService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 设备维修
 */

@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "设备维修", description = "设备维修", produces = MediaType.APPLICATION_JSON)
public class CMesDeciveRepairController {

	@Autowired
	private DeviceRepairService service;

	@RequestMapping(value = "/devicesRepair/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询设备維修信息", notes = "可根据多条件查询设备維修信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "deviceName", value = "设备名称", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int")
			 })
	@ResponseBody
	public JSONObject findList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6") Integer pageSize, String deviceName, Integer lineId) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);
		Map<String,Object> map = new HashMap<>();
		if(deviceName!=null && !deviceName.equals("")){

			map.put("deviceName", deviceName);
		}
		if(lineId!=null && lineId!=0 && !lineId.equals("")){
			map.put("lineId", lineId);
		}else{
			map.put("lineId","0");
		}

		JSONObject json = new JSONObject();
		try {
			List<CMesDeviceRepairT> findAll = service.findAll(map);
			PageInfo<CMesDeviceRepairT> pageinfo = new PageInfo<CMesDeviceRepairT>(findAll);
			List<CMesDeviceT> findDevice = service.findDevice();
			List<CMesLineT> findLine = service.findLine();
			json.put("code", 0);
			json.put("msg", pageinfo);
			json.put("lineList", findLine);
			json.put("deviceList", findDevice);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/devicesRepair/add", method = RequestMethod.POST)
	@ApiOperation("新增设备维修")
	@OptionalLog(module="设备", module2="设备维修", method="新增设备维修")
	public JSONObject add(@ModelAttribute CMesDeviceRepairT cRepairT) {
		JSONObject json = new JSONObject();

		try {

			if (service.insertRepair(cRepairT) == 1) {
				json.put("code", 0);
				json.put("msg", "新增成功");
			} else {
				json.put("code", 1);
				json.put("msg", "后端异常，新增失败");
			}

		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}
	@RequestMapping(value = "/devicesRepair/delete", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除设备维修信息", notes = "根据id删除设备维修信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "责任id", required = true, dataType = "Integer")
	@ResponseBody
	@OptionalLog(module="设备", module2="设备维修", method="删除设备维修")
	public JSONObject delete(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			service.deleteRepair(id.toString());
			json.put("code", 0);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;

	}

	@RequestMapping(value = "/devicesRepair/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询设备维修信息", notes = "根据id查询设备维修信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "设备维修id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findById(Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			CMesDeviceRepairT findByid = service.findByid(id.toString());
			json.put("code", 0);
			json.put("msg", findByid);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping(value = "/devicesRepair/alter", method = RequestMethod.POST)
	@ApiOperation(value = "修改设备维修信息", notes = "修改设备维修信息")
	@ResponseBody
	@ApiResponses({
			// code重复的情况下，第一个声明的生效。
			@ApiResponse(code = 200, message = "修改成功"), @ApiResponse(code = 202, message = "修改失败") })
	@OptionalLog(module="设备", module2="设备维修", method="编辑设备维修")
	public JSONObject alter(HttpServletRequest request, @ModelAttribute CMesDeviceRepairT cRepairT) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			if (service.updateRepair(cRepairT)==1) {
				json.put("code", 0);
				json.put("msg", "成功");
			}else{
				json.put("code", 1);
				json.put("msg", "失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 2);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "/devicesRepair/findDevicesByLineId", method = RequestMethod.POST)
	@ApiOperation(value = "根据产线id查询设备信息", notes = "根据产线id查询设备信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "产线id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findDevicesByLineId(Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			List<CMesDeviceT> findDevice = service.findDevices(id.toString());
			json.put("code", 0);
			json.put("msg", findDevice);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}
}
