package com.megagao.production.ssm.service; 
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月7日 下午1:53:49 
 * 类说明 
 * 短信接口
 */
public interface SmsService {
	/**
	 * 给手机发送的短信
	 * 
	 * @param phone
	 *            手机号
	 * @param text
	 *            短信内容
	 * @return
	 */
	public int sendSmsText(String phone, String text);

	/**
	 * 
	 * <p>
	 * 发送短信模板内容
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月21日 下午1:48:17
	 * @param phone
	 * @param tempalte
	 * @param args
	 * @see
	 */
	public int sendSmsTemplateText(String phone, String tempalte, String[] args);

	/**
	 * 
	 * <p>
	 * 发送短信验证码
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月21日 下午1:40:04
	 * @param phone
	 * @param verifycode
	 * @return
	 * @see
	 */
	public int sendSmsVerifyCode(String phone, String verifycode);
}
