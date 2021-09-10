package com.skeqi.finance.controller.endhandle.withholding;

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
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingInacctVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctEditBo;
import com.skeqi.finance.service.endhandle.ITGlWithholdingInacctService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证预提-转入科目Controller
 *
 * @author toms
 * @date 2021-07-27
 */
@Api(value = "凭证预提-转入科目控制器", tags = {"凭证预提-转入科目管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/withholdingInacct")
public class TGlWithholdingInacctController extends BaseController {

    private final ITGlWithholdingInacctService iTGlWithholdingInacctService;

    /**
     * 查询凭证预提-转入科目列表
     */
    @ApiOperation("查询凭证预提-转入科目列表")
    @PreAuthorize("@ss.hasPermi('finance:withholdingInacct:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlWithholdingInacctVo> list(@Validated TGlWithholdingInacctQueryBo bo) {
        return iTGlWithholdingInacctService.queryPageList(bo);
    }

    /**
     * 导出凭证预提-转入科目列表
     */
    @ApiOperation("导出凭证预提-转入科目列表")
    @PreAuthorize("@ss.hasPermi('finance:withholdingInacct:export')")
    @Log(title = "凭证预提-转入科目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlWithholdingInacctVo> export(@Validated TGlWithholdingInacctQueryBo bo) {
        List<TGlWithholdingInacctVo> list = iTGlWithholdingInacctService.queryList(bo);
        ExcelUtil<TGlWithholdingInacctVo> util = new ExcelUtil<TGlWithholdingInacctVo>(TGlWithholdingInacctVo.class);
        return util.exportExcel(list, "凭证预提-转入科目");
    }

    /**
     * 获取凭证预提-转入科目详细信息
     */
    @ApiOperation("获取凭证预提-转入科目详细信息")
    @PreAuthorize("@ss.hasPermi('finance:withholdingInacct:query')")
    @GetMapping("/{fEnterAccountId}")
    public AjaxResult<TGlWithholdingInacctVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fEnterAccountId") Long fEnterAccountId) {
        return AjaxResult.success(iTGlWithholdingInacctService.queryById(fEnterAccountId));
    }

    /**
     * 新增凭证预提-转入科目
     */
    @ApiOperation("新增凭证预提-转入科目")
    @PreAuthorize("@ss.hasPermi('finance:withholdingInacct:add')")
    @Log(title = "凭证预提-转入科目", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlWithholdingInacctAddBo bo) {
        return toAjax(iTGlWithholdingInacctService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证预提-转入科目
     */
    @ApiOperation("修改凭证预提-转入科目")
    @PreAuthorize("@ss.hasPermi('finance:withholdingInacct:edit')")
    @Log(title = "凭证预提-转入科目", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlWithholdingInacctEditBo bo) {
        return toAjax(iTGlWithholdingInacctService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证预提-转入科目
     */
    @ApiOperation("删除凭证预提-转入科目")
    @PreAuthorize("@ss.hasPermi('finance:withholdingInacct:remove')")
    @Log(title = "凭证预提-转入科目" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fEnterAccountIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] fEnterAccountIds) {
        return toAjax(iTGlWithholdingInacctService.deleteWithValidByIds(Arrays.asList(fEnterAccountIds), true) ? 1 : 0);
    }
}
