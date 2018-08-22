package com.megagao.production.ssm.domain.vo; 
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月14日 下午4:20:23 
 * 类说明 
 */

import java.io.Serializable;

public class RefundVo implements Serializable {
	private static final long serialVersionUID = 1L;

	// 订单号
	private String outTradeNo;

	// 交易号
	private String tradeNo;

	// 本次退款金额
	private String refundAmount;

	// 退款成功回调地址
	private String callbackUrl;

	// 退款原因
	private String refundReason;

	// 微信交易号
	private String transactionId;

	// 订单总金额
	private String totalFee;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
}

