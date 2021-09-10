package com.skeqi.finance.controller.accounting;

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
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountGroupVo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupEditBo;
import com.skeqi.finance.service.account.ITBdAccountGroupService;
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
@RequestMapping("/finance/accountGroup")
public class TBdAccountGroupController extends BaseController {

    private final ITBdAccountGroupService iTBdAccountGroupService;

    /**
     * 查询会计要素列表
     */
    @ApiOperation("查询会计要素列表")
    @PreAuthorize("@ss.hasPermi('finance:group:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdAccountGroupVo> list(@Validated @RequestBody TBdAccountGroupQueryBo bo) {
        return iTBdAccountGroupService.queryPageList(bo);
    }

    /**
     * 导出会计要素列表
     */
    @ApiOperation("导出会计要素列表")
    @PreAuthorize("@ss.hasPermi('finance:group:export')")
    @Log(title = "会计要素", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdAccountGroupVo> export(@Validated TBdAccountGroupQueryBo bo) {
        List<TBdAccountGroupVo> list = iTBdAccountGroupService.queryList(bo);
        ExcelUtil<TBdAccountGroupVo> util = new ExcelUtil<TBdAccountGroupVo>(TBdAccountGroupVo.class);
        return util.exportExcel(list, "会计要素");
    }

    /**
     * 获取会计要素详细信息
     */
    @ApiOperation("获取会计要素详细信息")
    @PreAuthorize("@ss.hasPermi('finance:group:query')")
    @PostMapping("/getInfo/{id}")
    public AjaxResult<TBdAccountGroupVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Integer fAcctgroupId) {
        return AjaxResult.success(iTBdAccountGroupService.queryById(fAcctgroupId));
    }

    /**
     * 新增会计要素
     */
    @ApiOperation("新增会计要素")
    @PreAuthorize("@ss.hasPermi('finance:group:add')")
    @Log(title = "会计要素", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdAccountGroupAddBo bo) {
        return toAjax(iTBdAccountGroupService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改会计要素
     */
    @ApiOperation("修改会计要素")
    @PreAuthorize("@ss.hasPermi('finance:group:edit')")
    @Log(title = "会计要素", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TBdAccountGroupEditBo bo) {
        return toAjax(iTBdAccountGroupService.updateByEditBo(bo) ? 1 : 0);
    }

	/**
	 * 审核会计要素
	 */
	@ApiOperation("审核会计要素")
	@PreAuthorize("@ss.hasPermi('finance:group:edit')")
	@Log(title = "会计要素", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotEmpty(message = "主键不能为空")
									 @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTBdAccountGroupService.audit(s) ? 1 : 0);
	}

    /**
     * 删除会计要素
     */
    @ApiOperation("删除会计要素")
    @PreAuthorize("@ss.hasPermi('finance:group:remove')")
    @Log(title = "会计要素" , businessType = BusinessType.DELETE)
    @PostMapping("/remove/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTBdAccountGroupService.deleteWithValidByIds(s, true) ? 1 : 0);
    }
}
