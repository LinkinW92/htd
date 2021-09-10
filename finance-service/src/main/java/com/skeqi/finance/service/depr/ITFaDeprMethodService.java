package com.skeqi.finance.service.depr;

import com.skeqi.finance.domain.TFaDeprMethod;
import com.skeqi.finance.pojo.vo.basicinformation.depr.TFaDeprMethodVo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprMethodEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 折旧方法Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITFaDeprMethodService extends IServicePlus<TFaDeprMethod> {
	/**
	 * 查询单个
	 * @return
	 */
	TFaDeprMethodVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TFaDeprMethodVo> queryPageList(TFaDeprMethodQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TFaDeprMethodVo> queryList(TFaDeprMethodQueryBo bo);

	/**
	 * 根据新增业务对象插入折旧方法
	 * @param bo 折旧方法新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TFaDeprMethodAddBo bo);

	/**
	 * 根据编辑业务对象修改折旧方法
	 * @param bo 折旧方法编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TFaDeprMethodEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
