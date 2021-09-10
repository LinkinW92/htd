package com.skeqi.finance.enums;

import lombok.Getter;

public enum VchCretaeEnum {


	/**
	 *  全部
	 */
	ONE("1", "按普通方式结转"),
	/**
	 *  终止
	 */
	TWO("2", "按损益科目类别结转"),
	/**
	 *  包含未过账凭证
	 */
	THREE("3", "按核算维度结转");

	@Getter
	private  String code;
	@Getter
	private  String info;

	VchCretaeEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static VchCretaeEnum getObj(String code){
		for (VchCretaeEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}


}
