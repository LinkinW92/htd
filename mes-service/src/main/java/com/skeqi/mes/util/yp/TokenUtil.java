package com.skeqi.mes.util.yp;

import java.util.Random;

public class TokenUtil {

	/**
	 * 随机位数生产Token
	 * @param length
	 * @return
	 */
	public static String randomToken(Integer length) {
		Random random = new Random();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(2);
			long result = 0;

			switch (number) {
			case 0:
				/* A-Z 的 ASCII 码值[65,90] */
				result = Math.round(Math.random() * 25 + 65);
				sb.append(String.valueOf((char) result));
				break;
			case 1:
				/* a-z 的 ASCII 码值[97,122] */
				result = Math.round(Math.random() * 25 + 97);
				sb.append(String.valueOf((char) result));
				break;
			}
		}

		return sb.toString();
	}

}
