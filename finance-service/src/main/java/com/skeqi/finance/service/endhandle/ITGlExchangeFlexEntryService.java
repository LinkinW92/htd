package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.TGlExchangeFlexEntry;
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeFlexEntryVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeFlexEntryQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeFlexEntryAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeFlexEntryEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 期末调汇核算维度分录Service接口
 *
 * @author toms
 * @date 2021-07-30
 */
public interface ITGlExchangeFlexEntryService extends IServicePlus<TGlExchangeFlexEntry> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlExchangeFlexEntryVo queryById(Integer fEntryId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlExchangeFlexEntryVo> queryPageList(TGlExchangeFlexEntryQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlExchangeFlexEntryVo> queryList(TGlExchangeFlexEntryQueryBo bo);

	/**
	 * 根据新增业务对象插入期末调汇核算维度分录
	 * @param bo 期末调汇核算维度分录新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlExchangeFlexEntryAddBo bo);

	/**
	 * 根据编辑业务对象修改期末调汇核算维度分录
	 * @param bo 期末调汇核算维度分录编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlExchangeFlexEntryEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
