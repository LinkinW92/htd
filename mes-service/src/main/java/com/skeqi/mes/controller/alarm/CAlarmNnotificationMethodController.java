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
import com.skeqi.mes.pojo.alarm.CAlarmNotificationMethod;
import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;
import com.skeqi.mes.pojo.project.CMesLossTypeT;
import com.skeqi.mes.service.alarm.CAlarmNnotificationMethodService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 通知方式
 *
 * @author yinp
 *
 */
@RestController
@RequestMapping("api/AlarmNnotificationMethod")
public class CAlarmNnotificationMethodController {

	@Autowired
	CAlarmNnotificationMethodService service;

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
	 * 查询所有损失类型
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findLossTypeAll", method = RequestMethod.POST)
	public Rjson findLossTypeAll(HttpServletRequest request) {
		try {

			List<CMesLossTypeT> list = service.findLossTypeAll();

			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 查询所有问题等级
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findProblemLevelAll", method = RequestMethod.POST)
	public Rjson findProblemLevelAll(HttpServletRequest request) {
		try {

			List<CAlarmProblemLevel> list = service.findProblemLevelAll();

			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 查询所有通知渠道
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findNotificationChannelsAll", method = RequestMethod.POST)
	public Rjson findNotificationChannelsAll(HttpServletRequest request) {
		try {
			String userName = EqualsUtil.string(request, "userName", "用户名称", true);
			Integer typeId = EqualsUtil.integer(request, "typeId", "通知渠道类型id", true);

			List<CAlarmNotificationChannels> list = service.findNotificationChannelsList(userName, typeId);

			return new Rjson().success(list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 查询通知方式
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findNnotificationMethodList", method = RequestMethod.POST)
	public Rjson findNnotificationMethodList(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);
			String userName = EqualsUtil.string(request, "userName", "用户名称", true);

			PageInfo<CAlarmNotificationMethod> pageInfo = service.findNnotificationMethodList(userName, pageNumber,
					pageSize);

			return new Rjson().success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 新增通知方式
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addNnotificationMethod", method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="通知方式", method="新增通知方式")
	public Rjson addNnotificationMethod(HttpServletRequest request) {
		try {
			Integer lossTypeId = EqualsUtil.integer(request, "lossTypeId", "损失类型", true);
			Integer notificationChannelsId = EqualsUtil.integer(request, "notificationChannelsId", "通知渠道", true);
			String userName = EqualsUtil.string(request, "userName", "用户名称", true);
			Integer serviceId = EqualsUtil.integer(request, "serviceId", "服务编号", true);
			Integer problemLevelId = EqualsUtil.integer(request, "problemLevelId", "问题等级", true);
			Integer problemState = EqualsUtil.integer(request, "problemState", "问题状态", true);
			if(problemState!=1  && problemState!=2 && problemState!=3) {
				throw new Exception("问题状态参数有误");
			}

			JSONObject json = new JSONObject();
			json.put("lossTypeId", lossTypeId);
			json.put("notificationChannelsId", notificationChannelsId);
			json.put("userName", userName);
			json.put("serviceId", serviceId);
			json.put("problemLevelId", problemLevelId);
			json.put("problemState", problemState);

			service.addNnotificationMethod(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 更新通知方式
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateNnotificationMethod", method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="通知方式", method="编辑通知方式")
	public Rjson updateNnotificationMethod(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer lossTypeId = EqualsUtil.integer(request, "lossTypeId", "损失类型", true);
			Integer notificationChannelsId = EqualsUtil.integer(request, "notificationChannelsId", "通知渠道", true);
			String userName = EqualsUtil.string(request, "userName", "用户名称", true);
			Integer serviceId = EqualsUtil.integer(request, "serviceId", "服务编号", true);
			Integer problemLevelId = EqualsUtil.integer(request, "problemLevelId", "问题等级", true);
			Integer problemState = EqualsUtil.integer(request, "problemState", "问题状态", true);
			if(problemState!=1  && problemState!=2 && problemState!=3) {
				throw new Exception("问题状态参数有误");
			}

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("lossTypeId", lossTypeId);
			json.put("notificationChannelsId", notificationChannelsId);
			json.put("userName", userName);
			json.put("serviceId", serviceId);
			json.put("problemLevelId", problemLevelId);
			json.put("problemState", problemState);

			service.updateNnotificationMethod(json);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 删除通知方式
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "deleteNnotificationMethod", method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="通知方式", method="删除通知方式")
	public Rjson deleteNnotificationMethod(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteNnotificationMethod(id);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 测试
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "test", method = RequestMethod.POST)
	public Rjson test(HttpServletRequest request) {
		try {
			Integer notificationChannelsTypeId = EqualsUtil.integer(request, "notificationChannelsTypeId", "渠道类型",
					true);
			Integer notificationChannelsId = EqualsUtil.integer(request, "notificationChannelsId", "通知渠道", true);
			Integer serviceId = EqualsUtil.integer(request, "serviceId", "服务编号", true);

			service.test(notificationChannelsTypeId, notificationChannelsId, serviceId);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}
}
