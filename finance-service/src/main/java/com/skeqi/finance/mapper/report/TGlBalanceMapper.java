package com.skeqi.finance.mapper.report;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.finance.domain.TGlBalance;
import com.skeqi.finance.pojo.bo.ABFlexItemPropertyDetailQueryBo;
import com.skeqi.finance.pojo.bo.ABTotalBalanceQueryBo;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceQueryBo;
import com.skeqi.finance.pojo.bo.voucher.BalanceVchDetailQueryBo;
import com.skeqi.finance.pojo.vo.voucher.BalanceVchVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 科目余额Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
//@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlBalanceMapper extends BaseMapperPlus<TGlBalance> {

	/**
	 * 查询科目余额信息
	 */
	Page<Map> queryList2(IPage<TGlBalance> page, @Param("bo") TGlBalanceQueryBo bo);

	/**
	 * 查询总分类账信息
	 */
	Page<Map> queryList3(IPage<TGlBalance> page, @Param("bo") ABTotalBalanceQueryBo bo);

	/**
	 * 查询核算维度明细账
	 */
	Page<Map> queryList4(IPage<TGlBalance> page, @Param("bo") ABFlexItemPropertyDetailQueryBo bo);

	/**
	 * 查询期初余额
	 */
	BigDecimal queryBeginBalance(Map map);

	/**
	 * 查询明细账需要
	 *
	 * @return
	 */
	List<BalanceVchVo> queryByVch(@Param("bo") BalanceVchDetailQueryBo bo);

	TGlBalance queryByVchNoDetail(@Param("bo") TGlBalanceQueryBo bo);

}
