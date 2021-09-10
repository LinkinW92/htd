package com.skeqi.finance.controller.basesource;

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
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdBusinessAreaVo;
import com.skeqi.finance.pojo.bo.basicinformation.area.TBdBusinessAreaQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.area.TBdBusinessAreaAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.area.TBdBusinessAreaEditBo;
import com.skeqi.finance.service.basicinformation.base.ITBdBusinessAreaService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 业务领域Controller
 *
 * @author toms
 * @date 2021-07-13
 */
@Api(value = "业务领域控制器", tags = {"业务领域管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/businessArea")
public class TBdBusinessAreaController extends BaseController {

    private final ITBdBusinessAreaService iTBdBusinessAreaService;

    /**
     * 查询业务领域列表
     */
    @ApiOperation("查询业务领域列表")
    @PreAuthorize("@ss.hasPermi('finance:businessArea:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdBusinessAreaVo> list(@Validated @RequestBody TBdBusinessAreaQueryBo bo) {
        return iTBdBusinessAreaService.queryPageList(bo);
    }

    /**
     * 导出业务领域列表
     */
    @ApiOperation("导出业务领域列表")
    @PreAuthorize("@ss.hasPermi('finance:businessArea:export')")
    @Log(title = "业务领域", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdBusinessAreaVo> export(@Validated @RequestBody TBdBusinessAreaQueryBo bo) {
        List<TBdBusinessAreaVo> list = iTBdBusinessAreaService.queryList(bo);
        ExcelUtil<TBdBusinessAreaVo> util = new ExcelUtil<TBdBusinessAreaVo>(TBdBusinessAreaVo.class);
        return util.exportExcel(list, "业务领域");
    }

    /**
     * 获取业务领域详细信息
     */
    @ApiOperation("获取业务领域详细信息")
    @PreAuthorize("@ss.hasPermi('finance:businessArea:query')")
    @PostMapping("/{id}")
    public AjaxResult<TBdBusinessAreaVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Integer id) {
        return AjaxResult.success(iTBdBusinessAreaService.queryById(id));
    }

    /**
     * 新增业务领域
     */
    @ApiOperation("新增业务领域")
    @PreAuthorize("@ss.hasPermi('finance:businessArea:add')")
    @Log(title = "业务领域", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdBusinessAreaAddBo bo) {
        return toAjax(iTBdBusinessAreaService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改业务领域
     */
    @ApiOperation("修改业务领域")
    @PreAuthorize("@ss.hasPermi('finance:businessArea:edit')")
    @Log(title = "业务领域", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TBdBusinessAreaEditBo bo) {
        return toAjax(iTBdBusinessAreaService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除业务领域
     */
    @ApiOperation("删除业务领域")
    @PreAuthorize("@ss.hasPermi('finance:businessArea:remove')")
    @Log(title = "业务领域" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] ids) {
        return toAjax(iTBdBusinessAreaService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
