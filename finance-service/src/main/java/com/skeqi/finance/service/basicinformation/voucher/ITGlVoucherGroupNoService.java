package com.skeqi.finance.service.basicinformation.voucher;

import com.skeqi.finance.domain.TGlVoucherGroupNo;
import com.skeqi.finance.pojo.vo.TGlVoucherGroupNoVo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherGroupNoQueryBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherGroupNoAddBo;
import com.skeqi.finance.pojo.bo.voucher.TGlVoucherGroupNoEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证号排序Service接口
 *
 * @author toms
 * @date 2021-08-09
 */
public interface ITGlVoucherGroupNoService extends IServicePlus<TGlVoucherGroupNo> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlVoucherGroupNoVo queryById(Integer fId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlVoucherGroupNoVo> queryPageList(TGlVoucherGroupNoQueryBo bo);

	/**
	 * 查询凭证号排序
	 * @param bo
	 * @return
	 */
	List<Integer>  listVchNo(TGlVoucherGroupNoQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlVoucherGroupNoVo> queryList(TGlVoucherGroupNoQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证号排序
	 * @param bo 凭证号排序新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlVoucherGroupNoAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证号排序
	 * @param bo 凭证号排序编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlVoucherGroupNoEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
