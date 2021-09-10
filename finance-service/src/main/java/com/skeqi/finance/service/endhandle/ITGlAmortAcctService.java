package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.amortization.TGlAmortAcct;
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortAcctVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortAcctEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证摊销-待摊销科目Service接口
 *
 * @author toms
 * @date 2021-07-27
 */
public interface ITGlAmortAcctService extends IServicePlus<TGlAmortAcct> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlAmortAcctVo queryById(Long fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlAmortAcctVo> queryPageList(TGlAmortAcctQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlAmortAcctVo> queryList(TGlAmortAcctQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证摊销-待摊销科目
	 * @param bo 凭证摊销-待摊销科目新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlAmortAcctAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证摊销-待摊销科目
	 * @param bo 凭证摊销-待摊销科目编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlAmortAcctEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
