package com.skeqi.finance.controller.endhandle.amortization;

import java.util.List;
import java.util.Arrays;

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
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortizationSchemeVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortizationSchemeQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortizationSchemeAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortizationSchemeEditBo;
import com.skeqi.finance.service.endhandle.ITGlAmortizationSchemeService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证摊销Controller
 *
 * @author toms
 * @date 2021-07-27
 */
@Api(value = "凭证摊销控制器", tags = {"凭证摊销管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/amortizationScheme")
public class TGlAmortizationSchemeController extends BaseController {

    private final ITGlAmortizationSchemeService iTGlAmortizationSchemeService;

    /**
     * 查询凭证摊销列表
     */
    @ApiOperation("查询凭证摊销列表")
    @PreAuthorize("@ss.hasPermi('finance:amortizationScheme:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlAmortizationSchemeVo> list(@Validated @RequestBody TGlAmortizationSchemeQueryBo bo) {
        return iTGlAmortizationSchemeService.queryPageList(bo);
    }

    /**
     * 导出凭证摊销列表
     */
    @ApiOperation("导出凭证摊销列表")
    @PreAuthorize("@ss.hasPermi('finance:amortizationScheme:export')")
    @Log(title = "凭证摊销", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlAmortizationSchemeVo> export(@Validated @RequestBody TGlAmortizationSchemeQueryBo bo) {
        List<TGlAmortizationSchemeVo> list = iTGlAmortizationSchemeService.queryList(bo);
        ExcelUtil<TGlAmortizationSchemeVo> util = new ExcelUtil<TGlAmortizationSchemeVo>(TGlAmortizationSchemeVo.class);
        return util.exportExcel(list, "凭证摊销");
    }

    /**
     * 获取凭证摊销详细信息
     */
    @ApiOperation("获取凭证摊销详细信息")
    @PreAuthorize("@ss.hasPermi('finance:amortizationScheme:query')")
    @GetMapping("/getInfo/{fSchemeId}")
    public AjaxResult<TGlAmortizationSchemeVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fSchemeId") Integer fSchemeId) {
        return AjaxResult.success(iTGlAmortizationSchemeService.queryById(fSchemeId));
    }

    /**
     * 新增凭证摊销
     */
    @ApiOperation("新增凭证摊销")
    @PreAuthorize("@ss.hasPermi('finance:amortizationScheme:add')")
    @Log(title = "凭证摊销", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TGlAmortizationSchemeAddBo bo) {
        return toAjax(iTGlAmortizationSchemeService.insertByAddBo(bo) ? 1 : 0);
    }

	/**
	 * 执行凭证摊销
	 */
	@ApiOperation("执行凭证摊销")
	@PreAuthorize("@ss.hasPermi('finance:amortizationScheme:edit')")
	@Log(title = "凭证摊销", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/execute/{fId}")
	public AjaxResult execute(@NotNull(message = "主键不能为空")
								  @PathVariable("fId") Integer fId){
		return iTGlAmortizationSchemeService.execute(fId);
	}

    /**
     * 修改凭证摊销
     */
    @ApiOperation("修改凭证摊销")
    @PreAuthorize("@ss.hasPermi('finance:amortizationScheme:edit')")
    @Log(title = "凭证摊销", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TGlAmortizationSchemeEditBo bo) {
        return toAjax(iTGlAmortizationSchemeService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证摊销
     */
    @ApiOperation("删除凭证摊销")
    @PreAuthorize("@ss.hasPermi('finance:amortizationScheme:remove')")
    @Log(title = "凭证摊销" , businessType = BusinessType.DELETE)
    @PostMapping("/remove/{fSchemeIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] fSchemeIds) {
        return toAjax(iTGlAmortizationSchemeService.deleteWithValidByIds(Arrays.asList(fSchemeIds), true) ? 1 : 0);
    }
}
