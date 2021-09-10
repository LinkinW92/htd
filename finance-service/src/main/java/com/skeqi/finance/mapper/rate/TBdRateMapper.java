package com.skeqi.finance.mapper.rate;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.rate.TBdRate;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.rate.TBdRateQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.rate.TBdRateVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 基础汇率Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TBdRateMapper extends BaseMapperPlus<TBdRate> {


	IPage<TBdRateVo> getRatePage(Page<TBdRateQueryBo> page,@Param("bo") TBdRateQueryBo bo);

	List<TBdRateVo> getRateList(@Param("bo") TBdRateQueryBo bo);

	Integer getRateCount(TBdRateQueryBo bo);

	TBdRate findRateByDateAndType(@Param("date") Date date,@Param("type") Integer type,@Param("fCyforid") Integer fCyforid,@Param("fCytoid") Integer fCytoid);

}
