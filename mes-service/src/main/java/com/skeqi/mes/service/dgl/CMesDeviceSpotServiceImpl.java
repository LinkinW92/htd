package com.skeqi.mes.service.dgl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.dgl.CMesDeviceSpotDao;
import com.skeqi.mes.pojo.CMesDeviceSpotT;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesLineT;

@Service
public class CMesDeviceSpotServiceImpl implements CMesDeviceSpotService {

	@Autowired
	private CMesDeviceSpotDao spotDao;

	@Override
	public List<CMesDeviceSpotT> findDeviceSpotList(Map<String, Object> map) {
		return spotDao.findDeviceSpotList(map);
	}

	@Override
	public List<CMesLineT> lineList() {
		return spotDao.lineList();
	}

	@Override
	public List<CMesDeviceT> findlineById(Integer lineId) {
		return spotDao.findlineById(lineId);
	}

	@Override
	public Integer addDeviceSpot(CMesDeviceSpotT spot) {
		return spotDao.addDeviceSpot(spot);
	}

	@Override
	public Integer delDeviceSpot(Integer id) {
		return spotDao.delDeviceSpot(id);
	}

	@Override
	public CMesDeviceSpotT findByDeviceSpotId(Integer id) {
		return spotDao.findByDeviceSpotId(id);
	}

	@Override
	public Integer updateDeviceSpot(CMesDeviceSpotT spot) {
		return spotDao.updateDeviceSpot(spot);
	}

}
