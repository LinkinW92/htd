package com.skeqi.finance.controller.endhandle.exchange;

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
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeSchemeVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeEditBo;
import com.skeqi.finance.service.endhandle.ITGlExchangeSchemeService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 期末调汇方案Controller
 *
 * @author toms
 * @date 2021-07-30
 */
@Api(value = "期末调汇方案控制器", tags = {"期末调汇方案管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/exchangeScheme")
public class TGlExchangeSchemeController extends BaseController {

    private final ITGlExchangeSchemeService iTGlExchangeSchemeService;

    /**
     * 查询期末调汇方案列表
     */
    @ApiOperation("查询期末调汇方案列表")
    @PreAuthorize("@ss.hasPermi('finance:exchangeScheme:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlExchangeSchemeVo> list(@Validated @RequestBody  TGlExchangeSchemeQueryBo bo) {
        return iTGlExchangeSchemeService.queryPageList(bo);
    }

    /**
     * 导出期末调汇方案列表
     */
    @ApiOperation("导出期末调汇方案列表")
    @PreAuthorize("@ss.hasPermi('finance:exchangeScheme:export')")
    @Log(title = "期末调汇方案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlExchangeSchemeVo> export(@Validated @RequestBody  TGlExchangeSchemeQueryBo bo) {
        List<TGlExchangeSchemeVo> list = iTGlExchangeSchemeService.queryList(bo);
        ExcelUtil<TGlExchangeSchemeVo> util = new ExcelUtil<TGlExchangeSchemeVo>(TGlExchangeSchemeVo.class);
        return util.exportExcel(list, "期末调汇方案");
    }

    /**
     * 获取期末调汇方案详细信息
     */
    @ApiOperation("获取期末调汇方案详细信息")
    @PreAuthorize("@ss.hasPermi('finance:exchangeScheme:query')")
    @GetMapping("/getInfo/{fId}")
    public AjaxResult<TGlExchangeSchemeVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTGlExchangeSchemeService.queryById(fId));
    }

    /**
     * 新增期末调汇方案
     */
    @ApiOperation("新增期末调汇方案")
    @PreAuthorize("@ss.hasPermi('finance:exchangeScheme:add')")
    @Log(title = "期末调汇方案", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TGlExchangeSchemeAddBo bo) {
        return toAjax(iTGlExchangeSchemeService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改期末调汇方案
     */
    @ApiOperation("修改期末调汇方案")
    @PreAuthorize("@ss.hasPermi('finance:exchangeScheme:edit')")
    @Log(title = "期末调汇方案", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TGlExchangeSchemeEditBo bo) {
        return toAjax(iTGlExchangeSchemeService.updateByEditBo(bo) ? 1 : 0);
    }


	/**
	 * 执行
	 */
	@ApiOperation("执行期末调汇方案")
	@PreAuthorize("@ss.hasPermi('finance:exchangeScheme:edit')")
	@Log(title = "期末调汇方案", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/execute/{fId}")
	public AjaxResult execute(@NotNull(message = "主键不能为空")
							  @PathVariable("fId") Integer fId){
		return iTGlExchangeSchemeService.execute(fId,false);
	}


	/**
     * 删除期末调汇方案
     */
    @ApiOperation("删除期末调汇方案")
    @PreAuthorize("@ss.hasPermi('finance:exchangeScheme:remove')")
    @Log(title = "期末调汇方案" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fIds) {
        return toAjax(iTGlExchangeSchemeService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
