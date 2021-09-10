package com.skeqi.finance.service.basicinformation.voucher;

import com.skeqi.finance.domain.basicinformation.voucher.TBdVoucherGroup;
import com.skeqi.finance.pojo.vo.basicinformation.voucher.TBdVoucherGroupVo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupQueryBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupAddBo;
import com.skeqi.finance.pojo.bo.basicinformation.voucher.TBdVoucherGroupEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 凭证字Service接口
 *
 * @author toms
 * @date 2021-07-09
 */
public interface ITBdVoucherGroupService extends IServicePlus<TBdVoucherGroup> {
	/**
	 * 查询单个
	 * @return
	 */
	TBdVoucherGroupVo queryById(Integer fVchgroupId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TBdVoucherGroupVo> queryPageList(TBdVoucherGroupQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TBdVoucherGroupVo> queryList(TBdVoucherGroupQueryBo bo);

	/**
	 * 根据新增业务对象插入凭证字
	 * @param bo 凭证字新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TBdVoucherGroupAddBo bo);

	/**
	 * 根据编辑业务对象修改凭证字
	 * @param bo 凭证字编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TBdVoucherGroupEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @return
	 */
	Boolean deleteWithValidByIds(List<Integer> ids);

	/**
	 * 凭证字审核
	 * @param ids
	 * @return
	 */
	Boolean auditByIds(List<Integer> ids);

	/**
	 * 凭证字反审核
	 * @param ids
	 * @return
	 */
	Boolean antiAuditByIds(List<Integer> ids);


	/**
	 * 凭证字禁用
	 * @param bo
	 * @return
	 */
	Boolean disableByEditBo(TBdVoucherGroupEditBo bo);

}
