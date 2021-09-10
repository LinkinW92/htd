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
import com.skeqi.mes.service.all.AssembleTrayPService;
import com.skeqi.mes.service.all.CMesWebApiLogsService;
import com.skeqi.mes.util.DateUtil;

/**
 *
 * @author liu 更新托盘号
 *
 */
@RestController
@RequestMapping("api")
public class AssembleTrayPController {
	@Autowired
	CMesWebApiLogsService logsService;

	@Autowired
	private AssembleTrayPService assembleTrayPService;

	@RequestMapping(value="assembleTray",method = RequestMethod.POST)
	public void assembleTray(HttpServletRequest request,HttpServletResponse response) throws IOException {
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
		dx.setApiName("assembleTray");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		try {

			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);
			// 获取总称码
			String snBarcode = (String) json.get("snBarcode");
			// 获取工位名称
			String stationName = (String) json.get("stationName");
			// 产线名称
			String lineName = (String) json.get("lineName");
			// 托盘号
			String trayNum = (String) json.get("trayNum");

			if (snBarcode.isEmpty() || stationName.isEmpty() || lineName.isEmpty() || trayNum.isEmpty()) {

				jo.put("isSuccess", false);
				jo.put("errMsg", "输入信息不完整");
				data.put("code", "18");
				data.put("r", "18");
				jo.put("result", data);
				response.getWriter().append(jo.toJSONString());
			} else {
				response.getWriter().append(assembleTrayPService.assembleTray(snBarcode, stationName, lineName, trayNum, jo).toJSONString());
//				return assembleTrayPService.assembleTray(snBarcode, stationName, lineName, trayNum, jo);
			}

		} catch (Exception e) {
			jo.put("isSuccess", false);
			jo.put("msg", "更新信息失败");
			data.put("code", "17");
			data.put("r", "17");
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


	@RequestMapping(value="checkTray",method = RequestMethod.POST)
	public void checkTray(HttpServletRequest request,HttpServletResponse response) throws IOException {
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
		dx.setApiName("checkTray");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		try {

			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);

			// 获取总称码
			String snBarcode = (String) json.get("snBorcode");

			// 获取工位
			String station = (String) json.get("station");
			// 获取产线名
			String line = (String) json.get("line");
			// 获取托盘号
			String trayNum = (String) json.get("trayNum");

			if (snBarcode.isEmpty() || station.isEmpty() || line.isEmpty() || trayNum.isEmpty()) {
				jo.put("isSuccess", false);
				jo.put("errMsg", "输入信息不完整");
				data.put("code", "18");
				data.put("r", "18");
				jo.put("result", data);
				response.getWriter().append(jo.toJSONString());
			} else {
				response.getWriter().append(assembleTrayPService.checkTray(line, trayNum, jo).toJSONString());
//				return assembleTrayPService.checkTray(line, trayNum, jo);

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
