package com.skeqi.finance.controller.cashflow;

import com.skeqi.common.annotation.Log;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.finance.domain.cashflow.TAccount;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherQueryBo;
import com.skeqi.finance.pojo.vo.cashflow.TGlVoucherEntryCashVo;
import com.skeqi.finance.service.cashflow.TAccountService;
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

@Api(value = "T型账控制器", tags = {"T型账控制器"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/cashFlow/tAccount")
public class TAccountController  extends BaseController {
	private final TAccountService accountService;
	/**
	 * 查询
	 */
	@ApiOperation("查询")
	@PreAuthorize("@ss.hasPermi('finance:cashFlow:tAccount:list')")
	@PostMapping("/list")
	public AjaxResult<List<Map<String,Object>>> list(@Validated @RequestBody TAccount tAccount) {
		return AjaxResult.success(accountService.selectAccountAndFlowProject(tAccount));
	}


	/**
	 * 修改主表项目
	 */
	@ApiOperation("修改主表项目")
	@PreAuthorize("@ss.hasPermi('finance:cashFlow:tAccount:edit')")
	@Log(title = "T型账", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/edit")
	public AjaxResult<Void> edit(@Validated @RequestBody Map<String,Object>  bo) {
		List<TGlVoucherEntryCashVo> tGlVoucherEntryCashVoList = (List<TGlVoucherEntryCashVo>) bo.get("list");
		return toAjax(accountService.updateByList(tGlVoucherEntryCashVoList) ? 1 : 0);
	}

}
