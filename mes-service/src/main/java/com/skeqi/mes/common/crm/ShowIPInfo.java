package com.skeqi.mes.common.crm;

import javax.servlet.http.HttpServletRequest;

public class ShowIPInfo {

//	展示客户端IP地址
	public String getIpAddr(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
		return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
		}


}
