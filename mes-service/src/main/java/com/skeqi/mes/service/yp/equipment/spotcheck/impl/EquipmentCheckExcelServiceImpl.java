package com.skeqi.mes.service.yp.equipment.spotcheck.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.equipment.spotcheck.EquipmentCheckExcelDao;
import com.skeqi.mes.service.yp.equipment.spotcheck.EquipmentCheckExcelService;
import com.skeqi.mes.util.yp.ExcelUtil;

/**
 * 设备点检excel
 *
 * @author yinp Date 2021年3月9日
 */
@Service
public class EquipmentCheckExcelServiceImpl implements EquipmentCheckExcelService {

	@Autowired
	EquipmentCheckExcelDao dao;

	/**
	 * 导出查询
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> configList(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.configList(json);
	}

	/**
	 * 导出点检版本
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> editionList(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.editionList(json);
	}

	/**
	 * 导出点检项
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> itemList() {
		// TODO Auto-generated method stub
		return dao.itemList();
	}

	/**
	 * 导入配置
	 *
	 * @param file
	 * @throws Exception
	 */
	@Override
	public String uploadConfig(MultipartFile file) throws Exception {

		// 判断文件是否为空，是否符合后缀名要求
		String msg = ExcelUtil.validateUploadFile(file, "xls", "xlsx");
		if (msg != null) {
			throw new Exception(msg);
		}

		byte[] byteArr = file.getBytes();
		InputStream is = new ByteArrayInputStream(byteArr);

		List<JSONObject> list = ExcelUtil.AnalysisExcel(is);

		if (list.size() < 2) {
			throw new Exception("无导入数据");
		}

		JSONObject json1 = list.get(0);
		if (!"点检名".equals(json1.getString("0")) || !"点检编号".equals(json1.getString("1"))
				|| !"产线名称".equals(json1.getString("2")) || !"设备名称".equals(json1.getString("3"))
				|| !"描述".equals(json1.getString("4"))) {
			throw new Exception("导入格式有误");
		}

		// 查询所有产线跟设备
		List<JSONObject> lineList = dao.findLineAndEquipment();

		StringBuffer msgBuffer = new StringBuffer();

		for (int i = 1; i < list.size(); i++) {
			JSONObject configJson = new JSONObject();

			if (dao.findConfigCountName(list.get(i).getString("0")) > 0) {
				msgBuffer.append("点检名：" + list.get(i).getString("0") + " 点检名已存在  ");
				continue;
			}

			if (dao.findConfigCountCode(list.get(i).getString("1")) > 0) {
				msgBuffer.append("点检名：" + list.get(i).getString("1") + "  点检编号已存在  ");
				continue;
			}

			j: for (int j = 0; j < lineList.size(); j++) {
				if (list.get(i).getString("2").equals(lineList.get(j).getString("lineName"))) {
					List<JSONObject> equipment = JSONObject.parseArray(lineList.get(j).getString("equipment"),
							JSONObject.class);
					for (int j2 = 0; j2 < equipment.size(); j2++) {
						if (list.get(i).getString("3").equals(equipment.get(j2).getString("equipmentName"))) {
							configJson.put("equipmentId", equipment.get(j2).getInteger("equipmentId"));
							break j;
						}
						if (j2 == (equipment.size() - 1)) {
							msgBuffer.append("点检名：" + list.get(i).getString("0") + " 设备不存在  ");
							break j;
						}
					}
				}
				if (j == (lineList.size() - 1)) {
					msgBuffer.append("点检名：" + list.get(i).getString("0") + " 产线不存在  ");
				}
			}

			if (configJson.getInteger("equipmentId") != null) {
				configJson.put("name", list.get(i).getString("0"));
				configJson.put("code", list.get(i).getString("1"));
				configJson.put("explain", list.get(i).getString("4"));

				// 通过设备id查询设备存在配置的数量
				if (dao.findConfigByEquipmentId(configJson.getInteger("equipmentId")) > 0) {
					msgBuffer.append("点检名：" + list.get(i).getString("0") + " 已存在点检配置  ");
					continue;
				}

				// 新增点检配置
				if (dao.addConfig(configJson) != 1) {
					msgBuffer.append("点检名：" + list.get(i).getString("0") + " 添加失败  ");
				}
			}

		}
		return msgBuffer.toString();
	}

	@Override
	public String uploadEdition(MultipartFile file) throws Exception {
		// 判断文件是否为空，是否符合后缀名要求
		String msg = ExcelUtil.validateUploadFile(file, "xls", "xlsx");
		if (msg != null) {
			throw new Exception(msg);
		}

		byte[] byteArr = file.getBytes();
		InputStream is = new ByteArrayInputStream(byteArr);

		List<JSONObject> list = ExcelUtil.AnalysisExcel(is);

		if (list.size() < 2) {
			throw new Exception("无导入数据");
		}

		JSONObject json1 = list.get(0);
		if (!"点检名".equals(json1.getString("0")) || !"版本号".equals(json1.getString("1"))
				|| !"状态".equals(json1.getString("2"))) {
			throw new Exception("导入格式有误");
		}

		List<JSONObject> configList = dao.configList(null);

		StringBuffer msgBuffer = new StringBuffer();

		for (int i = 1; i < list.size(); i++) {
			JSONObject edition = new JSONObject();
			for (int j = 0; j < configList.size(); j++) {
				if (configList.get(j).getString("name").equals(list.get(i).getString("0"))) {
					edition.put("equipmentId", configList.get(j).getInteger("equipmentId"));
					break;
				}

			}

			if (edition.getInteger("equipmentId") != null) {
				edition.put("edition", list.get(i).getString("1"));
				edition.put("state", list.get(i).getString("2").equals("启用") ? 1 : 2);

				if (dao.findEditionCount(list.get(i).getString("1"), edition.getInteger("equipmentId")) > 0) {
					msgBuffer.append("版本号：" + list.get(i).getString("0") + " 已存在  ");
					continue;
				}

				// 新增版本
				int result = dao.addEdition(edition);
				if (result != 1) {
					msgBuffer.append("版本号：" + list.get(i).getString("1") + " 添加失败  ");
				}
			} else {
				if (i == (list.size() - 1)) {
					msgBuffer.append("版本号：" + list.get(i).getString("0") + " 点检名不存在  ");
				}
			}

		}

		return msgBuffer.toString();
	}

	/**
	 * 导入点检项
	 *
	 * @param file
	 * @throws Exception
	 */
	@Override
	public String uploadItem(MultipartFile file) throws Exception {

		// 判断文件是否为空，是否符合后缀名要求
		String msg = ExcelUtil.validateUploadFile(file, "xls", "xlsx");
		if (msg != null) {
			throw new Exception(msg);
		}

		byte[] byteArr = file.getBytes();
		InputStream is = new ByteArrayInputStream(byteArr);

		List<JSONObject> list = ExcelUtil.AnalysisExcel(is);

		if (list.size() < 2) {
			throw new Exception("无导入数据");
		}

		JSONObject json1 = list.get(0);
		if (!"点检名".equals(json1.getString("0")) || !"版本号".equals(json1.getString("1"))
				|| !"点检项".equals(json1.getString("2"))) {
			throw new Exception("导入格式有误");
		}
		StringBuffer msgBuffer = new StringBuffer();

		// 查询所有配置项跟版本号
		List<JSONObject> configLit = dao.findConfigAndeditionAll();
		for (int i = 0; i < list.size(); i++) {
			JSONObject item = new JSONObject();
			item.put("item", list.get(i).getString("2"));
			item.put("explain", list.get(i).getString("3"));

			config: for (int j = 0; j < configLit.size(); j++) {
				JSONObject configJson = configLit.get(j);
				if (configJson.getString("configName").equals(list.get(i).getString("0"))) {
					List<JSONObject> editionList = JSON.parseArray(configJson.getString("edition"), JSONObject.class);
					for (int k = 0; k < editionList.size(); k++) {
						if (editionList.get(k).getString("editionName").equals(list.get(i).getString("1"))) {
							item.put("editionId", editionList.get(k).getInteger("editionId"));
							break config;
						}
						if (k == (editionList.size() - 1)) {
							msgBuffer.append("点检项：" + list.get(i).getString("1") + " 版本号不存在  ");
						}
					}

				}

				if (j == (configJson.size() - 1)) {
					msgBuffer.append("点检项：" + list.get(i).getString("0") + " 点检名不存在  ");
				}

			}
			if (item.getString("editionId") != null) {
				if (dao.addItem(item) != 1) {
					msgBuffer.append("点检项：" + list.get(i).getString("3") + " 添加失败  ");
				}
			}

		}
		return msgBuffer.toString();
	}

}
