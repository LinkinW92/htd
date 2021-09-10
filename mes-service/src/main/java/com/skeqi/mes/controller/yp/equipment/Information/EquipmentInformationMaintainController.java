package com.skeqi.mes.controller.yp.equipment.Information;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.equipment.Information.EquipmentInformationMaintainService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 保养
 * @date 2020-12-18
 */
@RestController
@RequestMapping("/api/EquipmentInformationMaintain")
public class EquipmentInformationMaintainController {

	@Autowired
	EquipmentInformationMaintainService service;

	/**
	 * 新增保养记录
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	@Transactional
	public Rjson add(HttpServletRequest request) {
		try {
			int equipmentId = EqualsUtil.integer(request, "equipmentId", "设备", true);
			String equipmentName = EqualsUtil.string(request, "equipmentName", "设备名称", true);
			String lineName = EqualsUtil.string(request, "lineName", "产线名称", true);
			int maintenancePeriod = EqualsUtil.integer(request, "maintenancePeriod", "保养周期", true);

			JSONObject json = new JSONObject();
			json.put("equipmentId", equipmentId);
			json.put("equipmentName", equipmentName);
			json.put("lineName", lineName);
			json.put("maintenancePeriod", maintenancePeriod);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

}
