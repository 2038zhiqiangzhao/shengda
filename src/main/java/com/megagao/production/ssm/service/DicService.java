package com.megagao.production.ssm.service; 

import java.util.List;

import com.megagao.production.ssm.domain.vo.BaseDic;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月7日 上午10:20:20 
 * 类说明 
 */
public interface DicService {

	List<BaseDic> findAllDictData();

	List<BaseDic> findDictByKey(BaseDic baseDic);

	List<BaseDic> findDictByParentKey(BaseDic baseDic);


}
