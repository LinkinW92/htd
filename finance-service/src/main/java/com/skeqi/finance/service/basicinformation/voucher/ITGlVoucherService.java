package com.skeqi.finance.service.basicinformation.voucher;

import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.finance.domain.voucher.TGlVoucher;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceQueryBo;
import com.skeqi.finance.pojo.bo.cashflow.TGlVoucherCashFlowBo;
import com.skeqi.finance.pojo.bo.voucher.*;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherVo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.pojo.vo.voucher.VoucherPageVo;
import com.skeqi.finance.pojo.vo.voucher.VoucherVo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证录入主Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITGlVoucherService extends IServicePlus<TGlVoucher> {
	/**
	 * 查询单个
	 * @return
	 */
	VoucherVo queryById(Integer fVoucherId);

	/**
	 * 查询列表
	 */
    TableDataInfo<VoucherPageVo> queryPageList(TGlVoucherQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlVoucherVo> queryList(TGlVoucherQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证录入主
	 * @param bo 凭证录入主新增业务对象
	 * @return
	 */
	AjaxResult insertByAddBo(TGlVoucherAddBo bo);

	/**
	 * 指定现金流量
	 * @param list
	 * @return
	 */
	AjaxResult assignCashFlow(List<TGlVoucherCashFlowBo> list);

	/**
	 * 获取现金流量
	 * @param id
	 * @return
	 */
	AjaxResult getCashFlow(Integer id);

	/**
	 * 自动指定现金流量
	 * @param id
	 * @return
	 */
	AjaxResult autoAssign(Integer id);
	/**
	 * 根据编辑业务对象修改凭证录入主
	 * @param bo 凭证录入主编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlVoucherEditBo bo);

	/**
	 * 审核
	 * @param fId
	 * @return
	 */
	AjaxResult audit(Integer fId);

	/**
	 * 反审核
	 * @param fId
	 * @return
	 */
	AjaxResult auditNo(Integer fId);

	/**
	 * 保存
	 * @param vId
	 * @return
	 */
	AjaxResult save(Integer vId);

	/**
	 * 作废
	 * @param fId
	 * @return
	 */
	AjaxResult invalid(Integer fId);

	/**
	 * 凭证过账
	 * @param ids
	 * @return
	 */
	AjaxResult voucherPost(List<Integer> ids);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);

	/**
	 * 查询明细帐
	 * @param bo
	 * @return
	 */
	AjaxResult listDetail(BalanceVchDetailQueryBo bo);
}
