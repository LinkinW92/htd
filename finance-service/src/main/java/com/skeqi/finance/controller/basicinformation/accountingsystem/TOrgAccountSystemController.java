package com.skeqi.finance.controller.basicinformation.accountingsystem;

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAccountSystem;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupEditBo;
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
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAccountSystemVo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemEditBo;
import com.skeqi.finance.service.basicinformation.accountingsystem.ITOrgAccountSystemService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会计核算体系Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "会计核算体系控制器", tags = {"会计核算体系管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/system")
public class TOrgAccountSystemController extends BaseController {

    private final ITOrgAccountSystemService iTOrgAccountSystemService;

    /**
     * 查询会计核算体系列表(会计主体列表为主表)
     */
    @ApiOperation("查询会计核算体系列表(会计主体列表为主表)")
    @PreAuthorize("@ss.hasPermi('finance:system:list')")
    @PostMapping("/list")
    public TableDataInfo<TOrgAccountSystemVo> list(@Validated @RequestBody TOrgAccountSystemQueryBo bo) {
        return iTOrgAccountSystemService.queryPageList(bo);
    }

	/**
	 * 查询会计核算体系列表
	 */
	@ApiOperation("查询会计核算体系列表")
	@PreAuthorize("@ss.hasPermi('finance:system:listAccountSystem')")
	@PostMapping("/listAccountSystem")
	public TableDataInfo<TOrgAccountSystemVo> listAccountSystem(@Validated @RequestBody TOrgAccountSystemQueryBo bo) {
		return iTOrgAccountSystemService.queryPageListAccountSystem(bo);
	}

    /**
     * 导出会计核算体系列表
     */
    @ApiOperation("导出会计核算体系列表")
    @PreAuthorize("@ss.hasPermi('finance:system:export')")
    @Log(title = "会计核算体系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TOrgAccountSystemVo> export(@Validated TOrgAccountSystemQueryBo bo) {
        List<TOrgAccountSystemVo> list = iTOrgAccountSystemService.queryList(bo);
        ExcelUtil<TOrgAccountSystemVo> util = new ExcelUtil<TOrgAccountSystemVo>(TOrgAccountSystemVo.class);
        return util.exportExcel(list, "会计核算体系");
    }

    /**
     * 获取会计核算体系详细信息
     */
    @ApiOperation("获取会计核算体系详细信息")
    @PreAuthorize("@ss.hasPermi('finance:system:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TOrgAccountSystemVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTOrgAccountSystemService.queryById(fId));
    }

    /**
     * 新增会计核算体系
     */
    @ApiOperation("新增会计核算体系")
    @PreAuthorize("@ss.hasPermi('finance:system:add')")
    @Log(title = "会计核算体系", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TOrgAccountSystemAddBo bo) {
        return toAjax(iTOrgAccountSystemService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改会计核算体系
     */
    @ApiOperation("修改会计核算体系")
    @PreAuthorize("@ss.hasPermi('finance:system:edit')")
    @Log(title = "会计核算体系", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TOrgAccountSystemEditBo bo) {
        return toAjax(iTOrgAccountSystemService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除会计核算体系
     */
    @ApiOperation("删除会计核算体系")
    @PreAuthorize("@ss.hasPermi('finance:system:remove')")
    @Log(title = "会计核算体系" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTOrgAccountSystemService.deleteWithValidByIds(integerList) ? 1 : 0);
    }


	/**
	 * 审核会计核算体系
	 */
	@ApiOperation("审核会计核算体系")
	@PreAuthorize("@ss.hasPermi('finance:system:audit')")
	@Log(title = "会计核算体系" , businessType = BusinessType.UPDATE)
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTOrgAccountSystemService.auditByIds(integerList) ? 1 : 0);
	}


	/**
	 * 反审核会计核算体系
	 */
	@ApiOperation("反审核会计核算体系")
	@PreAuthorize("@ss.hasPermi('finance:system:antiAudit')")
	@Log(title = "会计核算体系" , businessType = BusinessType.UPDATE)
	@PostMapping("/antiAudit/{fIds}")
	public AjaxResult<Void> antiAudit(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTOrgAccountSystemService.antiAuditByIds(integerList) ? 1 : 0);
	}

	/**
	 * 禁用会计核算体系
	 */
	@ApiOperation("禁用会计核算体系")
	@PreAuthorize("@ss.hasPermi('finance:system:disable')")
	@Log(title = "会计核算体系" , businessType = BusinessType.UPDATE)
	@PostMapping("/disable")
	public AjaxResult<Void> disable(@RequestBody TOrgAccountSystemEditBo bo) {
		return toAjax(iTOrgAccountSystemService.disableByEditBo(bo) ? 1 : 0);
	}
}
