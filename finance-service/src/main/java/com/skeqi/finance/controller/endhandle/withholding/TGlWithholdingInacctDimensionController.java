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
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingInacctDimensionVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctDimensionQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctDimensionAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctDimensionEditBo;
import com.skeqi.finance.service.endhandle.ITGlWithholdingInacctDimensionService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证预提转入科目维度控制Controller
 *
 * @author toms
 * @date 2021-07-27
 */
@Api(value = "凭证预提转入科目维度控制控制器", tags = {"凭证预提转入科目维度控制管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/withholdingInacctDimension")
public class TGlWithholdingInacctDimensionController extends BaseController {

    private final ITGlWithholdingInacctDimensionService iTGlWithholdingInacctDimensionService;

    /**
     * 查询凭证预提转入科目维度控制列表
     */
    @ApiOperation("查询凭证预提转入科目维度控制列表")
    @PreAuthorize("@ss.hasPermi('finance:withholdingInacctDimension:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlWithholdingInacctDimensionVo> list(@Validated TGlWithholdingInacctDimensionQueryBo bo) {
        return iTGlWithholdingInacctDimensionService.queryPageList(bo);
    }

    /**
     * 导出凭证预提转入科目维度控制列表
     */
    @ApiOperation("导出凭证预提转入科目维度控制列表")
    @PreAuthorize("@ss.hasPermi('finance:withholdingInacctDimension:export')")
    @Log(title = "凭证预提转入科目维度控制", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlWithholdingInacctDimensionVo> export(@Validated TGlWithholdingInacctDimensionQueryBo bo) {
        List<TGlWithholdingInacctDimensionVo> list = iTGlWithholdingInacctDimensionService.queryList(bo);
        ExcelUtil<TGlWithholdingInacctDimensionVo> util = new ExcelUtil<TGlWithholdingInacctDimensionVo>(TGlWithholdingInacctDimensionVo.class);
        return util.exportExcel(list, "凭证预提转入科目维度控制");
    }

    /**
     * 获取凭证预提转入科目维度控制详细信息
     */
    @ApiOperation("获取凭证预提转入科目维度控制详细信息")
    @PreAuthorize("@ss.hasPermi('finance:withholdingInacctDimension:query')")
    @GetMapping("/{dimensionId}")
    public AjaxResult<TGlWithholdingInacctDimensionVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("dimensionId") Long dimensionId) {
        return AjaxResult.success(iTGlWithholdingInacctDimensionService.queryById(dimensionId));
    }

    /**
     * 新增凭证预提转入科目维度控制
     */
    @ApiOperation("新增凭证预提转入科目维度控制")
    @PreAuthorize("@ss.hasPermi('finance:withholdingInacctDimension:add')")
    @Log(title = "凭证预提转入科目维度控制", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlWithholdingInacctDimensionAddBo bo) {
        return toAjax(iTGlWithholdingInacctDimensionService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证预提转入科目维度控制
     */
    @ApiOperation("修改凭证预提转入科目维度控制")
    @PreAuthorize("@ss.hasPermi('finance:withholdingInacctDimension:edit')")
    @Log(title = "凭证预提转入科目维度控制", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlWithholdingInacctDimensionEditBo bo) {
        return toAjax(iTGlWithholdingInacctDimensionService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证预提转入科目维度控制
     */
    @ApiOperation("删除凭证预提转入科目维度控制")
    @PreAuthorize("@ss.hasPermi('finance:withholdingInacctDimension:remove')")
    @Log(title = "凭证预提转入科目维度控制" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{dimensionIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] dimensionIds) {
        return toAjax(iTGlWithholdingInacctDimensionService.deleteWithValidByIds(Arrays.asList(dimensionIds), true) ? 1 : 0);
    }
}
