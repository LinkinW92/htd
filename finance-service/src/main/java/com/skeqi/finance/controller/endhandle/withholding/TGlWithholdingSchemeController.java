package com.skeqi.finance.controller.endhandle.withholding;

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
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingSchemeVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingSchemeQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingSchemeAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingSchemeEditBo;
import com.skeqi.finance.service.endhandle.ITGlWithholdingSchemeService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证预提Controller
 *
 * @author toms
 * @date 2021-07-27
 */
@Api(value = "凭证预提控制器", tags = {"凭证预提管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/withholdingScheme")
public class TGlWithholdingSchemeController extends BaseController {

    private final ITGlWithholdingSchemeService iTGlWithholdingSchemeService;

    /**
     * 查询凭证预提列表
     */
    @ApiOperation("查询凭证预提列表")
    @PreAuthorize("@ss.hasPermi('finance:withholdingScheme:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlWithholdingSchemeVo> list(@Validated @RequestBody TGlWithholdingSchemeQueryBo bo) {
        return iTGlWithholdingSchemeService.queryPageList(bo);
    }

    /**
     * 导出凭证预提列表
     */
    @ApiOperation("导出凭证预提列表")
    @PreAuthorize("@ss.hasPermi('finance:withholdingScheme:export')")
    @Log(title = "凭证预提", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlWithholdingSchemeVo> export(@Validated TGlWithholdingSchemeQueryBo bo) {
        List<TGlWithholdingSchemeVo> list = iTGlWithholdingSchemeService.queryList(bo);
        ExcelUtil<TGlWithholdingSchemeVo> util = new ExcelUtil<TGlWithholdingSchemeVo>(TGlWithholdingSchemeVo.class);
        return util.exportExcel(list, "凭证预提");
    }

    /**
     * 获取凭证预提详细信息
     */
    @ApiOperation("获取凭证预提详细信息")
    @PreAuthorize("@ss.hasPermi('finance:withholdingScheme:query')")
    @GetMapping("/getInfo/{fSchemeId}")
    public AjaxResult<TGlWithholdingSchemeVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fSchemeId") Integer fSchemeId) {
        return AjaxResult.success(iTGlWithholdingSchemeService.queryById(fSchemeId));
    }

    /**
     * 新增凭证预提
     */
    @ApiOperation("新增凭证预提")
    @PreAuthorize("@ss.hasPermi('finance:withholdingScheme:add')")
    @Log(title = "凭证预提", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TGlWithholdingSchemeAddBo bo) {
        return toAjax(iTGlWithholdingSchemeService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证预提
     */
    @ApiOperation("修改凭证预提")
    @PreAuthorize("@ss.hasPermi('finance:withholdingScheme:edit')")
    @Log(title = "凭证预提", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TGlWithholdingSchemeAddBo bo) {
        return toAjax(iTGlWithholdingSchemeService.updateByEditBo(bo) ? 1 : 0);
    }

	/**
	 * 执行凭证预提
	 */
	@ApiOperation("执行凭证预提")
	@PreAuthorize("@ss.hasPermi('finance:amortizationScheme:edit')")
	@Log(title = "凭证预提", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/execute/{fId}")
	public AjaxResult execute(@NotNull(message = "主键不能为空")
							  @PathVariable("fId") Integer fId){
		return iTGlWithholdingSchemeService.execute(fId);
	}
    /**
     * 删除凭证预提
     */
    @ApiOperation("删除凭证预提")
    @PreAuthorize("@ss.hasPermi('finance:withholdingScheme:remove')")
    @Log(title = "凭证预提" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fSchemeIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] fSchemeIds) {
        return toAjax(iTGlWithholdingSchemeService.deleteWithValidByIds(Arrays.asList(fSchemeIds), true) ? 1 : 0);
    }
}
