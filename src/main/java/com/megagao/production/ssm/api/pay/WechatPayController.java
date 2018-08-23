package com.megagao.production.ssm.api.pay;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.common.ResultCode;
import com.megagao.production.ssm.common.encrypt.WechatAESUtil;
import com.megagao.production.ssm.domain.vo.PayVo;
import com.megagao.production.ssm.domain.vo.RefundVo;
import com.megagao.production.ssm.util.HttpClientUtils;
import com.megagao.production.ssm.util.MapConvertor;
import com.megagao.production.ssm.util.PayCommonUtil;
import com.megagao.production.ssm.util.ResponseUtils;
import com.megagao.production.ssm.util.XMLUtil;
import com.thoughtworks.xstream.XStream;

/**
 * 
 * 微信支付
 * 
 * @date 2017-12-15
 * @since
 * @version
 */
@Controller
@RequestMapping("/wechatpay")
public class WechatPayController {

	@Value("${wechatpay.appid}")
	private String appid;// 应用ID
	@Value("${wechatpay.api_key}")
	private String api_key;// 微信配置的key
	@Value("${wechatpay.mch_id}")
	private String mch_id;// 商户号ID
	@Value("${wechatpay.notify_url}")
	private String notify_url;// 回调地址
	@Value("${wechatpay.certfile_path}")
	private String certfile_path;// pkcs12证书文件路径

	public static final String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 微信api下单接口
	public static final String ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";// 微信api订单查询接口
	public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";// 微信退款接口

	private Logger logger = LoggerFactory.getLogger(WechatPayController.class);

	/**
	 * 
	 * 预支付单
	 * 
	 * @date 2017-12-18
	 */
	@RequestMapping("/payForApp")
	@ResponseBody
	public Object payForApp(@RequestBody PayVo payVo) {
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
			// 初始化参数
			SortedMap<String, String> params = new TreeMap<String, String>();
			params.put("appid", appid);
			params.put("mch_id", mch_id);
			params.put("nonce_str",
					UUID.randomUUID().toString().replace("-", ""));
			params.put("spbill_create_ip", "127.0.0.1");// 客户IP
			params.put("out_trade_no", payVo.getOutTradeNo());// 订单号
			BigDecimal centAmount = new BigDecimal(payVo.getTotalAmount())
					.multiply(new BigDecimal(100));
			params.put("total_fee", centAmount.intValue() + "");// 支付金额 分
			params.put("notify_url", notify_url);// 回调地址
			params.put("trade_type", "APP");// 支付类型:APP支付 [交易类型trade_type=APP]
			params.put("attach", payVo.getCallbackUrl());// 附加数据，项目回调地址
			String body = "微信支付-微信支付";
			if (StringUtils.isNotEmpty(payVo.getBody()))
				body = payVo.getBody();
			params.put("body", body);// 商品描述
			String sign = PayCommonUtil.createSign("UTF-8", params, api_key);
			params.put("sign", sign);// 签名
			String requestXML = PayCommonUtil.getRequestXml(params);
			// 发起APP支付请求
			String responseXml = HttpClientUtils.post(PAY_URL, requestXML,
					"application/xml", "utf-8", 20000, 20000);
			System.out.println("微信接口调用XML转化结果：payForApp()=>responseXml==="
					+ responseXml);
			XStream xStream = new XStream();
			xStream.alias("xml", Map.class);
			xStream.registerConverter(new MapConvertor());
			Map<String, String> resultMap = (Map) xStream.fromXML(responseXml);
			if ("SUCCESS".equals(resultMap.get("return_code"))
					&& "SUCCESS".equals(resultMap.get("result_code"))) {
				// 统一下单接口返回正常的prepay_id，再按签名规范重新生成签名后，将数据传输给APP。
				SortedMap<String, String> results = new TreeMap<String, String>();
				results.put("appid", appid);
				results.put("partnerid", mch_id);
				results.put("prepayid", resultMap.get("prepay_id").toString());
				results.put("package", "Sign=WXPay");
				results.put("noncestr",
						UUID.randomUUID().toString().replace("-", ""));
				// 本来生成的时间戳是13位，但是ios必须是10位，所以截取了一下
				results.put("timestamp", System.currentTimeMillis() / 1000 + "");
				String sign2 = PayCommonUtil.createSign("UTF-8", results,
						api_key);
				results.put("sign", sign2);
				return ResponseUtils.map(0, null, results);
			} else {
			
				return	ResponseUtils.map(-1, null, resultMap.get("return_msg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("支付异常", e);
			 return	ResponseUtils.map(-1, null, ResultCode.ERR_SYS);
		}
	}

	/**
	 * 
	 * 支付成功回调方法,微信
	 * 
	 * @date 2017-12-19
	 */
	@RequestMapping("/payNofityForWechat")
	public void payNofityForWechat(HttpServletRequest request,
			HttpServletResponse response) {
		// 读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		try {
			inputStream = request.getInputStream();
			String s;
			BufferedReader in = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			while ((s = in.readLine()) != null) {
				sb.append(s);
			}
			in.close();
			inputStream.close();

			// 解析xml成map
			Map<String, String> resultMap = new HashMap<String, String>();
			try {
				resultMap = XMLUtil.doXMLParse(sb.toString());
			} catch (JDOMException e) {
				logger.error("XML解析异常!");
				e.printStackTrace();
			}
			// 过滤空 设置 TreeMap
			SortedMap<String, String> params = new TreeMap<String, String>();
			Iterator it = resultMap.keySet().iterator();
			while (it.hasNext()) {
				String parameter = (String) it.next();
				String parameterValue = resultMap.get(parameter);
				if (StringUtils.isNotEmpty(parameterValue))
					params.put(parameter, parameterValue);
			}

			// 判断签名是否正确
			String resXml = "";
			if (PayCommonUtil.isTenpaySign("UTF-8", params, api_key)) {
				if ("SUCCESS".equalsIgnoreCase((String) params
						.get("return_code"))
						&& "SUCCESS".equalsIgnoreCase((String) params
								.get("result_code"))) {
					// 调用回调函数
					String callbackUrl = params.get("attach");
					if (StringUtils.isNotEmpty(callbackUrl)) {
						try {
							HttpClientUtils.postParameters(callbackUrl, params);
						} catch (Exception e) {
							e.printStackTrace();
							logger.error("项目回调函数调用失败");
						}
					}
					// 返回数据
					resXml = "<xml>"
							+ "<return_code><![CDATA[SUCCESS]]></return_code>"
							+ "<return_msg><![CDATA[OK]]></return_msg>"
							+ "</xml> ";
				} else {
					logger.error("支付失败,错误信息：" + params.get("err_code"));
					resXml = "<xml>"
							+ "<return_code><![CDATA[FAIL]]></return_code>"
							+ "<return_msg><![CDATA[报文为空]]></return_msg>"
							+ "</xml> ";
				}
			} else {
				resXml = "<xml>"
						+ "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[通知签名验证失败]]></return_msg>"
						+ "</xml> ";
				logger.error("通知签名验证失败");
			}

			// 处理业务完毕
			BufferedOutputStream out = new BufferedOutputStream(
					response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("微信支付异步通知发生异常", e);
		}
	}

	/**
	 * 微信：H5支付接口
	 * 
	 * @date 2017-12-19
	 */
	@RequestMapping("/payForMobileWeb")
	@ResponseBody
	public Object payForMobileWeb(HttpServletRequest request,
			HttpServletResponse response, @RequestBody PayVo payVo) {
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
			// 微信支付默认参数设置
			SortedMap<String, String> params = new TreeMap<String, String>();
			// 应用ID
			params.put("appid", appid);
			// 商户号ID
			params.put("mch_id", mch_id);
			// 订单号
			params.put("out_trade_no", payVo.getOutTradeNo());
			// 总价格
			BigDecimal centAmount = new BigDecimal(payVo.getTotalAmount())
					.multiply(new BigDecimal(100));
			params.put("total_fee", centAmount.intValue() + "");// 支付金额 分
			params.put("nonce_str",
					UUID.randomUUID().toString().replace("-", ""));
			// 商品描述
			String body = "微信支付";
			if (StringUtils.isNotEmpty(payVo.getBody()))
				body = payVo.getBody();
			params.put("body", body);// 商品描述
			// 客户IP
			params.put("spbill_create_ip", "127.0.0.1");
			params.put("notify_url", payVo.getCallbackUrl());
			// 支付类型:二维码支付 [交易类型trade_type=NATIVE]
			params.put("trade_type", "NATIVE");
			params.put("attach", payVo.getCallbackUrl());// 附加数据，项目回调地址
			// 签名
			String sign = PayCommonUtil.createSign("UTF-8", params, api_key);
			params.put("sign", sign);

			String requestXML = PayCommonUtil.getRequestXml(params);
			logger.info("微信接口调用：requestXML>>>==" + requestXML);

			// 发起H5支付请求:获得返回结果
			String responseXml = HttpClientUtils.post(PAY_URL, requestXML,
					"application/xml", "utf-8", 20000, 20000);
			try {
				Map map = XMLUtil.doXMLParse(responseXml);
				return ResponseUtils.map(0, null, map);
			} catch (JDOMException | IOException e) {
				logger.error("微信接口调用XML转化失败：requestXML=" + requestXML);
				e.printStackTrace();
			}
		} catch (ConnectTimeoutException e1) {
			e1.printStackTrace();
		} catch (SocketTimeoutException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return ResponseUtils.map(-1, null, ResultCode.ERR_SYS);
	}

	/**
	 * 微信支付PC二维码
	 * 
	 * @date 2017-12-19
	 */
	@ResponseBody
	@RequestMapping(value = "/qRCodeForPC")
	public Object qRCodeForPC(@RequestBody PayVo payVo) {
		try {
			// 订单编号为空或不存在
			if (StringUtils.isEmpty(payVo.getOutTradeNo()))
				return ResponseUtils.map(-1, null, ResultCode.ERR_ORDER_CODE);
			// 判断订单金额是否为空
			if (StringUtils.isEmpty(payVo.getTotalAmount()))
				return ResponseUtils.map(-1, null, ResultCode.ERR_TOTAL_AMOUNT);
			// 回调地址不能为空
			if (StringUtils.isEmpty(payVo.getCallbackUrl()))
				return ResponseUtils.map(-1,null,  ResultCode.ERR_CALLBACK_URL);

			// 微信支付默认参数设置
			SortedMap<String, String> params = new TreeMap<String, String>();
			// 应用ID
			params.put("appid", appid);
			// 商户号ID
			params.put("mch_id", mch_id);
			// 订单号+随机六位数字
			params.put("out_trade_no", payVo.getOutTradeNo());
			// 总价格
			BigDecimal centAmount = new BigDecimal(payVo.getTotalAmount())
					.multiply(new BigDecimal(100));
			params.put("total_fee", centAmount.intValue() + "");// 支付金额 分
			params.put("nonce_str",
					UUID.randomUUID().toString().replace("-", ""));
			// 商品描述
			String body = "微信支付";
			if (StringUtils.isNotEmpty(payVo.getBody()))
				body = payVo.getBody();
			params.put("body", body);// 商品描述
			// 客户IP
			params.put("spbill_create_ip", "127.0.0.1");
			params.put("notify_url", payVo.getCallbackUrl());
			// 支付类型:二维码支付 [交易类型trade_type=NATIVE]
			params.put("trade_type", "NATIVE");
			params.put("attach", payVo.getCallbackUrl());// 附加数据，项目回调地址
			// 签名
			String sign = PayCommonUtil.createSign("UTF-8", params, api_key);
			params.put("sign", sign);

			String requestXML = PayCommonUtil.getRequestXml(params);
			logger.info("微信支付PC二维码requestXML>>>=" + requestXML);

			// 微信支付PC二维码:获得返回结果
			String responseXml = HttpClientUtils.post(PAY_URL, requestXML,
					"application/xml", "utf-8", 20000, 20000);
			Map map = null;
			try {
				map = XMLUtil.doXMLParse(responseXml);
			} catch (JDOMException | IOException e) {
				e.printStackTrace();
				logger.error("微信支付PC二维码XMLUtil.doXMLParse()>>>==" + responseXml);
			}
			String urlCode = "";
			if (null != map && "SUCCESS".equals(map.get("return_code"))) {
				urlCode = (String) map.get("code_url");
				// 生成二维码
				if (!StringUtils.isEmpty(urlCode)) {
					// 生成二维码
					String QRCode = QRfromBaidu(urlCode);
					return ResponseUtils.map(0, null, QRCode);
				} else {
					return ResponseUtils.map(0, null, ResultCode.ERR_QR_CODE);
				}
			}
		} catch (ConnectTimeoutException e1) {
			e1.printStackTrace();
		} catch (SocketTimeoutException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return ResponseUtils.map(-1, null, ResultCode.ERR_SYS);
	}

	/**
	 * 把微信支付字符串生成二维码用图片显示
	 * 
	 * @param chl
	 * @return
	 */
	public String QRfromBaidu(String chl) {
		int widhtHeight = 300;
		String QRfromGoogle = "https://pan.baidu.com/share/qrcode?w="
				+ widhtHeight + "&h=" + widhtHeight + "&choe=UTF-8" + "&url="
				+ chl;

		return QRfromGoogle;
	}

	/**
	 * 微信申请退款接口
	 * 
	 * @date 2017-12-19
	 */
	@ResponseBody
	@RequestMapping(value = "/wechatRefund")
	public Object wechatRefund(@RequestBody RefundVo refundVo) {
		try {
			// 订单编号为空或不存在
			if (StringUtils.isEmpty(refundVo.getOutTradeNo()))
				return ResponseUtils.map(-1, null, ResultCode.ERR_ORDER_CODE);
			// 判断订单金额是否为空
			if (StringUtils.isEmpty(refundVo.getTotalFee()))
				return ResponseUtils.map(-1, null, ResultCode.ERR_TOTAL_AMOUNT);
			// 判断退款金额是否为空
			if (StringUtils.isEmpty(refundVo.getRefundAmount()))
			return ResponseUtils.map(-1, null, ResultCode.ERR_REFUND_AMOUNT);
			// 回调地址不能为空
			if (StringUtils.isEmpty(refundVo.getCallbackUrl()))
				return ResponseUtils.map(-1, null, ResultCode.ERR_CALLBACK_URL);
			// 初始化参数
			SortedMap<String, String> params = new TreeMap<String, String>();
			params.put("appid", appid);
			params.put("mch_id", mch_id);
			params.put("nonce_str",
					UUID.randomUUID().toString().replace("-", ""));
			params.put("out_trade_no", refundVo.getOutTradeNo());// 订单号
			params.put("transaction_id", refundVo.getTransactionId());// 订单交易号
			params.put("out_refund_no", PayCommonUtil.getCurrTime());// 商户退款单号
			BigDecimal centAmount = new BigDecimal(refundVo.getTotalFee())
					.multiply(new BigDecimal(100));
			params.put("total_fee", centAmount.intValue() + "");// 支付金额 分
			BigDecimal refundFee = new BigDecimal(refundVo.getRefundAmount())
					.multiply(new BigDecimal(100));
			params.put("refund_fee", refundFee.intValue() + "");// 退款金额 分
			String refundDesc = "微信退款";
			if (StringUtils.isNotEmpty(refundVo.getRefundReason()))
				refundDesc = refundVo.getRefundReason();
			params.put("refund_desc", refundDesc);// 退款原因
			String sign = PayCommonUtil.createSign("UTF-8", params, api_key);
			params.put("sign", sign);// 签名

			// 证书加载
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(
					certfile_path));
			try {
				keyStore.load(instream, mch_id.toCharArray());
			} finally {
				instream.close();
			}
			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom()
					.loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslcontext,
					new String[] { "TLSv1" },
					null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			HttpClientUtils.initCustomeSslsf(sslsf);

			String requestXML = PayCommonUtil.getRequestXml(params);
			// 发起APP支付请求
			String responseXml = HttpClientUtils.post(REFUND_URL, requestXML,
					"text/html", "utf-8", 20000, 20000);
			XStream xStream = new XStream();
			xStream.alias("xml", Map.class);
			xStream.registerConverter(new MapConvertor());
			Map resultMap = (Map) xStream.fromXML(responseXml);
			// 调用回调地址
			HttpClientUtils
					.postParameters(refundVo.getCallbackUrl(), resultMap);
			return ResponseUtils.map(0, null, resultMap);
		} catch (Exception e) {
			logger.error("微信退款发生异常", e);
		}
		return ResponseUtils.map(-1, null, ResultCode.ERR_SYS);
	}

	/**
	 * 
	 * 退款成功回调方法,微信
	 * 
	 * @date 2017-12-19
	 */
	@RequestMapping("/refundNofityForWechat")
	public void refundNofityForWechat(HttpServletRequest request,
			HttpServletResponse response) {
		// 读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		try {
			inputStream = request.getInputStream();
			String s;
			BufferedReader in = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			while ((s = in.readLine()) != null) {
				sb.append(s);
			}
			in.close();
			inputStream.close();

			// 解析xml成map
			Map<String, String> resultMap = new HashMap<String, String>();
			try {
				resultMap = XMLUtil.doXMLParse(sb.toString());
			} catch (JDOMException e) {
				logger.error("XML解析异常!");
				e.printStackTrace();
			}
			// 加密内容
			String req_info = resultMap.get("req_info");
			// 解密
			Map<String, String> dataMap = decryptAESToMap(req_info);
			String resXml = "";
			if ("SUCCESS".equalsIgnoreCase(resultMap.get("return_code"))
					&& "SUCCESS".equalsIgnoreCase(dataMap.get("refund_status"))) {

				// 调用回调函数
				String callbackUrl = dataMap.get("attach");
				if (StringUtils.isNotEmpty(callbackUrl)) {
					try {
						HttpClientUtils.postParameters(callbackUrl, dataMap);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("项目回调函数调用失败");
					}
				}
				// 返回数据
				resXml = "<xml>"
						+ "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			}

			// 处理业务完毕
			BufferedOutputStream out = new BufferedOutputStream(
					response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("微信退款异步通知发生异常", e);
		}
	}

	/**
	 * <p>
	 * AES解密后将数据放入map集合
	 * </p>
	 * 
	 * @date 2017-12-20
	 */
	public Map<String, String> decryptAESToMap(String data) throws Exception {
		/**
		 * paramStr样例： <root> <out_refund_no><![CDATA[48]]></out_refund_no>
		 * <out_trade_no><![CDATA[171106000411]]></out_trade_no>
		 * <refund_account>
		 * <![CDATA[REFUND_SOURCE_UNSETTLED_FUNDS]]></refund_account>
		 * <refund_fee><![CDATA[2]]></refund_fee>
		 * <refund_id><![CDATA[50000104632017110602250573638]]></refund_id>
		 * <refund_recv_accout><![CDATA[支付用户零钱]]></refund_recv_accout>
		 * <refund_request_source><![CDATA[API]]></refund_request_source>
		 * <refund_status><![CDATA[SUCCESS]]></refund_status>
		 * <settlement_refund_fee><![CDATA[2]]></settlement_refund_fee>
		 * <settlement_total_fee><![CDATA[2]]></settlement_total_fee>
		 * <success_time><![CDATA[2017-11-06 10:14:57]]></success_time>
		 * <total_fee><![CDATA[2]]></total_fee>
		 * <transaction_id><![CDATA[4200000005201711062790120473
		 * ]]></transaction_id> </root>
		 */
		String paramStr = WechatAESUtil.decryptData(data, api_key);
		XStream xStream = new XStream();
		xStream.alias("root", Map.class);
		xStream.registerConverter(new MapConvertor());
		Map<String, String> resultMap = (Map) xStream.fromXML(paramStr);
		return resultMap;
	}

}
