package com.skeqi.finance.controller.org;

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
import com.skeqi.finance.pojo.vo.SysOrganizationVo;
import com.skeqi.finance.pojo.bo.basicinformation.org.SysOrganizationQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.org.SysOrganizationAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.org.SysOrganizationEditBo;
import com.skeqi.finance.service.ISysOrganizationService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 组织管理Controller
 *
 * @author toms
 * @date 2021-07-16
 */
@Api(value = "组织管理控制器", tags = {"组织管理管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/sysOrganization")
public class SysOrganizationController extends BaseController {

    private final ISysOrganizationService iSysOrganizationService;

    /**
     * 查询组织管理列表
     */
    @ApiOperation("查询组织管理列表")
    @PreAuthorize("@ss.hasPermi('finance:sysOrganization:list')")
    @PostMapping("/list")
    public TableDataInfo<SysOrganizationVo> list(@Validated SysOrganizationQueryBo bo) {
        return iSysOrganizationService.queryPageList(bo);
    }

    /**
     * 导出组织管理列表
     */
    @ApiOperation("导出组织管理列表")
    @PreAuthorize("@ss.hasPermi('finance:sysOrganization:export')")
    @Log(title = "组织管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<SysOrganizationVo> export(@Validated SysOrganizationQueryBo bo) {
        List<SysOrganizationVo> list = iSysOrganizationService.queryList(bo);
        ExcelUtil<SysOrganizationVo> util = new ExcelUtil<SysOrganizationVo>(SysOrganizationVo.class);
        return util.exportExcel(list, "组织管理");
    }

    /**
     * 获取组织管理详细信息
     */
    @ApiOperation("获取组织管理详细信息")
    @PreAuthorize("@ss.hasPermi('finance:sysOrganization:query')")
    @PostMapping("/{id}")
    public AjaxResult<SysOrganizationVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Integer id) {
        return AjaxResult.success(iSysOrganizationService.queryById(id));
    }

    /**
     * 新增组织管理
     */
    @ApiOperation("新增组织管理")
    @PreAuthorize("@ss.hasPermi('finance:sysOrganization:add')")
    @Log(title = "组织管理", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody SysOrganizationAddBo bo) {
        return toAjax(iSysOrganizationService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改组织管理
     */
    @ApiOperation("修改组织管理")
    @PreAuthorize("@ss.hasPermi('finance:sysOrganization:edit')")
    @Log(title = "组织管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody SysOrganizationEditBo bo) {
        return toAjax(iSysOrganizationService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除组织管理
     */
    @ApiOperation("删除组织管理")
    @PreAuthorize("@ss.hasPermi('finance:sysOrganization:remove')")
    @Log(title = "组织管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] ids) {
        return toAjax(iSysOrganizationService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
