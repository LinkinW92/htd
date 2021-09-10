package com.skeqi.mes.util.yp;

import java.util.regex.Pattern;

/**
 * @author yinp
 * @explain 规则校验
 * @date 2020-11-11
 */
public class RuleChecking {

	/**
	 * @explain 校验手机号
	 * @param phone 手机号
	 * @param parameterNotes 参数注释
	 * @return
	 * @throws Exception
	 */
	public static void checkPhone(String phone, String parameterNotes) throws Exception {
		Pattern pattern = Pattern.compile("^[1]\\d{10}$");
		if(!pattern.matcher(phone).matches()) {
			throw new Exception(parameterNotes + "格式不正确");
		}

	}

	/**
	 * @explain 校验邮箱
	 * @param email 邮箱
	 * @param parameterNotes 参数注释
	 * @return
	 * @throws Exception
	 */
	public static void checkEmail(String email, String parameterNotes) throws Exception {
		if (email.contains("@") && email.contains(".")) {
			if (email.lastIndexOf(".") > email.lastIndexOf("@")) {
				return ;
			}
		}
		throw new Exception(parameterNotes + "格式不正确");
	}

}
