package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingPeriod;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingPeriodVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingPeriodQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingPeriodAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingPeriodEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证预提-预提期间Service接口
 *
 * @author toms
 * @date 2021-07-27
 */
public interface ITGlWithholdingPeriodService extends IServicePlus<TGlWithholdingPeriod> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlWithholdingPeriodVo queryById(Long fYear);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlWithholdingPeriodVo> queryPageList(TGlWithholdingPeriodQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlWithholdingPeriodVo> queryList(TGlWithholdingPeriodQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证预提-预提期间
	 * @param bo 凭证预提-预提期间新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlWithholdingPeriodAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证预提-预提期间
	 * @param bo 凭证预提-预提期间编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlWithholdingPeriodEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
