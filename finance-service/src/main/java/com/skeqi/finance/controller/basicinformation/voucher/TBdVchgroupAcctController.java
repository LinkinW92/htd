package com.skeqi.finance.controller.basicinformation.voucher;

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
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVchgroupAcctVo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctEditBo;
import com.skeqi.finance.service.basicinformation.voucher.ITBdVchgroupAcctService;
import com.skeqi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 凭证字-科目控制Controller
 *
 * @author toms
 * @date 2021-07-09
 */
@Api(value = "凭证字-科目控制控制器", tags = {"凭证字-科目控制管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/acct")
public class TBdVchgroupAcctController extends BaseController {

    private final ITBdVchgroupAcctService iTBdVchgroupAcctService;

    /**
     * 按凭证子内码查询-科目控制列表
	 * @return
	 */
    @ApiOperation("按凭证子内码查询-科目控制列表")
    @PreAuthorize("@ss.hasPermi('finance:acct:listByVchGroupNumber')")
    @PostMapping("/listByVchGroupNumber/{vchGroupId}")
    public AjaxResult<List<TBdVchgroupAcctVo>> list(@NotNull(message = "主键不能为空") @PathVariable Integer vchGroupId) {
		return  AjaxResult.success(iTBdVchgroupAcctService.selectByFVchgroupId(vchGroupId));
    }

    /**
     * 导出凭证字-科目控制列表
     */
    @ApiOperation("导出凭证字-科目控制列表")
    @PreAuthorize("@ss.hasPermi('finance:acct:export')")
    @Log(title = "凭证字-科目控制", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdVchgroupAcctVo> export(@Validated TBdVchgroupAcctQueryBo bo) {
        List<TBdVchgroupAcctVo> list = iTBdVchgroupAcctService.queryList(bo);
        ExcelUtil<TBdVchgroupAcctVo> util = new ExcelUtil<TBdVchgroupAcctVo>(TBdVchgroupAcctVo.class);
        return util.exportExcel(list, "凭证字-科目控制");
    }

    /**
     * 获取凭证字-科目控制详细信息
     */
    @ApiOperation("获取凭证字-科目控制详细信息")
    @PreAuthorize("@ss.hasPermi('finance:acct:query')")
    @PostMapping("/{fEntryId}")
    public AjaxResult<TBdVchgroupAcctVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fEntryId") Integer fEntryId) {
        return AjaxResult.success(iTBdVchgroupAcctService.queryById(fEntryId));
    }

    /**
     * 新增凭证字-科目控制
     */
    @ApiOperation("新增凭证字-科目控制")
    @PreAuthorize("@ss.hasPermi('finance:acct:add')")
    @Log(title = "凭证字-科目控制", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping()
    public AjaxResult<Void> add(@Validated @RequestBody TBdVchgroupAcctAddBo bo) {
        return toAjax(iTBdVchgroupAcctService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改凭证字-科目控制
     */
    @ApiOperation("修改凭证字-科目控制")
    @PreAuthorize("@ss.hasPermi('finance:acct:edit')")
    @Log(title = "凭证字-科目控制", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping()
    public AjaxResult<Void> edit(@Validated @RequestBody TBdVchgroupAcctEditBo bo) {
        return toAjax(iTBdVchgroupAcctService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除凭证字-科目控制
     */
    @ApiOperation("删除凭证字-科目控制")
    @PreAuthorize("@ss.hasPermi('finance:acct:remove')")
    @Log(title = "凭证字-科目控制" , businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{fEntryIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable String fEntryIds) {
		List<Integer> integerList= Arrays.asList(fEntryIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTBdVchgroupAcctService.deleteWithValidByIds(integerList, true) ? 1 : 0);
    }
}
