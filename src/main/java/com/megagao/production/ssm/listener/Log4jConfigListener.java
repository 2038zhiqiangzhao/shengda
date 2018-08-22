package com.megagao.production.ssm.listener; 
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月20日 上午11:01:39 
 * 类说明 
 * 设置日志动态路径
 */
public class Log4jConfigListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	    // 初始化日志路径
	    String rootPath = System.getProperty("user.dir"); //D:\production_ssm-master
	    System.setProperty("log.base",rootPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
