package com.skeqi.mes.controller.yp.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.oa.WaitingForMyApprovalService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 待我审批
 *
 * @author yinp
 * @data 2021年5月10日
 */
@RestController
@RequestMapping("/api/oa/waitingForMyApproval")
public class WaitingForMyApprovalController {

	@Autowired
	WaitingForMyApprovalService service;

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
			String userName = EqualsUtil.string(request, "userName", "用户名", true);
			String startDate = EqualsUtil.string(request, "startDate", "开始日期", false);
			String endDate = EqualsUtil.string(request, "endDate", "结束日期", false);
			String listNo = EqualsUtil.string(request, "listNo", "单号", false);
			String type = EqualsUtil.string(request, "type", "单据类型", false);
			Integer applicantId = EqualsUtil.integer(request, "applicantId", "申请人ID", false);

			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("userName", userName);
			json.put("startDate", startDate);
			json.put("endDate", endDate);
			json.put("listNo", listNo);
			json.put("type", type);
			json.put("applicantId", applicantId);

			PageHelper.startPage(pageNum, pageSize);
			List<JSONObject> list = service.list(json);
			PageInfo<JSONObject> pageInfo = null;
			if (list != null) {
				pageInfo = new PageInfo<JSONObject>(list, 5);
			}

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询明细
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryDetails")
	public Rjson queryDetails(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "操作人", true);

			JSONObject json = service.queryDetails(listNo, userId);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 审批
	 *
	 * @param request
	 * @return
	 */
	@OptionalLog(module = "OA", module2 = "待我审批", method = "审批")
	@Transactional
	@RequestMapping("/Approved")
	public Rjson Approved(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			String type = EqualsUtil.string(request, "type", "结果", true);
			String dis = EqualsUtil.string(request, "dis", "备注", false);
			String formData = EqualsUtil.string(request, "formData", "表单", false);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("userId", userId);
			json.put("type", type);
			json.put("dis", dis);
			json.put("formData", formData);

			service.Approved(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有用户
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findUserAll")
	public Rjson findUserAll() {
		try {

			List<JSONObject> list = service.findUserAll();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新表单
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateForm")
	public Rjson updateForm(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);
			Integer userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			String form = EqualsUtil.string(request, "form", "表单", true);

			JSONObject json = new JSONObject();
			json.put("listNo", listNo);
			json.put("userId", userId);
			json.put("form", form);

			service.updateForm(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}
}
