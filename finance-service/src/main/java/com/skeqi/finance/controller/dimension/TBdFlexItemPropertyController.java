package com.skeqi.finance.controller.dimension;

import com.skeqi.common.annotation.Log;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.finance.pojo.bo.TBdDimensionSource.DisableBo;
import com.skeqi.finance.pojo.bo.TBdFlexItemProperty.TBdFlexItemPropertyAddBo;
import com.skeqi.finance.pojo.bo.TBdFlexItemProperty.TBdFlexItemPropertyEditBo;
import com.skeqi.finance.pojo.bo.TBdFlexItemProperty.TBdFlexItemPropertyQueryBo;
import com.skeqi.finance.pojo.vo.dimension.TBdFlexItemPropertyVo;
import com.skeqi.finance.service.basicinformation.base.BaseTableService;
import com.skeqi.finance.service.dimension.ITBdFlexItemPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 核算维度Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "核算维度控制器", tags = {"核算维度管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/property")
public class TBdFlexItemPropertyController extends BaseController {

	private final ITBdFlexItemPropertyService iTBdFlexItemPropertyService;
	@Resource(name = "TBdFlexItemPropertyServiceImpl")
	private BaseTableService baseTableService;

	/**
	 * 查询核算维度列表
	 */
	@ApiOperation("查询核算维度列表")
	@PreAuthorize("@ss.hasPermi('finance:property:list')")
	@PostMapping("/list")
	public TableDataInfo<TBdFlexItemPropertyVo> list(@Validated @RequestBody TBdFlexItemPropertyQueryBo bo) {
		return iTBdFlexItemPropertyService.queryPageList(bo);
	}

	/**
	 * 导出核算维度列表
	 */
	@ApiOperation("导出核算维度列表")
	@PreAuthorize("@ss.hasPermi('finance:property:export')")
	@Log(title = "核算维度", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	public AjaxResult<TBdFlexItemPropertyVo> export(@Validated TBdFlexItemPropertyQueryBo bo) {
		List<TBdFlexItemPropertyVo> list = iTBdFlexItemPropertyService.queryList(bo);
		ExcelUtil<TBdFlexItemPropertyVo> util = new ExcelUtil<TBdFlexItemPropertyVo>(TBdFlexItemPropertyVo.class);
		return util.exportExcel(list, "核算维度");
	}

	/**
	 * 获取核算维度详细信息
	 */
	@ApiOperation("获取核算维度详细信息")
	@PreAuthorize("@ss.hasPermi('finance:property:query')")
	@GetMapping("/{fId}")
	public AjaxResult<TBdFlexItemPropertyVo> getInfo(@NotNull(message = "主键不能为空")
													 @PathVariable("fId") Integer fId) {
		return AjaxResult.success(iTBdFlexItemPropertyService.queryById(fId));
	}

	/**
	 * 新增核算维度
	 */
	@ApiOperation("新增核算维度")
	@PreAuthorize("@ss.hasPermi('finance:property:add')")
	@Log(title = "核算维度", businessType = BusinessType.INSERT)
	@RepeatSubmit
	@PostMapping("/add")
	public AjaxResult<Void> add(@Validated @RequestBody TBdFlexItemPropertyAddBo bo) {
		return toAjax(iTBdFlexItemPropertyService.insertByAddBo(bo) ? 1 : 0);
	}

	/**
	 * 修改核算维度
	 */
	@ApiOperation("修改核算维度")
	@PreAuthorize("@ss.hasPermi('finance:property:edit')")
	@Log(title = "核算维度", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/edit")
	public AjaxResult<Void> edit(@Validated @RequestBody TBdFlexItemPropertyEditBo bo) {
		return toAjax(iTBdFlexItemPropertyService.updateByEditBo(bo) ? 1 : 0);
	}

	/**
	 * 删除核算维度
	 */
	@ApiOperation("删除核算维度")
	@PreAuthorize("@ss.hasPermi('finance:property:remove')")
	@Log(title = "核算维度", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable Integer[] fIds) {
		return toAjax(iTBdFlexItemPropertyService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
	}


	/**
	 * 审核维度来源
	 */
	@ApiOperation("审核维度来源")
	@PreAuthorize("@ss.hasPermi('finance:property:audit')")
	@Log(title = "维度来源", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotEmpty(message = "主键不能为空")
									  @PathVariable Integer[] fIds) {
		return toAjax(baseTableService.audit(Arrays.asList(fIds)) ? 1 : 0);
	}

	/**
	 * 禁用维度来源
	 */
	@ApiOperation("禁用维度来源")
	@PreAuthorize("@ss.hasPermi('finance:property:disable')")
	@Log(title = "维度来源", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/disable")
	public AjaxResult<Void> disable(@Validated @RequestBody DisableBo bo) {
		return toAjax(baseTableService.disable(bo) ? 1 : 0);
	}
}
