package com.skeqi.finance.controller.init;

import com.skeqi.common.annotation.Log;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.finance.domain.init.TGlInitCashflow;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowTypeVo;
import com.skeqi.finance.service.init.TGlInitCashflowService;
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

import java.util.List;
import java.util.Map;

@Api(value = "初始化现金流量", tags = {"初始化现金流量"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/init/cashFlow")
public class TGlInitCashFlowController extends BaseController {

	private final TGlInitCashflowService initCashflowService;

	/**
	 * 查询现金流量项目信息
	 */
	@ApiOperation("查询现金流量项目信息")
	@PreAuthorize("@ss.hasPermi('finance:init:cashFlow:queryCashFlow')")
	@PostMapping("/queryCashFlow")
	public AjaxResult<List<TGlCashFlowTypeVo>> queryCashFlow() {
		List<TGlCashFlowTypeVo> list = initCashflowService.queryCashFlow();
		return AjaxResult.success(list);
	}

	/**
	 * 查询初始化余额信息
	 */
	@ApiOperation("查询初始化余额信息")
	@PreAuthorize("@ss.hasPermi('finance:init:cashFlow:queryInitCashFlow')")
	@PostMapping("/queryInitCashFlow")
	public AjaxResult<List<TGlInitCashflow>> queryInitCashFlow(@Validated @RequestBody TGlInitCashflow initCashflow) {
		List<TGlInitCashflow> list = initCashflowService.queryInitCashFlow(initCashflow);
		return AjaxResult.success(list);
	}

	/**
	 * 保存现金流量余额
	 */
	@ApiOperation("保存现金流量余额")
	@PreAuthorize("@ss.hasPermi('finance:cashFlow:tAccount:edit')")
	@Log(title = "初始化现金流量", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/add")
	public AjaxResult<Void> edit(@Validated @RequestBody Map<String,Object> bo) {
		List<TGlInitCashflow> tGlInitCashflows = (List<TGlInitCashflow>) bo.get("list");
		return toAjax(initCashflowService.insertByList(tGlInitCashflows) ? 1 : 0);
	}

}
