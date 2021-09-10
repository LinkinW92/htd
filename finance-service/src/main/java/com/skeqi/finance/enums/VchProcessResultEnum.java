package com.skeqi.finance.enums;

import lombok.Getter;

public enum VchProcessResultEnum {


	/**
	 *  不做任何操作
	 */
	NOT("1", "不做任何操作"),
	/**
	 *  审核
	 */
	AUDIT("2", "审核"),
	/**
	 *  审核并过账
	 */
	AUDIT_POST("3", "审核并过账");

	@Getter
	private  String code;
	@Getter
	private  String info;

	VchProcessResultEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static VchProcessResultEnum getObj(String code){
		for (VchProcessResultEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
