package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.WarehouseListDAO;
import com.skeqi.mes.pojo.CMesCheckoutEnterT;
import com.skeqi.mes.pojo.CMesWarehouseListT;


@Service
public class WarehouseListServiceImpl implements WarehouseListService{

	@Autowired
	private WarehouseListDAO dao;

	@Override
	public List<CMesWarehouseListT> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAll(map);
	}

	@Override
	public List<CMesCheckoutEnterT> listEnter(String sn) {
		// TODO Auto-generated method stub
		return dao.listEnter(sn);
	}
}
