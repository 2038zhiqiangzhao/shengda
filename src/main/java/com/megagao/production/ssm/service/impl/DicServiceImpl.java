package com.megagao.production.ssm.service.impl; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megagao.production.ssm.domain.vo.BaseDic;
import com.megagao.production.ssm.domain.vo.BaseDicExample;
import com.megagao.production.ssm.mapper.BaseDicMapper;
import com.megagao.production.ssm.service.DicService;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月7日 上午10:20:36 
 * 类说明 
 */
@Service(value="dicService")
public class DicServiceImpl implements DicService{
	@Autowired
	private BaseDicMapper baseDicMapper;

	@Override
	public List<BaseDic> findAllDictData() {
		
		return baseDicMapper.selectAll();
	}

	@Override
	public List<BaseDic> findDictByKey(BaseDic baseDic) {
		// TODO Auto-generated method stub
		BaseDicExample example=new BaseDicExample();
		example.createCriteria().andDicKeyEqualTo(baseDic.getDicKey());
		return baseDicMapper.selectByExample(example);
	}

	@Override
	public List<BaseDic> findDictByParentKey(BaseDic baseDic) {
		BaseDicExample example=new BaseDicExample();
		example.createCriteria().andDicPkeyEqualTo(baseDic.getDicPkey());
		return baseDicMapper.selectByExample(example);
	}

}
