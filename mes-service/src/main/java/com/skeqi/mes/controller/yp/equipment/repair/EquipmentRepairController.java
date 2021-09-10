package com.skeqi.mes.controller.yp.equipment.repair;

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
import com.skeqi.mes.service.yp.equipment.repair.EquipmentRepairService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 设备维修
 * @date 2021年3月11日
 */
@RestController
@RequestMapping("/api/equipment/repair")
public class EquipmentRepairController {

	@Autowired
	EquipmentRepairService service;

	/**
	 * @explain 查询集合
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {

			int pageSize = EqualsUtil.pageSize(request);
			int pageNumber = EqualsUtil.pageNumber(request);

			Integer lineId = EqualsUtil.integer(request, "lineId", "产线", false);
			Integer equipmentId = EqualsUtil.integer(request, "equipmentId", "设备", false);
			String repairman = EqualsUtil.string(request, "repairman", "维修人", false);
			String personInCharge = EqualsUtil.string(request, "personInCharge", "负责人", false);
			String startTime = EqualsUtil.string(request, "startTime", "开始时间", false);
			String endTime = EqualsUtil.string(request, "endTime", "结束时间", false);

			JSONObject json = new JSONObject();
			json.put("lineId", lineId);
			json.put("equipmentId", equipmentId);
			json.put("repairman", repairman);
			json.put("personInCharge", personInCharge);
			json.put("startTime", startTime);
			json.put("endTime", endTime);

			PageHelper.startPage(pageNumber, pageSize);
			List<JSONObject> list = service.list(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增维修记录
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	@Transactional
	public Rjson add(HttpServletRequest request) {
		try {
			String dt = EqualsUtil.string(request, "dt", "维修时间", false);
			int equipmentId = EqualsUtil.integer(request, "equipmentId", "设备", true);
			String repairman = EqualsUtil.string(request, "repairman", "维修人", false);
			String personInCharge = EqualsUtil.string(request, "personInCharge", "负责人 ", false);
			String reason = EqualsUtil.string(request, "reason", "维修原因", false);
			String remarks = EqualsUtil.string(request, "remarks", "备注", false);

			JSONObject json = new JSONObject();
			json.put("dt", dt);
			json.put("equipmentId", equipmentId);
			json.put("repairman", repairman);
			json.put("personInCharge", personInCharge);
			json.put("reason", reason);
			json.put("remarks", remarks);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新维修记录
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@Transactional
	public Rjson update(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);
			String dt = EqualsUtil.string(request, "dt", "维修时间", false);
			String repairman = EqualsUtil.string(request, "repairman", "维修人", false);
			String personInCharge = EqualsUtil.string(request, "personInCharge", "负责人 ", false);
			String reason = EqualsUtil.string(request, "reason", "维修原因", false);
			String remarks = EqualsUtil.string(request, "remarks", "备注", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("dt", dt);
			json.put("repairman", repairman);
			json.put("personInCharge", personInCharge);
			json.put("reason", reason);
			json.put("remarks", remarks);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除维修记录
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	@Transactional
	public Rjson delete(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);

			service.delete(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有产线跟设备
	 *
	 * @return
	 */
	@RequestMapping("/lineAndEquipment")
	public Rjson lineAndEquipment() {
		try {
			List<JSONObject> list = service.lineAndEquipment();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
