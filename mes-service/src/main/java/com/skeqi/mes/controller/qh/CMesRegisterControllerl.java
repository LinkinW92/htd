package com.skeqi.mes.controller.qh;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.skeqi.mes.util.yp.EqualsUtil;
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
import com.skeqi.mes.pojo.CMesStationLine;
import com.skeqi.mes.pojo.RegisterT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.service.all.SMesRegisterTService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping(value = "api")
@Api(value = "程序注册管理", description = "程序注册管理")
public class CMesRegisterControllerl {

	@Autowired
	SMesRegisterTService registerService;

	@Autowired
	CMesLineTService CMesLineTService;

	@Autowired
	CMesStationTService stationService;

	// 产线列表
	@RequestMapping(value = "/regist/registerList", method = RequestMethod.POST)
	@ApiOperation(value = "程序注册列表", notes = "程序注册列表")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
			@ApiImplicitParam(name = "lineName", value = "所属产线", required = false, paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "stationName", value = "所属工位", required = false, paramType = "query", dataType = "String")
	})
	public JSONObject registerList(HttpServletRequest request) throws Exception {
		Integer pageNum= EqualsUtil.integer(request,"pageNum","页码",false);
		Integer pageSize= EqualsUtil.integer(request,"pageSize","每页记录数",false);
		PageHelper.startPage(null == pageNum?1:pageNum,null == pageSize?10:pageSize);
		String lineName= EqualsUtil.string(request,"lineName","所属产线",false);
		String stationName= EqualsUtil.string(request,"stationName","所属工位",false);
		RegisterT registerT=new RegisterT();
		registerT.setLineName(lineName);
		registerT.setStationName(stationName);
		List<RegisterT> lineList = registerService.findAllRegisterT(registerT);
		PageInfo<RegisterT> pageInfo = new PageInfo<>(lineList);
		JSONObject json = new JSONObject();
		try {

			json.put("code", 0);
			json.put("lineList", lineList);
			json.put("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 根据产线ID查询工位
	@RequestMapping(value = "/regist/findStationByLid", method = RequestMethod.POST)
	@ApiOperation(value = "查询工位信息", notes = "根据前台传来的id查询工位")
	@ApiImplicitParam(paramType = "query", name = "id", value = "产线ID", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findStationByLid(HttpServletRequest request, Integer id) throws ServicesException {

		JSONObject json = new JSONObject();
		try {
			List<CMesStationLine> stationList = CMesLineTService.findStationByLid(id);
			json.put("station", stationList);
			json.put("code", 0);
			json.put("msg", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 新增程序注册
	@ResponseBody
	@RequestMapping(value = "/regist/addLine", method = RequestMethod.POST)
	@ApiOperation("添加程序注册")
	@ApiResponses({
			// code重复的情况下，第一个声明的生效。
			@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 202, message = "添加失败") })
	@OptionalLog(module="基础建模", module2="程序注册", method="新增程序注册")
	public JSONObject addLine(HttpServletRequest request, @ModelAttribute @Valid RegisterT registerT) throws Exception {

		InetAddress ia = InetAddress.getLocalHost();
		System.err.println("id=====" + ia);
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		System.err.println("mac=====" + mac);
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}

			int temp = mac[i] & 0xff;
			String str = Integer.toHexString(temp);

			if (str.length() == 1) {
				sb.append("0" + str);
			} else {
				sb.append(str);
			}
		}
		RegisterT register = new RegisterT();
		register.setHostname(registerT.getHostname());
		register.setIp(registerT.getIp());
		register.setLineId(registerT.getLineId());
		register.setStationId(registerT.getStationId());
		register.setMac(sb.toString());

		JSONObject json = new JSONObject();
		try {
			registerService.addRegisterT(register);
			json.put("code", 0);
			json.put("msg", "成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 2);
			json.put("msg", "未知错误");
		}
		return json;

	}

	@ResponseBody
	@RequestMapping(value = "/regist/toUpdateRegister", method = RequestMethod.POST)
	@ApiOperation("回显数据")
	@ApiImplicitParam(paramType = "query", name = "id", value = "ID", required = true, dataType = "Integer")
	public JSONObject toUpdateRegister(HttpServletRequest request, Integer id) throws Exception {
		RegisterT registerT = new RegisterT();

		JSONObject json = new JSONObject();
		try {
			registerService.findAllRegisterT(registerT);
			json.put("code", 0);
			json.put("msg", "成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 2);
			json.put("msg", "未知错误");
		}
		return json;

	}

	@ResponseBody
	@RequestMapping(value = "/regist/updateRegisterT", method = RequestMethod.POST)
	@ApiOperation("修改程序注册")
	@ApiResponses({
		// code重复的情况下，第一个声明的生效。
		@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 202, message = "失败") })
	@OptionalLog(module="基础建模", module2="程序注册", method="修改程序注册")
	public JSONObject editStation(HttpServletRequest request, @ModelAttribute @Valid RegisterT registerT) throws Exception {
		JSONObject json = new JSONObject();
		try {
			registerService.updadteRegisterT(registerT);
			json.put("code", 0);
			json.put("msg", "成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 2);
			json.put("msg", "未知错误");
		}
		return json;

	}

	@ResponseBody
	@RequestMapping(value = "/regist/delRegisterT", method = RequestMethod.POST)
	@ApiOperation("删除程序注册")
	@ApiResponses({
			// code重复的情况下，第一个声明的生效。
			@ApiResponse(code = 200, message = "成功"), @ApiResponse(code = 202, message = "失败") })
	@ApiImplicitParam(paramType = "query", name = "id", value = "程序注册ID", required = true, dataType = "Integer")
	@OptionalLog(module="基础建模", module2="程序注册", method="删除程序注册")
	public JSONObject delRegisterT(HttpServletRequest request, Integer id) throws Exception {

		JSONObject json = new JSONObject();
		try {
			registerService.delRegisterT(id);
			json.put("code", 0);
			json.put("msg", "成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
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
