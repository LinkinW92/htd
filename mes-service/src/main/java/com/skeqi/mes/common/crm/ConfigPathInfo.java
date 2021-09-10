package com.skeqi.mes.common.crm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

import com.skeqi.mes.controller.crm.BusinessInfoFileController;

public class ConfigPathInfo {


//	获取配置文件路径
	public String obtainPath(String configName) throws FileNotFoundException, IOException {
		 Properties pps = new Properties();
//	        pps.load(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/log4j.properties"));
//	        pps.load(new InputStreamReader(Object.class.getResourceAsStream("/log4j.properties"), "UTF-8")); this.getClass().getName()
		 	pps .load( ConfigPathInfo.class.getClassLoader().getResourceAsStream("file.properties"));
//	        pps.load(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/log4j.properties")));
	        Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
	        while(enum1.hasMoreElements()) {
	            String strKey = (String) enum1.nextElement();
	            String strValue = pps.getProperty(strKey);
	            System.out.println(strKey + "=" + strValue);
	        }
//	        String path  =  pps.getProperty("QRCode");
	        String path  =  pps.getProperty(configName);
	        return path;
	}



	  public static void main(String[] args) throws FileNotFoundException, IOException {
		  ConfigPathInfo config = new ConfigPathInfo();
		  System.out.println(config.obtainPath("CRMFile"));

	    }


}
