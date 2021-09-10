package com.skeqi.mes.service.qh.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.Preferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.qh.ToGrantAuthorizationDao;
import com.skeqi.mes.service.authorization.AuthorizationPublicKey;
import com.skeqi.mes.service.qh.ToGrantAuthorizationService;
import com.skeqi.mes.util.publicKey.PublicKeyDecode;
import com.skeqi.mes.util.publicKey.PublicKeyDemo;
import com.skeqi.mes.util.yp.SHA256;

import sun.misc.BASE64Decoder;

@Service
public class ToGrantAuthorizationServiceImpl implements ToGrantAuthorizationService {

	// 解码后的公钥
	private static PublicKey publicKey;

	@Autowired
	ToGrantAuthorizationDao dao;

	@Override
	public String getValue(String key) {
		Preferences pre = Preferences.systemRoot().node("/javaplayer");
		return pre.get(key, "sys");
	}

	@Override
	public String getLocalMac() throws Exception {
		InetAddress ia = InetAddress.getLocalHost();
		// 获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// 字节转换为整数
			int temp = mac[i] & 0xff;
			String str = Integer.toHexString(temp);
			if (str.length() == 1) {
				sb.append("0" + str);
			} else {
				sb.append(str);
			}
		}
		return sb.toString().toUpperCase();
	}

	@Override
	public String check() throws Exception {
		String key, data, zhuCheTime, youXiaoQi1, youXiaoQi2, mac, dataString;
		try {
			// 获取激活信息
			key = getValue("sys");

			if("sys".equals(key)) {
				throw new Exception("系统未激活");
			}

			// 对称解密
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bytes = decoder.decodeBuffer(key);

			// 获取到sha256 + 明文
			String result = new String(bytes,"utf-8");
			// sha256
			String sha256 = result.split(" ")[0];
			// 明文
			String jsonString = result.split(" ")[1];

			// json转sha256
			String sha256_1 = SHA256.getSHA256(jsonString);

			// 解码公钥
			publicKey = PublicKeyDecode.getPublicKey(AuthorizationPublicKey.PUBLIC_KEY);

			try {
				// 非对称 解密 sha256
				sha256 = PublicKeyDemo.decrypt(sha256, publicKey);
			} catch (Exception e) {
				throw new Exception("激活状态与本机不匹配");
			}

			// 对比两个sha256 如果不相等说明 明文被修改
			if (!sha256.equals(sha256_1)) {
				System.out.println("签名未通过");
				throw new Exception("签名未通过");
			}
			System.out.println("签名通过");

			JSONObject json = JSONObject.parseObject(jsonString);

			// 判断注册表内mac地址跟本机mac地址是否相同
			String localhostMac = getLocalMac();
			if (!localhostMac.equals(json.getString("mac"))) {
				throw new Exception("签名未通过");
			}

			// 获取当前系统时间
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			dataString = formatter.format(date).toString();

			// 判断系统时间是否被修改
			// 每次登录时会记录登录的时间
			// 查询比当前登录的日期大的数据条数
			// 如果有则说明系统时间被修改过
			// 此时将不给予校验通过
			int count = dao.findGreaterThanCurrentTimeCount(dataString);
			if (count != 0) {
				throw new Exception("系统日期可能被篡改");
			}

			// 查询最后一次登录校验的信息
			// 从而获取到激活码信息
			JSONObject json1 = dao.findMaxId();
			// 如果未查询出数据将判定为未激活
			// 不给予校验通过
			if (json1 == null) {
				throw new Exception("系统未激活");
			}
			// 对比注册表 跟数据库里的激活码是否一致
			// 不一致不给予校验通过
			if (!json1.getString("code").equals(key)) {
				throw new Exception("证书不一致");
			}

			youXiaoQi2 = json.getString("authorizeDeadline");

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage() + "---激活状态无法校验,请确认是否已激活,如已激活请联系供应商");
		}
		try {

			// 判断是否永久激活
			if (!"永久使用".equals(youXiaoQi2)) {
				// 计算到期时间
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// 到期时间
				Date dt1 = df.parse(youXiaoQi2 + " 23:59:59");
				// 现在时间
				Date dt2 = df.parse(dataString);
				if (dt1.getTime() < dt2.getTime()) {
					throw new Exception("软件已到期,请联系供应商");
				}
			}

			JSONObject json = new JSONObject();
			json.put("dt", dataString);
			json.put("code", key);
			int result = dao.add(json);
			if (result != 1) {
				throw new Exception("激活状态无法校验,请确认是否已激活,如已激活请联系供应商");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		if("永久使用".equals(youXiaoQi2)) {
			return youXiaoQi2;
		}
		return youXiaoQi2 + " 23:59:59";
	}

}
