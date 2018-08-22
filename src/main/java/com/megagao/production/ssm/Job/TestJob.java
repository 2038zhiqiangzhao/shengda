package com.megagao.production.ssm.Job; 

import com.megagao.production.ssm.util.Slf4jLogUtil;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月20日 下午5:15:59 
 * 类说明 
 */
public class TestJob {
	
	public void execute() {
		Slf4jLogUtil.get().debug("测试任务execute开始执行了");
	}
	public void run() {
		Slf4jLogUtil.get().debug("测试任务run开始执行了");
	}
}
