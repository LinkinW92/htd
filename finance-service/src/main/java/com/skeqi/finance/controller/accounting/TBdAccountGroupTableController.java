package com.skeqi.finance.controller.accounting;

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
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountGroupTableVo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupTableQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupTableAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupTableEditBo;
import com.skeqi.finance.service.account.ITBdAccountGroupTableService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会计要素Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "会计要素控制器", tags = {"会计要素管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/accountGroupTab")
public class TBdAccountGroupTableController extends BaseController {

    private final ITBdAccountGroupTableService iTBdAccountGroupTableService;

    /**
     * 查询会计要素列表
     */
    @ApiOperation("查询会计要素列表")
    @PreAuthorize("@ss.hasPermi('finance:table:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdAccountGroupTableVo> list(@Validated TBdAccountGroupTableQueryBo bo) {
        return iTBdAccountGroupTableService.queryPageList(bo);
    }

    /**
     * 导出会计要素列表
     */
    @ApiOperation("导出会计要素列表")
    @PreAuthorize("@ss.hasPermi('finance:table:export')")
    @Log(title = "会计要素", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdAccountGroupTableVo> export(@Validated TBdAccountGroupTableQueryBo bo) {
        List<TBdAccountGroupTableVo> list = iTBdAccountGroupTableService.queryList(bo);
        ExcelUtil<TBdAccountGroupTableVo> util = new ExcelUtil<TBdAccountGroupTableVo>(TBdAccountGroupTableVo.class);
        return util.exportExcel(list, "会计要素");
    }

    /**
     * 获取会计要素详细信息
     */
    @ApiOperation("获取会计要素详细信息")
    @PreAuthorize("@ss.hasPermi('finance:table:query')")
    @PostMapping("/{fAcctgroupTblid}")
    public AjaxResult<TBdAccountGroupTableVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fAcctgroupTblid") Integer fAcctgroupTblid) {
        return AjaxResult.success(iTBdAccountGroupTableService.queryById(fAcctgroupTblid));
    }

    /**
     * 新增会计要素
     */
    @ApiOperation("新增会计要素")
    @PreAuthorize("@ss.hasPermi('finance:table:add')")
    @Log(title = "会计要素", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdAccountGroupTableAddBo bo) {
        return toAjax(iTBdAccountGroupTableService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改会计要素
     */
    @ApiOperation("修改会计要素")
    @PreAuthorize("@ss.hasPermi('finance:table:edit')")
    @Log(title = "会计要素", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TBdAccountGroupTableEditBo bo) {
        return toAjax(iTBdAccountGroupTableService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除会计要素
     */
    @ApiOperation("删除会计要素")
    @PreAuthorize("@ss.hasPermi('finance:table:remove')")
    @Log(title = "会计要素" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fAcctgroupTblids}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fAcctgroupTblids) {
        return toAjax(iTBdAccountGroupTableService.deleteWithValidByIds(Arrays.asList(fAcctgroupTblids), true) ? 1 : 0);
    }
}
