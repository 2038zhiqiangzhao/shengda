package com.megagao.production.ssm.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.megagao.production.ssm.controller.file.FileUploader;
import com.megagao.production.ssm.controller.file.ImageUtils;
import com.megagao.production.ssm.service.PictureService;
import com.megagao.production.ssm.util.FileUtil;
import com.megagao.production.ssm.util.IDUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureServiceImpl implements PictureService {
	@Autowired
	private HttpServletRequest request;

	@SuppressWarnings("null")
	@Override
	public Map<String, Object> uploadPicture(MultipartFile uploadFile)
			throws Exception {
		FileUploader fileUploader = new FileUploader();
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

				// 大于1M，对图片进行压缩
				if (uploadFile2.length() / 1024l > 1024l) {
					ImageUtils.optimize(uploadFile2, uploadFile2, 0.1f);
				}

				String picUrl = fileUploader.upload(uploadFile2);
				uploadFile2.delete();
				// 图片上传成功后，将图片的地址写回
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
	public boolean deleteFile(String picName) throws Exception {
			FileUtil.deleteFile(picName);
		return true;

	}
}
