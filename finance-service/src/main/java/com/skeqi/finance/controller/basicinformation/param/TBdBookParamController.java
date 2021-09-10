package com.skeqi.finance.controller.basicinformation.param;

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
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdBookParamVo;
import com.skeqi.finance.pojo.bo.TBdBookParamQueryBo;
import com.skeqi.finance.pojo.bo.TBdBookParamAddBo;
import com.skeqi.finance.pojo.bo.TBdBookParamEditBo;
import com.skeqi.finance.service.basicinformation.base.ITBdBookParamService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 总账管理参数-账簿参数Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "总账管理参数-账簿参数控制器", tags = {"总账管理参数-账簿参数管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/bookParam")
public class TBdBookParamController extends BaseController {

    private final ITBdBookParamService iTBdBookParamService;

    /**
     * 查询总账管理参数-账簿参数列表
     */
    @ApiOperation("查询总账管理参数-账簿参数列表")
    @PreAuthorize("@ss.hasPermi('finance:param:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdBookParamVo> list(@Validated TBdBookParamQueryBo bo) {
        return iTBdBookParamService.queryPageList(bo);
    }

    /**
     * 导出总账管理参数-账簿参数列表
     */
    @ApiOperation("导出总账管理参数-账簿参数列表")
    @PreAuthorize("@ss.hasPermi('finance:param:export')")
    @Log(title = "总账管理参数-账簿参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdBookParamVo> export(@Validated TBdBookParamQueryBo bo) {
        List<TBdBookParamVo> list = iTBdBookParamService.queryList(bo);
        ExcelUtil<TBdBookParamVo> util = new ExcelUtil<TBdBookParamVo>(TBdBookParamVo.class);
        return util.exportExcel(list, "总账管理参数-账簿参数");
    }

    /**
     * 获取总账管理参数-账簿参数详细信息
     */
    @ApiOperation("获取总账管理参数-账簿参数详细信息")
    @PreAuthorize("@ss.hasPermi('finance:param:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TBdBookParamVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTBdBookParamService.queryById(fId));
    }

    /**
     * 新增总账管理参数-账簿参数
     */
    @ApiOperation("新增总账管理参数-账簿参数")
    @PreAuthorize("@ss.hasPermi('finance:param:add')")
    @Log(title = "总账管理参数-账簿参数", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TBdBookParamAddBo bo) {
        return toAjax(iTBdBookParamService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改总账管理参数-账簿参数
     */
    @ApiOperation("修改总账管理参数-账簿参数")
    @PreAuthorize("@ss.hasPermi('finance:param:edit')")
    @Log(title = "总账管理参数-账簿参数", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TBdBookParamEditBo bo) {
        return toAjax(iTBdBookParamService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除总账管理参数-账簿参数
     */
    @ApiOperation("删除总账管理参数-账簿参数")
    @PreAuthorize("@ss.hasPermi('finance:param:remove')")
    @Log(title = "总账管理参数-账簿参数" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fIds) {
        return toAjax(iTBdBookParamService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
