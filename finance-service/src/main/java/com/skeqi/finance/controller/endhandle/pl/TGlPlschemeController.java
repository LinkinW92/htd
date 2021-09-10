package com.skeqi.finance.controller.endhandle.pl;

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
import com.skeqi.finance.pojo.vo.endhandle.TGlPlschemeVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeEditBo;
import com.skeqi.finance.service.endhandle.ITGlPlschemeService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 结转损益方案Controller
 *
 * @author toms
 * @date 2021-08-02
 */
@Api(value = "结转损益方案控制器", tags = {"结转损益方案管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/plscheme")
public class TGlPlschemeController extends BaseController {

    private final ITGlPlschemeService iTGlPlschemeService;

    /**
     * 查询结转损益方案列表
     */
    @ApiOperation("查询结转损益方案列表")
    @PreAuthorize("@ss.hasPermi('finance:plscheme:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlPlschemeVo> list(@Validated  @RequestBody TGlPlschemeQueryBo bo) {
        return iTGlPlschemeService.queryPageList(bo);
    }

    /**
     * 导出结转损益方案列表
     */
    @ApiOperation("导出结转损益方案列表")
    @PreAuthorize("@ss.hasPermi('finance:plscheme:export')")
    @Log(title = "结转损益方案", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
    public AjaxResult<TGlPlschemeVo> export(@Validated  @RequestBody TGlPlschemeQueryBo bo) {
        List<TGlPlschemeVo> list = iTGlPlschemeService.queryList(bo);
        ExcelUtil<TGlPlschemeVo> util = new ExcelUtil<TGlPlschemeVo>(TGlPlschemeVo.class);
        return util.exportExcel(list, "结转损益方案");
    }

    /**
     * 获取结转损益方案详细信息
     */
    @ApiOperation("获取结转损益方案详细信息")
    @PreAuthorize("@ss.hasPermi('finance:plscheme:query')")
    @GetMapping("/getInfo/{fId}")
    public AjaxResult<TGlPlschemeVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTGlPlschemeService.queryById(fId));
    }

    /**
     * 新增结转损益方案
     */
    @ApiOperation("新增结转损益方案")
    @PreAuthorize("@ss.hasPermi('finance:plscheme:add')")
    @Log(title = "结转损益方案", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TGlPlschemeAddBo bo) {
        return toAjax(iTGlPlschemeService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改结转损益方案
     */
    @ApiOperation("修改结转损益方案")
    @PreAuthorize("@ss.hasPermi('finance:plscheme:edit')")
    @Log(title = "结转损益方案", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TGlPlschemeEditBo bo) {
        return toAjax(iTGlPlschemeService.updateByEditBo(bo) ? 1 : 0);
    }

	/**
	 * 执行
	 */
	@ApiOperation("执行结转损益方案")
	@PreAuthorize("@ss.hasPermi('finance:plscheme:edit')")
	@Log(title = "结转损益方案", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/execute/{fId}")
	public AjaxResult execute(@NotNull(message = "主键不能为空")
							  @PathVariable("fId") Integer fId){
		return toAjax(iTGlPlschemeService.execute(fId) ? 1 : 0);
	}
	/**
	 * 审核结转损益方案
	 */
	@ApiOperation("审核结转损益方案")
	@PreAuthorize("@ss.hasPermi('finance:plscheme:edit')")
	@Log(title = "结转损益方案", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/audit")
	public AjaxResult<Void> audit(@Validated @RequestBody TGlPlschemeEditBo bo) {
		return toAjax(iTGlPlschemeService.updateByEditBo(bo) ? 1 : 0);
	}

    /**
     * 删除结转损益方案
     */
    @ApiOperation("删除结转损益方案")
    @PreAuthorize("@ss.hasPermi('finance:plscheme:remove')")
    @Log(title = "结转损益方案" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTGlPlschemeService.deleteWithValidByIds(s, true) ? 1 : 0);
    }
}
