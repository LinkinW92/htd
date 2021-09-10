package com.skeqi.finance.mapper.basicinformation.accountingsystem;

import com.skeqi.finance.domain.basicinformation.accountingsystem.TOrgAcctSysEntry;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.accountingsystem.TOrgAcctSysEntryEditBo;
import com.skeqi.finance.pojo.vo.basicinformation.accountingsystem.TOrgAcctSysEntryVo;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * 会计核算体系之会计主体Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TOrgAcctSysEntryMapper extends BaseMapperPlus<TOrgAcctSysEntry> {
	/**
	 * 校验并批量添加数据
	 * @param entryAddBoList 会计核算体系之会计主体集合
	 * @return
	 */
    Integer insertByAddBoList(List<TOrgAcctSysEntryAddBo> entryAddBoList);

	/**
	 * 校验并批量编辑数据
	 * @param editList 会计核算体系之会计主体集合
	 * @return
	 */
	Integer updateByEditList(List<TOrgAcctSysEntryEditBo> editList);

	/**
	 * 按核算体系id查询会计主体列表
	 * @param fAcctsystemId
	 * @return
	 */
    List<TOrgAcctSysEntryVo> queryListByFAcctsystemId(Integer fAcctsystemId);
}
