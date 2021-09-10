package com.skeqi.mes.controller.yp.equipment.maintaindata;

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
import com.skeqi.mes.service.yp.equipment.maintaindata.MaintainDataService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 保养记录
 *
 * @date2021年3月12日
 * @author yinp
 */
@RestController
@RequestMapping("/api/equipment/maintainData")
public class MaintainDataController {

	@Autowired
	MaintainDataService service;

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

			String lineName = EqualsUtil.string(request, "lineName", "产线名称", false);
			String equipmentName = EqualsUtil.string(request, "equipmentName", "设备名称", false);
			String checkPerson = EqualsUtil.string(request, "checkPerson", "保养人", false);
			String startTime = EqualsUtil.string(request, "startTime", "开始时间", false);
			String endTime = EqualsUtil.string(request, "endTime", "结束时间", false);

			JSONObject json = new JSONObject();
			json.put("lineName", lineName);
			json.put("equipmentName", equipmentName);
			json.put("checkPerson", checkPerson);
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
	 * 新增记录
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
			String dt = EqualsUtil.string(request, "dt", "保养时间", true);
			String checkPerson = EqualsUtil.string(request, "checkPerson", "保养人", false);
			String remarks = EqualsUtil.string(request, "remarks", "备注", false);

			JSONObject json = new JSONObject();
			json.put("equipmentId", equipmentId);
			json.put("equipmentName", equipmentName);
			json.put("lineName", lineName);
			json.put("dt", dt);
			json.put("checkPerson", checkPerson);
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
			String dt = EqualsUtil.string(request, "dt", "保养时间", false);
			String checkPerson = EqualsUtil.string(request, "checkPerson", "保养人", false);
			String remarks = EqualsUtil.string(request, "remarks", "备注", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("dt", dt);
			json.put("checkPerson", checkPerson);
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

	/**
	 * 提交记录
	 * @param request
	 * @return
	 */
	@RequestMapping("sub")
	public Rjson sub(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);

			service.sub(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
