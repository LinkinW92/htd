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
import com.skeqi.mes.service.all.AssembleKeypartPService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author liu 更新物料的数据
 *
 */
@RestController
@RequestMapping("api")
public class AssembleKeypartPController {

	@Autowired
	CMesWebApiLogsService logsService;


	@Autowired
	private AssembleKeypartPService assembleKeypartPService;

	@Autowired
	EventService serviceEvent;

	// 该bean的存在，仅仅是为了 JSONObject 工具类在系统启动时初始化，可以使tomcat启动后的第一次接口请求快65ms。
	private JSONObject jsonPlaceholder = JSONObject.parseObject("{}");

	@RequestMapping(value="assembleKeypart",method = RequestMethod.POST)
	public void assembleKeypart(HttpServletRequest request,HttpServletResponse response) throws IOException {

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
		dx.setApiName("assembleKeypart");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		try {
			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);
			// 获取总成号
			String sn = (String) json.get("sn");
			// 获取物料条码
			String barcode = (String) json.get("barcode");
			// 获取物料名称
			String materialName = (String) json.get("materialName");
			// 获取工位名称
			String station = (String) json.get("station");
			// 获取员工号
			String emp = (String) json.get("emp");

			// 如果获取的数据部分为空
			if (sn==null || barcode==null || materialName==null || station==null
					|| emp==null) {
				jo.put("isSuccess", false);
				jo.put("errMsg", "输入信息不完整");
				data.put("code", "18");
				data.put("r", 18);
				jo.put("result", data);
				response.getWriter().append(str);
			} else {
				// 事件添加
				Map<String, Object> map = new HashMap<>();
				map.put("sn", sn);
				map.put("materialName", materialName);
				map.put("station", station);
				Map<String, Object> mapResult = serviceEvent.getKeyPartMaterialId(map);
				Map<String, Object> mapEvent = new HashMap<>();
				mapEvent.put("OBJECT_TYPE", "成品");
				mapEvent.put("OBJECT_ID", barcode);
				mapEvent.put("EVENT", "装配物料");
				mapEvent.put("PARAMETER2", station);
				if(mapResult != null){
					mapEvent.put("PARAMETER4", map.get("MATERIAL_INSTANCE_ID"));
				}
				serviceEvent.addEvent(mapEvent);

				response.getWriter().append(assembleKeypartPService.assembleKeypart(sn, barcode, materialName, station,
						emp, jo).toJSONString());
//				return assembleKeypartPService.assembleKeypart(snBarcode, materialBarcode, materialName, stationName,
//						emp, jo);


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



	@RequestMapping(value="checkMaterial",method = RequestMethod.POST)
	public void checkMaterial(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String str = IOUtils.toString(request.getInputStream(),request.getCharacterEncoding());

		response.setContentType("application/json");

		JSONObject jo = new JSONObject();
		JSONObject data = new JSONObject();
		if (str == null || str == "") {
			jo.put("isSuccess", false);
			jo.put("errMsg", "发送数据为空");
//			data.put("code", "201");
//			data.put("r", "201");
			jo.put("result", false);
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("checkMaterial");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		try {

			// 将接受到的字符串转换为json数组
			JSONObject json = JSONObject.parseObject(str);
			// 获取计划名称
			String barcode = json.getString("barcode");
			String station = json.getString("station");
			String step = json.getString("step");
			String line = json.getString("line");
			String sn = json.getString("sn");

			if (barcode==null || station==null || step==null || line==null
					|| sn==null && Integer.parseInt(step) > 0) {
				jo.put("isSuccess", false);
				jo.put("errMsg", "输入信息不完整");
//				data.put("code", "18");
//				data.put("r", "18");
				jo.put("result", false);
				response.getWriter().append(jo.toJSONString());
			} else {
				response.getWriter().append(assembleKeypartPService.checkMaterial(barcode, station, step, line, sn,
						jo).toJSONString());
//				return assembleKeypartPService.checkMaterial(materialBarcode, stationInName, stepNo, lineName, snMaterial,
//						jo);

				// 事件添加
				Map<String, Object> map = new HashMap<>();
				map.put("NAME", line);
				Map<String, Object> mapResult = serviceEvent.getLineCode(map);
				Map<String, Object> mapEvent = new HashMap<>();
				mapEvent.put("OBJECT_TYPE", "成品");
				mapEvent.put("OBJECT_ID", sn);
				mapEvent.put("EVENT", "检验物料");
				if(mapResult != null){
					mapEvent.put("PARAMETER1", mapResult.get("code"));
				}
				mapEvent.put("PARAMETER2", station);
				mapEvent.put("PARAMETER3", step);
				serviceEvent.addEvent(mapEvent);
			}

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			jo.put("isSuccess", false);
			jo.put("errMsg", "出现异常");
//			data.put("code", "999");
//			data.put("r", "999");
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
