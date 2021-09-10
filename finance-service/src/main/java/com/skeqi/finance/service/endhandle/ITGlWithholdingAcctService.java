package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingAcct;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingAcctVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingAcctEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证预提-预提科目Service接口
 *
 * @author toms
 * @date 2021-07-27
 */
public interface ITGlWithholdingAcctService extends IServicePlus<TGlWithholdingAcct> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlWithholdingAcctVo queryById(Long fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlWithholdingAcctVo> queryPageList(TGlWithholdingAcctQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlWithholdingAcctVo> queryList(TGlWithholdingAcctQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证预提-预提科目
	 * @param bo 凭证预提-预提科目新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlWithholdingAcctAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证预提-预提科目
	 * @param bo 凭证预提-预提科目编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlWithholdingAcctEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
