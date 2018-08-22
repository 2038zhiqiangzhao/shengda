package com.megagao.production.ssm.service.impl; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.megagao.production.ssm.exception.BusinessException;
import com.megagao.production.ssm.service.SmsService;
import com.megagao.production.ssm.util.HttpClientUtils;


/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月7日 下午1:54:54 
 * 类说明 
 */
@Component("smsManage")
public class SmsServiceImpl implements SmsService {
	Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
//	@Value("${sms.apikey}")
	private String apikey;
//	@Value("${sms.uri}")
	private String uri;

//	@Value("${sms.template.verifycode}")
	public String templateVerifycode;

	/**
	 * 短信发送
	 */
	@Override
	public int sendSmsText(String phone, String text) {
		try {
			String param = "?MobileTel=" + phone + "&SmsContent=" + text;
			String url = uri + param;

			String info = HttpClientUtils.get(url, "UTF-8");

			String resultArr = info.split("&")[0];
			String resultCode = resultArr.split("=")[1];
			if (!"0".equals(resultCode)) {
				throw new BusinessException("短信发送失败", info);
			} else {
				logger.debug("短信发送成功, 短信发送人:" + phone);
			}
		} catch (Exception e) {
			logger.error("短信发送失败, 短信发送人:" + phone, e);
			return -1;
		}
		return 0;
	}

	@Override
	public int sendSmsTemplateText(String phone, String tempalte, String[] args) {
		// String text = String.format(tempalte, args);
		return sendSmsText(phone, args[0]);
	}

	/**
	 * 
	 * <p>
	 * 发送验证码
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年8月21日 下午1:45:05
	 * @param phone
	 * @param verifyCode
	 * @see com.people2000.mzadmin.business.write.SmsManage#sendSmsVerifyCode(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public int sendSmsVerifyCode(String phone, String verifyCode) {
		return sendSmsTemplateText(phone, templateVerifycode,
				new String[] { verifyCode });
	}

	public static void main(String[] args) throws Exception {
		String url = "http://118.178.5.122/smsmz.php"
				+ "?MobileTel=15800805975&SmsContent=asdasdasd";

		String info = HttpClientUtils.get(url, "UTF-8");

		System.out.println("info" + info);

		String resultArr = info.split("&")[0];
		String resultCode = resultArr.split("=")[1];
		if (!"0".equals(resultCode)) {
			throw new BusinessException("短信发送失败", info);
		} else {
			System.out.println("短信发送成功, 短信发送人:");
		}
	}
}
