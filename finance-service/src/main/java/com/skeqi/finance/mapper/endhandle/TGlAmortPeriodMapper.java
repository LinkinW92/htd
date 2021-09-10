package com.skeqi.finance.mapper.endhandle;

import com.skeqi.finance.domain.endhandle.amortization.TGlAmortPeriod;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortPeriodVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 凭证摊销-摊销期间Mapper接口
 *
 * @author toms
 * @date 2021-07-27
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlAmortPeriodMapper extends BaseMapperPlus<TGlAmortPeriod> {
  List<TGlAmortPeriodVo> queryList(@Param("fSchemeId") Integer fSchemeId);
  Integer delBySchemeId(@Param("fSchemeId") Integer fSchemeId);
}
