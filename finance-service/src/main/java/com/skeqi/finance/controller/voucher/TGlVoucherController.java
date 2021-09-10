package com.skeqi.finance.controller.voucher;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import cn.hutool.json.JSONUtil;
import com.skeqi.finance.enums.BaseEnum;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceQueryBo;
import com.skeqi.finance.pojo.bo.cashflow.TGlVoucherCashFlowBo;
import com.skeqi.finance.pojo.bo.voucher.*;
import com.skeqi.finance.pojo.vo.voucher.VoucherPageVo;
import com.skeqi.finance.pojo.vo.voucher.VoucherVo;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.annotation.Log;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherVo;
import com.skeqi.finance.service.basicinformation.voucher.ITGlVoucherService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证录入主Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "凭证录入主控制器", tags = {"凭证录入主管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/voucher")
@Slf4j
public class TGlVoucherController extends BaseController {

    private final ITGlVoucherService iTGlVoucherService;

	/**
	 * 查询明细类帐列表
	 */
	@ApiOperation("查询明细类帐列表")
	@PreAuthorize("@ss.hasPermi('finance:voucher:list')")
	@PostMapping("/listDetail")
	public AjaxResult listDetail(@Validated @RequestBody BalanceVchDetailQueryBo bo) {
		return iTGlVoucherService.listDetail(bo);
	}

    /**
     * 查询凭证录入主列表
     */
    @ApiOperation("查询凭证录入主列表")
    @PreAuthorize("@ss.hasPermi('finance:voucher:list')")
    @PostMapping("/list")
    public TableDataInfo<VoucherPageVo> list(@Validated @RequestBody TGlVoucherQueryBo bo) {
        return iTGlVoucherService.queryPageList(bo);
    }

    /**
     * 导出凭证录入主列表
     */
    @ApiOperation("导出凭证录入主列表")
    @PreAuthorize("@ss.hasPermi('finance:voucher:export')")
    @Log(title = "凭证录入主", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlVoucherVo> export(@Validated @RequestBody TGlVoucherQueryBo bo) {
        List<TGlVoucherVo> list = iTGlVoucherService.queryList(bo);
        ExcelUtil<TGlVoucherVo> util = new ExcelUtil<TGlVoucherVo>(TGlVoucherVo.class);
        return util.exportExcel(list, "凭证录入主");
    }

    /**
     * 获取凭证录入主详细信息
     */
    @ApiOperation("获取凭证录入主详细信息")
    @PreAuthorize("@ss.hasPermi('finance:voucher:query')")
    @GetMapping("/getInfo/{fvoucherId}")
    public AjaxResult<VoucherVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fvoucherId") Integer fVoucherId) {
        return AjaxResult.success(iTGlVoucherService.queryById(fVoucherId));
    }

    /**
     * 新增凭证录入主
     */
    @ApiOperation("新增凭证录入主")
    @PreAuthorize("@ss.hasPermi('finance:voucher:add')")
    @Log(title = "凭证录入主", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TGlVoucherAddBo bo) {
    	log.info("【新增凭证录入】入参：[{}]", JSONUtil.toJsonStr(bo));
		bo.setFInternalind(BaseEnum.YES.getCode());
        return iTGlVoucherService.insertByAddBo(bo);
    }




	/**
	 * 凭证指定现金流量
	 */
	@ApiOperation("凭证指定现金流量")
	@PreAuthorize("@ss.hasPermi('finance:voucher:add')")
	@Log(title = "凭证指定现金流量", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/assignCashFlow")
	public AjaxResult<Void> assignCashFlow(@Validated @RequestBody List<TGlVoucherCashFlowBo> list) {
		log.info("【凭证指定现金流量】入参：[{}]", JSONUtil.toJsonStr(list));
		return iTGlVoucherService.assignCashFlow(list);
	}

	/**
	 * 查询凭证指定现金流量
	 */
	@ApiOperation("查询凭证指定现金流量")
	@PreAuthorize("@ss.hasPermi('finance:voucher:list')")
	@PostMapping("/getCashFlow/{fId}")
	public AjaxResult<Void> getCashFlow(@NotNull(message = "凭证编码不能为空")
											@PathVariable("fId") Integer fId) {
		return iTGlVoucherService.getCashFlow(fId);
	}


	/**
	 * 自动指定现金流量
	 */
	@ApiOperation("自动指定现金流量")
	@PreAuthorize("@ss.hasPermi('finance:voucher:list')")
	@PostMapping("/autoAssign/{fId}")
	public AjaxResult<Void> autoAssign(@NotNull(message = "编码不能为空")
										@PathVariable("fId") Integer fId) {
		return iTGlVoucherService.autoAssign(fId);
	}

    /**
     * 修改凭证录入主
     */
    @ApiOperation("修改凭证录入主")
    @PreAuthorize("@ss.hasPermi('finance:voucher:edit')")
    @Log(title = "凭证录入主", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TGlVoucherEditBo bo) {
		log.info("【修改凭证】入参：[{}]", JSONUtil.toJsonStr(bo));
        return toAjax(iTGlVoucherService.updateByEditBo(bo) ? 1 : 0);
    }

	/**
	 * 保存凭证
	 */
	@ApiOperation("保存凭证")
	@PreAuthorize("@ss.hasPermi('finance:voucher:edit')")
	@Log(title = "保存凭证", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/save/{fId}")
	public AjaxResult<Void> save(@NotNull(message = "凭证编码不能为空")
									 @PathVariable("fId") Integer fId) {
		return iTGlVoucherService.save(fId);
	}

	/**
	 * 凭证审核
	 */
	@ApiOperation("凭证审核")
	@PreAuthorize("@ss.hasPermi('finance:voucher:edit')")
	@Log(title = "凭证审核", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/audit/{fId}")
	public AjaxResult<Void> audit(@NotNull(message = "凭证编码不能为空")
									  @PathVariable("fId") Integer fId) {
		return iTGlVoucherService.audit(fId);
	}

	/**
	 * 凭证反审核
	 */
	@ApiOperation("凭证反审核")
	@PreAuthorize("@ss.hasPermi('finance:voucher:edit')")
	@Log(title = "凭证反审核", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/auditNo/{fId}")
	public AjaxResult<Void> auditNo(@NotNull(message = "凭证编码不能为空")
								  @PathVariable("fId") Integer fId) {
		return iTGlVoucherService.auditNo(fId);
	}

	/**
	 * 作废
	 */
	@ApiOperation("作废")
	@PreAuthorize("@ss.hasPermi('finance:voucher:edit')")
	@Log(title = "作废", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/invalid/{fId}")
	public AjaxResult<Void> invalid(@NotNull(message = "凭证编码不能为空")
										@PathVariable("fId") Integer fId) {
		return iTGlVoucherService.invalid(fId);
	}


	/**
	 * 凭证过账
	 */
	@ApiOperation("凭证过账")
	@PreAuthorize("@ss.hasPermi('finance:voucher:edit')")
	@Log(title = "凭证过账", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/voucherPost/{ids}")
	public AjaxResult<Void> voucherPost(@NotEmpty(message = "主键不能为空")
											@PathVariable("ids") String ids) {
		List<Integer> s= Arrays.asList(ids.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return iTGlVoucherService.voucherPost(s);
	}

    /**
     * 删除凭证录入主
     */
    @ApiOperation("删除凭证录入主")
    @PreAuthorize("@ss.hasPermi('finance:voucher:remove')")
    @Log(title = "凭证录入主" , businessType = BusinessType.DELETE)
    @PostMapping("remove/{fIds}")
	public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
								   @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTGlVoucherService.deleteWithValidByIds(s, true) ? 1 : 0);
    }


}
