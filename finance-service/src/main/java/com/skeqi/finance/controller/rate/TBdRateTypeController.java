package com.skeqi.finance.controller.rate;

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
import com.skeqi.finance.pojo.vo.basicinformation.rate.TBdRateTypeVo;
import com.skeqi.finance.pojo.bo.rate.TBdRateTypeQueryBo;
import com.skeqi.finance.pojo.bo.rate.TBdRateTypeAddBo;
import com.skeqi.finance.pojo.bo.rate.TBdRateTypeEditBo;
import com.skeqi.finance.service.rate.ITBdRateTypeService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 汇率类型Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "汇率类型控制器", tags = {"汇率类型管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/rateType")
public class TBdRateTypeController extends BaseController {

    private final ITBdRateTypeService iTBdRateTypeService;

    /**
     * 查询汇率类型列表
     */
    @ApiOperation("查询汇率类型列表")
    @PreAuthorize("@ss.hasPermi('finance:type:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdRateTypeVo> list(@Validated TBdRateTypeQueryBo bo) {
        return iTBdRateTypeService.queryPageList(bo);
    }

    /**
     * 导出汇率类型列表
     */
    @ApiOperation("导出汇率类型列表")
    @PreAuthorize("@ss.hasPermi('finance:type:export')")
    @Log(title = "汇率类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdRateTypeVo> export(@Validated TBdRateTypeQueryBo bo) {
        List<TBdRateTypeVo> list = iTBdRateTypeService.queryList(bo);
        ExcelUtil<TBdRateTypeVo> util = new ExcelUtil<TBdRateTypeVo>(TBdRateTypeVo.class);
        return util.exportExcel(list, "汇率类型");
    }

    /**
     * 获取汇率类型详细信息
     */
    @ApiOperation("获取汇率类型详细信息")
    @PreAuthorize("@ss.hasPermi('finance:type:query')")
    @PostMapping("/{fRatetypeId}")
    public AjaxResult<TBdRateTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fRatetypeId") Integer fRatetypeId) {
        return AjaxResult.success(iTBdRateTypeService.queryById(fRatetypeId));
    }

    /**
     * 新增汇率类型
     */
    @ApiOperation("新增汇率类型")
    @PreAuthorize("@ss.hasPermi('finance:type:add')")
    @Log(title = "汇率类型", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdRateTypeAddBo bo) {
        return toAjax(iTBdRateTypeService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改汇率类型
     */
    @ApiOperation("修改汇率类型")
    @PreAuthorize("@ss.hasPermi('finance:type:edit')")
    @Log(title = "汇率类型", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TBdRateTypeEditBo bo) {
        return toAjax(iTBdRateTypeService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除汇率类型
     */
    @ApiOperation("删除汇率类型")
    @PreAuthorize("@ss.hasPermi('finance:type:remove')")
    @Log(title = "汇率类型" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fRatetypeIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fRatetypeIds) {
        return toAjax(iTBdRateTypeService.deleteWithValidByIds(Arrays.asList(fRatetypeIds), true) ? 1 : 0);
    }
}
