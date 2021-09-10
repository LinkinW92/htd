package com.skeqi.mes.service.lyh;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.lyh.PlanMDao;
import com.skeqi.mes.pojo.RMesPlanT;
@Service
public class PlanMServiceImpl implements PlanMService {
	@Autowired
	PlanMDao planMDao;

	@Override
	public List<RMesPlanT> rMesPlanList(@SuppressWarnings("rawtypes") Map map) {
		return planMDao.rMesPlanList(map);
	}

}
