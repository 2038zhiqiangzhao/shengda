package com.megagao.production.ssm.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.megagao.production.ssm.domain.dto.BaseToken;
import com.megagao.production.ssm.domain.dto.CUserDto;
import com.megagao.production.ssm.domain.po.CUserPo;
import com.megagao.production.ssm.domain.vo.CUser;
import com.megagao.production.ssm.service.LoginService;
import com.megagao.production.ssm.util.CheckTokenUtils;
import com.megagao.production.ssm.util.EhChacheManagerUtils;
import com.megagao.production.ssm.util.LogUtils;
import com.megagao.production.ssm.util.PicUrlUtils;
import com.megagao.production.ssm.util.ResponseUtils;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2018年8月6日 上午10:05:26 类说明 :登录controller
 */
@RestController
@Api(tags = "app登录接口")//用于归类是哪一类的接口
@RequestMapping(method = RequestMethod.POST)
public class AppLoginController {
	@Autowired
	private LoginService loginService;
	private String token;
	private Logger logger = LogUtils.getLogger(AppLoginController.class);

	/**
	 * 登录
	 * 
	 * @param cUserDto
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "登录信息",response = CUserDto.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@RequestBody CUserDto cUserDto, HttpServletRequest req,
			HttpServletResponse resp) {
		HashMap<String, Object> map = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// 第一种登录方式 用户名+密码 用户名支持：实际用户名/手机号/邮箱
			if (StringUtils.isNotBlank(cUserDto.getRegisterType())
					&& cUserDto.getRegisterType().equals("1")) {
				if (cUserDto.getSalt() == null) {
					ResponseUtils.map(-1, "盐值不能为空或者登录类型为空", null);
				}
				// 优先1username登录 2手机号登录 3邮箱登录
				cUserDto.setName(cUserDto.getName());
				map = loginService.login(cUserDto, req);
				int code = Integer.parseInt(map.get("code").toString());// code
																		// 等于
				// 0表示登陆成功
				if (code == 0) {
					CUser cUser = (CUser) map.get("data");
					// 写缓存
					token = EhChacheManagerUtils.createToken(
							cUser.getId(), cUser);

				}
				resultMap.put("code", code);
				// 登录成功后，网站端token参数会自动写入缓存；app端需保存到本地，之后所有的请求需要携带token参数
				resultMap.put("token", token);
				// resultMap.put("ceshi",
				// EhChacheManagerUtils.getToken(createToken));
				resultMap.put("message", map.get("message").toString());
				return resultMap;

			}
			// 第二种 ：手机号+短信验证码登录
			else if (StringUtils.isNotBlank(cUserDto.getRegisterType())
					&& cUserDto.getRegisterType().equals("2")) {
				return resp;
			} else {
				
				return ResponseUtils.map(-1, "注册类型为空或者注册类型不匹配", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		      return ResponseUtils.map(-1, e, null);
		}

	}

	/**
	 * 退出登录
	 * 
	 * @param baseToken
	 * @param req
	 * @param resp
	 * @return
	 */
	@ApiOperation(value = "退出登录",response = BaseToken.class)
	@RequestMapping(value = "/exit", method = RequestMethod.POST)
	public Object exit(@RequestBody BaseToken baseToken,HttpServletRequest request) {
		  boolean isHavaToken = CheckTokenUtils.IsHavaToken(baseToken);
		  if(isHavaToken){
			  Object ehChacheIsMatching = CheckTokenUtils.EHChacheIsMatching(baseToken);
			  if(ehChacheIsMatching!=null){
				 return  ehChacheIsMatching;
			  }
		  }else{
				return  ResponseUtils.map(-1, "token为空", null);
		  }
		
		try {
			boolean clearToken = EhChacheManagerUtils.clearToken(baseToken
					.getToken());
			if (clearToken) {
				
				return ResponseUtils.map(0, "注销成功", null);
			} else {
				return ResponseUtils.map(-1, "注销失败", null);
			}
		} catch (Exception e) {
			return ResponseUtils.map(-1, "注销失败", null);
		}

	}

	/**
	 * 获取用户信息
	 * 
	 * @param baseToken
	 * @return
	 */
	int numString=0;
	@ApiOperation(value = "获取用户信息",response = BaseToken.class)
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
	public Object getUserInfo(@RequestBody BaseToken baseToken) {
		 boolean isHavaToken = CheckTokenUtils.IsHavaToken(baseToken);
		  if(isHavaToken){
			  Object ehChacheIsMatching = CheckTokenUtils.EHChacheIsMatching(baseToken);
			  if(ehChacheIsMatching!=null){
				 return  ehChacheIsMatching;
			  }
		  }else{
				return  ResponseUtils.map(-1, "token为空", null);
		  }
		try {
			CUser mtoken = (CUser) EhChacheManagerUtils.getToken(token);
		
			System.out.println("token:"+numString++);
			return ResponseUtils.map(0, "成功", mtoken);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.map(-1, "获取失败", null);
		}

	}

	/**
	 * 修改用户信息
	 * 
	 * @param cUser
	 * @return
	 */
	@ApiOperation(value = "修改用户信息",response = CUser.class)
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public Object updateUser(@RequestBody CUser cUser) {
		try {
			 boolean isHavaToken = CheckTokenUtils.IsHavaToken(cUser);
			  if(isHavaToken){
				  Object ehChacheIsMatching = CheckTokenUtils.EHChacheIsMatching(cUser);
				  if(ehChacheIsMatching!=null){
					 return  ehChacheIsMatching;
				  }
			  }else{
					return  ResponseUtils.map(-1, "token为空", null);
			  }
			// 获取登陆id
			CUser mtoken = (CUser) EhChacheManagerUtils.getToken(token);
			cUser.setId(mtoken.getId());
			// 头像url转换
			PicUrlUtils picUrlUtils = PicUrlUtils.getInstant();
			cUser.setUserphoto((picUrlUtils.setUrl(cUser.getUserphoto())));

			int updateUserInfo = loginService.updateUserInfo(cUser);
			if (updateUserInfo > 0) {
				return ResponseUtils.map(0, "修改成功", null);
			} else {
				return ResponseUtils.map(-1, "修改失败", null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.map(-1, "注销失败", null);
		}

	}

	/**
	 * 密码修改只需要传入2次密码匹配成功后的密码，匹配有前端去做
	 * 
	 * @param cUser
	 * @return
	 */
	@ApiOperation(value = "密码修改",response = CUser.class)
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public Object updatePassword(@RequestBody CUser cUser) {
		try {
			boolean isHavaToken = CheckTokenUtils.IsHavaToken(cUser);
			  if(isHavaToken){
				  Object ehChacheIsMatching = CheckTokenUtils.EHChacheIsMatching(cUser);
				  if(ehChacheIsMatching!=null){
					 return  ehChacheIsMatching;
				  }
			  }else{
					return  ResponseUtils.map(-1, "token为空", null);
			  }
			if (StringUtils.isNotBlank(cUser.getPassword())) {
				// 获取登陆id
				CUser mtoken = (CUser) EhChacheManagerUtils
						.getToken(token);
				cUser.setId(mtoken.getId());
				int updatePassword = loginService.updatePassword(cUser);
				if (updatePassword > 0) {
					 return ResponseUtils.map(0, "修改成功", null);
				} else {
					 return ResponseUtils.map(-1, "修改失败", null);
				}
			} else {
				 return ResponseUtils.map(-1, "密码为空", null);
			}
		} catch (Exception e) {
			  return ResponseUtils.map(-1, "异常", null);
		}

	}

	/** 重新绑定手机号码 */
	@ApiOperation(value = "重新绑定手机号码",response = CUserPo.class)
	@RequestMapping(value = "/checkMobileAndModifyMobile", method = RequestMethod.POST)
	public Object checkMobileAndModifyMobile(@RequestBody CUserPo cUser,
			HttpServletRequest req, HttpServletResponse resp) {
		logger.info("换绑手机号码参数：" + JSON.toJSONString(cUser));
		if (cUser.getCreateToken() == null) {
			ResponseUtils.map(-1, "缺少参数", null);
		}
		CUser mtoken = (CUser) EhChacheManagerUtils.getToken(token);
		cUser.setId(mtoken.getId());
		// 校验验证码
		HashMap map = checkCode(cUser);
		String code = map.get("code").toString();
		if (!"0".equals(code)) {
			return map;
		}
		final CUser mobileUserVO = new CUser();
		mobileUserVO.setMobile(cUser.getOrgianlMobile());
		// 根据手机号码查询，是否有校验过的验证码
		boolean b = loginService.hasAvailableCode(mobileUserVO);
		logger.info("校验是否存在校验过的验证码结果：" + b);
		
		/*
		 * final Long companyId = SessionContainer.getCompanyId();
		 * userAndCaptchas.setCompanyId(companyId);
		 */
		if (b) {

			final HashMap<String, Object> result = loginService
					.isRepeatPhone(cUser);

			logger.info("校验手机号码是否已经被注册结果：" + JSON.toJSONString(result));

			if (!"0".equals(result.get("code").toString())) {
				return result;
			}
			// 根据用户id修改手机号码
			loginService.modifyMobileWithTx(cUser);

			// 发送用户绑定手机变更消息到分销系统
			try {
				loginService.sendMsg(cUser.getMobile(), mtoken.getId()
						.toString());
			} catch (Exception e) {
				logger.error("发送用户绑定手机变更消息到分销系统异常：", e);
			}

			

			logger.info("修改绑定手机号码成功");

			// 清除缓存token
			EhChacheManagerUtils.remove(token);

			return ResponseUtils.map(0, "修改成功", null);
		 }
		  return ResponseUtils.map(-1, "原手机未校验未校验", null);

	}

	/**
	 * 校验验证码
	 * 
	 * @param cUser
	 * @return
	 */
	private HashMap checkCode(CUserPo cUser) {
		LogUtils.getLogger(getClass())
				.info("checkCode----------------------------------------------------");
		LogUtils.getLogger(getClass()).info(
				"captchas=" + cUser.getCaptchas() + "|" + "mobile="
						+ cUser.getMobile());
		HashMap<String, Object> resultMap = loginService.checkCodeWithTx(cUser);
		return resultMap;
	}

	/** 查询手机号码是否注册 */
	@ApiOperation(value = "查询手机号码是否注册",response = CUser.class)
	@RequestMapping(value = "/checkMobile", method = RequestMethod.POST)
	public Object checkMobile(@RequestBody CUser cUser) {
		try {
			if (cUser.getMobile() == null) {
				  return ResponseUtils.map(-1, "手机号码为空", null);
			}
			int updatePassword = loginService.findMobileIsOnly(cUser);
			if (updatePassword > 0) {
				  return ResponseUtils.map(1, "手机号已注册", null);
			} else {
				  return ResponseUtils.map(0, "手机号未注册", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			  return ResponseUtils.map(-1, "异常", null);
		}

	}
	/**通过用户名查询盐值
	 * 
	 * @param cUser
	 * @return
	 */
	@ApiOperation(value = "通过用户名查询盐值",response = CUser.class)
	@RequestMapping(value = "/selectSaltByName", method = RequestMethod.POST)
	public Object selectSalt(@RequestBody CUser cUser){
		if(cUser.getName()==null){
			return ResponseUtils.map(-1, "用户名为空", null);
		}
		 List<CUser> slectSalt = loginService.slectSalt(cUser);
		if(slectSalt!=null){
			return ResponseUtils.map(0, "成功", slectSalt);
		}else{
			return ResponseUtils.map(-1, "该用户没有注册", null);
		}
	}
	/**通过手机号查询盐值
	 * 
	 * @param cUser
	 * @return
	 */
	@ApiOperation(value = "通过手机号查询盐值",response = CUser.class)
	@RequestMapping(value = "/selectSaltByPhone", method = RequestMethod.POST)
	public Object selectSaltByPhone(@RequestBody CUser cUser){
		if(cUser.getMobile()==null){
			return ResponseUtils.map(-1, "手机号为空", null);
		}
		 List<CUser> selectByExamplePhone = loginService.slectSaltPhone(cUser);
		if(selectByExamplePhone!=null){
			return ResponseUtils.map(0, "成功", selectByExamplePhone);
		}else{
			return ResponseUtils.map(-1, "该手机号没有注册", null);
		}
	}
}
