package com.skeqi.finance.controller.accountbook;

import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.pojo.bo.ABFlexItemPropertyDetailQueryBo;
import com.skeqi.finance.pojo.vo.FlexItemPropertyDetailVo;
import com.skeqi.finance.service.report.ITGlBalanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 总账Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "核算维度明细账控制器", tags = {"核算维度明细账"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/propertybalance")
public class ABFlexItemPropertyDetailController extends BaseController {

	private final ITGlBalanceService iTGlBalanceService;

	/**
	 * 查询总账列表
	 */
	@ApiOperation("查询核算维度明细列表")
	@PreAuthorize("@ss.hasPermi('finance:propertybalance:list')")
	@PostMapping("/list")
	public TableDataInfo<FlexItemPropertyDetailVo> list(@Validated @RequestBody ABFlexItemPropertyDetailQueryBo bo) {
		return iTGlBalanceService.queryFlexItemPropertyDetail(bo);
	}


}
