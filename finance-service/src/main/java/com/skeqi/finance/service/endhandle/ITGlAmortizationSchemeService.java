package com.skeqi.finance.service.endhandle;

import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.finance.domain.endhandle.amortization.TGlAmortizationScheme;
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortizationSchemeVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortizationSchemeQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortizationSchemeAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortizationSchemeEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证摊销Service接口
 *
 * @author toms
 * @date 2021-07-27
 */
public interface ITGlAmortizationSchemeService extends IServicePlus<TGlAmortizationScheme> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlAmortizationSchemeVo queryById(Integer fSchemeId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlAmortizationSchemeVo> queryPageList(TGlAmortizationSchemeQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlAmortizationSchemeVo> queryList(TGlAmortizationSchemeQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证摊销
	 * @param bo 凭证摊销新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlAmortizationSchemeAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证摊销
	 * @param bo 凭证摊销编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlAmortizationSchemeEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

	/**
	 * 执行
	 * @param fId
	 * @return
	 */
	AjaxResult execute(Integer fId);
}
