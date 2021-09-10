package com.skeqi.finance.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.finance.domain.TGlExplanation;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationQueryBo;
import com.skeqi.finance.pojo.vo.TGlExplanationVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * 摘要库Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlExplanationMapper extends BaseMapperPlus<TGlExplanation> {
	IPage<TGlExplanationVo> queryPageList(Page<TGlExplanationQueryBo> userPage, @Param("bo") TGlExplanationQueryBo bo);

	TGlExplanation getExaminedOne(@Param("id") Integer id);
}
