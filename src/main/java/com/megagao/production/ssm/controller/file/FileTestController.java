package com.megagao.production.ssm.controller.file; 

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.vo.FileTestVo;
import com.megagao.production.ssm.service.FileTestService;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年7月25日 下午5:18:53 
 * 类说明 
 * 统计分析测试，文件上传下载测试等
 */
@Controller
@RequestMapping(value="fileTest")
public class FileTestController {
	@Autowired
	private FileTestService fileTestService;
	/**excel导入
	 * 
	 * @param fileName
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/excelin")
	public String FileExcelin() throws Exception{
		return "file/excelin";
		
	}
	@RequestMapping("/analysisone")
	public String analysisone(){
		return "file/analysis_one";
	}
	@RequestMapping("/analysistwo")
	public String analysistwo(){
		return "file/analysis_two";
	}
	
	@RequestMapping("/analysisthree")
	public String analysisthree(){
		return "file/analysis_three";
	}
	@RequestMapping("/analysisfour")
	public String analysisfour(){
		return "file/analysis_four";
	}
	/**文件上传
	 * 
	 * @param fileName
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/fileUpload")
	public String FileFileUpload() throws Exception{
		return "file/fileUpload";
	}
	
	/**统计分析
	 * 
	 * @param fileName
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/analysis")
	public String FilAanalysis() throws Exception{
		return "file/analysis";
	}
	
	/**图片上传和附件上传
	 * 
	 * @param cOrder
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid FileTestVo fileTestVo, BindingResult bindingResult) throws Exception {
		CustomResult result;
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			System.out.println(fieldError.getDefaultMessage());
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
			result = fileTestService.insert(fileTestVo);
		
		return result;
	}
}
