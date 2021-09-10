package com.skeqi.mes.controller.yp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.ProductionsInformationService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 生产信息
 * @date 2021-2-20
 */
@RestController
@RequestMapping("/api/Productions/Information")
public class ProductionsInformationController {

	@Autowired
	ProductionsInformationService service;

	/**
	 * @explain 查询
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.list();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 一键关机
	 * @param request
	 * @return
	 */
	@RequestMapping("shutDown")
	@OptionalLog(module="生产", module2="生产信息", method="一键关机")
	public Rjson shutDown(HttpServletRequest request) {
		try {
			String stationJson = EqualsUtil.string(request, "stationJson", "工位信息", true);
			List<JSONObject> jsonList = JSONObject.parseArray(stationJson, JSONObject.class);

			service.shutDown(jsonList);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

}
