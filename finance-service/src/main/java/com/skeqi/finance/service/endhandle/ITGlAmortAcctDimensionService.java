package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.amortization.TGlAmortAcctDimension;
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortAcctDimensionVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctDimensionQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctDimensionAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctDimensionEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证摊销预提维度控制Service接口
 *
 * @author toms
 * @date 2021-07-27
 */
public interface ITGlAmortAcctDimensionService extends IServicePlus<TGlAmortAcctDimension> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlAmortAcctDimensionVo queryById(Long dimensionId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlAmortAcctDimensionVo> queryPageList(TGlAmortAcctDimensionQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlAmortAcctDimensionVo> queryList(TGlAmortAcctDimensionQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证摊销预提维度控制
	 * @param bo 凭证摊销预提维度控制新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlAmortAcctDimensionAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证摊销预提维度控制
	 * @param bo 凭证摊销预提维度控制编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlAmortAcctDimensionEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
