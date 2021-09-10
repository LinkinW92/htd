package com.skeqi.finance.mapper.report;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.finance.domain.report.DimensionBalance;
import com.skeqi.finance.pojo.bo.report.DimensionBalanceQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherEntryQueryBo;
import com.skeqi.finance.pojo.vo.basicinformation.help.TBdHelpDataVo;
import com.skeqi.finance.pojo.vo.dimension.TBdFlexItemPropertyVo;
import com.skeqi.finance.pojo.vo.report.DimensionBalanceVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 核算维度余额表Mapper接口
 *
 * @date 2021-08-09
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface DimensionBalanceMapper extends BaseMapperPlus<DimensionBalance> {

	/**
	 * 查询核算维度余额列表
	 * @param userPage
	 * @param bo
	 * @return
	 */
	IPage<DimensionBalanceVo> queryPageList(Page<DimensionBalanceQueryBo> userPage, @Param("bo") DimensionBalanceQueryBo bo);

	/**
	 * 按科目内码id查询核算维度类别列表
	 * @param fAccountId
	 * @return
	 */
	List<TBdFlexItemPropertyVo> dimensionList(Integer fAccountId);

	/**
	 * 查询核算维度明细账
	 * @param bo
	 * @return
	 */
	List<Map<String, Object>> queryDimensionDetails(@Param("bo") TGlVoucherEntryQueryBo bo);

	/**
	 * 按核算维度类别id查询核算维度表列表
	 * @param flexItemPropertyId
	 * @return
	 */
	TBdFlexItemPropertyVo selectByFlexItemPropertyId(Integer flexItemPropertyId);

	/**
	 * 按核算维度类别id查询辅助资料列表
	 * @param flexItemPropertyId
	 * @return
	 */
	List<TBdHelpDataVo> selectHelpDataByFlexItemPropertyId(Integer flexItemPropertyId);
}
