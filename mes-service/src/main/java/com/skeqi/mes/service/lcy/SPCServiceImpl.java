package com.skeqi.mes.service.lcy;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skeqi.mes.mapper.lcy.SpcMapper;
import com.skeqi.mes.pojo.CMesSPCCoeffic;
import com.skeqi.mes.pojo.PMesBoltT;
@Service
public class SPCServiceImpl implements SPCService{

	@Autowired
	private  SpcMapper spcm;


	//获取spc工位信息
	@Override
	public List<String> querySPCStation() {
		// TODO Auto-generated method stub
		return spcm.querySPCStation();
	}
	//获取spc位置
	@Override
	public List<String> querySPCSite(String spcStation) {

		return spcm.querySPCSite(spcStation);
	}
	//获取螺栓数据
	@Override
	public List<PMesBoltT> querySpcBoltList(String station, String site, String startTime, String endTime,
			String sampleNumber) {
		// TODO Auto-generated method stub
		return spcm.querySpcBoltList(station,site,startTime,endTime,sampleNumber);
	}
	@Override
	public CMesSPCCoeffic querySPCCoeffic(String sampleSize) {
		// TODO Auto-generated method stub
		return spcm.querySPCCoeffic(sampleSize);
	}




}
