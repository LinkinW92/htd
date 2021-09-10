package com.skeqi.mes.common.lcy;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDate {


	public static String getDateforSimple(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String getDate=sdf.format(date);
		return getDate;
	}
	public static String getYearMonthDay(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String getDate=sdf.format(date);
		return getDate;
	}
	public static Date getYearMonthDay(String str){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format1.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateforSimple(String str){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = format1.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}

	public static String getNumber(Double number){
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(number);

	}

	public static int getMonthDays(Date date){
        //新建一个日历类对象
        Calendar calendar = Calendar.getInstance();
        //将时间放在里面
        calendar.setTime(date);
        //获取当前月有多少天
        int days=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //返回有多少天
        return days;
    }

	public static Date getMonthFirstDay(Date date){
        //新建一个日历类对象
        Calendar calendar = Calendar.getInstance();
        //将时间放在里面
        calendar.setTime(date);
        //获取当前月第一天
        calendar.set(Calendar.DAY_OF_MONTH,1);
        //获取当月第一天的时间
        date = calendar.getTime();
        //返回时间
        return date;
    }

	  public static Date getNextDay(Date date){
	        //新建一个日历类对象
	        Calendar calendar = Calendar.getInstance();
	        //将现在时间放入里面
	        calendar.setTime(date);
	        //当前时间加上一天
	        calendar.add(calendar.DATE, +1);
	        //获取新的日期
	        date = calendar.getTime();
	        //格式转化
	        date = getDateBySimple(date,"yyyy-MM-dd");
	        //返回现在日期
	        return date;
	    }

	  /***
	     *
	     * @param date Date 类型的数据
	     * @param dateStr 转化类型的格式  eg:yyyy-MM-dd HH:mm-ss
	     * @return 对应格式的时间类型
	     */
	    public static Date getDateBySimple(Date date,String dateStr){
	        try {
	            SimpleDateFormat format = new SimpleDateFormat(dateStr);
	            String dateString = format.format(date);
	            date = format.parse(dateString);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return date;
	    }


}
