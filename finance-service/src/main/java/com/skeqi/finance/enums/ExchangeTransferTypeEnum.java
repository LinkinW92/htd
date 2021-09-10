package com.skeqi.finance.enums;

import lombok.Getter;

/**
 * 调汇类型
 */
public enum  ExchangeTransferTypeEnum {

	/**
	 *  汇兑收益
	 */
	EXCHANGE_EARNING("1", "汇兑收益"),
	/**
	 *  汇兑损失
	 */
	EXCHANGE_LOSS("2", "汇兑损失"),

	/**
	 *  汇兑损益
	 */
	EXCHANGE_GAINS_LOSSES("3", "汇兑损益"),
	;

	@Getter
	private  String code;
	@Getter
	private  String info;

	ExchangeTransferTypeEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static ExchangeTransferTypeEnum getObj(String code){
		for (ExchangeTransferTypeEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
