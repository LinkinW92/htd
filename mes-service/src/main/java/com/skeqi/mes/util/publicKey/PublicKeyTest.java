package com.skeqi.mes.util.publicKey;

import java.security.PublicKey;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.util.yp.SHA256;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author yinp
 * @explain 公钥测试
 * @date 2020-10-29
 */
public class PublicKeyTest {

	// 公钥
	private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtXFfU/9g5d30ecK8fs/nAkHAzZJ0iNefn7eq3sooGMS/Dgas6RFu/UHOSoB+qoUEfzKrIUVZVqOt0sd+z/XUy9Ipkd2x69u4rLdqE//GNRfCCWUf1izqavVq+l/tvRepkhTpOdtrUqdjzM9eIa42qYkbiasretMTEZJV05sEp1lABXvoQAFZ89bqBqQDdTh2dI/LO4EKcVFsLy5mxV89p/fbqfsK9oZEMU1VSHbXEcbUufKXOvgIeCo9hshOItBi8XyR4DMeEnLesgob0omI4P+CBv/tthHDL793+GSrBx8fOfdUadn/jN9MLU/oGK20nMswS24fq6faim/UmBY/hwIDAQAB";

	// 明文
	private static String plaintext = "1q2w3e4r5t6y7u8i9o0p";

	// 私钥加密的密文
	private static String ciphertext = "cUNQMW52TTlHSk1SRS9TMzRIUDBpOVFFV0dBN2dPSWo0T3hyU2VnVmlWT0hqK2RkcTJNR25wQkZQ\r\n" +
			"ZkZzcXcvRjRQeGxPbzd2NWs1V3ZaWWMyVVhpVis1TmNydmNLdVRTS2JxRjBQaEFRdnZBN010Qmlk\r\n" +
			"aDZpeHVjbS80TVhtN2tUM3l1djlhSnZnQVI3ZlRiYXYyeXBtMXdqbk5rNUg1ODN3cTI4RlNySU5W\r\n" +
			"bVdqZ3ZwVk5GMVdCWC9Cb1NWcWl6Z2hVaDJvODVxeWZyM0dlSnRUMEZsZHNoNFMrZHRWeWVvUmk5\r\n" +
			"bDh0amx3em0xQy9QcE9yM1N2bFk1Yk1pKzBuWWVGamh2Y1o5V0xNSWswcldkYjZWZW5XN3JCcUZn\r\n" +
			"Mmc4UG1zcjJUYmNQSzViNGh4Rk5VSkpwb2ZETGdFYURScDlvRklLMlVlUENKMjE2Z3g0M0tNNjRB\r\n" +
			"PT0geyJhcHBseUFwcGxpY2FudCI6IuW8oOS4iSIsImFwcGx5Q29ycG9yYXRlTmFtZSI6IuaAneWu\r\n" +
			"oueQpiIsImFwcGx5TWFpbGJveCI6Iml0eWlucEBnbWFpbC5jb20iLCJhcHBseVRlbGVwaG9uZSI6\r\n" +
			"IjE3Nzc0NjA1NzAzIiwiYXBwbHlUaW1lIjoiMjAyMC0xMC0yOSIsImFwcGx5VmVyc2lvbiI6IjYu\r\n" +
			"MCIsImF1dGhvcml6ZUNvcnBvcmF0ZU5hbWUiOiLmgJ3lrqLnkKYiLCJhdXRob3JpemVEZWFkbGlu\r\n" +
			"ZSI6IjIwMjAtMTEtMjkiLCJhdXRob3JpemVNYXhpbXVtTnVtYmVyT2ZVc2VycyI6MTAsImN1cnJl\r\n" +
			"bnRTeXN0ZW1WZXJzaW9uIjoiNi4wIiwibWFjIjoiQjQtMkUtOTktRTktN0UtNDMiLCJwZXJtYW5l\r\n" +
			"bnRBdXRob3JpemF0aW9uIjpmYWxzZX0=";

	// 解码后的公钥
	private static PublicKey publicKey;

	// 公钥加密
	private static String jiami(String plaintext) {
		try {
			// 解码公钥
			publicKey = PublicKeyDecode.getPublicKey(PUBLIC_KEY);
			String result = PublicKeyDemo.encrypt(plaintext, publicKey);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 公钥解密
	private static String jiemi(String ciphertext) {
		try {
			// 解码公钥
			publicKey = PublicKeyDecode.getPublicKey(PUBLIC_KEY);
			String result = PublicKeyDemo.decrypt(ciphertext, publicKey);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 申请授权
	public static String apply() {
		JSONObject json = new JSONObject();
		json.put("applyVersion", "6.0");
		json.put("mac", "B4-2E-99-E9-7E-43");
		json.put("applyTime", "2020-10-29");
		json.put("currentSystemVersion", "6.0");
		json.put("applyCorporateName", "思客琦");
		json.put("applyApplicant", "张三");
		json.put("applyTelephone", "17774605703");
		json.put("applyMailbox", "ityinp@gmail.com");

		// json转sha256
		String sha256 = SHA256.getSHA256(json.toString());

		// 用json转的sha256 非对称加密
		String result = jiami(sha256);

		// 对称加密
		BASE64Encoder encoder = new BASE64Encoder();
		result = encoder.encode((result + " " + json).getBytes());
		System.out.println("发起授权申请：");
		System.out.println(result);
		return result;
	}

	// 签名
	public static boolean autograph() throws Exception {
		// 对称解密
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = decoder.decodeBuffer(ciphertext);

		// 获取到sha256 + 明文
		String result = new String(bytes,"utf-8");
		// sha256
		String sha256 = result.split(" ")[0];
		// 明文
		JSONObject json = JSONObject.parseObject(result.split(" ")[1]);

		// json转sha256
		String sha256_1 = SHA256.getSHA256(json.toString());

		// 非对称 解密 sha256
		sha256 = jiemi(sha256);

		// 对比两个sha256 如果不相等说明 明文被修改
		if (!sha256.equals(sha256_1)) {
			System.out.println("签名未通过");
			return false;
		}
		System.out.println("签名通过");
		System.out.println(json);
		return true;
	}

	public static void main(String[] args) throws Exception {
		//申请授权
//		apply();
		//签名
		autograph();
	}

}
