package com.skeqi.finance.mapper.basicinformation.accountingpolicies;

import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicyAsset;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAssetAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyAssetEditBo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingpolicies.TFaAcctPolicyAssetVo;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * 会计政策资产政策Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TFaAcctPolicyAssetMapper extends BaseMapperPlus<TFaAcctPolicyAsset> {


    Integer insertByAddBoList(List<TFaAcctPolicyAssetAddBo> policyAssetList);

	Integer updateByEditList(List<TFaAcctPolicyAssetEditBo> editList);

	/**
	 * 按会计政策id查询资产政策列表
	 * @param fAcctpolicyId
	 * @return
	 */
    List<TFaAcctPolicyAssetVo> queryListByFAcctpolicyId(Integer fAcctpolicyId);
}
