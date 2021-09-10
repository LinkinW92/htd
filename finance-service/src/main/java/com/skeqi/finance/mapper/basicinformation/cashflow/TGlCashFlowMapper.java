package com.skeqi.finance.mapper.basicinformation.cashflow;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.basicinformation.cashflow.TGlCashFlow;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * 现金流量项目-3Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlCashFlowMapper extends BaseMapperPlus<TGlCashFlow> {

	IPage<TGlCashFlowVo> queryPageList(Page<TGlCashFlowQueryBo> userPage, @Param("bo") TGlCashFlowQueryBo bo );
}
