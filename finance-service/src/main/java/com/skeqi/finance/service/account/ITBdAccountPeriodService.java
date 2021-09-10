package com.skeqi.finance.service.account;

import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.finance.domain.TBdAccountPeriod;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountPeriodVo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountPeriodQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountPeriodAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountPeriodEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.pojo.vo.period.AccountPeriodVo;

import java.util.Collection;
import java.util.List;

/**
 * 会计期间Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdAccountPeriodService extends IServicePlus<TBdAccountPeriod> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdAccountPeriodVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdAccountPeriodVo> queryPageList(TBdAccountPeriodQueryBo bo);

	/**
	 * 查询期间
	 * @param bo
	 * @return
	 */
	List<AccountPeriodVo> listPeriod(TBdAccountPeriodQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdAccountPeriodVo> queryList(TBdAccountPeriodQueryBo bo);

	/**
	 * 根据新增业务对象插入会计期间
	 * @param bo 会计期间新增业务对象
	 * @return
	 */
	AjaxResult insertByAddBo(TBdAccountPeriodAddBo bo);

	/**
	 * 根据编辑业务对象修改会计期间
	 * @param bo 会计期间编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdAccountPeriodEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);

	Boolean deleteWithValidByFEntryId(Integer id);
}
