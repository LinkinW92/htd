package com.skeqi.finance.service.account;

import com.skeqi.finance.domain.TBdAccountcy;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountcyVo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountcyQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountcyAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountcyEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 科目核算币别Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdAccountcyService extends IServicePlus<TBdAccountcy> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdAccountcyVo queryById(Integer fCurrencyId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdAccountcyVo> queryPageList(TBdAccountcyQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdAccountcyVo> queryList(TBdAccountcyQueryBo bo);

	/**
	 * 根据新增业务对象插入科目核算币别
	 * @param bo 科目核算币别新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdAccountcyAddBo bo);

	/**
	 * 根据编辑业务对象修改科目核算币别
	 * @param bo 科目核算币别编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdAccountcyEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
