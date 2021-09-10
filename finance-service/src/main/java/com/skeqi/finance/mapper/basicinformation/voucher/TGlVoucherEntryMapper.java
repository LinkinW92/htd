package com.skeqi.finance.mapper.basicinformation.voucher;

import com.skeqi.finance.domain.voucher.TGlVoucherEntry;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherEntryVo;
import com.skeqi.finance.pojo.vo.voucher.VoucherEntryVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 凭证录入分Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlVoucherEntryMapper extends BaseMapperPlus<TGlVoucherEntry> {

	List<VoucherEntryVo> getPageByVid(@Param("vId") Integer vId);

	List<VoucherEntryVo> queryByEntryId(@Param("entryId") Integer entryId);

	List<VoucherEntryVo> getCashFlowByVid(@Param("vId") Integer vId);

	Integer updateDetailCode(@Param("code") String code,@Param("dimensionCode") String dimensionCode,@Param("id") Integer id);

	Integer delByVchEntryId(@Param("vId") Integer vId);
}
