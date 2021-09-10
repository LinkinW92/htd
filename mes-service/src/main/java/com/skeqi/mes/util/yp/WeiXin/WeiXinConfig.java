package com.skeqi.mes.util.yp.WeiXin;

import com.skeqi.mes.util.yp.FileReading;

public class WeiXinConfig {

	public static String getErrorValue(String key) {
		return FileReading.getValue("WeiXin/errorCode.properties", key);
	}

	public static String getConfigValue(String key) {
		return FileReading.getValue("WeiXin/config.properties", key);
	}

	public static void getError(String key) throws Exception {
		String errorMsg = getErrorValue(key);;
		if(errorMsg.equals("")) {
			throw new Exception("未支持的异常code："+key);
		}
		throw new Exception(errorMsg);
	}

}
