package com.skeqi.finance.controller;

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
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdFileManageVo;
import com.skeqi.finance.pojo.bo.basicinformation.file.TBdFileManageQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.file.TBdFileManageAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.file.TBdFileManageEditBo;
import com.skeqi.finance.service.basicinformation.base.ITBdFileManageService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件管理Controller
 *
 * @author toms
 * @date 2021-08-18
 */
@Api(value = "文件管理控制器", tags = {"文件管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/fileManage")
public class TBdFileManageController extends BaseController {

    private final ITBdFileManageService iTBdFileManageService;

    /**
     * 查询文件管理列表
     */
    @ApiOperation("查询文件管理列表")
    @PreAuthorize("@ss.hasPermi('finance:fileManage:list')")
    @GetMapping("/list")
    public TableDataInfo<TBdFileManageVo> list(@Validated TBdFileManageQueryBo bo) {
        return iTBdFileManageService.queryPageList(bo);
    }

    /**
     * 导出文件管理列表
     */
    @ApiOperation("导出文件管理列表")
    @PreAuthorize("@ss.hasPermi('finance:fileManage:export')")
    @Log(title = "文件管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TBdFileManageVo> export(@Validated TBdFileManageQueryBo bo) {
        List<TBdFileManageVo> list = iTBdFileManageService.queryList(bo);
        ExcelUtil<TBdFileManageVo> util = new ExcelUtil<TBdFileManageVo>(TBdFileManageVo.class);
        return util.exportExcel(list, "文件管理");
    }

    /**
     * 获取文件管理详细信息
     */
    @ApiOperation("获取文件管理详细信息")
    @PreAuthorize("@ss.hasPermi('finance:fileManage:query')")
    @GetMapping("/{id}")
    public AjaxResult<TBdFileManageVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Integer id) {
        return AjaxResult.success(iTBdFileManageService.queryById(id));
    }

    /**
     * 新增文件管理
     */
    @ApiOperation("新增文件管理")
    @PreAuthorize("@ss.hasPermi('finance:fileManage:add')")
    @Log(title = "文件管理", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TBdFileManageAddBo bo) {
        return toAjax(iTBdFileManageService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改文件管理
     */
    @ApiOperation("修改文件管理")
    @PreAuthorize("@ss.hasPermi('finance:fileManage:edit')")
    @Log(title = "文件管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TBdFileManageEditBo bo) {
        return toAjax(iTBdFileManageService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除文件管理
     */
    @ApiOperation("删除文件管理")
    @PreAuthorize("@ss.hasPermi('finance:fileManage:remove')")
    @Log(title = "文件管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] ids) {
        return toAjax(iTBdFileManageService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
