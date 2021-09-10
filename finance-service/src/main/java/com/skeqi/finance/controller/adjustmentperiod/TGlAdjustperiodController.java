package com.skeqi.finance.controller.adjustmentperiod;

import com.skeqi.common.annotation.Log;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.finance.pojo.bo.adjustmentperiod.TGlAdjustperiodpAddBo;
import com.skeqi.finance.pojo.bo.adjustmentperiod.TGlAdjustperiodpEditBo;
import com.skeqi.finance.pojo.bo.adjustmentperiod.TGlAdjustperiodpQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherQueryBo;
import com.skeqi.finance.pojo.vo.adjustmentperiod.TGlAdjustperiodVo;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherVo;
import com.skeqi.finance.service.adjustmentperiod.TGlAdjustperiodService;
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
 * 调整期间管理控制器
 */
@Api(value = "调整期间管理控制器", tags = {"调整期间管理控制器"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/adjustPeriod")
public class TGlAdjustperiodController extends BaseController {

	private final TGlAdjustperiodService tGlAdjustperiodService;

	/**
	 * 查询调整期间列表
	 */
	@ApiOperation("查询调整期间列表")
	@PreAuthorize("@ss.hasPermi('finance:adjustPeriod:list')")
	@PostMapping("/list")
	public TableDataInfo<TGlAdjustperiodVo> list(@Validated @RequestBody TGlAdjustperiodpQueryBo bo) {
		return tGlAdjustperiodService.queryPageList(bo);
	}

	/**
	 * 按账簿id和年度期间查询期间号
	 * @return
	 */
	@ApiOperation("按账簿id和年度期间查询期间号")
	@PreAuthorize("@ss.hasPermi('finance:adjustPeriod:queryByBookIdAndYear')")
	@PostMapping("/queryByBookIdAndYear")
	public AjaxResult queryByBookIdAndYear(@Validated @RequestBody TGlAdjustperiodpQueryBo bo) {
		return AjaxResult.success(tGlAdjustperiodService.queryByBookIdAndYear(bo));
	}

	/**
	 * 查询调整期间列表
	 */
	@ApiOperation("查询调整期间列表")
	@PreAuthorize("@ss.hasPermi('finance:adjustPeriod:queryList')")
	@PostMapping("/queryList")
	public AjaxResult export(@Validated @RequestBody TGlAdjustperiodpQueryBo bo) {
		List<TGlAdjustperiodVo> list = tGlAdjustperiodService.queryList(bo);
		return AjaxResult.success(list);
	}

	/**
	 * 新增调整期间
	 */
	@ApiOperation("新增调整期间")
	@PreAuthorize("@ss.hasPermi('finance:adjustPeriod:add')")
	@Log(title = "调整期间", businessType = BusinessType.INSERT)
	@RepeatSubmit
	@PostMapping("/add")
	public AjaxResult<Void> add(@Validated @RequestBody TGlAdjustperiodpAddBo bo) {
		return toAjax(tGlAdjustperiodService.insertByAddBo(bo) ? 1 : 0);
	}

	/**
	 * 修改调整期间
	 */
	@ApiOperation("修改调整期间")
	@PreAuthorize("@ss.hasPermi('finance:adjustPeriod:edit')")
	@Log(title = "调整期间", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/edit")
	public AjaxResult<Void> edit(@Validated @RequestBody TGlAdjustperiodpEditBo bo) {
		return toAjax(tGlAdjustperiodService.updateByEditBo(bo) ? 1 : 0);
	}

	/**
	 * 删除调整期间
	 */
	@ApiOperation("删除调整期间")
	@PreAuthorize("@ss.hasPermi('finance:adjustPeriod:remove')")
	@Log(title = "调整期间" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(tGlAdjustperiodService.deleteWithValidByIds(integerList) ? 1 : 0);
	}

	/**
	 * 更新调整期间状态
	 */
	@ApiOperation("更新调整期间状态")
	@PreAuthorize("@ss.hasPermi('finance:adjustPeriod:updateStatus')")
	@Log(title = "调整期间" , businessType = BusinessType.UPDATE)
	@PostMapping("/updateStatus/{fIds}")
	public AjaxResult<Void> updateStatus(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
			List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(tGlAdjustperiodService.updateStatus(integerList) ? 1 : 0);
	}
}
