package com.skeqi.mes.pojo.yp;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.util.yp.WeiXin.WeiXinConfig;

/**
 * 微信配置
 *
 * @author yinp
 * @date 2021年6月11日
 *
 */
public class WeiXin {

	// 企业ID
	public static final String CORPID = WeiXinConfig.getConfigValue("CORPID");

	// 应用的凭证密钥
	public static final String CORPSECRET = WeiXinConfig.getConfigValue("CORPSECRET");

	// 获取到的凭证，最长为512字节
	private static String ACCESS_TOKEN = WeiXinConfig.getConfigValue("ACCESS_TOKEN");

	// 凭证的有效时间（秒）
	private static long EXPIRES_IN;

	// 返回时间，时间戳，单位秒
	private static long RETURN_TIME;

	// 数据持久化
	public static String getACCESS_TOKEN() {

		long newDate = System.currentTimeMillis() / 1000 + (60 * 10);

		if (newDate - getRETURN_TIME() > getEXPIRES_IN()) {
			ACCESS_TOKEN = null;
			EXPIRES_IN = 0;
			RETURN_TIME = 0;
		}

		return ACCESS_TOKEN;
	}

	public static void setACCESS_TOKEN(String aCCESS_TOKEN) {
		ACCESS_TOKEN = aCCESS_TOKEN;
	}

	public static long getEXPIRES_IN() {
		return EXPIRES_IN;
	}

	public static void setEXPIRES_IN(long eXPIRES_IN) {
		EXPIRES_IN = eXPIRES_IN;
	}

	public static long getRETURN_TIME() {
		return RETURN_TIME;
	}

	public static void setRETURN_TIME(long rETURN_TIME) {
		RETURN_TIME = rETURN_TIME;
	}

}
