package com.skeqi.finance.mapper.adjustmentperiod;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.finance.domain.adjustmentperiod.TGlAdjustperiod;
import com.skeqi.finance.pojo.bo.adjustmentperiod.TGlAdjustperiodpQueryBo;
import com.skeqi.finance.pojo.vo.adjustmentperiod.TGlAdjustperiodVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlAdjustperiodMapper extends BaseMapperPlus<TGlAdjustperiod> {
	IPage<TGlAdjustperiodVo> queryPageList(Page<TGlAdjustperiodpQueryBo> userPage, @Param("ew") LambdaQueryWrapper<TGlAdjustperiod> buildQueryWrapper);

	Integer  queryByBookIdAndYear(@Param("bo") TGlAdjustperiodpQueryBo bo);
}
