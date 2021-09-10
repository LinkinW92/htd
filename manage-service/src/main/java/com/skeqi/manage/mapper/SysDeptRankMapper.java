package com.skeqi.manage.mapper;

import com.skeqi.manage.domain.SysDeptRank;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 职级Mapper接口
 *
 * @author toms
 * @date 2021-08-26
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface SysDeptRankMapper extends BaseMapperPlus<SysDeptRank> {

}
