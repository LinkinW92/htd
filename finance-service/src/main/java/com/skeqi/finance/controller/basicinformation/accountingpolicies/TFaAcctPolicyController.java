package com.skeqi.finance.controller.basicinformation.accountingpolicies;

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemEditBo;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.annotation.Log;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyVo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyEditBo;
import com.skeqi.finance.service.basicinformation.accountingpolicies.ITFaAcctPolicyService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会计政策Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "会计政策控制器", tags = {"会计政策管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/acctPolicy")
public class TFaAcctPolicyController extends BaseController {

    private final ITFaAcctPolicyService iTFaAcctPolicyService;

    /**
     * 查询会计政策列表
     */
    @ApiOperation("查询会计政策列表")
    @PreAuthorize("@ss.hasPermi('finance:acctPolicy:list')")
    @PostMapping("/list")
    public TableDataInfo<TFaAcctPolicyVo> list(@Validated @RequestBody TFaAcctPolicyQueryBo bo) {
        return iTFaAcctPolicyService.queryPageList(bo);
    }

    /**
     * 导出会计政策列表
     */
    @ApiOperation("导出会计政策列表")
    @PreAuthorize("@ss.hasPermi('finance:acctPolicy:export')")
    @Log(title = "会计政策", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TFaAcctPolicyVo> export(@Validated TFaAcctPolicyQueryBo bo) {
        List<TFaAcctPolicyVo> list = iTFaAcctPolicyService.queryList(bo);
        ExcelUtil<TFaAcctPolicyVo> util = new ExcelUtil<TFaAcctPolicyVo>(TFaAcctPolicyVo.class);
        return util.exportExcel(list, "会计政策");
    }

    /**
     * 获取会计政策详细信息
     */
    @ApiOperation("获取会计政策详细信息")
    @PreAuthorize("@ss.hasPermi('finance:acctPolicy:query')")
    @PostMapping("/{fAcctpolicyId}")
    public AjaxResult<TFaAcctPolicyVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fAcctpolicyId") Integer fAcctpolicyId) {
        return AjaxResult.success(iTFaAcctPolicyService.queryById(fAcctpolicyId));
    }

    /**
     * 新增会计政策
     */
    @ApiOperation("新增会计政策")
    @PreAuthorize("@ss.hasPermi('finance:acctPolicy:add')")
    @Log(title = "会计政策", businessType = BusinessType.INSERT)
    @RepeatSubmit
	@PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TFaAcctPolicyAddBo bo) {
        return toAjax(iTFaAcctPolicyService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改会计政策
     */
    @ApiOperation("修改会计政策")
    @PreAuthorize("@ss.hasPermi('finance:acctPolicy:edit')")
    @Log(title = "会计政策", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TFaAcctPolicyEditBo bo) {
        return toAjax(iTFaAcctPolicyService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除会计政策
     */
    @ApiOperation("删除会计政策")
    @PreAuthorize("@ss.hasPermi('finance:acctPolicy:remove')")
    @Log(title = "会计政策" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTFaAcctPolicyService.deleteWithValidByIds(integerList) ? 1 : 0);
	}


	/**
	 * 审核会计政策
	 */
	@ApiOperation("审核会计政策")
	@PreAuthorize("@ss.hasPermi('finance:acctPolicy:audit')")
	@Log(title = "会计政策" , businessType = BusinessType.UPDATE)
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTFaAcctPolicyService.auditByIds(integerList) ? 1 : 0);
	}

	/**
	 * 反审核会计政策
	 */
	@ApiOperation("反审核会计政策")
	@PreAuthorize("@ss.hasPermi('finance:acctPolicy:antiAudit')")
	@Log(title = "会计政策" , businessType = BusinessType.UPDATE)
	@PostMapping("/antiAudit/{fIds}")
	public AjaxResult<Void> antiAudit(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTFaAcctPolicyService.antiAuditByIds(integerList) ? 1 : 0);
	}



	/**
	 * 禁用会计政策
	 */
	@ApiOperation("禁用会计核算体系")
	@PreAuthorize("@ss.hasPermi('finance:acctPolicy:disable')")
	@Log(title = "会计政策" , businessType = BusinessType.UPDATE)
	@PostMapping("/disable")
	public AjaxResult<Void> disable(@RequestBody TFaAcctPolicyEditBo bo) {
		return toAjax(iTFaAcctPolicyService.disableByEditBo(bo) ? 1 : 0);
	}
}
