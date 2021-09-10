package com.skeqi.mes.service.qh.authorization;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 工控客户端授权
 * @date 2021-2-2
 */
public interface IndustrialControlClientService {

	public static List<JSONObject> tokens = new ArrayList<JSONObject>();

	/**
	 * @explain 登录
	 * @param ip
	 */
	public JSONObject login(String ip) throws Exception;

	/**
	 * @explain 心跳
	 * @param token
	 */
	public void heartbeat(String token) throws Exception;

	/**
	 * @explain 登出
	 * @param token
	 */
	public void logOut(String token) throws Exception;

	/**
	 * @explain 清除token
	 */
	public void cleanToken();

}
