package com.skeqi.finance.mapper.basicinformation.accountingsystem;

import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAcctSysDetail;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 会计核算体系之下级组织Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TOrgAcctSysDetailMapper extends BaseMapperPlus<TOrgAcctSysDetail> {

}
