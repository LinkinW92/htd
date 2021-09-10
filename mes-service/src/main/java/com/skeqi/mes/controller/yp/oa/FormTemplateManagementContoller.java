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
import com.skeqi.mes.service.yp.oa.FormTemplateManagementService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 表单模板管理
 *
 * @author yinp
 * @data 2021年5月5日
 *
 */
@RestController
@RequestMapping("/api/oa/ApprovalCirculation/FormTemplateManagement")
public class FormTemplateManagementContoller {

	@Autowired
	FormTemplateManagementService service;

	/**
	 * 查询
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			int pageSize = EqualsUtil.pageSize(request);
			int pageNum = EqualsUtil.pageNum(request);

			PageHelper.startPage(pageNum, pageSize);
			List<JSONObject> list = service.list();
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "表单模板管理", method= "新增")
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			String name = EqualsUtil.string(request, "name", "模板名称", true);
			String code = EqualsUtil.string(request, "code", "编号", true);
			int typeId = EqualsUtil.integer(request, "typeId", "表单模板类型", true);
			String process = EqualsUtil.string(request, "process", "审批流程", true);
			String detailed = EqualsUtil.string(request, "detailed", "明细", true);
			String dis = EqualsUtil.string(request, "dis", "模板说明", false);
			String tokenUrl = EqualsUtil.string(request, "tokenUrl", "回调地址", false);
			String formType = EqualsUtil.string(request, "formType", "业务类型", true);
			String jumpUrl = EqualsUtil.string(request, "jumpUrl", "跳转地址", false);
			String overtime = EqualsUtil.string(request, "overtime", "超时时间", false);
			String overtimeType = EqualsUtil.string(request, "overtimeType", "超时类型", false);

			JSONObject json = new JSONObject();
			json.put("code", code);
			json.put("name", name);
			json.put("process", process);
			json.put("detailed", detailed);
			json.put("dis", dis);
			json.put("typeId", typeId);
			json.put("tokenUrl", tokenUrl);
			json.put("formType", formType);
			json.put("jumpUrl", jumpUrl);
			json.put("overtime", overtime);
			json.put("overtimeType", overtimeType);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "表单模板管理", method= "更新")
	@Transactional
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);
			String name = EqualsUtil.string(request, "name", "模板名称", true);
			String code = EqualsUtil.string(request, "code", "编号", true);
			int typeId = EqualsUtil.integer(request, "typeId", "表单模板类型", true);
			String process = EqualsUtil.string(request, "process", "审批流程", true);
			String detailed = EqualsUtil.string(request, "detailed", "明细", true);
			String dis = EqualsUtil.string(request, "dis", "模板说明", false);
			String tokenUrl = EqualsUtil.string(request, "tokenUrl", "回调地址", false);
			String formType = EqualsUtil.string(request, "formType", "业务类型", true);
			String jumpUrl = EqualsUtil.string(request, "jumpUrl", "跳转地址", false);
			String overtime = EqualsUtil.string(request, "overtime", "超时时间", false);
			String overtimeType = EqualsUtil.string(request, "overtimeType", "超时类型", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("code", code);
			json.put("name", name);
			json.put("dis", dis);
			json.put("process", process);
			json.put("detailed", detailed);
			json.put("typeId", typeId);
			json.put("tokenUrl", tokenUrl);
			json.put("formType", formType);
			json.put("jumpUrl", jumpUrl);
			json.put("overtime", overtime);
			json.put("overtimeType", overtimeType);

			if(formType.equals("普通类型")) {
				json.put("tokenUrl", null);
				json.put("jumpUrl", null);
			}

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "表单模板管理", method= "删除")
	@Transactional
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);
			String name = EqualsUtil.string(request, "name", "模板名称", true);

			service.delete(id, name);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增模板明细
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "表单模板管理", method= "新增模板明细")
	@Transactional
	@RequestMapping("/addTemplateDetailed")
	public Rjson addTemplateDetailed(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "表单模板id", true);
			String templateDetailed = EqualsUtil.string(request, "templateDetailed", "模板明细", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("templateDetailed", templateDetailed);

			service.addTemplateDetailed(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询模板
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findTemplateById")
	public Rjson findTemplateById(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);

			JSONObject dx = service.findTemplateById(id);

			return Rjson.success(dx);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增表单模板表格
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "表单模板管理", method= "新增表单模板表格")
	@Transactional
	@RequestMapping("/addFormTemplateTable")
	public Rjson addFormTemplateTable(HttpServletRequest request) {
		try {
			int formTemplateId = EqualsUtil.integer(request, "formTemplateId", "表单模板id", true);
			String groupName = EqualsUtil.string(request, "groupName", "组名", true);
			String group = EqualsUtil.string(request, "group", "组", false);
			String templateTable = EqualsUtil.string(request, "templateTable", "模板明细", true);

			JSONObject json = new JSONObject();
			json.put("formTemplateId", formTemplateId);
			json.put("group", group);
			json.put("groupName", groupName);
			json.put("templateTable", templateTable);

			service.addFormTemplateTable(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询表单模板表格
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findFormTemplateTable")
	public Rjson findFormTemplateTable(HttpServletRequest request) {
		try {
			int formTemplateId = EqualsUtil.integer(request, "formTemplateId", "表单模板id", true);
			String group = EqualsUtil.string(request, "group", "组", true);

			List<JSONObject> list = service.findFormTemplateTable(formTemplateId,group);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询表单模板类型
	 *
	 * @return
	 */
	@RequestMapping("/findFormTemplateType")
	public Rjson findFormTemplateType() {
		try {

			List<JSONObject> list = service.findFormTemplateType();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询组
	 * @param request
	 * @return
	 */
	@RequestMapping("/findGroup")
	public Rjson findGroup(HttpServletRequest request) {
		try {
			int formTemplateId = EqualsUtil.integer(request, "formTemplateId", "表单模板id", true);

			List<JSONObject> list = service.findGroup(formTemplateId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除组
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteGroup")
	public Rjson deleteGroup(HttpServletRequest request) {
		try {
			int formTemplateId = EqualsUtil.integer(request, "formTemplateId", "表单模板id", true);
			String group = EqualsUtil.string(request, "group", "组", true);

			service.deleteGroup(formTemplateId,group);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改状态
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateState")
	public Rjson updateState(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "ID", true);
			String state = EqualsUtil.string(request, "state", "状态", true);

			service.updateState(id, state);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}


}
