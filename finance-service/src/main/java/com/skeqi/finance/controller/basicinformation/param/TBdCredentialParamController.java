package com.skeqi.finance.controller.basicinformation.param;

import java.util.List;
import java.util.Arrays;

import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctAddBo;
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
import com.skeqi.finance.pojo.vo.TBdCredentialParamVo;
import com.skeqi.finance.pojo.bo.TBdCredentialParamQueryBo;
import com.skeqi.finance.pojo.bo.TBdCredentialParamAddBo;
import com.skeqi.finance.pojo.bo.TBdCredentialParamEditBo;
import com.skeqi.finance.service.ITBdCredentialParamService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "凭证控制器", tags = {"凭证管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/credential")
public class TBdCredentialParamController extends BaseController {

    private final ITBdCredentialParamService iTBdCredentialParamService;

    /**
     * 查询凭证列表
     */
    @ApiOperation("查询凭证列表")
    @PreAuthorize("@ss.hasPermi('finance:param:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdCredentialParamVo> list(@Validated TBdCredentialParamQueryBo bo) {
        return iTBdCredentialParamService.queryPageList(bo);
    }

    /**
     * 导出凭证列表
     */
    @ApiOperation("导出凭证列表")
    @PreAuthorize("@ss.hasPermi('finance:param:export')")
    @Log(title = "凭证", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdCredentialParamVo> export(@Validated TBdCredentialParamQueryBo bo) {
        List<TBdCredentialParamVo> list = iTBdCredentialParamService.queryList(bo);
        ExcelUtil<TBdCredentialParamVo> util = new ExcelUtil<TBdCredentialParamVo>(TBdCredentialParamVo.class);
        return util.exportExcel(list, "凭证");
    }

    /**
     * 获取凭证详细信息
     */
    @ApiOperation("获取凭证详细信息")
    @PreAuthorize("@ss.hasPermi('finance:param:query')")
    @PostMapping("/{fId}")
    public AjaxResult<TBdCredentialParamVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTBdCredentialParamService.queryById(fId));
    }

    /**
     * 新增凭证
     */
    @ApiOperation("新增凭证")
    @PreAuthorize("@ss.hasPermi('finance:param:add')")
    @Log(title = "凭证", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdCredentialParamAddBo bo, @Validated @RequestBody List<TBdVchgroupAcctAddBo> acctAddBo) {
		logger.info("凭证头：{}",bo.toString());
		acctAddBo.forEach(acctAddBo1 -> logger.info("凭证明细：{}",acctAddBo1.toString()));
        return toAjax(iTBdCredentialParamService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证
     */
    @ApiOperation("修改凭证")
    @PreAuthorize("@ss.hasPermi('finance:param:edit')")
    @Log(title = "凭证", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TBdCredentialParamEditBo bo) {
        return toAjax(iTBdCredentialParamService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证
     */
    @ApiOperation("删除凭证")
    @PreAuthorize("@ss.hasPermi('finance:param:remove')")
    @Log(title = "凭证" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fIds) {
        return toAjax(iTBdCredentialParamService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
