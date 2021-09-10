package com.skeqi.mes.controller.yp.alarm.serviceConfig;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.alarm.serviceConfig.EmailService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 邮箱配置
 *
 * @author yinp
 * @Data 2021年3月22日
 */
@RestController
@RequestMapping("/api/alarm/serviceConfig/email")
public class EmailController {

	@Autowired
	EmailService service;

	/**
	 * 查询邮箱配置
	 *
	 * @return
	 */
	@RequestMapping("/find")
	public Rjson find() {
		try {
			JSONObject json = service.find();

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新邮箱配置
	 * @return
	 */
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			String senderEmail = EqualsUtil.string(request, "senderEmail", "用户名", true);
			String authorizationCode  = EqualsUtil.string(request, "authorizationCode", "授权码", true);
			String theServer  = EqualsUtil.string(request, "theServer", "服务器", true);

			JSONObject json = new JSONObject();
			json.put("senderEmail", senderEmail);
			json.put("authorizationCode", authorizationCode);
			json.put("theServer", theServer);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 测试邮箱配置
	 * @return
	 */
	@RequestMapping("/test")
	public Rjson test(HttpServletRequest request) {
		try {
			String senderEmail = EqualsUtil.string(request, "senderEmail", "用户名", true);
			String authorizationCode  = EqualsUtil.string(request, "authorizationCode", "授权码", true);
			String theServer  = EqualsUtil.string(request, "theServer", "服务器", true);
			String addressee  = EqualsUtil.string(request, "addressee", "收件人", true);

			JSONObject json = new JSONObject();
			json.put("senderEmail", senderEmail);
			json.put("authorizationCode", authorizationCode);
			json.put("theServer", theServer);
			json.put("addressee", addressee);

			service.test(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}
}
