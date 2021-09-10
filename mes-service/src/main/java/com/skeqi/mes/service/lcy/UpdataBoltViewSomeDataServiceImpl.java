package com.skeqi.mes.service.lcy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.lcy.SpcMapper;
import com.skeqi.mes.mapper.lcy.UpdataBoltViewSomeDataMapper;
import com.skeqi.mes.pojo.CMesRecipeDatilT;

@Service
public class UpdataBoltViewSomeDataServiceImpl implements UpdataBoltViewSomeDataService{

	@Autowired
	private  UpdataBoltViewSomeDataMapper ubv;

	@Override
	public CMesRecipeDatilT queryRecipeDatil(int id) {
		// TODO Auto-generated method stub
		return ubv.queryRecipeDatil(id);
	}

	@Override
	public void updataViewBoltData(String id, String jsonStr) {
		 ubv.updataViewBoltData(id,jsonStr);
	}










}
