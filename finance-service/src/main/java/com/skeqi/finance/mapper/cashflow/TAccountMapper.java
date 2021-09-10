package com.skeqi.finance.mapper.cashflow;

import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.finance.domain.cashflow.TAccount;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountVo;
import com.skeqi.finance.pojo.vo.cashflow.TGlVoucherEntryCashVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * T型账Mapper接口
 *
 * @author wf
 * @date 2021-07-21
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TAccountMapper extends BaseMapperPlus<TAccount> {
	List<TGlVoucherEntryCashVo> selectFlowProject(@Param("list") List<Integer> list);

	List<TBdAccountVo> selectAccount(TAccount bo);

	Integer updateByList(@Param("list") List<TGlVoucherEntryCashVo> tGlVoucherEntryCashVoList);

}
