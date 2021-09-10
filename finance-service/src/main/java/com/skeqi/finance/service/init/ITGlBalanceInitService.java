package com.skeqi.finance.service.init;

import com.skeqi.finance.domain.TGlBalanceInit;
import com.skeqi.finance.pojo.vo.init.TGlBalanceInitVo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceInitQueryBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceInitAddBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceInitEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 科目初始录入数据Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITGlBalanceInitService extends IServicePlus<TGlBalanceInit> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlBalanceInitVo queryById(Integer fAccountBookId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlBalanceInitVo> queryPageList(TGlBalanceInitQueryBo bo);
	TableDataInfo<TGlBalanceInitVo> queryPageList2(TGlBalanceInitQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlBalanceInitVo> queryList(TGlBalanceInitQueryBo bo);

	/**
	 * 根据新增业务对象插入科目初始录入数据
	 * @param bo 科目初始录入数据新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlBalanceInitAddBo bo);

	/**
	 * 根据编辑业务对象修改科目初始录入数据
	 * @param bo 科目初始录入数据编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlBalanceInitEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
