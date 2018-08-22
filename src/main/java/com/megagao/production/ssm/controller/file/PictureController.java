package com.megagao.production.ssm.controller.file;

import com.megagao.production.ssm.service.PictureService;
import com.megagao.production.ssm.util.CollectionsFactory;
import com.megagao.production.ssm.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**图片上传
 * 
 * @author zhiqiang_zhao
 * @date &#36;{date} 
 * 2018年8月7日
 * 2018年8月7日
 */
@RestController
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	public String pictureUpload(MultipartFile uploadFile) throws Exception{
		Map<String,Object> result = pictureService.uploadPicture(uploadFile);
		//为了保证功能的兼容性，需要把Result转换成json格式的字符串。
		String json = JsonUtils.objectToJson(result);
		return json;
	}
	
	@RequestMapping("/pic/delete")
	public String pictureDelete(@RequestParam String picName) throws Exception{
		pictureService.deleteFile(picName);
		Map<String,Object> result = CollectionsFactory.newHashMap();
		result.put("data", "success");
		String json = JsonUtils.objectToJson(result);
		return json;
	}
}
