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
import com.skeqi.finance.pojo.vo.TGlInitDimensionVo;
import com.skeqi.finance.pojo.bo.basicinformation.init.TGlInitDimensionQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.init.TGlInitDimensionAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.init.TGlInitDimensionEditBo;
import com.skeqi.finance.service.init.ITGlInitDimensionService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 科目核算维度初始数据Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "科目核算维度初始数据控制器", tags = {"科目核算维度初始数据管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/dimension")
public class TGlInitDimensionController extends BaseController {

    private final ITGlInitDimensionService iTGlInitDimensionService;

    /**
     * 查询科目核算维度初始数据列表
     */
    @ApiOperation("查询科目核算维度初始数据列表")
    @PreAuthorize("@ss.hasPermi('finance:dimension:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlInitDimensionVo> list(@Validated TGlInitDimensionQueryBo bo) {
        return iTGlInitDimensionService.queryPageList(bo);
    }

    /**
     * 导出科目核算维度初始数据列表
     */
    @ApiOperation("导出科目核算维度初始数据列表")
    @PreAuthorize("@ss.hasPermi('finance:dimension:export')")
    @Log(title = "科目核算维度初始数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlInitDimensionVo> export(@Validated TGlInitDimensionQueryBo bo) {
        List<TGlInitDimensionVo> list = iTGlInitDimensionService.queryList(bo);
        ExcelUtil<TGlInitDimensionVo> util = new ExcelUtil<TGlInitDimensionVo>(TGlInitDimensionVo.class);
        return util.exportExcel(list, "科目核算维度初始数据");
    }

    /**
     * 获取科目核算维度初始数据详细信息
     */
    @ApiOperation("获取科目核算维度初始数据详细信息")
    @PreAuthorize("@ss.hasPermi('finance:dimension:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TGlInitDimensionVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTGlInitDimensionService.queryById(fId));
    }

    /**
     * 新增科目核算维度初始数据
     */
    @ApiOperation("新增科目核算维度初始数据")
    @PreAuthorize("@ss.hasPermi('finance:dimension:add')")
    @Log(title = "科目核算维度初始数据", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlInitDimensionAddBo bo) {
        return toAjax(iTGlInitDimensionService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改科目核算维度初始数据
     */
    @ApiOperation("修改科目核算维度初始数据")
    @PreAuthorize("@ss.hasPermi('finance:dimension:edit')")
    @Log(title = "科目核算维度初始数据", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlInitDimensionEditBo bo) {
        return toAjax(iTGlInitDimensionService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除科目核算维度初始数据
     */
    @ApiOperation("删除科目核算维度初始数据")
    @PreAuthorize("@ss.hasPermi('finance:dimension:remove')")
    @Log(title = "科目核算维度初始数据" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fIds) {
        return toAjax(iTGlInitDimensionService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
