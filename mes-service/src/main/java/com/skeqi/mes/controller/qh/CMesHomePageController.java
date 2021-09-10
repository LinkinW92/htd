package com.skeqi.mes.controller.qh;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.skeqi.mes.service.qh.CMesHomePageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/homePage", produces = MediaType.APPLICATION_JSON)
@Api(value = "首页管理", description = "首页管理", produces = MediaType.APPLICATION_JSON)
public class CMesHomePageController {

	@Autowired
	CMesHomePageService service;

	@ResponseBody
	@RequestMapping(value = "/passRate", method = RequestMethod.POST)
	@ApiOperation(value = "获取每日合格率")
	public JSONArray passRate() {
		return service.getPassRate();
	}
}
