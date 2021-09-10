package com.skeqi.finance.controller.endhandle.pl;

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
import com.skeqi.finance.pojo.vo.endhandle.TGlPlschemeEntryVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeEntryQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeEntryAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeEntryEditBo;
import com.skeqi.finance.service.endhandle.ITGlPlschemeEntryService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 结转损益方案分录Controller
 *
 * @author toms
 * @date 2021-08-02
 */
@Api(value = "结转损益方案分录控制器", tags = {"结转损益方案分录管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/plschemeEntry")
public class TGlPlschemeEntryController extends BaseController {

    private final ITGlPlschemeEntryService iTGlPlschemeEntryService;

    /**
     * 查询结转损益方案分录列表
     */
    @ApiOperation("查询结转损益方案分录列表")
    @PreAuthorize("@ss.hasPermi('finance:plschemeEntry:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlPlschemeEntryVo> list(@Validated TGlPlschemeEntryQueryBo bo) {
        return iTGlPlschemeEntryService.queryPageList(bo);
    }

    /**
     * 导出结转损益方案分录列表
     */
    @ApiOperation("导出结转损益方案分录列表")
    @PreAuthorize("@ss.hasPermi('finance:plschemeEntry:export')")
    @Log(title = "结转损益方案分录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlPlschemeEntryVo> export(@Validated TGlPlschemeEntryQueryBo bo) {
        List<TGlPlschemeEntryVo> list = iTGlPlschemeEntryService.queryList(bo);
        ExcelUtil<TGlPlschemeEntryVo> util = new ExcelUtil<TGlPlschemeEntryVo>(TGlPlschemeEntryVo.class);
        return util.exportExcel(list, "结转损益方案分录");
    }

    /**
     * 获取结转损益方案分录详细信息
     */
    @ApiOperation("获取结转损益方案分录详细信息")
    @PreAuthorize("@ss.hasPermi('finance:plschemeEntry:query')")
    @GetMapping("/{fEntryId}")
    public AjaxResult<TGlPlschemeEntryVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fEntryId") Long fEntryId) {
        return AjaxResult.success(iTGlPlschemeEntryService.queryById(fEntryId));
    }

    /**
     * 新增结转损益方案分录
     */
    @ApiOperation("新增结转损益方案分录")
    @PreAuthorize("@ss.hasPermi('finance:plschemeEntry:add')")
    @Log(title = "结转损益方案分录", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlPlschemeEntryAddBo bo) {
        return toAjax(iTGlPlschemeEntryService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改结转损益方案分录
     */
    @ApiOperation("修改结转损益方案分录")
    @PreAuthorize("@ss.hasPermi('finance:plschemeEntry:edit')")
    @Log(title = "结转损益方案分录", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlPlschemeEntryEditBo bo) {
        return toAjax(iTGlPlschemeEntryService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除结转损益方案分录
     */
    @ApiOperation("删除结转损益方案分录")
    @PreAuthorize("@ss.hasPermi('finance:plschemeEntry:remove')")
    @Log(title = "结转损益方案分录" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fEntryIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] fEntryIds) {
        return toAjax(iTGlPlschemeEntryService.deleteWithValidByIds(Arrays.asList(fEntryIds), true) ? 1 : 0);
    }
}
