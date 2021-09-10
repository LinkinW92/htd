package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingAcctDimension;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingAcctDimensionVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctDimensionQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctDimensionAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctDimensionEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证预提科目维度控制Service接口
 *
 * @author toms
 * @date 2021-07-27
 */
public interface ITGlWithholdingAcctDimensionService extends IServicePlus<TGlWithholdingAcctDimension> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlWithholdingAcctDimensionVo queryById(Long dimensionId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlWithholdingAcctDimensionVo> queryPageList(TGlWithholdingAcctDimensionQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlWithholdingAcctDimensionVo> queryList(TGlWithholdingAcctDimensionQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证预提科目维度控制
	 * @param bo 凭证预提科目维度控制新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlWithholdingAcctDimensionAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证预提科目维度控制
	 * @param bo 凭证预提科目维度控制编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlWithholdingAcctDimensionEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
