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
import com.skeqi.finance.pojo.vo.basicinformation.depr.TFaDeprMethodItemVo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodItemQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodItemAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodItemEditBo;
import com.skeqi.finance.service.depr.ITFaDeprMethodItemService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 折旧方法元素Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "折旧方法元素控制器", tags = {"折旧方法元素管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/deprMethodItem")
public class TFaDeprMethodItemController extends BaseController {

    private final ITFaDeprMethodItemService iTFaDeprMethodItemService;

    /**
     * 查询折旧方法元素列表
     */
    @ApiOperation("查询折旧方法元素列表")
    @PreAuthorize("@ss.hasPermi('finance:item:list')")
    @PostMapping("/list")
    public TableDataInfo<TFaDeprMethodItemVo> list(@Validated TFaDeprMethodItemQueryBo bo) {
        return iTFaDeprMethodItemService.queryPageList(bo);
    }

    /**
     * 导出折旧方法元素列表
     */
    @ApiOperation("导出折旧方法元素列表")
    @PreAuthorize("@ss.hasPermi('finance:item:export')")
    @Log(title = "折旧方法元素", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TFaDeprMethodItemVo> export(@Validated TFaDeprMethodItemQueryBo bo) {
        List<TFaDeprMethodItemVo> list = iTFaDeprMethodItemService.queryList(bo);
        ExcelUtil<TFaDeprMethodItemVo> util = new ExcelUtil<TFaDeprMethodItemVo>(TFaDeprMethodItemVo.class);
        return util.exportExcel(list, "折旧方法元素");
    }

    /**
     * 获取折旧方法元素详细信息
     */
    @ApiOperation("获取折旧方法元素详细信息")
    @PreAuthorize("@ss.hasPermi('finance:item:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TFaDeprMethodItemVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTFaDeprMethodItemService.queryById(fId));
    }

    /**
     * 新增折旧方法元素
     */
    @ApiOperation("新增折旧方法元素")
    @PreAuthorize("@ss.hasPermi('finance:item:add')")
    @Log(title = "折旧方法元素", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TFaDeprMethodItemAddBo bo) {
        return toAjax(iTFaDeprMethodItemService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改折旧方法元素
     */
    @ApiOperation("修改折旧方法元素")
    @PreAuthorize("@ss.hasPermi('finance:item:edit')")
    @Log(title = "折旧方法元素", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TFaDeprMethodItemEditBo bo) {
        return toAjax(iTFaDeprMethodItemService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除折旧方法元素
     */
    @ApiOperation("删除折旧方法元素")
    @PreAuthorize("@ss.hasPermi('finance:item:remove')")
    @Log(title = "折旧方法元素" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fIds) {
        return toAjax(iTFaDeprMethodItemService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
