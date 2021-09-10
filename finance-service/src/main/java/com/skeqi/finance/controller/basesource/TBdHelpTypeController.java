package com.skeqi.finance.controller.basesource;

import java.util.List;
import java.util.Arrays;

import cn.hutool.core.util.StrUtil;
import com.skeqi.common.exception.CustomException;
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
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpTypeVo;
import com.skeqi.finance.pojo.bo.help.TBdHelpTypeQueryBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpTypeAddBo;
import com.skeqi.finance.pojo.bo.help.TBdHelpTypeEditBo;
import com.skeqi.finance.service.help.ITBdHelpTypeService;
import com.skeqi.common.utils.poi.ExcelUtil;
import com.skeqi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 辅助资料类别Controller
 *
 * @author toms
 * @date 2021-07-13
 */
@Api(value = "辅助资料类别控制器", tags = {"辅助资料类别管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/finance/helpType")
public class TBdHelpTypeController extends BaseController {

    private final ITBdHelpTypeService iTBdHelpTypeService;

    /**
     * 查询辅助资料类别列表
     */
    @ApiOperation("查询辅助资料类别列表")
    @PreAuthorize("@ss.hasPermi('finance:helpType:list')")
    @PostMapping("/list")
    public TableDataInfo<TBdHelpTypeVo> list(@Validated @RequestBody TBdHelpTypeQueryBo bo) {
        return iTBdHelpTypeService.queryPageList(bo);
    }


	/**
	 * 查询辅助资料类别组
	 */
	@ApiOperation("查询辅助资料类别组")
	@PreAuthorize("@ss.hasPermi('finance:helpType:list')")
	@PostMapping("/listGroup")
	public TableDataInfo<TBdHelpTypeVo> listGroup(@Validated @RequestBody TBdHelpTypeQueryBo bo) {
		if(StrUtil.isBlank(bo.getFBusinessArea())){
			throw new CustomException("领域ID不能为空",1000);
		}
		return iTBdHelpTypeService.queryPageGroup(bo);
	}

	/**
	 * 查询辅助资料类别列表
	 */
	@ApiOperation("查询下级")
	@PreAuthorize("@ss.hasPermi('finance:helpType:getNextGrade')")
	@PostMapping("/getNextGrade")
	public AjaxResult getNextGrade(@Validated @RequestBody TBdHelpTypeQueryBo bo) {
		return AjaxResult.success(iTBdHelpTypeService.getNextGrade(bo));
	}

    /**
     * 导出辅助资料类别列表
     */
    @ApiOperation("导出辅助资料类别列表")
    @PreAuthorize("@ss.hasPermi('finance:helpType:export')")
    @Log(title = "辅助资料类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult<TBdHelpTypeVo> export(@Validated @RequestBody TBdHelpTypeQueryBo bo) {
        List<TBdHelpTypeVo> list = iTBdHelpTypeService.queryList(bo);
        ExcelUtil<TBdHelpTypeVo> util = new ExcelUtil<TBdHelpTypeVo>(TBdHelpTypeVo.class);
        return util.exportExcel(list, "辅助资料类别");
    }

    /**
     * 获取辅助资料类别详细信息
     */
    @ApiOperation("获取辅助资料类别详细信息")
    @PreAuthorize("@ss.hasPermi('finance:helpType:query')")
    @GetMapping("/getInfo/{fId}")
    public AjaxResult<TBdHelpTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("fId") Integer fId) {
        return AjaxResult.success(iTBdHelpTypeService.queryById(fId));
    }

    /**
     * 新增辅助资料类别
     */
    @ApiOperation("新增辅助资料类别")
    @PreAuthorize("@ss.hasPermi('finance:helpType:add')")
    @Log(title = "辅助资料类别", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/add")
    public AjaxResult<Void> add(@Validated @RequestBody TBdHelpTypeAddBo bo) {
        return toAjax(iTBdHelpTypeService.insertByAddBo(bo) ? 1 : 0);
    }

    /**
     * 修改辅助资料类别
     */
    @ApiOperation("修改辅助资料类别")
    @PreAuthorize("@ss.hasPermi('finance:helpType:edit')")
    @Log(title = "辅助资料类别", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("edit")
    public AjaxResult<Void> edit(@Validated @RequestBody TBdHelpTypeEditBo bo) {
    	if(null==bo.getFId()){
    		return AjaxResult.error("ID不能为空");
		}
        return toAjax(iTBdHelpTypeService.updateByEditBo(bo) ? 1 : 0);
    }

    /**
     * 删除辅助资料类别
     */
    @ApiOperation("删除辅助资料类别")
    @PreAuthorize("@ss.hasPermi('finance:helpType:remove')")
    @Log(title = "辅助资料类别" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{fIds}")
    public AjaxResult<Void> remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Integer[] fIds) {
        return toAjax(iTBdHelpTypeService.deleteWithValidByIds(Arrays.asList(fIds), true) ? 1 : 0);
    }
}
