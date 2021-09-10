package com.skeqi.finance.enums;

import lombok.Getter;

public enum VchProcessEnum {


	/**
	 *  全部
	 */
	ALL("1", "全部过账"),
	/**
	 *  终止
	 */
	STOP("2", "终止处理"),
	/**
	 *  包含未过账凭证
	 */
	NO_VCH("3", "包含未过账凭证");

	@Getter
	private  String code;
	@Getter
	private  String info;

	VchProcessEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static VchProcessEnum getObj(String code){
		for (VchProcessEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
