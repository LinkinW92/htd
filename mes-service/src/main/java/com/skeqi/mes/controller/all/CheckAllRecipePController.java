package com.skeqi.mes.controller.all;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.service.all.CMesWebApiLogsService;
import com.skeqi.mes.service.all.CheckAllRecipePService;
import com.skeqi.mes.util.DateUtil;

@RestController
@RequestMapping("api")
public class CheckAllRecipePController {

	@Autowired
	CMesWebApiLogsService logsService;

	@Autowired
	private CheckAllRecipePService checkAllRecipePService;

	@RequestMapping(value="checkAllRecipe",method = RequestMethod.POST)
	public void checkAllRecipe(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());

		response.setContentType("application/json");

		JSONObject jo = new JSONObject();
		JSONObject data = new JSONObject();
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			jo.put("errMsg", "发送数据为空");
			data.put("code", "201");
			data.put("r", "201");
			jo.put("result", data);
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("checkAllRecipe");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		try {

			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			// 产品ID
			Integer productionId = Integer.parseInt((String) json.get("productionIn"));
			// 产线ID
			Integer productionLine = Integer.parseInt((String) json.get("productionLine"));
			//工位类型
			String stationType = (String) json.get("stationType");
			//sn
			String sn = (String) json.get("sn");
			//工位ID
			Integer stationId = Integer.parseInt((String) json.get("stationInId"));

			if (productionId == null || productionLine == null || stationType.isEmpty() || stationId == null) {

				jo.put("isSuccess", false);
				jo.put("errMsg", "输入信息不完整");
				data.put("code", "18");
				data.put("r", "18");
				jo.put("result", data);
				response.getWriter().append(jo.toJSONString());
			} else {
				response.getWriter().append(checkAllRecipePService.checkAllRecipe(sn,productionId, productionLine, stationType, stationId, jo).toJSONString());
//				return checkAllRecipePService.checkAllRecipe(productionId, productionLine, stationType, stationId, jo);
			}

		} catch (Exception e) {

			jo.put("isSuccess", false);
			jo.put("errMsg", "出现异常");
			data.put("code", "999");
			data.put("r", "999");
			jo.put("result", data);
			response.getWriter().append(jo.toJSONString());

		} finally {
			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}
		}

	}


	@RequestMapping(value="checkClientLogin",method = RequestMethod.POST)
	public void checkClientLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());

		response.setContentType("application/json");

		JSONObject jo = new JSONObject();
		JSONObject data = new JSONObject();
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			jo.put("errMsg", "发送数据为空");
			data.put("code", "201");
			data.put("r", "201");
			data.put("rEmptype", "0");
			jo.put("result", data);
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("checkClientLogin");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		try {

			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);
			// 用户名
			String username = (String) json.get("username");
			// 用户密码
			String password = (String) json.get("password");
			// 工位员工
			String stationEmp = (String) json.get("stationEmp");
			//产线员工名称
			String lineEmpName = (String) json.get("lineEmpName");

			if (username.isEmpty() || password.isEmpty() || stationEmp.isEmpty() || lineEmpName.isEmpty()) {
				jo.put("isSuccess", false);
				jo.put("errMsg", "输入信息不完整");
				data.put("code", "18");
				data.put("r", "18");
				data.put("rEmptype", "0");
				jo.put("result", data);
				response.getWriter().append(jo.toJSONString());
			} else {
				response.getWriter().append(checkAllRecipePService.checkClientLogin(username, password, stationEmp, lineEmpName, jo).toJSONString());
//				return checkAllRecipePService.checkClientLogin(username, password, stationEmp, lineEmpName, jo);
			}

		} catch (Exception e) {
			jo.put("isSuccess", false);
			jo.put("errMsg", "出现异常");
			data.put("code", "999");
			data.put("r", "999");
			data.put("rEmptype", "0");
			jo.put("result", data);
			response.getWriter().append(jo.toJSONString());

		} finally {
			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}
		}

	}



	@RequestMapping(value="checkPlanSn",method = RequestMethod.POST)
	public void checkPlanSn(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());

		response.setContentType("application/json");

		JSONObject jo = new JSONObject();
		JSONObject data = new JSONObject();
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			jo.put("errMsg", "发送数据为空");
			data.put("code", "201");
			data.put("r", "201");
			jo.put("result", data);
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("checkPlanSn");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		try {

			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);
			// 获取计划名称
			String snPlan = (String) json.get("snPlan");

			if (snPlan.isEmpty()) {
				jo.put("isSuccess", false);
				jo.put("errMsg", "输入信息不完整");
				data.put("code", "18");
				data.put("r", "18");
				jo.put("result", data);
				response.getWriter().append(jo.toJSONString());
			} else {
				response.getWriter().append(checkAllRecipePService.checkWorkorderSn(snPlan, jo).toJSONString());
//				return checkAllRecipePService.checkPlanSn(snPlan, jo);
			}

		} catch (Exception e) {
			jo.put("isSuccess", false);
			jo.put("errMsg", "出现异常");
			data.put("code", "999");
			data.put("r", "999");
			jo.put("result", data);
			response.getWriter().append(jo.toJSONString());

		} finally {

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}
		}

	}


	@RequestMapping(value="checkProductionP",method = RequestMethod.POST)
	public void checkProduction(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());

		response.setContentType("application/json");

		JSONObject jo = new JSONObject();
		JSONObject data = new JSONObject();
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			jo.put("errMsg", "发送数据为空");
			data.put("code", "201");
			data.put("r", "201");
			jo.put("result", data);
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("checkProductionP");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		try {

			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			// 获取总称码
			String snProduction = (String) json.get("snProduction");
			// 获取工位
			String sId = (String) json.get("sId");

			if (snProduction.isEmpty() || sId.isEmpty()) {
				jo.put("isSuccess", false);
				jo.put("errMsg", "输入信息不完整");
				data.put("code", "18");
				data.put("r", "18");
				jo.put("result", data);
				response.getWriter().append(jo.toJSONString());
			} else {
				response.getWriter().append(checkAllRecipePService.checkProduction(snProduction, sId, jo).toJSONString());
//				return checkAllRecipePService.checkProduction(snProduction, sId, jo);
			}

		} catch (Exception e) {
			jo.put("isSuccess", false);
			jo.put("errMsg", "出现异常");
			data.put("code", "999");
			data.put("r", "999");
			jo.put("result", data);
			response.getWriter().append(jo.toJSONString());

		} finally {
			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}
		}

	}

}
