package com.skeqi.finance.mapper.cashflow;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.finance.domain.voucher.TGlVoucherEntryCash;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryCashQueryBo;
import com.skeqi.finance.pojo.vo.cashflow.TGlVoucherEntryCashVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 凭证分录现金流量Mapper接口
 *
 * @author toms
 * @date 2021-07-21
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlVoucherEntryCashMapper extends BaseMapperPlus<TGlVoucherEntryCash> {
	IPage<TGlVoucherEntryCashVo> queryPageList(Page<TGlVoucherEntryCashQueryBo> userPage, @Param("bo") TGlVoucherEntryCashQueryBo bo);


	List<TGlVoucherEntryCashVo> queryByVid(@Param("ids") List<Integer> ids);

	Integer delByVid(@Param("vId") Integer vId);


}
