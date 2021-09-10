package com.skeqi.mes.service.zch;

import com.alibaba.fastjson.JSONObject;

public interface AssembleBoltService {

	JSONObject getBolt(String snBarcode, String lineName, Integer stepNo, String stationBoltName);

	JSONObject assembleBolt(String snBarcode, String aValues, String tValues, String rValues, String lineName,
			Integer stepNo, String stationBoltName, String emp, Object batchCode);

}
