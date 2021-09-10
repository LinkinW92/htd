package com.skeqi.finance.service.basicinformation.voucher;

import com.skeqi.finance.domain.voucher.TGlVoucherEntryDimension;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherEntryDimensionVo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryDimensionQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryDimensionAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryDimensionEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证分录维度控制Service接口
 *
 * @author toms
 * @date 2021-07-21
 */
public interface ITGlVoucherEntryDimensionService extends IServicePlus<TGlVoucherEntryDimension> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlVoucherEntryDimensionVo queryById(Integer id);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlVoucherEntryDimensionVo> queryPageList(TGlVoucherEntryDimensionQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlVoucherEntryDimensionVo> queryList(TGlVoucherEntryDimensionQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证分录维度控制
	 * @param bo 凭证分录维度控制新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlVoucherEntryDimensionAddBo bo);

	Boolean insertBatch(List<TGlVoucherEntryDimension> list);

	/**
	 * 根据编辑业务对象修改凭证分录维度控制
	 * @param bo 凭证分录维度控制编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlVoucherEntryDimensionEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
