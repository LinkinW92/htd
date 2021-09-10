package com.skeqi.mes.common.qh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @Package com.skeqi.common
 * @ClassName: DateTool
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: Wangj
 * @date: 2019年10月17日
 */
public class DateTool {

	/**
	 * 在指定天数上增加指定日数
	 * @Title: addDate
	 * @author WangJ
	 * @param @param date
	 * @param @param day
	 * @param @return
	 * @param @throws ParseException    参数
	 * @return Date    返回类型
	 */
	public  Date addDate(Date date,long day) throws ParseException {
		 long time = date.getTime(); // 得到指定日期的毫秒数
		 day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		 time+=day; // 相加得到新的毫秒数
		 return new Date(time); // 将毫秒数转换成日期
	}
	/**
	 * 得到当前系统时间
	 * @Title: getDate
	 * @author WangJ
	 * @param @return    参数
	 * @return String    返回类型
	 */
	public  String getDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        return df.format(new Date());
	}
	/**
	 * 计算两个日期间隔多少天
	 * @Title: dateAndDate
	 * @author WangJ
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param date1 第二个日期2017-02-23
	 * @param @param date2 第一个日期2017-02-22
	 * @param @return
	 * @param @throws ParseException    参数
	 * @return Integer    返回类型
	 */
	public Integer dateAndDate(String date1,String date2) throws ParseException {
		Date a1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);//第二个日期2017-02-23
		Date b1 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);//第一个日期2017-02-22
		//获取相减后天数
		int day = (int) ((a1.getTime() - b1.getTime()) / (1000*3600*24));
		return day;
	}

	/**
	 * 得到2个日期中相差小时数
	 * @Title: dimHours
	 * @author WangJ
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param start
	 * @param @param end
	 * @param @return
	 * @param @throws ParseException    参数
	 * @return long    返回类型
	 * @throws
	 */
	public long dimHours(String start,String end) throws ParseException{
//		start = "2018-07-15 13:00:00";
//		end = "2018-07-15 18:00:00";

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = f.parse(start);
		Date endDate = f.parse(end);
		//东八区
		long startHour = startDate.getTime() / (1000 * 60 * 60) + 8;
		long endHour = endDate.getTime() / (1000 * 60 * 60) + 8;

		long startDay = startHour / 24;
		long endDay = endHour / 24;

		long start1 = startHour % 24;
		long end1 = endHour % 24;

		long hour = (endDay - startDay - 1) * (17 - 9) + Math.max(Math.min(end1, 17) - 9, 0)
		        + Math.max(17 - Math.max(start1, 9), 0);
		return hour;
	}
//	public static void main(String [] args) {
//		DateTool dateTool = new DateTool();
//		long hour = 0;
//		try {
//			hour= dateTool.dimHours("", "");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		System.out.println(hour);
//	}
}
