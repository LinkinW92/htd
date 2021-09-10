package com.skeqi.mes.controller.yp.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.oa.ApprovalRelatedService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 相关单据
 *
 * @author yinp
 * @data 2021年7月7日
 */
@RestController
@RequestMapping("/api/oa/approvalRelated")
public class ApprovalRelatedController {

	@Autowired
	ApprovalRelatedService service;

	/**
	 * 相关单据
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			int pageNum = EqualsUtil.pageNum(request);
			int pageSize = EqualsUtil.pageSize(request);

			Integer applicantId = EqualsUtil.integer(request, "applicantId", "申请人id", true);
			String listNo = EqualsUtil.string(request, "listNo", "单号", false);
			String state = EqualsUtil.string(request, "state", "单据状态", false);
			String name = EqualsUtil.string(request, "name", "单据名称", false);
			String startDate = EqualsUtil.string(request, "startDate", "开始日期", false);
			String endDate = EqualsUtil.string(request, "endDate", "结束日期", false);

			JSONObject json = new JSONObject();
			json.put("applicantId", applicantId);
			json.put("listNo", listNo);
			json.put("state", state);
			json.put("name", name);
			json.put("startDate", startDate);
			json.put("endDate", endDate);


			PageHelper.startPage(pageNum, pageSize);
			List<JSONObject> list = service.list(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询模板
	 * @return
	 */
	@RequestMapping("/findTemplateAll")
	public Rjson findTemplateAll() {
		try {

			List<JSONObject> list = service.findTemplateAll();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
