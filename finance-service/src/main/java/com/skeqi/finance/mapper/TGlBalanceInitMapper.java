package com.skeqi.finance.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.finance.domain.TGlBalanceInit;
import com.skeqi.finance.pojo.bo.TGlBalance.TGlBalanceInitQueryBo;
import com.skeqi.finance.pojo.vo.init.TGlBalanceInitVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * 科目初始录入数据Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlBalanceInitMapper extends BaseMapperPlus<TGlBalanceInit> {

	/**
	查询科目表
 	*/
	TGlBalanceInitVo queryOne2(@Param("fId") Integer fId);

	/**
	查询初始化数据
	 */
	Page<TGlBalanceInitVo> queryList2(IPage<TGlBalanceInitVo> page, @Param("bo") TGlBalanceInitQueryBo bo);

	/**
	查询初始化数据
	 */
	Page<TGlBalanceInitVo> queryList3(IPage<TGlBalanceInitVo> page, @Param("bo") TGlBalanceInitQueryBo bo);

	/**
	 * 初始化数据  转入 主表数据
	 */
	void endInit(@Param("fAccountBookId") Integer fAccountBookId);

	/**
	 * 初始化数据  转入 主表数据
	 */
	void notendInit(@Param("fAccountBookId") Integer fAccountBookId);
}
