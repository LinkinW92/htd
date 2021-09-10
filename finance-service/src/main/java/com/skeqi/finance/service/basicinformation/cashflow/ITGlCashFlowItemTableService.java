package com.skeqi.finance.service.basicinformation.cashflow;

import com.skeqi.finance.domain.basicinformation.cashflow.TGlCashFlowItemTable;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowItemTableVo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowItemTableQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowItemTableAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowItemTableEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 现金流量项目-1Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITGlCashFlowItemTableService extends IServicePlus<TGlCashFlowItemTable> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlCashFlowItemTableVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlCashFlowItemTableVo> queryPageList(TGlCashFlowItemTableQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlCashFlowItemTableVo> queryList(TGlCashFlowItemTableQueryBo bo);

	/**
	 * 根据新增业务对象插入现金流量项目-1
	 * @param bo 现金流量项目-1新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlCashFlowItemTableAddBo bo);

	/**
	 * 根据编辑业务对象修改现金流量项目-1
	 * @param bo 现金流量项目-1编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlCashFlowItemTableEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键
	 * @return
	 */
	Boolean deleteWithValidById(Collection<Integer> ids);
}
