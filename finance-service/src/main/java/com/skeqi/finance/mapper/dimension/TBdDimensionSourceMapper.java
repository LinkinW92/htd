package com.skeqi.finance.mapper.dimension;

import com.skeqi.finance.domain.TBdDimensionSource;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.domain.help.TBdBaseType;
import com.skeqi.finance.domain.help.TBdHelpData;
import com.skeqi.finance.pojo.vo.dimension.TBdDimensionSourceVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 维度来源Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
//@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TBdDimensionSourceMapper extends BaseMapperPlus<TBdDimensionSource> {

	TBdDimensionSource findByCode(@Param("id") Integer code);


	/**
	 * 查询基础资料类别
	 */
	List<TBdDimensionSourceVo> listBastType();

	/**
	 * 查询基础资料类别
	 */
	TBdBaseType getOneBastType(@Param("id") Integer id);
	/**
	 * 查询基础资料信息列表
	 */
	List<Map> listBaseData(@Param("sql") String sql);
	List<TBdHelpData> listBaseData2(@Param("typeId") Integer typeId);

	/**
	 * 查询基础资料信息 单个
	 */
	TBdDimensionSource getOneBaseData(@Param("sql") String sql,@Param("id") Integer id);
	TBdHelpData getOneBaseData2(@Param("id") Integer id);

}
