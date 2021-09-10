package com.skeqi.finance.controller.basicinformation.accountingsystem;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

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
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAcctSysDetailVo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysDetailQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysDetailAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysDetailEditBo;
import com.skeqi.finance.service.basicinformation.accountingsystem.ITOrgAcctSysDetailService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会计核算体系之下级组织Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "会计核算体系之下级组织控制器", tags = {"会计核算体系之下级组织管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/detail")
public class TOrgAcctSysDetailController extends BaseController {

    private final ITOrgAcctSysDetailService iTOrgAcctSysDetailService;

    /**
     * 查询会计核算体系之下级组织列表
     */
    @ApiOperation("查询会计核算体系之下级组织列表")
    @PreAuthorize("@ss.hasPermi('finance:detail:list')")
    @PostMapping("/list")
    public TableDataInfo<TOrgAcctSysDetailVo> list(@Validated TOrgAcctSysDetailQueryBo bo) {
        return iTOrgAcctSysDetailService.queryPageList(bo);
    }

    /**
     * 导出会计核算体系之下级组织列表
     */
    @ApiOperation("导出会计核算体系之下级组织列表")
    @PreAuthorize("@ss.hasPermi('finance:detail:export')")
    @Log(title = "会计核算体系之下级组织", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TOrgAcctSysDetailVo> export(@Validated TOrgAcctSysDetailQueryBo bo) {
        List<TOrgAcctSysDetailVo> list = iTOrgAcctSysDetailService.queryList(bo);
        ExcelUtil<TOrgAcctSysDetailVo> util = new ExcelUtil<TOrgAcctSysDetailVo>(TOrgAcctSysDetailVo.class);
        return util.exportExcel(list, "会计核算体系之下级组织");
    }

    /**
     * 获取会计核算体系之下级组织详细信息
     */
    @ApiOperation("获取会计核算体系之下级组织详细信息")
    @PreAuthorize("@ss.hasPermi('finance:detail:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TOrgAcctSysDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTOrgAcctSysDetailService.queryById(fId));
    }

    /**
     * 新增会计核算体系之下级组织
     */
    @ApiOperation("新增会计核算体系之下级组织")
    @PreAuthorize("@ss.hasPermi('finance:detail:add')")
    @Log(title = "会计核算体系之下级组织", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TOrgAcctSysDetailAddBo bo) {
        return toAjax(iTOrgAcctSysDetailService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改会计核算体系之下级组织
     */
    @ApiOperation("修改会计核算体系之下级组织")
    @PreAuthorize("@ss.hasPermi('finance:detail:edit')")
    @Log(title = "会计核算体系之下级组织", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TOrgAcctSysDetailEditBo bo) {
        return toAjax(iTOrgAcctSysDetailService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除会计核算体系之下级组织
     */
    @ApiOperation("删除会计核算体系之下级组织")
    @PreAuthorize("@ss.hasPermi('finance:detail:remove')")
    @Log(title = "会计核算体系之下级组织" , businessType = BusinessType.DELETE)
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTOrgAcctSysDetailService.deleteWithValidByIds(integerList, true) ? 1 : 0);
    }
}
