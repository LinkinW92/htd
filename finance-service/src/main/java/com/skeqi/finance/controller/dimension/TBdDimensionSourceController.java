package com.skeqi.finance.controller.dimension;

import com.skeqi.common.annotation.Log;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.finance.pojo.bo.TBdDimensionSource.*;
import com.skeqi.finance.pojo.vo.dimension.DimensionSourceData;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.finance.pojo.vo.dimension.TBdDimensionSourceVo;
import com.skeqi.finance.service.dimension.ITBdDimensionSourceService;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 维度来源Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "维度来源控制器", tags = {"维度来源管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/source")
public class TBdDimensionSourceController extends BaseController {

    private final ITBdDimensionSourceService iTBdDimensionSourceService;

//	@Resource(name = "TBdDimensionSourceServiceImpl")
//    private BaseTableService baseTableService;

    /**
     * 查询维度来源列表
     */
    @ApiOperation("查询维度来源列表")
    @PreAuthorize("@ss.hasPermi('finance:source:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdDimensionSourceVo> list(@Validated @RequestBody TBdDimensionSourceQueryBo bo) {
        return iTBdDimensionSourceService.queryPageList(bo);
    }

	/**
	 * 查询维度来源列表
	 */
	@ApiOperation("查询维度来源信息")
	@PreAuthorize("@ss.hasPermi('finance:source:data')")
	@PostMapping("/data")
	public AjaxResult<DimensionSourceData> data(int id) {
		return AjaxResult.success(iTBdDimensionSourceService.queryData(id));
	}
//
//
//    /**
//     * 导出维度来源列表
//     */
//    @ApiOperation("导出维度来源列表")
//    @PreAuthorize("@ss.hasPermi('finance:source:export')")
//    @Log(title = "维度来源", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public AjaxResult<TBdDimensionSourceVo> export(@Validated TBdDimensionSourceQueryBo bo) {
//        List<TBdDimensionSourceVo> list = iTBdDimensionSourceService.queryList(bo);
//        ExcelUtil<TBdDimensionSourceVo> util = new ExcelUtil<TBdDimensionSourceVo>(TBdDimensionSourceVo.class);
//        return util.exportExcel(list, "维度来源");
//    }
//
//    /**
//     * 获取维度来源详细信息
//     */
//    @ApiOperation("获取维度来源详细信息")
//    @PreAuthorize("@ss.hasPermi('finance:source:query')")
//    @PostMapping("/{fId}")
//    public AjaxResult<TBdDimensionSourceVo> getInfo(@NotNull(message = "主键不能为空")
//                                                  @PathVariable("fId") Integer fId) {
//        return AjaxResult.success(iTBdDimensionSourceService.queryById(fId));
//    }

//    /**
//     * 新增维度来源
//     */
//    @ApiOperation("新增维度来源")
//    @PreAuthorize("@ss.hasPermi('finance:source:add')")
//    @Log(title = "维度来源", businessType = BusinessType.INSERT)
//    @RepeatSubmit
//    @PostMapping()
//    public AjaxResult<Void> add(@Validated @RequestBody TBdDimensionSourceAddBo bo) {
//        return toAjax(iTBdDimensionSourceService.insertByAddBo(bo) ? 1 : 0);
//    }

//    /**
//     * 修改维度来源
//     */
//    @ApiOperation("修改维度来源")
//    @PreAuthorize("@ss.hasPermi('finance:source:edit')")
//    @Log(title = "维度来源", businessType = BusinessType.UPDATE)
//    @RepeatSubmit
//    @PutMapping()
//    public AjaxResult<Void> edit(@Validated @RequestBody TBdDimensionSourceEditBo bo) {
//        return toAjax(iTBdDimensionSourceService.updateByEditBo(bo) ? 1 : 0);
//    }
//
//    /**
//     * 删除维度来源
//     */
//    @ApiOperation("删除维度来源")
//    @PreAuthorize("@ss.hasPermi('finance:source:remove')")
//    @Log(title = "维度来源" , businessType = BusinessType.DELETE)
//    @DeleteMapping("/{fIds}")
//    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
//                                       @PathVariable Integer[] fIds) {
//        return toAjax(iTBdDimensionSourceService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
//    }

//
//	/**
//	 * 审核维度来源
//	 */
//	@ApiOperation("审核维度来源")
//	@PreAuthorize("@ss.hasPermi('finance:source:audit')")
//	@Log(title = "维度来源", businessType = BusinessType.UPDATE)
//	@RepeatSubmit
//	@PostMapping("/audit")
//	public AjaxResult<Void> audit(@Validated @RequestBody AuditBo bo) {
//		return toAjax(baseTableService.audit(bo) ? 1 : 0);
//	}
//
//	/**
//	 * 禁用维度来源
//	 */
//	@ApiOperation("禁用维度来源")
//	@PreAuthorize("@ss.hasPermi('finance:source:disable')")
//	@Log(title = "维度来源", businessType = BusinessType.UPDATE)
//	@RepeatSubmit
//	@PostMapping("/disable")
//	public AjaxResult<Void> disable(@Validated @RequestBody DisableBo bo) {
//		return toAjax(baseTableService.disable(bo) ? 1 : 0);
//	}
}
