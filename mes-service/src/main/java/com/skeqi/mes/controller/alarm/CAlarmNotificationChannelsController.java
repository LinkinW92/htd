package com.skeqi.mes.controller.alarm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannels;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannelsType;
import com.skeqi.mes.service.alarm.CAlarmNotificationChannelsService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 通知渠道
 *
 * @author yinp
 *
 */
@RestController
@RequestMapping("api/AlarmNotificationChannels")
public class CAlarmNotificationChannelsController {

	@Autowired
	CAlarmNotificationChannelsService service;

	/**
	 * 查询通知渠道类型
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findNotificationChannelsTypeList", method = RequestMethod.POST)
	public Rjson findNotificationChannelsTypeList(HttpServletRequest request) {
		try {

			List<CAlarmNotificationChannelsType> list = service.findNotificationChannelsTypeList();

			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 查询通知渠道
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findNotificationChannelsList", method = RequestMethod.POST)
	public Rjson findNotificationChannelsList(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);
			String userName = EqualsUtil.string(request, "userName", "用户名称", true);

			PageInfo<CAlarmNotificationChannels> pageInfo = service.findNotificationChannelsList(userName, pageNumber,
					pageSize);

			return new Rjson().success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 新增通知渠道
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addNotificationChannels", method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="通知途径", method="新增通知渠道")
	public Rjson addNotificationChannels(HttpServletRequest request) {
		try {
			String userName = EqualsUtil.string(request, "userName", "用户名称", true);
			String notificationChannels = EqualsUtil.string(request, "notificationChannels", "通知渠道", true);
			Integer notificationChannelsTypeId = EqualsUtil.integer(request, "notificationChannelsTypeId", "通知渠道类型",
					true);
			String notificationChannelsContent = EqualsUtil.string(request, "notificationChannelsContent", "内容", true);

			JSONObject json = new JSONObject();
			json.put("userName", userName);
			json.put("notificationChannels", notificationChannels);
			json.put("notificationChannelsTypeId", notificationChannelsTypeId);
			json.put("notificationChannelsContent", notificationChannelsContent.replaceAll("\\s*", ""));

			service.addNotificationChannels(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 更新通知渠道
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateNotificationChannels", method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="通知途径", method="编辑通知渠道")
	public Rjson updateNotificationChannels(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			String userName = EqualsUtil.string(request, "userName", "用户名称", true);
			String notificationChannels = EqualsUtil.string(request, "notificationChannels", "修改后通知渠道", true);
			Integer notificationChannelsTypeId = EqualsUtil.integer(request, "notificationChannelsTypeId", "通知渠道类型",
					true);
			String notificationChannelsContent = EqualsUtil.string(request, "notificationChannelsContent", "内容", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("userName", userName);
			json.put("notificationChannels", notificationChannels);
			json.put("notificationChannelsTypeId", notificationChannelsTypeId);
			json.put("notificationChannelsContent", notificationChannelsContent.replaceAll("\\s*", ""));

			service.updateNotificationChannels(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 删除通知渠道
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "deleteNotificationChannels", method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="通知途径", method="删除通知渠道")
	public Rjson deleteNotificationChannels(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteNotificationChannels(id);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Rjson().error(e.getMessage());
		}
	}

}
