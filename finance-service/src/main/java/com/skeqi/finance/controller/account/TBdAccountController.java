package com.skeqi.finance.controller.account;

import com.skeqi.common.annotation.Log;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountEditBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountQueryBo;
import com.skeqi.finance.pojo.bo.TBdDimensionSource.DisableBo;
import com.skeqi.finance.pojo.vo.TBdAccount.AccountTableVo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountVo;
import com.skeqi.finance.service.basicinformation.base.BaseTableService;
import com.skeqi.finance.service.account.ITBdAccountService;
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
 * 科目信息Controller
 *
 * @author toms
 * @date 2021-07-19
 */
@Api(value = "科目信息控制器", tags = {"科目信息管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/account")
public class TBdAccountController extends BaseController {

	private final ITBdAccountService iTBdAccountService;

	@Resource(name = "TBdAccountServiceImpl")
	private BaseTableService baseTableService;

	/**
	 * 查询科目信息列表
	 */
	@ApiOperation("查询科目信息列表")
	@PreAuthorize("@ss.hasPermi('finance:account:list')")
	@PostMapping("/list")
	public TableDataInfo<TBdAccountVo> list(@Validated @RequestBody TBdAccountQueryBo bo) {
		return iTBdAccountService.queryPageList(bo);
	}

	/**
	 * 导出科目信息列表
	 */
	@ApiOperation("导出科目信息列表")
	@PreAuthorize("@ss.hasPermi('finance:account:export')")
	@Log(title = "科目信息", businessType = BusinessType.EXPORT)
	@GetMapping("/export")
	public AjaxResult<TBdAccountVo> export(@Validated TBdAccountQueryBo bo) {
		List<TBdAccountVo> list = iTBdAccountService.queryList(bo);
		ExcelUtil<TBdAccountVo> util = new ExcelUtil<TBdAccountVo>(TBdAccountVo.class);
		return util.exportExcel(list, "科目信息");
	}

	/**
	 * 获取科目信息详细信息
	 */
	@ApiOperation("获取科目信息详细信息")
	@PreAuthorize("@ss.hasPermi('finance:account:query')")
	@GetMapping("/{fAcctId}")
	public AjaxResult<TBdAccountVo> getInfo(@NotNull(message = "主键不能为空")
											@PathVariable("fAcctId") Integer fAcctId) {
		return AjaxResult.success(iTBdAccountService.queryById(fAcctId));
	}

	/**
	 * 新增科目信息
	 */
	@ApiOperation("新增科目信息")
	@PreAuthorize("@ss.hasPermi('finance:account:add')")
	@Log(title = "科目信息", businessType = BusinessType.INSERT)
	@RepeatSubmit
	@PostMapping("/add")
	public AjaxResult<Void> add(@Validated @RequestBody TBdAccountAddBo bo) {
		return toAjax(iTBdAccountService.insertByAddBo(bo) ? 1 : 0);
	}

	/**
	 * 修改科目信息
	 */
	@ApiOperation("修改科目信息")
	@PreAuthorize("@ss.hasPermi('finance:account:edit')")
	@Log(title = "科目信息", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/edit")
	public AjaxResult<Void> edit(@Validated @RequestBody TBdAccountEditBo bo) {
		return toAjax(iTBdAccountService.updateByEditBo(bo) ? 1 : 0);
	}

	/**
	 * 删除科目信息
	 */
	@ApiOperation("删除科目信息")
	@PreAuthorize("@ss.hasPermi('finance:account:remove')")
	@Log(title = "科目信息", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fAcctIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable Long[] fAcctIds) {
		return toAjax(iTBdAccountService.deleteWithValidByIds(Arrays.asList(fAcctIds), true) ? 1 : 0);
	}


	/**
	 * 审核科目信息
	 */
	@ApiOperation("审核科目信息")
	@PreAuthorize("@ss.hasPermi('finance:account:audit')")
	@Log(title = "科目信息", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotEmpty(message = "主键不能为空")
									  @PathVariable Integer[] fIds) {
		return toAjax(baseTableService.audit(Arrays.asList(fIds)) ? 1 : 0);
	}

	/**
	 * 禁用科目信息
	 */
	@ApiOperation("禁用科目信息")
	@PreAuthorize("@ss.hasPermi('finance:account:disable')")
	@Log(title = "科目信息", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/disable")
	public AjaxResult<Void> disable(@Validated @RequestBody DisableBo bo) {
		return toAjax(baseTableService.disable(bo) ? 1 : 0);
	}



	/**
	 * 科目表查询。查询关联的会计要素，关联科目类别
	 */
	@ApiOperation("科目表查询。查询关联的会计要素，关联科目类别")
	@PreAuthorize("@ss.hasPermi('finance:account:listAcountTable')")
	@PostMapping("/listAcountTable")
	public List<AccountTableVo> listAcountTable() {
		return iTBdAccountService.listAcountTable();
	}
}
