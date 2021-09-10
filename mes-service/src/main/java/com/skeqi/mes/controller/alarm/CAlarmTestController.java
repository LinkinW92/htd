package com.skeqi.mes.controller.alarm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.alarm.CAlarmEvent;
import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;
import com.skeqi.mes.service.alarm.CAlarmTestService;
import com.skeqi.mes.service.alarm.CAlarmTimingUgradeService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

@RestController
@RequestMapping("api/AlarmTest")
public class CAlarmTestController {

	@Autowired
	CAlarmTestService service;

	@Autowired
	CAlarmTimingUgradeService s;

	/**
	 * 新增问题
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addProblem",method = RequestMethod.POST)
	public Rjson addProblem(HttpServletRequest request) {
		try {

			String problem = EqualsUtil.string(request, "problem", "问题", true);
			String userName = EqualsUtil.string(request, "userName", "用户名称", true);
			Integer problemLevelId = EqualsUtil.integer(request, "problemLevelId", "问题级别", true);
			Integer eventId = EqualsUtil.integer(request, "eventId", "事件", true);

			JSONObject json = new JSONObject();
			json.put("problem", problem);
			json.put("userName", userName);
			json.put("problemLevelId", problemLevelId);
			json.put("eventId", eventId);

			service.addProblem(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 查询问题级别
	 * @return
	 */
	@RequestMapping(value = "findProblemLevelList",method = RequestMethod.POST)
	public Rjson findProblemLevelList(HttpServletRequest request) {
		try {
			List<CAlarmProblemLevel> list = service.findProblemLevelList();
			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 查询事件
	 * @return
	 */
	@RequestMapping(value = "findAlarmEventList",method = RequestMethod.POST)
	public Rjson findAlarmEventList(HttpServletRequest request) {
		try {
			List<CAlarmEvent> list = service.findAlarmEventList();
			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	@RequestMapping("shengji")
	public void shengji() {
		s.upgrade();
	}



}
