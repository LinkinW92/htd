package com.skeqi.mes.util.yp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.ejb.access.EjbAccessException;

/**
 * 文件读取
 *
 * @author yinp
 *
 */
public class FileReading {

	/**
	 * 获取配置文件参数
	 *
	 * @param configPath
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getValue(String configPath, String key) {
		String value = "";

		Properties properties = new Properties();
		try {
			properties = PropertiesLoaderUtils.loadAllProperties(configPath);
			value = new String(properties.getProperty(key).getBytes("iso-8859-1"), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return value;
		}

	}

	public static void main(String[] args) {
		System.out.println(getValue("WeiXin/errorCode.properties", "60005"));;
	}

}
