package com.skeqi.finance.service.unit;

import com.skeqi.finance.domain.unit.TBdUnitConvertRate;
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitConvertRateVo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitConvertRateQueryBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitConvertRateAddBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitConvertRateEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 单位换算Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdUnitConvertRateService extends IServicePlus<TBdUnitConvertRate> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdUnitConvertRateVo queryById(Integer fUnitConvertRateid);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdUnitConvertRateVo> queryPageList(TBdUnitConvertRateQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdUnitConvertRateVo> queryList(TBdUnitConvertRateQueryBo bo);

	/**
	 * 根据新增业务对象插入单位换算
	 * @param bo 单位换算新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdUnitConvertRateAddBo bo);

	/**
	 * 根据编辑业务对象修改单位换算
	 * @param bo 单位换算编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdUnitConvertRateEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
