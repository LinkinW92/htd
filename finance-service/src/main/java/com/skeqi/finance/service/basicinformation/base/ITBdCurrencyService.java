package com.skeqi.finance.service.basicinformation.base;

import com.skeqi.finance.domain.TBdCurrency;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdCurrencyVo;
import com.skeqi.finance.pojo.bo.basicinformation.currency.TBdCurrencyQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.currency.TBdCurrencyAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.currency.TBdCurrencyEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 基础-币别Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdCurrencyService extends IServicePlus<TBdCurrency> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdCurrencyVo queryById(Integer fCurrencyId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdCurrencyVo> queryPageList(TBdCurrencyQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdCurrencyVo> queryList(TBdCurrencyQueryBo bo);

	/**
	 * 根据新增业务对象插入基础-币别
	 * @param bo 基础-币别新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdCurrencyAddBo bo);

	/**
	 * 根据编辑业务对象修改基础-币别
	 * @param bo 基础-币别编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdCurrencyEditBo bo);

	/**
	 * 审核
	 * @param ids
	 * @return
	 */
	Boolean audit(Collection<Integer> ids);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
