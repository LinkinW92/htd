package com.skeqi.finance.enums;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * 币别枚举
 */
public enum CurrencyEnum {

	/**
	 *  人民币
	 */
	CYN(1, BigDecimal.ONE);

	@Getter
	private  Integer id;
	@Getter
	private  BigDecimal rate;

	CurrencyEnum(Integer id, BigDecimal rate)
	{
		this.id = id;
		this.rate = rate;
	}

	public static CurrencyEnum getObj(Integer id){
		for (CurrencyEnum type : values()) {
			if ( type.getId().equals(id)) {
				return type;
			}
		}
		return null;
	}

}
