package com.skeqi.finance.mapper.dimension;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.finance.domain.TBdFlexItemProperty;
import com.skeqi.finance.domain.TGlBalance;
import com.skeqi.finance.pojo.bo.TBdFlexItemProperty.TBdFlexItemPropertyQueryBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceQueryBo;
import com.skeqi.finance.pojo.vo.dimension.TBdFlexItemPropertyVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * 核算维度Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TBdFlexItemPropertyMapper extends BaseMapperPlus<TBdFlexItemProperty> {


	/*
	查询核算维度
	 */
	TBdFlexItemPropertyVo queryOne2(@Param("fId") Integer fId);
	/*
	查询核算维度
	 */
	Page<TBdFlexItemPropertyVo> queryList2(IPage<TBdFlexItemPropertyVo> page,@Param("bo") TBdFlexItemPropertyQueryBo bo);
}
