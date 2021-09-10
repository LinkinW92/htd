package com.skeqi.finance.service.rate;

import com.skeqi.finance.domain.rate.TBdRate;
import com.skeqi.finance.pojo.vo.basicinformation.rate.TBdRateVo;
import com.skeqi.finance.pojo.bo.rate.TBdRateQueryBo;
import com.skeqi.finance.pojo.bo.rate.TBdRateAddBo;
import com.skeqi.finance.pojo.bo.rate.TBdRateEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 基础汇率Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdRateService extends IServicePlus<TBdRate> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdRateVo queryById(Integer fRateId);

	/**
	 * 查询汇率
	 * @param bo
	 * @return
	 */
	TBdRate getRate(Date date, Integer rateTypeId, Integer fCyforid,Integer fCytoid);


	/**
	 * 查询列表
	 */
    TableDataInfo<TBdRateVo> queryPageList(TBdRateQueryBo bo);

	List<TBdRateVo> getRateList(TBdRateQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdRateVo> queryList(TBdRateQueryBo bo);

	/**
	 * 根据新增业务对象插入基础汇率
	 * @param bo 基础汇率新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdRateAddBo bo);

	/**
	 * 根据编辑业务对象修改基础汇率
	 * @param bo 基础汇率编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdRateEditBo bo);

	/**
	 * 审核
	 * @param ids
	 * @return
	 */
	Boolean audit(Collection<Integer> ids);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
