package com.skeqi.mes.service.fqz;

import java.util.List;

import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.PMesStationPassEqStatusT;

public interface LineService {

	//查询所有产线
	public List<CMesLineT> listLine();

	//查询该产线下所有工位设备的状态
	public List<PMesStationPassEqStatusT>  listStatus(Integer id);

	public String findName(Integer id);
}
