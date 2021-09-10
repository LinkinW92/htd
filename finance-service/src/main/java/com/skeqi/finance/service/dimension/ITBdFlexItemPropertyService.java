package com.skeqi.finance.service.dimension;

import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;
import com.skeqi.finance.domain.TBdFlexItemProperty;
import com.skeqi.finance.pojo.bo.TBdFlexItemProperty.TBdFlexItemPropertyAddBo;
import com.skeqi.finance.pojo.bo.TBdFlexItemProperty.TBdFlexItemPropertyEditBo;
import com.skeqi.finance.pojo.bo.TBdFlexItemProperty.TBdFlexItemPropertyQueryBo;
import com.skeqi.finance.pojo.vo.dimension.TBdFlexItemPropertyVo;

import java.util.Collection;
import java.util.List;

/**
 * 核算维度Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdFlexItemPropertyService extends IServicePlus<TBdFlexItemProperty> {
	/**
	 * 查询单个
	 *
	 * @return
	 */
	TBdFlexItemPropertyVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
	TableDataInfo<TBdFlexItemPropertyVo> queryPageList(TBdFlexItemPropertyQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdFlexItemPropertyVo> queryList(TBdFlexItemPropertyQueryBo bo);

	/**
	 * 根据新增业务对象插入核算维度
	 *
	 * @param bo 核算维度新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdFlexItemPropertyAddBo bo);

	/**
	 * 根据编辑业务对象修改核算维度
	 *
	 * @param bo 核算维度编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdFlexItemPropertyEditBo bo);

	/**
	 * 校验并删除数据
	 *
	 * @param ids     主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
