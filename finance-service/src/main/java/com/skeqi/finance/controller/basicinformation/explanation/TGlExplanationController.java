package com.skeqi.finance.controller.basicinformation.explanation;

import com.skeqi.common.annotation.Log;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationEditBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationQueryBo;
import com.skeqi.finance.pojo.vo.TGlExplanationVo;
import com.skeqi.finance.service.ITGlExplanationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 摘要库Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "摘要库控制器", tags = {"摘要库管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/explanation")
public class TGlExplanationController extends BaseController {

    private final ITGlExplanationService iTGlExplanationService;

    /**
     * 查询摘要库列表
     */
    @ApiOperation("查询摘要库列表")
    @PreAuthorize("@ss.hasPermi('finance:explanation:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlExplanationVo> list(@Validated @RequestBody TGlExplanationQueryBo bo) {
        return iTGlExplanationService.queryPageList(bo);
    }

    /**
     * 导出摘要库列表
     */
    @ApiOperation("导出摘要库列表")
    @PreAuthorize("@ss.hasPermi('finance:explanation:export')")
    @Log(title = "摘要库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlExplanationVo> export(@Validated @RequestBody TGlExplanationQueryBo bo) {
        List<TGlExplanationVo> list = iTGlExplanationService.queryList(bo);
        ExcelUtil<TGlExplanationVo> util = new ExcelUtil<TGlExplanationVo>(TGlExplanationVo.class);
        return util.exportExcel(list, "摘要库");
    }

    /**
     * 获取摘要库详细信息
     */
    @ApiOperation("获取摘要库详细信息")
    @PreAuthorize("@ss.hasPermi('finance:explanation:query')")
    @PostMapping("/getInfo/{fId}")
    public AjaxResult<TGlExplanationVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTGlExplanationService.queryById(fId));
    }

    /**
     * 新增摘要库
     */
    @ApiOperation("新增摘要库")
    @PreAuthorize("@ss.hasPermi('finance:explanation:add')")
    @Log(title = "摘要库", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TGlExplanationAddBo bo) {
        return toAjax(iTGlExplanationService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改摘要库
     */
    @ApiOperation("修改摘要库")
    @PreAuthorize("@ss.hasPermi('finance:explanation:edit')")
    @Log(title = "摘要库", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TGlExplanationEditBo bo) {
        return toAjax(iTGlExplanationService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除摘要库
     */
    @ApiOperation("删除摘要库")
    @PreAuthorize("@ss.hasPermi('finance:explanation:remove')")
    @Log(title = "摘要库" , businessType = BusinessType.DELETE)
    @PostMapping("/remove/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
									   @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTGlExplanationService.deleteWithValidByIds(s, true) ? 1 : 0);
    }

	/**
	 * 审核摘要库
	 */
	@ApiOperation("审核摘要库")
	@PreAuthorize("@ss.hasPermi('finance:explanation:edit')")
	@Log(title = "摘要库" , businessType = BusinessType.UPDATE)
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotEmpty(message = "主键不能为空")
									  @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTGlExplanationService.auditWithValidByIds(s));
	}

	/**
	 * 反审核摘要库
	 */
	@ApiOperation("反审核摘要库")
	@PreAuthorize("@ss.hasPermi('finance:explanation:edit')")
	@Log(title = "摘要库" , businessType = BusinessType.UPDATE)
	@PutMapping("/deAudit/{fIds}")
	public AjaxResult<Void> deAudit(@NotEmpty(message = "主键不能为空")
								   @PathVariable Integer[] fIds) {
		return toAjax(iTGlExplanationService.deAuditWithValidByIds(Arrays.asList(fIds)));
	}

	/**
	 * 禁用摘要库
	 */
	@ApiOperation("禁用摘要库")
	@PreAuthorize("@ss.hasPermi('finance:explanation:edit')")
	@Log(title = "摘要库" , businessType = BusinessType.UPDATE)
	@PutMapping("/disable/{fIds}")
	public AjaxResult<Void> disable(@NotEmpty(message = "主键不能为空")
								  @PathVariable Integer[] fIds) {
		return toAjax(iTGlExplanationService.disableWithValidByIds(Arrays.asList(fIds)));
	}

	/**
	 * 禁用摘要库
	 */
	@ApiOperation("反禁用摘要库")
	@PreAuthorize("@ss.hasPermi('finance:explanation:edit')")
	@Log(title = "摘要库" , businessType = BusinessType.UPDATE)
	@PutMapping("/forbidden/{fIds}")
	public AjaxResult<Void> forbidden(@NotEmpty(message = "主键不能为空")
								  @PathVariable Integer[] fIds) {
		return toAjax(iTGlExplanationService.deDisableWithValidByIds(Arrays.asList(fIds)));
	}
}
