package com.skeqi.mes.service.zch;

import com.alibaba.fastjson.JSONObject;

public interface AssembleMaterialService {

	JSONObject checkMaterial(String sn, String lineName, Integer stepNo, String stationName, String barcode);

	JSONObject assembleKeypart(String sn, String barcode, String materialName, String stationName, String emp, String lineName, Integer stepNo);

}
