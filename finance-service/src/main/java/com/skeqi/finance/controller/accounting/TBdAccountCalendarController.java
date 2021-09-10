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
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountCalendarVo;
import com.skeqi.finance.pojo.bo.basicinformation.calendar.TBdAccountCalendarQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.calendar.TBdAccountCalendarAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.calendar.TBdAccountCalendarEditBo;
import com.skeqi.finance.service.account.ITBdAccountCalendarService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会计日历Controller
 *
 * @author toms
 * @date 2021-07-14
 */
@Api(value = "会计日历控制器", tags = {"会计日历管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/accountCalendar")
public class TBdAccountCalendarController extends BaseController {

    private final ITBdAccountCalendarService iTBdAccountCalendarService;

    /**
     * 查询会计日历列表
     */
    @ApiOperation("查询会计日历列表")
    @PreAuthorize("@ss.hasPermi('finance:accountCalendar:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdAccountCalendarVo> list(@Validated @RequestBody TBdAccountCalendarQueryBo bo) {
        return iTBdAccountCalendarService.queryPageList(bo);
    }

    /**
     * 导出会计日历列表
     */
    @ApiOperation("导出会计日历列表")
    @PreAuthorize("@ss.hasPermi('finance:accountCalendar:export')")
    @Log(title = "会计日历", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdAccountCalendarVo> export(@Validated @RequestBody TBdAccountCalendarQueryBo bo) {
        List<TBdAccountCalendarVo> list = iTBdAccountCalendarService.queryList(bo);
        ExcelUtil<TBdAccountCalendarVo> util = new ExcelUtil<TBdAccountCalendarVo>(TBdAccountCalendarVo.class);
        return util.exportExcel(list, "会计日历");
    }

    /**
     * 获取会计日历详细信息
     */
    @ApiOperation("获取会计日历详细信息")
    @PreAuthorize("@ss.hasPermi('finance:accountCalendar:query')")
    @PostMapping("/getInfo/{fId}")
    public AjaxResult<TBdAccountCalendarVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTBdAccountCalendarService.queryById(fId));
    }

    /**
     * 新增会计日历
     */
    @ApiOperation("新增会计日历")
    @PreAuthorize("@ss.hasPermi('finance:accountCalendar:add')")
    @Log(title = "会计日历", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdAccountCalendarAddBo bo) {
        return iTBdAccountCalendarService.insertByAddBo(bo);
    }


    /**
     * 审核会计日历
     */
    @ApiOperation("审核会计日历")
    @PreAuthorize("@ss.hasPermi('finance:accountCalendar:edit')")
    @Log(title = "会计日历", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/audit/{fIds}")
    public AjaxResult<Void> audit(@NotEmpty(message = "主键不能为空")
									  @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTBdAccountCalendarService.audit(s) ? 1 : 0);
    }


	/**
	 * 修改会计日历
	 */
	@ApiOperation("修改会计日历")
	@PreAuthorize("@ss.hasPermi('finance:accountCalendar:edit')")
	@Log(title = "会计日历", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/edit")
	public AjaxResult<Void> edit(@Validated @RequestBody TBdAccountCalendarEditBo bo) {
		return toAjax(iTBdAccountCalendarService.updateByEditBo(bo) ? 1 : 0);
	}



	/**
     * 删除会计日历
     */
    @ApiOperation("删除会计日历")
    @PreAuthorize("@ss.hasPermi('finance:accountCalendar:remove')")
    @Log(title = "会计日历" , businessType = BusinessType.DELETE)
    @PostMapping("/remove/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
									   @PathVariable("fIds") String fIds) {
    	List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTBdAccountCalendarService.deleteWithValidByIds(s, true) ? 1 : 0);
    }
}
