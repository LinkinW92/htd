package com.skeqi.mes.util.yp;

import java.util.ArrayList;
import java.util.List;

/**
 * 开放接口
 * @author yinp
 *
 */
public class OpenInterface {

	private static List<String> OPEN_INTERFACE_URL;

	//设置无需拦截的接口
	static {
		OPEN_INTERFACE_URL = new ArrayList<String>();

		//登录接口
		OPEN_INTERFACE_URL.add("/api/getLoginSomeValueL");
		//查询用户接口
		OPEN_INTERFACE_URL.add("/api/user/userList");
	}

	public static List<String> getOPEN_INTERFACE_URL() {
		return OPEN_INTERFACE_URL;
	}

	public static void setOPEN_INTERFACE_URL(List<String> oPEN_INTERFACE_URL) {
		OPEN_INTERFACE_URL = oPEN_INTERFACE_URL;
	}
}
