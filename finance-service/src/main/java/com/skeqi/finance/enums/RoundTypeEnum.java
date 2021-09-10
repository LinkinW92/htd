package com.skeqi.finance.enums;

import lombok.Getter;

/**
 * 舍入类型
 */
public enum RoundTypeEnum {


	/**
	 *  四舍五入
	 */
	ONE("1", "四舍五入"),
	/**
	 *  舍位
	 */
	TWO("2", "舍位"),
	/**
	 *  进位
	 */
	THREE("3", "进位"),
	;

	@Getter
	private  String code;
	@Getter
	private  String info;

	RoundTypeEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static RoundTypeEnum getObj(String code){
		for (RoundTypeEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
