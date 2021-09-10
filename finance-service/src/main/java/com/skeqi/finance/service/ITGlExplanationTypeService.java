package com.skeqi.finance.service;

import com.skeqi.finance.domain.TGlExplanationType;
import com.skeqi.finance.pojo.vo.TGlExplanationTypeVo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationTypeQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationTypeAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationTypeEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 摘要类别Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITGlExplanationTypeService extends IServicePlus<TGlExplanationType> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlExplanationTypeVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlExplanationTypeVo> queryPageList(TGlExplanationTypeQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlExplanationTypeVo> queryList(TGlExplanationTypeQueryBo bo);

	/**
	 * 根据新增业务对象插入摘要类别
	 * @param bo 摘要类别新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlExplanationTypeAddBo bo);

	/**
	 * 根据编辑业务对象修改摘要类别
	 * @param bo 摘要类别编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlExplanationTypeEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
