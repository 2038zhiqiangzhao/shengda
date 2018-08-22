package com.megagao.production.ssm.util;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.megagao.production.ssm.common.QRCodeDimensionCodeImage;
import com.swetake.util.Qrcode;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2018年8月13日 下午4:49:01 类说明 二维码处理工具类
 * 
 */
public class QRCodeDimensionCodeUtils {
	private static Logger logger = Logger.getLogger(QRCodeDimensionCodeUtils.class);
	// 图片宽度的一般
    private static final int IMAGE_WIDTH = 80;
    private static final int IMAGE_HEIGHT = 80;
    private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
    private static final int FRAME_WIDTH = 2;
    //二维码颜色
    private static final int BLACK = 0xFF000000;
    //二维码颜色
    private static final int WHITE = 0xFFFFFFFF;
	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 */
	public void encoderQRCode(String content, String imgPath) {
		this.encoderQRCode(content, imgPath, "png", 7);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 */
	public void encoderQRCode(String content, OutputStream output) {
		this.encoderQRCode(content, output, "png", 7);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 */
	public void encoderQRCode(String content, String imgPath, String imgType) {
		this.encoderQRCode(content, imgPath, imgType, 7);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 * @param imgType
	 *            图片类型
	 */
	public void encoderQRCode(String content, OutputStream output,
			String imgType) {
		this.encoderQRCode(content, output, imgType, 7);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public void encoderQRCode(String content, String imgPath, String imgType,
			int size) {
		try {
			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);

			File imgFile = new File(imgPath);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public void encoderQRCode(String content, OutputStream output,
			String imgType, int size) {
		try {
			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码(QRCode)图片的公共方法
	 * 
	 * @param content
	 *            存储内容
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 * @return
	 */
	public BufferedImage qRCodeCommon(String content, String imgType, int size) {
		BufferedImage bufImg = null;
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(size);
			// 获得内容的字节数组，设置编码格式
			byte[] contentBytes = content.getBytes("utf-8");
			// 图片尺寸
			int imgSize = 67 + 12 * (size - 1);
			bufImg = new BufferedImage(imgSize, imgSize,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			// 设置背景颜色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);

			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量，不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容> 二维码
			if (contentBytes.length > 0 && contentBytes.length < 800) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				throw new Exception("QRCode content bytes  length = "
						+ contentBytes.length + " not in [0, 800].");
			}
			gs.dispose();
			bufImg.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImg;
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param imgPath
	 *            图片路径
	 * @return
	 */
	public String decoderQRCode(String imgPath) {
		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new QRCodeDimensionCodeImage(
					bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param input
	 *            输入流
	 * @return
	 */
	public String decoderQRCode(InputStream input) {
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(input);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new QRCodeDimensionCodeImage(
					bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

	/**
	 * 将图片转成字节流
	 * 
	 * @param imagePath
	 * @return
	 */
	public static byte[] image2Bytes(String imagePath) {
		ImageIcon ima = new ImageIcon(imagePath);
		BufferedImage bu = new BufferedImage(ima.getImage().getWidth(null), ima
				.getImage().getHeight(null), BufferedImage.TYPE_INT_RGB);
		ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
		try {
			// 把这个jpg图像写到这个流中去,这里可以转变图片的编码格式
			boolean resultWrite = ImageIO.write(bu, "png", imageStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] tagInfo = imageStream.toByteArray();

		return tagInfo;
	}

	/**
	 * 根据字节流来判断这个图片的编码格式
	 * 
	 * @param imageBytes
	 * @return
	 */
	public static String checkImageType(byte[] imageBytes) {
		ByteArrayInputStream bais = null;
		MemoryCacheImageInputStream mcis = null;
		try {
			bais = new ByteArrayInputStream(imageBytes);
			mcis = new MemoryCacheImageInputStream(bais);
			Iterator<ImageReader> itr = ImageIO.getImageReaders(mcis);
			while (itr.hasNext()) {
				ImageReader reader = (ImageReader) itr.next();
				String imageName = reader.getClass().getSimpleName();
				if (imageName != null
						&& ("JPEGImageReader".equalsIgnoreCase(imageName))) {
					return "jpeg";
				} else if (imageName != null
						&& ("JPGImageReader".equalsIgnoreCase(imageName))) {
					return "jpg";
				} else if (imageName != null
						&& ("pngImageReader".equalsIgnoreCase(imageName))) {
					return "png";
				}
			}
		} catch (Exception e) {
		}
		return null;
	}
      public static String createFileSchemName(){
    	  try {
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyMMddHHmmss");
			  Date data =new Date();
			  String format = simpleDateFormat.format(data);
			  return format;
		} catch (Exception e) {
			e.printStackTrace();
			 return "";
		}
      }
      
      /**
       * 生成普通二维码
       *
       * @param contents
       * @param width
       * @param height
       * @param imgPath
       */
      public static void encodePR(String contents, int width, int height, String imgPath) {
          Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
          // 指定纠错等级
          hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
          // 指定编码格式
          hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
          try {
              BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                      BarcodeFormat.QR_CODE, width, height, hints);
              
              MatrixToImageWriter.writeToStream(bitMatrix, "jpg",
                      new FileOutputStream(imgPath));
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
      
   
      /**
       * 生成普通二维码
       *
       * @param contents
       * @param width
       * @param height
       */
	public static BufferedImage encodePRToBufferedImage(String contents, int width, int height) {
          Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
          // 指定纠错等级
          hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
          // 指定编码格式
          hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
          try {
              if(width<=0){
                  width=100;
              }
              if(height<=0){
                  height=100;
              }
              BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                      BarcodeFormat.QR_CODE, width, height, hints);
              
              return MatrixToImageWriter.toBufferedImage(bitMatrix);
          } catch (Exception e) {
              e.printStackTrace();
          }
          return null;
      }

      /**
       * 生成带图片的二维码
       *
       * @param content
       * @param width
       * @param height
       * @param srcImagePath
       * @param destImagePath
       */
      public static void encodePR(String content, int width, int height,
              String srcImagePath, String destImagePath) {
          try {
              ImageIO.write(genBarcode(content, width, height, srcImagePath),
                      "jpg", new File(destImagePath));
          } catch (IOException e) {
              e.printStackTrace();
          } catch (WriterException e) {
              e.printStackTrace();
          }
      }

      /**
       * 针对二维码进行解析
       *
       * @param imgPath
       * @return
       */
      public static String decodePR(String imgPath) {
          BufferedImage image = null;
          Result result = null;
          try {
              image = ImageIO.read(new File(imgPath));
              if (image == null) {
                  logger.error("the decode image may be not exists.");
              }
              LuminanceSource source = new BufferedImageLuminanceSource(image);
              BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

              Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
              hints.put(DecodeHintType.CHARACTER_SET, "GBK");

              result = new MultiFormatReader().decode(bitmap, hints);
              return result.getText();

          } catch (Exception e) {
              e.printStackTrace();
          }
          return null;
      }

      /**
       * 创建条形码
       *
       * @param contents
       * @param width
       * @param height
       * @param imgPath
       */
      public static void encodeBar(String contents, int width, int height, String imgPath) {
          // 条形码的最小宽度
          int codeWidth = 98;
          codeWidth = Math.max(codeWidth, width);
          try {
              BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                      BarcodeFormat.EAN_13, codeWidth, height, null);

              MatrixToImageWriter.writeToStream(bitMatrix, "png",
                      new FileOutputStream(imgPath));

          } catch (Exception e) {
              e.printStackTrace();
          }
      }

      /**
       * 针对条形码进行解析
       *
       * @param imgPath
       * @return
       */
      public static String decodeBar(String imgPath) {
          BufferedImage image = null;
          Result result = null;
          try {
              image = ImageIO.read(new File(imgPath));
              if (image == null) {
                  logger.error("the decode image may be not exit.");
              }
              LuminanceSource source = new BufferedImageLuminanceSource(image);
              BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

              result = new MultiFormatReader().decode(bitmap, null);
              return result.getText();
          } catch (Exception e) {
              e.printStackTrace();
          }
          return null;
      }

      /**
       * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标
       *
       * @param srcImageFile 源文件地址
       * @param height 目标高度
       * @param width 目标宽度
       * @param hasFiller 比例不对时是否需要补白：true为补白; false为不补白;
       * @throws IOException
       */
      private static BufferedImage scale(String srcImageFile, int height, int width,
              boolean hasFiller) throws IOException {
          double ratio = 0.0; // 缩放比例
          File file = new File(srcImageFile);
          BufferedImage srcImage = ImageIO.read(file);
          Image destImage = srcImage.getScaledInstance(width, height,
                  BufferedImage.SCALE_SMOOTH);
          // 计算比例
          if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
              if (srcImage.getHeight() > srcImage.getWidth()) {
                  ratio = (new Integer(height)).doubleValue()
                          / srcImage.getHeight();
              } else {
                  ratio = (new Integer(width)).doubleValue()
                          / srcImage.getWidth();
              }
              AffineTransformOp op = new AffineTransformOp(
                      AffineTransform.getScaleInstance(ratio, ratio), null);
              destImage = op.filter(srcImage, null);
          }
          if (hasFiller) {// 补白
              BufferedImage image = new BufferedImage(width, height,
                      BufferedImage.TYPE_INT_RGB);
              Graphics2D graphic = image.createGraphics();
              graphic.setColor(Color.white);
              graphic.fillRect(0, 0, width, height);
              if (width == destImage.getWidth(null))
                  graphic.drawImage(destImage, 0,
                          (height - destImage.getHeight(null)) / 2,
                          destImage.getWidth(null), destImage.getHeight(null),
                          Color.white, null);
              else
                  graphic.drawImage(destImage,
                          (width - destImage.getWidth(null)) / 2, 0,
                          destImage.getWidth(null), destImage.getHeight(null),
                          Color.white, null);
              graphic.dispose();
              destImage = image;
          }
          return (BufferedImage) destImage;
      }

      /**
       * 产生带有图片的二维码缓冲图像
       * @param content
       * @param width
       * @param height
       * @param srcImagePath
       * @return
       * @throws WriterException
       * @throws IOException
       */
      private static BufferedImage genBarcode(String content, int width, int height,
              String srcImagePath) throws WriterException, IOException {
          // 读取源图像
          BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH,
                  IMAGE_HEIGHT, true);
          int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
          for (int i = 0; i < scaleImage.getWidth(); i++) {
              for (int j = 0; j < scaleImage.getHeight(); j++) {
                  srcPixels[i][j] = scaleImage.getRGB(i, j);
              }
          }

          Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
          hint.put(EncodeHintType.CHARACTER_SET, "GBK");
          hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
          // 生成二维码
          MultiFormatWriter mutiWriter = new MultiFormatWriter();
          BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE,
                  width, height, hint);

          // 二维矩阵转为一维像素数组
          int halfW = matrix.getWidth() / 2;
          int halfH = matrix.getHeight() / 2;
          int[] pixels = new int[width * height];

          for (int y = 0; y < matrix.getHeight(); y++) {
              for (int x = 0; x < matrix.getWidth(); x++) {
                  // 读取图片
                  if (x > halfW - IMAGE_HALF_WIDTH
                          && x < halfW + IMAGE_HALF_WIDTH
                          && y > halfH - IMAGE_HALF_WIDTH
                          && y < halfH + IMAGE_HALF_WIDTH) {
                      pixels[y * width + x] = srcPixels[x - halfW
                              + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
                  }
                  // 在图片四周形成边框
                  else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
                          && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
                          && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                          + IMAGE_HALF_WIDTH + FRAME_WIDTH)
                          || (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH
                                  && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                                  && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                                  + IMAGE_HALF_WIDTH + FRAME_WIDTH)
                          || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
                                  && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                                  && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                                  - IMAGE_HALF_WIDTH + FRAME_WIDTH)
                          || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
                                  && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                                  && y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                                  + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
                      pixels[y * width + x] = 0xfffffff;
                  } else {
                      // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                      pixels[y * width + x] = matrix.get(x, y) ? 0xff000000
                              : 0xfffffff;
                  }
              }
          }

          BufferedImage image = new BufferedImage(width, height,
                  BufferedImage.TYPE_INT_RGB);
          image.getRaster().setDataElements(0, 0, width, height, pixels);

          return image;
      }
      
      public static void main(String[] args) {
          String contents = "http://www.google.com";
          String imgPath = "D://production_ssm-master//src//main//webapp//temp//";
          encodePR(contents, 100, 100,imgPath);
      }

   
    
      
      
      
      
	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main1(String[] args) {
		// 保存路径
		String imgPath = "C:\\Users\\zhq_zhao\\Pictures\\5.png";
		// 内容
		String encoderContent = "{" + "name：5" + "}";
		QRCodeDimensionCodeUtils handler = new QRCodeDimensionCodeUtils();
		// 生成二维码 其中size 1-40 越大越密集存储的信息越多
		handler.encoderQRCode(encoderContent, imgPath, "png", 5);
		System.out.println("========encoder success");
		// 解析二维码
		String decoderContent = handler.decoderQRCode(imgPath);
		System.out.println("解析结果如下：");
		System.out.println(decoderContent);
		System.out.println("========decoder success!!!");
		byte[] image2Bytes = image2Bytes(imgPath);
		System.out.println(image2Bytes);
		String checkImageType = checkImageType(image2Bytes);
		System.out.println(checkImageType);
		System.out.println((createFileSchemName()));
	}
}
