package com.megagao.production.ssm.util; 
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月7日 下午3:33:34 
 * 类说明 
 */

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
	static final ConcurrentMap<Object, Logger> factory = new ConcurrentHashMap();

	public static Logger getLogger(Object hostObjectOrClass) {
		return LoggerFactory.getLogger(getClass(hostObjectOrClass));
	}

	private static Class getClass(Object hostObjectOrClass) {
		if ((hostObjectOrClass instanceof Class)) {
			return (Class) hostObjectOrClass;
		}
		return hostObjectOrClass.getClass();
	}
}
