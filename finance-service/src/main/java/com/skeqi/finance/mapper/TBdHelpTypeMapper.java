package com.skeqi.finance.mapper;

import com.skeqi.finance.domain.help.TBdHelpType;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.help.TBdHelpTypeQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpTypeVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 辅助资料类别Mapper接口
 *
 * @author toms
 * @date 2021-07-13
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TBdHelpTypeMapper extends BaseMapperPlus<TBdHelpType> {

	Integer findByAreaId(@Param("areaId") Integer areaId);

	String findByParentId(@Param("parentId") Integer parentId);

	List<TBdHelpTypeVo> queryPageGroup(@Param("bo") TBdHelpTypeQueryBo bo);

	Integer queryPageGroupCount(@Param("bo") TBdHelpTypeQueryBo bo);

}
