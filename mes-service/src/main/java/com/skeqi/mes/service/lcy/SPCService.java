package com.skeqi.mes.service.lcy;

import java.util.List;

import com.skeqi.mes.pojo.CMesSPCCoeffic;
import com.skeqi.mes.pojo.PMesBoltT;

public interface SPCService {

	//获取spc工位
	List<String> querySPCStation();
	//获取spc查询数据的位置
	List<String> querySPCSite(String spcStation);
	//获取螺栓信息
	List<PMesBoltT> querySpcBoltList(String station, String site, String startTime, String endTime,
			String sampleNumber);

	CMesSPCCoeffic querySPCCoeffic(String sampleSize);


}
