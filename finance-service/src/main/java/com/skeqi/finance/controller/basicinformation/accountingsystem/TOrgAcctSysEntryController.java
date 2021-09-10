package com.skeqi.finance.controller.basicinformation.accountingsystem;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVchgroupAcctVo;
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
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAcctSysEntryVo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryEditBo;
import com.skeqi.finance.service.basicinformation.accountingsystem.ITOrgAcctSysEntryService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会计核算体系之会计主体Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "会计核算体系之会计主体控制器", tags = {"会计核算体系之会计主体管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/acctSysEntry")
public class TOrgAcctSysEntryController extends BaseController {

    private final ITOrgAcctSysEntryService iTOrgAcctSysEntryService;

    /**
     * 按核算体系id查询会计主体列表
     */
    @ApiOperation("按核算体系id查询会计主体列表")
    @PreAuthorize("@ss.hasPermi('finance:entry:listByFid')")
    @PostMapping("/listByFid/{fAcctsystemId}")
    public AjaxResult<List<TOrgAcctSysEntryVo>> list(@NotNull(message = "主键不能为空")
														 @PathVariable("fAcctsystemId") Integer fAcctsystemId) {
        return AjaxResult.success(iTOrgAcctSysEntryService.queryListByFAcctsystemId(fAcctsystemId));
    }

    /**
     * 导出会计核算体系之会计主体列表
     */
    @ApiOperation("导出会计核算体系之会计主体列表")
    @PreAuthorize("@ss.hasPermi('finance:entry:export')")
    @Log(title = "会计核算体系之会计主体", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TOrgAcctSysEntryVo> export(@Validated TOrgAcctSysEntryQueryBo bo) {
        List<TOrgAcctSysEntryVo> list = iTOrgAcctSysEntryService.queryList(bo);
        ExcelUtil<TOrgAcctSysEntryVo> util = new ExcelUtil<TOrgAcctSysEntryVo>(TOrgAcctSysEntryVo.class);
        return util.exportExcel(list, "会计核算体系之会计主体");
    }

    /**
     * 获取会计核算体系之会计主体详细信息
     */
    @ApiOperation("获取会计核算体系之会计主体详细信息")
    @PreAuthorize("@ss.hasPermi('finance:entry:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TOrgAcctSysEntryVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTOrgAcctSysEntryService.queryById(fId));
    }

    /**
     * 新增会计核算体系之会计主体
     */
    @ApiOperation("新增会计核算体系之会计主体")
    @PreAuthorize("@ss.hasPermi('finance:entry:add')")
    @Log(title = "会计核算体系之会计主体", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TOrgAcctSysEntryAddBo bo) {
        return toAjax(iTOrgAcctSysEntryService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改会计核算体系之会计主体
     */
    @ApiOperation("修改会计核算体系之会计主体")
    @PreAuthorize("@ss.hasPermi('finance:entry:edit')")
    @Log(title = "会计核算体系之会计主体", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TOrgAcctSysEntryEditBo bo) {
        return toAjax(iTOrgAcctSysEntryService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除会计核算体系之会计主体
     */
    @ApiOperation("删除会计核算体系之会计主体")
    @PreAuthorize("@ss.hasPermi('finance:entry:remove')")
    @Log(title = "会计核算体系之会计主体" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTOrgAcctSysEntryService.deleteWithValidByIds(integerList, true) ? 1 : 0);
    }
}
