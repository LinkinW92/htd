package com.skeqi.finance.enums;

import lombok.Getter;

/**
 * 资产类型枚举
 * @author toms
 */

public enum AssetsTypeEnum {

	/**
	 *  资产
	 */
	ASSETS("1", "资产"),
	/**
	 *  负责
	 */
	DEBT("2", "负责"),

	/**
	 *  共同
	 */
	COMMON("3", "共同"),

	/**
	 *  权益
	 */
	RI("4", "权益"),

	/**
	 *  成本
	 */
	COST("5", "成本"),

	/**
	 *  损益
	 */
	PL("6", "损益"),

	/**
	 *  表外科目
	 */
	OSA("7", "表外科目"),

	/**
	 *  营业收入
	 */
	OPERATING_INCOME("8", "营业收入"),

	/**
	 *  费用
	 */
	FEE("9", "费用"),

	/**
	 *  利得
	 */
	GAIN("10", "利得"),


	/**
	 *  收益
	 */
	INCOME("11", "收益"),


	/**
	 *  总收益
	 */
	SUM_INCOME("12", "总收益"),

	/**
	 *  损失
	 */
	LOSS("13", "损失"),


	/**
	 *  产权
	 */
	PROPERTY("14", "产权"),


	/**
	 *  业主投资
	 */
	OWNER_INVESTMENT("15", "业主投资"),


	/**
	 *  派给业主款
	 */
	ATTO("16", "派给业主款"),

	/**
	 *  业主权益
	 */
	OWNER_EQUITY("17", "业主权益"),

	/**
	 *  净资产
	 */
	NET_ASSETS("18", "净资产"),

	/**
	 *  收入
	 */
	REVENUE("19", "收入"),

	/**
	 *  预算收入
	 */
	BUDGET_REVENUE("20", "预算收入"),

	/**
	 *  预算支出
	 */
	BUDGET_EXPENDITURE("21", "预算支出"),

	/**
	 *  预算结余
	 */
	BUDGET_BALANCE("22", "预算结余"),
	;

	@Getter
	private  String code;
	@Getter
	private  String info;

	AssetsTypeEnum(String code, String info)
	{
		this.code = code;
		this.info = info;
	}

	public static AssetsTypeEnum getObj(String code){
		for (AssetsTypeEnum type : values()) {
			if ( type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}


}
