package com.megagao.production.ssm.controller.file; 

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.megagao.production.ssm.common.Constants;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.Testdata;
import com.megagao.production.ssm.service.TestService;
import com.megagao.production.ssm.util.ExcelUtils;
import com.megagao.production.ssm.util.JsonUtils;
import com.megagao.production.ssm.util.MyResultUtils;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年7月30日 上午11:52:25 
 * 类说明 
 * 测试数据
 */
@RestController
@RequestMapping(value="test")
public class TestData {
	
	@Autowired
	private TestService testService;
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, Testdata testdata) throws Exception{
		EUDataGridResult result = testService.getList(page, rows, testdata);
		return result;
	}

	/**
     * 下载模板信息
     */
    @RequestMapping(value = "/downloadTemplate",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    public String downloadTemplate(HttpServletResponse response,HttpServletRequest request,String callback) throws IOException {
     
    	MyResultUtils downloadTemplate = testService.downloadTemplate(response, request);
    	//响应结果之前判断是否为jsonp请求
    	if(org.apache.commons.lang.StringUtils.isNotBlank(callback)){
    		//把结果封装成一个js语句去响应
    		return	callback+"("+JsonUtils.objectToJson(downloadTemplate)+")";
    	}
    	//return new String(JsonUtils.objectToJson(downloadTemplate).getBytes("GBK"), "UTF-8");
		return JsonUtils.objectToJson(downloadTemplate);  //调用业务层方法;
    }	
    /**导入
     * 
     * @param request
     * @return
     * @throws IOException
     */
	
	@RequestMapping(value="/import", method=RequestMethod.POST)
	@ResponseBody
	public String excelimport(MultipartHttpServletRequest request) throws IOException{
		
		//2 开始写入
		Iterator<String> iterator = request.getFileNames();
		String json = null;
		while (iterator.hasNext()) {
			String fileName = iterator.next();
			MultipartFile multipartFile = request.getFile(fileName);
			Map<String,Object> result = testService.uploadFile(multipartFile);
			json = JsonUtils.objectToJson(result);
		}
		return json;
	}
	/**导出
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/export")
	@ResponseBody
	public CustomResult export(HttpServletRequest request) throws IOException{
		//查询数据库
		CustomResult export = testService.export(request);
		return export;
	}
	
	

}
