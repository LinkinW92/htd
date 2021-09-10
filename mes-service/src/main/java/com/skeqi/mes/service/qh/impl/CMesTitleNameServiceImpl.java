package com.skeqi.mes.service.qh.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.qh.CMesTitleNameDao;
import com.skeqi.mes.service.qh.CMesTitleNameService;

@Service
public class CMesTitleNameServiceImpl implements CMesTitleNameService{


	@Autowired
	CMesTitleNameDao dao;

	@Override
	public String findTitleName() {
		// TODO Auto-generated method stub
		return dao.findTitleName();
	}

	@Override
	public void updateTitleName(String name) {
		// TODO Auto-generated method stub
		dao.updateTitleName(name);
	}

}
