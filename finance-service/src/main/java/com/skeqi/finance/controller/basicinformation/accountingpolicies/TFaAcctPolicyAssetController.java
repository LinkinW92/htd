package com.skeqi.finance.controller.basicinformation.accountingpolicies;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyOrgVo;
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
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyAssetVo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAssetQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAssetAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAssetEditBo;
import com.skeqi.finance.service.basicinformation.accountingpolicies.ITFaAcctPolicyAssetService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会计政策资产政策Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "会计政策资产政策控制器", tags = {"会计政策资产政策管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/asset")
public class TFaAcctPolicyAssetController extends BaseController {

    private final ITFaAcctPolicyAssetService iTFaAcctPolicyAssetService;

    /**
     * 按会计政策id查询资产政策列表
     */
    @ApiOperation("按会计政策id查询资产政策列表")
    @PreAuthorize("@ss.hasPermi('finance:asset:listByFid')")
	@PostMapping("/listByFid/{fAcctpolicyId}")
	public AjaxResult<List<TFaAcctPolicyAssetVo>> list(@NotNull(message = "主键不能为空") @PathVariable Integer fAcctpolicyId) {
		return AjaxResult.success(iTFaAcctPolicyAssetService.queryListByFAcctpolicyId(fAcctpolicyId));
	}

    /**
     * 导出会计政策资产政策列表
     */
    @ApiOperation("导出会计政策资产政策列表")
    @PreAuthorize("@ss.hasPermi('finance:asset:export')")
    @Log(title = "会计政策资产政策", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TFaAcctPolicyAssetVo> export(@Validated TFaAcctPolicyAssetQueryBo bo) {
        List<TFaAcctPolicyAssetVo> list = iTFaAcctPolicyAssetService.queryList(bo);
        ExcelUtil<TFaAcctPolicyAssetVo> util = new ExcelUtil<TFaAcctPolicyAssetVo>(TFaAcctPolicyAssetVo.class);
        return util.exportExcel(list, "会计政策资产政策");
    }

    /**
     * 获取会计政策资产政策详细信息
     */
    @ApiOperation("获取会计政策资产政策详细信息")
    @PreAuthorize("@ss.hasPermi('finance:asset:query')")
    @PostMapping("/{fEntryId}")
    public AjaxResult<TFaAcctPolicyAssetVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fEntryId") Integer fEntryId) {
        return AjaxResult.success(iTFaAcctPolicyAssetService.queryById(fEntryId));
    }

    /**
     * 新增会计政策资产政策
     */
    @ApiOperation("新增会计政策资产政策")
    @PreAuthorize("@ss.hasPermi('finance:asset:add')")
    @Log(title = "会计政策资产政策", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TFaAcctPolicyAssetAddBo bo) {
        return toAjax(iTFaAcctPolicyAssetService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改会计政策资产政策
     */
    @ApiOperation("修改会计政策资产政策")
    @PreAuthorize("@ss.hasPermi('finance:asset:edit')")
    @Log(title = "会计政策资产政策", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TFaAcctPolicyAssetEditBo bo) {
        return toAjax(iTFaAcctPolicyAssetService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除会计政策资产政策
     */
    @ApiOperation("删除会计政策资产政策")
    @PreAuthorize("@ss.hasPermi('finance:asset:remove')")
    @Log(title = "会计政策资产政策" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fEntryIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fEntryIds") String fEntryIds) {
		List<Integer> integerList= Arrays.asList(fEntryIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTFaAcctPolicyAssetService.deleteWithValidByIds(integerList, true) ? 1 : 0);
    }
}
