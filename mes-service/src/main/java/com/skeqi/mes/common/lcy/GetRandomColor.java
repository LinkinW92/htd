package com.skeqi.mes.common.lcy;

import java.util.Random;

public class GetRandomColor {




	public static String getRandomColor(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<6;i++){
			Random r = new Random();
			sb.append(Integer.toHexString(r.nextInt(15)));
		}
		return "#"+sb;
	}



}
