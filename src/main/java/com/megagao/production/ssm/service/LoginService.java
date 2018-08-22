package com.megagao.production.ssm.service; 

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.megagao.production.ssm.domain.dto.CUserDto;
import com.megagao.production.ssm.domain.po.CUserPo;
import com.megagao.production.ssm.domain.vo.CUser;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月6日 上午10:48:25 
 * 类说明 
 */
public interface LoginService {

	HashMap<String, Object> login(CUserDto cUserDto, HttpServletRequest req);

	int updateUserInfo(CUser cUser);

	int updatePassword(CUser cUser);

	int findMobileIsOnly(CUser cUser);

	HashMap<String, Object> checkCodeWithTx(CUserPo cUser);

	boolean hasAvailableCode(CUser mobileUserVO);

	HashMap<String, Object> isRepeatPhone(CUserPo cUser);

	void modifyMobileWithTx(CUserPo cUser);

	void sendMsg(String mobile, String string);

	List<CUser> slectSalt(CUser cUser);

	List<CUser> slectSaltPhone(CUser cUser);


}
