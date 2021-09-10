package com.skeqi.finance.controller.basicinformation.depr;

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
import com.skeqi.finance.pojo.vo.basicinformation.depr.TFaDeprPolicyVo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprPolicyQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprPolicyAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprPolicyEditBo;
import com.skeqi.finance.service.depr.ITFaDeprPolicyService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 折旧政策Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "折旧政策控制器", tags = {"折旧政策管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/deprPolicy")
public class TFaDeprPolicyController extends BaseController {

    private final ITFaDeprPolicyService iTFaDeprPolicyService;

    /**
     * 查询折旧政策列表
     */
    @ApiOperation("查询折旧政策列表")
    @PreAuthorize("@ss.hasPermi('finance:policy:list')")
    @PostMapping("/list")
    public TableDataInfo<TFaDeprPolicyVo> list(@Validated TFaDeprPolicyQueryBo bo) {
        return iTFaDeprPolicyService.queryPageList(bo);
    }

    /**
     * 导出折旧政策列表
     */
    @ApiOperation("导出折旧政策列表")
    @PreAuthorize("@ss.hasPermi('finance:policy:export')")
    @Log(title = "折旧政策", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TFaDeprPolicyVo> export(@Validated TFaDeprPolicyQueryBo bo) {
        List<TFaDeprPolicyVo> list = iTFaDeprPolicyService.queryList(bo);
        ExcelUtil<TFaDeprPolicyVo> util = new ExcelUtil<TFaDeprPolicyVo>(TFaDeprPolicyVo.class);
        return util.exportExcel(list, "折旧政策");
    }

    /**
     * 获取折旧政策详细信息
     */
    @ApiOperation("获取折旧政策详细信息")
    @PreAuthorize("@ss.hasPermi('finance:policy:query')")
    @PostMapping("/{fPolicyId}")
    public AjaxResult<TFaDeprPolicyVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable("fPolicyId") Integer fPolicyId) {
        return AjaxResult.success(iTFaDeprPolicyService.queryById(fPolicyId));
    }

    /**
     * 新增折旧政策
     */
    @ApiOperation("新增折旧政策")
    @PreAuthorize("@ss.hasPermi('finance:policy:add')")
    @Log(title = "折旧政策", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TFaDeprPolicyAddBo bo) {
        return toAjax(iTFaDeprPolicyService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改折旧政策
     */
    @ApiOperation("修改折旧政策")
    @PreAuthorize("@ss.hasPermi('finance:policy:edit')")
    @Log(title = "折旧政策", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TFaDeprPolicyEditBo bo) {
        return toAjax(iTFaDeprPolicyService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除折旧政策
     */
    @ApiOperation("删除折旧政策")
    @PreAuthorize("@ss.hasPermi('finance:policy:remove')")
    @Log(title = "折旧政策" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fIds") String fIds) {
		List<Integer> integerList= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTFaDeprPolicyService.deleteWithValidByIds(integerList) ? 1 : 0);
	}

	/**
	 * 审核折旧政策
	 */
	@ApiOperation("审核折旧政策")
	@PreAuthorize("@ss.hasPermi('finance:policy:audit')")
	@Log(title = "折旧政策" , businessType = BusinessType.UPDATE)
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> ids= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTFaDeprPolicyService.auditByIds(ids) ? 1 : 0);
	}

	/**
	 * 反审折旧政策
	 */
	@ApiOperation("反审折旧政策")
	@PreAuthorize("@ss.hasPermi('finance:policy:antiAudit')")
	@Log(title = "折旧政策" , businessType = BusinessType.UPDATE)
	@PostMapping("/antiAudit/{fIds}")
	public AjaxResult<Void> antiAudit(@NotNull(message = "主键不能为空") @PathVariable("fIds") String fIds) {
		List<Integer> ids= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTFaDeprPolicyService.antiAuditByIds(ids) ? 1 : 0);
	}
}
