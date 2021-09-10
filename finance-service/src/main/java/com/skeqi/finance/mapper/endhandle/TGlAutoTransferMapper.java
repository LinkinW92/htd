package com.skeqi.finance.mapper.endhandle;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeqi.finance.domain.endhandle.VchQueryBo;
import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransfer;
import com.skeqi.common.core.mybatisplus.core.BaseMapperPlus;
import com.skeqi.common.core.mybatisplus.cache.MybatisPlusRedisCache;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferQueryBo;
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferVo;
import com.skeqi.finance.pojo.vo.voucher.TGlVoucherVo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自动转账主Mapper接口
 *
 * @author toms
 * @date 2021-07-26
 */
// 如使需切换数据源 请勿使用缓存 会造成数据不一致现象
@CacheNamespace(implementation = MybatisPlusRedisCache.class, eviction = MybatisPlusRedisCache.class)
public interface TGlAutoTransferMapper extends BaseMapperPlus<TGlAutoTransfer> {

	IPage<TGlAutoTransferVo> queryPageList(Page<TGlAutoTransferQueryBo> page,@Param("bo") TGlAutoTransferQueryBo bo);

	IPage<TGlVoucherVo> queryVchLogPage(Page<VchQueryBo> page,@Param("bo") VchQueryBo bo);

	TGlAutoTransferVo queryInfoById(@Param("id") Integer id);


}
