package com.skeqi.finance.service.unit;

import com.skeqi.finance.domain.unit.TBdUnitGroup;
import com.skeqi.finance.pojo.vo.basicinformation.unit.TBdUnitGroupVo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupQueryBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupAddBo;
import com.skeqi.finance.pojo.bo.unit.TBdUnitGroupEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 计量单位组Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdUnitGroupService extends IServicePlus<TBdUnitGroup> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdUnitGroupVo queryById(Integer fUnitGroupId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdUnitGroupVo> queryPageList(TBdUnitGroupQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdUnitGroupVo> queryList(TBdUnitGroupQueryBo bo);

	/**
	 * 根据新增业务对象插入计量单位组
	 * @param bo 计量单位组新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdUnitGroupAddBo bo);

	/**
	 * 根据编辑业务对象修改计量单位组
	 * @param bo 计量单位组编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdUnitGroupEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
