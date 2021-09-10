package com.skeqi.finance.service.cashflow;

import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.finance.domain.voucher.TGlVoucherEntryCash;
import com.skeqi.finance.pojo.vo.cashflow.TGlVoucherEntryCashVo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryCashQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryCashAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryCashEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证分录现金流量Service接口
 *
 * @author toms
 * @date 2021-07-21
 */
public interface ITGlVoucherEntryCashService extends IServicePlus<TGlVoucherEntryCash> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlVoucherEntryCashVo queryById(Integer id);

	/**
	 * 查询列表
	 * @return
	 */
	List<TGlVoucherEntryCashVo> queryByList(List<Integer> ids);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlVoucherEntryCashVo> queryPageList(TGlVoucherEntryCashQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlVoucherEntryCashVo> queryList(TGlVoucherEntryCashQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证分录现金流量
	 * @param bo 凭证分录现金流量新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlVoucherEntryCashAddBo bo);

	/**
	 * 批量新增维度
	 * @param list
	 * @return
	 */
	AjaxResult insertBatch(List<TGlVoucherEntryCash> list);
	/**
	 * 根据编辑业务对象修改凭证分录现金流量
	 * @param bo 凭证分录现金流量编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlVoucherEntryCashEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
