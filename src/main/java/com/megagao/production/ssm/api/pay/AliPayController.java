package com.megagao.production.ssm.api.pay;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.gexin.fastjson.JSON;
import com.megagao.production.ssm.util.HttpClientUtils;
import com.megagao.production.ssm.util.ResponseUtils;
import com.megagao.production.ssm.common.ResultCode;
import com.megagao.production.ssm.domain.vo.PayVo;
import com.megagao.production.ssm.domain.vo.RefundVo;

/**
 * 
 * 支付宝支付控制器
 * 
 * @date 2017-12-15
 * @since
 * @version
 */
@Controller
@RequestMapping("/alipay")
public class AliPayController  {

	private Logger logger = LoggerFactory.getLogger(AliPayController.class);

	@Value("${alipay.appId}")
	private String appId;// 应用id
	@Value("${alipay.privateKey}")
	private String privateKey;// 私钥
	@Value("${alipay.publicKey}")
	private String publicKey; // 公钥
	@Value("${alipay.notifyUrl}")
	private String notifyUrl;// 支付通知回调地址
	public static String SIGNTYPE = "RSA2";// RSA2
	public static String CHARSET = "utf-8";// 编码
	public static String TIMEOUT_EXPRESS = "60m"; // 有效期1h
	public static String PRODUCT_CODE = "QUICK_MSECURITY_PAY";
	public static String URL = "https://openapi.alipay.com/gateway.do";// 支付宝网关（固定）

	/**
	 * 
	 * 预支付单
	 * 
	 * @date 2017-12-15
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/aliPayForApp")
	public Object aliPayForApp(@RequestBody PayVo payVo) {
		try {
			// 订单编号为空或不存在
			if (StringUtils.isEmpty(payVo.getOutTradeNo()))
				
				return ResponseUtils.map(-1, null, ResultCode.ERR_ORDER_CODE);
			// 判断订单金额是否为空
			if (StringUtils.isEmpty(payVo.getTotalAmount()))
		
				return ResponseUtils.map(-1, null, ResultCode.ERR_TOTAL_AMOUNT);
			// 回调地址不能为空
			if (StringUtils.isEmpty(payVo.getCallbackUrl()))
				
				return ResponseUtils.map(-1, null, ResultCode.ERR_CALLBACK_URL);

			AlipayClient alipayClient = new DefaultAlipayClient(URL, appId,
					privateKey, "json", CHARSET, publicKey, SIGNTYPE);
			// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
			AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
			// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			// 订单名称，必填
			String subject = !StringUtils.isEmpty(payVo.getSubject()) ? payVo
					.getSubject() : "支付宝支付";
			// 商品描述，可空
			String body = !StringUtils.isEmpty(payVo.getBody()) ? payVo
					.getBody() : "支付宝支付";

			model.setBody(body);
			model.setSubject(subject);
			model.setOutTradeNo(payVo.getOutTradeNo());
			model.setTimeoutExpress(TIMEOUT_EXPRESS);
			model.setTotalAmount(payVo.getTotalAmount());
			model.setProductCode(PRODUCT_CODE);
			model.setPassbackParams(URLEncoder.encode(payVo.getCallbackUrl(),
					"utf-8"));
			request.setBizModel(model);
			request.setNotifyUrl(notifyUrl);

			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient
					.sdkExecute(request);
			if (!response.isSuccess()) {// 调用成功业务处理
				return ResponseUtils.map(-1, null, response.getMsg());
			}
			// 返回生成的预订单
			
			return ResponseUtils.map(0,null, response.getBody());
		} catch (Exception e) {
			logger.info(e.getMessage());
			return ResponseUtils.map(-1,null, ResultCode.ERR_SYS);
			
		}
	}

	/**
	 * @名称: aliPayForMobileWeb
	 * @描述: 支付宝支付手机Web:调用支付宝的支付接口：仅仅作为一个画面跳转
	 * @参数: @param aliPayForMobileWeb
	 * @参数: @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/aliPayForMobileWeb")
	public Object aliPayForMobileWeb(HttpServletRequest request,
			HttpServletResponse response, @RequestBody PayVo payVo) {
		try {
			// 订单编号为空或不存在
			if (StringUtils.isEmpty(payVo.getOutTradeNo()))
				return ResponseUtils.map(-1,null, ResultCode.ERR_ORDER_CODE); 
			// 判断订单金额是否为空
			if (StringUtils.isEmpty(payVo.getTotalAmount()))
				return ResponseUtils.map(-1,null, ResultCode.ERR_TOTAL_AMOUNT);

			// 回调地址不能为空
			if (StringUtils.isEmpty(payVo.getCallbackUrl()))
				return 	ResponseUtils.map(-1,null, ResultCode.ERR_CALLBACK_URL);
		
			// 订单名称，必填
			String subject = !StringUtils.isEmpty(payVo.getSubject()) ? payVo
					.getSubject() : "支付宝支付";
			// 商品描述，可空
			String body = !StringUtils.isEmpty(payVo.getBody()) ? payVo
					.getBody() : "支付宝支付";
			// 超时时间 可空
			String timeout_express = TIMEOUT_EXPRESS;
			// 销售产品码 必填
			String product_code = "QUICK_WAP_PAY";
			// 支付成功后的跳转地址:地址+参数(jsessionId+orderCode)
			String rerurnURL = payVo.getReturnUrl();

			AlipayClient alipayClient = new DefaultAlipayClient(URL, appId,
					privateKey, "json", CHARSET, publicKey, SIGNTYPE);

			AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();
			// 封装请求支付信息
			AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
			model.setOutTradeNo(payVo.getOutTradeNo());
			model.setSubject(subject);
			model.setTotalAmount(payVo.getTotalAmount());
			model.setBody(body);
			model.setTimeoutExpress(timeout_express);
			model.setProductCode(product_code);
			model.setPassbackParams(URLEncoder.encode(payVo.getCallbackUrl(),
					"utf-8"));
			alipay_request.setBizModel(model);
			alipay_request.setNotifyUrl(notifyUrl);
			// 设置同步地址:用户传递过来的参数
			alipay_request.setReturnUrl(rerurnURL);
			// 文本消息与图文消息参数
			alipay_request.setBizContent("{\"out_trade_no\":\""
					+ payVo.getOutTradeNo() + "\"," + "\"total_amount\":\""
					+ payVo.getTotalAmount() + "\"," + "\"subject\":\""
					+ subject + "\"," + "\"body\":\"" + body + "\","
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

			// form表单生产
			String form = "";

			// 调用SDK生成表单
			form = alipayClient.pageExecute(alipay_request).getBody();
			// 直接将完整的表单html输出到页面
			response.setContentType("text/html;charset=" + CHARSET);
			// 直接将完整的表单html输出到页面
			response.getWriter().write(form);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (AlipayApiException e) {
			logger.error("阿里支付接口异常!");
			e.printStackTrace();
			return 	ResponseUtils.map(-1,null, ResultCode.ERR_SYS);
		
		} catch (IOException e) {
			logger.error("输出流异常!");
			e.printStackTrace();
			return ResponseUtils.map(-1,null, ResultCode.ERR_SYS);
			
		} catch (Exception e) {
			e.printStackTrace();
			return 	ResponseUtils.map(-1,null, ResultCode.ERR_SYS);
		
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @名称: aliPayForPC
	 * @描述: 支付宝支付PC:调用支付宝的支付接口：仅仅作为一个画面跳转
	 * @参数: @param payVo
	 * @参数: @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/aliPayForPC")
	public Object aliPayForPC(HttpServletRequest request,
			HttpServletResponse response, @RequestBody PayVo payVo)
			throws IOException {
		try {
			// 订单编号为空或不存在
			if (StringUtils.isEmpty(payVo.getOutTradeNo()))
				return ResponseUtils.map(-1,null, ResultCode.ERR_ORDER_CODE);
	
			// 判断订单金额是否为空
			if (StringUtils.isEmpty(payVo.getTotalAmount()))
				return	ResponseUtils.map(-1,null, ResultCode.ERR_TOTAL_AMOUNT);
		
			// 回调地址不能为空
			if (StringUtils.isEmpty(payVo.getCallbackUrl()))
				return ResponseUtils.map(-1,null, ResultCode.ERR_CALLBACK_URL);
	
			// 订单名称，必填
			String subject = !StringUtils.isEmpty(payVo.getSubject()) ? payVo
					.getSubject() : "支付宝支付";
			// 商品描述，可空
			String body = !StringUtils.isEmpty(payVo.getBody()) ? payVo
					.getBody() : "支付宝支付";

			// 超时时间 可空
			String timeout_express = TIMEOUT_EXPRESS;
			// 销售产品码 必填
			String product_code = "FAST_INSTANT_TRADE_PAY";

			AlipayClient alipayClient = new DefaultAlipayClient(URL, appId,
					privateKey, "json", CHARSET, publicKey, SIGNTYPE);

			AlipayTradePagePayRequest alipay_request = new AlipayTradePagePayRequest();
			// 封装请求支付信息
			AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
			model.setOutTradeNo(payVo.getOutTradeNo());
			model.setSubject(subject);
			model.setTotalAmount(payVo.getTotalAmount());
			model.setBody(body);
			model.setTimeoutExpress(timeout_express);
			model.setProductCode(product_code);
			model.setPassbackParams(URLEncoder.encode(payVo.getCallbackUrl(),
					"utf-8"));
			alipay_request.setBizModel(model);
			alipay_request.setNotifyUrl(notifyUrl);

			// form表单生产
			String form = "";

			// 调用SDK生成表单
			form = alipayClient.pageExecute(alipay_request).getBody();
			response.setContentType("text/html;charset=" + CHARSET);
			// 直接将完整的表单html输出到页面
			response.getWriter().write(form);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (AlipayApiException e) {
			logger.error("阿里支付接口异常!");
			e.printStackTrace();
			return ResponseUtils.map(-1,null, ResultCode.ERR_SYS);
			
		} catch (IOException e) {
			logger.error("输出流异常!");
			e.printStackTrace();
			return ResponseUtils.map(-1,null, ResultCode.ERR_SYS);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.map(-1,null, ResultCode.ERR_SYS);
		}
		return null;
	}

	/**
	 * 
	 * 支付宝支付，异步通知回调方法
	 * 
	 * @date 2017-12-18
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/payNofityForAlipay")
	public void payNofityForAlipay(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> paramsUtf8 = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		String contentType = request.getHeader("Content-Type");
		logger.debug("Content-Type:" + contentType);
		if (requestParams.isEmpty()) {
			logger.error("支付宝异步通知被执行，但是无参数！");
			return;
		}
		try {
			for (Iterator iter = requestParams.keySet().iterator(); iter
					.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "utf-8");
				params.put(name, valueStr);
				paramsUtf8.put(name, new String(
						valueStr.getBytes("ISO-8859-1"), "utf-8"));
			}
			boolean signVerified = AlipaySignature.rsaCheckV1(params,
					publicKey, "utf-8", "RSA2");
			if (!signVerified) {// 验证失败再验证一次
				signVerified = AlipaySignature.rsaCheckV1(params, publicKey,
						"utf-8", "RSA2");
			}
			if (!signVerified) {// 验证失败
				return;
			}

			final String orderCode = paramsUtf8.get("out_trade_no");
			final String tradeStatus = paramsUtf8.get("trade_status");
			final String data = JSONObject.toJSONString(paramsUtf8);
			String callbackUrl = URLDecoder.decode(
					paramsUtf8.get("passback_params"), "utf-8");
			if (tradeStatus.equals("TRADE_SUCCESS")) { // 支付成功
				try {
					if (StringUtils.isEmpty(callbackUrl)) {
						logger.error("回调函数请求地址为空");
						return;
					}
					logger.debug("callbackUrl==" + callbackUrl);
					logger.debug("paramsUtf8==" + paramsUtf8.toString());
					// 支付成功操作,调用回调地址
					HttpClientUtils.postParameters(callbackUrl, paramsUtf8);
				} catch (Exception e) {
					logger.error("【丢单】支付宝支付成功通知后业务操作异常", e);
					throw e;
				}
				response.getWriter().print("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().print("fail");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			logger.error("支付宝支付异步通知发生异常", e);
		}
	}

	/**
	 * 支付宝退款接口
	 * 
	 * @param RefundVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/aliRefund")
	public Object aliRefund(@RequestBody RefundVo refundVo) {
		try {
			// 订单编号不能为空
			if (StringUtils.isEmpty(refundVo.getOutTradeNo()))
				return ResultCode.ERR_ORDER_CODE;
			// 订单退款金额不能为空
			if (StringUtils.isEmpty(refundVo.getRefundAmount()))
				return ResultCode.ERR_REFUND_AMOUNT;
			// 回调地址不能为空
			if (StringUtils.isEmpty(refundVo.getCallbackUrl()))
				return	ResponseUtils.map(-1,null, ResultCode.ERR_CALLBACK_URL);
		
			AlipayClient alipayClient = new DefaultAlipayClient(URL, appId,
					privateKey, "json", CHARSET, publicKey, SIGNTYPE);
			// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
			AlipayTradeRefundModel model = new AlipayTradeRefundModel();

			model.setOutTradeNo(refundVo.getOutTradeNo());
			model.setRefundReason(refundVo.getRefundReason());
			model.setRefundAmount(refundVo.getRefundAmount());
			request.setBizModel(model);

			AlipayTradeRefundResponse response = alipayClient.execute(request);
			if (!response.isSuccess()) {// 调用成功业务处理
				return 	ResponseUtils.map(-1,null, response.getMsg());
			}
			// 退款成功回调地址
			Map map = JSON.parseObject(response.getBody());
			HttpClientUtils.postParameters(refundVo.getCallbackUrl(), map);
			// 返回
			return 	ResponseUtils.map(-1,null, response.getBody());
		} catch (AlipayApiException e) {
			e.printStackTrace();
			logger.error("alipayClient.execute(request)AlipayApiException:调用支付宝接口失败...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 	ResponseUtils.map(-1,null, ResultCode.ERR_SYS);
	
	}
}
