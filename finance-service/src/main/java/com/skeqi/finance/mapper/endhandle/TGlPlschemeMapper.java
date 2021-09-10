package com.skeqi.finance.mapper.endhandle;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.endhandle.TGlPlscheme;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.endhandle.TGlPlschemeQueryBo;
import com.skeqi.finance.pojo.vo.endhandle.TGlPlschemeVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 结转损益方案Mapper接口
 *
 * @author toms
 * @date 2021-08-02
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlPlschemeMapper extends BaseMapperPlus<TGlPlscheme> {

	TGlPlschemeVo getInfoById(@Param("id") Integer id);

	IPage<TGlPlschemeVo> queryPageList(Page<TGlPlschemeQueryBo> page,@Param("bo") TGlPlschemeQueryBo bo);

}
