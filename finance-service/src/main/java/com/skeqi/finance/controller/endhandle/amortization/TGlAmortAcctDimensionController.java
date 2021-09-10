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
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortAcctDimensionVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctDimensionQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctDimensionAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctDimensionEditBo;
import com.skeqi.finance.service.endhandle.ITGlAmortAcctDimensionService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证摊销预提维度控制Controller
 *
 * @author toms
 * @date 2021-07-27
 */
@Api(value = "凭证摊销预提维度控制控制器", tags = {"凭证摊销预提维度控制管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/amortAcctDimension")
public class TGlAmortAcctDimensionController extends BaseController {

    private final ITGlAmortAcctDimensionService iTGlAmortAcctDimensionService;

    /**
     * 查询凭证摊销预提维度控制列表
     */
    @ApiOperation("查询凭证摊销预提维度控制列表")
    @PreAuthorize("@ss.hasPermi('finance:amortAcctDimension:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlAmortAcctDimensionVo> list(@Validated TGlAmortAcctDimensionQueryBo bo) {
        return iTGlAmortAcctDimensionService.queryPageList(bo);
    }

    /**
     * 导出凭证摊销预提维度控制列表
     */
    @ApiOperation("导出凭证摊销预提维度控制列表")
    @PreAuthorize("@ss.hasPermi('finance:amortAcctDimension:export')")
    @Log(title = "凭证摊销预提维度控制", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlAmortAcctDimensionVo> export(@Validated TGlAmortAcctDimensionQueryBo bo) {
        List<TGlAmortAcctDimensionVo> list = iTGlAmortAcctDimensionService.queryList(bo);
        ExcelUtil<TGlAmortAcctDimensionVo> util = new ExcelUtil<TGlAmortAcctDimensionVo>(TGlAmortAcctDimensionVo.class);
        return util.exportExcel(list, "凭证摊销预提维度控制");
    }

    /**
     * 获取凭证摊销预提维度控制详细信息
     */
    @ApiOperation("获取凭证摊销预提维度控制详细信息")
    @PreAuthorize("@ss.hasPermi('finance:amortAcctDimension:query')")
    @GetMapping("/{dimensionId}")
    public AjaxResult<TGlAmortAcctDimensionVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("dimensionId") Long dimensionId) {
        return AjaxResult.success(iTGlAmortAcctDimensionService.queryById(dimensionId));
    }

    /**
     * 新增凭证摊销预提维度控制
     */
    @ApiOperation("新增凭证摊销预提维度控制")
    @PreAuthorize("@ss.hasPermi('finance:amortAcctDimension:add')")
    @Log(title = "凭证摊销预提维度控制", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlAmortAcctDimensionAddBo bo) {
        return toAjax(iTGlAmortAcctDimensionService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证摊销预提维度控制
     */
    @ApiOperation("修改凭证摊销预提维度控制")
    @PreAuthorize("@ss.hasPermi('finance:amortAcctDimension:edit')")
    @Log(title = "凭证摊销预提维度控制", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlAmortAcctDimensionEditBo bo) {
        return toAjax(iTGlAmortAcctDimensionService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证摊销预提维度控制
     */
    @ApiOperation("删除凭证摊销预提维度控制")
    @PreAuthorize("@ss.hasPermi('finance:amortAcctDimension:remove')")
    @Log(title = "凭证摊销预提维度控制" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{dimensionIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] dimensionIds) {
        return toAjax(iTGlAmortAcctDimensionService.deleteWithValidByIds(Arrays.asList(dimensionIds), true) ? 1 : 0);
    }
}
