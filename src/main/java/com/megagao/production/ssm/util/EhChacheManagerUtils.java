package com.megagao.production.ssm.util;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;

import com.megagao.production.ssm.domain.vo.CUser;


/**
 * @author 作者 E-mail:
 * @version 创建时间：2018年8月6日 下午1:45:47 类说明 ;保存用户信息，放入shiro-ehchae缓存中
 * shiro-ehchache缓存工具类：包含了token的引入和其他的信息
 */
public class EhChacheManagerUtils {

	private static EhCacheManager shiroCacheManager;
	private static Cache<Object, Object> myToken ;
	static {
		 shiroCacheManager = SpringUtils
				.getBean("shiroCacheManager");
		myToken = shiroCacheManager.getCache("myToken");

	}


	/**
	 * 生成一个令牌
	 * 
	 * @param long1
	 *            用户ID
	 * @return 返回令牌
	 */
	public static String createToken(Long userId, CUser cUser) {
		// 生成token
		UUID uuid = UUID.randomUUID();
		String token = userId + "_" + uuid.toString().replaceAll("-", "");
		// 将token存入ehchache
		String key = token;
		myToken.put(key, cUser);
		return token;
	}

	/**
	 * 获取token用户信息
	 * 
	 * @param token
	 * @return
	 */
	public static Object getToken(String token) {
		Object object = myToken.get(token);
		return object;
	}

	/**
	 * 检查token是否正确
	 * 
	 * @param token
	 *            令牌
	 * @return true正确;false失败
	 */
	public static boolean checkToken(String token) {
		// 解析出userId和uuid
		if (token == null || "".equals(token)) {
			return false;
		}
		String[] arr1 = token.split("_");
		if (arr1.length != 2) {
			return false;
		}
		// 根据ehchache进行检查
		Object r_token = myToken.get(token);
		if (r_token == null) {
			return false;
		}
		return true;
	}

	/**
	 * 注销Token
	 * 
	 * @param token
	 *            令牌
	 * @return true正确;false失败
	 */
	public static boolean clearToken(String token) {
		// 解析出userId和uuid
		if (token == null || "".equals(token)) {
			return false;
		}
		String[] arr1 = token.split("_");
		if (arr1.length != 2) {
			return false;
		}
		
		String r_token = (String) myToken.get(token);
		if (r_token == null) {
			return false;
		}
		// 注销token
		myToken.remove(token);
		return true;
	}

	/**
	 * 添加key value
	 * */
	public static void put(Object key, Object value) {
		myToken.put(key, value);
	}

	/**
	 * 清除指定的key
	 * */
	public static void remove(Object key) {
		myToken.remove(key);
	}

	/**
	 * 获取缓存大小
	 * */
	public int size() {
		return myToken.size();
	}

	/**
	 * 获取所有值的集合
	 * */
	public Collection<Object> values() {
		return myToken.values();
	}

	/**
	 * 清除所有缓存
	 * */
	public void clearAll() {
		myToken.clear();
	}

	/**
	 * 获取所有Session
	 * 
	 * @throws Exception
	 */
	public Collection<Session> AllSession() {
		Set<Session> sessions = new HashSet<Session>();
		try {

			Collection<Object> values = myToken.values();
			for (Object v : values) {
				if (StringUtils.isNotBlank((CharSequence) v)
						&& v instanceof Session) {
					sessions.add((Session) v);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return sessions;
	}

	
}
