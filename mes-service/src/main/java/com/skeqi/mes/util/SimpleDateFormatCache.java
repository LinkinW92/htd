package com.skeqi.mes.util;

import java.text.SimpleDateFormat;

public class SimpleDateFormatCache {
	public static SimpleDateFormat getYmdhms() {
		String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        return sdf;
	}
}
