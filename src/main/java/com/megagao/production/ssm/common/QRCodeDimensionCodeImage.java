package com.megagao.production.ssm.common; 

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月13日 下午4:52:39 
 * 类说明 
 */
public class QRCodeDimensionCodeImage implements QRCodeImage {
  
    BufferedImage bufImg;
    
    public QRCodeDimensionCodeImage(BufferedImage bufImg) {
        this.bufImg = bufImg;
    }
    
    public int getHeight() {
        return bufImg.getHeight();
    }

    public int getPixel(int x, int y) {
        return bufImg.getRGB(x, y);
    }

    public int getWidth() {
        return bufImg.getWidth();
    }

	
    

}
