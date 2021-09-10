package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.DeviceUpkeepDAO;
import com.skeqi.mes.pojo.CMesDeviceUpkeep;

@Service
public class DeviceUpkeepServiceImpl implements DeviceUpkeepService {

	@Autowired
	private DeviceUpkeepDAO dao;
 	@Override
	public List<CMesDeviceUpkeep> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAll(map);
	}

	@Override
	public Integer insertUpkeep(CMesDeviceUpkeep c) {
		// TODO Auto-generated method stub
		return dao.insertUpkeep(c);
	}

	@Override
	public Integer updateupkeep(CMesDeviceUpkeep c) {
		// TODO Auto-generated method stub
		return dao.updateupkeep(c);
	}


	@Override
	public CMesDeviceUpkeep findByid(String id) {
		// TODO Auto-generated method stub
		return dao.findByid(id);
	}

	@Override
	public Integer deleteupkeep(String id) {
		// TODO Auto-generated method stub
		return dao.deleteupkeep(id);
	}

	@Override
	public Integer findTime(Integer id) {
		// TODO Auto-generated method stub
		return dao.findTime(id);
	}

	@Override
	public void updateDate(String id, String value) {
		// TODO Auto-generated method stub
		dao.updateDate(id, value);
	}

}
