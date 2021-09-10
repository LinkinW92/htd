package com.skeqi.finance.controller.basicinformation.cashflow;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAssetQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyAssetVo;
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
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowItemTableVo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowItemTableQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowItemTableAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowItemTableEditBo;
import com.skeqi.finance.service.basicinformation.cashflow.ITGlCashFlowItemTableService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 现金流量项目-1Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "现金流量项目-1控制器", tags = {"现金流量项目-1管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/cashFlow")
public class TGlCashFlowItemTableController extends BaseController {

    private final ITGlCashFlowItemTableService iTGlCashFlowItemTableService;

    /**
     * 查询现金流量项目-1列表
     */
    @ApiOperation("查询现金流量项目-1列表")
    @PreAuthorize("@ss.hasPermi('finance:table:list')")
	@PostMapping("/list")
	public AjaxResult<List<TGlCashFlowItemTableVo>> list(@Validated @RequestBody TGlCashFlowItemTableQueryBo bo) {
		return AjaxResult.success(iTGlCashFlowItemTableService.queryList(bo));
	}


    /**
     * 新增现金流量项目-1
     */
    @ApiOperation("新增现金流量项目-1")
    @PreAuthorize("@ss.hasPermi('finance:table:add')")
    @Log(title = "现金流量项目-1", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TGlCashFlowItemTableAddBo bo) {
        return toAjax(iTGlCashFlowItemTableService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改现金流量项目-1
     */
    @ApiOperation("修改现金流量项目-1")
    @PreAuthorize("@ss.hasPermi('finance:table:edit')")
    @Log(title = "现金流量项目-1", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TGlCashFlowItemTableEditBo bo) {
        return toAjax(iTGlCashFlowItemTableService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除现金流量项目-1
     */
    @ApiOperation("删除现金流量项目-1")
    @PreAuthorize("@ss.hasPermi('finance:table:remove')")
    @Log(title = "现金流量项目-1" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTGlCashFlowItemTableService.deleteWithValidById(integerList) ? 1 : 0);
    }
}
