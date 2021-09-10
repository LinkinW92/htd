package com.skeqi.finance.controller.account;

import com.skeqi.common.annotation.Log;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.finance.pojo.bo.TBdAccountTable.TBdAccountTableAddBo;
import com.skeqi.finance.pojo.bo.TBdAccountTable.TBdAccountTableEditBo;
import com.skeqi.finance.pojo.bo.TBdAccountTable.TBdAccountTableQueryBo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountTableVo;
import com.skeqi.finance.service.account.ITBdAccountTableService;
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

/**
 * 科目Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "科目控制器", tags = {"科目管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/accountTable")
public class TBdAccountTableController extends BaseController {

	private final ITBdAccountTableService iTBdAccountTableService;

	/**
	 * 查询科目列表
	 */
	@ApiOperation("查询科目列表")
	@PreAuthorize("@ss.hasPermi('finance:table:list')")
	@PostMapping("/list")
	public TableDataInfo<TBdAccountTableVo> list(@Validated @RequestBody TBdAccountTableQueryBo bo) {
		return iTBdAccountTableService.queryPageList(bo);
	}

	/**
	 * 导出科目列表
	 */
	@ApiOperation("导出科目列表")
	@PreAuthorize("@ss.hasPermi('finance:table:export')")
	@Log(title = "科目", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult<TBdAccountTableVo> export(@Validated TBdAccountTableQueryBo bo) {
		List<TBdAccountTableVo> list = iTBdAccountTableService.queryList(bo);
		ExcelUtil<TBdAccountTableVo> util = new ExcelUtil<TBdAccountTableVo>(TBdAccountTableVo.class);
		return util.exportExcel(list, "科目");
	}

	/**
	 * 获取科目详细信息
	 */
	@ApiOperation("获取科目详细信息")
	@PreAuthorize("@ss.hasPermi('finance:table:query')")
	@GetMapping("/{fAcctTableId}")
	public AjaxResult<TBdAccountTableVo> getInfo(@NotNull(message = "主键不能为空")
												 @PathVariable("fAcctTableId") Integer fAcctTableId) {
		return AjaxResult.success(iTBdAccountTableService.queryById(fAcctTableId));
	}

	/**
	 * 新增科目
	 */
	@ApiOperation("新增科目")
	@PreAuthorize("@ss.hasPermi('finance:table:add')")
	@Log(title = "科目", businessType = BusinessType.INSERT)
	@RepeatSubmit
	@PostMapping("/add")
	public AjaxResult<Void> add(@Validated @RequestBody TBdAccountTableAddBo bo) {
		return toAjax(iTBdAccountTableService.insertByAddBo(bo) ? 1 : 0);
	}

	/**
	 * 修改科目
	 */
	@ApiOperation("修改科目")
	@PreAuthorize("@ss.hasPermi('finance:table:edit')")
	@Log(title = "科目", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/edit")
	public AjaxResult<Void> edit(@Validated @RequestBody TBdAccountTableEditBo bo) {
		return toAjax(iTBdAccountTableService.updateByEditBo(bo) ? 1 : 0);
	}

	/**
	 * 删除科目
	 */
	@ApiOperation("删除科目")
	@PreAuthorize("@ss.hasPermi('finance:table:remove')")
	@Log(title = "科目", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fAcctTableIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable Integer[] fAcctTableIds) {
		return toAjax(iTBdAccountTableService.deleteWithValidByIds(Arrays.asList(fAcctTableIds), true) ? 1 : 0);
	}

}
