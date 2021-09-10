package com.skeqi.finance.service.basicinformation.base;

import com.skeqi.finance.domain.TBdBusinessArea;
import com.skeqi.finance.pojo.vo.basicinformation.base.TBdBusinessAreaVo;
import com.skeqi.finance.pojo.bo.basicinformation.area.TBdBusinessAreaQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.area.TBdBusinessAreaAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.area.TBdBusinessAreaEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 业务领域Service接口
 *
 * @author toms
 * @date 2021-07-13
 */
public interface ITBdBusinessAreaService extends IServicePlus<TBdBusinessArea> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdBusinessAreaVo queryById(Integer id);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdBusinessAreaVo> queryPageList(TBdBusinessAreaQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdBusinessAreaVo> queryList(TBdBusinessAreaQueryBo bo);

	/**
	 * 根据新增业务对象插入业务领域
	 * @param bo 业务领域新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdBusinessAreaAddBo bo);

	/**
	 * 根据编辑业务对象修改业务领域
	 * @param bo 业务领域编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdBusinessAreaEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
