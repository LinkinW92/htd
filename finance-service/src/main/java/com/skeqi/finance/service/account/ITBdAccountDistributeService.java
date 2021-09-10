package com.skeqi.finance.service.account;

import com.skeqi.finance.domain.TBdAccountDistribute;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountDistributeVo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountDistributeQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountDistributeAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountDistributeEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 科目分发Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdAccountDistributeService extends IServicePlus<TBdAccountDistribute> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdAccountDistributeVo queryById(Integer fEntryId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdAccountDistributeVo> queryPageList(TBdAccountDistributeQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdAccountDistributeVo> queryList(TBdAccountDistributeQueryBo bo);

	/**
	 * 根据新增业务对象插入科目分发
	 * @param bo 科目分发新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdAccountDistributeAddBo bo);

	/**
	 * 根据编辑业务对象修改科目分发
	 * @param bo 科目分发编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdAccountDistributeEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
