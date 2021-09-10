package com.skeqi.finance.controller.report;

import java.util.List;

import lombok.RequiredArgsConstructor;
import javax.validation.constraints.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.skeqi.common.annotation.Log;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.finance.pojo.vo.TGlBalanceVo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceQueryBo;
import com.skeqi.finance.service.report.ITGlBalanceService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 科目余额Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "科目余额控制器", tags = {"科目余额管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/balance")
public class TGlBalanceController extends BaseController {

    private final ITGlBalanceService iTGlBalanceService;

    /**
     * 查询科目余额列表
     */
    @ApiOperation("查询科目余额列表")
    @PreAuthorize("@ss.hasPermi('finance:balance:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlBalanceVo> list(@Validated @RequestBody TGlBalanceQueryBo bo) {
        return iTGlBalanceService.queryPageList(bo);
    }

    /**
     * 导出科目余额列表
     */
    @ApiOperation("导出科目余额列表")
    @PreAuthorize("@ss.hasPermi('finance:balance:export')")
    @Log(title = "科目余额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlBalanceVo> export(@Validated TGlBalanceQueryBo bo) {
        List<TGlBalanceVo> list = iTGlBalanceService.queryList(bo);
        ExcelUtil<TGlBalanceVo> util = new ExcelUtil<TGlBalanceVo>(TGlBalanceVo.class);
        return util.exportExcel(list, "科目余额");
    }

//    /**
//     * 获取科目余额详细信息
//     */
//    @ApiOperation("获取科目余额详细信息")
//    @PreAuthorize("@ss.hasPermi('finance:balance:query')")
//    @PostMapping("/{fAccountBookId}")
//    public AjaxResult<TGlBalanceVo> getInfo(@NotNull(message = "主键不能为空")
//                                                  @PathVariable("fAccountBookId") Integer fAccountBookId) {
//        return AjaxResult.success(iTGlBalanceService.queryById(fAccountBookId));
//    }
//
//    /**
//     * 新增科目余额
//     */
//    @ApiOperation("新增科目余额")
//    @PreAuthorize("@ss.hasPermi('finance:balance:add')")
//    @Log(title = "科目余额", businessType = BusinessType.INSERT)
//    @RepeatSubmit
//    @PostMapping()
//    public AjaxResult<Void> add(@Validated @RequestBody TGlBalanceAddBo bo) {
//        return toAjax(iTGlBalanceService.insertByAddBo(bo) ? 1 : 0);
//    }
//
//    /**
//     * 修改科目余额
//     */
//    @ApiOperation("修改科目余额")
//    @PreAuthorize("@ss.hasPermi('finance:balance:edit')")
//    @Log(title = "科目余额", businessType = BusinessType.UPDATE)
//    @RepeatSubmit
//    @PutMapping()
//    public AjaxResult<Void> edit(@Validated @RequestBody TGlBalanceEditBo bo) {
//        return toAjax(iTGlBalanceService.updateByEditBo(bo) ? 1 : 0);
//    }
//
//    /**
//     * 删除科目余额
//     */
//    @ApiOperation("删除科目余额")
//    @PreAuthorize("@ss.hasPermi('finance:balance:remove')")
//    @Log(title = "科目余额" , businessType = BusinessType.DELETE)
//    @DeleteMapping("/{fAccountBookIds}")
//    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
//                                       @PathVariable Integer[] fAccountBookIds) {
//        return toAjax(iTGlBalanceService.deleteWithValidByIds(Arrays.asList(fAccountBookIds), true) ? 1 : 0);
//    }
}
