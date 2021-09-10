package com.skeqi.finance.service.dimension;

import com.skeqi.finance.domain.TBdDimensionSource;
import com.skeqi.finance.pojo.bo.TBdDimensionSource.*;
import com.skeqi.finance.pojo.vo.dimension.DimensionSourceData;
import com.skeqi.finance.pojo.vo.dimension.TBdDimensionSourceVo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 维度来源Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdDimensionSourceService extends IServicePlus<TBdDimensionSource> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdDimensionSourceVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdDimensionSourceVo> queryPageList(TBdDimensionSourceQueryBo bo);
	/**
	 * 查询列表
	 */
	DimensionSourceData queryData(int id);


	/**
	 * 查询列表
	 */
	List<TBdDimensionSourceVo> queryList(TBdDimensionSourceQueryBo bo);

	/**
	 * 根据新增业务对象插入维度来源
	 * @param bo 维度来源新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdDimensionSourceAddBo bo);

	/**
	 * 根据编辑业务对象修改维度来源
	 * @param bo 维度来源编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdDimensionSourceEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);

}
