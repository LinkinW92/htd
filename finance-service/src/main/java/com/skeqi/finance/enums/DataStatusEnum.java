package com.skeqi.finance.enums;

import lombok.Getter;

/**
 * 数据状态枚举
 */
public enum  DataStatusEnum {

	/**
	 * 删除
	 */
	DELETE("-1", "删除"),
	/**
	 *  创建/待审核
	 */
	CREATE("0", "创建"),

	/**
	 *  保存
	 */
	SAVE("4", "保存"),

	/**
	 *  暂存
	 */
	TMP_SAVE("5", "暂存"),
	/**
	 *  审核
	 */
	AUDIT("1", "审核"),

	/**
	 *  驳回/重新审核
	 */
	REJECT("2", "驳回/重新审核"),

	/**
	 *  作废
	 */
	INVALID("3", "作废"),
     ;

	@Getter
	private  String code;
	@Getter
	private  String info;

	DataStatusEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static DataStatusEnum getObj(String code){
		for (DataStatusEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}
}
