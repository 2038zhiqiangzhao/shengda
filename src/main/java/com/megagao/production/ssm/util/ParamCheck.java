package com.megagao.production.ssm.util; 

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月6日 上午10:52:39 
 * 类说明 
 */
public class ParamCheck {
	// 对字符串进行净化
		public static String trimTemp(String temp) {
			return temp == null ? null : temp.replaceAll("\\s+", "");
		}

		// 校验手机号码
		public static boolean isMobileNO(String mobile) {
			Pattern p = Pattern
					.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobile);
			return m.matches();
		}

		// 校验邮箱
		public static boolean isEmail(String email) {
			String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
			Pattern p = Pattern.compile(str);
			Matcher m = p.matcher(email);
			return m.matches();
		}

		// 只能为字母和数字
		public static boolean isNumberOrLetter(String temp) {
			return temp.matches("[0-9A-Za-z]*");
		}

		// 只为数字
		public static boolean isNumber(String temp) {
			return temp.matches("[0-9]*");
		}

		// 非法字符
		public static boolean isIllegalLetter(String temp) {
			// return temp.matches("/^(?:[/u4e00-/u9fa5]*/w*/s*)+$/");
			return false;
		}
}
