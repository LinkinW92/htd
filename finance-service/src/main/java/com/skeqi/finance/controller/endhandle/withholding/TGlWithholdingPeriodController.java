package com.skeqi.finance.controller.endhandle.withholding;

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
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingPeriodVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingPeriodQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingPeriodAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingPeriodEditBo;
import com.skeqi.finance.service.endhandle.ITGlWithholdingPeriodService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证预提-预提期间Controller
 *
 * @author toms
 * @date 2021-07-27
 */
@Api(value = "凭证预提-预提期间控制器", tags = {"凭证预提-预提期间管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/withholdingPeriod")
public class TGlWithholdingPeriodController extends BaseController {

    private final ITGlWithholdingPeriodService iTGlWithholdingPeriodService;

    /**
     * 查询凭证预提-预提期间列表
     */
    @ApiOperation("查询凭证预提-预提期间列表")
    @PreAuthorize("@ss.hasPermi('finance:withholdingPeriod:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlWithholdingPeriodVo> list(@Validated TGlWithholdingPeriodQueryBo bo) {
        return iTGlWithholdingPeriodService.queryPageList(bo);
    }

    /**
     * 导出凭证预提-预提期间列表
     */
    @ApiOperation("导出凭证预提-预提期间列表")
    @PreAuthorize("@ss.hasPermi('finance:withholdingPeriod:export')")
    @Log(title = "凭证预提-预提期间", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlWithholdingPeriodVo> export(@Validated TGlWithholdingPeriodQueryBo bo) {
        List<TGlWithholdingPeriodVo> list = iTGlWithholdingPeriodService.queryList(bo);
        ExcelUtil<TGlWithholdingPeriodVo> util = new ExcelUtil<TGlWithholdingPeriodVo>(TGlWithholdingPeriodVo.class);
        return util.exportExcel(list, "凭证预提-预提期间");
    }

    /**
     * 获取凭证预提-预提期间详细信息
     */
    @ApiOperation("获取凭证预提-预提期间详细信息")
    @PreAuthorize("@ss.hasPermi('finance:withholdingPeriod:query')")
    @GetMapping("/{fYear}")
    public AjaxResult<TGlWithholdingPeriodVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fYear") Long fYear) {
        return AjaxResult.success(iTGlWithholdingPeriodService.queryById(fYear));
    }

    /**
     * 新增凭证预提-预提期间
     */
    @ApiOperation("新增凭证预提-预提期间")
    @PreAuthorize("@ss.hasPermi('finance:withholdingPeriod:add')")
    @Log(title = "凭证预提-预提期间", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlWithholdingPeriodAddBo bo) {
        return toAjax(iTGlWithholdingPeriodService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证预提-预提期间
     */
    @ApiOperation("修改凭证预提-预提期间")
    @PreAuthorize("@ss.hasPermi('finance:withholdingPeriod:edit')")
    @Log(title = "凭证预提-预提期间", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlWithholdingPeriodEditBo bo) {
        return toAjax(iTGlWithholdingPeriodService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证预提-预提期间
     */
    @ApiOperation("删除凭证预提-预提期间")
    @PreAuthorize("@ss.hasPermi('finance:withholdingPeriod:remove')")
    @Log(title = "凭证预提-预提期间" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fYears}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] fYears) {
        return toAjax(iTGlWithholdingPeriodService.deleteWithValidByIds(Arrays.asList(fYears), true) ? 1 : 0);
    }
}
