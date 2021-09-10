package com.skeqi.mes.controller.wms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.skeqi.mes.pojo.chenj.srm.req.CSrmEnterpriseReq;
import com.skeqi.mes.service.chenj.srm.CSrmSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.wms.DepartmentService;
import com.skeqi.mes.util.*;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp 部门
 */
@RestController
@RequestMapping("/wms/department")
public class DepartmentController {

	@Autowired
	DepartmentService service;

	@Autowired
	CSrmSupplierService srmSupplierService;

	/**
	 * 查询
	 *
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {

			List<JSONObject> list = service.list();

			return Rjson.success(list);
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
			String name = EqualsUtil.string(request, "name", "部门名称", true);
			Integer superiorId = EqualsUtil.integer(request, "superiorId", "上级部门", true);

			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("superiorId", superiorId);

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
			String name = EqualsUtil.string(request, "name", "部门名称", true);
			Integer superiorId = EqualsUtil.integer(request, "superiorId", "上级部门", false);
			String executive = EqualsUtil.string(request, "executive", "主管", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("name", name);
			json.put("superiorId", superiorId);
			json.put("executive", executive);

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
	 * 通过上级部门id查询部门
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findDepartmentBySuperiorId")
	public Rjson findDepartmentBySuperiorId(HttpServletRequest request) {
		try {
			Integer superiorId = EqualsUtil.integer(request, "superiorId", "上级部门Id", true);

			List<JSONObject> list = service.findDepartmentBySuperiorId(superiorId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过部门ID查询用户
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findUserByDepartmentId")
	public Rjson findUserByDepartmentId(HttpServletRequest request) {
		try {
			int departmentId = EqualsUtil.integer(request, "departmentId", "部门ID", true);

			List<JSONObject> list = service.findUserByDepartmentId(departmentId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 用户导入模板
	 *
	 * @return
	 */
	@RequestMapping("/userImportTemplate")
	public Rjson userImportTemplate() {
		try {
			String[] a = { "用户名", "姓名", "职位", "职级", "电话", "邮箱", "角色" };

			return Rjson.success(a);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 批量导入用户
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/batchImportUsers")
	public Rjson batchImportUsers(HttpServletRequest request) {
		try {
			Integer departmentId = EqualsUtil.integer(request, "departmentId", "部门ID", true);
			String userList = EqualsUtil.string(request, "userList", "用户集合", true);

			service.batchImportUsers(departmentId, userList);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增用户
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/addUser")
	public Rjson addUser(HttpServletRequest request) {
		try {
			String userName = EqualsUtil.string(request, "userName", "用户名", true);
			String password = EqualsUtil.string(request, "password", "密码", false);
			String name = EqualsUtil.string(request, "name", "姓名", true);
			String status = EqualsUtil.string(request, "status", "状态", true);
			Integer departmentId = EqualsUtil.integer(request, "departmentId", "部门ID", true);
			Integer roleId = EqualsUtil.integer(request, "roleId", "角色Id", true);
			String mobile = EqualsUtil.string(request, "mobile", "手机号", false);
			String position = EqualsUtil.string(request, "position", "职务", false);
			String email = EqualsUtil.string(request, "email", "用户邮箱", false);
			Integer reportsTo = EqualsUtil.integer(request, "reportsTo", "直属主管", false);
			Integer rankId = EqualsUtil.integer(request, "rankId", "职级", false);

			JSONObject json = new JSONObject();
			json.put("userName", userName);
			json.put("password", password);
			json.put("name", name);
			json.put("status", status);
			json.put("departmentId", departmentId);
			json.put("roleId", roleId);
			json.put("mobile", mobile);
			json.put("position", position);
			json.put("email", email);
			json.put("reportsTo", reportsTo);
			json.put("rankId", rankId);

			service.addUser(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 修改用户
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/updateUser")
	public Rjson updateUser(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "ID", true);
			String userName = EqualsUtil.string(request, "userName", "用户名", true);
			String password = EqualsUtil.string(request, "password", "密码", false);
			String name = EqualsUtil.string(request, "name", "姓名", true);
			String status = EqualsUtil.string(request, "status", "状态", true);
			Integer departmentId = EqualsUtil.integer(request, "departmentId", "部门ID", true);
			Integer roleId = EqualsUtil.integer(request, "roleId", "角色Id", true);
			String mobile = EqualsUtil.string(request, "mobile", "手机号", false);
			String position = EqualsUtil.string(request, "position", "职务", false);
			String email = EqualsUtil.string(request, "email", "用户邮箱", false);
			Integer reportsTo = EqualsUtil.integer(request, "reportsTo", "直属主管", false);
			Integer rankId = EqualsUtil.integer(request, "rankId", "职级", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("userName", userName);
			json.put("password", password);
			json.put("name", name);
			json.put("status", status);
			json.put("departmentId", departmentId);
			json.put("roleId", roleId);
			json.put("mobile", mobile);
			json.put("position", position);
			json.put("email", email);
			json.put("reportsTo", reportsTo);
			json.put("rankId", rankId);

			service.updateUser(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除用户
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/deleteUser")
	public Rjson deleteUser(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "ID", true);

			service.deleteUser(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		String a = "/user/157/123/";
		System.out.println(a.substring(0, a.indexOf("/", 2) + 1));
	}

}
