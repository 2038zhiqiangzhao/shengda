package com.megagao.production.ssm.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 提醒预警、信息报备、统计分析、系统bug入口
 * 
 * @author zhq_zhao
 *
 */
@Controller
@RequestMapping(value="portal")
public class portalCenter {
	/**
	 * 统计分析
	 * 
	 */
	@RequestMapping("/gonggao")
	public String gonggao() throws Exception{
		return "portal/gonggao";
	}
	
	/**
	 * 信息报备
	 * 
	 */
	@RequestMapping("/daiban")
	public String daiban() throws Exception{
		return "portal/daiban";
	}
	/**
	 * 提醒预警
	 * 
	 */
	@RequestMapping("/yujing")
	public String yujing() throws Exception{
		return "portal/yujing";
	}
	/**
	 * 系统BUG反馈
	 * 
	 */
	@RequestMapping("/bug")
	public String bug() throws Exception{
		return "portal/bug";
	}
}
