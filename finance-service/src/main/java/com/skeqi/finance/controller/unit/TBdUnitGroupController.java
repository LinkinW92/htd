package com.skeqi.finance.controller.unit;

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
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitGroupVo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupQueryBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupAddBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupEditBo;
import com.skeqi.finance.service.unit.ITBdUnitGroupService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 计量单位组Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "计量单位组控制器", tags = {"计量单位组管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/unitGroup")
public class TBdUnitGroupController extends BaseController {

    private final ITBdUnitGroupService iTBdUnitGroupService;

    /**
     * 查询计量单位组列表
     */
    @ApiOperation("查询计量单位组列表")
    @PreAuthorize("@ss.hasPermi('finance:group:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdUnitGroupVo> list(@Validated TBdUnitGroupQueryBo bo) {
        return iTBdUnitGroupService.queryPageList(bo);
    }

    /**
     * 导出计量单位组列表
     */
    @ApiOperation("导出计量单位组列表")
    @PreAuthorize("@ss.hasPermi('finance:group:export')")
    @Log(title = "计量单位组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdUnitGroupVo> export(@Validated TBdUnitGroupQueryBo bo) {
        List<TBdUnitGroupVo> list = iTBdUnitGroupService.queryList(bo);
        ExcelUtil<TBdUnitGroupVo> util = new ExcelUtil<TBdUnitGroupVo>(TBdUnitGroupVo.class);
        return util.exportExcel(list, "计量单位组");
    }

    /**
     * 获取计量单位组详细信息
     */
    @ApiOperation("获取计量单位组详细信息")
    @PreAuthorize("@ss.hasPermi('finance:group:query')")
    @PostMapping("/{fUnitGroupId}")
    public AjaxResult<TBdUnitGroupVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fUnitGroupId") Integer fUnitGroupId) {
        return AjaxResult.success(iTBdUnitGroupService.queryById(fUnitGroupId));
    }

    /**
     * 新增计量单位组
     */
    @ApiOperation("新增计量单位组")
    @PreAuthorize("@ss.hasPermi('finance:group:add')")
    @Log(title = "计量单位组", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdUnitGroupAddBo bo) {
        return toAjax(iTBdUnitGroupService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改计量单位组
     */
    @ApiOperation("修改计量单位组")
    @PreAuthorize("@ss.hasPermi('finance:group:edit')")
    @Log(title = "计量单位组", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TBdUnitGroupEditBo bo) {
        return toAjax(iTBdUnitGroupService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除计量单位组
     */
    @ApiOperation("删除计量单位组")
    @PreAuthorize("@ss.hasPermi('finance:group:remove')")
    @Log(title = "计量单位组" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fUnitGroupIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fUnitGroupIds) {
        return toAjax(iTBdUnitGroupService.deleteWithValidByIds(Arrays.asList(fUnitGroupIds), true) ? 1 : 0);
    }
}
