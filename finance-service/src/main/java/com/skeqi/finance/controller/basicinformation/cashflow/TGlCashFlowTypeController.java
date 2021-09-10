package com.skeqi.finance.controller.basicinformation.cashflow;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.skeqi.finance.pojo.bo.basicinformation.cashflow.*;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowItemTableVo;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowVo;
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
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowTypeVo;
import com.skeqi.finance.service.basicinformation.cashflow.ITGlCashFlowTypeService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 现金流量项目类别-2Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "现金流量项目类别-2控制器", tags = {"现金流量项目类别-2管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/cashFlowType")
public class TGlCashFlowTypeController extends BaseController {

    private final ITGlCashFlowTypeService iTGlCashFlowTypeService;

    /**
     * 查询现金流量项目类别-2列表
     */
    @ApiOperation("查询现金流量项目类别-2列表")
    @PreAuthorize("@ss.hasPermi('finance:type:list')")
	@PostMapping("/list")
	public AjaxResult<List<TGlCashFlowTypeVo>> list(@Validated @RequestBody TGlCashFlowTypeQueryBo bo) {
		return AjaxResult.success(iTGlCashFlowTypeService.queryList(bo));
	}

    /**
     * 新增现金流量项目类别-2
     */
    @ApiOperation("新增现金流量项目类别-2")
    @PreAuthorize("@ss.hasPermi('finance:type:add')")
    @Log(title = "现金流量项目类别-2", businessType = BusinessType.INSERT)
    @RepeatSubmit
	@PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TGlCashFlowTypeAddBo bo) {
        return toAjax(iTGlCashFlowTypeService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改现金流量项目类别-2
     */
    @ApiOperation("修改现金流量项目类别-2")
    @PreAuthorize("@ss.hasPermi('finance:type:edit')")
    @Log(title = "现金流量项目类别-2", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TGlCashFlowTypeEditBo bo) {
        return toAjax(iTGlCashFlowTypeService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除现金流量项目类别-2
     */
    @ApiOperation("删除现金流量项目类别-2")
    @PreAuthorize("@ss.hasPermi('finance:type:remove')")
    @Log(title = "现金流量项目类别-2" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTGlCashFlowTypeService.deleteWithValidById(integerList) ? 1 : 0);
    }
}
