package com.skeqi.finance.service.rate;

import com.skeqi.finance.domain.rate.TBdRateType;
import com.skeqi.finance.pojo.vo.basicinformation.rate.TBdRateTypeVo;
import com.skeqi.finance.pojo.bo.rate.TBdRateTypeQueryBo;
import com.skeqi.finance.pojo.bo.rate.TBdRateTypeAddBo;
import com.skeqi.finance.pojo.bo.rate.TBdRateTypeEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 汇率类型Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdRateTypeService extends IServicePlus<TBdRateType> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdRateTypeVo queryById(Integer fRatetypeId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdRateTypeVo> queryPageList(TBdRateTypeQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdRateTypeVo> queryList(TBdRateTypeQueryBo bo);

	/**
	 * 根据新增业务对象插入汇率类型
	 * @param bo 汇率类型新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdRateTypeAddBo bo);

	/**
	 * 根据编辑业务对象修改汇率类型
	 * @param bo 汇率类型编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdRateTypeEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
