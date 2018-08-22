package com.megagao.production.ssm.common.encrypt;

import java.security.MessageDigest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MD5Support {
	private static final Log LOG = LogFactory.getLog(MD5Support.class);

	private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static final String MD5(String s) {
		try {
			byte[] strTemp = s.getBytes("UTF-8");

			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];

				str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];

				str[(k++)] = hexDigits[(byte0 & 0xF)];
			}
			return new String(str);
		} catch (Exception e) {
			LOG.warn(e.getMessage());
		}
		return null;
	}

	public static final String MD5(String s, String encoding) {
		try {
			byte[] strTemp;
			if (encoding != null)
				strTemp = s.getBytes(encoding);
			else {
				strTemp = s.getBytes();
			}

			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];

				str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];

				str[(k++)] = hexDigits[(byte0 & 0xF)];
			}
			return new String(str);
		} catch (Exception e) {
			LOG.warn(e.getMessage());
		}
		return null;
	}
}