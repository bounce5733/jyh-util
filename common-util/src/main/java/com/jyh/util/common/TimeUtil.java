package com.jyh.util.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 * 
 * @author jiangyonghua
 * @date 2017年4月26日 上午10:54:26
 */
public class TimeUtil {

	public static final String CHN_DATE_FORMAT = "yyyy-MM-dd";
	public static final String CHN_TIME_FORMAT = "HH:mm:ss";
	public static final String CHN_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * @return yyyy-MM-dd格式当前日期
	 */
	public static String getDate() {
		return toStr(new Date());
	}

	/**
	 * @return yyyy-MM-dd HH:mm:ss格式当前时间
	 */
	public static String getTime() {
		return toStr(new Date(), CHN_DATETIME_FORMAT);
	}

	/**
	 * 根据时间变量返回时间字符串 yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String toStr(Date date) {
		return toStr(date, CHN_DATE_FORMAT);
	}

	/**
	 * 根据时间变量返回时间字符串
	 * 
	 * @return 返回时间字符串
	 * @param pattern
	 *            时间字符串样式
	 * @param date
	 *            时间变量
	 */
	public static String toStr(Date date, String pattern) {

		if (date == null) {
			return null;
		}
		try {
			SimpleDateFormat sfDate = new SimpleDateFormat(pattern);
			sfDate.setLenient(false);
			return sfDate.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串转换为日期java.util.Date
	 * 
	 * @param dateText
	 *            字符串
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static Date toDate(String dateText, String format) {

		if (dateText == null) {
			return null;
		}
		DateFormat df = null;
		try {
			if (format == null) {
				df = new SimpleDateFormat();
			} else {
				df = new SimpleDateFormat(format);
			}
			df.setLenient(false);

			return df.parse(dateText);
		} catch (ParseException e) {

			return null;
		}
	}

	/**
	 * 将长整型数字转换为时间
	 * 
	 * @param time
	 * @return
	 */
	public static String num2DateTime(long time) {

		if (time > 0L) {
			SimpleDateFormat sf = new SimpleDateFormat(CHN_DATETIME_FORMAT);
			Date date = new Date(time);
			return sf.format(date);
		}
		return "";
	}

	/**
	 * 将长整型数字转换为日期
	 * 
	 * @param time
	 * @return
	 */
	public static String num2Date(long time) {

		if (time > 0L) {
			SimpleDateFormat sf = new SimpleDateFormat(CHN_DATE_FORMAT);
			Date date = new Date(time);
			return sf.format(date);
		}
		return "";
	}

	/**
	 * 将时间转换为长整型数字
	 * 
	 * @param time
	 * @return
	 */
	public static long dateTime2Num(String time) {
		Date date = toDate(time, CHN_DATETIME_FORMAT);
		return date.getTime();
	}

	/**
	 * 将日期转换为长整型数字
	 * 
	 * @param date
	 * @return
	 */
	public static long date2Num(String date) {
		return toDate(date, CHN_DATE_FORMAT).getTime();
	}

}