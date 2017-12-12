package com._520it.crm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

	
	/** 获取日期当天的最后一秒
	 * @param date
	 * @return
	 */
	public static Date getEndDate(Date date) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// 修改时分秒
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	/** 获取日期当天的第一秒
	 * @param date
	 * @return
	 */
	public static Date getStartDate(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// 修改时分秒
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}


	/*public static void main(String[] args) {
		System.out.println(getEndDate(new Date()));
	}*/

	/***
	 * 获取日期的当月第一天
	 * @param date
	 * @return
	 */
	public static Date get1stInMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		//System.err.println("hhh" + c.getTime().toString());
		return c.getTime();
	}

	/***
	 * 获取日期当年的第一天
	 * @param date
	 * @return
	 */
	public static Date get1stInYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);
		return c.getTime();
	}

	/***
	 * 获取当前日期的当年的最后一天
	 * @param date
	 * @return
	 */
	public static Date getLastDayInYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		c.set(Calendar.MONTH, Calendar.DECEMBER);
		c.set(Calendar.DAY_OF_MONTH,31);
		date = c.getTime();
		return getEndDate(date);
	}

	/**
	 * 获取日期当月的最后一天
	 * @param date
	 * @return
	 */
	public static Date getLastDayInMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		//获取某月最大天数
		int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		c.set(Calendar.DAY_OF_MONTH, lastDay);

		date = c.getTime();
		return getEndDate(date);
	}

	// 获取无格式的日期字符串
	public static String getNoFormaterString(Date date) {

		// 1. 自定义时间格式
		String format = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(format);

		// 2. 将时间转为该格式的字符串
		String time = sdf.format(date);
		//System.out.println(time);
		return time;
	}
}
