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
import com.skeqi.mes.pojo.CMesDeviceSpotT;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesIqcCheckT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.dgl.CMesDeviceSpotService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 设备点检
 */

@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "设备点检", description = "设备点检", produces = MediaType.APPLICATION_JSON)
public class CMesDeciveSpotController {
	@Autowired
	CMesDeviceSpotService spotService;

	@RequestMapping(value = "/devices/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询设备点检信息", notes = "可根据多条件查询设备点检信息")
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
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("deviceName", deviceName);
		map.put("lineId", lineId);


		JSONObject json = new JSONObject();

		try {
			List<CMesDeviceSpotT> DeviceList = spotService.findDeviceSpotList(map);
			PageInfo<CMesDeviceSpotT> pageInfo = new PageInfo<>(DeviceList);
			List<CMesLineT> lineList = spotService.lineList();

			json.put("code", 0);
			json.put("msg", pageInfo);
			json.put("lineList", lineList);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}



	@RequestMapping(value = "/devices/findBylineId", method = RequestMethod.POST)
	@ApiOperation(value = "根据产线id查询设备信息", notes = "根据id查询设备信息")
	@ApiImplicitParam(paramType = "query", name = "lineId", value = "产线id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findBylineId(Integer lineId) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			List<CMesDeviceT> cMesDeviceTs = spotService.findlineById(lineId);
			json.put("code", 0);
			json.put("msg", cMesDeviceTs);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/devices/add", method = RequestMethod.POST)
	@ApiOperation("新增设备检点")
	public JSONObject add(@ModelAttribute CMesDeviceSpotT cMesDeviceSpotT) {
		JSONObject json = new JSONObject();

		try {

			if (spotService.addDeviceSpot(cMesDeviceSpotT) == 1) {
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


	@RequestMapping(value = "/devices/delete", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除设备点检信息", notes = "根据id删除设备点检信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "责任id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject delete(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			spotService.delDeviceSpot(id);
			json.put("code", 0);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	@RequestMapping(value = "/devices/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询设备点检信息", notes = "根据id查询设备点检信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "设备检点id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findById(Integer id) throws ServicesException {
		JSONObject json = new JSONObject();


		try {
			CMesDeviceSpotT deviceSpotT = spotService.findByDeviceSpotId(id);
			json.put("code", 0);
			json.put("msg", deviceSpotT);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping(value = "/devices/alter", method = RequestMethod.POST)
	@ApiOperation(value = "修改设备点检信息", notes = "修改设备点检信息")
	@ResponseBody
	@ApiResponses({
			// code重复的情况下，第一个声明的生效。
			@ApiResponse(code = 200, message = "修改成功"), @ApiResponse(code = 202, message = "修改失败") })
	public JSONObject alter(HttpServletRequest request, @ModelAttribute CMesDeviceSpotT cMesDeviceSpotT) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
			spotService.updateDeviceSpot(cMesDeviceSpotT);
			json.put("code", 0);
			json.put("msg", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 2);
			json.put("msg", "未知错误");
		}
		return json;
	}

}
