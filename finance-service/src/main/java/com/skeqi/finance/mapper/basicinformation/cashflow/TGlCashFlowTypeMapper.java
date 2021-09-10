package com.skeqi.finance.mapper.basicinformation.cashflow;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.basicinformation.cashflow.TGlCashFlowType;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowTypeQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowTypeVo;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 现金流量项目类别-2Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlCashFlowTypeMapper extends BaseMapperPlus<TGlCashFlowType> {

    IPage<TGlCashFlowTypeVo> queryPageList(Page<TGlCashFlowTypeQueryBo> userPage, TGlCashFlowTypeQueryBo bo);
}
