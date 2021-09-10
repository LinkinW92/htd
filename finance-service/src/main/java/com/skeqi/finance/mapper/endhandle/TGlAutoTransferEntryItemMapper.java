package com.skeqi.finance.mapper.endhandle;

import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransferEntryItem;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * 自动转账核算维度Mapper接口
 *
 * @author toms
 * @date 2021-07-26
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlAutoTransferEntryItemMapper extends BaseMapperPlus<TGlAutoTransferEntryItem> {

	Integer delByTransferEntryId(@Param("id")Integer id);

}
