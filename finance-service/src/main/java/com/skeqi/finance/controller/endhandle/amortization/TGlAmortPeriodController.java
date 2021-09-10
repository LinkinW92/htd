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
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortPeriodVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortPeriodQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortPeriodAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortPeriodEditBo;
import com.skeqi.finance.service.endhandle.ITGlAmortPeriodService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证摊销-摊销期间Controller
 *
 * @author toms
 * @date 2021-07-27
 */
@Api(value = "凭证摊销-摊销期间控制器", tags = {"凭证摊销-摊销期间管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/amortPeriod")
public class TGlAmortPeriodController extends BaseController {

    private final ITGlAmortPeriodService iTGlAmortPeriodService;

    /**
     * 查询凭证摊销-摊销期间列表
     */
    @ApiOperation("查询凭证摊销-摊销期间列表")
    @PreAuthorize("@ss.hasPermi('finance:amortPeriod:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlAmortPeriodVo> list(@Validated TGlAmortPeriodQueryBo bo) {
        return iTGlAmortPeriodService.queryPageList(bo);
    }

    /**
     * 导出凭证摊销-摊销期间列表
     */
    @ApiOperation("导出凭证摊销-摊销期间列表")
    @PreAuthorize("@ss.hasPermi('finance:amortPeriod:export')")
    @Log(title = "凭证摊销-摊销期间", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlAmortPeriodVo> export(@Validated TGlAmortPeriodQueryBo bo) {
        List<TGlAmortPeriodVo> list = iTGlAmortPeriodService.queryList(bo);
        ExcelUtil<TGlAmortPeriodVo> util = new ExcelUtil<TGlAmortPeriodVo>(TGlAmortPeriodVo.class);
        return util.exportExcel(list, "凭证摊销-摊销期间");
    }

    /**
     * 获取凭证摊销-摊销期间详细信息
     */
    @ApiOperation("获取凭证摊销-摊销期间详细信息")
    @PreAuthorize("@ss.hasPermi('finance:amortPeriod:query')")
    @GetMapping("/{fSchemeId}")
    public AjaxResult<TGlAmortPeriodVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fSchemeId") Long fSchemeId) {
        return AjaxResult.success(iTGlAmortPeriodService.queryById(fSchemeId));
    }

    /**
     * 新增凭证摊销-摊销期间
     */
    @ApiOperation("新增凭证摊销-摊销期间")
    @PreAuthorize("@ss.hasPermi('finance:amortPeriod:add')")
    @Log(title = "凭证摊销-摊销期间", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlAmortPeriodAddBo bo) {
        return toAjax(iTGlAmortPeriodService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证摊销-摊销期间
     */
    @ApiOperation("修改凭证摊销-摊销期间")
    @PreAuthorize("@ss.hasPermi('finance:amortPeriod:edit')")
    @Log(title = "凭证摊销-摊销期间", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlAmortPeriodEditBo bo) {
        return toAjax(iTGlAmortPeriodService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证摊销-摊销期间
     */
    @ApiOperation("删除凭证摊销-摊销期间")
    @PreAuthorize("@ss.hasPermi('finance:amortPeriod:remove')")
    @Log(title = "凭证摊销-摊销期间" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fSchemeIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] fSchemeIds) {
        return toAjax(iTGlAmortPeriodService.deleteWithValidByIds(Arrays.asList(fSchemeIds), true) ? 1 : 0);
    }
}
