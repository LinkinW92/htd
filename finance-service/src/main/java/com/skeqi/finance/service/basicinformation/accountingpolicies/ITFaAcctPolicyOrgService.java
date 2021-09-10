package com.skeqi.finance.service.basicinformation.accountingpolicies;

import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicyOrg;
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyOrgVo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 会计政策适用核算组织Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITFaAcctPolicyOrgService extends IServicePlus<TFaAcctPolicyOrg> {
	/**
	 * 查询单个
	 * @return
	 */
	TFaAcctPolicyOrgVo queryById(Integer fEntryId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TFaAcctPolicyOrgVo> queryPageList(TFaAcctPolicyOrgQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TFaAcctPolicyOrgVo> queryList(TFaAcctPolicyOrgQueryBo bo);

	/**
	 * 根据新增业务对象插入会计政策适用核算组织
	 * @param bo 会计政策适用核算组织新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TFaAcctPolicyOrgAddBo bo);

	/**
	 * 根据编辑业务对象修改会计政策适用核算组织
	 * @param bo 会计政策适用核算组织编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TFaAcctPolicyOrgEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);

}
