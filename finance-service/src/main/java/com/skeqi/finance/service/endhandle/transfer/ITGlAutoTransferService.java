package com.skeqi.finance.service.endhandle.transfer;

import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.finance.domain.endhandle.VchQueryBo;
import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransfer;
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferVo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferQueryBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferAddBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 自动转账主Service接口
 *
 * @author toms
 * @date 2021-07-26
 */
public interface ITGlAutoTransferService extends IServicePlus<TGlAutoTransfer> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlAutoTransferVo queryById(Integer fTransferId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlAutoTransferVo> queryPageList(TGlAutoTransferQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlAutoTransferVo> queryList(TGlAutoTransferQueryBo bo);

	/**
	 * 根据新增业务对象插入自动转账主
	 * @param bo 自动转账主新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlAutoTransferAddBo bo);

	/**
	 * 根据编辑业务对象修改自动转账主
	 * @param bo 自动转账主编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlAutoTransferEditBo bo);

	/**
	 * 执行记录
	 * @param fId
	 * @return
	 */
	AjaxResult execute(Integer fId);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);

	/**
	 * 查询生成凭证日志
	 * @return
	 */
	AjaxResult queryVchLogPage(VchQueryBo bo);

	/**
	 * 结账
	 * @param ids
	 * @return
	 */
	AjaxResult settleAcct(Collection<Integer> ids);

	/**
	 * 反结账
	 * @param ids
	 * @return
	 */
	AjaxResult settleAcctNo(Collection<Integer> ids);
}
