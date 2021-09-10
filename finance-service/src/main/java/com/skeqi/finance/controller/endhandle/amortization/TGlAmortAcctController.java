package com.skeqi.finance.controller.endhandle.amortization;

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
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortAcctVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctEditBo;
import com.skeqi.finance.service.endhandle.ITGlAmortAcctService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证摊销-待摊销科目Controller
 *
 * @author toms
 * @date 2021-07-27
 */
@Api(value = "凭证摊销-待摊销科目控制器", tags = {"凭证摊销-待摊销科目管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/amortAcct")
public class TGlAmortAcctController extends BaseController {

    private final ITGlAmortAcctService iTGlAmortAcctService;

    /**
     * 查询凭证摊销-待摊销科目列表
     */
    @ApiOperation("查询凭证摊销-待摊销科目列表")
    @PreAuthorize("@ss.hasPermi('finance:amortAcct:list')")
    @GetMapping("/list")
    public TableDataInfo<TGlAmortAcctVo> list(@Validated TGlAmortAcctQueryBo bo) {
        return iTGlAmortAcctService.queryPageList(bo);
    }

    /**
     * 导出凭证摊销-待摊销科目列表
     */
    @ApiOperation("导出凭证摊销-待摊销科目列表")
    @PreAuthorize("@ss.hasPermi('finance:amortAcct:export')")
    @Log(title = "凭证摊销-待摊销科目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult<TGlAmortAcctVo> export(@Validated TGlAmortAcctQueryBo bo) {
        List<TGlAmortAcctVo> list = iTGlAmortAcctService.queryList(bo);
        ExcelUtil<TGlAmortAcctVo> util = new ExcelUtil<TGlAmortAcctVo>(TGlAmortAcctVo.class);
        return util.exportExcel(list, "凭证摊销-待摊销科目");
    }

    /**
     * 获取凭证摊销-待摊销科目详细信息
     */
    @ApiOperation("获取凭证摊销-待摊销科目详细信息")
    @PreAuthorize("@ss.hasPermi('finance:amortAcct:query')")
    @GetMapping("/getInfo/{fId}")
    public AjaxResult<TGlAmortAcctVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Long fId) {
        return AjaxResult.success(iTGlAmortAcctService.queryById(fId));
    }

    /**
     * 新增凭证摊销-待摊销科目
     */
    @ApiOperation("新增凭证摊销-待摊销科目")
    @PreAuthorize("@ss.hasPermi('finance:amortAcct:add')")
    @Log(title = "凭证摊销-待摊销科目", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TGlAmortAcctAddBo bo) {
        return toAjax(iTGlAmortAcctService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证摊销-待摊销科目
     */
    @ApiOperation("修改凭证摊销-待摊销科目")
    @PreAuthorize("@ss.hasPermi('finance:amortAcct:edit')")
    @Log(title = "凭证摊销-待摊销科目", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TGlAmortAcctEditBo bo) {
        return toAjax(iTGlAmortAcctService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证摊销-待摊销科目
     */
    @ApiOperation("删除凭证摊销-待摊销科目")
    @PreAuthorize("@ss.hasPermi('finance:amortAcct:remove')")
    @Log(title = "凭证摊销-待摊销科目" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] fIds) {
        return toAjax(iTGlAmortAcctService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
