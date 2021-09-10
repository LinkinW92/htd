package com.skeqi.finance.controller.asset;

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
import com.skeqi.finance.pojo.vo.asset.TFaAssetTypeVo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeQueryBo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeAddBo;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeEditBo;
import com.skeqi.finance.service.asset.ITFaAssetTypeService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资产类别Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "资产类别控制器", tags = {"资产类别管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/assetType")
public class TFaAssetTypeController extends BaseController {

    private final ITFaAssetTypeService iTFaAssetTypeService;

    /**
     * 查询资产类别列表
     */
    @ApiOperation("查询资产类别列表")
    @PreAuthorize("@ss.hasPermi('finance:assetType:list')")
    @PostMapping("/list")
    public TableDataInfo<TFaAssetTypeVo> list(@Validated TFaAssetTypeQueryBo bo) {
        return iTFaAssetTypeService.queryPageList(bo);
    }

    /**
     * 导出资产类别列表
     */
    @ApiOperation("导出资产类别列表")
    @PreAuthorize("@ss.hasPermi('finance:assetType:export')")
    @Log(title = "资产类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TFaAssetTypeVo> export(@Validated TFaAssetTypeQueryBo bo) {
        List<TFaAssetTypeVo> list = iTFaAssetTypeService.queryList(bo);
        ExcelUtil<TFaAssetTypeVo> util = new ExcelUtil<TFaAssetTypeVo>(TFaAssetTypeVo.class);
        return util.exportExcel(list, "资产类别");
    }

    /**
     * 获取资产类别详细信息
     */
    @ApiOperation("获取资产类别详细信息")
    @PreAuthorize("@ss.hasPermi('finance:assetType:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TFaAssetTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTFaAssetTypeService.queryById(fId));
    }

    /**
     * 新增资产类别
     */
    @ApiOperation("新增资产类别")
    @PreAuthorize("@ss.hasPermi('finance:assetType:add')")
    @Log(title = "资产类别", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TFaAssetTypeAddBo bo) {
        return toAjax(iTFaAssetTypeService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改资产类别
     */
    @ApiOperation("修改资产类别")
    @PreAuthorize("@ss.hasPermi('finance:assetType:edit')")
    @Log(title = "资产类别", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TFaAssetTypeEditBo bo) {
        return toAjax(iTFaAssetTypeService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除资产类别
     */
    @ApiOperation("删除资产类别")
    @PreAuthorize("@ss.hasPermi('finance:assetType:remove')")
    @Log(title = "资产类别" , businessType = BusinessType.DELETE)
    @PostMapping("/remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTFaAssetTypeService.deleteWithValidByIds(integerList) ? 1 : 0);
    }


	/**
	 * 审核资产类别
	 */
	@ApiOperation("审核资产类别")
	@PreAuthorize("@ss.hasPermi('finance:group:audit')")
	@Log(title = "资产类别" , businessType = BusinessType.UPDATE)
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTFaAssetTypeService.auditByIds(integerList) ? 1 : 0);
	}

	/**
	 * 反审核资产类别
	 */
	@ApiOperation("反审核资产类别")
	@PreAuthorize("@ss.hasPermi('finance:group:antiAudit')")
	@Log(title = "资产类别" , businessType = BusinessType.UPDATE)
	@PostMapping("/antiAudit/{fIds}")
	public AjaxResult<Void> antiAudit(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTFaAssetTypeService.antiAuditByIds(integerList) ? 1 : 0);
	}
}
