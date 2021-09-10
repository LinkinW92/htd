package com.skeqi.finance.service.basicinformation.cashflow;

import com.skeqi.finance.domain.basicinformation.cashflow.TGlCashFlow;
import com.skeqi.finance.pojo.bo.basicinformation.accountingpolicies.TFaAcctPolicyEditBo;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowVo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 现金流量项目-3Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITGlCashFlowService extends IServicePlus<TGlCashFlow> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlCashFlowVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlCashFlowVo> queryPageList(TGlCashFlowQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlCashFlowVo> queryList(TGlCashFlowQueryBo bo);

	/**
	 * 根据新增业务对象插入现金流量项目-3
	 * @param bo 现金流量项目-3新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlCashFlowAddBo bo);

	/**
	 * 根据编辑业务对象修改现金流量项目-3
	 * @param bo 现金流量项目-3编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlCashFlowEditBo bo);

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
	 * 禁用
	 * @param bo
	 * @return
	 */
	Boolean disableByEditBo(TGlCashFlowEditBo bo);

	/**
	 * 反审核
	 * @param ids
	 * @return
	 */
    Boolean antiAuditByIds(List<Integer> ids);
}
