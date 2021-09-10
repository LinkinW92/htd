package com.skeqi.mes.Exception.util;
/**
 * 未知错误,300-400
 * @author : FQZ
 * @Package: com.skeqi.mes.Exception.util
 * @date   : 2020年2月10日 下午3:37:44
 */
public class UnknownException extends ServicesException{

	private String message;

	private int code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public UnknownException(String message, int code) {
		super();
		this.message = message;
		this.code = code;
	}

	public UnknownException() {
		super();
	}



}
