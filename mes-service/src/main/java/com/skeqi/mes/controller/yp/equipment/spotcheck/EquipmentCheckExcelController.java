package com.skeqi.mes.controller.yp.equipment.spotcheck;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.yp.equipment.spotcheck.EquipmentCheckExcelService;
import com.skeqi.mes.util.Rjson;

/**
 * 设备点检excel
 *
 * @author yinp Date 2021年3月9日
 */
@RestController
@RequestMapping("/api/equipment/EquipmentCheckExcel")
public class EquipmentCheckExcelController {

	@Autowired
	EquipmentCheckExcelService service;

	/**
	 * 导出点检配置
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/configList")
	public Rjson configList(HttpServletRequest request) {
		try {
			JSONObject json = new JSONObject();
			List<JSONObject> list = service.configList(json);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 导出点检版本
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/editionList")
	public Rjson editionList(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.editionList(null);

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 导出点检项
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/itemList")
	public Rjson itemList(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.itemList();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 导入配置
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/uploadConfig")
	public Rjson uploadConfig(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
		try {

			String msg = service.uploadConfig(file);

			return Rjson.success(msg);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 导入版本
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/uploadEdition")
	public Rjson uploadEdition(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
		try {

			String msg = service.uploadEdition(file);

			return Rjson.success(msg);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 导入点检项
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/uploadItem")
	public Rjson uploadItem(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
		try {

			String msg = service.uploadItem(file);

			return Rjson.success(msg);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
