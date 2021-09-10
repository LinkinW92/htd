package com.skeqi.finance.mapper.basicinformation.voucher;

import com.skeqi.finance.domain.voucher.TGlVoucherEntryDimension;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherEntryDimensionVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 凭证分录维度控制Mapper接口
 *
 * @author toms
 * @date 2021-07-21
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlVoucherEntryDimensionMapper extends BaseMapperPlus<TGlVoucherEntryDimension> {

	String getDimension(@Param("vid") Integer vid);

	TGlVoucherEntryDimensionVo getByEntryId(@Param("vid") Integer vid);
}
