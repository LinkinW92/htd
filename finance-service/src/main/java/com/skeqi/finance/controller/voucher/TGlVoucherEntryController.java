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
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherEntryVo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryEditBo;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherEntryService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证录入分Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "凭证录入分控制器", tags = {"凭证录入分管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/voucherEntry")
public class TGlVoucherEntryController extends BaseController {

    private final ITGlVoucherEntryService iTGlVoucherEntryService;

    /**
     * 查询凭证录入分列表
     */
    @ApiOperation("查询凭证录入分列表")
    @PreAuthorize("@ss.hasPermi('finance:entry:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlVoucherEntryVo> list(@Validated TGlVoucherEntryQueryBo bo) {
        return iTGlVoucherEntryService.queryPageList(bo);
    }

    /**
     * 导出凭证录入分列表
     */
    @ApiOperation("导出凭证录入分列表")
    @PreAuthorize("@ss.hasPermi('finance:entry:export')")
    @Log(title = "凭证录入分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlVoucherEntryVo> export(@Validated TGlVoucherEntryQueryBo bo) {
        List<TGlVoucherEntryVo> list = iTGlVoucherEntryService.queryList(bo);
        ExcelUtil<TGlVoucherEntryVo> util = new ExcelUtil<TGlVoucherEntryVo>(TGlVoucherEntryVo.class);
        return util.exportExcel(list, "凭证录入分");
    }

    /**
     * 获取凭证录入分详细信息
     */
    @ApiOperation("获取凭证录入分详细信息")
    @PreAuthorize("@ss.hasPermi('finance:entry:query')")
    @PostMapping("/{fEntryId}")
    public AjaxResult<TGlVoucherEntryVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fEntryId") Integer fEntryId) {
        return AjaxResult.success(iTGlVoucherEntryService.queryById(fEntryId));
    }

    /**
     * 新增凭证录入分
     */
    @ApiOperation("新增凭证录入分")
    @PreAuthorize("@ss.hasPermi('finance:entry:add')")
    @Log(title = "凭证录入分", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlVoucherEntryAddBo bo) {
        return toAjax( iTGlVoucherEntryService.insertByAddBo(bo));
    }

    /**
     * 修改凭证录入分
     */
    @ApiOperation("修改凭证录入分")
    @PreAuthorize("@ss.hasPermi('finance:entry:edit')")
    @Log(title = "凭证录入分", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlVoucherEntryEditBo bo) {
        return toAjax(iTGlVoucherEntryService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证录入分
     */
    @ApiOperation("删除凭证录入分")
    @PreAuthorize("@ss.hasPermi('finance:entry:remove')")
    @Log(title = "凭证录入分" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fEntryIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fEntryIds) {
        return toAjax(iTGlVoucherEntryService.deleteWithValidByIds(Arrays.asList(fEntryIds), true) ? 1 : 0);
    }
}
