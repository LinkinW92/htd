package com.skeqi.finance.mapper.endhandle;

import com.skeqi.finance.domain.endhandle.TGlPlschemeFlex;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 结账损益核算维度Mapper接口
 *
 * @author toms
 * @date 2021-08-17
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlPlschemeFlexMapper extends BaseMapperPlus<TGlPlschemeFlex> {

	List<TGlPlschemeFlex> queryFlexById(@Param("fId") Integer fId);

}
