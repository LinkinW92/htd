package com.skeqi.finance.controller.init;

import com.skeqi.common.annotation.Log;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookQueryBo;
import com.skeqi.finance.service.basicinformation.accountbook.ITBdAccountBookService;
import com.skeqi.finance.service.init.IGeneralLedgerInitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;

/**
 * 科目初始录入数据Controller
 *
 * @author toms
 * @date 2021-07-09
 */

@Api(value = "总账初始化控制器", tags = {"总账初始化数据管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/generallLedger/init")
public class GeneralLedgerInitController extends BaseController {

	private final IGeneralLedgerInitService iGeneralLedgerInitService;
	//账簿业务类
	private final ITBdAccountBookService itBdAccountBookService;

//	/**
//	 * 查询科目初始录入数据列表
//	 */
//	@ApiOperation("查询总账初始化数据列表")
//	@PreAuthorize("@ss.hasPermi('finance:generallLedger:init:list')")
//	@PostMapping("/list")
//	public AjaxResult list(@Validated @RequestBody TBdAccountBookQueryBo bo) {
//		return AjaxResult.success(itBdAccountBookService.queryList(bo));
//	}

	/**
	 * 初始化操作
	 */
	@ApiOperation("初始化操作")
	@PreAuthorize("@ss.hasPermi('finance:generallLedger:init:update')")
	@Log(title = "初始化状态结束", businessType = BusinessType.DELETE)
	@PostMapping("/endInit/{fIds}")
	public AjaxResult<Void> endInit(@NotEmpty(message = "主键不能为空")
										@PathVariable Integer[] fIds) {
		return toAjax(iGeneralLedgerInitService.endInitWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
	}

	/**
	 * 初始化操作
	 */
	@ApiOperation("反初始化操作")
	@PreAuthorize("@ss.hasPermi('finance:generallLedger:init:update2')")
	@Log(title = "反初始化状态结束", businessType = BusinessType.DELETE)
	@PostMapping("/notendInit/{fIds}")
	public AjaxResult<Void> notendInit(@NotEmpty(message = "主键不能为空")
									@PathVariable Integer[] fIds) {
		return toAjax(iGeneralLedgerInitService.notendInitWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
	}
}
