package com.skeqi.finance.service.cashflow;

import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.finance.domain.cashflow.TAccount;
import com.skeqi.finance.pojo.vo.cashflow.TGlVoucherEntryCashVo;

import java.util.List;
import java.util.Map;

/**
 * T型账Service接口
 *
 * @author toms
 * @date 2021-07-21
 */
public interface TAccountService extends IServicePlus<TAccount> {
	List<Map<String,Object>> selectAccountAndFlowProject(TAccount bo);

	Boolean updateByList(List<TGlVoucherEntryCashVo> tGlVoucherEntryCashVoList);
}
