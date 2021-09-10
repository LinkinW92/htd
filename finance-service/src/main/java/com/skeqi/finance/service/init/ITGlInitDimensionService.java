package com.skeqi.finance.service.init;

import com.skeqi.finance.domain.TGlInitDimension;
import com.skeqi.finance.pojo.vo.TGlInitDimensionVo;
import com.skeqi.finance.pojo.bo.basicinformation.init.TGlInitDimensionQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.init.TGlInitDimensionAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.init.TGlInitDimensionEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 科目核算维度初始数据Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITGlInitDimensionService extends IServicePlus<TGlInitDimension> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlInitDimensionVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlInitDimensionVo> queryPageList(TGlInitDimensionQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlInitDimensionVo> queryList(TGlInitDimensionQueryBo bo);

	/**
	 * 根据新增业务对象插入科目核算维度初始数据
	 * @param bo 科目核算维度初始数据新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlInitDimensionAddBo bo);

	/**
	 * 根据编辑业务对象修改科目核算维度初始数据
	 * @param bo 科目核算维度初始数据编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlInitDimensionEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
