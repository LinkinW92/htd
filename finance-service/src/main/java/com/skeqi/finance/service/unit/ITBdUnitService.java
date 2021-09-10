package com.skeqi.finance.service.unit;

import com.skeqi.finance.domain.unit.TBdUnit;
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitVo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitQueryBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitAddBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 计量单位Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdUnitService extends IServicePlus<TBdUnit> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdUnitVo queryById(Integer fUnitId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdUnitVo> queryPageList(TBdUnitQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdUnitVo> queryList(TBdUnitQueryBo bo);

	/**
	 * 根据新增业务对象插入计量单位
	 * @param bo 计量单位新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdUnitAddBo bo);

	/**
	 * 根据编辑业务对象修改计量单位
	 * @param bo 计量单位编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdUnitEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
