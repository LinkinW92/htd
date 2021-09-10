package com.skeqi.finance.controller.endhandle.amortization;

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
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortInacctVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortInacctQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortInacctAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortInacctEditBo;
import com.skeqi.finance.service.endhandle.ITGlAmortInacctService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证摊销-转入科目Controller
 *
 * @author toms
 * @date 2021-07-27
 */
@Api(value = "凭证摊销-转入科目控制器", tags = {"凭证摊销-转入科目管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/amortInacct")
public class TGlAmortInacctController extends BaseController {

    private final ITGlAmortInacctService iTGlAmortInacctService;

    /**
     * 查询凭证摊销-转入科目列表
     */
    @ApiOperation("查询凭证摊销-转入科目列表")
    @PreAuthorize("@ss.hasPermi('finance:amortInacct:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlAmortInacctVo> list(@Validated TGlAmortInacctQueryBo bo) {
        return iTGlAmortInacctService.queryPageList(bo);
    }

    /**
     * 导出凭证摊销-转入科目列表
     */
    @ApiOperation("导出凭证摊销-转入科目列表")
    @PreAuthorize("@ss.hasPermi('finance:amortInacct:export')")
    @Log(title = "凭证摊销-转入科目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlAmortInacctVo> export(@Validated TGlAmortInacctQueryBo bo) {
        List<TGlAmortInacctVo> list = iTGlAmortInacctService.queryList(bo);
        ExcelUtil<TGlAmortInacctVo> util = new ExcelUtil<TGlAmortInacctVo>(TGlAmortInacctVo.class);
        return util.exportExcel(list, "凭证摊销-转入科目");
    }

    /**
     * 获取凭证摊销-转入科目详细信息
     */
    @ApiOperation("获取凭证摊销-转入科目详细信息")
    @PreAuthorize("@ss.hasPermi('finance:amortInacct:query')")
    @GetMapping("/{fId}")
    public AjaxResult<TGlAmortInacctVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Long fId) {
        return AjaxResult.success(iTGlAmortInacctService.queryById(fId));
    }

    /**
     * 新增凭证摊销-转入科目
     */
    @ApiOperation("新增凭证摊销-转入科目")
    @PreAuthorize("@ss.hasPermi('finance:amortInacct:add')")
    @Log(title = "凭证摊销-转入科目", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlAmortInacctAddBo bo) {
        return toAjax(iTGlAmortInacctService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证摊销-转入科目
     */
    @ApiOperation("修改凭证摊销-转入科目")
    @PreAuthorize("@ss.hasPermi('finance:amortInacct:edit')")
    @Log(title = "凭证摊销-转入科目", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlAmortInacctEditBo bo) {
        return toAjax(iTGlAmortInacctService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证摊销-转入科目
     */
    @ApiOperation("删除凭证摊销-转入科目")
    @PreAuthorize("@ss.hasPermi('finance:amortInacct:remove')")
    @Log(title = "凭证摊销-转入科目" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] fIds) {
        return toAjax(iTGlAmortInacctService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
