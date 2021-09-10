package com.skeqi.mes.service.lcy;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.lcy.GetFlawRateDao;

@Service
public class GetFlawRateServiceImpl implements GetFlawRateService{

	@Autowired
	private GetFlawRateDao getFlawRateDao;

	@Override
	public int getProductionNumber(Date startDate, Date endDate,int lineId) {

		return getFlawRateDao.getProductionNumber(startDate,endDate,lineId);
	}

	@Override
	public int getFlawProductionNumber(Date startDate, Date endDate,int lineId) {
		return getFlawRateDao.getFlawProductionNumber(startDate,endDate,lineId);
	}



}
