package com.skeqi.finance.controller.asset;

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
import com.skeqi.finance.pojo.vo.asset.TFaAssetTypeGroupVo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeGroupQueryBo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeGroupAddBo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeGroupEditBo;
import com.skeqi.finance.service.asset.ITFaAssetTypeGroupService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资产类别组Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "资产类别组控制器", tags = {"资产类别组管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/assetTypeGroup")
public class TFaAssetTypeGroupController extends BaseController {

    private final ITFaAssetTypeGroupService iTFaAssetTypeGroupService;

    /**
     * 查询资产类别组列表
     */
    @ApiOperation("查询资产类别组列表")
    @PreAuthorize("@ss.hasPermi('finance:assetTypeGroup:list')")
    @PostMapping("/list")
    public AjaxResult<List<TFaAssetTypeGroupVo>> list(@Validated @RequestBody TFaAssetTypeGroupQueryBo bo) {
        return AjaxResult.success(iTFaAssetTypeGroupService.queryList(bo));
    }


    /**
     * 导出资产类别组列表
     */
    @ApiOperation("导出资产类别组列表")
    @PreAuthorize("@ss.hasPermi('finance:assetTypeGroup:export')")
    @Log(title = "资产类别组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TFaAssetTypeGroupVo> export(@Validated TFaAssetTypeGroupQueryBo bo) {
        List<TFaAssetTypeGroupVo> list = iTFaAssetTypeGroupService.queryList(bo);
        ExcelUtil<TFaAssetTypeGroupVo> util = new ExcelUtil<TFaAssetTypeGroupVo>(TFaAssetTypeGroupVo.class);
        return util.exportExcel(list, "资产类别组");
    }

    /**
     * 获取资产类别组详细信息
     */
    @ApiOperation("获取资产类别组详细信息")
    @PreAuthorize("@ss.hasPermi('finance:assetTypeGroup:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TFaAssetTypeGroupVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTFaAssetTypeGroupService.queryById(fId));
    }

    /**
     * 新增资产类别组
     */
    @ApiOperation("新增资产类别组")
    @PreAuthorize("@ss.hasPermi('finance:assetTypeGroup:add')")
    @Log(title = "资产类别组", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TFaAssetTypeGroupAddBo bo) {
        return toAjax(iTFaAssetTypeGroupService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改资产类别组
     */
    @ApiOperation("修改资产类别组")
    @PreAuthorize("@ss.hasPermi('finance:assetTypeGroup:edit')")
    @Log(title = "资产类别组", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TFaAssetTypeGroupEditBo bo) {
        return toAjax(iTFaAssetTypeGroupService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除资产类别组
     */
    @ApiOperation("删除资产类别组")
    @PreAuthorize("@ss.hasPermi('finance:assetTypeGroup:remove')")
    @Log(title = "资产类别组" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fIds) {
        return toAjax(iTFaAssetTypeGroupService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
