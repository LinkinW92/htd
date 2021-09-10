package com.skeqi.mes.service.qh.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.prefs.Preferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.qh.ActivationDao;
import com.skeqi.mes.service.qh.ActivationService;

/**
 * @author yinp
 * @explain 激活
 * @date 2020-10-28
 */
@Service
public class ActivationServiceImpl implements ActivationService {

	@Autowired
	ActivationDao dao;

	@Override
	public List<JSONObject> findList(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.findList(json);
	}

	@Override
	public char[] jiami(char[] str, int n) {
		int i, len;
		char[] miwen;

		len = str.length;
		if ((miwen = new char[len + 1]) == null) // 申请内存
		{
			System.out.print("申请内存失败!\n");
			System.exit(1);
		}
		for (i = 0; i < len; i++) // 移位替换
		{
			miwen[i] = (char) (str[i] + n);
		}
		miwen[len] = '\0';
		return miwen;
	}

	@Override
	public char[] jiemi(char[] str, int n) {
		int i, len;
		char[] mingwen;

		len = str.length;
		if ((mingwen = new char[len + 1]) == null)// 申请内存
		{
			System.out.print("申请内存失败!\n");
			System.exit(1);
		}
		for (i = 0; i < len; i++) // 移位替换
		{
			mingwen[i] = (char) (str[i] - n);
		}
		mingwen[len] = '\0';
		return mingwen;
	}

	@Override
	public String encryption(String effectiveActivationTime, String termOfValidity, String mac, int replacementNumber) {
		char[] miwen;

		miwen = jiami((effectiveActivationTime + termOfValidity + mac).toCharArray(), replacementNumber); // 加密

		return new String(miwen);
	}

	@Override
	public String decrypt(String ciphertext, int replacementNumber) {
		char[] miwen;
		char[] s = ciphertext.toCharArray();

		miwen = jiemi(s, replacementNumber); // 解密

		return new String(miwen);
	}

	@Override
	public void writeValue(String code) {
		String[] keys = { "sys" };
		String[] values = { code };
		// HKEY_LOCAL_MACHINE\Software\JavaSoft\prefs下写入注册表值.
		Preferences pre = Preferences.systemRoot().node("/javaplayer");
		for (int i = 0; i < keys.length; i++) {
			pre.put(keys[i], values[i]);
		}
	}

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
	public String check(String activationCode) throws Exception {
		String days;
		try {
			String key, mac;

			char[] miwen;

			miwen = jiemi(activationCode.toCharArray(), 33); // 解密

			// 解密后的信息
			key = new String(miwen);
			// 天数
			days = key.substring(0, 2);
			// 物理地址
			mac = key.substring(2, key.length()-1);

			// 判断输入的激活码是否适合该软件
			String localhostMac = getLocalMac();
			if (!mac.equals(localhostMac)) {
				throw new Exception("激活码有误");
			}

			int resultCount = dao.findCountByActivationCode(activationCode);
			if(resultCount!=0) {
				throw new Exception("激活码已被使用");
			}

			JSONObject json = new JSONObject();
			json.put("activationCode", activationCode);
			json.put("days", days);
			// 新增激活记录
			dao.add(json);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		try {
			String key, data, zhuCheTime, youXiaoQi1, youXiaoQi2, mac;
			char[] miwen;

			// 获取目前正在使用的code
			key = getValue("sys");
			miwen = jiemi(key.toCharArray(), 33);// 解密
			// 解密完的数据
			data = new String(miwen);
			// 注册时间
			zhuCheTime = data.substring(0, 10);
			// 有效期开始时间
			youXiaoQi1 = data.substring(10, 20);
			// 有效期结束时间
			youXiaoQi2 = data.substring(21, 31);
			// mac地址
			mac = data.substring(31, data.length() - 1);

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = df.parse(youXiaoQi2);// 有效期结束时间

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, Integer.parseInt(days));// 把日期往后增加一天.整数往后推,负数往前移动
			date=calendar.getTime();
			//更新 有效期结束时间
			youXiaoQi2 = formatter.format(date).toString();

			miwen = jiami((zhuCheTime + youXiaoQi1 + "_" + youXiaoQi2 + mac).toCharArray(), 33);//加密

			//写入注册表
			writeValue(new String(miwen));

			JSONObject json = new JSONObject();
			json.put("code", new String(miwen));

			dao.addCheck(json);

		} catch (Exception e) {
			throw new Exception("激活码有误");
		}

		return null;
	}

}
