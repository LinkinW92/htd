package com.skeqi.mes.service.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.all.GetcurrentempPDao;
import com.skeqi.mes.pojo.api.GetcurrentempPT;
import com.skeqi.mes.pojo.api.GetcurrentstepPT;
import com.skeqi.mes.pojo.api.UpdatecurrentempPT;
import com.skeqi.mes.pojo.api.UpdatecurrentstepPT;
import com.skeqi.mes.service.all.GetcurrentempPService;

@Service
public class GetcurrentempPServiceImpl implements GetcurrentempPService{

	@Autowired
	GetcurrentempPDao dao;

	@Override
	public GetcurrentempPT find1(String snBarcode, String stationEmpName, String lineEmpName) {
		// TODO Auto-generated method stub
		return dao.find1(snBarcode, stationEmpName, lineEmpName);
	}

	@Override
	public GetcurrentempPT find2(String snBarcode, String stationEmpName, String lineEmpName) {
		// TODO Auto-generated method stub
		return dao.find2(snBarcode, stationEmpName, lineEmpName);
	}

	//
	//
	//
	//
	@Override
	public GetcurrentstepPT find10(String serialnumber, String station, String line) {
		// TODO Auto-generated method stub
		return dao.find10(serialnumber, station, line);
	}

	@Override
	public GetcurrentstepPT find20(String serialnumber, String station, String line) {
		// TODO Auto-generated method stub
		return dao.find20(serialnumber, station, line);
	}

	//
	//
	//
	//
	//

	@Override
	public UpdatecurrentempPT find100(String serialnumber, String stationName, String line) {
		// TODO Auto-generated method stub
		return dao.find100(serialnumber, stationName, line);
	}

	@Override
	public void update1(String empname, String serialnumber, String stationName, String line) {
		// TODO Auto-generated method stub
		dao.update1(empname, serialnumber, stationName, line);
	}


	//
	//
	//
	//
	@Override
	public UpdatecurrentstepPT find1s(String serialnumber, String station, String line) {
		// TODO Auto-generated method stub
		return dao.find1s(serialnumber, station, line);
	}

	@Override
	public void update1s(String step, String serialnumber, String station, String line) {
		// TODO Auto-generated method stub
		dao.update1s(step, serialnumber, station, line);
	}

}
