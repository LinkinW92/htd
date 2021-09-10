package com.skeqi.mes.controller.alarm;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmIpWhiteList;
import com.skeqi.mes.service.alarm.CAlarmUserTokenService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.CheckUtil;
import com.skeqi.mes.util.yp.EqualsUtil;

@RestController
@RequestMapping("api/AlarmUserToken")
public class CAlarmUserTokenController {

	@Autowired
	CAlarmUserTokenService service;

	/**
	 * 查询用户token
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findUserToken",method = RequestMethod.POST)
	public Rjson findUserToken(HttpServletRequest request) {
		try {
			String userName = EqualsUtil.string(request, "userName", "用户名", true);

			JSONObject json = service.findUserToken(userName);

			return new Rjson().success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 通过token查询用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findUserByToken",method = RequestMethod.POST)
	public Rjson findUserByToken(HttpServletRequest request) {
		try {
			String token = EqualsUtil.string(request, "token", "令牌", true);

			CMesUserT user = service.findUserByToken(token);

			return new Rjson().success(user);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 更新用户token
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateUserToken",method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="授权Token", method="更新用户token")
	public Rjson updateUserToken(HttpServletRequest request) {
		try {
			String userName = EqualsUtil.string(request, "userName", "用户名", true);

			service.updateUserToken(userName);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 查询ip白名单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findIpWhiteList",method = RequestMethod.POST)
	public Rjson findIpWhiteList(HttpServletRequest request) {
		try {
			String userName = EqualsUtil.string(request, "userName", "用户名", true);

			CAlarmIpWhiteList dx = service.findIpWhiteList(userName);

			return new Rjson().success(dx);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 设置查询ip白名单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "setUpIpWhiteList",method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="授权Token", method="设置查询ip白名单")
	public Rjson setUpIpWhiteList(HttpServletRequest request) {
		try {
			String userName = EqualsUtil.string(request, "userName", "用户名", true);
			String ip = EqualsUtil.string(request, "ip", "ip", true).replaceAll("\\s*", "");

			String[] ips = ip.split(",");
			for (String string : ips) {
				CheckUtil.ip(string);
			}

			Integer result = service.updateIpWhiteList(userName, ip);
			if(result==0) {
				service.addIpWhiteList(userName, ip);
			}

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}
