package com.skeqi.finance.service.account;

import com.skeqi.finance.domain.TBdAccountType;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountTypeVo;
import com.skeqi.finance.pojo.bo.TBdAccountType.TBdAccountTypeQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccountType.TBdAccountTypeAddBo;
import com.skeqi.finance.pojo.bo.TBdAccountType.TBdAccountTypeEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 科目类别Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdAccountTypeService extends IServicePlus<TBdAccountType> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdAccountTypeVo queryById(Integer fAcctTypeId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdAccountTypeVo> queryPageList(TBdAccountTypeQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdAccountTypeVo> queryList(TBdAccountTypeQueryBo bo);

	/**
	 * 根据新增业务对象插入科目类别
	 * @param bo 科目类别新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdAccountTypeAddBo bo);

	/**
	 * 根据编辑业务对象修改科目类别
	 * @param bo 科目类别编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdAccountTypeEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
