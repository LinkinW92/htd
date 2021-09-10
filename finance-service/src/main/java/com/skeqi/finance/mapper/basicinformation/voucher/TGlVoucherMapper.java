package com.skeqi.finance.mapper.basicinformation.voucher;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.voucher.TGlVoucher;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.voucher.BalanceVchDetailQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherQueryBo;
import com.skeqi.finance.pojo.bo.voucher.VoucherBalanceBo;
import com.skeqi.finance.pojo.vo.voucher.*;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 凭证录入主Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlVoucherMapper extends BaseMapperPlus<TGlVoucher> {

	VoucherVo getInfoById(@Param("id") Integer id);

	List<TGlVoucher> queryListByBook(@Param("bookId") Integer bookId,@Param("year") Integer year,@Param("period") Integer period);


	IPage<VoucherPageVo> getVoucherPage(Page<TGlVoucherQueryBo> page, @Param("bo") TGlVoucherQueryBo bo);

	TGlVoucherVo queryIncomeLossVch(@Param("bo") TGlVoucherQueryBo bo);

	Integer getVoucherPageCount(@Param("bo") TGlVoucherQueryBo bo);


	VoucherBalanceVo queryVoucherBalance(@Param("bo")VoucherBalanceBo bo);

	List<BalanceDetailVo> queryVchDetail(@Param("bo") BalanceVchDetailQueryBo bo);

	Integer countVchNo(@Param("bookId") Integer bookId,@Param("fYear") Integer fYear,
					   @Param("fPeriod") Integer fPeriod,@Param("fVoucherGroupId") Integer fVoucherGroupId,@Param("fVoucherGroupNo") Integer fVoucherGroupNo);

	Integer delVchNo(@Param("bookId") Integer bookId,@Param("fYear") Integer fYear,
					   @Param("fPeriod") Integer fPeriod,@Param("fVoucherGroupId") Integer fVoucherGroupId,@Param("fVoucherGroupNo") Integer fVoucherGroupNo);

	List<TGlVoucherEntryVo> queryExchangeINfo(@Param("bo")TGlVoucherEntryQueryBo bo);
}
