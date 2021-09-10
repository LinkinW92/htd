package com.skeqi.finance.controller.basicinformation.cashflow;

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyEditBo;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
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
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowVo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowEditBo;
import com.skeqi.finance.service.basicinformation.cashflow.ITGlCashFlowService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 现金流量项目-3Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "现金流量项目-3控制器", tags = {"现金流量项目-3管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/flow")
public class TGlCashFlowController extends BaseController {

    private final ITGlCashFlowService iTGlCashFlowService;

    /**
     * 查询现金流量项目-3列表
     */
    @ApiOperation("查询现金流量项目-3列表")
    @PreAuthorize("@ss.hasPermi('finance:flow:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlCashFlowVo> list(@Validated @RequestBody TGlCashFlowQueryBo bo) {
        return iTGlCashFlowService.queryPageList(bo);
    }

    /**
     * 导出现金流量项目-3列表
     */
    @ApiOperation("导出现金流量项目-3列表")
    @PreAuthorize("@ss.hasPermi('finance:flow:export')")
    @Log(title = "现金流量项目-3", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlCashFlowVo> export(@Validated TGlCashFlowQueryBo bo) {
        List<TGlCashFlowVo> list = iTGlCashFlowService.queryList(bo);
        ExcelUtil<TGlCashFlowVo> util = new ExcelUtil<TGlCashFlowVo>(TGlCashFlowVo.class);
        return util.exportExcel(list, "现金流量项目-3");
    }

    /**
     * 获取现金流量项目-3详细信息
     */
    @ApiOperation("获取现金流量项目-3详细信息")
    @PreAuthorize("@ss.hasPermi('finance:flow:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TGlCashFlowVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTGlCashFlowService.queryById(fId));
    }

    /**
     * 新增现金流量项目-3
     */
    @ApiOperation("新增现金流量项目-3")
    @PreAuthorize("@ss.hasPermi('finance:flow:add')")
    @Log(title = "现金流量项目-3", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Valid @RequestBody TGlCashFlowAddBo bo) {
        return toAjax(iTGlCashFlowService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改现金流量项目-3
     */
    @ApiOperation("修改现金流量项目-3")
    @PreAuthorize("@ss.hasPermi('finance:flow:edit')")
    @Log(title = "现金流量项目-3", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Valid @RequestBody TGlCashFlowEditBo bo) {
        return toAjax(iTGlCashFlowService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除现金流量项目-3
     */
    @ApiOperation("删除现金流量项目-3")
    @PreAuthorize("@ss.hasPermi('finance:flow:remove')")
    @Log(title = "现金流量项目-3" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTGlCashFlowService.deleteWithValidByIds(integerList) ? 1 : 0);
	}


	/**
	 * 审核现金流量项目-3
	 */
	@ApiOperation("审核现金流量项目-3")
	@PreAuthorize("@ss.hasPermi('finance:flow:audit')")
	@Log(title = "现金流量项目-3" , businessType = BusinessType.UPDATE)
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTGlCashFlowService.auditByIds(integerList) ? 1 : 0);
	}

	/**
	 * 反审核现金流量项目-3
	 */
	@ApiOperation("反审核现金流量项目-3")
	@PreAuthorize("@ss.hasPermi('finance:flow:antiAudit')")
	@Log(title = "现金流量项目-3" , businessType = BusinessType.UPDATE)
	@PostMapping("/antiAudit/{fIds}")
	public AjaxResult<Void> antiAudit(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTGlCashFlowService.antiAuditByIds(integerList) ? 1 : 0);
	}

	/**
	 * 禁用现金流量项目-3
	 */
	@ApiOperation("禁用现金流量项目-3")
	@PreAuthorize("@ss.hasPermi('finance:flow:disable')")
	@Log(title = "现金流量项目-3" , businessType = BusinessType.UPDATE)
	@PostMapping("/disable")
	public AjaxResult<Void> disable(@RequestBody TGlCashFlowEditBo bo) {
		return toAjax(iTGlCashFlowService.disableByEditBo(bo) ? 1 : 0);
	}
}
