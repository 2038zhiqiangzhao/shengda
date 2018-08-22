package com.megagao.production.ssm.domain.vo; 

import java.io.Serializable;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月14日 下午1:28:31 
 * 类说明 
 */
public class QRCodeImageVo implements Serializable{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String with;
	   private String height;
	    private String size;
	    private String imageType;
	    private String jsonContent;
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public String getImageType() {
			return imageType;
		}
		public void setImageType(String imageType) {
			this.imageType = imageType;
		}
		public String getJsonContent() {
			return jsonContent;
		}
		public void setJsonContent(String jsonContent) {
			this.jsonContent = jsonContent;
		}
		public String getWith() {
			return with;
		}
		public void setWith(String with) {
			this.with = with;
		}
		public String getHeight() {
			return height;
		}
		public void setHeight(String height) {
			this.height = height;
		}
	    
}
