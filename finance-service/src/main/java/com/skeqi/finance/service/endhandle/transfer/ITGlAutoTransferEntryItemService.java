package com.skeqi.finance.service.endhandle.transfer;

import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransferEntryItem;
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferEntryItemVo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryItemQueryBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryItemAddBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryItemEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 自动转账核算维度Service接口
 *
 * @author toms
 * @date 2021-07-26
 */
public interface ITGlAutoTransferEntryItemService extends IServicePlus<TGlAutoTransferEntryItem> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlAutoTransferEntryItemVo queryById(Integer fEntryId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlAutoTransferEntryItemVo> queryPageList(TGlAutoTransferEntryItemQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlAutoTransferEntryItemVo> queryList(TGlAutoTransferEntryItemQueryBo bo);

	/**
	 * 根据新增业务对象插入自动转账核算维度
	 * @param bo 自动转账核算维度新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlAutoTransferEntryItemAddBo bo);

	/**
	 * 根据编辑业务对象修改自动转账核算维度
	 * @param bo 自动转账核算维度编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlAutoTransferEntryItemEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
