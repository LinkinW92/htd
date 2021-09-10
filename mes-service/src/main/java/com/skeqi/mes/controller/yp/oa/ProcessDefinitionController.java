package com.skeqi.mes.controller.yp.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.oa.ProcessDefinitionService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 流程定义
 *
 * @author yinp
 * @data 2021年5月10日
 */
@RestController
@RequestMapping("/api/oa/processDefinition")
public class ProcessDefinitionController {

	@Autowired
	ProcessDefinitionService service;

	/**
	 * 查询流程
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			Integer pageNum = EqualsUtil.pageNum(request);
			Integer pageSize = EqualsUtil.pageSize(request);

//			int userId = EqualsUtil.integer(request, "userId", "用户", true);

			JSONObject json = new JSONObject();
//			json.put("userId", userId);

			PageHelper.startPage(pageNum, pageSize);
			List<JSONObject> list = service.list(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增流程定义
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "流程定义", method= "新增流程定义")
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			String key = EqualsUtil.string(request, "key", "key", true);
			Integer templateId = EqualsUtil.integer(request, "templateId", "模板Id", false);
			String templateName = EqualsUtil.string(request, "templateName", "模板名称", true);
			String xml = EqualsUtil.string(request, "xml", "流程", true);
			String positionid = EqualsUtil.string(request, "positionid", "职位", false);

			JSONObject json = new JSONObject();
			json.put("key", key);
			json.put("templateId", templateId);
			json.put("templateName", templateName);
			json.put("xml", xml);
			json.put("positionid", positionid);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询表单模板
	 *
	 * @return
	 */
	@RequestMapping("/queryFormTemplate")
	public Rjson queryFormTemplate() {
		try {
			List<JSONObject> list = service.queryFormTemplate();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询用户信息
	 *
	 * @return
	 */
	@RequestMapping("/queryUser")
	public Rjson queryUser() {
		try {
			List<JSONObject> list = service.queryUser();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询模板明细
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findTemplateDetailed")
	public Rjson findTemplateDetailed(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);

			List<JSONObject> list = service.findTemplateDetailed(id);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询流程xml
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/inquiryProcessXML")
	public Rjson inquiryProcessXML(HttpServletRequest request) {
		try {

			String key = EqualsUtil.string(request, "key", "key", true);

			JSONObject json = service.inquiryProcessXML(key);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改流程
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "流程定义", method= "修改流程")
	@Transactional
	@RequestMapping("/updateProcess")
	public Rjson updateProcess(HttpServletRequest request) {
		try {
			String key = EqualsUtil.string(request, "key", "key", true);
			Integer templateId = EqualsUtil.integer(request, "templateId", "模板Id", false);
			String templateName = EqualsUtil.string(request, "templateName", "模板名称", true);
			String xml = EqualsUtil.string(request, "xml", "流程", true);
			String positionid = EqualsUtil.string(request, "positionid", "职位", false);

			JSONObject json = new JSONObject();
			json.put("key", key);
			json.put("templateId", templateId);
			json.put("templateName", templateName);
			json.put("xml", xml);
			json.put("positionid", positionid);

			service.updateProcess(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询部门
	 *
	 * @return
	 */
	@RequestMapping("/findDepartment")
	public Rjson findDepartment() {
		try {

			List<JSONObject> list = service.findDepartment();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询职位
	 *
	 * @return
	 */
	@RequestMapping("/findPositionid")
	public Rjson findPositionid() {
		try {

			List<JSONObject> list = service.findPositionid();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 流程删除
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "流程定义", method= "流程删除")
	@Transactional
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			String key = EqualsUtil.string(request, "key", "流程的key", true);
			String name = EqualsUtil.string(request, "name", "表单名称", true);

			service.delete(key, name);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	@RequestMapping("/updateMenu")
	public Rjson updateMenu(HttpServletRequest request) {
		try {

			String name = EqualsUtil.string(request, "name", "name", true);
			String key = EqualsUtil.string(request, "key", "key", true);
			int templateId = EqualsUtil.integer(request, "templateId", "templateId", true);
			String typeName = EqualsUtil.string(request, "typeName", "typeName", true);

			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("key", key);
			json.put("typeName", typeName);
			json.put("templateId", templateId);

			service.asd(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
