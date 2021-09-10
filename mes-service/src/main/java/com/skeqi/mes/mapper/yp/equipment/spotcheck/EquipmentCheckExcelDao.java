package com.skeqi.mes.mapper.yp.equipment.spotcheck;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备点检excel
 *
 * @author yinp Date 2021年3月9日
 */
public interface EquipmentCheckExcelDao {

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
	 * 新增点检配置
	 *
	 * @param json
	 */
	public int addConfig(JSONObject json);

	/**
	 * 查询点检配置名是否存在
	 *
	 * @param name
	 * @return
	 */
	public int findConfigCountName(@Param("name") String name);

	/**
	 * 查询点检配置编号是否存在
	 *
	 * @param name
	 * @return
	 */
	public int findConfigCountCode(@Param("code") String code);

	/**
	 * 查询所有产线跟设备
	 *
	 * @return
	 */
	public List<JSONObject> findLineAndEquipment();

	/**
	 * 通过设备id查询设备存在配置的数量
	 *
	 * @param equipmentId
	 * @return
	 */
	public int findConfigByEquipmentId(@Param("equipmentId") int equipmentId);

	/**
	 * 新增版本
	 *
	 * @param json
	 * @return
	 */
	public int addEdition(JSONObject json);

	/**
	 * 查询是否存在重复版本号
	 * @param edition
	 * @param equipmentId
	 * @return
	 */
	public int findEditionCount(@Param("edition") String edition, @Param("equipmentId") int equipmentId);

	/**
	 * 查询所有配置项跟版本号
	 * @return
	 */
	public List<JSONObject> findConfigAndeditionAll();

	/**
	 * 新增点检项
	 * @param json
	 * @return
	 */
	public int addItem(JSONObject json);

}
