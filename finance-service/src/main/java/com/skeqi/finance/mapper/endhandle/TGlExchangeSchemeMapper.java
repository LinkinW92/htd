package com.skeqi.finance.mapper.endhandle;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.endhandle.TGlExchangeScheme;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.endhandle.TGlExchangeSchemeQueryBo;
import com.skeqi.finance.pojo.vo.endhandle.TGlExchangeSchemeVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 期末调汇方案Mapper接口
 *
 * @author toms
 * @date 2021-07-30
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlExchangeSchemeMapper extends BaseMapperPlus<TGlExchangeScheme> {

	TGlExchangeSchemeVo queryById(@Param("id") Integer id);
	IPage<TGlExchangeSchemeVo> queryPageList(Page<TGlExchangeSchemeQueryBo> page, @Param("bo") TGlExchangeSchemeQueryBo bo);
}
