package com.skeqi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据源
 *
 * @author toms
 */
@AllArgsConstructor
public enum DataSourceType {
	/**
	 * 主库
	 */
	MASTER("master"),

	/**
	 * 从库
	 */
	SLAVE("slave");

	@Getter
	private final String source;
}
