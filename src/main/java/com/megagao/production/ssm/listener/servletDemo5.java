package com.megagao.production.ssm.listener; 

import java.io.PrintWriter;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年7月24日 下午5:27:34 
 * 类说明 
 */
public class servletDemo5 extends HttpServlet  {
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        doPost(req, resp);
	    }
	 
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 
	        req.setCharacterEncoding("utf-8");
	        resp.setContentType("text/html;charset=utf-8");
	        HttpSession session = req.getSession();// 创建Session
	        // session.setMaxInactiveInterval(10);//设置失效时间10秒 不是销毁
	        PrintWriter writer = resp.getWriter();
	        ServletContext servletContext = getServletContext();
	        Object num = servletContext.getAttribute("num");
	        writer.write("在线人数:" + num);
	    }
}
