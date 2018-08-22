package com.megagao.production.ssm.util; 
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.mysql.jdbc.log.LogUtils;
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月6日 下午5:29:05 
 * 类说明 
 */


public class PicUrlUtils {

	public static String getUrlPrefix() {
		return urlPrefix;
	}

	public static void setUrlPrefix(String urlPrefix) {
		PicUrlUtils.urlPrefix = urlPrefix;
	}

	// url默认前缀，统一上传到该前缀上
	private static String urlPrefix = "";
	// code为key，url为value
	private static LinkedHashMap<String, String> urlPatternCodeMap = new LinkedHashMap<String, String>();
	// 过滤域名，目前不怎么用得着。
	private static ArrayList<String> urlFilterList = new ArrayList<String>();

	private PicUrlUtils() {
		System.out.println("PicUrlUtils初始化开始");
	}

	private static PicUrlUtils picUrlUtils = null;

	// private static Lock lock = new ReentrantLock();

	public static PicUrlUtils getInstant() {
		if (picUrlUtils == null) {
			// lock.lock();
			try {
				picUrlUtils = new PicUrlUtils();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// lock.unlock();
			}
		}
		return picUrlUtils;
	}

	/**
	 * 对于含有http://的url地址，取得最后一个/到最后一个字符
	 * 
	 * @param url
	 * @return 得到含codeId的文件名
	 */
	public String getFileName(String url) {
		if (url == null || "".equals(url.trim())) {
			return "";
		}
		if (url.contains("http://")) {
			url = url.substring(url.lastIndexOf("/") + 1);
		}
		return url;
	}

	/**
	 * 对于含有http://的url地址，返回不包含最后一个/的域名前缀
	 * 
	 * @param url
	 * @return 不包含最后一个/的域名前缀
	 */
	public String getDomainInfo(String url) {
		String domainInfo = "";
		if (url == null || "".equals(url.trim())) {
			return "";
		}
		if (url.contains("http://")) {
			url = url.substring(0, url.lastIndexOf("/"));
		}
		return url;
	}

	/**
	 * 对于含有codeId的文件名，取得codeId
	 * 
	 * @param url
	 * @return codeId，如果有的话，没有返回空串
	 */
	public String getUrlCodeId(String url) {
		url = getFileName(url);
		String codeId = "";
		if (!"".equals(url) && url.indexOf("_codeId_") > 0) {
			codeId = url.substring(0, url.indexOf("_codeId_"));
		}
		return codeId;
	}

	/**
	 * 取得文件名
	 * 
	 * @param url
	 * @return
	 */
	public String getFileNameWithoutCodeId(String url) {
		url = getFileName(url);
		String codeId = getUrlCodeId(url);
		url = url.replace(codeId + "_codeId_", "");
		return url;
	}

	/**
	 * DTO中对url字段使用此方法
	 * 
	 * @param url
	 * @return
	 */
	public String getUrl(String url) {
		System.out.println("从数据库读取出来url----->" + url);
		String prefix = "";
		if (url != null && url.length() > 0) {
			if (url.indexOf("_codeId_") > 0) {
				String codeId = url.substring(0, url.indexOf("_codeId_"));
				url = url.replace(codeId + "_codeId_", "");
				prefix = getDomainInfoByCodeId(codeId);
			} else {
				prefix = urlPrefix;
			}
			if (url != null && !url.contains("http://")) {// url类似1458095624917_13.878807429004336_1.jpg，则直接加上前缀
				if (prefix != null) {
					url = prefix + url;
				}
			} else {
				if (!isContantsFilterString(url)) {
					if (prefix != null) {
						url = prefix + url.substring(url.lastIndexOf("/") + 1);
					}
				}
			}
		}
		System.out.println("PicUrlUtils.getUrl解析之后的url----->" + url);
		return url;
	}

	/**
	 * PO中对url字段使用此方法
	 * 
	 * @param url
	 * @return
	 */
	public String setUrl(String url) {
		String str = "";
		if (url != null && url.length() > 0) {
			// 对于过滤器中配置的域名，不进行过滤，直接返回原完整url地址存到数据库中。
			if (isContantsFilterString(url)) {
				str = url;
			} else {
				String domainInfo = getDomainInfo(url);
				String fileName = getFileName(url);
				String codeId = getCodeIdByDomainInfo(domainInfo + "/");
				if (codeId != null && codeId.length() > 0) {
					if (fileName.indexOf("_codeId_") > 0) {
						str = fileName;
					} else {
						str = codeId + "_codeId_" + fileName;
					}

				}
				if (str.length() == 0 || codeId == "") {
					str = url;
				}
			}

			// 对于非过滤器中配置的域名，根据域名找到对应code，加上codeId前缀，存到数据库中。
			if (url != null && url.contains("http:")) {
				url = url.substring(url.lastIndexOf("/") + 1);
			}
			System.out.println(url);
		} else {
			str = url;
		}
		return str;
	}

	public String getDomainInfoByCodeId(String codeId) {
		String domainInfo = "";
		if (urlPatternCodeMap != null) {
			domainInfo = urlPatternCodeMap.get(codeId);
			if (domainInfo == null) {
				domainInfo = urlPrefix;
			}
		}
		return domainInfo;

	}

	public String getCodeIdByDomainInfo(String prefix) {
		for (Map.Entry entry : urlPatternCodeMap.entrySet()) {
			if (prefix.equals(entry.getValue()))
				return (String) entry.getKey();
		}
		return "";

	}

	/**
	 * 判断是否是老数据，比如数据库存的可能是 http://tim.ksyun.storage.rw.kssws.ks-cdn.com/
	 * 1458095624917_13.878807429004336_1.jpg 或者是
	 * http://ksyun.storage.rw.kssws.ks
	 * -cdn.com/1463454354635_72.4342266864072_Hydrangeas.jpg
	 * 只要url中包括getConfigChilds中的任何一个过滤条件
	 * ，则是老数据。不做任何处理，对于非老数据，且不带http的，需要加上url_prefix
	 * 
	 * @return true表示是以前金山云服务器的老数据。false表示不是金山云的老数据。
	 */
	public boolean isContantsFilterString(String url) {
		boolean flag = false;
		if (urlFilterList != null && urlFilterList.size() > 0) {
			for (String filter : urlFilterList) {
				if (url.indexOf(filter) >= 0) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
}

