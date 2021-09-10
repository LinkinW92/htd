package com.skeqi.finance.service.account;

import com.skeqi.finance.domain.TBdAccountGroupTable;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountGroupTableVo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupTableQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupTableAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupTableEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 会计要素Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdAccountGroupTableService extends IServicePlus<TBdAccountGroupTable> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdAccountGroupTableVo queryById(Integer fAcctgroupTblid);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdAccountGroupTableVo> queryPageList(TBdAccountGroupTableQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdAccountGroupTableVo> queryList(TBdAccountGroupTableQueryBo bo);

	/**
	 * 根据新增业务对象插入会计要素
	 * @param bo 会计要素新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdAccountGroupTableAddBo bo);

	/**
	 * 根据编辑业务对象修改会计要素
	 * @param bo 会计要素编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdAccountGroupTableEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
