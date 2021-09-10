package com.skeqi.finance.mapper.init;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.domain.init.TGlInitCashflow;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowTypeVo;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlInitCashflowMapper extends BaseMapper<TGlInitCashflow> {
	List<TGlCashFlowTypeVo> queryCashFlow();

	Integer insertByList(List<TGlInitCashflow> tGlInitCashflows);

	List<TGlInitCashflow> queryInitCashFlow(TGlInitCashflow initCashflow);

	Integer updateByList(List<TGlInitCashflow> add);
}
