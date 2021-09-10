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
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountTypeVo;
import com.skeqi.finance.pojo.bo.TBdAccountType.TBdAccountTypeQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccountType.TBdAccountTypeAddBo;
import com.skeqi.finance.pojo.bo.TBdAccountType.TBdAccountTypeEditBo;
import com.skeqi.finance.service.account.ITBdAccountTypeService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 科目类别Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "科目类别控制器", tags = {"科目类别管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/accountType")
public class TBdAccountTypeController extends BaseController {

    private final ITBdAccountTypeService iTBdAccountTypeService;

    /**
     * 查询科目类别列表
     */
    @ApiOperation("查询科目类别列表")
    @PreAuthorize("@ss.hasPermi('finance:type:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdAccountTypeVo> list(@Validated @RequestBody TBdAccountTypeQueryBo bo) {
        return iTBdAccountTypeService.queryPageList(bo);
    }

    /**
     * 导出科目类别列表
     */
    @ApiOperation("导出科目类别列表")
    @PreAuthorize("@ss.hasPermi('finance:type:export')")
    @Log(title = "科目类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdAccountTypeVo> export(@Validated TBdAccountTypeQueryBo bo) {
        List<TBdAccountTypeVo> list = iTBdAccountTypeService.queryList(bo);
        ExcelUtil<TBdAccountTypeVo> util = new ExcelUtil<TBdAccountTypeVo>(TBdAccountTypeVo.class);
        return util.exportExcel(list, "科目类别");
    }

    /**
     * 获取科目类别详细信息
     */
    @ApiOperation("获取科目类别详细信息")
    @PreAuthorize("@ss.hasPermi('finance:type:query')")
    @PostMapping("/{fAcctTypeId}")
    public AjaxResult<TBdAccountTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fAcctTypeId") Integer fAcctTypeId) {
        return AjaxResult.success(iTBdAccountTypeService.queryById(fAcctTypeId));
    }

    /**
     * 新增科目类别
     */
    @ApiOperation("新增科目类别")
    @PreAuthorize("@ss.hasPermi('finance:type:add')")
    @Log(title = "科目类别", businessType = BusinessType.INSERT)
    @RepeatSubmit
	@PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdAccountTypeAddBo bo) {
        return toAjax(iTBdAccountTypeService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改科目类别
     */
    @ApiOperation("修改科目类别")
    @PreAuthorize("@ss.hasPermi('finance:type:edit')")
    @Log(title = "科目类别", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TBdAccountTypeEditBo bo) {
        return toAjax(iTBdAccountTypeService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除科目类别
     */
    @ApiOperation("删除科目类别")
    @PreAuthorize("@ss.hasPermi('finance:type:remove')")
    @Log(title = "科目类别" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fAcctTypeIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fAcctTypeIds) {
        return toAjax(iTBdAccountTypeService.deleteWithValidByIds(Arrays.asList(fAcctTypeIds), true) ? 1 : 0);
    }
}
