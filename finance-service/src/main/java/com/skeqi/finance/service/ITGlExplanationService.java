package com.skeqi.finance.service;

import com.skeqi.finance.domain.TGlExplanation;
import com.skeqi.finance.pojo.vo.TGlExplanationVo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.explanation.TGlExplanationEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 摘要库Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITGlExplanationService extends IServicePlus<TGlExplanation> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlExplanationVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlExplanationVo> queryPageList(TGlExplanationQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlExplanationVo> queryList(TGlExplanationQueryBo bo);

	/**
	 * 根据新增业务对象插入摘要库
	 * @param bo 摘要库新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlExplanationAddBo bo);

	/**
	 * 根据编辑业务对象修改摘要库
	 * @param bo 摘要库编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlExplanationEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);

	boolean auditWithValidByIds(List<Integer> asList);

	boolean deAuditWithValidByIds(List<Integer> asList);

	boolean disableWithValidByIds(List<Integer> asList);

	boolean deDisableWithValidByIds(List<Integer> asList);
}
