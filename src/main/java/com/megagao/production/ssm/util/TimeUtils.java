package com.megagao.production.ssm.util; 

import java.util.Calendar;
import java.util.GregorianCalendar;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月3日 下午4:24:59 
 * 类说明 
 */
public class TimeUtils {
	private static final Long MinuteInMs = 60 * 1000L;

	public static int getMinFromNowTOToday() {
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long diff = (cal.getTimeInMillis() - System.currentTimeMillis())
				/ MinuteInMs;
		return (int) diff;
	}
}