package com.skeqi.finance.service.account;

import com.skeqi.finance.domain.TBdAccountFlexentry;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountFlexentryVo;
import com.skeqi.finance.pojo.bo.TBdAccountFlexentry.TBdAccountFlexentryQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccountFlexentry.TBdAccountFlexentryAddBo;
import com.skeqi.finance.pojo.bo.TBdAccountFlexentry.TBdAccountFlexentryEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 科目核算维度组分录Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdAccountFlexentryService extends IServicePlus<TBdAccountFlexentry> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdAccountFlexentryVo queryById(Integer fEntryId);

	/**
	 * 查询列表
	 * @return
	 */
	List<TBdAccountFlexentryVo> queryByAcctId(Integer acctId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdAccountFlexentryVo> queryPageList(TBdAccountFlexentryQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdAccountFlexentryVo> queryList(TBdAccountFlexentryQueryBo bo);

	/**
	 * 根据新增业务对象插入科目核算维度组分录
	 * @param bo 科目核算维度组分录新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdAccountFlexentryAddBo bo);

	/**
	 * 根据编辑业务对象修改科目核算维度组分录
	 * @param bo 科目核算维度组分录编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdAccountFlexentryEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
