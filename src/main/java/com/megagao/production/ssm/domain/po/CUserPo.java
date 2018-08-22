package com.megagao.production.ssm.domain.po;

import com.megagao.production.ssm.domain.vo.CUser;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2018年8月7日 下午4:01:06 类说明
 */
public class CUserPo extends CUser {
	private String orgianlMobile;
	private String captchas;
    private String createToken;
	public String getOrgianlMobile() {
		return orgianlMobile;
	}

	public void setOrgianlMobile(String orgianlMobile) {
		this.orgianlMobile = orgianlMobile;
	}

	public String getCaptchas() {
		return captchas;
	}

	public void setCaptchas(String captchas) {
		this.captchas = captchas;
	}

	public String getCreateToken() {
		return createToken;
	}

	public void setCreateToken(String createToken) {
		this.createToken = createToken;
	}

}
