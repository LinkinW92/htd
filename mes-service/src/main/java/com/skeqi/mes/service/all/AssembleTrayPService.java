package com.skeqi.mes.service.all;

import com.alibaba.fastjson.JSONObject;

public interface AssembleTrayPService {

	public JSONObject assembleTray(String snBarcode, String stationName, String lineName, String trayNum,JSONObject jo);

	JSONObject checkTray(String line, String trayNum, JSONObject jo);

}
