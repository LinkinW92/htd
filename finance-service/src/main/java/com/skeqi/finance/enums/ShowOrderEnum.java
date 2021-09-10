package com.skeqi.finance.enums;

import lombok.Getter;

/**
 * 币单显示顺序
 */
public enum ShowOrderEnum {


	/**
	 *  正向
	 */
	POSITIVE("1", "正"),
	/**
	 *  反向
	 */
	REVERSE("0", "反");

	@Getter
	private  String code;
	@Getter
	private  String info;

	ShowOrderEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static ShowOrderEnum getObj(String code){
		for (ShowOrderEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
