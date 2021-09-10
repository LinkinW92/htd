package com.skeqi.mes.common.lcy;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class Encryption {
	private  static MessageDigest digest = null;

	static {

			try {
				digest = MessageDigest.getInstance("md5");
			} catch (NoSuchAlgorithmException e) {

			}

	}

	//加密密码
	public static String encryptPassWord(String plain){
		if(plain == null){
			return null;
		}
		byte[] bytes = new byte[0];
		try {
			bytes = digest.digest(plain.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		 for (byte b : bytes) {
             // 与运算
             int number = b & 0xff;// 加盐
             String str = Integer.toHexString(number);
             if (str.length() == 1) {
                 buffer.append("0");
             }
             buffer.append(str);

         }
		return buffer.toString();
	}


	//循环多少次
	public static String getPassWord(String salt,int hashIterations){

		for (int i = 0; i <hashIterations; i++) {
			salt = encryptPassWord(salt);

		}

		return salt;
	}

	// 加密用户密码
	public static String getPassWord(String userName, String userPwd) {
		return getPassWord(userPwd + userName + 666666 + userPwd, 555);
	}


	//匹配明文
	public static boolean matchPassword(String plain,String entrypted){

		return encryptPassWord(plain).equals(entrypted);

	}


	public static void main(String[] args) {

		//5b6f250d9a79f738e3028e0cf873a652
		String uname ="admin";
		String pwd="123456";
		/*String uname ="admin";
		String pwd="SKQAdministratorTripod1316";*/
		String salt = pwd+uname+666666+pwd;
		int hashIterations = 555;
		//System.out.println(Encryption.getPassWord("skq"+123456+"skq", 364));
		System.out.println(getPassWord(salt,hashIterations));

	}



}
