package com.megagao.production.ssm.api;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.megagao.production.ssm.controller.file.FileUploader;
import com.megagao.production.ssm.domain.vo.QRCodeImageVo;
import com.megagao.production.ssm.util.LogUtils;
import com.megagao.production.ssm.util.QRCodeDimensionCodeUtils;
import com.megagao.production.ssm.util.ResponseUtils;
/**
 * @author 作者 E-mail:
 * @version 创建时间：2018年8月13日 下午3:20:23 类说明 二维码处理类
 */
@Controller
@Api(tags = "二维码接口")
// 用于归类是哪一类的接口
@RequestMapping(method = RequestMethod.POST)
public class QRCodeController {
	private static final int URLTYPE=1;
	private Logger logger = LogUtils.getLogger(AppLoginController.class);
	private static FileUploader fileUploader;
	static{
		 fileUploader = new FileUploader();
	}
	/**此接口使用保存url地址到服务器中
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@ApiOperation(value = "生成二维码返回地址",response=QRCodeImageVo.class)
	@RequestMapping(value = "/generateQRCodeURL", consumes = "application/json", method = RequestMethod.POST)
   public Object generateQRCodeURL(@RequestBody QRCodeImageVo qRCodeImageVo,
		   HttpServletRequest request,HttpServletResponse response){
		try {   
			Object checkParamer = checkParamer(qRCodeImageVo,URLTYPE);
			if(	!checkParamer.equals("ok")){
				return checkParamer;
			}
			String createFileSchemName = QRCodeDimensionCodeUtils.createFileSchemName();
		      //1 生成临时文件放到temp中
			// D:\production_ssm-master\src\main\webapp\
 			String realPath = request.getSession().getServletContext().getRealPath("/")+
					"temp"+"\\" +createFileSchemName+".png";
 			System.out.println(realPath);
			//2 创建文件
			File file=new File(realPath);
			File fileParent = file.getParentFile();
        	if(!fileParent.exists()){
        		fileParent.mkdirs();
        	}
			if(file.isFile()&&file.exists()){
				file.delete();
			}
			//3 将文件写入磁盘中/创建文件
			boolean createNewFile = file.createNewFile();
			if(createNewFile){
				System.out.println("文件创建成功");
				logger.info("文件创建成功");
			}else{
				System.out.println("文件创建失败");
				logger.info("文件创建失败");
			}
			QRCodeDimensionCodeUtils handler = new QRCodeDimensionCodeUtils();
			// 生成二维码 其中size 1-40 越大越密集存储的信息越多
			handler.encoderQRCode(qRCodeImageVo.getJsonContent(), file.toString(),
					qRCodeImageVo.getImageType(), Integer.parseInt(qRCodeImageVo.getSize()));
			//4 上传文件到文件服务器
			String url = fileUploader.upload(file);
			//5 删除本地的文件
			if(file.exists()){
				file.delete();
			}
			// 6 返回给前端地址
			return ResponseUtils.map(0, "成功", url);
	       } catch (Exception e) {    
	           e.printStackTrace();   
	           return ResponseUtils.map(-1, "失败", null);
	       }
		
		 
		 
   }
	/**参数校验
	 * 
	 * @param qRCodeDimensionCodeImage
	 * @return
	 */
	private Object checkParamer(QRCodeImageVo qRCodeImageVo,int type) {
		 Map<String, ? extends Object> map=null;
		 if(type==1){
			 if(!StringUtils.isNotBlank(qRCodeImageVo.getSize())){
		         map = ResponseUtils.map(-1, "请输入二维码大小", null);
		      }else if(!StringUtils.isNotBlank(qRCodeImageVo.getJsonContent())){
		    	  map= ResponseUtils.map(-1, "请输入二维码内容", null);
		      }	else if(!StringUtils.isNotBlank(qRCodeImageVo.getImageType())){
		    	  map= ResponseUtils.map(-1, "请输入二维码类型", null);
		      }else{
		    	  return "ok";
		    }
		     	
		 
      	
	}
		return map;
	
}
	
	/**链接地址，特点信息越多越密集
	 * size取值1-40，越大越密集存储信息越多
	 * 可以指定imageType =png、jpg等
	 * @param request
	 * @param response
	 * @throws IOException 
	 * 192.168.88.135:8086/shengda_ssm/generateQRCodeStream?jsonContent={jj}&with=200&height=200&imageType=png&size=10
	 */
	@ApiOperation(value = "生成二维码返回流")
	@RequestMapping(value = "/generateQRCodeStream",method = RequestMethod.GET)
    public Object generateQRCodeStream(String height ,String imageType,String jsonContent,String size,String  with,
    		HttpServletRequest request,HttpServletResponse response) throws IOException{
		 ServletOutputStream stream = null;
		try {   
		
			String createFileSchemName = QRCodeDimensionCodeUtils.createFileSchemName();
		      //1 生成临时文件放到temp中
			// D:\production_ssm-master\src\main\webapp\
			String realPath = request.getSession().getServletContext().getRealPath("/")+
					"temp"+"\\" +createFileSchemName+".png";
			System.out.println(realPath);
			//2 创建文件
			File file=new File(realPath);
			File fileParent = file.getParentFile();
      	    if(!fileParent.exists()){
      		fileParent.mkdirs();
      	    }
			if(file.isFile()&&file.exists()){
				file.delete();
			}
			//3 将文件写入磁盘中/创建文件
			boolean createNewFile = file.createNewFile();
			if(createNewFile){
				System.out.println("文件创建成功");
				logger.info("文件创建成功");
			}else{
				System.out.println("文件创建失败");
				logger.info("文件创建失败");
			} 
			QRCodeDimensionCodeUtils handler = new QRCodeDimensionCodeUtils();
			// 生成二维码 其中size 1-40 越大越密集存储的信息越多
			handler.encoderQRCode(jsonContent, file.toString(),
					imageType, Integer.parseInt(size));
             stream = response.getOutputStream();
             QRCodeWriter writer = new QRCodeWriter();
             BitMatrix m = writer.encode(file.toString(), BarcodeFormat.QR_CODE, 
            		 Integer.parseInt(with),Integer.parseInt(height));
             MatrixToImageWriter.writeToStream(m, imageType, stream);
             if(file.exists()){
 				file.delete();
 			}
	       } catch (Exception e) {    
	           e.printStackTrace(); 
	           
	       } finally {
               if (stream != null) {
                   stream.flush();
                   stream.close();
               }
           }
		return null;  
      }
     
	/**链接地址
	 * @param request
	 * @param response
	 * @throws IOException 
	 * 192.168.88.135:8086/shengda_ssm/createQrCode?content={jj}&width=200&height=200
	 */
	@ApiOperation(value = "生成二维码链接地址")
	@RequestMapping(value = "/createQrCode",method = RequestMethod.GET)
    public Object createQrCode(String content, int width, int height,
            HttpServletResponse response){
		BufferedImage bi = QRCodeDimensionCodeUtils.encodePRToBufferedImage(content, width,
	                height);
	        try {
	            response.setContentType("image/jpeg");
	            OutputStream os = response.getOutputStream();
	            ImageIO.write(bi, "jpg", os);
	            os.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            try {
	                response.sendError(500);
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        }
	        return null;
	    }

      }
     

