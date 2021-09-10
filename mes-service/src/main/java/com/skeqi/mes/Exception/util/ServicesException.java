package com.skeqi.mes.Exception.util;

public class ServicesException extends Exception{

	private int code;
	private String message;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ServicesException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public ServicesException(String message) {
		super();
		this.message = message;
	}
	public ServicesException() {
		super();
	}



}
