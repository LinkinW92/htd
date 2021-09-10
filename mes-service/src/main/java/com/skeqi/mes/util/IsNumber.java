package com.skeqi.mes.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断String是否为数字
 * @author : FQZ
 * @Package: com.skeqi.util
 * @date   : 2020年3月20日 下午4:29:24
 */
public class IsNumber {

	 public static boolean isNumeric(String str){
         Pattern pattern = Pattern.compile("[0-9]*");
         Matcher isNum = pattern.matcher(str);
         if( !isNum.matches() ){
             return false;
         }
         return true;
	 }

}
