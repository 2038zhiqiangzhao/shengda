package com.megagao.production.ssm.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.visitor.functions.If;
import com.megagao.production.ssm.domain.dto.CUserDto;
import com.megagao.production.ssm.domain.vo.CUser;
import com.megagao.production.ssm.domain.vo.CUserExample;
import com.megagao.production.ssm.domain.vo.UCaptchas;
import com.megagao.production.ssm.domain.vo.UCaptchasExample;
import com.megagao.production.ssm.mapper.CUserMapper;
import com.megagao.production.ssm.mapper.UCaptchasMapper;
import com.megagao.production.ssm.service.MobileUserService;
import com.megagao.production.ssm.service.SmsService;
import com.megagao.production.ssm.util.EhcacheUtils;
import com.megagao.production.ssm.util.LogUtils;
import com.megagao.production.ssm.util.MD5Utils;
import com.megagao.production.ssm.util.TimeUtils;
import com.megagao.production.ssm.util.UserPasswordHashUtils;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2018年8月3日 上午11:28:18 类说明
 */
@Service
public class MobileUserServiceImpl implements MobileUserService {

	@Autowired
	private CacheManager shiroCacheManager;
	@Autowired
	private UCaptchasMapper uCaptchasMapper;
	@Autowired
	private CUserMapper cUserMapper;
	@Autowired
	private SmsService smsService;

	@Override
	public HashMap<String, Object> sendCodeWithTx(CUser cUser) {
		Cache<String, Integer> cache = shiroCacheManager.getCache("myCaptchas");
		String mobileN = cUser.getMobile();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isEmpty(mobileN)) {
			resultMap.put("code", 5);
			resultMap.put("message", "手机号码不能为空!");
			return resultMap;
		}
		// 校验次数是否超过10次
		if (!EhcacheUtils.checkTime(cache, mobileN)) {
			resultMap.put("code", 6);
			resultMap.put("message", "请以最后一次验证码为准");
			return resultMap;
		}
		UCaptchasExample uCaptchasExample = new UCaptchasExample();
		uCaptchasExample.setOrderByClause("expire_time desc");
		UCaptchasExample.Criteria criteria = uCaptchasExample.createCriteria();
		criteria.andMobileEqualTo(cUser.getMobile())
				.andExpireTimeGreaterThanOrEqualTo(new Date())
				.andSuccessIsIsNull();
		List list = uCaptchasMapper.selectByExample(uCaptchasExample);
		if (CollectionUtils.isNotEmpty(list)) {
			UCaptchas captchas1 = (UCaptchas) list.get(0);
			Calendar timeout = Calendar.getInstance();
			timeout.setTime(new Date());
			timeout.add(Calendar.MINUTE, 9);
			if (captchas1.getExpireTime().compareTo(timeout.getTime()) >= 0) {
				resultMap.put("code", 6);
				resultMap.put("message", "一分钟内至多发送一次");
				return resultMap;
			}
		}

		String charValue = "";
		charValue = getString(6);
		UCaptchas mCaptchas = new UCaptchas();
		mCaptchas.setMobile(mobileN);
		mCaptchas.setCaptchas(charValue);

		// 开始发送验证码
		int resultCode = smsService.sendSmsVerifyCode(mobileN, charValue);

		if (resultCode == 0) {
			// 缓存中put+1
			EhcacheUtils.putAuthCodeNum(cache, mobileN);
			resultMap.put("code", 0);
			resultMap.put("message", "校验码发送成功!");

			// 生成失效时间
			Calendar timeout = Calendar.getInstance();
			timeout.setTime(new Date());
			timeout.add(Calendar.MINUTE, 10);
			// 如果为第十次发送，验证码有效期设置为当天有效
			if (10 == EhcacheUtils.getTimes(mobileN, cache)) {
				timeout.add(Calendar.MINUTE,
						TimeUtils.getMinFromNowTOToday() - 10);
			}
			// 保存手机号。验证码。失效时间
			UCaptchas captchas2 = new UCaptchas();
			captchas2.setMobile(mobileN);
			captchas2.setCaptchas(charValue);
			captchas2.setExpireTime(timeout.getTime());
			uCaptchasMapper.insert(captchas2);
		} else {
			resultMap.put("code", -1);
			resultMap.put("message", "error ");
		}
		return resultMap;
	}

	private String getString(int n) {
		// 生成6位验证码
		String charValue = "";
		for (int i = 0; i < n; i++) {
			char c = (char) (randomInt(0, 10) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;
	}

	public int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	@Override
	public HashMap<String, Object> checkCodeWithTx(CUserDto cUserDto) {
		LogUtils.getLogger(getClass())
				.info("checkCodeWithTx----------------------------------------------");
		LogUtils.getLogger(getClass()).info(
				"captchas=" + cUserDto.getCaptchas() + "|" + "mobile="
						+ cUserDto.getMobile());
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if (cUserDto.getMobile() == null) {
			resultMap.put("code", 2);
			resultMap.put("message", "手机号码不能为空!");
			return resultMap;
		}
		if (cUserDto.getCaptchas() == null) {
			resultMap.put("code", 3);
			resultMap.put("message", "验证码不能为空!");
			return resultMap;
		}
		// 根据手机号码和验证码查询验证码是否正确
		UCaptchasExample captchasPOExample = new UCaptchasExample();
		captchasPOExample.setOrderByClause("expire_time desc");
		UCaptchasExample.Criteria criteria = captchasPOExample.createCriteria();
		criteria.andMobileEqualTo(cUserDto.getMobile())
				// .andCaptchasEqualTo(captchas.getCaptchas())
				.andExpireTimeGreaterThanOrEqualTo(new Date())
				.andSuccessIsIsNull();
		List list = uCaptchasMapper.selectByExample(captchasPOExample);
		if (null == list || list.size() == 0) {
			// 验证码错误
			resultMap.put("code", 1);
			resultMap.put("message", "验证码错误或失效!");
			return resultMap;
		}
		if (!cUserDto.getCaptchas().equals(
				((UCaptchas) list.get(0)).getCaptchas())) {
			// 验证码错误
			resultMap.put("code", 1);
			resultMap.put("message", "验证码错误或失效!");
			return resultMap;
		}
		// 验证成功
		Calendar timeout = Calendar.getInstance();
		timeout.setTime(new Date());
		timeout.add(Calendar.MINUTE, TimeUtils.getMinFromNowTOToday() - 1);
		UCaptchas captchas2 = (UCaptchas) list.get(0);
		if (captchas2.getExpireTime().compareTo(timeout.getTime()) < 0) {
			captchas2.setSuccessIs(1);
		}
		uCaptchasMapper.updateByPrimaryKeySelective(captchas2);
		for (int i = 1; i < list.size(); i++) {
			UCaptchas captchas1 = (UCaptchas) list.get(i);
			if (captchas1.getExpireTime().compareTo(timeout.getTime()) < 0) {
				captchas1.setSuccessIs(0);
			}
			uCaptchasMapper.updateByPrimaryKeySelective(captchas1);
		}
		resultMap.put("code", 0);
		resultMap.put("message", "校验成功!");
		return resultMap;
	}

	/**
	 * 加盐前后台逻辑（随机盐）： 1 前端注册： 原始密码2次MD5加密 后台接受数据加密 只保存盐值和2次MD5加密+盐值生成的摘要数据 输入数据 {
	 * 原始密码2次MD5加密值 前端随机生成的盐值 } 输出数据：数据库不保存MD5值，只保存加盐摘要值和随机盐值 { 前端随机生成的盐值 } 2
	 * 前端登录:原始密码2次MD5加密+后台返回来的随机盐； 后台：算的加盐摘要后匹配数据库中的加盐摘要，匹配成功认为登录密码正确
	 * 
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	@Override
	public HashMap<String, Object> registerWithTx(CUserDto cUserDto)
			throws Exception {
		String genRandomSalt = UserPasswordHashUtils.genRandomSalt();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if ((cUserDto.getRegisterType() == null)) {
			resultMap.put("code", -1);
			resultMap.put("message", "注册类型为空");
		} else {
			// 注册方式1:用户名+密码注册
			if (cUserDto.getRegisterType().equals("1")) {
				if (StringUtils.isNotBlank(cUserDto.getName())
						&& StringUtils.isNotBlank(cUserDto.getPassword())) {
					// 2次md5加密密码值
					String twoMd5Password = cUserDto.getPassword();
					// 后台生成的随机盐
					cUserDto.setSalt(genRandomSalt);
					String salt = cUserDto.getSalt();
					// 经过加盐后的密码摘要
					String passwordHash = UserPasswordHashUtils
							.genPasswordHash(twoMd5Password, salt);
					cUserDto.setPassword(passwordHash);
					// 插入数据库
					// 判断数据库中是否已经有该用户名
					CUserExample cUserExample = new CUserExample();
					cUserExample.createCriteria().andNameEqualTo(
							cUserDto.getName());
					List<CUser> selectByExample = cUserMapper
							.selectByExample(cUserExample);
					if (selectByExample.size() > 0
							&& selectByExample.get(0) != null) {
						resultMap.put("code", -1);
						resultMap.put("message", "该用户名已存在!");
					} else {
						int insertSelective = cUserMapper
								.insertSelective(cUserDto);
						if (insertSelective > 0) {
							resultMap.put("code", 20);
							resultMap.put("salt", salt);// 返回盐值
							resultMap.put("message", "注册成功!");
						} else {
							resultMap.put("code", -1);
							resultMap.put("message", "注册失败!");
						}
					}

				} else {
					resultMap.put("code", -1);
					resultMap.put("message", "用户名或者密码不空");
				}
			}
			// 注册方式2:手机号+短信验证码注册
			else if (cUserDto.getRegisterType().equals("2")) {
				// 由于短信网关未接通暂时搁置
			}
			// 注册方式3:手机号+密码注册
			else if (cUserDto.getRegisterType().equals("3")) {
				if (StringUtils.isNotBlank(cUserDto.getMobile())
						&& StringUtils.isNotBlank(cUserDto.getPassword())) {
					// 判断手机号是否存在
					CUserExample cUserExample = new CUserExample();
					cUserExample.createCriteria().andMobileEqualTo(
							cUserDto.getMobile());
					List<CUser> selectByExample = cUserMapper
							.selectByExample(cUserExample);
					if (selectByExample.size() == 0) {
						int insertSelective = cUserMapper
								.insertSelective(cUserDto);
						if (insertSelective > 0) {
							resultMap.put("code", 20);
							resultMap.put("message", "注册成功");
						} else {
							resultMap.put("code", -1);
							resultMap.put("message", "注册失败");
						}

					} else {
						resultMap.put("code", -1);
						resultMap.put("message", "该手机号已注册");
					}

				} else {
					resultMap.put("code", -1);
					resultMap.put("message", "手机号或者密码不空");
				}

			} else {
				resultMap.put("code", -1);
				resultMap.put("message", "注册方式为空或者注册方式不正确");
			}
		}

		return resultMap;
	}
}
