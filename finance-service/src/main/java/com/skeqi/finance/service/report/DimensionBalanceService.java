package com.skeqi.finance.service.report;

import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.domain.report.DimensionBalance;
import com.skeqi.finance.pojo.bo.report.DimensionBalanceQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpDataVo;
import com.skeqi.finance.pojo.vo.dimension.TBdFlexItemPropertyVo;
import com.skeqi.finance.pojo.vo.report.DimensionBalanceVo;

import java.util.List;
import java.util.Map;

/**
 * 核算维度余额表Service接口
 *
 * @author toms
 * @date 2021-08-09
 */
public interface DimensionBalanceService extends IServicePlus<DimensionBalance> {
	/**
	 * 查询核算维度余额列表
	 */
    TableDataInfo<DimensionBalanceVo> queryPageList(DimensionBalanceQueryBo bo);

	/**
	 * 按科目内码id查询核算维度类别列表
	 * @param fAccountId
	 * @return
	 */
	List<TBdFlexItemPropertyVo> dimensionList(Integer fAccountId);


	/**
	 * 查询核算维度明细账
	 * @param bo
	 * @return
	 */
	List<Map<String,Object>> queryDimensionDetails(TGlVoucherEntryQueryBo bo);

	/**
	 * 按核算维度类别id查询核算维度编码列表
	 * @param flexItemPropertyId
	 * @return
	 */
	List<TBdHelpDataVo> dimensionCodeList(Integer flexItemPropertyId);


}
