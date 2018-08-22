package com.megagao.production.ssm.service.impl; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.vo.FileTestVo;
import com.megagao.production.ssm.mapper.FileTestMapper;
import com.megagao.production.ssm.service.FileTestService;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年7月26日 上午9:42:57 
 * 类说明 
 */
@Service("fileTestService")
public class FileTestServiceImpl implements FileTestService{
    
	@Autowired
	private FileTestMapper fileTestMapper;
	@Override
	public CustomResult insert(FileTestVo fileTestVo) {
		int i = fileTestMapper.insertSelective(fileTestVo);
		if(i>0){
			return CustomResult.ok();
		}else{
			return CustomResult.build(101, "上传失败");
		}
	}

}
