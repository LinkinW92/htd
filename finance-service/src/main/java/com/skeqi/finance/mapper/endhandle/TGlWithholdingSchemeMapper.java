package com.skeqi.finance.mapper.endhandle;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingScheme;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.endhandle.TGlWithholdingSchemeQueryBo;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingSchemeVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * 凭证预提Mapper接口
 *
 * @author toms
 * @date 2021-07-27
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlWithholdingSchemeMapper extends BaseMapperPlus<TGlWithholdingScheme> {

	IPage<TGlWithholdingSchemeVo> queryPageList(Page<TGlWithholdingSchemeQueryBo> userPage, @Param("bo") TGlWithholdingSchemeQueryBo bo);

	TGlWithholdingSchemeVo getInfo(@Param("fSchemeId") Integer fSchemeId);
}
