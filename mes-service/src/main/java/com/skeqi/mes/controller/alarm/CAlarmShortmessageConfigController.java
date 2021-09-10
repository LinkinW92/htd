package com.skeqi.mes.controller.alarm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.alarm.CAlarmShortmessageConfig;
import com.skeqi.mes.service.alarm.CAlarmShortmessageConfigService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 短信服务配置
 * @author yinp
 *
 */
@RestController
@RequestMapping("api/AlarmShortmessageConfig")
public class CAlarmShortmessageConfigController {

	@Autowired
	CAlarmShortmessageConfigService service;

	/**
	 * 查询邮箱服务配置集合
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findShortmessageConfigList",method = RequestMethod.POST)
	public Rjson findShortmessageConfigList(HttpServletRequest request) {
		try {
			Integer pageNumber = EqualsUtil.pageNumber(request);
			Integer pageSize = EqualsUtil.pageSize(request);
			String userName = EqualsUtil.string(request, "userName", "用户名", true);

			PageHelper.startPage(pageNumber, pageSize);
			List<CAlarmShortmessageConfig> list = service.findShortmessageConfigList(userName);
			PageInfo<CAlarmShortmessageConfig> pageInfo = new PageInfo<CAlarmShortmessageConfig>(list);

			return new Rjson().success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 新增短信服务配置
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addShortmessageConfig",method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="短信服务", method="新增短信服务配置")
	public Rjson addShortmessageConfig(HttpServletRequest request) {
		try {
			String userName = EqualsUtil.string(request, "userName", "用户名", true);
			String header = EqualsUtil.string(request, "header", "请求头", true);
			String parameter = EqualsUtil.string(request, "parameter", "参数", true);
			String charset = EqualsUtil.string(request, "charset", "字符集", true);
			String apiUrl = EqualsUtil.string(request, "apiUrl", "接口地址", true);
			String requestMethod = EqualsUtil.string(request, "requestMethod", "请求方式", true);

			CAlarmShortmessageConfig dx = new CAlarmShortmessageConfig();
			dx.setHeader(header);
			dx.setParameter(parameter);
			dx.setCharset(charset);
			dx.setApiUrl(apiUrl);
			dx.setRequestMethod(requestMethod);

			service.addShortmessageConfig(dx, userName);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 更新短信服务配置
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateShortmessageConfig",method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="短信服务", method="编辑短信服务配置")
	public Rjson updateShortmessageConfig(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			String userName = EqualsUtil.string(request, "userName", "用户名", true);
			String header = EqualsUtil.string(request, "header", "请求头", true);
			String parameter = EqualsUtil.string(request, "parameter", "参数", true);
			String charset = EqualsUtil.string(request, "charset", "字符集", true);
			String apiUrl = EqualsUtil.string(request, "apiUrl", "接口地址", true);
			String requestMethod = EqualsUtil.string(request, "requestMethod", "请求方式", true);

			CAlarmShortmessageConfig dx = new CAlarmShortmessageConfig();
			dx.setId(id);
			dx.setHeader(header);
			dx.setParameter(parameter);
			dx.setCharset(charset);
			dx.setApiUrl(apiUrl);
			dx.setRequestMethod(requestMethod);

			service.updateShortmessageConfig(dx, userName);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

	/**
	 * 删除短信服务配置
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteShortmessageConfig",method = RequestMethod.POST)
	@OptionalLog(module="告警通知", module2="短信服务", method="删除短信服务配置")
	public Rjson deleteShortmessageConfig(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.deleteShortmessageConfig(id);

			return new Rjson().success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return new Rjson().error(e.getMessage());
		}
	}

}
