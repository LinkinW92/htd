package com.skeqi.mes.util.yp;

import java.util.regex.Pattern;


public class CheckUtil {

	public static void phone(String phone) throws Exception {
		System.out.println(phone);
		String[] phones = phone.split(",");
		for (String string : phones) {
			if (string.length() != 11) {
				throw new Exception(string + "手机号应为11位数");
			} else {
				Pattern pattern = Pattern.compile("^[1]\\d{10}$");
				if (!pattern.matcher(string).matches()) {
					throw new Exception(string + "手机号是错误格式");
				}
			}
		}
	}

	public static void email(String email) throws Exception {
		String[] emails = email.split(",");
		for (String string : emails) {

			Pattern pattern = Pattern
					.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
			if (!pattern.matcher(string).matches()) {
				throw new Exception(string + "邮箱号是错误格式");
			}

		}
	}

	public static void ip(String ip) throws Exception {
		String ipReg = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
		Pattern ipPattern = Pattern.compile(ipReg);

		boolean flag = ipPattern.matcher(ip).matches();
		if(!flag) {
			throw new Exception("ip地址格式不正确");
		}
	}

	public static void main(String[] args) {
		try {
			ip("1.1.1.1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
