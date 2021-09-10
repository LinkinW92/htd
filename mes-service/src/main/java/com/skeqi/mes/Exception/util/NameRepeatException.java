package com.skeqi.mes.Exception.util;
/**
 * name重复异常，异常码100-200
 * @author : FQZ
 * @Package: com.skeqi.mes.Exception.util
 * @date   : 2020年2月10日 下午3:36:57
 */
public class NameRepeatException extends ServicesException{

	private String message;

	private int code;



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}





	public NameRepeatException(String message, int code) {
		super();
		this.message = message;
		this.code = code;
	}

	public NameRepeatException() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}



}
