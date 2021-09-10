package com.skeqi.finance.service.init;

import com.skeqi.finance.domain.init.TGlInitCashflow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowTypeVo;

import java.util.List;

public interface TGlInitCashflowService extends IService<TGlInitCashflow>{

	List<TGlCashFlowTypeVo> queryCashFlow();

	Boolean insertByList(List<TGlInitCashflow> tGlVoucherEntryCashVoList);

	List<TGlInitCashflow> queryInitCashFlow(TGlInitCashflow initCashflow);
}
