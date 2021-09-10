package com.skeqi.finance.controller.endhandle.exchange;

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
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeFlexEntryVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeFlexEntryQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeFlexEntryAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeFlexEntryEditBo;
import com.skeqi.finance.service.endhandle.ITGlExchangeFlexEntryService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 期末调汇核算维度分录Controller
 *
 * @author toms
 * @date 2021-07-30
 */
@Api(value = "期末调汇核算维度分录控制器", tags = {"期末调汇核算维度分录管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/exchangeFlexEntry")
public class TGlExchangeFlexEntryController extends BaseController {

    private final ITGlExchangeFlexEntryService iTGlExchangeFlexEntryService;

    /**
     * 查询期末调汇核算维度分录列表
     */
    @ApiOperation("查询期末调汇核算维度分录列表")
    @PreAuthorize("@ss.hasPermi('finance:exchangeFlexEntry:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlExchangeFlexEntryVo> list(@Validated TGlExchangeFlexEntryQueryBo bo) {
        return iTGlExchangeFlexEntryService.queryPageList(bo);
    }

    /**
     * 导出期末调汇核算维度分录列表
     */
    @ApiOperation("导出期末调汇核算维度分录列表")
    @PreAuthorize("@ss.hasPermi('finance:exchangeFlexEntry:export')")
    @Log(title = "期末调汇核算维度分录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlExchangeFlexEntryVo> export(@Validated TGlExchangeFlexEntryQueryBo bo) {
        List<TGlExchangeFlexEntryVo> list = iTGlExchangeFlexEntryService.queryList(bo);
        ExcelUtil<TGlExchangeFlexEntryVo> util = new ExcelUtil<TGlExchangeFlexEntryVo>(TGlExchangeFlexEntryVo.class);
        return util.exportExcel(list, "期末调汇核算维度分录");
    }

    /**
     * 获取期末调汇核算维度分录详细信息
     */
    @ApiOperation("获取期末调汇核算维度分录详细信息")
    @PreAuthorize("@ss.hasPermi('finance:exchangeFlexEntry:query')")
    @GetMapping("/{fEntryId}")
    public AjaxResult<TGlExchangeFlexEntryVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fEntryId") Integer fEntryId) {
        return AjaxResult.success(iTGlExchangeFlexEntryService.queryById(fEntryId));
    }

    /**
     * 新增期末调汇核算维度分录
     */
    @ApiOperation("新增期末调汇核算维度分录")
    @PreAuthorize("@ss.hasPermi('finance:exchangeFlexEntry:add')")
    @Log(title = "期末调汇核算维度分录", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlExchangeFlexEntryAddBo bo) {
        return toAjax(iTGlExchangeFlexEntryService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改期末调汇核算维度分录
     */
    @ApiOperation("修改期末调汇核算维度分录")
    @PreAuthorize("@ss.hasPermi('finance:exchangeFlexEntry:edit')")
    @Log(title = "期末调汇核算维度分录", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlExchangeFlexEntryEditBo bo) {
        return toAjax(iTGlExchangeFlexEntryService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除期末调汇核算维度分录
     */
    @ApiOperation("删除期末调汇核算维度分录")
    @PreAuthorize("@ss.hasPermi('finance:exchangeFlexEntry:remove')")
    @Log(title = "期末调汇核算维度分录" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fEntryIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fEntryIds) {
        return toAjax(iTGlExchangeFlexEntryService.deleteWithValidByIds(Arrays.asList(fEntryIds), true) ? 1 : 0);
    }
}
