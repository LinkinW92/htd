package com.skeqi.finance.mapper.endhandle;

import com.skeqi.finance.domain.endhandle.TGlPlschemeEntry;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.vo.endhandle.TGlPlschemeEntryVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 结转损益方案分录Mapper接口
 *
 * @author toms
 * @date 2021-08-02
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlPlschemeEntryMapper extends BaseMapperPlus<TGlPlschemeEntry> {

	List<TGlPlschemeEntryVo> queryByEntryId(@Param("entryId") Integer entryId);
}
