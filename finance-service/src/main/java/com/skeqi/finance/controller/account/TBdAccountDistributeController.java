package com.skeqi.finance.controller.account;

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
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountDistributeVo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountDistributeQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountDistributeAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountDistributeEditBo;
import com.skeqi.finance.service.account.ITBdAccountDistributeService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 科目分发Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "科目分发控制器", tags = {"科目分发管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/distribute")
public class TBdAccountDistributeController extends BaseController {

    private final ITBdAccountDistributeService iTBdAccountDistributeService;

    /**
     * 查询科目分发列表
     */
    @ApiOperation("查询科目分发列表")
    @PreAuthorize("@ss.hasPermi('finance:distribute:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdAccountDistributeVo> list(@Validated TBdAccountDistributeQueryBo bo) {
        return iTBdAccountDistributeService.queryPageList(bo);
    }

    /**
     * 导出科目分发列表
     */
    @ApiOperation("导出科目分发列表")
    @PreAuthorize("@ss.hasPermi('finance:distribute:export')")
    @Log(title = "科目分发", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdAccountDistributeVo> export(@Validated TBdAccountDistributeQueryBo bo) {
        List<TBdAccountDistributeVo> list = iTBdAccountDistributeService.queryList(bo);
        ExcelUtil<TBdAccountDistributeVo> util = new ExcelUtil<TBdAccountDistributeVo>(TBdAccountDistributeVo.class);
        return util.exportExcel(list, "科目分发");
    }

    /**
     * 获取科目分发详细信息
     */
    @ApiOperation("获取科目分发详细信息")
    @PreAuthorize("@ss.hasPermi('finance:distribute:query')")
    @PostMapping("/{fEntryId}")
    public AjaxResult<TBdAccountDistributeVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fEntryId") Integer fEntryId) {
        return AjaxResult.success(iTBdAccountDistributeService.queryById(fEntryId));
    }

    /**
     * 新增科目分发
     */
    @ApiOperation("新增科目分发")
    @PreAuthorize("@ss.hasPermi('finance:distribute:add')")
    @Log(title = "科目分发", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TBdAccountDistributeAddBo bo) {
        return toAjax(iTBdAccountDistributeService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改科目分发
     */
    @ApiOperation("修改科目分发")
    @PreAuthorize("@ss.hasPermi('finance:distribute:edit')")
    @Log(title = "科目分发", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TBdAccountDistributeEditBo bo) {
        return toAjax(iTBdAccountDistributeService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除科目分发
     */
    @ApiOperation("删除科目分发")
    @PreAuthorize("@ss.hasPermi('finance:distribute:remove')")
    @Log(title = "科目分发" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fEntryIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fEntryIds) {
        return toAjax(iTBdAccountDistributeService.deleteWithValidByIds(Arrays.asList(fEntryIds), true) ? 1 : 0);
    }
}
