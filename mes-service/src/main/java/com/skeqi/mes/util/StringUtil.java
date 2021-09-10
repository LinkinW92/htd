package com.skeqi.mes.util;

public class StringUtil {

	public static boolean eqNu(Object object) {
		if(object==null){
			return false;
		}
		if(object==""){
			return false;
		}
		if("".equals(object.toString())){
			return false;
		}
		return true;
	}

	/**
	 *
	 * @param a 原字符串
	 * @param b 长度
	 * @return
	 */
	public static String lpad(String a,Integer b){
		for (int i = a.length(); i < b; i++) {
			a = " "+a;
		}
		return a;
	}


	public static void main(String[] args) {
		String a = "02LPC00701021A91######";
		System.out.println(a.substring(0,a.indexOf("#")));
	}

	public static Integer instr(String a,String b) {

		Integer c = a.length()-a.lastIndexOf(b,1)-1;

		return c;
	}

	public static String substr(String a,Integer b) {

		String c = a.substring(b);

		return c;
	}

}
