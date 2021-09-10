package com.skeqi.finance.service.unit;

import com.skeqi.finance.domain.unit.TBdUnitGroupTable;
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitGroupTableVo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupTableQueryBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupTableAddBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupTableEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 计量单位分组Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdUnitGroupTableService extends IServicePlus<TBdUnitGroupTable> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdUnitGroupTableVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdUnitGroupTableVo> queryPageList(TBdUnitGroupTableQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdUnitGroupTableVo> queryList(TBdUnitGroupTableQueryBo bo);

	/**
	 * 根据新增业务对象插入计量单位分组
	 * @param bo 计量单位分组新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdUnitGroupTableAddBo bo);

	/**
	 * 根据编辑业务对象修改计量单位分组
	 * @param bo 计量单位分组编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdUnitGroupTableEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
