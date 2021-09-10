package com.skeqi.mes.service.yin;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesDeviceTypeT;
import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesLabelType;

public interface DeviceService {

	List<CMesDeviceT> deviceList(Map<String, Object> map);
	List<CMesDeviceTypeT> deviceTypeList();
	void addDevice(Map<String, Object> map);
	void editDevice(Map<String, Object> map);
	void delDevice(Map<String, Object> map);
	List<CMesLabelManagerT> listLabelManager(Map<String, Object> map);
	void addLabelManager(Map<String, Object> map);
	void editLabelManager(Map<String, Object> map);
	void removeLabelManager(Map<String, Object> map);
	List<CMesLabelType> ruleTypeManagerList(Map<String, Object> map);
	void addRuleTypeManager(Map<String, Object> map);
	void editRuleTypeManager(Map<String, Object> map);
	void removeRuleTypeManager(Map<String, Object> map);
	int countMaterialByPrintLable(Map<String, Object> map);
	int countProductionByPrintLable(Map<String, Object> map);
	int countLabelByLabelType(Map<String, Object> map);
	int countDeviceByName(Map<String, Object> map);
	int countLabelByNameAndNumber(Map<String, Object> map);
	int countRuleTypeByName(Map<String, Object> map);

}
