package com.skeqi.finance.mapper.endhandle;

import com.skeqi.finance.domain.endhandle.TGlExchangeSchemeEntry;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeSchemeEntryVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 期末调汇方案分录Mapper接口
 *
 * @author toms
 * @date 2021-07-30
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlExchangeSchemeEntryMapper extends BaseMapperPlus<TGlExchangeSchemeEntry> {

	Integer deleteByFid(@Param("fid") Integer fid);

	List<TGlExchangeSchemeEntryVo> queryList(@Param("fid") Integer fid);
}
