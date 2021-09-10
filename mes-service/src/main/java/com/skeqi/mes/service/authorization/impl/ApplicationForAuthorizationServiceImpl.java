package com.skeqi.mes.service.authorization.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.authorization.ApplicationForAuthorizationDao;
import com.skeqi.mes.finals.SystemFinal;
import com.skeqi.mes.service.authorization.ApplicationForAuthorizationService;
import com.skeqi.mes.service.authorization.AuthorizationPublicKey;
import com.skeqi.mes.util.publicKey.PublicKeyDecode;
import com.skeqi.mes.util.publicKey.PublicKeyDemo;
import com.skeqi.mes.util.yp.EqualsUtil;
import com.skeqi.mes.util.yp.NetworkInterfaceConfig;
import com.skeqi.mes.util.yp.SHA256;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author yinp
 * @explain 申请授权
 * @date 2020-11-6
 */
@Service
public class ApplicationForAuthorizationServiceImpl implements ApplicationForAuthorizationService {

	// 解码后的公钥
	private static PublicKey publicKey;

	@Autowired
	ApplicationForAuthorizationDao dao;

	/**
	 * @explain 申请授权
	 * @param request
	 * @return
	 */
	@Override
	public String apply(JSONObject json) throws Exception {

		String mac, applyTime;

		// 获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		applyTime = df.format(new Date()).toString();// new Date()为获取当前系统时间

		// 获取mac地址
		InetAddress ia = InetAddress.getLocalHost();
		mac = getLocalMac(ia);

		// mac地址
		json.put("mac", mac);
		// 授权申请时间
		json.put("applyTime", applyTime);
		// 密钥标识
		json.put("keySign", AuthorizationPublicKey.KEY_SIGN);
		// 系统版本号
		json.put("systemVersion", SystemFinal.VERSION);

		// json转sha256
		String sha256 = SHA256.getSHA256(json.toString());

		// 解码公钥
		publicKey = PublicKeyDecode.getPublicKey(AuthorizationPublicKey.PUBLIC_KEY);

		// 加密
		String result = PublicKeyDemo.encrypt(sha256, publicKey);

		// 对称加密
		BASE64Encoder encoder = new BASE64Encoder();
		result = encoder.encode((result + " " + json).getBytes("utf-8"));
		System.out.println(result);
		return result;
	}

	/**
	 * @explain 签名
	 * @param request
	 * @return
	 */
	@Override
	public String autograph(JSONObject jsonPar) throws Exception {

		String code = jsonPar.getString("code");

		// 对称解密
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = decoder.decodeBuffer(code);

		String result, sha256, jsonString;

		try {
			// 获取到sha256 + 明文
			result = new String(bytes,"utf-8");
			// sha256
			sha256 = result.split(" ")[0];
			// 明文
			jsonString = result.split(" ")[1];
		} catch (Exception e) {
			throw new Exception("激活码格式有误");
		}

		// json转sha256
		String sha256_1 = SHA256.getSHA256(jsonString);

		// 解码公钥
		publicKey = PublicKeyDecode.getPublicKey(AuthorizationPublicKey.PUBLIC_KEY);

		try {
			// 非对称 解密 sha256
			sha256 = PublicKeyDemo.decrypt(sha256, publicKey);
		} catch (Exception e) {
			throw new Exception("公钥不匹配，无法激活");
		}

		// 对比两个sha256 如果不相等说明 明文被修改
		if (!sha256.equals(sha256_1)) {
			System.out.println("签名被篡改");
			throw new Exception("签名被篡改");
		}

		JSONObject json = JSONObject.parseObject(jsonString);

		// 判断注册表内mac地址跟本机mac地址是否相同
		//支持多网口
		List<String> macList = NetworkInterfaceConfig.getAllMac();
		for (int i = 0; i < macList.size(); i++) {
			if (macList.get(i).toUpperCase().equals(json.getString("mac"))) {
				break;
			}
			if (i == macList.size() - 1) {
				throw new Exception("签名未通过");
			}
		}

		// 获取mac地址
//		InetAddress ia = InetAddress.getLocalHost();
//		String mac = getLocalMac(ia);
//
//		if (!mac.equals(json.getString("mac"))) {
//			System.out.println("签名无效");
//			throw new Exception("签名无效");
//		}

		// 获取当前系统时间
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String dt = sf.format(new Date()).toString();// new Date()为获取当前系统时间

		// 查询最后一次校验的数据
		JSONObject json1 = dao.findMaxId();

		if (json1 == null) {
			// 第一次使用
			json1 = new JSONObject();
		} else {

			// 不是永久激活需要计算到期时间
			if (!"永久使用".equals(json.getString("authorizeDeadline"))) {
				// 计算到期时间
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// 最后一次校验时间
				Date dt1 = df.parse(json1.getString("dt"));
				// 现在时间
				Date dt2 = df.parse(dt);
				if (dt1.getTime() > dt2.getTime()) {
					throw new Exception("签名失败，可能存在修改系统时间的行为");
				}
			}

			// 之前的激活码
			String oldCode = json1.getString("code");

			// 对称解密
			BASE64Decoder oldDecoder = new BASE64Decoder();
			byte[] oldBytes = oldDecoder.decodeBuffer(oldCode);

			String oldResult, oldSha256, oldJsonString;

			try {
				// 获取到sha256 + 明文
				oldResult = new String(oldBytes);
				// sha256
				oldSha256 = oldResult.split(" ")[0];
				// 明文
				oldJsonString = oldResult.split(" ")[1];
			} catch (Exception e) {
				throw new Exception("验证老激活码格式有误");
			}

			// 解码公钥
			publicKey = PublicKeyDecode.getPublicKey(AuthorizationPublicKey.PUBLIC_KEY);

			try {
				// 非对称 解密 sha256
				oldSha256 = PublicKeyDemo.decrypt(oldSha256, publicKey);
			} catch (Exception e) {
				throw new Exception("验证老激活码时公钥不匹配，无法激活");
			}

			JSONObject oldJson = JSONObject.parseObject(oldJsonString);

			if (oldJson.getInteger("numberOfRegistrations") > json.getInteger("numberOfRegistrations")) {
				throw new Exception("激活码已过期，无法激活");
			}

			if (oldJson.getInteger("numberOfRegistrations") == json.getInteger("numberOfRegistrations")) {
				throw new Exception("请勿重复使用激活码");
			}

//				// 老激活码的到期时间
//				dt1 = df.parse(oldJson.getString("authorizeDeadline") + " 23:59:59");
//				// 新激活码的到期时间
//				dt2 = df.parse(json.getString("authorizeDeadline") + " 23:59:59");
//				if (dt1.getTime() > dt2.getTime()) {
//					throw new Exception("当前激活码的激活时长小于当前系统所到期时长，激活失败");
//				}

			// 不是永久激活需要计算到期时间
			if (!"永久使用".equals(json.getString("authorizeDeadline"))) {
				// 计算是否在有效期内
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// 到期时间
				Date dt1 = df.parse(json.getString("authorizeDeadline") + " 23:59:59");
				// 现在时间
				Date dt2 = df.parse(dt);
				if (dt1.getTime() < dt2.getTime()) {
					throw new Exception("激活码已过期");
				}
			}

		}

		json1.put("code", code);
		json1.put("dt", dt);

		// 保存校验记录
		dao.addCheck(json1);

		// 证书写入注册表
		writeValue(code);

		return jsonString;
	}

	/**
	 * @explain 证书写入注册表
	 * @param code
	 */
	private void writeValue(String code) {
		String[] keys = { "sys" };
		String[] values = { code };
		// HKEY_LOCAL_MACHINE\Software\JavaSoft\prefs下写入注册表值.
		Preferences pre = Preferences.systemRoot().node("/javaplayer");
		for (int i = 0; i < keys.length; i++) {
			pre.put(keys[i], values[i]);
		}
	}

	/**
	 * @explain 获取mac地址
	 * @param ia
	 * @throws Exception
	 */
	private String getLocalMac(InetAddress ia) throws Exception {
		// TODO Auto-generated method stub
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

}
