package com.skeqi.mes.service.yp.impl;

import java.net.InetAddress;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.ProductionsInformationDao;
import com.skeqi.mes.service.yp.ProductionsInformationService;

/**
 * @author yinp
 * @explain 生产信息
 * @date 2021-2-20
 */
@Service
public class ProductionsInformationServiceImpl implements ProductionsInformationService {

	@Autowired
	ProductionsInformationDao dao;

	/**
	 * @explain 查询
	 * @return
	 */
	@Override
	public List<JSONObject> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	/**
	 * @explain 一键关机
	 * @param ip
	 */
	@Override
	public void shutDown(List<JSONObject> list) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer msgBuffer = new StringBuffer();
		StringBuffer msgBuffer1 = new StringBuffer();
		for (JSONObject json : list) {
			String stationName = json.getString("stationName");
			String ip = json.getString("ip");
			String userName = json.getString("userName");
			String password = json.getString("password");

			if (findPing(ip)) {
				if (!shutdownAll(ip, userName, password)) {
					if (msgBuffer1.length() == 0) {
						msgBuffer1.append(stationName);
					} else {
						msgBuffer1.append("、" + stationName);
					}
				}
			} else {
				if (msgBuffer.length() == 0) {
					msgBuffer.append(stationName);
				} else {
					msgBuffer.append("、" + stationName);
				}

			}
		}
		if (msgBuffer.length() != 0) {
			msgBuffer.append("工位不在线");
		}
		if (msgBuffer1.length() != 0) {
			msgBuffer1.append("工位关机失败");
			msgBuffer.append(msgBuffer1);
		}
		if (msgBuffer.length() != 0) {
			throw new Exception(msgBuffer.toString());
		}
	}

	public static boolean shutdownAll(String ip, String userName, String password) {
		try {
			Runtime.getRuntime().exec("net use * /del /y");
			Process process = Runtime.getRuntime()
					.exec("net use \\\\" + ip + "\\ipc$ \"" + password + "\" /user:\"" + userName + "\"");
			int i = process.waitFor();// 等待命令执行完
			if (i == 0) {
				Runtime.getRuntime().exec("shutdown -s -t 0 -m \\\\" + ip + " -c \"test\" -f");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(findPing("127.0.0.1"));
	}

	public static boolean findPing(String host) {
		boolean status = true;
		try {
			status = InetAddress.getByName(host).isReachable(10000);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;

	}

}
