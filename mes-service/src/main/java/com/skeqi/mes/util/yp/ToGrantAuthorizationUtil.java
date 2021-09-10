package com.skeqi.mes.util.yp;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.prefs.Preferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.qh.ToGrantAuthorizationDao;

@Service
public class ToGrantAuthorizationUtil {

	@Autowired
	ToGrantAuthorizationDao dao;

	static char[] jiami(char[] str, int n) // 加密算法
	{
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

	static char[] jiemi(char[] str, int n) // 解密算法
	{
		int i, len;
		char[] mingwen;

		len = str.length;
		// 申请内存
		if ((mingwen = new char[len + 1]) == null) {
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

	/**
	 *
	 * @param effectiveActivationTime
	 * @param termOfValidity
	 * @param mac
	 * @param replacementNumber
	 * @return
	 * @throws Exception
	 */
	public static String encryption(String effectiveActivationTime, String termOfValidity, String mac,
			int replacementNumber) throws Exception {
		char[] miwen;

		miwen = jiami((effectiveActivationTime + termOfValidity + mac).toCharArray(), replacementNumber); // 加密

		return new String(miwen);
	}

	public static String decrypt(String ciphertext, int replacementNumber) {
		char[] miwen;
		char[] s = ciphertext.toCharArray();

		miwen = jiemi(s, replacementNumber); // 解密

		return new String(miwen);
	}

	// 校验
	public static boolean check() {
		// 获取激活信息
		String key = getValue("System");
		// 所有解密后的激活信息
		String data = decrypt(key, 33);
		// 注册时间
		String zhuCheTime = data.substring(0, 10);
		System.out.println(zhuCheTime);
		// 有效期开始时间
		String youXiaoQi1 = data.substring(10, 20);
		System.out.println(youXiaoQi1);
		// 有效期结束时间
		String youXiaoQi2 = data.substring(21, 31);
		System.out.println(youXiaoQi2);
		// mac地址
		String mac = data.substring(31, data.length());
		System.out.println(mac);
		return true;
	}

	public static void main(String[] args) throws Exception {
		writeValue();
	}

	public static String getLocalMac() throws Exception {
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

	// 把相应的值储存到变量中去
	public static void writeValue() {
		String[] keys = { "sys" };
		String[] values = { "SQSQNRQNSYSQSQNRQNSYSQSQNRRNSYcUNSfNZZNfZNXfNUT" };
		// HKEY_LOCAL_MACHINE\Software\JavaSoft\prefs下写入注册表值.
		Preferences pre = Preferences.systemRoot().node("/javaplayer");
		for (int i = 0; i < keys.length; i++) {
			pre.put(keys[i], values[i]);
		}
	}

	/***
	 * 根据key获取value
	 *
	 */
	public static String getValue(String key) {
		Preferences pre = Preferences.systemRoot().node("/javaplayer");
		return pre.get(key, "sys");
	}

}
