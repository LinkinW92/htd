package com.skeqi.mes.Exception.util;
/**
 * 参数异常错误，异常码200-300
 * @author : FQZ
 * @Package: com.skeqi.mes.Exception.util
 * @date   : 2020年2月10日 下午3:37:22
 */
public class ParameterNullException extends ServicesException{

	private String message;

	private int code;

	public ParameterNullException(String message, int code) {
		super();
		this.message = message;
		this.code = code;
	}

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

	public ParameterNullException() {
		super();
	}


}
