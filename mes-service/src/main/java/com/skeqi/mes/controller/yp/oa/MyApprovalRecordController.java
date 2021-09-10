package com.skeqi.mes.controller.yp.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.oa.MyApprovalRecordService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 我的审批记录
 *
 * @author yinp
 * @data 2021年5月13日
 *
 */
@RestController
@RequestMapping("/api/oa/MyApprovalRecord")
public class MyApprovalRecordController {

	@Autowired
	MyApprovalRecordService service;

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

			int userId = EqualsUtil.integer(request, "userId", "用户ID", true);
			String startDate = EqualsUtil.string(request, "startDate", "开始日期", false);
			String endDate = EqualsUtil.string(request, "endDate", "结束日期", false);
			String listNo = EqualsUtil.string(request, "listNo", "单号", false);
			String type = EqualsUtil.string(request, "type", "单据类型", false);
			Integer applicantId = EqualsUtil.integer(request, "applicantId", "申请人ID", false);

			JSONObject json = new JSONObject();
			json.put("userId", userId);
			json.put("startDate", startDate);
			json.put("endDate", endDate);
			json.put("listNo", listNo);
			json.put("type", type);
			json.put("applicantId", applicantId);

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
	 * 查询审批明细
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryDetails")
	public Rjson queryDetails(HttpServletRequest request) {
		try {

			int id = EqualsUtil.integer(request, "id", "id", true);

			JSONObject json = new JSONObject();
			json.put("id", id);

			List<JSONObject> list = service.queryDetails(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询审批备注
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/findApprovalRecordNote")
	public Rjson findApprovalRecordNote(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);

			List<JSONObject> list = service.findApprovalRecordNote(id);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
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

}
