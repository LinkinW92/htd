package com.skeqi.finance.mapper.endhandle;

import com.skeqi.finance.domain.endhandle.withholding.TGlWithholdingInacct;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.vo.endhandle.TGlWithholdingInacctVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 凭证预提-转入科目Mapper接口
 *
 * @author toms
 * @date 2021-07-27
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlWithholdingInacctMapper extends BaseMapperPlus<TGlWithholdingInacct> {

	List<TGlWithholdingInacctVo> queryList(@Param("fSchemeId") Integer fSchemeId);

}
