package com.skeqi.finance.service.report;

import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.domain.TGlBalance;
import com.skeqi.finance.pojo.bo.ABFlexItemPropertyDetailQueryBo;
import com.skeqi.finance.pojo.bo.ABTotalBalanceQueryBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceAddBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceEditBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceQueryBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceVoucherBo;
import com.skeqi.finance.pojo.vo.ABTotalBalanceVo;
import com.skeqi.finance.pojo.vo.FlexItemPropertyDetailVo;
import com.skeqi.finance.pojo.vo.TGlBalanceVo;

import java.util.Collection;
import java.util.List;

/**
 * 科目余额Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITGlBalanceService extends IServicePlus<TGlBalance> {
	/**
	 * 查询单个
	 *
	 * @return
	 */
	TGlBalanceVo queryById(Integer fAccountBookId);

	/**
	 * 查询列表
	 */
	TableDataInfo<TGlBalanceVo> queryPageList(TGlBalanceQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlBalanceVo> queryList(TGlBalanceQueryBo bo);

	/**
	 * 根据新增业务对象插入科目余额
	 *
	 * @param bo 科目余额新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlBalanceAddBo bo);

	/**
	 * 根据编辑业务对象修改科目余额
	 *
	 * @param bo 科目余额编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlBalanceEditBo bo);

	/**
	 * 校验并删除数据
	 *
	 * @param ids     主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);


	/**
	 * 更新凭证科目余额
	 *
	 * @param bo
	 * @return
	 */
	AjaxResult updateVoucherBalance(TGlBalanceVoucherBo bo);


	/**
	 * 查询总分类账
	 *
	 * @param bo
	 * @return
	 */
	TableDataInfo<ABTotalBalanceVo> queryABTotalBalance(ABTotalBalanceQueryBo bo);

	/**
	 * 查询核算维度明细账
	 * @param bo
	 * @return
	 */
	TableDataInfo<FlexItemPropertyDetailVo> queryFlexItemPropertyDetail(ABFlexItemPropertyDetailQueryBo bo);
}
