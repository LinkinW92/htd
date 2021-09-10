package com.skeqi.finance.mapper.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.TBdAccountType;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountEditBo;
import com.skeqi.finance.pojo.bo.TBdAccountTable.TBdAccountTableQueryBo;
import com.skeqi.finance.pojo.bo.TBdAccountType.TBdAccountTypeQueryBo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountTableVo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountTypeVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 科目类别Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TBdAccountTypeMapper extends BaseMapperPlus<TBdAccountType> {

    Integer updateByList(List<TBdAccountEditBo> list);

	/*
	查询科目类别
	 */
	TBdAccountTypeVo queryOne2(@Param("fId") Integer fId);

	/*
查询科目类别
 */
	Page<TBdAccountTypeVo> queryList2(IPage<TBdAccountTypeVo> page, @Param("bo") TBdAccountTypeQueryBo bo);

}
