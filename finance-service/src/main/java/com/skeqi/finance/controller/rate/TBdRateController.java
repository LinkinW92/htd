package com.skeqi.finance.controller.rate;

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
import com.skeqi.finance.pojo.vo.basicinformation.rate.TBdRateVo;
import com.skeqi.finance.pojo.bo.rate.TBdRateQueryBo;
import com.skeqi.finance.pojo.bo.rate.TBdRateAddBo;
import com.skeqi.finance.pojo.bo.rate.TBdRateEditBo;
import com.skeqi.finance.service.rate.ITBdRateService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 基础汇率Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "基础汇率控制器", tags = {"基础汇率管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/baseRate")
public class TBdRateController extends BaseController {

    private final ITBdRateService iTBdRateService;

    /**
     * 查询基础汇率列表
     */
    @ApiOperation("查询基础汇率列表")
    @PreAuthorize("@ss.hasPermi('finance:rate:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdRateVo> list(@Validated  @RequestBody TBdRateQueryBo bo) {
        return iTBdRateService.queryPageList(bo);
    }

	/**
	 * 查询基础汇率列表
	 */
	@ApiOperation("查询基础汇率列表")
	@PreAuthorize("@ss.hasPermi('finance:rate:list')")
	@PostMapping("/getRateList")
	public AjaxResult getRateList(@Validated  @RequestBody TBdRateQueryBo bo) {
		if(null==bo.getFRateTypeId()){
			return AjaxResult.error("汇率类型不能为空");
		}
		if(null==bo.getFCytoid()){
			return AjaxResult.error("目标币ID不能为空");
		}
		if(null==bo.getFUseOrgid()){
			return AjaxResult.error("组织ID不能为空");
		}
		return AjaxResult.success(iTBdRateService.getRateList(bo));
	}

	@ApiOperation("查询汇率信息")
	@PreAuthorize("@ss.hasPermi('finance:rate:list')")
	@PostMapping("/getRate")
    public AjaxResult getRate(@RequestBody TBdRateQueryBo bo){
    	if(null==bo.getFRateTypeId()){
    		return AjaxResult.error("汇率类型不能为空");
		}
		if(null==bo.getFCyforid()){
			return AjaxResult.error("原币ID不能为空");
		}
		if(null==bo.getFCytoid()){
			return AjaxResult.error("目标币ID不能为空");
		}
       return AjaxResult.success(iTBdRateService.getRate(bo.getFDate(),bo.getFRateTypeId(),bo.getFCyforid(),bo.getFCytoid()));
	}

    /**
     * 导出基础汇率列表
     */
    @ApiOperation("导出基础汇率列表")
    @PreAuthorize("@ss.hasPermi('finance:rate:export')")
    @Log(title = "基础汇率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdRateVo> export(@Validated @RequestBody TBdRateQueryBo bo) {
        List<TBdRateVo> list = iTBdRateService.queryList(bo);
        ExcelUtil<TBdRateVo> util = new ExcelUtil<TBdRateVo>(TBdRateVo.class);
        return util.exportExcel(list, "基础汇率");
    }

    /**
     * 获取基础汇率详细信息
     */
    @ApiOperation("获取基础汇率详细信息")
    @PreAuthorize("@ss.hasPermi('finance:rate:query')")
    @PostMapping("/{fRateId}")
    public AjaxResult<TBdRateVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fRateId") Integer fRateId) {
        return AjaxResult.success(iTBdRateService.queryById(fRateId));
    }

    /**
     * 新增基础汇率
     */
    @ApiOperation("新增基础汇率")
    @PreAuthorize("@ss.hasPermi('finance:rate:add')")
    @Log(title = "基础汇率", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdRateAddBo bo) {
        return toAjax(iTBdRateService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改基础汇率
     */
    @ApiOperation("修改基础汇率")
    @PreAuthorize("@ss.hasPermi('finance:rate:edit')")
    @Log(title = "基础汇率", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TBdRateEditBo bo) {
        return toAjax(iTBdRateService.updateByEditBo(bo) ? 1 : 0);
    }

	/**
	 * 审核基础汇率
	 */
	@ApiOperation("审核基础汇率")
	@PreAuthorize("@ss.hasPermi('finance:rate:edit')")
	@Log(title = "基础汇率", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotEmpty(message = "主键不能为空")
									  @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTBdRateService.audit(s) ? 1 : 0);
	}

    /**
     * 删除基础汇率
     */
    @ApiOperation("删除基础汇率")
    @PreAuthorize("@ss.hasPermi('finance:rate:remove')")
    @Log(title = "基础汇率" , businessType = BusinessType.DELETE)
    @DeleteMapping("remove/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
									   @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTBdRateService.deleteWithValidByIds(s, true) ? 1 : 0);
    }
}
