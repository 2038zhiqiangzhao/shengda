package com.megagao.production.ssm.util; 
import java.util.HashMap;
import java.util.Map;
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月6日 上午10:15:30 
 * 类说明 
 */
public class ResponseUtils {

	public static <T, X extends T, Y extends T,Z extends T> Map<String, T> map(X code,
			Y message,Z data) {
		Map<String, T> result = new HashMap<String, T>();
		result.put("code", code);
		result.put("message", message);
		result.put("data", data);
		return result;
	}


}