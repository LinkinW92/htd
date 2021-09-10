package com.skeqi.mes.service.zch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.zch.CallLogsDao;

@Service
public class CallLogsServiceImpl implements CallLogsService {

	@Autowired
	CallLogsDao dao;

}
