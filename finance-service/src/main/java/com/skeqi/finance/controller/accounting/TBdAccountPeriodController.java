package com.skeqi.finance.controller.accounting;

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
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountPeriodVo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountPeriodQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountPeriodAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountPeriodEditBo;
import com.skeqi.finance.service.account.ITBdAccountPeriodService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会计期间Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "会计期间控制器", tags = {"会计期间管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/period")
public class TBdAccountPeriodController extends BaseController {

    private final ITBdAccountPeriodService iTBdAccountPeriodService;

    /**
     * 查询会计期间列表
     */
    @ApiOperation("查询会计期间列表")
    @PreAuthorize("@ss.hasPermi('finance:period:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdAccountPeriodVo> list(@Validated @RequestBody TBdAccountPeriodQueryBo bo) {
        return iTBdAccountPeriodService.queryPageList(bo);
    }


	/**
	 * 查询会计期间列表
	 */
	@ApiOperation("查询会计期间列表")
	@PreAuthorize("@ss.hasPermi('finance:accountCalendar:list')")
	@PostMapping("/listPeriod")
	public AjaxResult listPeriod(@Validated @RequestBody TBdAccountPeriodQueryBo bo) {
		return AjaxResult.success(iTBdAccountPeriodService.listPeriod(bo));
	}
    /**
     * 导出会计期间列表
     */
    @ApiOperation("导出会计期间列表")
    @PreAuthorize("@ss.hasPermi('finance:period:export')")
    @Log(title = "会计期间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdAccountPeriodVo> export(@Validated TBdAccountPeriodQueryBo bo) {
        List<TBdAccountPeriodVo> list = iTBdAccountPeriodService.queryList(bo);
        ExcelUtil<TBdAccountPeriodVo> util = new ExcelUtil<TBdAccountPeriodVo>(TBdAccountPeriodVo.class);
        return util.exportExcel(list, "会计期间");
    }

    /**
     * 获取会计期间详细信息
     */
    @ApiOperation("获取会计期间详细信息")
    @PreAuthorize("@ss.hasPermi('finance:period:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TBdAccountPeriodVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTBdAccountPeriodService.queryById(fId));
    }

    /**
     * 新增会计期间
     */
    @ApiOperation("新增会计期间")
    @PreAuthorize("@ss.hasPermi('finance:period:add')")
    //@Log(title = "会计期间", businessType = BusinessType.INSERT)
    //@RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdAccountPeriodAddBo bo) {
        return iTBdAccountPeriodService.insertByAddBo(bo);
    }

    /**
     * 修改会计期间
     */
    @ApiOperation("修改会计期间")
    @PreAuthorize("@ss.hasPermi('finance:period:edit')")
    @Log(title = "会计期间", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TBdAccountPeriodEditBo bo) {
        return toAjax(iTBdAccountPeriodService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除会计期间
     */
    @ApiOperation("删除会计期间")
    @PreAuthorize("@ss.hasPermi('finance:period:remove')")
    @Log(title = "会计期间" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fIds) {
        return toAjax(iTBdAccountPeriodService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
