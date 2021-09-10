package com.skeqi.finance.service.endhandle.transfer;

import com.skeqi.finance.domain.endhandle.transfer.TGlAutoTransferEntry;
import com.skeqi.finance.pojo.vo.transfer.TGlAutoTransferEntryVo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryQueryBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryAddBo;
import com.skeqi.finance.pojo.bo.transfer.TGlAutoTransferEntryEditBo;
import com.skeqi.common.core.mybatisplus.core.IServicePlus;
import com.skeqi.common.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 自动转账分录Service接口
 *
 * @author toms
 * @date 2021-07-26
 */
public interface ITGlAutoTransferEntryService extends IServicePlus<TGlAutoTransferEntry> {
	/**
	 * 查询单个
	 * @return
	 */
	TGlAutoTransferEntryVo queryById(Integer fTransferEntryId);

	/**
	 * 查询列表
	 */
    TableDataInfo<TGlAutoTransferEntryVo> queryPageList(TGlAutoTransferEntryQueryBo bo);

	/**
	 * 查询列表
	 */
	List<TGlAutoTransferEntryVo> queryList(TGlAutoTransferEntryQueryBo bo);

	List<TGlAutoTransferEntryVo> queryListByPid(Integer pId);
	/**
	 * 根据新增业务对象插入自动转账分录
	 * @param bo 自动转账分录新增业务对象
	 * @return
	 */
	Boolean insertByAddBo(TGlAutoTransferEntryAddBo bo);

	/**
	 * 根据编辑业务对象修改自动转账分录
	 * @param bo 自动转账分录编辑业务对象
	 * @return
	 */
	Boolean updateByEditBo(TGlAutoTransferEntryEditBo bo);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Integer> ids, Boolean isValid);
}
