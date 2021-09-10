package com.skeqi.mes.service.dgl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.dgl.CMesDeviceDao;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesStationT;

@Service
public class CMesDeviceServiceImpl implements CMesDeviceService {

	@Autowired
	private CMesDeviceDao deviceDao;

	@Override
	public List<CMesDeviceT> findDeviceList(Map<String, Object> map) {
		return deviceDao.findDeviceList(map);
	}

	@Override
	public List<CMesStationT> StationNameList() {
		return deviceDao.StationNameList();
	}

	@Override
	public List<CMesLineT> lineList() {
		return deviceDao.lineList();
	}

	@Override
	public List<CMesDeviceT> findByDeviceName(String deviceName) {
		return deviceDao.findByDeviceName(deviceName);
	}

	@Override
	public void addCMesDevice(Map<String, Object> map) {
		deviceDao.addCMesDevice(map);
	}

	@Override
	public Integer delDevice(Integer id) {
		return deviceDao.delDevice(id);
	}

	@Override
	public List<CMesDeviceT> findByDeviceId(Integer id) {
		return deviceDao.findByDeviceId(id);
	}

	@Override
	public Integer updateDevice(CMesDeviceT device) {
		return deviceDao.updateDevice(device);
	}

}
