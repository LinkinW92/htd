package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.TGlExchangeSchemeEntry;
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeSchemeEntryVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeEntryQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeEntryAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeEntryEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 期末调汇方案分录Service接口
 *
 * @author toms
 * @date 2021-07-30
 */
public interface ITGlExchangeSchemeEntryService extends IServicePlus<TGlExchangeSchemeEntry> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlExchangeSchemeEntryVo queryById(Long fEntryId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlExchangeSchemeEntryVo> queryPageList(TGlExchangeSchemeEntryQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlExchangeSchemeEntryVo> queryList(TGlExchangeSchemeEntryQueryBo bo);

	/**
	 * 根据新增业务对象插入期末调汇方案分录
	 * @param bo 期末调汇方案分录新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlExchangeSchemeEntryAddBo bo);

	/**
	 * 根据编辑业务对象修改期末调汇方案分录
	 * @param bo 期末调汇方案分录编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlExchangeSchemeEntryEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
