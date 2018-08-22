package com.megagao.production.ssm.sms.dahantc;

import java.util.Random;

/**
 * @author 作者 :魏伟
 * @version 创建时间：2017年6月26日 下午1:43:30
 */
public class Utils {

	// 随机生成一个N位数注册码
	public static String productRandom(int n) {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < n; i++) {
			result += random.nextInt(10);
		}
		return result;
	}

}
