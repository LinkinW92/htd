package com.skeqi.finance.mapper;

import com.skeqi.finance.domain.TBdAccountCalendar;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 会计日历Mapper接口
 *
 * @author toms
 * @date 2021-07-14
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TBdAccountCalendarMapper extends BaseMapperPlus<TBdAccountCalendar> {

}
