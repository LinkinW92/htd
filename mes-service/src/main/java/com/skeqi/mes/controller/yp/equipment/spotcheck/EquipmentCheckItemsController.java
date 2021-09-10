package com.skeqi.mes.controller.yp.equipment.spotcheck;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.equipment.spotcheck.EquipmentCheckItemsService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 设备点检项
 *
 * @Date 2021年3月4日
 * @author yinp
 */
@RestController
@RequestMapping("/api/equipment/equipmentCheckItems")
public class EquipmentCheckItemsController {

	@Autowired
	EquipmentCheckItemsService service;

	/**
	 * 查询设备点检项
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {

			Integer editionId = EqualsUtil.integer(request, "editionId", "版本", true);

			List<JSONObject> list = service.list(editionId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增点检项
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	@Transactional
	public Rjson add(HttpServletRequest request) {
		try {
			String checkItems = EqualsUtil.string(request, "checkItems", "点检项", true);
			String explain = EqualsUtil.string(request, "explain", "描述", false);
			int editionId = EqualsUtil.integer(request, "editionId", "版本", true);

			JSONObject json = new JSONObject();
			json.put("checkItems", checkItems);
			json.put("explain", explain);
			json.put("editionId", editionId);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新点检项
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@Transactional
	public Rjson update(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);
			String checkItems = EqualsUtil.string(request, "checkItems", "点检项", true);
			String explain = EqualsUtil.string(request, "explain", "描述", false);
			int editionId = EqualsUtil.integer(request, "editionId", "版本", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("checkItems", checkItems);
			json.put("explain", explain);
			json.put("editionId", editionId);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除点检项
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
}
