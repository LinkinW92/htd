package com.skeqi.finance.controller.accountbook;

import com.skeqi.common.annotation.Log;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.finance.pojo.bo.ABTotalBalanceQueryBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceQueryBo;
import com.skeqi.finance.pojo.vo.ABTotalBalanceVo;
import com.skeqi.finance.pojo.vo.TGlBalanceVo;
import com.skeqi.finance.service.report.ITGlBalanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 总账Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "总账控制器", tags = {"总账管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/totalbalance")
public class ABTotalBalanceController extends BaseController {

    private final ITGlBalanceService iTGlBalanceService;

    /**
     * 查询总账列表
     */
    @ApiOperation("查询总账列表")
    @PreAuthorize("@ss.hasPermi('finance:totalbalance:list')")
    @PostMapping("/list")
    public TableDataInfo<ABTotalBalanceVo> list(@Validated @RequestBody ABTotalBalanceQueryBo bo) {
        return iTGlBalanceService.queryABTotalBalance(bo);
    }


}
