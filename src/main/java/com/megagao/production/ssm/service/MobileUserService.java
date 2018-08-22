package com.megagao.production.ssm.service; 

import java.util.HashMap;

import com.megagao.production.ssm.domain.dto.CUserDto;
import com.megagao.production.ssm.domain.vo.CUser;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月3日 上午11:27:45 
 * 类说明 
 */
public interface MobileUserService {

	HashMap<String, Object> sendCodeWithTx(CUser cUser);

	HashMap<String, Object> checkCodeWithTx(CUserDto cUserDto);


	HashMap<String, Object> registerWithTx(CUserDto cUserDto) throws Exception;

}
