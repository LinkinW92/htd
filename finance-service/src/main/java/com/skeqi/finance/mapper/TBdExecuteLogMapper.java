package com.skeqi.finance.mapper;

import com.skeqi.finance.domain.TBdExecuteLog;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 执行业务日志记录Mapper接口
 *
 * @author toms
 * @date 2021-08-07
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TBdExecuteLogMapper extends BaseMapperPlus<TBdExecuteLog> {

}
