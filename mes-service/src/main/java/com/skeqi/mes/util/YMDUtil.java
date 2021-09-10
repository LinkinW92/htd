package com.skeqi.mes.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * 生成条码
 * @author LYH
 *
 */
public class YMDUtil {

	public static Map<String, Character> getCharForDate(){
		//处理年
		char y[] = {'1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G',
				'H','J','K','L','M','N','P','R','S','T','V','W','X','Y'};
		//处理月
		char m[] = {'1','2','3','4','5','6','7','8','9','A','B','C'};
		//处理日
		char d[] = {'1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G',
				'H','J','K','L','M','N','P','R','S','T','V','W','X','Y','0'};
		//处理时
		char h[] = {'1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G',
				'H','J','K','L','M','N','P','R'};
		Map<String, Character> map=new HashMap<String, Character>();//存放生成的数据
		int countYear = 0;
		for(int i=2011;i<=2099;i++){
			map.put("y"+i,y[countYear++]);
			if (countYear==y.length) {
				countYear=0;
			}
		}
		int countMonth = 0;
		for (int i = 1; i <= m.length; i++) {
			map.put("m"+i,m[countMonth++]);
		}
		int countDay = 0;
		for (int i = 1; i <= d.length; i++) {
			map.put("d"+i,d[countDay++]);
		}
		int countHour = 0;
		for (int i = 1; i <= h.length; i++) {
			map.put("h"+i,h[countHour++]);
		}
		return map;
	}

//	public static void main(String[] args) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setFirstDayOfWeek(Calendar.MONDAY);
//		calendar.setTime(new Date());
//		System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
//		System.err.println(getWeekOfDate(new Date()));
//	}
	/**
	 * * 获取当前日期是星期几
	 * * @param dt
	 * * @return 当前日期是星期几
	 * */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0){
			w = 0;
		}
		return weekDays[w];
	}

	private static void getLocalMac(InetAddress ia) throws SocketException {
        //获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mac.length; i++) {
			if(i!=0){
				sb.append("-");
			}
			int temp = mac[i]&0xff;
			String str = Integer.toHexString(temp);
			if(str.length()==1){
				sb.append("0"+str);
			}else{
				sb.append(str);
			}
		}
	 System.out.println("本机MAC地址:"+sb.toString().toUpperCase());
   }

	public static void main(String[] args) throws Exception {
		InetAddress ia = InetAddress.getLocalHost();
		String ip=ia.toString().split("/")[1];
		System.out.println("IP:"+ip);
		getLocalMac(ia);
	}
}

