package com.skeqi.finance.controller.cashflow;

import com.skeqi.common.annotation.Log;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountEditBo;
import com.skeqi.finance.service.account.ITBdAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "项目批量预设控制器", tags = {"项目批量预设控制器"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/cashFlow/projectBatchPreset")
public class ProjectBatchPresetController extends BaseController {

	private final ITBdAccountService iTBdAccountService;

	/**
	 * 修改项目批量预设信息
	 */
	@ApiOperation("修改项目批量预设信息")
	@PreAuthorize("@ss.hasPermi('finance:cashFlow:projectBatchPreset:edit')")
	@Log(title = "项目批量预设信息", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/edit")
	public AjaxResult<Void> edit(@Validated @RequestBody Map<String,Object> map) {
		List<TBdAccountEditBo> list = (List<TBdAccountEditBo>) map.get("list");
		return toAjax(iTBdAccountService.updateByList(list) ? 1 : 0);
	}
}
