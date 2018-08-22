package com.megagao.production.ssm.common;

public class ResultCode {
	public static final String SUCCESS = "命令执行成功";
	public static final String ERR_SYS = "服务器内部错误，请联系管理员";
	public static final String ERR_ORDER_CODE = "订单编号不能为空";
	public static final String ERR_TOTAL_AMOUNT = "订单金额不能为空";
	public static final String ERR_REFUND_AMOUNT = "订单退款金额不能为空";
	public static final String ERR_CALLBACK_URL = "回调地址不能为空";
	public static final String ERR_QR_CODE = "获取二维码失败";

}
