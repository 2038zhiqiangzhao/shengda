package com.megagao.production.ssm.sms.dahantc;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Properties;

/**
 * 
 * @author 於利艳
 * @Create 2017年6月30日
 */
public class ReadProperties {
	private static Properties prop;
	private static volatile ReadProperties instance;

	private ReadProperties() {
	}

	public static ReadProperties getIstance() {
		if (instance == null) {
			synchronized (ReadProperties.class) {
				if (instance == null) {
					instance = new ReadProperties();
					prop = new Properties();
				}
			}
		}
		return instance;
	}

	/**
	 * 读取error文件信息，并根据错误码返回错误信息
	 * 
	 * @param key
	 * @return
	 */
	public String readProperties(String key) {
		Iterator<String> it = Properties("error.properties");
		try {
			while (it.hasNext()) {
				if (key.equals(it.next()))
					return new String(prop.getProperty(key).getBytes(
							"ISO-8859-1"), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String readSMSProperties(String key) {
		Iterator<String> it = Properties("sms.properties");
		try {
			while (it.hasNext()) {
				if (key.equals(it.next()))
					return new String(prop.getProperty(key).getBytes(
							"ISO-8859-1"), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 读取error文件信息，并根据错误码返回错误信息
	 * 
	 * @param key
	 * @return
	 */
	public String readErroCode(String key) {
		Iterator<String> it = Properties("error2.properties");
		try {
			while (it.hasNext()) {
				if (key.equals(it.next()))
					return new String(prop.getProperty(key).getBytes(
							"ISO-8859-1"), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public Iterator<String> Properties(String url) {
		InputStream in = null;
		try {
			in = new BufferedInputStream(this.getClass().getResourceAsStream(
					"/" + url));
			prop.load(in);
			return prop.stringPropertyNames().iterator();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
}
