package com.megagao.production.ssm.service.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megagao.production.ssm.domain.dto.CUserDto;
import com.megagao.production.ssm.domain.po.CUserPo;
import com.megagao.production.ssm.domain.vo.CUser;
import com.megagao.production.ssm.domain.vo.CUserExample;
import com.megagao.production.ssm.domain.vo.CUserExample.Criteria;
import com.megagao.production.ssm.domain.vo.UCaptchas;
import com.megagao.production.ssm.domain.vo.UCaptchasExample;
import com.megagao.production.ssm.mapper.CUserMapper;
import com.megagao.production.ssm.mapper.UCaptchasMapper;
import com.megagao.production.ssm.service.LoginService;
import com.megagao.production.ssm.util.LogUtils;
import com.megagao.production.ssm.util.ParamCheck;
import com.megagao.production.ssm.util.TimeUtils;
import com.megagao.production.ssm.util.UserPasswordHashUtils;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2018年8月6日 上午10:48:56 类说明
 */
@Service
public class LoginServiceImpl implements LoginService {
	private Logger logger = LogUtils.getLogger(LoginServiceImpl.class);
	@Autowired
	private CUserMapper cUserMapper;
	@Autowired
	private UCaptchasMapper uCaptchasMapper;

	@Override
	public HashMap<String, Object> login(CUserDto cUserDto,
			HttpServletRequest req) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		String tempUsername = ParamCheck.trimTemp(cUserDto.getName());
		if (StringUtils.isBlank(cUserDto.getName())) {
			resultMap.put("code", 1);
			resultMap.put("message", "用户名称为空，登录失败！");
			return resultMap;
		}
		cUserDto.setName(tempUsername);

		String tempPassword = ParamCheck.trimTemp(cUserDto.getPassword());
		if (StringUtils.isBlank(cUserDto.getPassword())) {
			resultMap.put("code", 2);
			resultMap.put("message", "密码为空，登录失败！");
			return resultMap;
		}
		cUserDto.setPassword(tempPassword);

		try {
			CUserExample example = new CUserExample();
			example.createCriteria().andNameEqualTo(cUserDto.getName());
			List<CUser> selectByExample = cUserMapper.selectByExample(example);

			if (selectByExample.size() > 0 && selectByExample.get(0) != null) {
				// 查询到记录,验证密码正确性
				CUserExample example2 = new CUserExample();
				example2.createCriteria()
						.andNameEqualTo(cUserDto.getName())
						.andPasswordEqualTo(
								UserPasswordHashUtils.genPasswordHash(
										cUserDto.getPassword(),
										cUserDto.getSalt()));
				List<CUser> selectByExample2 = cUserMapper
						.selectByExample(example2);
				if (selectByExample2.size() >= 0
						&& selectByExample2.get(0) != null) {
					// 用户名和密码都配成功
					resultMap.put("data", selectByExample2.get(0));
					resultMap.put("code", 0);
					resultMap.put("message", "登录成功！");
				} else {
					resultMap.put("code", 3);
					resultMap.put("message", "用户名或密码错误，登录失败！");
					return resultMap;
				}
			} else {
				// 没有查到记录
				resultMap.put("code", 4);
				resultMap.put("message", "该用户未注册！");
				return resultMap;
			}
			return resultMap;

		} catch (Exception e) {
			resultMap.put("code", 5);
			resultMap.put("message", "查询出错，登录失败！");
			return resultMap;
		}

	}

	@Override
	public int updateUserInfo(CUser cUser) {
		int updateByPrimaryKeySelective = cUserMapper
				.updateByPrimaryKeySelective(cUser);
		return updateByPrimaryKeySelective;
	}

	@Override
	public int updatePassword(CUser cUser) {
		int updateByPrimaryKey = cUserMapper.updateByPrimaryKey(cUser);
		return updateByPrimaryKey;
	}

	@Override
	public int findMobileIsOnly(CUser cUser) {
		CUserExample uUserExample = new CUserExample();
		uUserExample.createCriteria()
				.andMobileEqualTo(cUser.getMobile());
		List<CUser> selectByExample = cUserMapper.selectByExample(uUserExample);
		if (selectByExample.size() > 0) {
			return selectByExample.size();
		} else {
			return 0;
		}

	}

	@Override
	public HashMap<String, Object> checkCodeWithTx(CUserPo cUser) {
		LogUtils.getLogger(getClass())
				.info("checkCodeWithTx----------------------------------------------");
		LogUtils.getLogger(getClass()).info(
				"captchas=" + cUser.getCaptchas() + "|" + "mobile="
						+ cUser.getMobile());
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		if (cUser.getMobile() == null) {
			resultMap.put("code", 2);
			resultMap.put("message", "手机号码不能为空!");
			return resultMap;
		}
		if (cUser.getCaptchas() == null) {
			resultMap.put("code", 3);
			resultMap.put("message", "验证码不能为空!");
			return resultMap;
		}
		// 根据手机号码和验证码查询验证码是否正确
		UCaptchasExample captchasPOExample = new UCaptchasExample();
		captchasPOExample.setOrderByClause("expire_time desc");
		UCaptchasExample.Criteria criteria = captchasPOExample
				.createCriteria();
		criteria.andMobileEqualTo(cUser.getMobile())
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
		if (!cUser.getCaptchas().equals(
				( (CUserDto) list.get(0)).getCaptchas())) {
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
    * 根据手机号码查询，是否有校验过的验证码
    */
	@Override
	public boolean hasAvailableCode(CUser mobileUserVO) {
		if (mobileUserVO.getMobile() == null) {
			return false;
		}
		final UCaptchasExample example = new UCaptchasExample();
		final UCaptchasExample.Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(mobileUserVO.getMobile());
	
		criteria.andSuccessIsEqualTo(1);
		criteria.andExpireTimeGreaterThan(new Date());
		try {
			final List list = uCaptchasMapper.selectByExample(example);
			if (CollectionUtils.isEmpty(list)) {
				return false;
			}
			return true;
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			return false;
		}
	}

	@Override
	public HashMap<String, Object> isRepeatPhone(CUserPo cUser) {
		logger.info("查询手机号码是否被注册,参数：");
		logger.info("手机号码：" + cUser.getMobile());
		logger.info("用户名：" + cUser.getName());
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", 0);
		CUserExample example = new CUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andMobileEqualTo(cUser.getMobile());
		// 过滤掉 已注销的用户
		criteria.andIsDeleteEqualTo((byte) 0);
		try {
			logger.info("查询新系统是否存在该用户： ");
			List list = cUserMapper.selectByExample(example);
			logger.info("新系统-查询结果大小： " + list.size());
			if (list.size() > 0) {
				resultMap.put("code", -1);
				resultMap.put("message", "手机号码已经被其他用户注册!");
			}

			return resultMap;
		} catch (Exception e) {
			LogUtils.getLogger(getClass()).error(e.getMessage(), e);
			;
			resultMap.put("code", -2);
			resultMap.put("message", "系统错误!");
			return resultMap;
		}
	}
  /**根据用户id修改手机号码
   * 
   */
	@Override
	public void modifyMobileWithTx(CUserPo cUser) {
		String oldMobile = null;
		CUser userPO = null;

		final CUser user = new CUser();
		user.setMobile(cUser.getMobile());
		user.setId(cUser.getId());

		try {
			userPO = cUserMapper.selectByPrimaryKey(user.getId());
			if (null != userPO) {
				oldMobile = userPO.getMobile();
			}

		} catch (Exception e) {
			logger.error(
					String.format("select user by id error. userid:%s",
							user.getId()), e);
		}

		try {
			cUserMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			logger.error(String.format("update user error. user:%s", user), e);
		}
		
	}

	@Override
	public void sendMsg(String mobile, String userId) {
		String updateType = "1";// 发送消息类型为更新用户手机号码

		if (StringUtils.isBlank(mobile)) {
			logger.error("手机绑定中手机号码为空");
			return;
		}
		if (StringUtils.isBlank(userId)) {
			logger.error("手机绑定用户id为空");
			return;
		}

		Map<String, String> messageObject = new HashMap<>();
		messageObject.put("mobile", mobile);
		messageObject.put("id", userId);
		messageObject.put("updateType", updateType);

		logger.info("用户绑定手机发送MQ成功");
		
	}

	@Override
	public List<CUser> slectSalt(CUser cUser) {
		CUserExample example=new CUserExample();
		example.createCriteria().andNameEqualTo(cUser.getName());
		List<CUser> selectByExample = cUserMapper.selectByExample(example);
		if(selectByExample!=null&&selectByExample.size()>0){
			return selectByExample;
			
		}else{
			return null;
		}
		
	}

	@Override
	public List<CUser> slectSaltPhone(CUser cUser) {
		CUserExample example=new CUserExample();
		example.createCriteria().andMobileEqualTo(cUser.getMobile());
		List<CUser> selectByExamplePhone = cUserMapper.selectByExample(example);
		if(selectByExamplePhone!=null&&selectByExamplePhone.size()>0){
			return selectByExamplePhone;
			
		}else{
			return null;
		}
	}

}
