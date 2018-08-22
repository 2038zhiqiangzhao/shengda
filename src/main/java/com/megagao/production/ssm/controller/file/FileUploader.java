package com.megagao.production.ssm.controller.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploader {

	/**
	 * 文件服务器ip
	 */
	private static String ip;

	/**
	 * 文件服务共享名称
	 */
	private static String shareName;

	/**
	 * 图片服务器域名
	 */
	private static String domain;

	private static String username;

	private static String password;
	static{
		Properties prop = new Properties();
		try {
			//装载配置文件
			prop.load(FileUploader.class.getClassLoader().getResourceAsStream("file-uploader.properties"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	    ip = prop.getProperty("fileUploader.ip");
	    domain = prop.getProperty("fileUploader.domain");
	    shareName = prop.getProperty("fileUploader.shareName");
	    username = prop.getProperty("fileUploader.user");
	    password = prop.getProperty("fileUploader.password");
	   
	}


	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMddhhmmss-sss");

	private Logger logger = LoggerFactory.getLogger(FileUploader.class);

	public FileUploader() {
	}

	public FileUploader(String ip, String shareName, String domain) {
		super();
		this.ip = ip;
		this.shareName = shareName;
		this.domain = domain;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getRootPath() {
		String root = "smb://" + ip + "/" + shareName;
		if (!StringUtils.isBlank(getUsername())
				&& !StringUtils.isBlank(getPassword())) {
			root = "smb://" + username + ":" + password + "@" + ip + "/"
					+ shareName;
		}
		return root;
	}

	/**
	 * 
	 * <p>
	 * 获取smb文件 基于Root Path的子路径
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年4月15日 下午5:43:32
	 * @param smbPath
	 * @return
	 * @see
	 */
	public String getChildPath(String smbPath) {
		return smbPath.substring(getRootPath().length());
	}

	/**
	 * 
	 * <p>
	 * 上传文件到samba共享服务器
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年4月15日 下午5:38:57
	 * @param in
	 * @param toPath
	 * @see
	 */
	private void uploadSamFile(File in, String toPath) {
		FileInputStream fis = null;
		SmbFileOutputStream sfos = null;
		try {
			fis = new FileInputStream(in);
			SmbFile smbFile = new SmbFile(toPath);
			// 判断目录是否存在，不存在则创建
			String parent = smbFile.getParent();
			if (!getRootPath().equals(parent)) {
				SmbFile parentFile = new SmbFile(parent);
				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
			}

			sfos = new SmbFileOutputStream(smbFile);

			byte[] buffer = new byte[1024];
			int c = 0;
			while ((c = fis.read(buffer)) != -1) {
				sfos.write(buffer);
			}
		} catch (SmbException e) {
			logger.error("smb file upload exception ", e);
		} catch (MalformedURLException e) {
			logger.error("smb file upload exception ", e);
		} catch (UnknownHostException e) {
			logger.error("smb file upload exception ", e);
		} catch (IOException e) {
			logger.error("smb file upload exception ", e);
		} finally {
			try {
				fis.close();
				sfos.close();
			} catch (IOException e) {
				logger.error("smb file upload finally io close exception ", e);
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 文件存储
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年4月13日 下午1:21:16
	 * @param in
	 * @return
	 * @see
	 */
	private String saveFile(File in) {
		String fileName = in.getName();
		String fileType = "";
		if (fileName.lastIndexOf(".") > 0) {
			fileType = fileName.substring(fileName.lastIndexOf("."));
		}

		String targetFileName = createFileName() + fileType;
		String targetPath = getRootPath() + "/" + targetFileName;
		uploadSamFile(in, targetPath);
		return targetPath;
	}

	/**
	 * 
	 * <p>
	 * 文件存储
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年4月13日 下午1:21:16
	 * @param in
	 * @return
	 * @see
	 */
	private String saveFile(File in, String fileName) {
		String dirName = createFileName();
		String targetPath = getRootPath() + "/" + dirName + "/" + fileName;
		uploadSamFile(in, targetPath);
		return targetPath;
	}

	/**
	 * 
	 * <p>
	 * 文件名称生成规则：年月日时分秒毫秒-UUID
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年4月13日 下午1:21:31
	 * @return
	 * @see
	 */
	public String createFileName() {
		return dateFormat.format(new Date()) + "-"
				+ UUID.randomUUID().toString();
	}

	/**
	 * 
	 * <p>
	 * 上传文件
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年4月13日 下午1:22:33
	 * @param file
	 * @return 返回文件访问地址
	 * @see
	 */
	public String upload(File file) {
		String targetPath = saveFile(file);
		return "http://" + this.getDomain() + getChildPath(targetPath);
	}

	/**
	 * 
	 * <p>
	 * 上传文件
	 * </p>
	 * 
	 * @author dusai
	 * @date 2017年4月13日 下午1:22:33
	 * @param file
	 *            上传文件
	 * @param fileName
	 *            上传到指定的文件名
	 * @return 返回文件访问地址
	 * @see
	 */
	public String upload(File file, String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return upload(file);
		}

		String path = saveFile(file, fileName);
		return "http://" + this.getDomain() + getChildPath(path);
	}

}
