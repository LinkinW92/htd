package com.skeqi.finance.service.endhandle;

import com.skeqi.finance.domain.endhandle.amortization.TGlAmortInacct;
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortInacctVo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortInacctQueryBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortInacctAddBo;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortInacctEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证摊销-转入科目Service接口
 *
 * @author toms
 * @date 2021-07-27
 */
public interface ITGlAmortInacctService extends IServicePlus<TGlAmortInacct> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlAmortInacctVo queryById(Long fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlAmortInacctVo> queryPageList(TGlAmortInacctQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlAmortInacctVo> queryList(TGlAmortInacctQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证摊销-转入科目
	 * @param bo 凭证摊销-转入科目新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlAmortInacctAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证摊销-转入科目
	 * @param bo 凭证摊销-转入科目编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlAmortInacctEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
