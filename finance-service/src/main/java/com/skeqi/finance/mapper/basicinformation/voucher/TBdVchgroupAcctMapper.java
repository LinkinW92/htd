package com.skeqi.finance.mapper.basicinformation.voucher;

import com.skeqi.finance.domain.basicinformation.voucher.TBdVchgroupAcct;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVchgroupAcctEditBo;
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVchgroupAcctVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 凭证字-科目控制Mapper接口
 *
 * @author toms
 * @date 2021-07-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TBdVchgroupAcctMapper extends BaseMapperPlus<TBdVchgroupAcct> {

	/**
	 * 校验并批量添加数据
	 * @param acctAddBoList 凭证字-科目控制集合
	 * @return
	 */
    Integer insertByAddBoList(List<TBdVchgroupAcctAddBo> acctAddBoList);

	Integer updateByEditList(List<TBdVchgroupAcctEditBo> editList);

	/**
	 * 按凭证子内码查询科目控制列表
	 * @param fVchgroupId
	 * @return
	 */
	List<TBdVchgroupAcctVo> selectByFVchgroupId(@Param("fVchgroupId") Integer fVchgroupId);

}
