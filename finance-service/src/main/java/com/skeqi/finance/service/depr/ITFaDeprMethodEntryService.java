package com.skeqi.finance.service.depr;

import com.skeqi.finance.domain.TFaDeprMethodEntry;
import com.skeqi.finance.pojo.vo.basicinformation.depr.TFaDeprMethodEntryVo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodEntryQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodEntryAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodEntryEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 折旧方法明细Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITFaDeprMethodEntryService extends IServicePlus<TFaDeprMethodEntry> {
	/**
	 * 查询单个
	 * @return
	 */
	TFaDeprMethodEntryVo queryById(Integer fEntryId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TFaDeprMethodEntryVo> queryPageList(TFaDeprMethodEntryQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TFaDeprMethodEntryVo> queryList(TFaDeprMethodEntryQueryBo bo);

	/**
	 * 根据新增业务对象插入折旧方法明细
	 * @param bo 折旧方法明细新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TFaDeprMethodEntryAddBo bo);

	/**
	 * 根据编辑业务对象修改折旧方法明细
	 * @param bo 折旧方法明细编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TFaDeprMethodEntryEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
