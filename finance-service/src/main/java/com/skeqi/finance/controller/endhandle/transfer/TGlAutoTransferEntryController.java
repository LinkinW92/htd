package com.skeqi.finance.controller.endhandle.transfer;

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
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferEntryVo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryQueryBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryAddBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryEditBo;
import com.skeqi.finance.service.endhandle.transfer.ITGlAutoTransferEntryService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 自动转账分录Controller
 *
 * @author toms
 * @date 2021-07-26
 */
@Api(value = "自动转账分录控制器", tags = {"自动转账分录管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/autoTransferEntry")
public class TGlAutoTransferEntryController extends BaseController {

    private final ITGlAutoTransferEntryService iTGlAutoTransferEntryService;

    /**
     * 查询自动转账分录列表
     */
    @ApiOperation("查询自动转账分录列表")
    @PreAuthorize("@ss.hasPermi('finance:autoTransferEntry:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlAutoTransferEntryVo> list(@Validated TGlAutoTransferEntryQueryBo bo) {
        return iTGlAutoTransferEntryService.queryPageList(bo);
    }

    /**
     * 导出自动转账分录列表
     */
    @ApiOperation("导出自动转账分录列表")
    @PreAuthorize("@ss.hasPermi('finance:autoTransferEntry:export')")
    @Log(title = "自动转账分录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlAutoTransferEntryVo> export(@Validated TGlAutoTransferEntryQueryBo bo) {
        List<TGlAutoTransferEntryVo> list = iTGlAutoTransferEntryService.queryList(bo);
        ExcelUtil<TGlAutoTransferEntryVo> util = new ExcelUtil<TGlAutoTransferEntryVo>(TGlAutoTransferEntryVo.class);
        return util.exportExcel(list, "自动转账分录");
    }

    /**
     * 获取自动转账分录详细信息
     */
    @ApiOperation("获取自动转账分录详细信息")
    @PreAuthorize("@ss.hasPermi('finance:autoTransferEntry:query')")
    @GetMapping("/{fTransferEntryId}")
    public AjaxResult<TGlAutoTransferEntryVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fTransferEntryId") Integer fTransferEntryId) {
        return AjaxResult.success(iTGlAutoTransferEntryService.queryById(fTransferEntryId));
    }

    /**
     * 新增自动转账分录
     */
    @ApiOperation("新增自动转账分录")
    @PreAuthorize("@ss.hasPermi('finance:autoTransferEntry:add')")
    @Log(title = "自动转账分录", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlAutoTransferEntryAddBo bo) {
        return toAjax(iTGlAutoTransferEntryService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改自动转账分录
     */
    @ApiOperation("修改自动转账分录")
    @PreAuthorize("@ss.hasPermi('finance:autoTransferEntry:edit')")
    @Log(title = "自动转账分录", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlAutoTransferEntryEditBo bo) {
        return toAjax(iTGlAutoTransferEntryService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除自动转账分录
     */
    @ApiOperation("删除自动转账分录")
    @PreAuthorize("@ss.hasPermi('finance:autoTransferEntry:remove')")
    @Log(title = "自动转账分录" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fTransferEntryIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fTransferEntryIds) {
        return toAjax(iTGlAutoTransferEntryService.deleteWithValidByIds(Arrays.asList(fTransferEntryIds), true) ? 1 : 0);
    }
}
