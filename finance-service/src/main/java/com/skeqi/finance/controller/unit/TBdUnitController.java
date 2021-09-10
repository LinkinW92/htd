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
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitVo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitQueryBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitAddBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitEditBo;
import com.skeqi.finance.service.unit.ITBdUnitService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 计量单位Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "计量单位控制器", tags = {"计量单位管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/unit")
public class TBdUnitController extends BaseController {

    private final ITBdUnitService iTBdUnitService;

    /**
     * 查询计量单位列表
     */
    @ApiOperation("查询计量单位列表")
    @PreAuthorize("@ss.hasPermi('finance:unit:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdUnitVo> list(@Validated TBdUnitQueryBo bo) {
        return iTBdUnitService.queryPageList(bo);
    }

    /**
     * 导出计量单位列表
     */
    @ApiOperation("导出计量单位列表")
    @PreAuthorize("@ss.hasPermi('finance:unit:export')")
    @Log(title = "计量单位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdUnitVo> export(@Validated TBdUnitQueryBo bo) {
        List<TBdUnitVo> list = iTBdUnitService.queryList(bo);
        ExcelUtil<TBdUnitVo> util = new ExcelUtil<TBdUnitVo>(TBdUnitVo.class);
        return util.exportExcel(list, "计量单位");
    }

    /**
     * 获取计量单位详细信息
     */
    @ApiOperation("获取计量单位详细信息")
    @PreAuthorize("@ss.hasPermi('finance:unit:query')")
    @PostMapping("/{fUnitId}")
    public AjaxResult<TBdUnitVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fUnitId") Integer fUnitId) {
        return AjaxResult.success(iTBdUnitService.queryById(fUnitId));
    }

    /**
     * 新增计量单位
     */
    @ApiOperation("新增计量单位")
    @PreAuthorize("@ss.hasPermi('finance:unit:add')")
    @Log(title = "计量单位", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdUnitAddBo bo) {
        return toAjax(iTBdUnitService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改计量单位
     */
    @ApiOperation("修改计量单位")
    @PreAuthorize("@ss.hasPermi('finance:unit:edit')")
    @Log(title = "计量单位", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TBdUnitEditBo bo) {
        return toAjax(iTBdUnitService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除计量单位
     */
    @ApiOperation("删除计量单位")
    @PreAuthorize("@ss.hasPermi('finance:unit:remove')")
    @Log(title = "计量单位" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fUnitIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fUnitIds) {
        return toAjax(iTBdUnitService.deleteWithValidByIds(Arrays.asList(fUnitIds), true) ? 1 : 0);
    }
}
