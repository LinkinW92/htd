package com.skeqi.mes.service.all;

import com.alibaba.fastjson.JSONObject;

public interface AssembleKeypartPService {

	JSONObject assembleKeypart(String snBarcode, String materialBarcode, String materialName, String stationName,
			String emp, JSONObject jo);

	JSONObject checkMaterial(String materialBarcode, String stationInName, String stepNo, String lineName,
			String snMaterial, JSONObject jo);

}
