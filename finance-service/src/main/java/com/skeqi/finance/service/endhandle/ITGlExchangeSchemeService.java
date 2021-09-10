package com.skeqi.finance.service.endhandle;

import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.finance.domain.endhandle.TGlExchangeScheme;
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeSchemeVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 期末调汇方案Service接口
 *
 * @author toms
 * @date 2021-07-30
 */
public interface ITGlExchangeSchemeService extends IServicePlus<TGlExchangeScheme> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlExchangeSchemeVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlExchangeSchemeVo> queryPageList(TGlExchangeSchemeQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlExchangeSchemeVo> queryList(TGlExchangeSchemeQueryBo bo);

	/**
	 * 根据新增业务对象插入期末调汇方案
	 * @param bo 期末调汇方案新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlExchangeSchemeAddBo bo);

	/**
	 * 根据编辑业务对象修改期末调汇方案
	 * @param bo 期末调汇方案编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlExchangeSchemeEditBo bo);

	/**
	 * 执行
	 * @param fId
	 * @return
	 */
	AjaxResult execute(Integer fId,boolean flag);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
