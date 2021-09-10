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
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountcyVo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountcyQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountcyAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountcyEditBo;
import com.skeqi.finance.service.account.ITBdAccountcyService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 科目核算币别Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "科目核算币别控制器", tags = {"科目核算币别管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/accountcy")
public class TBdAccountcyController extends BaseController {

    private final ITBdAccountcyService iTBdAccountcyService;

    /**
     * 查询科目核算币别列表
     */
    @ApiOperation("查询科目核算币别列表")
    @PreAuthorize("@ss.hasPermi('finance:accountcy:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdAccountcyVo> list(@Validated TBdAccountcyQueryBo bo) {
        return iTBdAccountcyService.queryPageList(bo);
    }

    /**
     * 导出科目核算币别列表
     */
    @ApiOperation("导出科目核算币别列表")
    @PreAuthorize("@ss.hasPermi('finance:accountcy:export')")
    @Log(title = "科目核算币别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdAccountcyVo> export(@Validated TBdAccountcyQueryBo bo) {
        List<TBdAccountcyVo> list = iTBdAccountcyService.queryList(bo);
        ExcelUtil<TBdAccountcyVo> util = new ExcelUtil<TBdAccountcyVo>(TBdAccountcyVo.class);
        return util.exportExcel(list, "科目核算币别");
    }

    /**
     * 获取科目核算币别详细信息
     */
    @ApiOperation("获取科目核算币别详细信息")
    @PreAuthorize("@ss.hasPermi('finance:accountcy:query')")
    @PostMapping("/{fCurrencyId}")
    public AjaxResult<TBdAccountcyVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fCurrencyId") Integer fCurrencyId) {
        return AjaxResult.success(iTBdAccountcyService.queryById(fCurrencyId));
    }

    /**
     * 新增科目核算币别
     */
    @ApiOperation("新增科目核算币别")
    @PreAuthorize("@ss.hasPermi('finance:accountcy:add')")
    @Log(title = "科目核算币别", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TBdAccountcyAddBo bo) {
        return toAjax(iTBdAccountcyService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改科目核算币别
     */
    @ApiOperation("修改科目核算币别")
    @PreAuthorize("@ss.hasPermi('finance:accountcy:edit')")
    @Log(title = "科目核算币别", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TBdAccountcyEditBo bo) {
        return toAjax(iTBdAccountcyService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除科目核算币别
     */
    @ApiOperation("删除科目核算币别")
    @PreAuthorize("@ss.hasPermi('finance:accountcy:remove')")
    @Log(title = "科目核算币别" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fCurrencyIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fCurrencyIds) {
        return toAjax(iTBdAccountcyService.deleteWithValidByIds(Arrays.asList(fCurrencyIds), true) ? 1 : 0);
    }
}
