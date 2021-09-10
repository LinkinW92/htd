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
import com.skeqi.mes.service.yp.oa.FormTemplateTypeService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 表单模板类型
 *
 * @author yinp
 * @date 2021年5月27日
 */
@RestController
@RequestMapping("/api/oa/formTemplateType")
public class FormTemplateTypeController {

	@Autowired
	FormTemplateTypeService service;

	/**
	 * 查询
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			int pageNum = EqualsUtil.pageNum(request);
			int pageSize = EqualsUtil.pageSize(request);

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
	@OptionalLog(module = "OA", module2 = "表单模板类型", method= "新增")
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			String name = EqualsUtil.string(request, "name", "表单模板类型名称", true);
			String dis = EqualsUtil.string(request, "dis", "描述", false);

			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("dis", dis);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			// 手动回滚事务
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
	@OptionalLog(module = "OA", module2 = "表单模板类型", method= " 更新")
	@Transactional
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "ID", true);
			String name = EqualsUtil.string(request, "name", "表单模板类型名称", true);
			String oldName = EqualsUtil.string(request, "oldName", "表单模板类型老的名称", true);
			String dis = EqualsUtil.string(request, "dis", "描述", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("name", name);
			json.put("oldName", oldName);
			json.put("dis", dis);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			// 手动回滚事务
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
	@OptionalLog(module = "OA", module2 = "表单模板类型", method= "删除")
	@Transactional
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "ID", true);
			String name = EqualsUtil.string(request, "name", "表单模板类型名称", true);

			service.delete(id, name);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	@RequestMapping("/updateMenu")
	public Rjson updateMenu(HttpServletRequest request) {
		try {

			String name = EqualsUtil.string(request, "name", "name", true);

			JSONObject json = new JSONObject();
			json.put("name", name);

			service.asd(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
