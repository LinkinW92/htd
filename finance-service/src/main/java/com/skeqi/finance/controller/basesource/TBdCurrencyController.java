package com.skeqi.finance.controller.basesource;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

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
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdCurrencyVo;
import com.skeqi.finance.pojo.bo.basicinformation.currency.TBdCurrencyQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.currency.TBdCurrencyAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.currency.TBdCurrencyEditBo;
import com.skeqi.finance.service.basicinformation.base.ITBdCurrencyService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 基础-币别Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "基础-币别控制器", tags = {"基础-币别管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/currency")
public class TBdCurrencyController extends BaseController {

    private final ITBdCurrencyService iTBdCurrencyService;

    /**
     * 查询基础-币别列表
     */
    @ApiOperation("查询基础-币别列表")
    @PreAuthorize("@ss.hasPermi('finance:currency:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdCurrencyVo> list(@Validated @RequestBody TBdCurrencyQueryBo bo) {
        return iTBdCurrencyService.queryPageList(bo);
    }

    /**
     * 导出基础-币别列表
     */
    @ApiOperation("导出基础-币别列表")
    @PreAuthorize("@ss.hasPermi('finance:currency:export')")
    @Log(title = "基础-币别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdCurrencyVo> export(@Validated TBdCurrencyQueryBo bo) {
        List<TBdCurrencyVo> list = iTBdCurrencyService.queryList(bo);
        ExcelUtil<TBdCurrencyVo> util = new ExcelUtil<TBdCurrencyVo>(TBdCurrencyVo.class);
        return util.exportExcel(list, "基础-币别");
    }

    /**
     * 获取基础-币别详细信息
     */
    @ApiOperation("获取基础-币别详细信息")
    @PreAuthorize("@ss.hasPermi('finance:currency:query')")
    @PostMapping("/{fCurrencyId}")
    public AjaxResult<TBdCurrencyVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fCurrencyId") Integer fCurrencyId) {
        return AjaxResult.success(iTBdCurrencyService.queryById(fCurrencyId));
    }

    /**
     * 新增基础-币别
     */
    @ApiOperation("新增基础-币别")
    @PreAuthorize("@ss.hasPermi('finance:currency:add')")
    @Log(title = "基础-币别", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdCurrencyAddBo bo) {
        return toAjax(iTBdCurrencyService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改基础-币别
     */
    @ApiOperation("修改基础-币别")
    @PreAuthorize("@ss.hasPermi('finance:currency:edit')")
    @Log(title = "基础-币别", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TBdCurrencyEditBo bo) {
        return toAjax(iTBdCurrencyService.updateByEditBo(bo) ? 1 : 0);
    }


	/**
	 * 修改基础-币别
	 */
	@ApiOperation("审核-币别")
	@PreAuthorize("@ss.hasPermi('finance:currency:edit')")
	@Log(title = "审核-币别", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotEmpty(message = "主键不能为空")
									  @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTBdCurrencyService.audit(s) ? 1 : 0);
	}

    /**
     * 删除基础-币别
     */
    @ApiOperation("删除基础-币别")
    @PreAuthorize("@ss.hasPermi('finance:currency:remove')")
    @Log(title = "基础-币别" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTBdCurrencyService.deleteWithValidByIds(s, true) ? 1 : 0);
    }
}
