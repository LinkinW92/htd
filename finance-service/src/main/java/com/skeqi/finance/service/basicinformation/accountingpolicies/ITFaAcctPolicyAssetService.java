package com.skeqi.finance.service.basicinformation.accountingpolicies;

import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicyAsset;
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyAssetVo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAssetQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAssetAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAssetEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 会计政策资产政策Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITFaAcctPolicyAssetService extends IServicePlus<TFaAcctPolicyAsset> {
	/**
	 * 查询单个
	 * @return
	 */
	TFaAcctPolicyAssetVo queryById(Integer fEntryId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TFaAcctPolicyAssetVo> queryPageList(TFaAcctPolicyAssetQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TFaAcctPolicyAssetVo> queryList(TFaAcctPolicyAssetQueryBo bo);

	/**
	 * 根据新增业务对象插入会计政策资产政策
	 * @param bo 会计政策资产政策新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TFaAcctPolicyAssetAddBo bo);

	/**
	 * 根据编辑业务对象修改会计政策资产政策
	 * @param bo 会计政策资产政策编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TFaAcctPolicyAssetEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);


    Boolean insertByAddByList(List<TFaAcctPolicyAssetAddBo> policyAssetList);

	Boolean updateByEditBoList(List<TFaAcctPolicyAssetEditBo> policyAssetList);

	/**
	 * 按会计政策id查询资产政策列表
	 * @param fAcctpolicyId
	 * @return
	 */
	List<TFaAcctPolicyAssetVo> queryListByFAcctpolicyId(Integer fAcctpolicyId);
}
