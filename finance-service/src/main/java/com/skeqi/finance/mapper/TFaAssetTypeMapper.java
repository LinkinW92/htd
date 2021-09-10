package com.skeqi.finance.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.asset.TFaAssetType;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.asset.TFaAssetTypeQueryBo;
import com.skeqi.finance.pojo.vo.asset.TFaAssetTypeVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * 资产类别Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TFaAssetTypeMapper extends BaseMapperPlus<TFaAssetType> {

	IPage<TFaAssetTypeVo> queryPageList(Page<TFaAssetTypeQueryBo> userPage, @Param("ew") LambdaQueryWrapper<TFaAssetType> buildQueryWrapper);
}
