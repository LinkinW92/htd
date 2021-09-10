package com.skeqi.mes.controller.all;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import com.skeqi.mes.service.zch.EventService;
import com.skeqi.mes.service.all.AssembleLeakagePService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author liu 更新气密性数据
 *
 */
@RestController
@RequestMapping("api")
public class AssembleLeakagePController {

	@Autowired
	CMesWebApiLogsService logsService;

	@Autowired
	private AssembleLeakagePService assembleLeakagePService;

	@Autowired
	EventService serviceEvent;

	@RequestMapping(value="assembleWeigh",method = RequestMethod.POST)
	public void assembleWeigh(HttpServletRequest request,HttpServletResponse response) throws IOException {
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
		dx.setApiName("assembleWeigh");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		try {

			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);
			// 获取总称码
			String snBarcode = (String) json.get("snBarcode");
			// 获取工位名称
			String stationName = (String) json.get("stationName");
			// 称重值
			String weighValues = (String) json.get("weighValues");
			// 员工号
			String emp = (String) json.get("emp");

			if (snBarcode.isEmpty() || stationName.isEmpty() || weighValues.isEmpty() || emp.isEmpty()) {
				jo.put("isSuccess", false);
				jo.put("errMsg", "输入信息不完整");
				data.put("code", "18");
				data.put("r", "18");
				jo.put("result", data);
				response.getWriter().append(jo.toJSONString());
			} else {
				response.getWriter().append(assembleLeakagePService.assembleWeigh(snBarcode, stationName, weighValues, emp, jo).toJSONString());
//				return assembleWeighPService.assembleWeigh(snBarcode, stationName, weighValues, emp, jo);

			}

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			jo.put("isSuccess", false);
			jo.put("errMsg", "更新信息失败");
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


	@RequestMapping(value="assembleLeakage",method = RequestMethod.POST)
	public void assembleLeakage(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());

		response.setContentType("application/json");

		JSONObject jo = new JSONObject();
		JSONObject data = new JSONObject();
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			jo.put("errMsg", "发送数据为空");
//			data.put("r", "201");
//			data.put("code", "201");
			jo.put("result", false);

			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("assembleLeakage");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		try {

			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);
			// 获取总称码
			String snBarcode = json.getString("sn");
			// 气密性泄露值
			String lValues = json.getString("l");
			// 气密性压力
			String pValues = json.getString("p");
			// 气密性结果
			String rValues = json.getString("r");
			// 获取工位名称
			String stationName = json.getString("station");
			// 获取员工号
			String emp =  json.getString("emp");

			if (snBarcode.isEmpty() || lValues.isEmpty() || pValues.isEmpty() || rValues.isEmpty()
					|| stationName.isEmpty() || emp.isEmpty()) {
				jo.put("isSuccess", false);
				jo.put("errMsg", "输入信息不完整");
//				data.put("code", "18");
//				data.put("r", "18");
				jo.put("result", false);

				response.getWriter().append(jo.toJSONString());
			} else {
				response.getWriter().append(assembleLeakagePService.assembleLeakage(snBarcode, lValues, pValues, rValues, stationName, emp,
						jo).toJSONString());
//				return assembleLeakagePService.assembleLeakage(snBarcode, lValues, pValues, rValues, stationName, emp,
//						jo);

				// 事件添加
				Map<String, Object> mapEvent = new HashMap<>();
				mapEvent.put("OBJECT_TYPE", "成品");
				mapEvent.put("OBJECT_ID", snBarcode);
				mapEvent.put("EVENT", "气密性测试");
				mapEvent.put("PARAMETER2", stationName);
				serviceEvent.addEvent(mapEvent);

			}

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			jo.put("isSuccess", false);
			jo.put("errMsg", "更新信息失败");
//			data.put("code", "17");
//			data.put("r", "17");
			jo.put("result", false);
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
