package com.skeqi.mes.service.yp.equipment.maintain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备保养excel
 *
 * @author yinp Date 2021年3月11日
 */
public interface EquipmentMaintainExcelService {

	/**
	 * 导出保养配置
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> configList(JSONObject json);

	/**
	 * 导出保养版本
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> editionList(JSONObject json);

	/**
	 * 导出保养项
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> itemList();

	/**
	 * 导入配置
	 *
	 * @param file
	 * @throws Exception
	 */
	public String uploadConfig(MultipartFile file) throws Exception;

	/**
	 * 导入版本
	 *
	 * @param file
	 * @throws Exception
	 */
	public String uploadEdition(MultipartFile file) throws Exception;

	/**
	 * 导入保养项
	 *
	 * @param file
	 * @throws Exception
	 */
	public String uploadItem(MultipartFile file) throws Exception;

}
