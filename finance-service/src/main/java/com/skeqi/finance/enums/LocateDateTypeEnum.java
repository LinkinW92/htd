package com.skeqi.finance.enums;

import lombok.Getter;

public enum LocateDateTypeEnum {

	/**
	 *  当前系统日期
	 */
	CURRY_DAY("1", "当前系统日期"),
	/**
	 *  当前期间最后一天
	 */
	LAST_DAY("2", "当前期间最后一天");

	@Getter
	private  String code;
	@Getter
	private  String info;

	LocateDateTypeEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static LocateDateTypeEnum getObj(String code){
		for (LocateDateTypeEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}

}
