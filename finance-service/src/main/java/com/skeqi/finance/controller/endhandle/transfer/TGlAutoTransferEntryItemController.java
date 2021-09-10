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
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferEntryItemVo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryItemQueryBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryItemAddBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryItemEditBo;
import com.skeqi.finance.service.endhandle.transfer.ITGlAutoTransferEntryItemService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 自动转账核算维度Controller
 *
 * @author toms
 * @date 2021-07-26
 */
@Api(value = "自动转账核算维度控制器", tags = {"自动转账核算维度管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/autoTransferEntryItem")
public class TGlAutoTransferEntryItemController extends BaseController {

    private final ITGlAutoTransferEntryItemService iTGlAutoTransferEntryItemService;

    /**
     * 查询自动转账核算维度列表
     */
    @ApiOperation("查询自动转账核算维度列表")
    @PreAuthorize("@ss.hasPermi('finance:autoTransferEntryItem:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlAutoTransferEntryItemVo> list(@Validated TGlAutoTransferEntryItemQueryBo bo) {
        return iTGlAutoTransferEntryItemService.queryPageList(bo);
    }

    /**
     * 导出自动转账核算维度列表
     */
    @ApiOperation("导出自动转账核算维度列表")
    @PreAuthorize("@ss.hasPermi('finance:autoTransferEntryItem:export')")
    @Log(title = "自动转账核算维度", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlAutoTransferEntryItemVo> export(@Validated TGlAutoTransferEntryItemQueryBo bo) {
        List<TGlAutoTransferEntryItemVo> list = iTGlAutoTransferEntryItemService.queryList(bo);
        ExcelUtil<TGlAutoTransferEntryItemVo> util = new ExcelUtil<TGlAutoTransferEntryItemVo>(TGlAutoTransferEntryItemVo.class);
        return util.exportExcel(list, "自动转账核算维度");
    }

    /**
     * 获取自动转账核算维度详细信息
     */
    @ApiOperation("获取自动转账核算维度详细信息")
    @PreAuthorize("@ss.hasPermi('finance:autoTransferEntryItem:query')")
    @GetMapping("/{fEntryId}")
    public AjaxResult<TGlAutoTransferEntryItemVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fEntryId") Integer fEntryId) {
        return AjaxResult.success(iTGlAutoTransferEntryItemService.queryById(fEntryId));
    }

    /**
     * 新增自动转账核算维度
     */
    @ApiOperation("新增自动转账核算维度")
    @PreAuthorize("@ss.hasPermi('finance:autoTransferEntryItem:add')")
    @Log(title = "自动转账核算维度", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlAutoTransferEntryItemAddBo bo) {
        return toAjax(iTGlAutoTransferEntryItemService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改自动转账核算维度
     */
    @ApiOperation("修改自动转账核算维度")
    @PreAuthorize("@ss.hasPermi('finance:autoTransferEntryItem:edit')")
    @Log(title = "自动转账核算维度", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlAutoTransferEntryItemEditBo bo) {
        return toAjax(iTGlAutoTransferEntryItemService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除自动转账核算维度
     */
    @ApiOperation("删除自动转账核算维度")
    @PreAuthorize("@ss.hasPermi('finance:autoTransferEntryItem:remove')")
    @Log(title = "自动转账核算维度" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fEntryIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fEntryIds) {
        return toAjax(iTGlAutoTransferEntryItemService.deleteWithValidByIds(Arrays.asList(fEntryIds), true) ? 1 : 0);
    }
}
