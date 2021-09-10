package com.skeqi.finance.service.basicinformation.cashflow;

import com.skeqi.finance.domain.basicinformation.cashflow.TGlCashFlowType;
import com.skeqi.finance.pojo.vo.basicinformation.cashflow.TGlCashFlowTypeVo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowTypeQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowTypeAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.cashflow.TGlCashFlowTypeEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 现金流量项目类别-2Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITGlCashFlowTypeService extends IServicePlus<TGlCashFlowType> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlCashFlowTypeVo queryById(Integer fItemTypeid);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlCashFlowTypeVo> queryPageList(TGlCashFlowTypeQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlCashFlowTypeVo> queryList(TGlCashFlowTypeQueryBo bo);

	/**
	 * 根据新增业务对象插入现金流量项目类别-2
	 * @param bo 现金流量项目类别-2新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlCashFlowTypeAddBo bo);

	/**
	 * 根据编辑业务对象修改现金流量项目类别-2
	 * @param bo 现金流量项目类别-2编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlCashFlowTypeEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键
	 * @return
	 */
	Boolean deleteWithValidById(Collection<Integer> ids);
}
