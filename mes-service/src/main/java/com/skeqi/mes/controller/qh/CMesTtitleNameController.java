package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.qh.ActivationService;
import com.skeqi.mes.service.qh.CMesTitleNameService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 *   登录页标题自定义
 * @author : FQZ
 * @Package: com.skeqi.mes.controller.qh
 * @date   : 2021-1-26 10:22:40
 */
@RestController
@RequestMapping(value = "api/titleName", produces = MediaType.APPLICATION_JSON)
@Api(value = "登录标题管理", description = "登录标题管理", produces = MediaType.APPLICATION_JSON)
public class CMesTtitleNameController {

	@Autowired
	CMesTitleNameService service;

	@RequestMapping(value = "/findTitleName", method = RequestMethod.GET)
	@ApiOperation(value = "查询当前登录页标题", notes = "查询当前登录页标题")
	@ResponseBody
	public Rjson findTitleName(HttpServletRequest request) {
		try {
			String findTitleName = service.findTitleName();
			return Rjson.success(findTitleName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/updateTitleName", method = RequestMethod.POST)
	@ApiOperation(value = "修改当前登录页标题", notes = "修改当前登录页标题")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query", dataType = "string"),
		 })
	@OptionalLog(module="生产", module2="标题信息", method="修改当前登录页标题")
	public Rjson updateTitleName(HttpServletRequest request, String name) {
		try {
			service.updateTitleName(name);
			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

}
