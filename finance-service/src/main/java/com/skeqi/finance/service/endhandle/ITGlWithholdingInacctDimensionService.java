package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingInacctDimension;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingInacctDimensionVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctDimensionQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctDimensionAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctDimensionEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证预提转入科目维度控制Service接口
 *
 * @author toms
 * @date 2021-07-27
 */
public interface ITGlWithholdingInacctDimensionService extends IServicePlus<TGlWithholdingInacctDimension> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlWithholdingInacctDimensionVo queryById(Long dimensionId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlWithholdingInacctDimensionVo> queryPageList(TGlWithholdingInacctDimensionQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlWithholdingInacctDimensionVo> queryList(TGlWithholdingInacctDimensionQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证预提转入科目维度控制
	 * @param bo 凭证预提转入科目维度控制新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlWithholdingInacctDimensionAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证预提转入科目维度控制
	 * @param bo 凭证预提转入科目维度控制编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlWithholdingInacctDimensionEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
