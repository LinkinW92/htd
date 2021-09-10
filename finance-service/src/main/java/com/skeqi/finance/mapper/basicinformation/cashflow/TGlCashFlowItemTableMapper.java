package com.skeqi.finance.mapper.basicinformation.cashflow;

import com.skeqi.finance.domain.basicinformation.cashflow.TGlCashFlowItemTable;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 现金流量项目-1Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlCashFlowItemTableMapper extends BaseMapperPlus<TGlCashFlowItemTable> {

}
