package com.skeqi.mes.service.qh.authorization.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.prefs.Preferences;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.qh.authorization.IndustrialControlClientService;

import sun.misc.BASE64Decoder;

@Service
public class IndustrialControlClientServiceImpl implements IndustrialControlClientService {

	/**
	 * @explain 登录
	 * @param ip
	 */
	@Override
	public JSONObject login(String ip) throws Exception {

		for (JSONObject token : tokens) {
			if (token.getString("ip").equals(ip)) {
				throw new Exception("工控客户端已登录");
			}
		}

		if (tokens.size() >= getMaxICClientConnections()) {
			throw new Exception("工控客户端登录数已上限");
		}

		JSONObject token = new JSONObject();
		token.put("ip", ip);
		token.put("token", getToken());
		token.put("time", System.currentTimeMillis() / 1000);

		tokens.add(token);

		return token;
	}

	/**
	 * @explain 心跳
	 * @param token
	 * @throws Exception
	 */
	@Override
	public void heartbeat(String token) throws Exception {
		boolean boo = false;
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i).getString("token").equals(token)) {
				tokens.get(i).put("time", System.currentTimeMillis() / 1000);
				boo = true;
			}
		}
		if (!boo) {
			throw new Exception("工控客户端已离线");
		}
	}

	/**
	 * @explain 登出
	 * @param token
	 */
	@Override
	public void logOut(String token) throws Exception {
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i).getString("token").equals(token)) {
				tokens.remove(i);
				return;
			}
		}
	}

	/**
	 * @explain 清除token
	 */
	@Scheduled(cron = "0/10 * * * * ?")
	@Override
	public void cleanToken() {
		long date1 = System.currentTimeMillis() / 1000;
		for (int i = 0; i < tokens.size(); i++) {
			long date2 = tokens.get(i).getLong("time");
			if (date1 - date2 > 30) {
				tokens.remove(i);
			}
		}

	}

	int getMaxICClientConnections() throws IOException {
		String key = readValue("sys");

		// 对称解密
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = decoder.decodeBuffer(key);

		// 获取到sha256 + 明文
		String result = new String(bytes, "utf-8");

		// 明文
		String jsonString = result.split(" ")[1];

		JSONObject json = JSONObject.parseObject(jsonString);

		return json.getInteger("maxICClientConnections");
	}

	// 读取注册表
	String readValue(String key) {
		Preferences pre = Preferences.systemRoot().node("/javaplayer");
		String string = pre.get(key, "");
		return string;
	}

	String getToken() {
		String[] w = { "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l",
				"z", "x", "c", "v", "b", "n", "m" };
		String[] W = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L",
				"Z", "X", "C", "V", "B", "N", "M" };
		int[] n = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		StringBuffer str = new StringBuffer();

		Random r = new Random();
		for (int i = 0; i < 16; i++) {
			int ran = r.nextInt(25);
			str.append(w[ran]);
			ran = r.nextInt(25);
			str.append(W[ran]);
			ran = r.nextInt(9);
			str.append(n[ran]);
		}
		return str.toString();
	}

}
