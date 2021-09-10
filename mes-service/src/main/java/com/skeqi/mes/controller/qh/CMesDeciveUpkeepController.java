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
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesDeviceUpkeep;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.fqz.DeviceRepairService;
import com.skeqi.mes.service.fqz.DeviceUpkeepService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * 设备保养
 */

@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "设备保养", description = "设备保养", produces = MediaType.APPLICATION_JSON)
public class CMesDeciveUpkeepController {

	@Autowired
	private DeviceRepairService rservice;

	@Autowired
	private DeviceUpkeepService uservice;

	@RequestMapping(value = "/devicesUpkeep/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询设备保养信息", notes = "可根据多条件查询设备保养信息")
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
/*				String name1 = new String(dname.getBytes("ISO-8859-1"),"utf-8");*/
				map.put("deviceName", deviceName);
			}
			if(lineId!=null && lineId!=0 && !lineId.equals("")){
				map.put("lineId", lineId);
			}else{
				map.put("lineId","0");
			}
			List<CMesDeviceUpkeep> findAll = uservice.findAll(map);
			for (CMesDeviceUpkeep cMesDeviceUpkeep : findAll) {
				Integer findTime = uservice.findTime(cMesDeviceUpkeep.getId());  //计算剩余寿命
				if(findTime<=1){
					cMesDeviceUpkeep.setSurplusMaintain(1);
				}else{
					cMesDeviceUpkeep.setSurplusMaintain(0);
				}
			}
			JSONObject json = new JSONObject();

			try {
				List<CMesLineT> findLine = rservice.findLine();
				List<CMesDeviceT> findDevice = rservice.findDevice();
				PageInfo<CMesDeviceUpkeep> pageinfo = new PageInfo<CMesDeviceUpkeep>(findAll);

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
	@RequestMapping(value = "/devicesUpkeep/add", method = RequestMethod.POST)
	@ApiOperation("新增设备保养")
	@OptionalLog(module="设备", module2="设备保养", method="新增设备保养")
	public JSONObject add(@ModelAttribute CMesDeviceUpkeep cUpkeep) {
		JSONObject json = new JSONObject();

		try {

			if (uservice.insertUpkeep(cUpkeep) == 1) {
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
	@RequestMapping(value = "/devicesUpkeep/alter", method = RequestMethod.POST)
	@ApiOperation(value = "修改设备保养信息", notes = "修改设备保养信息")
	@ResponseBody
	@ApiResponses({
			// code重复的情况下，第一个声明的生效。
			@ApiResponse(code = 200, message = "修改成功"), @ApiResponse(code = 202, message = "修改失败") })
	@OptionalLog(module="设备", module2="设备保养", method="编辑设备保养")
	public JSONObject alter(HttpServletRequest request, @ModelAttribute CMesDeviceUpkeep cUpkeep) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			if (uservice.updateupkeep(cUpkeep)==1) {
				json.put("code", 0);
				json.put("msg", "成功");
			}else{
				json.put("code", 1);
				json.put("msg", "失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 2);
			json.put("msg", "未知错误");
		}
		return json;
	}



	@RequestMapping(value = "/devicesUpkeep/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询设备保养信息", notes = "根据id查询设备保养信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "设备保养id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findById(Integer id) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			CMesDeviceUpkeep upkeep = uservice.findByid(id.toString());
			json.put("code", 0);
			json.put("msg", upkeep);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

//	@RequestMapping("/editupkeep")
//	@ResponseBody
//	public Map<String,Object> editupkeep(HttpServletRequest request){
//		Map<String,Object> map = new HashMap<>();
//		CMesDeviceUpkeep c = new CMesDeviceUpkeep();
//		String id = request.getParameter("id");
//		String lineId = request.getParameter("lineids");
//		String mnames = request.getParameter("mnames");
//		String upkeepperson = request.getParameter("upkeeppersons");
//		String emp = request.getParameter("emps").trim();
// 		String note = request.getParameter("notes").trim();
// 		String periods = request.getParameter("periods").trim();
//		c.setId(Integer.parseInt(id));
//		c.setLineId(Integer.parseInt(lineId));
//		c.setDeviceName(mnames);
//		c.setUpkeepPerson(upkeepperson);
//		c.setEmp(emp);
//		c.setNote(note);
//		c.setMaintainCycle(Integer.parseInt(periods));
//		try {
//			uservice.updateupkeep(c);
//			map.put("msg",1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//		}
//		return map;
//	}


	@RequestMapping(value = "/devicesUpkeep/delete", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除设备保养信息", notes = "根据id删除设备保养信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "责任id", required = true, dataType = "Integer")
	@ResponseBody
	@OptionalLog(module="设备", module2="设备保养", method="删除设备保养")
	public JSONObject delete(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			uservice.deleteupkeep(id.toString());
			json.put("code", 0);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}



	@RequestMapping(value = "/devicesUpkeep/devicesMaintain", method = RequestMethod.POST)
	@ApiOperation(value = "根据id进行设备保养", notes = "根据id进行设备保养")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "id", value = "设备id", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query", name = "value", value = "保养时长", required = true, dataType = "String")
		 })
	@ResponseBody
	@OptionalLog(module="设备", module2="设备保养", method="进行设备保养")
	public JSONObject devicesMaintain(HttpServletRequest request, @RequestParam String id,@RequestParam String value) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			uservice.updateDate(id, value);
			json.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}
//	@RequestMapping("/orverupkeep")
//	@ResponseBody
//	public Map<String,Object> orverupkeep(HttpServletRequest request){
//		Map<String,Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		String value = request.getParameter("value");
//		try {
//			uservice.updateDate(id, value);
//			map.put("msg",1);
//		} catch (Exception e) {
//			map.put("msg",2);
//			e.printStackTrace();
//		}
//		return map;
//	}


}
