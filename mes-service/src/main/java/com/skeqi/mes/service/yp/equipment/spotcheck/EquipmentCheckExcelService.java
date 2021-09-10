package com.skeqi.mes.service.yp.equipment.spotcheck;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备点检excel
 *
 * @author yinp Date 2021年3月9日
 */
public interface EquipmentCheckExcelService {

	/**
	 * 导出点检配置
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> configList(JSONObject json);

	/**
	 * 导出点检版本
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> editionList(JSONObject json);

	/**
	 * 导出点检项
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
	 * 导入点检项
	 *
	 * @param file
	 * @throws Exception
	 */
	public String uploadItem(MultipartFile file) throws Exception;

}
