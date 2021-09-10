package com.skeqi.mes.service.yin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.yin.RouteDao;

@Service
@Transactional
public class RouteServiceImpl implements RouteService {

	@Autowired
	RouteDao routeDao;

	@Override
	public void insertRoute(Map<String, Object> map) {
		// TODO Auto-generated method stub
		routeDao.insertRoute(map);
	}

	@Override
	public void insertProductionWay(Map<String, Object> map) {
		// TODO Auto-generated method stub
		routeDao.insertProductionWay(map);
	}

}
