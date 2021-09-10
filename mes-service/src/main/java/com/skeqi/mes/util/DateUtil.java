package com.skeqi.mes.util;


import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.junit.Test;

public class DateUtil {

	public static void main(String[] args) {
		System.out.println(getNian());
		System.out.println(getYue());;
		System.out.println(getRi());
	}

	@Test
	public void testName() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("MM");// 设置日期格式
		// new Date()为获取当前系统时间
		System.out.println(Integer.parseInt(df.format(new Date())));
	}

	public static String getNian(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

	public static String getYue(){
		SimpleDateFormat df = new SimpleDateFormat("MM");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

	public static String getRi(){
		SimpleDateFormat df = new SimpleDateFormat("dd");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

	public static String getDate(String gs) {
		SimpleDateFormat df = new SimpleDateFormat(gs);// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

	public static String getNowDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

	public static Date getNowDateHms() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		String format = "HH:mm:ss";
		Date nowTime = new SimpleDateFormat(format).parse(df.format(new Date()));
		return nowTime;
	}

//	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
//		String format = "HH:mm:ss";
//		Date nowTime = new SimpleDateFormat(format).parse(df.format(new Date()));
//		System.out.println(nowTime);
//	}

	/**
	 * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	 *
	 * @param nowTime
	 *            当前时间
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 * @author jqlin
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {

		if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 	时间戳转yyyy-MM-dd HH:mm:ss
	 * @param ts 时间戳
	 * @return
	 */
	public static String parseTimestamp(Long ts) {
		SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		return format.format(ts);
	}

	/**
	 * 	时间戳转yyyy-MM-dd HH:mm:ss
	 * @param ts 时间戳
	 * @return
	 */
	public static String parseTimestamp(String ts) {
		 Long time=new Long(ts);
		return parseTimestamp(time);
	}

}
