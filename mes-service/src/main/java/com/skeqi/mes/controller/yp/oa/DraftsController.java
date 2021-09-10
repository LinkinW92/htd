package com.skeqi.mes.controller.yp.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.oa.DraftsService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;
import com.skeqi.mes.util.yp.FileReading;
import com.skeqi.mes.util.yp.FileUtil;

/**
 * 草稿箱
 *
 * @author yinp
 * @data 2021年6月2日
 *
 */
@RestController
@RequestMapping("/api/oa/drafts")
public class DraftsController {

	@Value(value = "${fileName.ApprovalFilePath}")
	private static String PATH;

	@Autowired
	DraftsService service;

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

			int userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			String startDate = EqualsUtil.string(request, "startDate", "开始日期", false);
			String endDate = EqualsUtil.string(request, "endDate", "结束日期", false);
			String listNo = EqualsUtil.string(request, "listNo", "单号", false);
			String type = EqualsUtil.string(request, "type", "单据类型", false);
			String state = EqualsUtil.string(request, "state", "状态", false);

			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("startDate", startDate);
			json.put("endDate", endDate);
			json.put("listNo", listNo);
			json.put("type", type);
			json.put("states", state);

			PageHelper.startPage(pageNum, pageSize);
			List<JSONObject> list = service.list(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询现有的单据类型
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findType")
	public Rjson findType(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.findType();
			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询单据
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findApprovalRecordDrafts")
	public Rjson findApprovalRecordDrafts(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			JSONObject json = service.findApprovalRecordDrafts(listNo);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询单据详情
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findApprovalRecordDetailedDrafts")
	public Rjson findApprovalRecordDetailedDrafts(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			List<JSONObject> json = service.findApprovalRecordDetailedDrafts(listNo);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询公司
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findCompany")
	public Rjson findCompany() {
		try {
			List<JSONObject> list = service.findCompany();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 通过公司ID查询部门
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findDepartmentByCompanyId")
	public Rjson findDepartmentByCompanyId(HttpServletRequest request) {
		try {
			int companyId = EqualsUtil.integer(request, "companyId", "公司ID", true);

			List<JSONObject> list = service.findDepartmentByCompanyId(companyId);

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
	 * 通过id查询用户
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findUserById")
	public Rjson findUserById(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "用户ID", true);

			JSONObject list = service.findUserById(id);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 保存草稿
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "草稿箱", method = "保存草稿")
	@Transactional
	@RequestMapping("/saveDraft")
	public Rjson saveDraft(HttpServletRequest request) {
		try {
			int userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			int formTemplateId = EqualsUtil.integer(request, "formTemplateId", "表单模板ID", true);
			String userName = EqualsUtil.string(request, "userName", "用户名称", true);
			String name = EqualsUtil.string(request, "name", "申请类型", true);
			String key = EqualsUtil.string(request, "key", "key", true);
			String detailed = EqualsUtil.string(request, "detailed", "明细", true);
			String table = EqualsUtil.string(request, "table", "表格", true);

			String applyUserName = EqualsUtil.string(request, "applyUserName", "申请用户", true);
			String applyDepartmentName = EqualsUtil.string(request, "applyDepartmentName", "申请人部门", true);
			String applyCompanyName = EqualsUtil.string(request, "applyCompanyName", "申请人公司", true);

			String relationListNo = EqualsUtil.string(request, "relationListNo", "关联单据号", false);
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			String deleteImg = EqualsUtil.string(request, "deleteImg", "已删除的图片", false);
			String deleteFile = EqualsUtil.string(request, "deleteFile", "已删除的文件", false);

			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("formTemplateId", formTemplateId);
			json.put("userName", userName);
			json.put("name", name);
			json.put("key", key);
			json.put("detailed", detailed);
			json.put("table", table);
			json.put("relationListNo", relationListNo);
			json.put("listNo", listNo);

			json.put("applyUserName", applyUserName);
			json.put("applyDepartmentName", applyDepartmentName);
			json.put("applyCompanyName", applyCompanyName);

			json.put("deleteImg", deleteImg);
			json.put("deleteFile", deleteFile);

			service.saveDraft(json, request);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 发布
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "草稿箱", method = "发布")
	@Transactional
	@RequestMapping("/release")
	public Rjson release(HttpServletRequest request) {
		try {
			int userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			int formTemplateId = EqualsUtil.integer(request, "formTemplateId", "表单模板ID", true);
			String userName = EqualsUtil.string(request, "userName", "用户名称", true);
			String name = EqualsUtil.string(request, "name", "申请类型", true);
			String key = EqualsUtil.string(request, "key", "key", true);
			String detailed = EqualsUtil.string(request, "detailed", "明细", true);
			String table = EqualsUtil.string(request, "table", "表格", true);

			String applyUserName = EqualsUtil.string(request, "applyUserName", "申请用户", true);
			String applyDepartmentName = EqualsUtil.string(request, "applyDepartmentName", "申请人部门", true);
			String applyCompanyName = EqualsUtil.string(request, "applyCompanyName", "申请人公司", true);

			String relationListNo = EqualsUtil.string(request, "relationListNo", "关联单据号", false);
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			String deleteImg = EqualsUtil.string(request, "deleteImg", "已删除的图片", false);
			String deleteFile = EqualsUtil.string(request, "deleteFile", "已删除的文件", false);

			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("formTemplateId", formTemplateId);
			json.put("userName", userName);
			json.put("name", name);
			json.put("key", key);
			json.put("detailed", detailed);
			json.put("table", table);
			json.put("relationListNo", relationListNo);
			json.put("listNo", listNo);

			json.put("applyUserName", applyUserName);
			json.put("applyDepartmentName", applyDepartmentName);
			json.put("applyCompanyName", applyCompanyName);

			json.put("deleteImg", deleteImg);
			json.put("deleteFile", deleteFile);

			service.release(json, request);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询表格明细
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findTableDrafts")
	public Rjson findTableDrafts(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			JSONObject json = service.findTableDrafts(listNo);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 直接发布
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "草稿箱", method = "直接发布")
	@Transactional
	@RequestMapping("/directRelease")
	public Rjson directRelease(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			service.directRelease(listNo);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}



}
