package com.megagao.production.ssm.test;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import com.megagao.production.ssm.util.Slf4jLogUtil;

public class test {
	private static org.slf4j.Logger logger2 = LoggerFactory.getLogger(test.class);
	private static Logger logger = Logger.getLogger(test.class);  
	      public   static   void  main(String[] args) throws Exception  {
	    	 
	    	  logger.info("普通Info信息");
	          logger.debug("调试debug信息");
	          logger.error("报错error信息");
	          logger.warn("警告warn信息");
	          logger.fatal("严重错误fatal信息");
	          logger2.error("阿里支付接口异常!");
	          Slf4jLogUtil.get().error("测试");
	          logger2.error("查实的发放");
	          logger.debug("调试debug信息");
	       
	     } 
}