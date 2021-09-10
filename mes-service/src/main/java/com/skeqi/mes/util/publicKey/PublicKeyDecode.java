package com.skeqi.mes.util.publicKey;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author yinp
 * @explain 解码PublicKey
 * @date 2020-10-29
 */
public class PublicKeyDecode {
	/**
	 * 解码PublicKey
	 *
	 * @param key
	 * @return
	 */
	public static PublicKey getPublicKey(String key) {
		try {
			byte[] byteKey = Base64.getDecoder().decode(key);
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePublic(x509EncodedKeySpec);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
