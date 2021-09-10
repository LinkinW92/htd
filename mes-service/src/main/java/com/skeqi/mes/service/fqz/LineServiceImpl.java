package com.skeqi.mes.service.fqz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.LineDAO;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.PMesStationPassEqStatusT;

@Service
public class LineServiceImpl implements LineService {

	@Autowired
	private LineDAO linedao;

	@Override
	public List<CMesLineT> listLine() {
		// TODO Auto-generated method stub
		return linedao.listLine();
	}
	@Override
	public List<PMesStationPassEqStatusT> listStatus(Integer id) {
		// TODO Auto-generated method stub
		List<PMesStationPassEqStatusT> listStatus = linedao.listStatus(id);
		return listStatus;
	}
	@Override
	public String findName(Integer id) {
		// TODO Auto-generated method stub
		return linedao.findName(id);
	}

}
