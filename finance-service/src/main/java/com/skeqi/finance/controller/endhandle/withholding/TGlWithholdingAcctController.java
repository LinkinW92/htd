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
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingAcctVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctEditBo;
import com.skeqi.finance.service.endhandle.ITGlWithholdingAcctService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证预提-预提科目Controller
 *
 * @author toms
 * @date 2021-07-27
 */
@Api(value = "凭证预提-预提科目控制器", tags = {"凭证预提-预提科目管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/withholdingAcct")
public class TGlWithholdingAcctController extends BaseController {

    private final ITGlWithholdingAcctService iTGlWithholdingAcctService;

    /**
     * 查询凭证预提-预提科目列表
     */
    @ApiOperation("查询凭证预提-预提科目列表")
    @PreAuthorize("@ss.hasPermi('finance:withholdingAcct:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlWithholdingAcctVo> list(@Validated TGlWithholdingAcctQueryBo bo) {
        return iTGlWithholdingAcctService.queryPageList(bo);
    }

    /**
     * 导出凭证预提-预提科目列表
     */
    @ApiOperation("导出凭证预提-预提科目列表")
    @PreAuthorize("@ss.hasPermi('finance:withholdingAcct:export')")
    @Log(title = "凭证预提-预提科目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlWithholdingAcctVo> export(@Validated TGlWithholdingAcctQueryBo bo) {
        List<TGlWithholdingAcctVo> list = iTGlWithholdingAcctService.queryList(bo);
        ExcelUtil<TGlWithholdingAcctVo> util = new ExcelUtil<TGlWithholdingAcctVo>(TGlWithholdingAcctVo.class);
        return util.exportExcel(list, "凭证预提-预提科目");
    }

    /**
     * 获取凭证预提-预提科目详细信息
     */
    @ApiOperation("获取凭证预提-预提科目详细信息")
    @PreAuthorize("@ss.hasPermi('finance:withholdingAcct:query')")
    @GetMapping("/{fId}")
    public AjaxResult<TGlWithholdingAcctVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Long fId) {
        return AjaxResult.success(iTGlWithholdingAcctService.queryById(fId));
    }

    /**
     * 新增凭证预提-预提科目
     */
    @ApiOperation("新增凭证预提-预提科目")
    @PreAuthorize("@ss.hasPermi('finance:withholdingAcct:add')")
    @Log(title = "凭证预提-预提科目", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TGlWithholdingAcctAddBo bo) {
        return toAjax(iTGlWithholdingAcctService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证预提-预提科目
     */
    @ApiOperation("修改凭证预提-预提科目")
    @PreAuthorize("@ss.hasPermi('finance:withholdingAcct:edit')")
    @Log(title = "凭证预提-预提科目", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TGlWithholdingAcctEditBo bo) {
        return toAjax(iTGlWithholdingAcctService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证预提-预提科目
     */
    @ApiOperation("删除凭证预提-预提科目")
    @PreAuthorize("@ss.hasPermi('finance:withholdingAcct:remove')")
    @Log(title = "凭证预提-预提科目" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] fIds) {
        return toAjax(iTGlWithholdingAcctService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
