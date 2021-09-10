package com.skeqi.mes.controller.alarm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.alarm.CAlarmEvent;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannels;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannelsType;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationMethod;
import com.skeqi.mes.pojo.alarm.CAlarmProblems;
import com.skeqi.mes.service.alarm.CAlarmEventService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

@RestController
@RequestMapping("api/AlarmEvent")
public class CAlarmEventController {

	@Autowired
	CAlarmEventService service;


	/**
	 * 查询事件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findAlarmEventList",method = RequestMethod.POST)
	public Rjson findAlarmEventList(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);
			String userName =  EqualsUtil.string(request, "userName", "用户名称", true);

			PageInfo<CAlarmEvent> pageInfo = service.findAlarmEventList(userName, pageNumber, pageSize);

			return new Rjson().success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 新增事件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addAlarmEvent",method = RequestMethod.POST)
	public Rjson addAlarmEvent(HttpServletRequest request) {
		try {
			String userName =  EqualsUtil.string(request, "userName", "用户名称", true);
			String event =  EqualsUtil.string(request, "event", "事件", true);
			String note =  EqualsUtil.string(request, "note", "说明", true);

			JSONObject json = new JSONObject();
			json.put("userName", userName);
			json.put("event", event);
			json.put("note", note);

			service.addAlarmEvent(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 更新事件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateAlarmEvent",method = RequestMethod.POST)
	public Rjson updateAlarmEvent(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			String event =  EqualsUtil.string(request, "event", "修改前事件", true);
			String event1 =  EqualsUtil.string(request, "event", "修改后事件", true);
			String note =  EqualsUtil.string(request, "note", "说明", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("event", event);
			json.put("event1", event1);
			json.put("note", note);

			service.updateAlarmEvent(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 删除事件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteAlarmEvent",method = RequestMethod.POST)
	public Rjson deleteAlarmEvent(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteAlarmEvent(id);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}








}
