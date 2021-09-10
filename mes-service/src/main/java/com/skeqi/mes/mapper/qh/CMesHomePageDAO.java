package com.skeqi.mes.mapper.qh;

public interface CMesHomePageDAO {

	/**
	 * 获取今日合格数量
	 * @return
	 */
	Integer getPassCount();

	/**
	 * 获取今日未合格数量
	 * @return
	 */
	Integer getUnPassCount();

}
