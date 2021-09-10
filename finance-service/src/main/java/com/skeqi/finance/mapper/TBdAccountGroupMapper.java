package com.skeqi.finance.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.TBdAccountGroup;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountGroupQueryBo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountGroupVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * 会计要素Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TBdAccountGroupMapper extends BaseMapperPlus<TBdAccountGroup> {

	IPage<TBdAccountGroupVo> queryPageList(Page<TBdAccountGroupQueryBo> page, @Param("bo")TBdAccountGroupQueryBo bo);

}
