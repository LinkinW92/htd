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
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingAcctDimensionVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctDimensionQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctDimensionAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctDimensionEditBo;
import com.skeqi.finance.service.endhandle.ITGlWithholdingAcctDimensionService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证预提科目维度控制Controller
 *
 * @author toms
 * @date 2021-07-27
 */
@Api(value = "凭证预提科目维度控制控制器", tags = {"凭证预提科目维度控制管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/withholdingAcctDimension")
public class TGlWithholdingAcctDimensionController extends BaseController {

    private final ITGlWithholdingAcctDimensionService iTGlWithholdingAcctDimensionService;

    /**
     * 查询凭证预提科目维度控制列表
     */
    @ApiOperation("查询凭证预提科目维度控制列表")
    @PreAuthorize("@ss.hasPermi('finance:withholdingAcctDimension:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlWithholdingAcctDimensionVo> list(@Validated TGlWithholdingAcctDimensionQueryBo bo) {
        return iTGlWithholdingAcctDimensionService.queryPageList(bo);
    }

    /**
     * 导出凭证预提科目维度控制列表
     */
    @ApiOperation("导出凭证预提科目维度控制列表")
    @PreAuthorize("@ss.hasPermi('finance:withholdingAcctDimension:export')")
    @Log(title = "凭证预提科目维度控制", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlWithholdingAcctDimensionVo> export(@Validated TGlWithholdingAcctDimensionQueryBo bo) {
        List<TGlWithholdingAcctDimensionVo> list = iTGlWithholdingAcctDimensionService.queryList(bo);
        ExcelUtil<TGlWithholdingAcctDimensionVo> util = new ExcelUtil<TGlWithholdingAcctDimensionVo>(TGlWithholdingAcctDimensionVo.class);
        return util.exportExcel(list, "凭证预提科目维度控制");
    }

    /**
     * 获取凭证预提科目维度控制详细信息
     */
    @ApiOperation("获取凭证预提科目维度控制详细信息")
    @PreAuthorize("@ss.hasPermi('finance:withholdingAcctDimension:query')")
    @GetMapping("/{dimensionId}")
    public AjaxResult<TGlWithholdingAcctDimensionVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("dimensionId") Long dimensionId) {
        return AjaxResult.success(iTGlWithholdingAcctDimensionService.queryById(dimensionId));
    }

    /**
     * 新增凭证预提科目维度控制
     */
    @ApiOperation("新增凭证预提科目维度控制")
    @PreAuthorize("@ss.hasPermi('finance:withholdingAcctDimension:add')")
    @Log(title = "凭证预提科目维度控制", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlWithholdingAcctDimensionAddBo bo) {
        return toAjax(iTGlWithholdingAcctDimensionService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证预提科目维度控制
     */
    @ApiOperation("修改凭证预提科目维度控制")
    @PreAuthorize("@ss.hasPermi('finance:withholdingAcctDimension:edit')")
    @Log(title = "凭证预提科目维度控制", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlWithholdingAcctDimensionEditBo bo) {
        return toAjax(iTGlWithholdingAcctDimensionService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证预提科目维度控制
     */
    @ApiOperation("删除凭证预提科目维度控制")
    @PreAuthorize("@ss.hasPermi('finance:withholdingAcctDimension:remove')")
    @Log(title = "凭证预提科目维度控制" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{dimensionIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] dimensionIds) {
        return toAjax(iTGlWithholdingAcctDimensionService.deleteWithValidByIds(Arrays.asList(dimensionIds), true) ? 1 : 0);
    }
}
