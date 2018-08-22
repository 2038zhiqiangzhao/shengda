package com.megagao.production.ssm.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megagao.production.ssm.domain.dto.CUserDto;
import com.megagao.production.ssm.domain.vo.BaseDic;
import com.megagao.production.ssm.domain.vo.CUser;
import com.megagao.production.ssm.service.MobileUserService;
import com.megagao.production.ssm.util.LogUtils;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2018年8月3日 上午11:21:54 类说明 用户注册controller
 */
@RestController
@Api(tags = "注册接口")//用于归类是哪一类的接口
@RequestMapping(method = RequestMethod.POST)
public class RegisterController {

	@Autowired
	private MobileUserService mobileUserService;

	/**
	 * 发送验证码
	 *
	 * @param cUser
	 * @return
	 * @throws Exception
	 *             若指定consumes为application/json,那么服务器仅处理request
	 *             Content-Type为“application/json”类型的请求 consumes：
	 *             指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
	 *             produces: 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
	 */
	@ApiOperation(value = "发送验证码",response = CUser.class)
	@RequestMapping(value = "/sendCaptchas", consumes = "application/json", method = RequestMethod.POST)
	public Object sendCode(@RequestBody CUser cUser) throws Exception {
		HashMap<String, Object> resultMap = null;
		resultMap = mobileUserService.sendCodeWithTx(cUser);
		return resultMap;
	}

	/**
	 * 校验验证码
	 *
	 * @param cUserDto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "校验验证码",response = CUserDto.class)
	@RequestMapping(value = "/checkCaptchas", method = RequestMethod.POST)
	public HashMap checkCode(@RequestBody CUserDto cUserDto) throws Exception {
		LogUtils.getLogger(getClass())
				.info("checkCode----------------------------------------------------");
		LogUtils.getLogger(getClass()).info(
				"captchas=" + cUserDto.getCaptchas() + "|" + "mobile="
						+ cUserDto.getMobile());

		HashMap<String, Object> resultMap = mobileUserService
				.checkCodeWithTx(cUserDto);
		return resultMap;
	}

	/**
	 * 注册
	 *
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "注册",response = CUserDto.class)
	@RequestMapping(value = "/register", consumes = "application/json", method = RequestMethod.POST)
	public Object submitUser(@RequestBody CUserDto cUserDto) throws Exception {
		// 逻辑处理
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			HashMap<String, Object> map = mobileUserService
					.registerWithTx(cUserDto);
			return map;
		} catch (Exception e) {
			resultMap.put("code", "-1");
			resultMap.put("message", e.getMessage());
			return resultMap;
		}
	}
}
