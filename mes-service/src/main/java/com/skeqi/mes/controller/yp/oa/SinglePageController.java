package com.skeqi.mes.controller.yp.oa;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.oa.SinglePageService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 单据信息
 * @author yinp
 * @data 2021年5月21日
 *
 */
@RestController
@RequestMapping("/api/oa/SinglePage")
public class SinglePageController {

	@Autowired
	SinglePageService service;

	/**
	 * 查询单据
	 *
	 * @return
	 */
	@RequestMapping("/query")
	public Rjson query(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			JSONObject list = service.query(listNo);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询明细
	 *
	 * @return
	 */
	@RequestMapping("/queryDetails")
	public Rjson queryDetails(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			List<JSONObject> list = service.queryDetails(listNo);

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
	@RequestMapping("/findApprovalRecordNote")
	@Transactional
	public Rjson findApprovalRecordNote(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			List<JSONObject> list = service.findApprovalRecordNote(listNo);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询审批记录表表格
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findApprovalRecordTable")
	public Rjson findApprovalRecordTable(HttpServletRequest request) {
		try {
			String listNo = EqualsUtil.string(request, "listNo", "单据号", true);

			JSONObject json = service.findApprovalRecordTable(listNo);

			return Rjson.success(json);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}


}
