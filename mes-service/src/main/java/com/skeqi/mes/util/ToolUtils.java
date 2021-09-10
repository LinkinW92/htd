package com.skeqi.mes.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.api.CMesWebApiLogs;
import com.skeqi.mes.service.all.CMesWebApiLogsService;
import com.skeqi.mes.service.zch.LogService;

import sun.misc.BASE64Decoder;

@Component
public class ToolUtils {


	@Autowired
	private LogService logService;
	@Autowired
	private CMesWebApiLogsService apiLogsService;
	private static ToolUtils toolUtils;

	@PostConstruct
	public void init() {
		toolUtils = this;
		toolUtils.logService = this.logService;
		toolUtils.apiLogsService = this.apiLogsService;
	}

	public static String getCookieValue(HttpServletRequest request, String name){
		String value = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			if(name != null && !"".equals(name)){
				for (Cookie cookie : cookies) {
					if(name.equals(cookie.getName())){
						value = cookie.getValue();
						break;
					}
				}
			}
		}
		return value;
	}

	public static String getCookieValueDecoder(HttpServletRequest request, String name){
		String value = getCookieValue(request, name);
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			value = new String(decoder.decodeBuffer(value), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static String getJsonValueFromString(String jsonStr, String key){
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		String value = "";
		try {
			value = jsonObject.getString(key);
		} catch (Exception e) {
		}
		return value;
	}

	public static String getCookieUserName(HttpServletRequest request){
		String value = getCookieValueDecoder(request, "user");
		String result = "";
		try {
			result = getJsonValueFromString(value.substring(0, value.indexOf("}") + 1), "userName");
		} catch (Exception e) {
		}
		return result;
	}
	/**
	 * 获取cookie值
	 * @param request
	 * @return jsonObject
	 */
	public static JSONObject getCookieUser(HttpServletRequest request){
		String value = getCookieValueDecoder(request, "user");
		JSONObject result =null;
		try {
			result = JSON.parseObject(value);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 转义特殊符号
	 * @param str
	 * @return
	 */
	public static String replaceSpecialStr(String str) {
		char[] c = str.toCharArray();
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < c.length; i++) {
			if (String.valueOf(c[i]).equals("*")) {
				b.append("\\*");
			} else if (String.valueOf(c[i]).equals("+")) {
				b.append("\\+");
			} else if (String.valueOf(c[i]).equals("(")) {
				b.append("\\(");
			} else if (String.valueOf(c[i]).equals("-")) {
				b.append("\\-");
			} else if (String.valueOf(c[i]).equals(")")) {
				b.append("\\)");
			} else {
				b.append(c[i]);
			}
		}
		String D = new String(b);
		return D;
	}

	/**
	 * 获取真实ip
	 * @param request
	 * @return
	 */
	public static String getRealIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		}
		if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
			// 根据网卡取本机配置的IP
			InetAddress inet = null;
			try {
				inet = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			ipAddress = inet.getHostAddress();
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	public static void errorLog(Object obj, Throwable e, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();

		String ip = getRealIp(request);
		String username = getCookieUserName(request);
		Logger log = Logger.getLogger(obj.getClass());
		log.error(" - " + username + "(" + ip + ") " , e);

		map.put("errorMsg", e.getMessage());
		map.put("username", username);
		map.put("ip", ip);

		toolUtils.logService.addErrorLog(map);
	}

	/**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    public static Map<String, Object> getJson2Map(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Map<String, Object> returnMap = new HashMap<>();
    	String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
		response.setContentType("application/json");

		if (str == null || str == "") {
			return null;
		}
		returnMap.put("jsonStr", str);
		JSONObject jo = JSONObject.parseObject(str);

		Iterator it =jo.entrySet().iterator();
		 while (it.hasNext()) {
		       Entry<String, Object> entry = (Entry<String, Object>) it.next();
		       returnMap.put(entry.getKey(), entry.getValue());
		 }

    	return returnMap;
    }

    /**
     * 插入接口日志
     * @param apiName 接口名
     * @param callTime 调用时间
     * @param parameter 参数
     * @param returnResult 返回结果
     */
    public static void insertApiLogs(String apiName, String callTime, String parameter, String returnResult) {
    	CMesWebApiLogs dx = new CMesWebApiLogs();
		dx.setApiName(apiName);
		dx.setCallTime(callTime);
		dx.setParameter(parameter);
		dx.setReturnResult(returnResult);
		dx.setReturnTime(DateUtil.getNowDate());
		toolUtils.apiLogsService.add(dx);
    }

    public static void main(String[] args) {
//		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

//    	Map<String, Object> map = new HashMap<>();
//    	map.put("sn", "111");
//    	map.put("ocv_o", "2222");
//    	JSONObject jo = new JSONObject(map);
//    	System.out.println(jo.toJSONString());

    	System.out.println(generateNewNumber("P202106200003", "P", "yyyyMMdd", 4));
	}
	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		String bigStr;
		try {
			bigStr = new BigDecimal(str).toString();
		} catch (Exception e) {
			//异常 说明包含非数字。
			return false;
		}
		return true;
	}

	/**
	 * 生成下一个编号
	 * @param lastNum 最后编码
	 * @param prefix 前缀
	 * @param ruleDate 时间段规则
	 * @param n 流水号位数
	 * @return
	 */
	public static String generateNewNumber(String lastNum, String prefix, String ruleDate, Integer n) {
		SimpleDateFormat sdf1 = new SimpleDateFormat(ruleDate);
		String now = sdf1.format(new Date());
		Integer serialInt = 1;
		if(!StringUtils.isEmpty(lastNum)) {
			int length = lastNum.length();
			String lastDate = lastNum.substring(prefix.length(), length - n);
			String lastSerialNum = lastNum.substring(length - n, length);
			serialInt = Integer.parseInt(lastSerialNum);

			if(now.equals(lastDate)) {
				serialInt ++;
			} else {
				serialInt = 1;
			}
		}
		String newSerialNum = String.format("%0" + n + "d", serialInt);

		return prefix + now + newSerialNum;
	}
}
