package com.megagao.production.ssm.domain.vo; 
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月14日 下午4:19:31 
 * 类说明 
 */

import java.io.Serializable;

public class PayVo implements Serializable {
	private static final long serialVersionUID = 1L;
	// 公共参数
	private String totalAmount;
	// 废弃字段：付款的时候不使用这个字段
	private String outTradeNo;
	// 业务编码
	private String bsCode;

	// 支付宝转用户
	private String subject;
	private String body;
	private String returnUrl;

	private String callbackUrl;

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getBsCode() {
		return bsCode;
	}

	public void setBsCode(String bsCode) {
		this.bsCode = bsCode;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
}
