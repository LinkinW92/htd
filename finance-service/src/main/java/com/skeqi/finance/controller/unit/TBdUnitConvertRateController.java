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
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitConvertRateVo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitConvertRateQueryBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitConvertRateAddBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitConvertRateEditBo;
import com.skeqi.finance.service.unit.ITBdUnitConvertRateService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 单位换算Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "单位换算控制器", tags = {"单位换算管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/unitConvertRate")
public class TBdUnitConvertRateController extends BaseController {

    private final ITBdUnitConvertRateService iTBdUnitConvertRateService;

    /**
     * 查询单位换算列表
     */
    @ApiOperation("查询单位换算列表")
    @PreAuthorize("@ss.hasPermi('finance:rate:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdUnitConvertRateVo> list(@Validated TBdUnitConvertRateQueryBo bo) {
        return iTBdUnitConvertRateService.queryPageList(bo);
    }

    /**
     * 导出单位换算列表
     */
    @ApiOperation("导出单位换算列表")
    @PreAuthorize("@ss.hasPermi('finance:rate:export')")
    @Log(title = "单位换算", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdUnitConvertRateVo> export(@Validated TBdUnitConvertRateQueryBo bo) {
        List<TBdUnitConvertRateVo> list = iTBdUnitConvertRateService.queryList(bo);
        ExcelUtil<TBdUnitConvertRateVo> util = new ExcelUtil<TBdUnitConvertRateVo>(TBdUnitConvertRateVo.class);
        return util.exportExcel(list, "单位换算");
    }

    /**
     * 获取单位换算详细信息
     */
    @ApiOperation("获取单位换算详细信息")
    @PreAuthorize("@ss.hasPermi('finance:rate:query')")
    @PostMapping("/{fUnitConvertRateid}")
    public AjaxResult<TBdUnitConvertRateVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fUnitConvertRateid") Integer fUnitConvertRateid) {
        return AjaxResult.success(iTBdUnitConvertRateService.queryById(fUnitConvertRateid));
    }

    /**
     * 新增单位换算
     */
    @ApiOperation("新增单位换算")
    @PreAuthorize("@ss.hasPermi('finance:rate:add')")
    @Log(title = "单位换算", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TBdUnitConvertRateAddBo bo) {
        return toAjax(iTBdUnitConvertRateService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改单位换算
     */
    @ApiOperation("修改单位换算")
    @PreAuthorize("@ss.hasPermi('finance:rate:edit')")
    @Log(title = "单位换算", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TBdUnitConvertRateEditBo bo) {
        return toAjax(iTBdUnitConvertRateService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除单位换算
     */
    @ApiOperation("删除单位换算")
    @PreAuthorize("@ss.hasPermi('finance:rate:remove')")
    @Log(title = "单位换算" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fUnitConvertRateids}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fUnitConvertRateids) {
        return toAjax(iTBdUnitConvertRateService.deleteWithValidByIds(Arrays.asList(fUnitConvertRateids), true) ? 1 : 0);
    }
}
