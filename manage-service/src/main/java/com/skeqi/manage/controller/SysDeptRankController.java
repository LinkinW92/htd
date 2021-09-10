package com.skeqi.manage.controller;

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
import com.skeqi.manage.domain.vo.SysDeptRankVo;
import com.skeqi.manage.domain.bo.SysDeptRankQueryBo;
import com.skeqi.manage.domain.bo.SysDeptRankAddBo;
import com.skeqi.manage.domain.bo.SysDeptRankEditBo;
import com.skeqi.manage.service.ISysDeptRankService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 职级Controller
 *
 * @author toms
 * @date 2021-08-26
 */
@Api(value = "职级控制器", tags = {"职级管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/manage/deptRank")
public class SysDeptRankController extends BaseController {

    private final ISysDeptRankService iSysDeptRankService;

    /**
     * 查询职级列表
     */
    @ApiOperation("查询职级列表")
    @PreAuthorize("@ss.hasPermi('manage:deptRank:list')")
    @GetMapping("/list")
    public TableDataInfo<SysDeptRankVo> list(@Validated SysDeptRankQueryBo bo) {
        return iSysDeptRankService.queryPageList(bo);
    }

    /**
     * 导出职级列表
     */
    @ApiOperation("导出职级列表")
    @PreAuthorize("@ss.hasPermi('manage:deptRank:export')")
    @Log(title = "职级", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<SysDeptRankVo> export(@Validated SysDeptRankQueryBo bo) {
        List<SysDeptRankVo> list = iSysDeptRankService.queryList(bo);
        ExcelUtil<SysDeptRankVo> util = new ExcelUtil<SysDeptRankVo>(SysDeptRankVo.class);
        return util.exportExcel(list, "职级");
    }

    /**
     * 获取职级详细信息
     */
    @ApiOperation("获取职级详细信息")
    @PreAuthorize("@ss.hasPermi('manage:deptRank:query')")
    @GetMapping("/{id}")
    public AjaxResult<SysDeptRankVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Integer id) {
        return AjaxResult.success(iSysDeptRankService.queryById(id));
    }

    /**
     * 新增职级
     */
    @ApiOperation("新增职级")
    @PreAuthorize("@ss.hasPermi('manage:deptRank:add')")
    @Log(title = "职级", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody SysDeptRankAddBo bo) {
        return toAjax(iSysDeptRankService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改职级
     */
    @ApiOperation("修改职级")
    @PreAuthorize("@ss.hasPermi('manage:deptRank:edit')")
    @Log(title = "职级", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody SysDeptRankEditBo bo) {
        return toAjax(iSysDeptRankService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除职级
     */
    @ApiOperation("删除职级")
    @PreAuthorize("@ss.hasPermi('manage:deptRank:remove')")
    @Log(title = "职级" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] ids) {
        return toAjax(iSysDeptRankService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
