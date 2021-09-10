package com.skeqi.finance.enums;

import lombok.Getter;

/**
 * 借贷
 * @author toms
 */

public enum BorrowEnum {

	/**
	 *  自动
	 */
	AUTO("0", "自动判定"),
	/**
	 *  借方
	 */
	DEBIT("1", "借方"),
	/**
	 *  贷方
	 */
	CREDIT("-1", "贷方"),

	/**
	 *  平
	 */
	FLAT("2", "平");

	@Getter
	private  String code;
	@Getter
	private  String info;

	BorrowEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static BorrowEnum getObj(String code){
		for (BorrowEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}


}
