package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingInacct;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingInacctVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingInacctEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证预提-转入科目Service接口
 *
 * @author toms
 * @date 2021-07-27
 */
public interface ITGlWithholdingInacctService extends IServicePlus<TGlWithholdingInacct> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlWithholdingInacctVo queryById(Long fEnterAccountId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlWithholdingInacctVo> queryPageList(TGlWithholdingInacctQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlWithholdingInacctVo> queryList(TGlWithholdingInacctQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证预提-转入科目
	 * @param bo 凭证预提-转入科目新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlWithholdingInacctAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证预提-转入科目
	 * @param bo 凭证预提-转入科目编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlWithholdingInacctEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
