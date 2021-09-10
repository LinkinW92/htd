package com.skeqi.mes.controller.yp.qh;

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
import com.skeqi.mes.service.yp.qh.ExternalContactsService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 外部联系人
 *
 * @author yinp
 * @data 2021年6月8日
 */
@RestController
@RequestMapping("/api/externalContacts")
public class ExternalContactsController {

	@Autowired
	ExternalContactsService service;

	/**
	 * 查询
	 *
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			int pageNum = EqualsUtil.pageNum(request);
			int pageSize = EqualsUtil.pageSize(request);

			String selectedList = EqualsUtil.string(request, "selectedList", "已选标签", false);

			JSONObject json = new JSONObject();
			json.put("selectedList", selectedList);

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
	 * 新增
	 *
	 * @return
	 */
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			String name = EqualsUtil.string(request, "name", "名称", true);
			String phone = EqualsUtil.string(request, "phone", "电话", true);
			String company = EqualsUtil.string(request, "company", "公司", false);
			String position = EqualsUtil.string(request, "position", "职位", false);
			String mailbox = EqualsUtil.string(request, "mailbox", "邮箱", false);
			String address = EqualsUtil.string(request, "address", "地址", false);
			String dis = EqualsUtil.string(request, "dis", "备注", false);
			String personInCharge = EqualsUtil.string(request, "personInChargeJSON", "负责人", true);
			String sharer = EqualsUtil.string(request, "sharerJSON", "共享人", false);
			String tag = EqualsUtil.string(request, "tagJSON", "标签", true);

			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("phone", phone);
			json.put("company", company);
			json.put("position", position);
			json.put("mailbox", mailbox);
			json.put("address", address);
			json.put("dis", dis);
			json.put("personInCharge", personInCharge);
			json.put("sharer", sharer);
			json.put("tag", tag);

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
	 * @return
	 */
	@Transactional
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "ID", true);
			String name = EqualsUtil.string(request, "name", "名称", true);
			String phone = EqualsUtil.string(request, "phone", "电话", true);
			String company = EqualsUtil.string(request, "company", "公司", false);
			String position = EqualsUtil.string(request, "position", "职位", false);
			String mailbox = EqualsUtil.string(request, "mailbox", "邮箱", false);
			String address = EqualsUtil.string(request, "address", "地址", false);
			String dis = EqualsUtil.string(request, "dis", "备注", false);
			String personInCharge = EqualsUtil.string(request, "personInChargeJSON", "负责人", true);
			String sharer = EqualsUtil.string(request, "sharerJSON", "共享人", false);
			String tag = EqualsUtil.string(request, "tagJSON", "标签", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("name", name);
			json.put("phone", phone);
			json.put("company", company);
			json.put("position", position);
			json.put("mailbox", mailbox);
			json.put("address", address);
			json.put("dis", dis);
			json.put("personInCharge", personInCharge);
			json.put("sharer", sharer);
			json.put("tag", tag);

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
	 * @return
	 */
	@Transactional
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "ID", true);

			service.delete(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有标签
	 *
	 * @return
	 */
	@RequestMapping("/findLabel")
	public Rjson findLabel() {
		try {
			List<JSONObject> list = service.findLabel();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有部门
	 *
	 * @return
	 */
	@RequestMapping("/findDepartmentAll")
	public Rjson findDepartmentAll() {
		try {
			List<JSONObject> list = service.findDepartmentAll();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过部门id查询用户
	 *
	 * @return
	 */
	@RequestMapping("/findUserByDepartmentId")
	public Rjson findUserByDepartmentId(HttpServletRequest request) {
		try {
			int departmentId = EqualsUtil.integer(request, "departmentId", "部门Id", true);

			List<JSONObject> list = service.findUserByDepartmentId(departmentId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
