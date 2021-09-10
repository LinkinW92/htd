package com.skeqi.finance.controller.endhandle.transfer;

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
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferVo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferQueryBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferAddBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEditBo;
import com.skeqi.finance.service.endhandle.transfer.ITGlAutoTransferService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 自动转账主Controller
 *
 * @author toms
 * @date 2021-07-26
 */
@Api(value = "自动转账主控制器", tags = {"自动转账主管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/autoTransfer")
public class TGlAutoTransferController extends BaseController {

    private final ITGlAutoTransferService iTGlAutoTransferService;

    /**
     * 查询自动转账主列表
     */
    @ApiOperation("查询自动转账主列表")
    @PreAuthorize("@ss.hasPermi('finance:autoTransfer:list')")
    @PostMapping("/list")
    public TableDataInfo<TGlAutoTransferVo> list(@Validated @RequestBody TGlAutoTransferQueryBo bo) {
        return iTGlAutoTransferService.queryPageList(bo);
    }

    /**
     * 导出自动转账主列表
     */
    @ApiOperation("导出自动转账主列表")
    @PreAuthorize("@ss.hasPermi('finance:autoTransfer:export')")
    @Log(title = "自动转账主", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TGlAutoTransferVo> export(@Validated @RequestBody TGlAutoTransferQueryBo bo) {
        List<TGlAutoTransferVo> list = iTGlAutoTransferService.queryList(bo);
        ExcelUtil<TGlAutoTransferVo> util = new ExcelUtil<TGlAutoTransferVo>(TGlAutoTransferVo.class);
        return util.exportExcel(list, "自动转账主");
    }

    /**
     * 获取自动转账主详细信息
     */
    @ApiOperation("获取自动转账主详细信息")
    @PreAuthorize("@ss.hasPermi('finance:autoTransfer:query')")
    @GetMapping("/getInfo/{fTransferId}")
    public AjaxResult<TGlAutoTransferVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fTransferId") Integer fTransferId) {
        return AjaxResult.success(iTGlAutoTransferService.queryById(fTransferId));
    }

    /**
     * 新增自动转账主
     */
    @ApiOperation("新增自动转账主")
    @PreAuthorize("@ss.hasPermi('finance:autoTransfer:add')")
    @Log(title = "自动转账主", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TGlAutoTransferAddBo bo) {
        return toAjax(iTGlAutoTransferService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改自动转账主
     */
    @ApiOperation("修改自动转账主")
    @PreAuthorize("@ss.hasPermi('finance:autoTransfer:edit')")
    @Log(title = "自动转账主", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TGlAutoTransferEditBo bo) {
        return toAjax(iTGlAutoTransferService.updateByEditBo(bo) ? 1 : 0);
    }

	/**
	 * 执行自动转账主
	 */
	@ApiOperation("执行自动转账主")
	@PreAuthorize("@ss.hasPermi('finance:autoTransfer:edit')")
	@Log(title = "执行自动转账主", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/execute/{fId}")
	public AjaxResult<Void> execute(@NotNull(message = "主键不能为空")
										@PathVariable("fId") Integer fId) {
		return iTGlAutoTransferService.execute(fId);
	}

    /**
     * 删除自动转账主
     */
    @ApiOperation("删除自动转账主")
    @PreAuthorize("@ss.hasPermi('finance:autoTransfer:remove')")
    @Log(title = "自动转账主" , businessType = BusinessType.DELETE)
    @PostMapping("/remove/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
									   @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTGlAutoTransferService.deleteWithValidByIds(s, true) ? 1 : 0);
    }



}
