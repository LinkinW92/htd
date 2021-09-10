package com.skeqi.mes.controller.alarm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.alarm.CAlarmEmailConfig;
import com.skeqi.mes.service.alarm.CAlarmEmailConfigService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 邮箱服务配置
 * @author yinp
 *
 */
@RestController
@RequestMapping("api/AlarmEmailConfig")
public class CAlarmEmailConfigController {

	@Autowired
	CAlarmEmailConfigService service;

	/**
	 * 查询邮箱服务配置集合
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findEmailConfigList",method = RequestMethod.POST)
	public Rjson findEmailConfigList(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);
			String userName = EqualsUtil.string(request, "userName", "用户名", true);

			PageHelper.startPage(pageNumber, pageSize);
			List<CAlarmEmailConfig> list = service.findEmailConfigList(userName);
			PageInfo<CAlarmEmailConfig> pageInfo = new PageInfo<CAlarmEmailConfig>(list);

			return new Rjson().success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 新增邮箱服务配置
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addEmailConfig",method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="邮件服务", method="新增邮箱服务配置")
	public Rjson addEmailConfig(HttpServletRequest request) {
		try {
			String userName = EqualsUtil.string(request, "userName", "用户名", true);
			String senderEmail = EqualsUtil.string(request, "senderEmail", "发件人", true);
			String theServer = EqualsUtil.string(request, "theServer", "服务器", true);
			String authorizationCode = EqualsUtil.string(request, "authorizationCode", "授权码", true);

			CAlarmEmailConfig dx = new CAlarmEmailConfig();
			dx.setSenderEmail(senderEmail);
			dx.setTheServer(theServer);
			dx.setAuthorizationCode(authorizationCode);

			service.addEmailConfig(dx, userName);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 更新邮箱服务配置
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateEmailConfig",method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="邮件服务", method="编辑邮箱服务配置")
	public Rjson updateEmailConfig(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			String userName = EqualsUtil.string(request, "userName", "用户名", true);
			String senderEmail = EqualsUtil.string(request, "senderEmail", "发件人", true);
			String theServer = EqualsUtil.string(request, "theServer", "服务器", true);
			String authorizationCode = EqualsUtil.string(request, "authorizationCode", "授权码", true);

			CAlarmEmailConfig dx = new CAlarmEmailConfig();
			dx.setId(id);
			dx.setSenderEmail(senderEmail);
			dx.setTheServer(theServer);
			dx.setAuthorizationCode(authorizationCode);

			service.updateEmailConfig(dx, userName);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 删除邮箱服务配置
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteEmailConfig",method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="邮件服务", method="删除邮箱服务配置")
	public Rjson deleteEmailConfig(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteEmailConfig(id);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}
