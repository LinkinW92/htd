package com.skeqi.mes.controller.yp.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.oa.OAHomeService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * OA首页
 *
 * @author yinp
 * @data 2021年7月09日
 */
@RestController
@RequestMapping("/api/oa/OAHome")
public class OAHomeController {

	@Autowired
	OAHomeService service;

	/**
	 * 查询数量
	 * @param request
	 * @return
	 */
	@RequestMapping("/findNumber")
	public Rjson findNumber(HttpServletRequest request) {
		try {

			Integer userId = EqualsUtil.integer(request, "userId", "用户名ID", true);
			String date = EqualsUtil.string(request, "date", "日期", true);

			JSONObject json = service.findNumber(userId, date);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询待办
	 * @param request
	 * @return
	 */
	@RequestMapping("/findDaiBan")
	public Rjson findDaiBan(HttpServletRequest request) {
		try {

			Integer userId = EqualsUtil.integer(request, "userId", "用户名ID", true);
			String date = EqualsUtil.string(request, "date", "日期", true);

			List<JSONObject> list = service.findDaiBan(userId, date);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询已办
	 * @param request
	 * @return
	 */
	@RequestMapping("/findDone")
	public Rjson findDone(HttpServletRequest request) {
		try {

			Integer userId = EqualsUtil.integer(request, "userId", "用户名ID", true);
			String date = EqualsUtil.string(request, "date", "日期", true);

			List<JSONObject> list = service.findDone(userId, date);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询办结
	 * @param request
	 * @return
	 */
	@RequestMapping("/findToConclude")
	public Rjson findToConclude(HttpServletRequest request) {
		try {

			Integer userId = EqualsUtil.integer(request, "userId", "用户名ID", true);
			String date = EqualsUtil.string(request, "date", "日期", true);

			List<JSONObject> list = service.findToConclude(userId, date);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
