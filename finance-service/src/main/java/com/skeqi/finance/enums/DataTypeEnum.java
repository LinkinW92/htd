package com.skeqi.finance.enums;

import lombok.Getter;

/**
 * 数据类型
 * @author dzb
 */

public enum DataTypeEnum {

	/**
	 *  是
	 */
	Basis("1", "基础资料"),
	/**
	 *  否
	 */
	Assist("2", "辅助资料");

	@Getter
	private  String code;
	@Getter
	private  String info;

	DataTypeEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static DataTypeEnum getObj(String code){
		for (DataTypeEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}


}
