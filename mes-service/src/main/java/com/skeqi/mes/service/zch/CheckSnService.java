package com.skeqi.mes.service.zch;

import com.alibaba.fastjson.JSONObject;

public interface CheckSnService {

	JSONObject checkSN(String snBarcode, String station, String line, Boolean getStationRecipe);

	void test();

	JSONObject scanEmp(String emp, String lineName, String stationName, String sN, Integer stepNo);

}
