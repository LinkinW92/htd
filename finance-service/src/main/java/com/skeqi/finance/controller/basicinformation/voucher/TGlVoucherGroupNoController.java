package com.skeqi.finance.controller.basicinformation.voucher;

import java.util.List;
import java.util.Arrays;

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
import com.skeqi.finance.pojo.vo.TGlVoucherGroupNoVo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherGroupNoQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherGroupNoAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherGroupNoEditBo;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherGroupNoService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证号排序Controller
 *
 * @author toms
 * @date 2021-08-09
 */
@Api(value = "凭证号排序控制器", tags = {"凭证号排序管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/voucherGroupNo")
public class TGlVoucherGroupNoController extends BaseController {

    private final ITGlVoucherGroupNoService iTGlVoucherGroupNoService;

    /**
     * 查询凭证号排序列表
     */
    @ApiOperation("查询凭证号排序列表")
    @PreAuthorize("@ss.hasPermi('finance:voucherGroupNo:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlVoucherGroupNoVo> list(@Validated @RequestBody  TGlVoucherGroupNoQueryBo bo) {
        return iTGlVoucherGroupNoService.queryPageList(bo);
    }

	/**
	 * 查询凭证号排序列表
	 */
	@ApiOperation("查询凭证号排序列表")
	@PreAuthorize("@ss.hasPermi('finance:voucherGroupNo:list')")
	@PostMapping("/listVchNo")
	public AjaxResult listVchNo(@Validated @RequestBody  TGlVoucherGroupNoQueryBo bo) {
		return AjaxResult.success(iTGlVoucherGroupNoService.listVchNo(bo));
	}

    /**
     * 导出凭证号排序列表
     */
    @ApiOperation("导出凭证号排序列表")
    @PreAuthorize("@ss.hasPermi('finance:voucherGroupNo:export')")
    @Log(title = "凭证号排序", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlVoucherGroupNoVo> export(@Validated @RequestBody  TGlVoucherGroupNoQueryBo bo) {
        List<TGlVoucherGroupNoVo> list = iTGlVoucherGroupNoService.queryList(bo);
        ExcelUtil<TGlVoucherGroupNoVo> util = new ExcelUtil<TGlVoucherGroupNoVo>(TGlVoucherGroupNoVo.class);
        return util.exportExcel(list, "凭证号排序");
    }

    /**
     * 获取凭证号排序详细信息
     */
    @ApiOperation("获取凭证号排序详细信息")
    @PreAuthorize("@ss.hasPermi('finance:voucherGroupNo:query')")
    @GetMapping("/{fId}")
    public AjaxResult<TGlVoucherGroupNoVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTGlVoucherGroupNoService.queryById(fId));
    }

    /**
     * 新增凭证号排序
     */
    @ApiOperation("新增凭证号排序")
    @PreAuthorize("@ss.hasPermi('finance:voucherGroupNo:add')")
    @Log(title = "凭证号排序", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlVoucherGroupNoAddBo bo) {
        return toAjax(iTGlVoucherGroupNoService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证号排序
     */
    @ApiOperation("修改凭证号排序")
    @PreAuthorize("@ss.hasPermi('finance:voucherGroupNo:edit')")
    @Log(title = "凭证号排序", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlVoucherGroupNoEditBo bo) {
        return toAjax(iTGlVoucherGroupNoService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证号排序
     */
    @ApiOperation("删除凭证号排序")
    @PreAuthorize("@ss.hasPermi('finance:voucherGroupNo:remove')")
    @Log(title = "凭证号排序" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fIds) {
        return toAjax(iTGlVoucherGroupNoService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
