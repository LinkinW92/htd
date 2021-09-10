package com.skeqi.finance.service.depr;

import com.skeqi.finance.domain.TFaDeprPolicy;
import com.skeqi.finance.pojo.vo.basicinformation.depr.TFaDeprPolicyVo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprPolicyQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprPolicyAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.depr.TFaDeprPolicyEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 折旧政策Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITFaDeprPolicyService extends IServicePlus<TFaDeprPolicy> {
	/**
	 * 查询单个
	 * @return
	 */
	TFaDeprPolicyVo queryById(Integer fPolicyId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TFaDeprPolicyVo> queryPageList(TFaDeprPolicyQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TFaDeprPolicyVo> queryList(TFaDeprPolicyQueryBo bo);

	/**
	 * 根据新增业务对象插入折旧政策
	 * @param bo 折旧政策新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TFaDeprPolicyAddBo bo);

	/**
	 * 根据编辑业务对象修改折旧政策
	 * @param bo 折旧政策编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TFaDeprPolicyEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids);

	/**
	 * 审核
	 * @param ids
	 * @return
	 */
	Boolean auditByIds(List<Integer> ids);

	/**
	 * 反审核
	 * @param ids
	 * @return
	 */
	Boolean antiAuditByIds(List<Integer> ids);
}
