package com.skeqi.mes.service.zch;

import com.alibaba.fastjson.JSONObject;

public interface UpdateSnService {

	JSONObject updateSN(String snBarconde, String stationname, String lineName);

}
