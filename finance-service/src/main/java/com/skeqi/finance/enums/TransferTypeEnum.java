package com.skeqi.finance.enums;

import lombok.Getter;

public enum TransferTypeEnum {


	/**
	 *  是
	 */
	IN("1", "转入"),
	/**
	 *  否
	 */
	OUT("2", "按比例转出余额");

	@Getter
	private  String code;
	@Getter
	private  String info;

	TransferTypeEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static TransferTypeEnum getObj(String code){
		for (TransferTypeEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
