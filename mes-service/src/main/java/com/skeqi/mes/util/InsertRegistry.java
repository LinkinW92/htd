package com.skeqi.mes.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.prefs.Preferences;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.common.lcy.OracleEncrytion;

public class InsertRegistry {



	public static  JSONObject findRegistry(){
		JSONObject json = new JSONObject();
		json.put("code",0);
		json.put("day",0);
		json.put("msg","正常使用中");
		try {
			//首先读取注册表，判断是否有值
			String readValue = readValue("dt");
			SimpleDateFormat sim  = new SimpleDateFormat("yyyyMMdd");
			if(readValue==null || readValue==""){

				Calendar ca = Calendar.getInstance();
				ca.add(Calendar.DATE, 60);  // num为增加的天数，可以改变的
				Date d = ca.getTime();
				String enddate = sim.format(d);

				String localMac = getLocalMac();
				InsertRegistry.writeValue("dt", OracleEncrytion.getOracleEncrytion(localMac+","+enddate));   //添加到注册表


				if(readValue("dt")==null || readValue("dt")==""){   //如果注册表被删
					json.put("code",1);
					json.put("msg","系统没有注册码,请联系管理员");
					return json;
				}
			}else{
				String dt = readValue("dt");
				String oraclePassword = OracleEncrytion.getOraclePassword(dt);   //获取过期时间
				String[] split = oraclePassword.split(",");

				String mac = getLocalMac();
				if(!mac.equals(split[0])){
					json.put("code",1);
					json.put("msg","此注册码不能用于此电脑");
					return json;
				}


				String format = sim.format(new Date());  //当前时间
				SimpleDateFormat sims = new SimpleDateFormat("yyyyMMdd");
				Date parse = sims.parse(split[1]);
				Date parse2 = sims.parse(format);
				long dis = parse.getTime()-parse2.getTime();
				long day = dis / (24 * 60 * 60 * 1000);

				//当前时间大于过期时间
				if(Integer.parseInt(format)>Integer.parseInt(split[1])){
					json.put("code",1);
					json.put("day",day);
					json.put("msg","软件试用期已到,请联系供应商！");
					return json;
				}else{
					json.put("day",day);
					//过期时间与当前时间相差一周以内
					if(day<7 && day>0){
						json.put("code",2);
						json.put("day",day);
						json.put("msg","软件试用期还剩"+day+"天,请尽快联系供应商！");
					}else if(day==0){  //过期时间等于当前时间
						json.put("code",2);
						json.put("day",day);
						json.put("msg","软件明天将要到期,请尽快联系供应商！");
					}
					return json;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("code",1);
			json.put("msg","系统错误，请联系管理员");
		}
		return json;
	}

	public static void main(String[] args) throws Exception {
		JSONObject findRegistry = new JSONObject();
		findRegistry = InsertRegistry.findRegistry();
		System.out.println("code     ***"+findRegistry.get("code"));
		System.out.println("day     ***"+findRegistry.get("day"));
		System.out.println("msg     ***"+findRegistry.get("msg"));
	}


	//写入注册表
	private static void writeValue(String key,String value){
		//HKEY_LOCAL_MACHINE\Software\JavaSoft\prefs
		Preferences pre = Preferences.systemRoot();
		pre.put(key, value);
	}

	//读取注册表
	private static String readValue(String key){
		Preferences pre = Preferences.systemRoot();
		String string = pre.get(key, "");
		return string;
	}


	//获取mac地址
	private static String getLocalMac() throws SocketException, Exception {
        //获取网卡，获取地址
		InetAddress ia = InetAddress.getLocalHost();
		String ip=ia.toString().split("/")[1];
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
		return sb.toString().toUpperCase();
//		return "E4-54-E8-79-8C-3E";
   }


}
