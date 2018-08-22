package com.megagao.production.ssm.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

public class SessionCounter implements HttpSessionListener {

    private static int activeSessions = 0;
    //获取活动的session个数(在线人数)
    public static int getActiveSessions() {
        return activeSessions;
    }
	// System.out.println("创建了");
	 @Override
	    public void sessionCreated(HttpSessionEvent event) {
	        // System.out.println("创建了");
	        HttpSession session = event.getSession();// 获得Session对象
	        // 通过Session获得servletcontext对象
	        ServletContext servletContext = session.getServletContext();
	        activeSessions++;
	        /**
	         * 1.获取num值
	         * 2.加1
	         * 3.存入servletcontext
	         */
	        Object object = servletContext.getAttribute("num");
	        if (object == null) {
	            servletContext.setAttribute("num", 1);
	        } else {
	            Object num = servletContext.getAttribute("num");
	            int num1 = (int) num;
	            servletContext.setAttribute("num", num1 + 1);
	        }
	    }
	 
	    @Override
	    public void sessionDestroyed(HttpSessionEvent event) {
	   
	        // System.out.println("销毁了");
	        HttpSession session = event.getSession();// 获得Session对象
	        // 通过Session获得servletcontext对象
	        ServletContext servletContext = session.getServletContext();
	        /**
	         *  1.获取num值
	         *  2.减1
	         *  3.存入servletcontext
	         */
//	        servletContext.setAttribute("num",  servletContext.getAttribute("num") - 1);
	     	if (activeSessions > 0)
	            activeSessions--;
	    
	    }

}
