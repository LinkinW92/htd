package com.skeqi.mes.controller.alarm.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skeqi.mes.pojo.alarm.CAlarmProblems;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

@RestController
@RequestMapping("openApi/AlarmProblems")
public class ApiAlarmProblemsContoller {

	/**
	 * 新增问题
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addProblems",method = RequestMethod.POST)
	public Rjson addProblems(HttpServletRequest request) {
		try {
			Rjson rjson = (Rjson) request.getAttribute("result");
			if(rjson.getCode()!=200) {
				throw new Exception(rjson.getMsg());
			}

			String problem = EqualsUtil.string(request, "problem", "问题", true);
			Integer eventId = EqualsUtil.integer(request, "eventId", "事件编号", true);

			CAlarmProblems dx = new CAlarmProblems();
			dx.setProblem(problem);
			dx.setEventId(eventId);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}


}
