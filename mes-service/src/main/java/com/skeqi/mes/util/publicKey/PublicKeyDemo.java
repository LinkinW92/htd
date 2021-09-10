package com.skeqi.mes.util.publicKey;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;

/**
 * @author yinp
 * @explain 公钥
 * @date 2020-10-29
 */
public class PublicKeyDemo {

	// 公钥加密
	public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
		Cipher encryptCipher = Cipher.getInstance("RSA");
		encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

		byte[] cipherText = encryptCipher.doFinal(plainText.getBytes());

		return Base64.getEncoder().encodeToString(cipherText);
	}

	// 公钥解密
	public static String decrypt(String cipherText, PublicKey publicKey) throws Exception {
		byte[] bytes = Base64.getDecoder().decode(cipherText);

		Cipher decriptCipher = Cipher.getInstance("RSA");
		decriptCipher.init(Cipher.DECRYPT_MODE, publicKey);

		return new String(decriptCipher.doFinal(bytes));
	}

}
