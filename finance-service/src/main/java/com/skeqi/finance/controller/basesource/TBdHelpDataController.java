package com.skeqi.finance.controller.basesource;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.skeqi.finance.enums.DataStatusEnum;
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
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpDataVo;
import com.skeqi.finance.pojo.bo.help.TBdHelpDataQueryBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpDataAddBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpDataEditBo;
import com.skeqi.finance.service.help.ITBdHelpDataService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 辅助资料Controller
 *
 * @author toms
 * @date 2021-07-13
 */
@Api(value = "辅助资料控制器", tags = {"辅助资料管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/helpData")
public class TBdHelpDataController extends BaseController {

    private final ITBdHelpDataService iTBdHelpDataService;

    /**
     * 查询辅助资料列表
     */
    @ApiOperation("查询辅助资料列表")
    @PreAuthorize("@ss.hasPermi('finance:helpData:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdHelpDataVo> list(@Validated @RequestBody TBdHelpDataQueryBo bo) {
        return iTBdHelpDataService.queryPageList(bo);
    }

	/**
	 * 查询辅助资料列表
	 */
	@ApiOperation("根据类型查询辅助资料列表")
	@PreAuthorize("@ss.hasPermi('finance:helpData:list')")
	@PostMapping("/listByType")
	public TableDataInfo<TBdHelpDataVo> listByType(@Validated @RequestBody TBdHelpDataQueryBo bo) {
		return iTBdHelpDataService.listByType(bo);
	}

	/**
	 * 查询货币符号列表
	 */
	@ApiOperation("查询货币符号列表")
	@PreAuthorize("@ss.hasPermi('finance:helpData:list')")
	@PostMapping("/listByCurry")
	public TableDataInfo<TBdHelpDataVo> listByCurry(@Validated @RequestBody TBdHelpDataQueryBo bo) {
		bo.setFNumber("Currency Symbol");
		bo.setFDocumentStatus(DataStatusEnum.AUDIT.getCode());
		return iTBdHelpDataService.queryPageList(bo);
	}

    /**
     * 导出辅助资料列表
     */
    @ApiOperation("导出辅助资料列表")
    @PreAuthorize("@ss.hasPermi('finance:helpData:export')")
    @Log(title = "辅助资料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdHelpDataVo> export(@Validated TBdHelpDataQueryBo bo) {
        List<TBdHelpDataVo> list = iTBdHelpDataService.queryList(bo);
        ExcelUtil<TBdHelpDataVo> util = new ExcelUtil<TBdHelpDataVo>(TBdHelpDataVo.class);
        return util.exportExcel(list, "辅助资料");
    }

    /**
     * 获取辅助资料详细信息
     */
    @ApiOperation("获取辅助资料详细信息")
    @PreAuthorize("@ss.hasPermi('finance:helpData:query')")
    @PostMapping("/getInfo/{fId}")
    public AjaxResult<TBdHelpDataVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTBdHelpDataService.queryById(fId));
    }

    /**
     * 新增辅助资料
     */
    @ApiOperation("新增辅助资料")
    @PreAuthorize("@ss.hasPermi('finance:helpData:add')")
    @Log(title = "辅助资料", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdHelpDataAddBo bo) {
        return toAjax(iTBdHelpDataService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改辅助资料
     */
    @ApiOperation("修改辅助资料")
    @PreAuthorize("@ss.hasPermi('finance:helpData:edit')")
    @Log(title = "辅助资料", businessType = BusinessType.UPDATE)
    @RepeatSubmit
	@PostMapping("/edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TBdHelpDataEditBo bo) {
        return toAjax(iTBdHelpDataService.updateByEditBo(bo) ? 1 : 0);
    }

	/**
	 * 审核辅助资料
	 */
	@ApiOperation("审核辅助资料")
	@PreAuthorize("@ss.hasPermi('finance:helpData:edit')")
	@Log(title = "审核辅助资料", businessType = BusinessType.UPDATE)
	@RepeatSubmit
	@PostMapping("/audit/{fIds}")
	public AjaxResult<Void> audit(@NotEmpty(message = "主键不能为空")
									  @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTBdHelpDataService.audit(s) ? 1 : 0);
	}

    /**
     * 删除辅助资料
     */
    @ApiOperation("删除辅助资料")
    @PreAuthorize("@ss.hasPermi('finance:helpData:remove')")
    @Log(title = "辅助资料" , businessType = BusinessType.DELETE)
	@PostMapping("/remove/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
									   @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTBdHelpDataService.deleteWithValidByIds(s, true) ? 1 : 0);
    }
}
