package com.skeqi.mes.service.all;

import com.skeqi.mes.pojo.api.GetcurrentempPT;
import com.skeqi.mes.pojo.api.GetcurrentstepPT;
import com.skeqi.mes.pojo.api.UpdatecurrentempPT;
import com.skeqi.mes.pojo.api.UpdatecurrentstepPT;

public interface GetcurrentempPService {

	public GetcurrentempPT find1(String snBarcode, String stationEmpName, String lineEmpName);

	public GetcurrentempPT find2(String snBarcode, String stationEmpName, String lineEmpName);

	public GetcurrentstepPT find10(String serialnumber, String station, String line);

	public GetcurrentstepPT find20(String serialnumber, String station, String line);

	public UpdatecurrentempPT find100(String serialnumber, String stationName, String line);

	public void update1(String empname, String serialnumber, String stationName, String line);

	public UpdatecurrentstepPT find1s(String serialnumber, String station, String line);

	public void update1s(String step, String serialnumber, String station, String line);
}

