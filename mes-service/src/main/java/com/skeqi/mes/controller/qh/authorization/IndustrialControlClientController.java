package com.skeqi.mes.controller.qh.authorization;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.qh.authorization.IndustrialControlClientService;
import com.skeqi.mes.service.qh.authorization.impl.IndustrialControlClientServiceImpl;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 工控客户端授权
 * @date 2021-2-2
 */
@RestController
@RequestMapping("/api/authorization")
public class IndustrialControlClientController {

	@Autowired
	IndustrialControlClientService service;

	/**
	 * @explain 登入
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public Rjson login(HttpServletRequest request) {
		try {
			String ip = EqualsUtil.string(request, "ip", "ip", true);

			JSONObject json = service.login(ip);

			return new Rjson().success(json);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 登出
	 * @param request
	 * @return
	 */
	@RequestMapping("/logOut")
	public Rjson logOut(HttpServletRequest request) {
		try {
			String token = EqualsUtil.string(request, "token", "token", true);

			service.logOut(token);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 心跳
	 * @param request
	 * @return
	 */
	@RequestMapping("/heartbeat")
	public Rjson heartbeat(HttpServletRequest request) {
		try {
			String token = EqualsUtil.string(request, "token", "token", true);

			service.heartbeat(token);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * @explain 获取Tokens
	 * @return
	 */
	@RequestMapping("/getTokens")
	public Rjson getTokens() {
		return new Rjson().success(IndustrialControlClientServiceImpl.tokens);
	}

}
