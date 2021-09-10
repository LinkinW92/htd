package com.skeqi.mes.common.lcy;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;


public class OracleEncrytion {


	private static MessageDigest digest = null;

	/*public static String getOracleEncrytion(String plain){
		if(plain==null||plain==""){
			return null;
		}
		byte[] bytes = plain.getBytes();
		StringBuffer sb = new StringBuffer();



			for (int i = 0; i < bytes.length; i++) {
				sb.append(bytes[i]+1+"u");


			}

		return sb.toString();
	}

	public static String getOraclePassword(String plain){
		if(plain == null||plain==""){
			return null;
		}
		String[] bytes=plain.split("u");
		StringBuffer sb = new StringBuffer();
		for (String string : bytes) {
			byte a =Byte.parseByte(string);
			char b =(char)(byte) (a-1);
			sb.append(b);
		}
		return sb.toString();
	}*/

	/*public static String encryptPassword(String plain) {
		if (plain == null)
			return null;
		byte[] bytes = digest.digest(plain.getBytes());
		return Base64.getEncoder().encodeToString(bytes);
	}

	*//**
	 * 閸栧綊鍘ょ�靛棛鐖滈弰搴㈡瀮閸滃苯鐦戦弬锟�
	 *
	 * @param plain
	 * @param encrypted
	 * @return
	 *//*
	public static boolean matchPassword(String plain, String encrypted) {
		return encryptPassword(plain).equals(encrypted);
	}*/


	public static String getOracleEncrytion(String plain){
		if(plain==null||plain==""){
			return null;
		}

		byte[] bytes =plain.getBytes();
		StringBuffer sb = new StringBuffer();
		sb.append(plain.length()+"o");

		for (int i = 0; i < bytes.length; i++) {
			byte a = (byte) (bytes[i]);
			Integer b= (int)(a);
			if(b>=0){
				Integer num = (int)b;
				String st =num.toHexString(num*33-34);
				sb.append(st+"y");
				//sb.append((+"b"));
			}else {
				Integer num = (int)b;
				String st =num.toHexString(num*33-34);
				sb.append(st+"t");
				//sb.append(Integer.parseInt((Math.abs(b)+""), 16)+"c");
			}
		}


		return sb.toString();
	}

	public static String getOraclePassword(String plain){
		if(plain == null||plain==""){
			return null;
		}
		String[] strs=plain.split("o");
		if(strs.length!=2){
			return null;
		}
		Integer length = Integer.parseInt(strs[0]);//閼惧嘲褰囬梹鍨
		List<String> getSomeList = new ArrayList<>();

		String[] strs2 = strs[1].split("y");
		StringBuffer sb = new StringBuffer();
		for (String string : strs2) {
			String str = string+"P";
			String[] strs3=str.split("t");

			for (int i = 0; i < strs3.length; i++) {
				if("P".equals(strs3[i])){

				}else{
					getSomeList.add(strs3[i]);

				}
			}
			/*for (String string2 : strs3) {
				if(string2.endsWith("P")){
					string2 =string2.substring(0, string2.length()-1);
					if(string2!=null||string2!=""){
					Byte byt=Byte.parseByte(string2);
					byt=(byte)(-byt+length*length);
					byt=(byte)(byt/3);
					sb.append(byt);
					}
				}else{
					Byte byt = Byte.parseByte(string2);
					byt =(byte)(byt+length*length);
					byt=(byte)(byt/3);
					sb.append(byt);
				}

			}*/

		}

		for (String s : getSomeList) {
			if(s.endsWith("P")){
				s = s.substring(0,s.length()-1);
				Integer number = Integer.parseInt(s,16);
				int a = (number+34)/33;
				byte b = (byte)a;
				char c = (char) b;
				sb.append(c);
				/*Byte byt=Byte.parseByte(s);
				byt=(byte)(byt/3);
				sb.append(byt);*/
			}else{
				Integer number =Integer.parseInt(s,16);
				int a = (number+34)/33;
				byte b = (byte)a;
				char c = (char) b;
				sb.append(c);
				//System.out.println(s);



			/*	Byte byt = Byte.parseByte(s);

				byt =(byte)(byt+length*length);
				System.out.println(byt);
				byt=(byte)(byt/3);
				sb.append(byt);*/
			}
		}

		return sb.toString();
	}



	public static void main(String[] args) {

		System.out.println("root:"+OracleEncrytion.getOracleEncrytion("root"));
		System.out.println("qihang:"+OracleEncrytion.getOracleEncrytion("qihang"));
		System.out.println("andon:"+OracleEncrytion.getOracleEncrytion("andon"));
		System.out.println("mes:"+OracleEncrytion.getOracleEncrytion("mes"));
		System.out.println("123456:"+OracleEncrytion.getOracleEncrytion("123456"));




	}//





}
