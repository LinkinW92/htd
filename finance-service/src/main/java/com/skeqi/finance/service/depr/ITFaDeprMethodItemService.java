package com.skeqi.finance.service.depr;

import com.skeqi.finance.domain.TFaDeprMethodItem;
import com.skeqi.finance.pojo.vo.basicinformation.depr.TFaDeprMethodItemVo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodItemQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodItemAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodItemEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 折旧方法元素Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITFaDeprMethodItemService extends IServicePlus<TFaDeprMethodItem> {
	/**
	 * 查询单个
	 * @return
	 */
	TFaDeprMethodItemVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TFaDeprMethodItemVo> queryPageList(TFaDeprMethodItemQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TFaDeprMethodItemVo> queryList(TFaDeprMethodItemQueryBo bo);

	/**
	 * 根据新增业务对象插入折旧方法元素
	 * @param bo 折旧方法元素新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TFaDeprMethodItemAddBo bo);

	/**
	 * 根据编辑业务对象修改折旧方法元素
	 * @param bo 折旧方法元素编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TFaDeprMethodItemEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
