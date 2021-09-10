package com.skeqi.finance.service.account;

import com.skeqi.finance.domain.TBdAccountTable;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountTableVo;
import com.skeqi.finance.pojo.bo.TBdAccountTable.TBdAccountTableQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccountTable.TBdAccountTableAddBo;
import com.skeqi.finance.pojo.bo.TBdAccountTable.TBdAccountTableEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 科目Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdAccountTableService extends IServicePlus<TBdAccountTable> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdAccountTableVo queryById(Integer fAcctTableId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdAccountTableVo> queryPageList(TBdAccountTableQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdAccountTableVo> queryList(TBdAccountTableQueryBo bo);

	/**
	 * 根据新增业务对象插入科目
	 * @param bo 科目新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdAccountTableAddBo bo);

	/**
	 * 根据编辑业务对象修改科目
	 * @param bo 科目编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdAccountTableEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
