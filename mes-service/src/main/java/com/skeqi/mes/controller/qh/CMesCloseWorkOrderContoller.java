package com.skeqi.mes.controller.qh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.PMesPlanT;
import com.skeqi.mes.service.all.RMesPlanTService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/***
 *
 * @author ENS 强制关闭工单
 *
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "强制关闭工单", description = "强制关闭工单", produces = MediaType.APPLICATION_JSON)
public class CMesCloseWorkOrderContoller {

	@Autowired
	RMesPlanTService planService;

	@RequestMapping(value = "/closeWorkOrder/findList", method = RequestMethod.POST)
	@ApiOperation(value = "强制关闭工单", notes = "可根据工单编号，起止时间查询对应强制关闭工单信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "planSerialno", value = "工单编号", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "String")
	})
	@ResponseBody
	public JSONObject findList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6")Integer pageSize,String planSerialno,String startTime,
			String endTime)throws ServicesException {

		PageHelper.startPage(pageNum, pageSize);

		Map<String, Object> map = new HashMap<>();

		map.put("planSerialno", planSerialno);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("flag", 3);

		List<PMesPlanT> planList = planService.findOKWorkOrderR(map);

		PageInfo<PMesPlanT> pageInfo = new PageInfo<>(planList);

		JSONObject json = new JSONObject();

		try {
			json.put("code", 0);
			json.put("msg", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

}
