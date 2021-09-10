package com.skeqi.finance.controller.basicinformation.depr;

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
import com.skeqi.finance.pojo.vo.basicinformation.depr.TFaDeprMethodEntryVo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodEntryQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodEntryAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodEntryEditBo;
import com.skeqi.finance.service.depr.ITFaDeprMethodEntryService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 折旧方法明细Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "折旧方法明细控制器", tags = {"折旧方法明细管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/deprMethodEntry")
public class TFaDeprMethodEntryController extends BaseController {

    private final ITFaDeprMethodEntryService iTFaDeprMethodEntryService;

    /**
     * 查询折旧方法明细列表
     */
    @ApiOperation("查询折旧方法明细列表")
    @PreAuthorize("@ss.hasPermi('finance:entry:list')")
    @PostMapping("/list")
    public TableDataInfo<TFaDeprMethodEntryVo> list(@Validated TFaDeprMethodEntryQueryBo bo) {
        return iTFaDeprMethodEntryService.queryPageList(bo);
    }

    /**
     * 导出折旧方法明细列表
     */
    @ApiOperation("导出折旧方法明细列表")
    @PreAuthorize("@ss.hasPermi('finance:entry:export')")
    @Log(title = "折旧方法明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TFaDeprMethodEntryVo> export(@Validated TFaDeprMethodEntryQueryBo bo) {
        List<TFaDeprMethodEntryVo> list = iTFaDeprMethodEntryService.queryList(bo);
        ExcelUtil<TFaDeprMethodEntryVo> util = new ExcelUtil<TFaDeprMethodEntryVo>(TFaDeprMethodEntryVo.class);
        return util.exportExcel(list, "折旧方法明细");
    }

    /**
     * 获取折旧方法明细详细信息
     */
    @ApiOperation("获取折旧方法明细详细信息")
    @PreAuthorize("@ss.hasPermi('finance:entry:query')")
    @PostMapping("/{fEntryId}")
    public AjaxResult<TFaDeprMethodEntryVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fEntryId") Integer fEntryId) {
        return AjaxResult.success(iTFaDeprMethodEntryService.queryById(fEntryId));
    }

    /**
     * 新增折旧方法明细
     */
    @ApiOperation("新增折旧方法明细")
    @PreAuthorize("@ss.hasPermi('finance:entry:add')")
    @Log(title = "折旧方法明细", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TFaDeprMethodEntryAddBo bo) {
        return toAjax(iTFaDeprMethodEntryService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改折旧方法明细
     */
    @ApiOperation("修改折旧方法明细")
    @PreAuthorize("@ss.hasPermi('finance:entry:edit')")
    @Log(title = "折旧方法明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TFaDeprMethodEntryEditBo bo) {
        return toAjax(iTFaDeprMethodEntryService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除折旧方法明细
     */
    @ApiOperation("删除折旧方法明细")
    @PreAuthorize("@ss.hasPermi('finance:entry:remove')")
    @Log(title = "折旧方法明细" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fEntryIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fEntryIds) {
        return toAjax(iTFaDeprMethodEntryService.deleteWithValidByIds(Arrays.asList(fEntryIds), true) ? 1 : 0);
    }
}
