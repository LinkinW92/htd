package com.skeqi.mes.service.all;

import com.alibaba.fastjson.JSONObject;

public interface AssembleLeakagePService {



	JSONObject assembleLeakage(String snBarcode, String lValues, String pValues, String rValues, String stationName,
			String emp, JSONObject jo);


	JSONObject assembleWeigh(String snBarcode, String stationName, String weighValues, String emp,JSONObject jo);
}
