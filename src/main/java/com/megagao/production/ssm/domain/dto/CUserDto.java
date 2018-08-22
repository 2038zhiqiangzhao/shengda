package com.megagao.production.ssm.domain.dto; 

import java.util.Calendar;

import com.megagao.production.ssm.domain.vo.CUser;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月3日 下午4:37:00 
 * 类说明 
 */
public class CUserDto extends CUser {
	private String registerType;// 三种注册/登录方式
	private String captchas;

	public String getCaptchas() {
		return captchas;
	}

	public void setCaptchas(String captchas) {
		this.captchas = captchas;
	}

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}

	

}
