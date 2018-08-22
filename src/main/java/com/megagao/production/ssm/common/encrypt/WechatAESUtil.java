package com.megagao.production.ssm.common.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class WechatAESUtil {
	/**
	 * 密钥算法
	 */
	private static final String ALGORITHM = "AES";
	/**
	 * 加解密算法/工作模式/填充方式
	 */
	private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS5Padding";

	/**
	 * AES加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptData(String data, String key) throws Exception {
		SecretKeySpec keySpac = new SecretKeySpec(MD5Support.MD5(key)
				.toLowerCase().getBytes(), ALGORITHM);

		// 创建密码器
		Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
		// 初始化
		cipher.init(Cipher.ENCRYPT_MODE, keySpac);
		return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
	}

	/**
	 * AES解密
	 * 
	 * @param base64Data
	 * @return
	 * @throws Exception
	 */
	public static String decryptData(String base64Data, String key)
			throws Exception {
		SecretKeySpec keySpac = new SecretKeySpec(MD5Support.MD5(key)
				.toLowerCase().getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);
		cipher.init(Cipher.DECRYPT_MODE, keySpac);
		return new String(cipher.doFinal(Base64.decodeBase64(base64Data)));
	}
}
