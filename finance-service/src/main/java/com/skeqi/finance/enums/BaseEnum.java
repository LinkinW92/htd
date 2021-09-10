package com.skeqi.finance.enums;

import lombok.Getter;

/**
 * @author toms
 */

public enum BaseEnum {

	/**
	 *  是
	 */
	YES("1", "是"),
	/**
	 *  否
	 */
	NO("0", "否");

	@Getter
	private  String code;
	@Getter
	private  String info;

	BaseEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static BaseEnum getObj(String code){
		for (BaseEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}


}
