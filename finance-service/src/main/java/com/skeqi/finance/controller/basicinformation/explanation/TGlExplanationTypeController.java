package com.skeqi.finance.controller.basicinformation.explanation;

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
import com.skeqi.finance.pojo.vo.TGlExplanationTypeVo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationTypeQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationTypeAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationTypeEditBo;
import com.skeqi.finance.service.ITGlExplanationTypeService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 摘要类别Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "摘要类别控制器", tags = {"摘要类别管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/explanationType")
public class TGlExplanationTypeController extends BaseController {

    private final ITGlExplanationTypeService iTGlExplanationTypeService;

    /**
     * 查询摘要类别列表
     */
    @ApiOperation("查询摘要类别列表")
    @PreAuthorize("@ss.hasPermi('finance:type:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlExplanationTypeVo> list(@Validated  @RequestBody TGlExplanationTypeQueryBo bo) {
        return iTGlExplanationTypeService.queryPageList(bo);
    }

    /**
     * 导出摘要类别列表
     */
    @ApiOperation("导出摘要类别列表")
    @PreAuthorize("@ss.hasPermi('finance:type:export')")
    @Log(title = "摘要类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlExplanationTypeVo> export(@Validated  @RequestBody TGlExplanationTypeQueryBo bo) {
        List<TGlExplanationTypeVo> list = iTGlExplanationTypeService.queryList(bo);
        ExcelUtil<TGlExplanationTypeVo> util = new ExcelUtil<TGlExplanationTypeVo>(TGlExplanationTypeVo.class);
        return util.exportExcel(list, "摘要类别");
    }

    /**
     * 获取摘要类别详细信息
     */
    @ApiOperation("获取摘要类别详细信息")
    @PreAuthorize("@ss.hasPermi('finance:type:query')")
    @PostMapping("/getInfo/{fId}")
    public AjaxResult<TGlExplanationTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTGlExplanationTypeService.queryById(fId));
    }

    /**
     * 新增摘要类别
     */
    @ApiOperation("新增摘要类别")
    @PreAuthorize("@ss.hasPermi('finance:type:add')")
    @Log(title = "摘要类别", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TGlExplanationTypeAddBo bo) {
        return toAjax(iTGlExplanationTypeService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改摘要类别
     */
    @ApiOperation("修改摘要类别")
    @PreAuthorize("@ss.hasPermi('finance:type:edit')")
    @Log(title = "摘要类别", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TGlExplanationTypeEditBo bo) {
        return toAjax(iTGlExplanationTypeService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除摘要类别
     */
    @ApiOperation("删除摘要类别")
    @PreAuthorize("@ss.hasPermi('finance:type:remove')")
    @Log(title = "摘要类别" , businessType = BusinessType.DELETE)
    @PostMapping("/remove/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
									   @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTGlExplanationTypeService.deleteWithValidByIds(s, true) ? 1 : 0);
    }
}
