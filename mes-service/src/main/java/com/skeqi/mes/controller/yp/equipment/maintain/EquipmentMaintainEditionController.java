package com.skeqi.mes.controller.yp.equipment.maintain;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.equipment.maintain.EquipmentMaintainEditionService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 设备保养版本
 *
 * @author yinp
 * @Date 2021年3月11日
 */
@RestController
@RequestMapping("/api/equipment/equipmentMaintainEdition")
public class EquipmentMaintainEditionController {

	@Autowired
	EquipmentMaintainEditionService service;

	/**
	 * 查询保养 版本
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {

			Integer equipmentId = EqualsUtil.integer(request, "equipmentId", "设备", true);

			List<JSONObject> list = service.list(equipmentId);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增保养版本
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {

			Integer equipmentId = EqualsUtil.integer(request, "equipmentId", "设备", true);
			String edition = EqualsUtil.string(request, "edition", "版本号", true);
			Integer state = EqualsUtil.integer(request, "state", "状态", true);

			JSONObject json = new JSONObject();
			json.put("equipmentId", equipmentId);
			json.put("edition", edition);
			json.put("state", state);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新保养版本
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer equipmentId = EqualsUtil.integer(request, "equipmentId", "设备", true);
			String edition = EqualsUtil.string(request, "edition", "版本号", true);
			Integer state = EqualsUtil.integer(request, "state", "状态", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("equipmentId", equipmentId);
			json.put("edition", edition);
			json.put("state", state);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除保养版本
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);

			service.delete(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 启用保养版本
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/qiYong")
	public Rjson qiYong(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "id", true);
			Integer equipmentId = EqualsUtil.integer(request, "equipmentId", "设备", true);

			service.qiYong(id, equipmentId);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询闲置版本号
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/nullEditionList")
	public Rjson nullEditionList(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.nullEditionList();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
