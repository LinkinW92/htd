package com.skeqi.finance.enums;

import lombok.Getter;

public enum BaseTypeEnum {

	/**
	 *  主
	 */
	MAIN("1", "主"),
	/**
	 *  副
	 */
	VICE("0", "副");

	@Getter
	private  String code;
	@Getter
	private  String info;

	BaseTypeEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static BaseTypeEnum getObj(String code){
		for (BaseTypeEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
