package com.skeqi.mes.controller.yp.equipment.spotcheck;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.equipment.spotcheck.EquipmentMaintainService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * 设备保养
 *
 * @author yinp
 * @Date 2021年3月6日
 */
@RestController
@RequestMapping("/api/equipment/equipmentMaintain")
public class EquipmentMaintainController {

	@Autowired
	EquipmentMaintainService service;

	/**
	 * 查询保养记录集合
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			int pageNum = EqualsUtil.pageNumber(request);
			int pageSize = EqualsUtil.pageSize(request);

			JSONObject json = new JSONObject();

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
	 * 新增保养记录
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			int equipmentId = EqualsUtil.integer(request, "equipmentId", "设备Id", true);
			String dt = EqualsUtil.string(request, "dt", "保养时间", true);
			String personLiable = EqualsUtil.string(request, "personLiable", "负责人", true);
			String maintainer = EqualsUtil.string(request, "maintainer", "保养人", true);
			String note = EqualsUtil.string(request, "note", "备注", true);

			JSONObject json = new JSONObject();
			json.put("equipmentId", equipmentId);
			json.put("dt", dt);
			json.put("personLiable", personLiable);
			json.put("maintainer", maintainer);
			json.put("note", note);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新保养记录
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			int id = EqualsUtil.integer(request, "id", "id", true);
			String dt = EqualsUtil.string(request, "dt", "保养时间", true);
			String personLiable = EqualsUtil.string(request, "personLiable", "负责人", true);
			String maintainer = EqualsUtil.string(request, "maintainer", "保养人", true);
			String note = EqualsUtil.string(request, "note", "备注", true);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("dt", dt);
			json.put("personLiable", personLiable);
			json.put("maintainer", maintainer);
			json.put("note", note);

			service.update(json);

			return Rjson.success();
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

}
