package com.skeqi.finance.controller.init;

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
import com.skeqi.finance.pojo.vo.init.TGlBalanceInitVo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceInitQueryBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceInitAddBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceInitEditBo;
import com.skeqi.finance.service.init.ITGlBalanceInitService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 科目初始录入数据Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "科目初始录入数据控制器", tags = {"科目初始录入数据管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/init")
public class TGlBalanceInitController extends BaseController {

    private final ITGlBalanceInitService iTGlBalanceInitService;

    /**
     * 查询科目初始录入数据列表
     */
    @ApiOperation("查询科目初始录入数据列表")
    @PreAuthorize("@ss.hasPermi('finance:init:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlBalanceInitVo> list(@Validated @RequestBody TGlBalanceInitQueryBo bo) {
        return iTGlBalanceInitService.queryPageList(bo);
    }


	@ApiOperation("查询科目初始录入数据列表-核算维度")
	@PreAuthorize("@ss.hasPermi('finance:init:list2')")
	@PostMapping("/list2")
	public TableDataInfo<TGlBalanceInitVo> list2(@Validated @RequestBody TGlBalanceInitQueryBo bo) {
		return iTGlBalanceInitService.queryPageList2(bo);
	}

    /**
     * 导出科目初始录入数据列表
     */
    @ApiOperation("导出科目初始录入数据列表")
    @PreAuthorize("@ss.hasPermi('finance:init:export')")
    @Log(title = "科目初始录入数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlBalanceInitVo> export(@Validated @RequestBody TGlBalanceInitQueryBo bo) {
        List<TGlBalanceInitVo> list = iTGlBalanceInitService.queryList(bo);
        ExcelUtil<TGlBalanceInitVo> util = new ExcelUtil<TGlBalanceInitVo>(TGlBalanceInitVo.class);
        return util.exportExcel(list, "科目初始录入数据");
    }
//
//    /**
//     * 获取科目初始录入数据详细信息
//     */
//    @ApiOperation("获取科目初始录入数据详细信息")
//    @PreAuthorize("@ss.hasPermi('finance:init:query')")
//    @PostMapping("/{fAccountBookId}")
//    public AjaxResult<TGlBalanceInitVo> getInfo(@NotNull(message = "主键不能为空")
//                                                  @PathVariable("fAccountBookId") Integer fAccountBookId) {
//        return AjaxResult.success(iTGlBalanceInitService.queryById(fAccountBookId));
//    }

    /**
     * 新增科目初始录入数据
     */
    @ApiOperation("新增科目初始录入数据")
    @PreAuthorize("@ss.hasPermi('finance:init:add')")
    @Log(title = "科目初始录入数据", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TGlBalanceInitAddBo bo) {
        return toAjax(iTGlBalanceInitService.insertByAddBo(bo) ? 1 : 0);
    }
//
//    /**
//     * 修改科目初始录入数据
//     */
//    @ApiOperation("修改科目初始录入数据")
//    @PreAuthorize("@ss.hasPermi('finance:init:edit')")
//    @Log(title = "科目初始录入数据", businessType = BusinessType.UPDATE)
//    @RepeatSubmit
//    @PostMapping("/edit")
//    public AjaxResult<Void> edit(@Validated @RequestBody TGlBalanceInitEditBo bo) {
//        return toAjax(iTGlBalanceInitService.updateByEditBo(bo) ? 1 : 0);
//    }
//
//    /**
//     * 删除科目初始录入数据
//     */
//    @ApiOperation("删除科目初始录入数据")
//    @PreAuthorize("@ss.hasPermi('finance:init:remove')")
//    @Log(title = "科目初始录入数据" , businessType = BusinessType.DELETE)
//    @DeleteMapping("/{fAccountBookIds}")
//    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
//                                       @PathVariable Integer[] fAccountBookIds) {
//        return toAjax(iTGlBalanceInitService.deleteWithValidByIds(Arrays.asList(fAccountBookIds), true) ? 1 : 0);
//    }
}
