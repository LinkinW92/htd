package com.skeqi.finance.service.basicinformation.base;

import com.skeqi.finance.pojo.bo.TBdDimensionSource.DisableBo;

import java.util.Collection;

/**
 * @Created by DZB
 * @Date 2021/7/21 10:58
 * @Description TODO
 */
public interface BaseTableService<T> {


	/**
	 *  审核
	 * @param bo
	 * @return
	 */
	Boolean audit(Collection<Integer> ids);
	/**
	 *  禁用
	 * @param bo
	 * @return
	 */
	Boolean disable(DisableBo bo);

}
