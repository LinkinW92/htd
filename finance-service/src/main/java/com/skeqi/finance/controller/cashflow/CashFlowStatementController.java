package com.skeqi.finance.controller.cashflow;

import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowTypeQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowTypeVo;
import com.skeqi.finance.service.basicinformation.cashflow.ITGlCashFlowTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "现金流量表控制器", tags = {"现金流量表控制器"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/cashFlow/cashFlowStatement")
public class CashFlowStatementController extends BaseController {

	private final ITGlCashFlowTypeService iTGlCashFlowTypeService;


	/**
	 * 查询现金流量表
	 */
	@ApiOperation("查询现金流量表")
	@PreAuthorize("@ss.hasPermi('finance:cashFlow:cashFlowStatement:queryCashFlowStatement')")
	@PostMapping("/queryCashFlowStatement")
	public TableDataInfo<TGlCashFlowTypeVo> queryCashFlowStatement(@Validated @RequestBody TGlCashFlowTypeQueryBo bo) {
		return iTGlCashFlowTypeService.queryPageList(bo);
	}
}
