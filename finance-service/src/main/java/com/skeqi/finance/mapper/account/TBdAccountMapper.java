package com.skeqi.finance.mapper.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.finance.domain.TBdAccount;
import com.skeqi.finance.pojo.bo.TBdAccount.TBdAccountQueryBo;
import com.skeqi.finance.pojo.vo.TBdAccount.TBdAccountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 科目信息Mapper接口
 *
 * @author toms
 * @date 2021-07-19
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
//@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TBdAccountMapper extends BaseMapperPlus<TBdAccount> {

	Map getGlCashFlow(@Param("fId") Integer fId);


	TBdAccountVo selectByFAcctNumber(@Param("fAcctNumber") String fAcctNumber, @Param("fAccttableId") Integer fAccttableId);

	/**
	 *查询科目信息
	 */
	TBdAccountVo queryOneById( @Param("id") Integer id);
	/**
	 *查询科目信息
	 */
	Page<TBdAccountVo> queryList2(IPage<TBdAccountVo> page, @Param("bo") TBdAccountQueryBo bo);

	/**
	 *查询科目维度组名称
	 */
	List<String> queryFlexentryNameList(@Param("fAcctId") Integer fAcctId);

	/**
	 * 查询外币核算组名称
	 */
	List<String>  queryCurrencyNameList(@Param("currencyStr") String currencyStr);

	/**
	 *查询科目维度组
	 */
	List<Map> queryFlexentryList(@Param("fAcctId") Integer fAcctId);

	/**
	 * 查询外币核算组
	 */
	List<Map>  queryCurrencyList(@Param("currencyStr") String currencyStr);

	/**
	 * 按现金流量项目id查询科目信息
	 * @param fCashFlowId
	 * @return
	 */
    List<TBdAccount> selectByFCashFlowId(Integer fCashFlowId);
}
