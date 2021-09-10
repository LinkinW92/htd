package com.skeqi.mes.controller.all;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.service.all.CMesWebApiLogsService;
import com.skeqi.mes.service.all.RMesNextBarcodePEcService;
import com.skeqi.mes.service.zch.EventService;
import com.skeqi.mes.service.all.AssembleBoltPService;
import com.skeqi.mes.util.DateUtil;
import com.skeqi.mes.util.ToolUtils;

@Controller
@RequestMapping("api")
public class RMesNextBarcodePEcController {

	@Autowired
	CMesWebApiLogsService logsService;

	@Autowired
	RMesNextBarcodePEcService barcodePEcService;

	@Autowired
	EventService serviceEvent;

	// 入参
	Integer lineInId;
	// 出参
	Integer rPrint;// 0：成功 >1 失败
	String nextBarcode;
	String nextOneBarcode;
	String labelName;

	Integer temp_plan_count;// 产线计划数量
	Integer temp_plan_level_max;// 最大优先级
	Integer temp_plan_level;
	Integer temp_plan_comdition_count;
	Integer temp_plan_number;
	Integer temp_plan_online_number;
	Integer temp_current_plan_id;// 工单id
	Integer temp_plan_id;// 计划id
	Integer temp_plan_print_count;
	Integer temp_alarm_mark;// 没有计划标记
	String temp_label_rules;// 条码标签
	String temp_label_vr;// 条码日期和流水号格式
	Integer temp_label_type_id;// 条码类型id gb/t 34014 强制规定为id=1
	String temp_label_head;// 条码标签‘#’之前部分
	String temp_label_end;// 条码标签‘#’之后部分
	String temp_label_ymd;// 条码类型中月日年
	String temp_label_code;// 条码类型中流水号
	Integer tmep_code_serial_max;
	Integer temp_production_id;
	String temp_old_sn_code;
	String temp_old_sn_num;
	String temp_current_create_year;// 处理后的年份
	String tmep_current_month;// 当前月份
	String temp_current_create_day;// 处理后的天数
	String temp_print_flag;// 是否打印的标记
	String temp_shift;// 班次
	Integer temp_mark;// 标记
	String temp_exception_msg;
	Integer temp_day;
	Integer temp_year;
	String temp_label_name;// 标签名称
	String temp_date;

	// 保存返回数据
	JSONObject jo = new JSONObject();

	// 保存返回数据
	JSONObject joz = new JSONObject();

	@Autowired
	AssembleBoltPService service;

	@RequestMapping(value = "nextBarcode", method = RequestMethod.POST)
	public synchronized void RMesNextBarcodePEc(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {

		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());

		response.setContentType("application/json");

		// joz.put("param", str);
		if (str == null || str == "") {
			jo.put("isSuccess", true);
			jo.put("errMsg", "发送数据为空");
			response.getWriter().append(jo.toJSONString());
		}

		CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName("nextBarcode");
		dx.setCallTime(DateUtil.getNowDate());
		dx.setParameter(str);

		// 将接受到的字符串转换为json数组
		JSONObject json = JSONObject.parseObject(str);

		try {
			// 获取总称码
			lineInId = Integer.parseInt(json.getString("lineId"));
		} catch (NullPointerException e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			jo.put("isSuccess", false);
			jo.put("errMsg", "参数缺失！");

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());

			/**
			 * 生成日志
			 */
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				e2.printStackTrace();
				jo.put("errMsg", jo.getString("errMsg") + "生成日志出错了!");
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			response.getWriter().append(jo.toJSONString());
			return;
		}

		try {
			// 调用方法主体
			jo = barcodePEcService.main(json);
//			jo.put("isSuccess", true);
//			jo.put("errMsg", "");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			jo.put("isSuccess", false);
			jo.put("errMsg", "更新数据失败！");
		} finally {

//			joz.put("rBolt", rBolt);

			dx.setReturnResult(jo.toJSONString());
			dx.setReturnTime(DateUtil.getNowDate());

			/**
			 * 生成日志
			 */
			try {
				logsService.add(dx);
			} catch (Exception e2) {
				e2.printStackTrace();
				System.err.println("生成日志出错了");
				// TODO: handle exception
			}

			// 事件添加
			Map<String, Object> map = new HashMap<>();
			map.put("lineId", lineInId);
			Map<String, Object> mapResult = serviceEvent.getLineCode(map);
			Map<String, Object> mapEvent = new HashMap<>();
			mapEvent.put("OBJECT_TYPE", "成品");
			if(jo != null && jo.get("result") != null){
				mapEvent.put("OBJECT_ID", jo.getJSONObject("result").get("barcode"));
			}
			mapEvent.put("EVENT", "生成条码");
			if(mapResult != null){
				mapEvent.put("PARAMETER1", mapResult.get("code"));
			}
			serviceEvent.addEvent(mapEvent);

			response.getWriter().append(jo.toJSONString());

		}

	}

}
