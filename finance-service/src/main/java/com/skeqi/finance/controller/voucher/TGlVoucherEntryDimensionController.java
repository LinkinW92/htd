package com.skeqi.finance.controller.voucher;

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
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherEntryDimensionVo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryDimensionQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryDimensionAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryDimensionEditBo;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherEntryDimensionService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证分录维度控制Controller
 *
 * @author toms
 * @date 2021-07-21
 */
@Api(value = "凭证分录维度控制控制器", tags = {"凭证分录维度控制管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/voucherEntryDimension")
public class TGlVoucherEntryDimensionController extends BaseController {

    private final ITGlVoucherEntryDimensionService iTGlVoucherEntryDimensionService;

    /**
     * 查询凭证分录维度控制列表
     */
    @ApiOperation("查询凭证分录维度控制列表")
    @PreAuthorize("@ss.hasPermi('finance:voucherEntryDimension:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlVoucherEntryDimensionVo> list(@Validated TGlVoucherEntryDimensionQueryBo bo) {
        return iTGlVoucherEntryDimensionService.queryPageList(bo);
    }

    /**
     * 导出凭证分录维度控制列表
     */
    @ApiOperation("导出凭证分录维度控制列表")
    @PreAuthorize("@ss.hasPermi('finance:voucherEntryDimension:export')")
    @Log(title = "凭证分录维度控制", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlVoucherEntryDimensionVo> export(@Validated TGlVoucherEntryDimensionQueryBo bo) {
        List<TGlVoucherEntryDimensionVo> list = iTGlVoucherEntryDimensionService.queryList(bo);
        ExcelUtil<TGlVoucherEntryDimensionVo> util = new ExcelUtil<TGlVoucherEntryDimensionVo>(TGlVoucherEntryDimensionVo.class);
        return util.exportExcel(list, "凭证分录维度控制");
    }

    /**
     * 获取凭证分录维度控制详细信息
     */
    @ApiOperation("获取凭证分录维度控制详细信息")
    @PreAuthorize("@ss.hasPermi('finance:voucherEntryDimension:query')")
    @PostMapping("/{id}")
    public AjaxResult<TGlVoucherEntryDimensionVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Integer id) {
        return AjaxResult.success(iTGlVoucherEntryDimensionService.queryById(id));
    }

    /**
     * 新增凭证分录维度控制
     */
    @ApiOperation("新增凭证分录维度控制")
    @PreAuthorize("@ss.hasPermi('finance:voucherEntryDimension:add')")
    @Log(title = "凭证分录维度控制", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlVoucherEntryDimensionAddBo bo) {
        return toAjax(iTGlVoucherEntryDimensionService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证分录维度控制
     */
    @ApiOperation("修改凭证分录维度控制")
    @PreAuthorize("@ss.hasPermi('finance:voucherEntryDimension:edit')")
    @Log(title = "凭证分录维度控制", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlVoucherEntryDimensionEditBo bo) {
        return toAjax(iTGlVoucherEntryDimensionService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证分录维度控制
     */
    @ApiOperation("删除凭证分录维度控制")
    @PreAuthorize("@ss.hasPermi('finance:voucherEntryDimension:remove')")
    @Log(title = "凭证分录维度控制" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] ids) {
        return toAjax(iTGlVoucherEntryDimensionService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
