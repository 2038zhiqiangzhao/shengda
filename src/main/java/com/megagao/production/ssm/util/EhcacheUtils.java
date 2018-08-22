package com.megagao.production.ssm.util; 

import net.sf.jsqlparser.expression.StringValue;

import org.apache.shiro.cache.Cache;


/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月3日 下午3:20:32 
 * 类说明 
 * 缓存的具体业务逻辑
 */
public class EhcacheUtils {
	private static Integer num=0;
	// 校验次数是否超过10次
	public static boolean checkTime(Cache<String, Integer> cache,String mobile ) {
		//获取key
		String key = getKey(mobile);
		//获取value
		Integer authCodeNum = cache.get(key);
		if (authCodeNum == null) {
			cache.put(getKey(mobile),num);
			return true;
		}
		if(10 <=authCodeNum){
		    return false;
		 }
		
		return true;
	}

	   // 拼接key
		public static  String getKey(String mobile) {
			return "auth_code_" + mobile;
		}
		
		
		
		//缓存中put+1
		public static void putAuthCodeNum(Cache<String, Integer> cache,String mobile ){
			//往缓存中加添key
			cache.put(getKey(mobile), cache.get(getKey(mobile))+1);
		}

		// 根据手机号码获取次数
		public static int getTimes(String mobile,Cache<String, Integer> cache) {
			String key = getKey(mobile);
			Integer authCodeNum = cache.get(key);
			if (authCodeNum == null) {
				return 0;
			}
			return  authCodeNum;
		}

}
