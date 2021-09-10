package com.skeqi.finance.enums;

import lombok.Getter;

public enum VchSourceEnum {


	/**
	 *  预提凭证标识
	 */
	WITHHOLDING("WITHHOLDING", "预提凭证标识"),
	/**
	 *  自动转账标识
	 */
	AUTO_TRANSFER("AUTO_TRANSFER", "自动转账标识"),

	/**
	 * 凭证摊销
	 */
	AMORTIZATION("AMORTIZATION", "凭证摊销标识"),

	/**
	 * 期末调汇
	 */
	END_EXCHANGE("END_EXCHANGE", "期末调汇"),

	/**
	 * 结转损益
	 */
	INCOME_LOSS("INCOME_LOSS", "结转损益"),
	;

	@Getter
	private  String code;
	@Getter
	private  String info;

	VchSourceEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static VchSourceEnum getObj(String code){
		for (VchSourceEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
