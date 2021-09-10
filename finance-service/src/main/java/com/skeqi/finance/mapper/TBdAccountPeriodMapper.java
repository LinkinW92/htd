package com.skeqi.finance.mapper;

import com.skeqi.finance.domain.TBdAccountPeriod;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountPeriodQueryBo;
import com.skeqi.finance.pojo.vo.period.AccountPeriodVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会计期间Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TBdAccountPeriodMapper extends BaseMapperPlus<TBdAccountPeriod> {

	Integer delByEntryId(@Param("entryId")Integer entryId);

	TBdAccountPeriod findInfo(@Param("entryId")Integer entryId,@Param("year")Integer year,@Param("period")Integer period);

	TBdAccountPeriod findPeriodInfo(@Param("entryId")Integer entryId,@Param("year")Integer year,@Param("month")Integer month);

	List<AccountPeriodVo> listPeriod(@Param("bo") TBdAccountPeriodQueryBo bo);

	AccountPeriodVo queryNextPeriod(@Param("cId") Integer cId,@Param("fYear") Integer fYear,@Param("pYear") Integer pYear,@Param("fPeriod") Integer fPeriod);

	AccountPeriodVo queryPrePeriod(@Param("cId") Integer cId,@Param("fYear") Integer fYear,@Param("pYear") Integer pYear,@Param("fPeriod") Integer fPeriod);
}
