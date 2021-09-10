package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.amortization.TGlAmortPeriod;
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortPeriodVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortPeriodQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortPeriodAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortPeriodEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证摊销-摊销期间Service接口
 *
 * @author toms
 * @date 2021-07-27
 */
public interface ITGlAmortPeriodService extends IServicePlus<TGlAmortPeriod> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlAmortPeriodVo queryById(Long fSchemeId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlAmortPeriodVo> queryPageList(TGlAmortPeriodQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlAmortPeriodVo> queryList(TGlAmortPeriodQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证摊销-摊销期间
	 * @param bo 凭证摊销-摊销期间新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlAmortPeriodAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证摊销-摊销期间
	 * @param bo 凭证摊销-摊销期间编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlAmortPeriodEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
