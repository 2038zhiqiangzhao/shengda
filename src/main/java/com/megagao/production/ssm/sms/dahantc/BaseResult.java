package com.megagao.production.ssm.sms.dahantc;

public class BaseResult {

	private String code;
	private String message;
	private Object object;

	private static volatile BaseResult instance;

	private BaseResult() {
	}

	public static BaseResult getIstance() {
		if (instance == null) {
			synchronized (BaseResult.class) {
				if (instance == null) {
					instance = new BaseResult();
				}
			}
		}
		return instance;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
