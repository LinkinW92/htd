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
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitGroupTableVo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupTableQueryBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupTableAddBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupTableEditBo;
import com.skeqi.finance.service.unit.ITBdUnitGroupTableService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 计量单位分组Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "计量单位分组控制器", tags = {"计量单位分组管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/unitGroupTab")
public class TBdUnitGroupTableController extends BaseController {

    private final ITBdUnitGroupTableService iTBdUnitGroupTableService;

    /**
     * 查询计量单位分组列表
     */
    @ApiOperation("查询计量单位分组列表")
    @PreAuthorize("@ss.hasPermi('finance:table:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdUnitGroupTableVo> list(@Validated TBdUnitGroupTableQueryBo bo) {
        return iTBdUnitGroupTableService.queryPageList(bo);
    }

    /**
     * 导出计量单位分组列表
     */
    @ApiOperation("导出计量单位分组列表")
    @PreAuthorize("@ss.hasPermi('finance:table:export')")
    @Log(title = "计量单位分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdUnitGroupTableVo> export(@Validated TBdUnitGroupTableQueryBo bo) {
        List<TBdUnitGroupTableVo> list = iTBdUnitGroupTableService.queryList(bo);
        ExcelUtil<TBdUnitGroupTableVo> util = new ExcelUtil<TBdUnitGroupTableVo>(TBdUnitGroupTableVo.class);
        return util.exportExcel(list, "计量单位分组");
    }

    /**
     * 获取计量单位分组详细信息
     */
    @ApiOperation("获取计量单位分组详细信息")
    @PreAuthorize("@ss.hasPermi('finance:table:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TBdUnitGroupTableVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTBdUnitGroupTableService.queryById(fId));
    }

    /**
     * 新增计量单位分组
     */
    @ApiOperation("新增计量单位分组")
    @PreAuthorize("@ss.hasPermi('finance:table:add')")
    @Log(title = "计量单位分组", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TBdUnitGroupTableAddBo bo) {
        return toAjax(iTBdUnitGroupTableService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改计量单位分组
     */
    @ApiOperation("修改计量单位分组")
    @PreAuthorize("@ss.hasPermi('finance:table:edit')")
    @Log(title = "计量单位分组", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TBdUnitGroupTableEditBo bo) {
        return toAjax(iTBdUnitGroupTableService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除计量单位分组
     */
    @ApiOperation("删除计量单位分组")
    @PreAuthorize("@ss.hasPermi('finance:table:remove')")
    @Log(title = "计量单位分组" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fIds) {
        return toAjax(iTBdUnitGroupTableService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
