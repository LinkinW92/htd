package com.skeqi.finance.mapper.endhandle;

import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransferEntry;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferEntryVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自动转账分录Mapper接口
 *
 * @author toms
 * @date 2021-07-26
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlAutoTransferEntryMapper extends BaseMapperPlus<TGlAutoTransferEntry> {

	Integer delByTransferId(@Param("id") Integer id);

	List<TGlAutoTransferEntryVo> queryPageByPId(@Param("pId") Integer pId);
}
