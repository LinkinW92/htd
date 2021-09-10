package com.skeqi.finance.mapper;

import com.skeqi.finance.domain.TGlVoucherGroupNo;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 凭证号排序Mapper接口
 *
 * @author toms
 * @date 2021-08-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlVoucherGroupNoMapper extends BaseMapperPlus<TGlVoucherGroupNo> {


	List<Integer> queryNotContinueNo(@Param("fYear") Integer fYear,@Param("fPeriod") Integer fPeriod,
	                          @Param("fVoucherGroupId") Integer fVoucherGroupId,@Param("fBookId") Integer fBookId);

	Integer queryMaxNo(@Param("fYear") Integer fYear,@Param("fPeriod") Integer fPeriod,
					   @Param("fVoucherGroupId") Integer fVoucherGroupId,@Param("fBookId") Integer fBookId);
}
