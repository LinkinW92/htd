package com.skeqi.mes.controller.yp.equipment.spotcheck;

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
import com.skeqi.mes.service.yp.equipment.spotcheck.EquipmentCheckConfigService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 设备点检配置
 *
 * @author yinp Date 2021年3月6日
 */
@RestController
@RequestMapping("/api/equipment/equipmentCheckConfig")
public class EquipmentCheckConfigController {

	@Autowired
	EquipmentCheckConfigService service;

	/**
	 * 查询点检配置
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			int pageNum = EqualsUtil.pageNumber(request);
			int pageSize = EqualsUtil.pageSize(request);

			String name = EqualsUtil.string(request, "name", "点检名", false);
			Integer equipmentId = EqualsUtil.integer(request, "equipmentId", "设备", false);
			Integer state = EqualsUtil.integer(request, "state", "状态", false);
			Integer lineId = EqualsUtil.integer(request, "lineId", "产线", false);

			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("equipmentId", equipmentId);
			json.put("state", state);
			json.put("lineId", lineId);

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
	 * 查询所有产线及设备
	 *
	 * @return
	 */
	@RequestMapping("/findLineAndEquipment")
	public Rjson findLineAndEquipment() {
		try {
			List<JSONObject> list = service.findLineAndEquipment();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增点检配置
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	@Transactional
	public Rjson add(HttpServletRequest request) {
		try {
			String name = EqualsUtil.string(request, "name", "点检名 ", true);
			String code = EqualsUtil.string(request, "code", "点检编号 ", true);
			String edition = EqualsUtil.string(request, "edition", "版本号", true);
			int editionId = EqualsUtil.integer(request, "editionId", "版本号id", true);
			int equipmentId = EqualsUtil.integer(request, "equipmentId", "设备id", true);
			String itemList = EqualsUtil.string(request, "itemList", "点检项", false);
			String explain = EqualsUtil.string(request, "explain", "描述", false);

			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("code", code);
			json.put("edition", edition);
			json.put("editionId", editionId);
			json.put("equipmentId", equipmentId);
			json.put("itemList", itemList);
			json.put("explain", explain);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新点检配置
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@Transactional
	public Rjson update(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);
			String name = EqualsUtil.string(request, "name", "点检名 ", true);
			String code = EqualsUtil.string(request, "code", "点检编号", true);
			int equipmentId = EqualsUtil.integer(request, "equipmentId", "设备id", true);
			int editionId = EqualsUtil.integer(request, "editionId", "版本id", true);
			String edition = EqualsUtil.string(request, "edition", "版本", true);
			int editioneEquipmentId = EqualsUtil.integer(request, "editioneEquipmentId", "", true);
			String explain = EqualsUtil.string(request, "explain", "描述", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("name", name);
			json.put("code", code);
			json.put("equipmentId", equipmentId);
			json.put("editionId", editionId);
			json.put("edition", edition);
			json.put("editioneEquipmentId", editioneEquipmentId);
			json.put("explain", explain);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除点检配置
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	@Transactional
	public Rjson delete(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);
			int equipmentId = EqualsUtil.integer(request, "equipmentId", "设备id", true);

			service.delete(id,equipmentId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询版本号
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findEdition")
	public Rjson findEdition(HttpServletRequest request) {
		try {
			int equipmentId = EqualsUtil.integer(request, "equipmentId", "设备id", true);

			List<JSONObject> list = service.findEdition(equipmentId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
