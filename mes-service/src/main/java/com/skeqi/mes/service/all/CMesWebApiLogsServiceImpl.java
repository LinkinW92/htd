package com.skeqi.mes.service.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.all.CMesWebApiLogsDao;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;

@Service
public class CMesWebApiLogsServiceImpl implements CMesWebApiLogsService {

	@Autowired
	CMesWebApiLogsDao dao;

	@Override
	public void add(CMesWebApiLogs dx) {
		// TODO Auto-generated method stub
		dao.add(dx);
	}

}
