package com.skeqi.finance.service.endhandle;

import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingScheme;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingSchemeVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingSchemeQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingSchemeAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingSchemeEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证预提Service接口
 *
 * @author toms
 * @date 2021-07-27
 */
public interface ITGlWithholdingSchemeService extends IServicePlus<TGlWithholdingScheme> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlWithholdingSchemeVo queryById(Integer fSchemeId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlWithholdingSchemeVo> queryPageList(TGlWithholdingSchemeQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlWithholdingSchemeVo> queryList(TGlWithholdingSchemeQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证预提
	 * @param bo 凭证预提新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlWithholdingSchemeAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证预提
	 * @param bo 凭证预提编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlWithholdingSchemeAddBo bo);

	/**
	 * 执行
	 * @param fId
	 * @return
	 */
	AjaxResult execute(Integer fId);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
