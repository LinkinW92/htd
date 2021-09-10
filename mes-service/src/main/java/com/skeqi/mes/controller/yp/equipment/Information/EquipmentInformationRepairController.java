package com.skeqi.mes.controller.yp.equipment.Information;

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
import com.skeqi.mes.service.yp.equipment.Information.EquipmentInformationRepairService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 设备维修
 * @date 2020-12-18
 */
@RestController
@RequestMapping("/api/EquipmentInformationRepair")
public class EquipmentInformationRepairController {

	@Autowired
	EquipmentInformationRepairService service;

	/**
	 * @explain 查询集合
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			int pageNumber = EqualsUtil.pageNumber(request);
			int pageSize = EqualsUtil.pageSize(request);
			int parentId = EqualsUtil.integer(request, "parentId", "设备id", false);

			PageHelper.startPage(pageNumber, pageSize);
			List<JSONObject> list = service.list(parentId);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * @explain 新增维修
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			int parentId = EqualsUtil.integer(request, "parentId", "设备id", true);
			String repairPerson = EqualsUtil.string(request, "repairPerson", "维修人", true);
			String emp = EqualsUtil.string(request, "emp", "负责员工", true);
			String reason = EqualsUtil.string(request, "reason", "维修原因", true);
			String note = EqualsUtil.string(request, "note", "备注", false);

			JSONObject json = new JSONObject();
			json.put("parentId", parentId);
			json.put("repairPerson", repairPerson);
			json.put("emp", emp);
			json.put("reason", reason);
			json.put("note", note);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
