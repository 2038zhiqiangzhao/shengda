package com.megagao.production.ssm.listener; 

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megagao.production.ssm.domain.vo.BaseDic;
import com.megagao.production.ssm.service.DicService;
import com.megagao.production.ssm.util.SpringUtils;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月20日 上午9:48:41 
 * 类说明 
 * 初始化数据库的一些数据
 * 
 */
public class InitControllerListener  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
    /**
     * 初始化数据配置，获取bean的实例必须指定
     * 例如 @Service(value="dicService")
     * 注意:不能@Autowired指定，不然报空指针
     */
	public void init() throws ServletException {
		DicService bean = SpringUtils.getBean("dicService");
		List<BaseDic> findAllDictData = bean.findAllDictData();
		System.out.println(findAllDictData);
	}
	
	
}
