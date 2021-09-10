package com.skeqi.mes.service.chenj.srm.impl;

import com.skeqi.mes.mapper.chenj.srm.SRMLogMapper;
import com.skeqi.mes.service.chenj.srm.SRMLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SRMLogServiceImpl implements SRMLogService {

	@Resource
	private SRMLogMapper dao;
	@Override
	public Integer addSRMLogInfo(String user, String menuName) {
		// TODO Auto-generated method stub
		return dao.addSRMLogInfo(user, menuName);
	}

}
