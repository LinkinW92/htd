package com.skeqi.finance.controller.cashflow;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.annotation.Log;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.finance.pojo.vo.cashflow.TGlVoucherEntryCashVo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryCashQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryCashAddBo;
import com.skeqi.finance.service.cashflow.ITGlVoucherEntryCashService;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证分录现金流量Controller
 *
 * @author toms
 * @date 2021-07-21
 */
@Api(value = "凭证分录现金流量控制器", tags = {"凭证分录现金流量管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/cashFlow/voucherEntryCash")
public class TGlVoucherEntryCashController extends BaseController {

    private final ITGlVoucherEntryCashService iTGlVoucherEntryCashService;

    /**
     * 查询现金流量列表
     */
    @ApiOperation("查询现金流量列表")
    @PreAuthorize("@ss.hasPermi('finance:cashFlow:voucherEntryCash:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlVoucherEntryCashVo> list(@Validated @RequestBody TGlVoucherEntryCashQueryBo bo) {
        return iTGlVoucherEntryCashService.queryPageList(bo);
    }


    /**
     * 新增凭证分录现金流量
     */
    @ApiOperation("新增凭证分录现金流量")
    @PreAuthorize("@ss.hasPermi('finance:cashFlow:voucherEntryCash:add')")
    @Log(title = "凭证分录现金流量", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlVoucherEntryCashAddBo bo) {
        return toAjax(iTGlVoucherEntryCashService.insertByAddBo(bo) ? 1 : 0);
    }
}
