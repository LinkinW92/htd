package com.skeqi.mes.service.yin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.yin.DeviceDao;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesDeviceTypeT;
import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesLabelType;

@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	DeviceDao deviceDao;

	@Override
	public List<CMesDeviceT> deviceList(Map<String, Object> map) {
		return deviceDao.deviceList(map);
	}

	@Override
	public List<CMesDeviceTypeT> deviceTypeList() {
		return deviceDao.deviceTypeList();
	}
	@Override
	@Transactional
	public void addDevice(Map<String, Object> map) {
		deviceDao.addDevice(map);
	}
	@Override
	@Transactional
	public void editDevice(Map<String, Object> map) {
		deviceDao.editDevice(map);
	}
	@Override
	@Transactional
	public void delDevice(Map<String, Object> map) {
		deviceDao.delDevice(map);
	}

	@Override
	public List<CMesLabelManagerT> listLabelManager(Map<String, Object> map) {
		return deviceDao.listLabelManager(map);
	}
	@Override
	@Transactional
	public void addLabelManager(Map<String, Object> map) {
		deviceDao.addLabelManager(map);
	}
	@Override
	@Transactional
	public void editLabelManager(Map<String, Object> map) {
		deviceDao.editLabelManager(map);
	}
	@Override
	@Transactional
	public void removeLabelManager(Map<String, Object> map) {
		deviceDao.removeLabelManager(map);
	}
	@Override
	public List<CMesLabelType> ruleTypeManagerList(Map<String, Object> map) {
		return deviceDao.ruleTypeManagerList(map);
	}
	@Override
	@Transactional
	public void addRuleTypeManager(Map<String, Object> map) {
		deviceDao.addRuleTypeManager(map);
	}
	@Override
	@Transactional
	public void editRuleTypeManager(Map<String, Object> map) {
		deviceDao.editRuleTypeManager(map);
	}
	@Override
	@Transactional
	public void removeRuleTypeManager(Map<String, Object> map) {
		deviceDao.removeRuleTypeManager(map);
	}

	@Override
	public int countMaterialByPrintLable(Map<String, Object> map) {
		return deviceDao.countMaterialByPrintLable(map);
	}

	@Override
	public int countProductionByPrintLable(Map<String, Object> map) {
		return deviceDao.countProductionByPrintLable(map);
	}

	@Override
	public int countLabelByLabelType(Map<String, Object> map) {
		return deviceDao.countLabelByLabelType(map);
	}

	@Override
	public int countDeviceByName(Map<String, Object> map) {
		return deviceDao.countDeviceByName(map);
	}

	@Override
	public int countLabelByNameAndNumber(Map<String, Object> map) {
		return deviceDao.countLabelByNameAndNumber(map);
	}

	@Override
	public int countRuleTypeByName(Map<String, Object> map) {
		return deviceDao.countRuleTypeByName(map);
	}

}
