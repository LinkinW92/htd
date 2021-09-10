package com.skeqi.mes.mapper.yin;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesDeviceTypeT;
import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesLabelType;

public interface DeviceDao {
	/**
	 * 设备管理列表
	 */
	List<CMesDeviceT> deviceList(Map<String, Object> map);
	/**
	 * 设备类型列表
	 */
	List<CMesDeviceTypeT> deviceTypeList();
	/**
	 * 添加设备信息
	 */
	void addDevice(Map<String, Object> map);
	/**
	 * 修改设备信息
	 */
	void editDevice(Map<String, Object> map);
	/**
	 * 删除设备
	 */
	void delDevice(Map<String, Object> map);
	/**
	 * 标签列表
	 */
	List<CMesLabelManagerT> listLabelManager(Map<String, Object> map);
	/**
	 * 添加标签
	 */
	void addLabelManager(Map<String, Object> map);
	/**
	 * 修改标签信息
	 */
	void editLabelManager(Map<String, Object> map);
	/**
	 * 删除
	 * @param map
	 */
	void removeLabelManager(Map<String, Object> map);
	/**
	 * 规则类型管理
	 */
	List<CMesLabelType> ruleTypeManagerList(Map<String, Object> map);
	/**
	 * 添加规则类型
	 */
	void addRuleTypeManager(Map<String, Object> map);
	/**
	 * 修改规则类型
	 */
	void editRuleTypeManager(Map<String, Object> map);
	/**
	 * 删除规则类型
	 */
	void removeRuleTypeManager(Map<String, Object> map);
	int countMaterialByPrintLable(Map<String, Object> map);
	int countProductionByPrintLable(Map<String, Object> map);
	int countLabelByLabelType(Map<String, Object> map);
	int countDeviceByName(Map<String, Object> map);
	int countLabelByNameAndNumber(Map<String, Object> map);
	int countRuleTypeByName(Map<String, Object> map);
}
