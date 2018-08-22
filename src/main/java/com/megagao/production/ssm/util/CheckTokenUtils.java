package com.megagao.production.ssm.util; 
import org.apache.commons.lang3.StringUtils;
import com.megagao.production.ssm.domain.dto.BaseToken;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月8日 下午2:02:33 
 * 类说明 
 * 检测api调用是否传入了token信息，如果没有传入那么返回提示，让用户重新登录或者传入正确的token信息
 */
public class CheckTokenUtils {
	
	/**判断客户端是否传入了token
	 * @param token
	 * @return
	 */
	public static boolean IsHavaToken(BaseToken token){
		if(StringUtils.isNotBlank(token.getToken())){
			return true;
		}
		return false;
	}
	/**判断客户端传过来的token是否匹配echache中的数据，如果不匹配那么token失效或者传入的不匹配/已经被篡改
	 * @param token
	 * @return
	 */
	public static Object  EHChacheIsMatching(BaseToken token){
		boolean checkToken = EhChacheManagerUtils.checkToken(token.getToken());
		if(!checkToken){
		return ResponseUtils.map(-1, "token失效或者已经被篡改", null);
		}
		return null;
	}
}
