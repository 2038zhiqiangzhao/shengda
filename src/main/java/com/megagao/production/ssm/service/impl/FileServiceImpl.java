package com.megagao.production.ssm.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.megagao.production.ssm.controller.file.FileUploader;
import com.megagao.production.ssm.controller.file.ImageUtils;
import com.megagao.production.ssm.service.FileService;
import com.megagao.production.ssm.util.FileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
/**注意上传文件和不能和上传图片一样，上传图片的原始文件名称不能改变，这个更具具体需求来执行
 * 
 * @author zhiqiang_zhao
 * @date &#36;{date} 
 * 2018年7月26日
 * 2018年7月26日
 */
@Service
public class FileServiceImpl implements FileService {
	private static FileUploader fileUploader;
	static{
		 fileUploader = new FileUploader();
	}
	@Autowired
	private HttpServletRequest request;
	@SuppressWarnings("null")
	@Override
	public Map<String,Object> uploadFile(MultipartFile uploadFile) throws Exception{
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		File uploadFile2 = null;
		try {
			if (uploadFile.getSize() > 10 * 1024 * 1024) {
				resultMap.put("error", 1);
				resultMap.put("message", "上传图片大小不能超过10M！");
				return resultMap;
			}

			if (uploadFile != null && uploadFile.getOriginalFilename() != null
					&& uploadFile.getOriginalFilename().length() > 0) {

				// 文件保存路径
				String filePath = request.getSession().getServletContext()
						.getRealPath("/")
						+ UUID.randomUUID().toString()
						+ uploadFile.getOriginalFilename();
				// 转存文件
				uploadFile2 = new File(filePath);
				if (uploadFile2.exists()) {
					uploadFile2.delete();
				}
				
				// 将内存中的文件写入磁盘
				uploadFile.transferTo(uploadFile2);
				String picUrl = fileUploader.upload(uploadFile2);
				uploadFile2.delete();
				// 文件上传成功后，将文件的地址写回
				resultMap.put("error", 0);
				resultMap.put("url",picUrl);
				return resultMap;
			} else {
				// 返回结果
				uploadFile2.delete();
				resultMap.put("error", 1);
				resultMap.put("message", "文件异常");
				return resultMap;
			}

		} catch (Exception e) {
			uploadFile2.delete();
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传发生异常");
			return resultMap;
		}
	}

	@Override
	public boolean deleteFile(String fileName) throws Exception{
			FileUtil.deleteFile(fileName);
		    return true;
	}
}
