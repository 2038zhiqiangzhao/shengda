package com.megagao.production.ssm.service; 

import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.vo.FileTestVo;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年7月26日 上午9:42:18 
 * 类说明 
 */
public interface FileTestService {

	CustomResult insert(FileTestVo fileTestVo);

}
