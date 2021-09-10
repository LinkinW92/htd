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
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeSchemeEntryVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeEntryQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeEntryAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeEntryEditBo;
import com.skeqi.finance.service.endhandle.ITGlExchangeSchemeEntryService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 期末调汇方案分录Controller
 *
 * @author toms
 * @date 2021-07-30
 */
@Api(value = "期末调汇方案分录控制器", tags = {"期末调汇方案分录管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/exchangeSchemeEntry")
public class TGlExchangeSchemeEntryController extends BaseController {

    private final ITGlExchangeSchemeEntryService iTGlExchangeSchemeEntryService;

    /**
     * 查询期末调汇方案分录列表
     */
    @ApiOperation("查询期末调汇方案分录列表")
    @PreAuthorize("@ss.hasPermi('finance:exchangeSchemeEntry:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlExchangeSchemeEntryVo> list(@Validated TGlExchangeSchemeEntryQueryBo bo) {
        return iTGlExchangeSchemeEntryService.queryPageList(bo);
    }

    /**
     * 导出期末调汇方案分录列表
     */
    @ApiOperation("导出期末调汇方案分录列表")
    @PreAuthorize("@ss.hasPermi('finance:exchangeSchemeEntry:export')")
    @Log(title = "期末调汇方案分录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlExchangeSchemeEntryVo> export(@Validated TGlExchangeSchemeEntryQueryBo bo) {
        List<TGlExchangeSchemeEntryVo> list = iTGlExchangeSchemeEntryService.queryList(bo);
        ExcelUtil<TGlExchangeSchemeEntryVo> util = new ExcelUtil<TGlExchangeSchemeEntryVo>(TGlExchangeSchemeEntryVo.class);
        return util.exportExcel(list, "期末调汇方案分录");
    }

    /**
     * 获取期末调汇方案分录详细信息
     */
    @ApiOperation("获取期末调汇方案分录详细信息")
    @PreAuthorize("@ss.hasPermi('finance:exchangeSchemeEntry:query')")
    @GetMapping("/{fEntryId}")
    public AjaxResult<TGlExchangeSchemeEntryVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fEntryId") Long fEntryId) {
        return AjaxResult.success(iTGlExchangeSchemeEntryService.queryById(fEntryId));
    }

    /**
     * 新增期末调汇方案分录
     */
    @ApiOperation("新增期末调汇方案分录")
    @PreAuthorize("@ss.hasPermi('finance:exchangeSchemeEntry:add')")
    @Log(title = "期末调汇方案分录", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlExchangeSchemeEntryAddBo bo) {
        return toAjax(iTGlExchangeSchemeEntryService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改期末调汇方案分录
     */
    @ApiOperation("修改期末调汇方案分录")
    @PreAuthorize("@ss.hasPermi('finance:exchangeSchemeEntry:edit')")
    @Log(title = "期末调汇方案分录", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlExchangeSchemeEntryEditBo bo) {
        return toAjax(iTGlExchangeSchemeEntryService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除期末调汇方案分录
     */
    @ApiOperation("删除期末调汇方案分录")
    @PreAuthorize("@ss.hasPermi('finance:exchangeSchemeEntry:remove')")
    @Log(title = "期末调汇方案分录" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fEntryIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] fEntryIds) {
        return toAjax(iTGlExchangeSchemeEntryService.deleteWithValidByIds(Arrays.asList(fEntryIds), true) ? 1 : 0);
    }
}
