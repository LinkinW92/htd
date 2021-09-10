package com.skeqi.finance.service.account;

import com.skeqi.finance.domain.TBdAccount;
import com.skeqi.finance.pojo.vo.TBdAccount.AccountTableVo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountVo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountAddBo;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 科目信息Service接口
 *
 * @author toms
 * @date 2021-07-19
 */
public interface ITBdAccountService extends IServicePlus<TBdAccount> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdAccountVo queryById(Integer fAcctId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdAccountVo> queryPageList(TBdAccountQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdAccountVo> queryList(TBdAccountQueryBo bo);

	/**
	 * 根据新增业务对象插入科目信息
	 * @param bo 科目信息新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdAccountAddBo bo);

	/**
	 * 根据编辑业务对象修改科目信息
	 * @param bo 科目信息编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdAccountEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


	/**
	 * 科目表查询。查询关联的会计要素，关联科目类别
	 *
	 * @return
	 */
	public List<AccountTableVo> listAcountTable();

	/**
	 * 修改项目批量预设信息
	 * @param list
	 * @return
	 */
	Boolean updateByList(List<TBdAccountEditBo> list);
}
