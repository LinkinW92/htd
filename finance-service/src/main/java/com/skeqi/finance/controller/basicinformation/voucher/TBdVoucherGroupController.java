package com.skeqi.finance.controller.basicinformation.voucher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.skeqi.common.utils.time.StringUtil;
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
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVoucherGroupVo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupEditBo;
import com.skeqi.finance.service.basicinformation.voucher.ITBdVoucherGroupService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证字Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "凭证字控制器", tags = {"凭证字管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/voucherGroup")
public class TBdVoucherGroupController extends BaseController {

    private final ITBdVoucherGroupService iTBdVoucherGroupService;

    /**
     * 查询凭证字列表
     */
    @ApiOperation("查询凭证字列表")
    @PreAuthorize("@ss.hasPermi('finance:group:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdVoucherGroupVo> list(@Validated @RequestBody TBdVoucherGroupQueryBo bo) {
        return iTBdVoucherGroupService.queryPageList(bo);
    }

    /**
     * 导出凭证字列表
     */
    @ApiOperation("导出凭证字列表")
    @PreAuthorize("@ss.hasPermi('finance:group:export')")
    @Log(title = "凭证字", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdVoucherGroupVo> export(@Validated TBdVoucherGroupQueryBo bo) {
        List<TBdVoucherGroupVo> list = iTBdVoucherGroupService.queryList(bo);
        ExcelUtil<TBdVoucherGroupVo> util = new ExcelUtil<TBdVoucherGroupVo>(TBdVoucherGroupVo.class);
        return util.exportExcel(list, "凭证字");
    }

    /**
     * 获取凭证字详细信息
     */
    @ApiOperation("获取凭证字详细信息")
    @PreAuthorize("@ss.hasPermi('finance:group:query')")
    @PostMapping("/{fVchgroupId}")
    public AjaxResult<TBdVoucherGroupVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable("fVchgroupId") Integer fVchgroupId) {
        return AjaxResult.success(iTBdVoucherGroupService.queryById(fVchgroupId));
    }

    /**
     * 新增凭证字
     */
    @ApiOperation("新增凭证字")
    @PreAuthorize("@ss.hasPermi('finance:group:add')")
    @Log(title = "凭证字", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdVoucherGroupAddBo bo) {
        return toAjax(iTBdVoucherGroupService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证字
     */
    @ApiOperation("修改凭证字")
    @PreAuthorize("@ss.hasPermi('finance:group:edit')")
    @Log(title = "凭证字", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TBdVoucherGroupEditBo bo) {
        return toAjax(iTBdVoucherGroupService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证字
     */
    @ApiOperation("删除凭证字")
    @PreAuthorize("@ss.hasPermi('finance:group:remove')")
    @Log(title = "凭证字" , businessType = BusinessType.DELETE)
    @PostMapping("/remove/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
									   @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTBdVoucherGroupService.deleteWithValidByIds(integerList) ? 1 : 0);
    }

	/**
	 * 审核凭证字
	 */
	@ApiOperation("审核凭证字")
	@PreAuthorize("@ss.hasPermi('finance:group:audit')")
	@Log(title = "凭证字" , businessType = BusinessType.UPDATE)
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTBdVoucherGroupService.auditByIds(integerList) ? 1 : 0);
	}

	/**
	 * 反审核凭证字
	 */
	@ApiOperation("反审核凭证字")
	@PreAuthorize("@ss.hasPermi('finance:group:antiAudit')")
	@Log(title = "凭证字" , businessType = BusinessType.UPDATE)
	@PostMapping("/antiAudit/{fIds}")
	public AjaxResult<Void> antiAuditByIds(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTBdVoucherGroupService.antiAuditByIds(integerList) ? 1 : 0);
	}

	/**
	 * 禁用凭证字
	 */
	@ApiOperation("禁用凭证字")
	@PreAuthorize("@ss.hasPermi('finance:group:disable')")
	@Log(title = "凭证字" , businessType = BusinessType.UPDATE)
	@PostMapping("/disable")
	public AjaxResult<Void> disable(@RequestBody TBdVoucherGroupEditBo bo) {
		return toAjax(iTBdVoucherGroupService.disableByEditBo(bo) ? 1 : 0);
	}
}
