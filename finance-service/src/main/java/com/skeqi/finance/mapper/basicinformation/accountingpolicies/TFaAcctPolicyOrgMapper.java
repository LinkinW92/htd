package com.skeqi.finance.mapper.basicinformation.accountingpolicies;

import com.skeqi.finance.domain.basicinformation.accountingpolicies.TFaAcctPolicyOrg;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyOrgAddBo;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * 会计政策适用核算组织Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TFaAcctPolicyOrgMapper extends BaseMapperPlus<TFaAcctPolicyOrg> {


    Integer insertByAddBoList(List<TFaAcctPolicyOrgAddBo> orgAddBoList);
}
