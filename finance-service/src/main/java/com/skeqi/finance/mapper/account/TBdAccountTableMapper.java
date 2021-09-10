package com.skeqi.finance.mapper.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.TBdAccountTable;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.TBdAccountTable.TBdAccountTableQueryBo;
import com.skeqi.finance.pojo.bo.TBdFlexItemProperty.TBdFlexItemPropertyQueryBo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountTableVo;
import com.skeqi.finance.pojo.vo.dimension.TBdFlexItemPropertyVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * 科目Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TBdAccountTableMapper extends BaseMapperPlus<TBdAccountTable> {


	/*
	查询科目表
	 */
	TBdAccountTableVo queryOne2(@Param("fId") Integer fId);

	/*
	查询科目表
	 */
	Page<TBdAccountTableVo> queryList2(IPage<TBdAccountTableVo> page, @Param("bo") TBdAccountTableQueryBo bo);

}
