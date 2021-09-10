package com.skeqi.finance.controller.basicinformation.accountbook;

import com.skeqi.common.annotation.Log;
import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.core.controller.BaseController;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.common.enums.BusinessType;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookEditBo;
import com.skeqi.finance.pojo.bo.basicinformation.book.TBdAccountBookQueryBo;
import com.skeqi.finance.pojo.vo.book.TBdAccountBookVo;
import com.skeqi.finance.service.basicinformation.accountbook.ITBdAccountBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 账簿Controller
 *
 * @author toms
 * @date 2021-07-13
 */
@Api(value = "账簿控制器", tags = {"账簿管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/accountBook")
public class TBdAccountBookController extends BaseController {

    private final ITBdAccountBookService iTBdAccountBookService;

    /**
     * 查询账簿列表
     */
    @ApiOperation("查询账簿列表")
    @PreAuthorize("@ss.hasPermi('finance:accountBook:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdAccountBookVo> list(@Validated @RequestBody TBdAccountBookQueryBo bo) {
        return iTBdAccountBookService.queryPageList(bo);
    }


	/**
	 * 查询会计政策、日历、记账本位币、汇率类型
	 */
	@ApiOperation("查询会计政策、日历、记账本位币、汇率类型")
	@PreAuthorize("@ss.hasPermi('finance:accountBook:list')")
	@PostMapping("/queryAcctInfo")
	public AjaxResult queryAcctInfo(@Validated @RequestBody TBdAccountBookQueryBo bo) {
		return iTBdAccountBookService.queryAcctInfo(bo);
	}

    /**
     * 导出账簿列表
     */
    @ApiOperation("导出账簿列表")
    @PreAuthorize("@ss.hasPermi('finance:accountBook:export')")
    @Log(title = "账簿", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdAccountBookVo> export(@Validated @RequestBody TBdAccountBookQueryBo bo) {
		List<TBdAccountBookVo> list = iTBdAccountBookService.queryList(bo);
		ExcelUtil<TBdAccountBookVo> util = new ExcelUtil<TBdAccountBookVo>(TBdAccountBookVo.class);
		return util.exportExcel(list, "账簿");
	}

    /**
     * 获取账簿详细信息
     */
    @ApiOperation("获取账簿详细信息")
    @PreAuthorize("@ss.hasPermi('finance:accountBook:query')")
    @GetMapping("/getInfo/{fBookId}")
    public AjaxResult<TBdAccountBookVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fBookId") Integer fBookId) {
        return AjaxResult.success(iTBdAccountBookService.queryById(fBookId));
    }

    /**
     * 新增账簿
     */
    @ApiOperation("新增账簿")
    @PreAuthorize("@ss.hasPermi('finance:accountBook:add')")
    @Log(title = "账簿", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdAccountBookAddBo bo) {
        return toAjax(iTBdAccountBookService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改账簿
     */
    @ApiOperation("修改账簿")
    @PreAuthorize("@ss.hasPermi('finance:accountBook:edit')")
    @Log(title = "账簿", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TBdAccountBookEditBo bo) {
        return toAjax(iTBdAccountBookService.updateByEditBo(bo) ? 1 : 0);
    }

	/**
	 * 审核账簿
	 */
	@ApiOperation("审核账簿")
	@PreAuthorize("@ss.hasPermi('finance:accountBook:remove')")
	@Log(title = "账簿" , businessType = BusinessType.UPDATE)
	@PostMapping("audit/{fIds}")
	public AjaxResult<Void> audit(@NotEmpty(message = "主键不能为空")
									  @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
		return toAjax(iTBdAccountBookService.audit(s) ? 1 : 0);
	}

    /**
     * 删除账簿
     */
    @ApiOperation("删除账簿")
    @PreAuthorize("@ss.hasPermi('finance:accountBook:remove')")
    @Log(title = "账簿" , businessType = BusinessType.DELETE)
    @PostMapping("remove/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
									   @PathVariable("fIds") String fIds) {
		List<Integer> s= Arrays.asList(fIds.split(",")).stream().mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return toAjax(iTBdAccountBookService.deleteWithValidByIds(s, true) ? 1 : 0);
    }
}
