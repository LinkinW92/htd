package com.skeqi.finance.controller;

import java.util.List;
import java.util.Arrays;

import com.skeqi.finance.domain.TBdExecuteLog;
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
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdExecuteLogVo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TBdExecuteLogEditBo;
import com.skeqi.finance.service.basicinformation.base.ITBdExecuteLogService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 执行业务日志记录Controller
 *
 * @author toms
 * @date 2021-08-07
 */
@Api(value = "执行业务日志记录控制器", tags = {"执行业务日志记录管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/executeLog")
public class TBdExecuteLogController extends BaseController {

    private final ITBdExecuteLogService iTBdExecuteLogService;

    /**
     * 查询执行业务日志记录列表
     */
    @ApiOperation("查询执行业务日志记录列表")
    @PreAuthorize("@ss.hasPermi('finance:executeLog:list')")
    @GetMapping("/list")
    public TableDataInfo<TBdExecuteLogVo> list(@Validated @RequestBody TBdExecuteLogQueryBo bo) {
        return iTBdExecuteLogService.queryPageList(bo);
    }

    /**
     * 导出执行业务日志记录列表
     */
    @ApiOperation("导出执行业务日志记录列表")
    @PreAuthorize("@ss.hasPermi('finance:executeLog:export')")
    @Log(title = "执行业务日志记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TBdExecuteLogVo> export(@Validated @RequestBody TBdExecuteLogQueryBo bo) {
        List<TBdExecuteLogVo> list = iTBdExecuteLogService.queryList(bo);
        ExcelUtil<TBdExecuteLogVo> util = new ExcelUtil<TBdExecuteLogVo>(TBdExecuteLogVo.class);
        return util.exportExcel(list, "执行业务日志记录");
    }

    /**
     * 获取执行业务日志记录详细信息
     */
    @ApiOperation("获取执行业务日志记录详细信息")
    @PreAuthorize("@ss.hasPermi('finance:executeLog:query')")
    @GetMapping("/{id}")
    public AjaxResult<TBdExecuteLogVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Integer id) {
        return AjaxResult.success(iTBdExecuteLogService.queryById(id));
    }

    /**
     * 新增执行业务日志记录
     */
    @ApiOperation("新增执行业务日志记录")
    @PreAuthorize("@ss.hasPermi('finance:executeLog:add')")
    @Log(title = "执行业务日志记录", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<TBdExecuteLog> add(@Validated @RequestBody TBdExecuteLogAddBo bo) {
        return iTBdExecuteLogService.insertByAddBo(bo);
    }

    /**
     * 修改执行业务日志记录
     */
    @ApiOperation("修改执行业务日志记录")
    @PreAuthorize("@ss.hasPermi('finance:executeLog:edit')")
    @Log(title = "执行业务日志记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TBdExecuteLogEditBo bo) {
        return toAjax(iTBdExecuteLogService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除执行业务日志记录
     */
    @ApiOperation("删除执行业务日志记录")
    @PreAuthorize("@ss.hasPermi('finance:executeLog:remove')")
    @Log(title = "执行业务日志记录" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] ids) {
        return toAjax(iTBdExecuteLogService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
