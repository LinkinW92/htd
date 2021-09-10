package com.skeqi.finance.mapper.endhandle;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.endhandle.amortization.TGlAmortizationScheme;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.endhandle.TGlAmortizationSchemeQueryBo;
import com.skeqi.finance.pojo.vo.endhandle.TGlAmortizationSchemeVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 凭证摊销Mapper接口
 *
 * @author toms
 * @date 2021-07-27
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlAmortizationSchemeMapper extends BaseMapperPlus<TGlAmortizationScheme> {

	IPage<TGlAmortizationSchemeVo> queryPageList(Page<TGlAmortizationSchemeQueryBo> userPage, @Param("bo") TGlAmortizationSchemeQueryBo bo);

	TGlAmortizationSchemeVo getInfo(@Param("fSchemeId") Integer fSchemeId);
}
