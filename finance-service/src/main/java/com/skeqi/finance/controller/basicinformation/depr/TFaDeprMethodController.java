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
import com.skeqi.finance.pojo.vo.basicinformation.depr.TFaDeprMethodVo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodEditBo;
import com.skeqi.finance.service.depr.ITFaDeprMethodService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 折旧方法Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "折旧方法控制器", tags = {"折旧方法管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/deprMethod")
public class TFaDeprMethodController extends BaseController {

    private final ITFaDeprMethodService iTFaDeprMethodService;

    /**
     * 查询折旧方法列表
     */
    @ApiOperation("查询折旧方法列表")
    @PreAuthorize("@ss.hasPermi('finance:method:list')")
    @PostMapping("/list")
    public TableDataInfo<TFaDeprMethodVo> list(@Validated TFaDeprMethodQueryBo bo) {
        return iTFaDeprMethodService.queryPageList(bo);
    }

    /**
     * 导出折旧方法列表
     */
    @ApiOperation("导出折旧方法列表")
    @PreAuthorize("@ss.hasPermi('finance:method:export')")
    @Log(title = "折旧方法", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TFaDeprMethodVo> export(@Validated TFaDeprMethodQueryBo bo) {
        List<TFaDeprMethodVo> list = iTFaDeprMethodService.queryList(bo);
        ExcelUtil<TFaDeprMethodVo> util = new ExcelUtil<TFaDeprMethodVo>(TFaDeprMethodVo.class);
        return util.exportExcel(list, "折旧方法");
    }

    /**
     * 获取折旧方法详细信息
     */
    @ApiOperation("获取折旧方法详细信息")
    @PreAuthorize("@ss.hasPermi('finance:method:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TFaDeprMethodVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTFaDeprMethodService.queryById(fId));
    }

    /**
     * 新增折旧方法
     */
    @ApiOperation("新增折旧方法")
    @PreAuthorize("@ss.hasPermi('finance:method:add')")
    @Log(title = "折旧方法", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TFaDeprMethodAddBo bo) {
        return toAjax(iTFaDeprMethodService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改折旧方法
     */
    @ApiOperation("修改折旧方法")
    @PreAuthorize("@ss.hasPermi('finance:method:edit')")
    @Log(title = "折旧方法", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TFaDeprMethodEditBo bo) {
        return toAjax(iTFaDeprMethodService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除折旧方法
     */
    @ApiOperation("删除折旧方法")
    @PreAuthorize("@ss.hasPermi('finance:method:remove')")
    @Log(title = "折旧方法" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fIds) {
        return toAjax(iTFaDeprMethodService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
