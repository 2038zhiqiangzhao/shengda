package com.megagao.production.ssm.api; 

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megagao.production.ssm.service.DicService;
import com.megagao.production.ssm.util.ResponseUtils;
import com.megagao.production.ssm.domain.vo.BaseDic;
import com.megagao.production.ssm.domain.vo.CUser;


/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月7日 上午10:19:27 
 * 类说明 数据字典
 */
@RestController
@Api(tags = "字典接口")//用于归类是哪一类的接口
@RequestMapping(method = RequestMethod.POST)
public class DicController {
	@Autowired
	private DicService dicService;
	/**查询所有数据
	 * 
	 * @return
	 */
	@ApiOperation(value = "查询所有数据",response = BaseDic.class)
	@RequestMapping(value="/findAllDictData")
	public Object findAllDictData() {
		try {
			List<BaseDic> baseDicList=	dicService.findAllDictData();
			return ResponseUtils.map(0, "ok", baseDicList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.map(-1, "fail", e.toString());
		}
	}
	/**根据字段编号获取数据字典*/
	@ApiOperation(value = "根据字段编号获取数据字典",response = BaseDic.class)
	@RequestMapping(value="/findDictByKey")
	public Object findDictByKey(@RequestBody BaseDic baseDic) {
		if(baseDic.getDicKey()==null){
			return ResponseUtils.map(-1, "fail", "字典值不能为空");
		}
		try {
			List<BaseDic> baseDicList=	dicService.findDictByKey(baseDic);
			return ResponseUtils.map(0, "ok", baseDicList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.map(-1, "fail", e.toString());
		}
	}
	
    /** 根据父级字段编号获取子级数据字典*/
	@ApiOperation(value = "根据父级字段编号获取子级数据字典",response = BaseDic.class)
	@RequestMapping(value="/findDictByParentKey")
	public Object findDictByParentKey(@RequestBody BaseDic baseDic) {
		if(baseDic.getDicPkey()==null){
			return ResponseUtils.map(-1, "fail", "字典值不能为空");
		}
		try {
			List<BaseDic> baseDicList=	dicService.findDictByParentKey(baseDic);
			return ResponseUtils.map(0, "ok", baseDicList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.map(-1, "fail", e.toString());
		}
	}
	
	
}
