package com.skeqi.finance.controller.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountFlexentryVo;
import com.skeqi.finance.pojo.bo.TBdAccountFlexentry.TBdAccountFlexentryQueryBo;
import com.skeqi.finance.service.account.ITBdAccountFlexentryService;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 科目核算维度组分录Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "科目核算维度组分录控制器", tags = {"科目核算维度组分录管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/flexentry")
public class TBdAccountFlexentryController extends BaseController {

    private final ITBdAccountFlexentryService iTBdAccountFlexentryService;

    /**
     * 查询科目核算维度组分录列表
     */
    @ApiOperation("查询科目核算维度组分录列表")
    @PreAuthorize("@ss.hasPermi('finance:flexentry:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdAccountFlexentryVo> list(@Validated TBdAccountFlexentryQueryBo bo) {
        return iTBdAccountFlexentryService.queryPageList(bo);
    }
//
//    /**
//     * 导出科目核算维度组分录列表
//     */
//    @ApiOperation("导出科目核算维度组分录列表")
//    @PreAuthorize("@ss.hasPermi('finance:flexentry:export')")
//    @Log(title = "科目核算维度组分录", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public AjaxResult<TBdAccountFlexentryVo> export(@Validated TBdAccountFlexentryQueryBo bo) {
//        List<TBdAccountFlexentryVo> list = iTBdAccountFlexentryService.queryList(bo);
//        ExcelUtil<TBdAccountFlexentryVo> util = new ExcelUtil<TBdAccountFlexentryVo>(TBdAccountFlexentryVo.class);
//        return util.exportExcel(list, "科目核算维度组分录");
//    }
//
//    /**
//     * 获取科目核算维度组分录详细信息
//     */
//    @ApiOperation("获取科目核算维度组分录详细信息")
//    @PreAuthorize("@ss.hasPermi('finance:flexentry:query')")
//    @PostMapping("/{fEntryId}")
//    public AjaxResult<TBdAccountFlexentryVo> getInfo(@NotNull(message = "主键不能为空")
//                                                  @PathVariable("fEntryId") Integer fEntryId) {
//        return AjaxResult.success(iTBdAccountFlexentryService.queryById(fEntryId));
//    }
//
//    /**
//     * 新增科目核算维度组分录
//     */
//    @ApiOperation("新增科目核算维度组分录")
//    @PreAuthorize("@ss.hasPermi('finance:flexentry:add')")
//    @Log(title = "科目核算维度组分录", businessType = BusinessType.INSERT)
//    @RepeatSubmit
//	@PostMapping("/add")
//    public AjaxResult<Void> add(@Validated @RequestBody TBdAccountFlexentryAddBo bo) {
//        return toAjax(iTBdAccountFlexentryService.insertByAddBo(bo) ? 1 : 0);
//    }
//
//    /**
//     * 修改科目核算维度组分录
//     */
//    @ApiOperation("修改科目核算维度组分录")
//    @PreAuthorize("@ss.hasPermi('finance:flexentry:edit')")
//    @Log(title = "科目核算维度组分录", businessType = BusinessType.UPDATE)
//    @RepeatSubmit
//    @PutMapping()
//    public AjaxResult<Void> edit(@Validated @RequestBody TBdAccountFlexentryEditBo bo) {
//        return toAjax(iTBdAccountFlexentryService.updateByEditBo(bo) ? 1 : 0);
//    }
//
//    /**
//     * 删除科目核算维度组分录
//     */
//    @ApiOperation("删除科目核算维度组分录")
//    @PreAuthorize("@ss.hasPermi('finance:flexentry:remove')")
//    @Log(title = "科目核算维度组分录" , businessType = BusinessType.DELETE)
//    @DeleteMapping("/{fEntryIds}")
//    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
//                                       @PathVariable Integer[] fEntryIds) {
//        return toAjax(iTBdAccountFlexentryService.deleteWithValidByIds(Arrays.asList(fEntryIds), true) ? 1 : 0);
//    }
}
