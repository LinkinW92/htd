package com.skeqi.mes.util;

import java.lang.reflect.Method;

/**
 * 通过java反射机制执行类方法
 * @author LiYinHui
 *
 */
public class GetProperty {

	/**
	 *
	 * @param propertyName 方法名
	 * @param cls 类Class
	 * @param obj 对象
	 * @return 返回对象obj.propertyName的执行结果
	 */
	public Object getProperty(String propertyName,Class<?> cls,Object obj){
		try{
			if(propertyName!=null &&!"".equals(propertyName)){
				Method method = cls.getMethod(propertyName);
				return method.invoke(obj);
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
