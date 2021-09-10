package com.skeqi.finance.mapper.basicinformation.accountingsystem;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAccountSystem;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAccountSystemQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAccountSystemVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 会计核算体系Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TOrgAccountSystemMapper extends BaseMapperPlus<TOrgAccountSystem> {

	/**
	 * 分页查询
	 * @param userPage
	 * @param queryWrapper
	 * @return
	 */
    IPage<TOrgAccountSystemVo> queryPageList(Page<TOrgAccountSystemQueryBo> userPage, @Param("ew") Wrapper<TOrgAccountSystem> queryWrapper);

	List<TOrgAccountSystemVo> selectByFAcctOrgId(Integer fAcctOrgId);

	IPage<TOrgAccountSystemVo> queryPageListAccountSystem(Page<TOrgAccountSystemQueryBo> userPage, @Param("ew") LambdaQueryWrapper<TOrgAccountSystem> buildQueryWrapper);

	/**
	 * 按会计政策内码查询核算体系
	 * @param fAccPolicyId
	 * @return
	 */
	List<TOrgAccountSystem> selectByFAccPolicyId(Integer fAccPolicyId);
}
