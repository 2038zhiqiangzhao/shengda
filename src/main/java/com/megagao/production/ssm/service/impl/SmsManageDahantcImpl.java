//package com.megagao.production.ssm.service.impl; 
//
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import com.megagao.production.ssm.service.SmsService;
//import com.megagao.production.ssm.sms.dahantc.SmsSDK;
//
///** 
// * @author 作者 E-mail: 
// * @version 创建时间：2018年8月7日 下午2:02:04 
// * 类说明 
// */
//@Service("smsManageDahantc")
//public class SmsManageDahantcImpl implements SmsService {
//
//	public static final String TEMPLATE_VERIFYCODE = "";// 短信验证码模板
//
//	Logger logger = LoggerFactory.getLogger(SmsManageDahantcImpl.class);
////	@Value("${sms.uri}")
//	private String uri;
////	@Value("${sms.accountId}")
//	private String accountId;
////	@Value("${sms.authToken}")
//	private String authToken;
////	@Value("${sms.appId}")
//	private String appId;
////	@Value("${sms.template.verifycode}")
//	public String templateVerifycode;
//
//	/**
//	 * 短信发送
//	 */
//	@Override
//	public int sendSmsText(String phone, String text) {
//		return 0;
//	}
//
//	@Override
//	public int sendSmsTemplateText(String phone, String tempalte, String[] args) {
//		// 初始化SDK
//		SmsSDK smsSDK = new SmsSDK();
//
//		/*
//		 * 帐户参数配置 用户登录之后在开发者控制台【首页】寻找以下配置参数： AccountSid: 帐号ID，对应开发者帐号下的 ACCOUNT
//		 * SID AuthToken: 授权令牌，对应开发者帐号下的 AUTH TOKEN ApiUrl:
//		 * API调用路径，对应着开发者后台中的API URL AppId: 应用ID，对应开发者控制台【应用管理】S中的某个APP ID
//		 */
//		smsSDK.setAccountSid(accountId);
//		smsSDK.setAuthToken(authToken);
//		smsSDK.setApiUrl(uri);
//		smsSDK.setAppId(appId);
//
//		/*
//		 * 发送模板短信 to 手机号码，多个手机号码需用英文逗号分开 tempId 模板ID，对应开发者后台中的模板编号 datas
//		 * 替换内容，格式为数组，例如：new String[]{"1238", "3"}
//		 */
//		Map result = smsSDK.sendSMS(phone, tempalte, args); // 实际调用请保证这些参数真实有效！
//		System.out.println(result);
//
//		if ("000000".equals(result.get("statusCode"))) {
//			// 发送成功
//			Map templateSMS = (Map) result.get("templateSMS");
//			System.out
//					.println("dateCreated: " + templateSMS.get("dateCreated"));
//			System.out.println("smsMessageSid: "
//					+ templateSMS.get("smsMessageSid"));
//
//			return 0;
//
//		} else {
//			// 发送失败
//			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= "
//					+ result.get("statusMsg"));
//
//			// TODO 错误处理
//			return -1;
//		}
//
//	}
//
//	/**
//	 * 
//	 * <p>
//	 * 发送验证码
//	 * </p>
//	 * 
//	 * @author dusai
//	 * @date 2017年8月21日 下午1:45:05
//	 * @param phone
//	 * @param verifyCode
//	 * @see com.people2000.mzadmin.business.write.SmsManage#sendSmsVerifyCode(java.lang.String,
//	 *      java.lang.String)
//	 */
//	@Override
//	public int sendSmsVerifyCode(String phone, String verifyCode) {
//		return sendSmsTemplateText(phone, templateVerifycode,
//				new String[] { verifyCode });
//	}
//}