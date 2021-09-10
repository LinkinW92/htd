package com.skeqi.finance.controller.report;

import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.pojo.bo.report.DimensionBalanceQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryQueryBo;
import com.skeqi.finance.pojo.vo.report.DimensionBalanceVo;
import com.skeqi.finance.service.report.DimensionBalanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

/**
 * 核算维度余额表控制器
 * @date 2021-08-09
 */
@Api(value = "核算维度余额表控制器", tags = {"核算维度余额表"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/report/dimension")
public class DimensionBalanceController extends BaseController {

	private final DimensionBalanceService dimensionBalanceService;

	/**
	 * 查询核算维度余额列表
	 */
	@ApiOperation("查询核算维度余额列表")
	@PreAuthorize("@ss.hasPermi('finance:report:dimension:list')")
	@PostMapping("/list")
	public TableDataInfo<DimensionBalanceVo> list(@Validated @RequestBody DimensionBalanceQueryBo bo) {
		return dimensionBalanceService.queryPageList(bo);
	}


	/**
	 * 按科目内码id查询核算维度类别列表
	 */
	@ApiOperation("按科目内码id查询核算维度类别列表")
	@PreAuthorize("@ss.hasPermi('finance:report:dimension:dimensionList')")
	@PostMapping("/dimensionList/{fAccountId}")
	public AjaxResult dimensionList( @NotEmpty(message = "科目内码不能为空") @PathVariable Integer fAccountId) {
		return AjaxResult.success(dimensionBalanceService.dimensionList(fAccountId));
	}

	/**
	 * 按核算维度类别id查询核算维度编码列表
	 */
	@ApiOperation("按核算维度类别id查询核算维度编码列表")
	@PreAuthorize("@ss.hasPermi('finance:report:dimension:dimensionCodeList')")
	@PostMapping("/dimensionCodeList/{flexItemPropertyId}")
	public AjaxResult dimensionCodeList(@NotEmpty(message = "核算维度类别id不能为空") @PathVariable Integer flexItemPropertyId) {
		return AjaxResult.success(dimensionBalanceService.dimensionCodeList(flexItemPropertyId));
	}

	/**
	 * 查询核算维度明细账
	 */
	@ApiOperation("查询核算维度明细账")
	@PreAuthorize("@ss.hasPermi('finance:report:dimension:dimensionDetails')")
	@PostMapping("/dimensionList")
	public AjaxResult dimensionDetails(@Validated @RequestBody TGlVoucherEntryQueryBo bo) {
		return AjaxResult.success(dimensionBalanceService.queryDimensionDetails(bo));
	}
}
