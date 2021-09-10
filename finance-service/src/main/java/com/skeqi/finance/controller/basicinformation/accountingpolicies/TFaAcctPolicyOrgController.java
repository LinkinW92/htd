package com.skeqi.finance.controller.basicinformation.accountingpolicies;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAcctSysEntryVo;
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
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyOrgVo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgEditBo;
import com.skeqi.finance.service.basicinformation.accountingpolicies.ITFaAcctPolicyOrgService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会计政策适用核算组织Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "会计政策适用核算组织控制器", tags = {"会计政策适用核算组织管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/org")
public class TFaAcctPolicyOrgController extends BaseController {

    private final ITFaAcctPolicyOrgService iTFaAcctPolicyOrgService;

    /**
     * 按会计政策id查询会计政策适用核算组织列表
     */
    @ApiOperation("按会计政策id查询会计政策适用核算组织列表")
    @PreAuthorize("@ss.hasPermi('finance:org:listByFid')")
	@PostMapping("/listByFid")
	public AjaxResult<List<TFaAcctPolicyOrgVo>> list(@Validated @RequestBody TFaAcctPolicyOrgQueryBo bo) {
		return AjaxResult.success(iTFaAcctPolicyOrgService.queryList(bo));
	}

    /**
     * 导出会计政策适用核算组织列表
     */
    @ApiOperation("导出会计政策适用核算组织列表")
    @PreAuthorize("@ss.hasPermi('finance:org:export')")
    @Log(title = "会计政策适用核算组织", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TFaAcctPolicyOrgVo> export(@Validated TFaAcctPolicyOrgQueryBo bo) {
        List<TFaAcctPolicyOrgVo> list = iTFaAcctPolicyOrgService.queryList(bo);
        ExcelUtil<TFaAcctPolicyOrgVo> util = new ExcelUtil<TFaAcctPolicyOrgVo>(TFaAcctPolicyOrgVo.class);
        return util.exportExcel(list, "会计政策适用核算组织");
    }

    /**
     * 获取会计政策适用核算组织详细信息
     */
    @ApiOperation("获取会计政策适用核算组织详细信息")
    @PreAuthorize("@ss.hasPermi('finance:org:query')")
    @PostMapping("/{fEntryId}")
    public AjaxResult<TFaAcctPolicyOrgVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fEntryId") Integer fEntryId) {
        return AjaxResult.success(iTFaAcctPolicyOrgService.queryById(fEntryId));
    }

    /**
     * 新增会计政策适用核算组织
     */
    @ApiOperation("新增会计政策适用核算组织")
    @PreAuthorize("@ss.hasPermi('finance:org:add')")
    @Log(title = "会计政策适用核算组织", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TFaAcctPolicyOrgAddBo bo) {
        return toAjax(iTFaAcctPolicyOrgService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改会计政策适用核算组织
     */
    @ApiOperation("修改会计政策适用核算组织")
    @PreAuthorize("@ss.hasPermi('finance:org:edit')")
    @Log(title = "会计政策适用核算组织", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TFaAcctPolicyOrgEditBo bo) {
        return toAjax(iTFaAcctPolicyOrgService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除会计政策适用核算组织
     */
    @ApiOperation("删除会计政策适用核算组织")
    @PreAuthorize("@ss.hasPermi('finance:org:remove')")
    @Log(title = "会计政策适用核算组织" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fEntryIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fEntryIds") String fEntryIds) {
		List<Integer> integerList= Arrays.asList(fEntryIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTFaAcctPolicyOrgService.deleteWithValidByIds(integerList, true) ? 1 : 0);
    }
}
