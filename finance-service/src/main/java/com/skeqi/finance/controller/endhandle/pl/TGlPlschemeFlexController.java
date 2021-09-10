package com.skeqi.finance.controller.endhandle.pl;

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
import com.skeqi.finance.pojo.vo.endhandle.TGlPlschemeFlexVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeFlexQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeFlexAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeFlexEditBo;
import com.skeqi.finance.service.endhandle.ITGlPlschemeFlexService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 结账损益核算维度Controller
 *
 * @author toms
 * @date 2021-08-17
 */
@Api(value = "结账损益核算维度控制器", tags = {"结账损益核算维度管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/plschemeFlex")
public class TGlPlschemeFlexController extends BaseController {

    private final ITGlPlschemeFlexService iTGlPlschemeFlexService;

    /**
     * 查询结账损益核算维度列表
     */
    @ApiOperation("查询结账损益核算维度列表")
    @PreAuthorize("@ss.hasPermi('finance:plschemeFlex:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlPlschemeFlexVo> list(@Validated TGlPlschemeFlexQueryBo bo) {
        return iTGlPlschemeFlexService.queryPageList(bo);
    }

    /**
     * 导出结账损益核算维度列表
     */
    @ApiOperation("导出结账损益核算维度列表")
    @PreAuthorize("@ss.hasPermi('finance:plschemeFlex:export')")
    @Log(title = "结账损益核算维度", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlPlschemeFlexVo> export(@Validated TGlPlschemeFlexQueryBo bo) {
        List<TGlPlschemeFlexVo> list = iTGlPlschemeFlexService.queryList(bo);
        ExcelUtil<TGlPlschemeFlexVo> util = new ExcelUtil<TGlPlschemeFlexVo>(TGlPlschemeFlexVo.class);
        return util.exportExcel(list, "结账损益核算维度");
    }

    /**
     * 获取结账损益核算维度详细信息
     */
    @ApiOperation("获取结账损益核算维度详细信息")
    @PreAuthorize("@ss.hasPermi('finance:plschemeFlex:query')")
    @GetMapping("/{fEntryId}")
    public AjaxResult<TGlPlschemeFlexVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fEntryId") Integer fEntryId) {
        return AjaxResult.success(iTGlPlschemeFlexService.queryById(fEntryId));
    }

    /**
     * 新增结账损益核算维度
     */
    @ApiOperation("新增结账损益核算维度")
    @PreAuthorize("@ss.hasPermi('finance:plschemeFlex:add')")
    @Log(title = "结账损益核算维度", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlPlschemeFlexAddBo bo) {
        return toAjax(iTGlPlschemeFlexService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改结账损益核算维度
     */
    @ApiOperation("修改结账损益核算维度")
    @PreAuthorize("@ss.hasPermi('finance:plschemeFlex:edit')")
    @Log(title = "结账损益核算维度", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlPlschemeFlexEditBo bo) {
        return toAjax(iTGlPlschemeFlexService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除结账损益核算维度
     */
    @ApiOperation("删除结账损益核算维度")
    @PreAuthorize("@ss.hasPermi('finance:plschemeFlex:remove')")
    @Log(title = "结账损益核算维度" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fEntryIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fEntryIds) {
        return toAjax(iTGlPlschemeFlexService.deleteWithValidByIds(Arrays.asList(fEntryIds), true) ? 1 : 0);
    }
}
