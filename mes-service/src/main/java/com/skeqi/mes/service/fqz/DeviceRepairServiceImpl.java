package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.DeviceRepairDAO;
import com.skeqi.mes.pojo.CMesDeviceRepairT;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesLineT;

@Service
public class DeviceRepairServiceImpl implements DeviceRepairService {

	@Autowired
	private DeviceRepairDAO dao;

	@Override
	public List<CMesDeviceRepairT> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAll(map);
	}

	@Override
	public Integer insertRepair(CMesDeviceRepairT c) {
		// TODO Auto-generated method stub
		return dao.insertRepair(c);
	}

	@Override
	public Integer updateRepair(CMesDeviceRepairT c) {
		// TODO Auto-generated method stub
		return dao.updateRepair(c);
	}

	@Override
	public Integer deleteRepair(String id) {
		// TODO Auto-generated method stub
		return dao.deleteRepair(id);
	}

	@Override
	public List<CMesLineT> findLine() {
		// TODO Auto-generated method stub
		return dao.findLine();
	}

	@Override
	public List<CMesDeviceT> findDevice() {
		// TODO Auto-generated method stub
		return dao.findDevice();
	}

	@Override
	public CMesDeviceRepairT findByid(String id) {
		// TODO Auto-generated method stub
		return dao.findByid(id);
	}

	@Override
	public List<CMesDeviceT> findDevices(String id) {
		// TODO Auto-generated method stub
		return dao.findDevices(id);
	}

}
