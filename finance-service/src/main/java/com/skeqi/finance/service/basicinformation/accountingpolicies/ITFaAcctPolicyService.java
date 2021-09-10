package com.skeqi.finance.service.basicinformation.accountingpolicies;

import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicy;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemEditBo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyVo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 会计政策Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITFaAcctPolicyService extends IServicePlus<TFaAcctPolicy> {
	/**
	 * 查询单个
	 * @return
	 */
	TFaAcctPolicyVo queryById(Integer fAcctpolicyId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TFaAcctPolicyVo> queryPageList(TFaAcctPolicyQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TFaAcctPolicyVo> queryList(TFaAcctPolicyQueryBo bo);

	/**
	 * 根据新增业务对象插入会计政策
	 * @param bo 会计政策新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TFaAcctPolicyAddBo bo);

	/**
	 * 根据编辑业务对象修改会计政策
	 * @param bo 会计政策编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TFaAcctPolicyEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids);

	/**
	 * 审核
	 * @param ids
	 * @return
	 */
	Boolean auditByIds(List<Integer> ids);

	/**
	 * 反审核
	 * @param ids
	 * @return
	 */
	Boolean antiAuditByIds(List<Integer> ids);

	/**
	 * 禁用
	 * @param bo
	 * @return
	 */
	Boolean disableByEditBo(TFaAcctPolicyEditBo bo);


}
